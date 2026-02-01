package dev.dragonofshuu.candylands.registries.spread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.apache.commons.lang3.ObjectUtils;

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
    public static SpreadReturnType applySpread(SpreadContext context, SpreadRules rules) {
        // Check conditions
        for (SpreadCondition condition : rules.conditions) {
            if (!condition.canSpread(context)) {
                return new SpreadReturnType(false, false, true);
            }
        }

        // Gather blocks to convert
        List<BlockPos> blocksToConvert = fetchConvertableBlocks(context, rules);

        if (blocksToConvert == null) {
            return new SpreadReturnType(false, false, false);
        }

        // Convert blocks
        boolean didSpread = false;
        for (BlockPos blockPos : blocksToConvert) {
            SpreadContext.Target blockTarget = context.withTarget(blockPos);
            BlockState blockTargetState = blockTarget.targetState();

            SpreadConverter conversionRules = getConversionRulesForBlockState(blockTargetState, rules);

            if (conversionRules == null) {
                throw new IllegalStateException("No conversion mapping found for block state: " + blockPos);
            }
            if (!conversionRules.canConvert(blockTarget)) {
                continue;
            }
            didSpread = context.level().setBlock(blockPos, conversionRules.toState(), 3) || didSpread;
            if (rules.biome != null && didSpread) {
                spreadBiome(context.level(), getBiomeHolder(context.level(), rules.biome), context.sourcePos(),
                        blockPos);
            }
        }
        // return new SpreadReturnType(didSpread, !blocksToConvert.isEmpty(), false);
        // canSpread is true if there were blocks that we did convert, as the only
        // thing that will stop it from converting is conditions not being met,
        // but it's possible there is a block we could convert but the conversion
        // conditions
        // are not met, for example dirt blocks surrounded by stone.
        return new SpreadReturnType(didSpread, didSpread, false);
    }

    private static @Nullable List<BlockPos> fetchConvertableBlocks(SpreadContext context, SpreadRules rules) {
        BlockPos centerPos = context.sourcePos();
        List<BlockPos> potentialBlocks = getAllNearbyBlocks(context.level(), rules.maxDistances, centerPos,
                context.random());
        List<BlockPos> potentialButConvertableBlocks = filterConvertableBlocks(context.level(), potentialBlocks,
                rules);

        if (potentialButConvertableBlocks.isEmpty()) {
            return null;
        }

        if (rules.isSmart) {
            return selectRandomQuantityOfBlocks(
                    potentialButConvertableBlocks,
                    rules.minConversions,
                    rules.maxConversions,
                    rules.isSmart,
                    context.random());
        }

        return selectRandomQuantityOfBlocks(
                potentialBlocks,
                rules.minConversions,
                rules.maxConversions,
                rules.isSmart,
                context.random());
    }

    private static List<BlockPos> selectRandomQuantityOfBlocks(
            List<BlockPos> potentialButConvertableBlocks,
            int minConversions,
            int maxConversions,
            boolean isSmart,
            RandomSource random) {
        List<BlockPos> chosenBlocks = new ArrayList<BlockPos>();
        int conversions = random.nextIntBetweenInclusive(minConversions, maxConversions);
        for (int i = 0; i < Math.min(conversions, potentialButConvertableBlocks.size()); i++) {
            int index = random.nextInt(potentialButConvertableBlocks.size());
            chosenBlocks.add(
                    isSmart ? potentialButConvertableBlocks.remove(index) : potentialButConvertableBlocks.get(index));
        }
        return chosenBlocks;
    }

    private static List<BlockPos> filterConvertableBlocks(
            ServerLevel level,
            Collection<BlockPos> filterableBlocks,
            SpreadRules rules) {
        List<BlockPos> potentialButConvertableBlocks = new ArrayList<BlockPos>();
        for (BlockPos blockPos : filterableBlocks) {
            if (getConversionRulesForBlockState(level.getBlockState(blockPos), rules) != null) {
                potentialButConvertableBlocks.add(blockPos);
            }
        }
        return potentialButConvertableBlocks;
    }

    private static SpreadConverter getConversionRulesForBlockState(
            BlockState blockState,
            SpreadRules rules) {
        SpreadConverter blockstateConversionRules = rules.conversionMap.get(blockState);
        SpreadConverter blockConversionRules = rules.blockConversionMap.get(blockState.getBlock());

        return ObjectUtils.firstNonNull(
                blockstateConversionRules,
                blockConversionRules);
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
