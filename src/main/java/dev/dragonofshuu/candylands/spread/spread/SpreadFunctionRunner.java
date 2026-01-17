package dev.dragonofshuu.candylands.spread.spread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class SpreadFunctionRunner {
    public static boolean applySpread(SpreadContext context, SpreadRules rules) {
        // Check conditions
        for (SpreadCondition condition : rules.conditions) {
            if (!condition.canSpread(context)) {
                return false;
            }
        }

        // Gather blocks to convert
        List<BlockPos> blocksToConvert = fetchConvertableBlocks(context, rules);

        // Convert blocks
        boolean didSpread = false;
        for (BlockPos blockPos : blocksToConvert) {
            SpreadConverter conversionRules = rules.conversionMap.get(context.level().getBlockState(blockPos));
            if (conversionRules == null) {
                throw new IllegalStateException("No conversion mapping found for block state: " + blockPos);
            }
            if (!conversionRules.canConvert(context)) {
                continue;
            }
            didSpread = context.level().setBlock(blockPos, conversionRules.toState(), 3) || didSpread;
            if (rules.biome != null && didSpread) {
                spreadBiome(context.level(), getBiomeHolder(context.level(), rules.biome), context.blockPos(),
                        blockPos);
            }
        }
        return didSpread;
    }

    private static List<BlockPos> fetchConvertableBlocks(SpreadContext context, SpreadRules rules) {
        BlockPos centerPos = context.blockPos();
        List<BlockPos> potentialBlocks = rules.isSmart
                ? getAllNearbyBlocks(context.level(), rules.maxDistances, centerPos, context.random())
                : getRandomNearbyBlocks(context.level(), rules.maxAttempts, rules.maxDistances, centerPos,
                        context.random());
        List<BlockPos> potentialButConvertableBlocks = filterConvertableBlocks(context.level(), potentialBlocks,
                rules.conversionMap.keySet());
        List<BlockPos> chosenBlocks = selectRandomQuantityOfBlocks(
                potentialButConvertableBlocks,
                rules.minConversions,
                rules.maxConversions,
                context.random());
        return chosenBlocks;
    }

    private static List<BlockPos> selectRandomQuantityOfBlocks(
            List<BlockPos> potentialButConvertableBlocks,
            int minConversions,
            int maxConversions,
            RandomSource random) {
        List<BlockPos> chosenBlocks = new ArrayList<BlockPos>();
        int conversions = random.nextIntBetweenInclusive(minConversions, maxConversions);
        for (int i = 0; i < Math.min(conversions, potentialButConvertableBlocks.size()); i++) {
            int index = random.nextInt(potentialButConvertableBlocks.size());
            chosenBlocks.add(potentialButConvertableBlocks.remove(index));
        }
        return chosenBlocks;
    }

    private static List<BlockPos> filterConvertableBlocks(
            ServerLevel level,
            Collection<BlockPos> filterableBlocks,
            Collection<BlockState> targetBlocks) {
        List<BlockPos> potentialButConvertableBlocks = new ArrayList<BlockPos>();
        for (BlockPos blockPos : filterableBlocks) {
            if (targetBlocks.contains(level.getBlockState(blockPos))) {
                potentialButConvertableBlocks.add(blockPos);
            }
        }
        return potentialButConvertableBlocks;
    }

    private static List<BlockPos> getRandomNearbyBlocks(ServerLevel level, int maxAttempts, Vec3i maxDistances,
            BlockPos centerPos, RandomSource random) {
        List<BlockPos> potentialBlocks = new ArrayList<BlockPos>();
        for (int i = 0; i < maxAttempts; i++) {
            BlockPos targetPos = centerPos.offset(
                    random.nextInt(1 + maxDistances.getX() * 2) - maxDistances.getX(),
                    random.nextInt(1 + maxDistances.getY() * 2) - maxDistances.getY(),
                    random.nextInt(1 + maxDistances.getZ() * 2) - maxDistances.getZ());
            potentialBlocks.add(targetPos);
        }
        return potentialBlocks;
    }

    private static List<BlockPos> getAllNearbyBlocks(ServerLevel level, Vec3i maxDistances, BlockPos centerPos,
            RandomSource random) {
        BoundingBox box = BoundingBox.fromCorners(
                centerPos.offset(-maxDistances.getX(), -maxDistances.getY(), -maxDistances.getZ()),
                centerPos.offset(maxDistances.getX(), maxDistances.getY(), maxDistances.getZ()));
        List<BlockPos> availablePositions = new ArrayList<BlockPos>();

        for (BlockPos blockPos : BlockPos.betweenClosed(
                box.minX(), box.minY(), box.minZ(),
                box.maxX(), box.maxY(), box.maxZ())) {
            availablePositions.add(blockPos.immutable());
        }
        return availablePositions;
    }

    private static Holder<Biome> getBiomeHolder(ServerLevel level, ResourceKey<Biome> biomeKey) {
        var registryAccess = level.registryAccess();
        var biome = registryAccess.holderOrThrow(biomeKey);
        return biome;
    }

    private static void spreadBiome(ServerLevel level, Holder<Biome> biome, BlockPos blockPos,
            BlockPos randomBlockPos) {
        var chunk = level.getChunk(randomBlockPos);

        chunk.fillBiomesFromNoise(
                makeResolver(chunk, BoundingBox.fromCorners(blockPos, randomBlockPos).inflatedBy(3, 8, 3), biome,
                        (oldBiome) -> true),
                level.getChunkSource().randomState().sampler());
        chunk.markUnsaved();
        level.getChunkSource().chunkMap.resendBiomesForChunks(List.of(chunk));
    }

    private static BiomeResolver makeResolver(
            ChunkAccess chunk, BoundingBox targetRegion, Holder<Biome> newBiome,
            Predicate<Holder<Biome>> filter) {
        return (p_262550_, p_262551_, p_262552_, p_262553_) -> {
            int i = QuartPos.toBlock(p_262550_);
            int j = QuartPos.toBlock(p_262551_);
            int k = QuartPos.toBlock(p_262552_);
            Holder<Biome> holder = chunk.getNoiseBiome(p_262550_, p_262551_, p_262552_);
            if (targetRegion.isInside(i, j, k) && filter.test(holder)) {
                return newBiome;
            } else {
                return holder;
            }
        };
    }
}
