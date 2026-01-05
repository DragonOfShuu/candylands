package dev.dragonofshuu.candylands.block.custom;

import java.util.List;
import java.util.function.Predicate;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import dev.dragonofshuu.candylands.util.MainGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.lighting.LightEngine;

public class CandyGrassBlock extends Block implements BonemealableBlock {
    public CandyGrassBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<CandyGrassBlock> CODEC = simpleCodec(CandyGrassBlock::new);

    @Override
    public MapCodec<CandyGrassBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256229_, BlockPos p_256432_, BlockState p_255677_) {
        return p_256229_.getBlockState(p_256432_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_221275_, RandomSource p_221276_, BlockPos p_221277_,
            BlockState p_221278_) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_,
            BlockState p_221273_) {
        // BlockPos blockpos = p_221272_.above();
        // BlockState blockstate = Blocks.SHORT_GRASS.defaultBlockState();
        // Optional<Holder.Reference<PlacedFeature>> optional =
        // p_221270_.registryAccess()
        // .lookupOrThrow(Registries.PLACED_FEATURE)
        // .get(VegetationPlacements.GRASS_BONEMEAL);

        // label51:
        // for (int i = 0; i < 128; i++) {
        // BlockPos blockpos1 = blockpos;

        // for (int j = 0; j < i / 16; j++) {
        // blockpos1 = blockpos1.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3)
        // - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
        // if (!p_221270_.getBlockState(blockpos1.below()).is(this) ||
        // p_221270_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_221270_,
        // blockpos1)) {
        // continue label51;
        // }
        // }

        // BlockState blockstate1 = p_221270_.getBlockState(blockpos1);
        // if (blockstate1.is(blockstate.getBlock()) && p_221271_.nextInt(10) == 0) {
        // BonemealableBlock bonemealableblock =
        // (BonemealableBlock)blockstate.getBlock();
        // if (bonemealableblock.isValidBonemealTarget(p_221270_, blockpos1,
        // blockstate1)) {
        // bonemealableblock.performBonemeal(p_221270_, p_221271_, blockpos1,
        // blockstate1);
        // }
        // }

        // if (blockstate1.isAir()) {
        // Holder<PlacedFeature> holder;
        // if (p_221271_.nextInt(8) == 0) {
        // List<ConfiguredFeature<?, ?>> list =
        // p_221270_.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
        // if (list.isEmpty()) {
        // continue;
        // }

        // int k = p_221271_.nextInt(list.size());
        // holder = ((RandomPatchConfiguration)list.get(k).config()).feature();
        // } else {
        // if (!optional.isPresent()) {
        // continue;
        // }

        // holder = optional.get();
        // }

        // holder.value().place(p_221270_, p_221270_.getChunkSource().getGenerator(),
        // p_221271_, blockpos1);
        // }
        // }
    }

    @Override
    public BonemealableBlock.Type getType() {
        return BonemealableBlock.Type.NEIGHBOR_SPREADER;
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        if (!canBeGrass(currentBlockState, level, blockPos)) {
            // Forge: prevent loading unloaded chunks when checking neighbor's light and
            // spreading
            if (!level.isAreaLoaded(blockPos, 1))
                return;
            level.setBlockAndUpdate(blockPos, MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState());
        }

        // Forge: prevent loading unloaded chunks when checking neighbor's light and
        // spreading
        if (!level.isAreaLoaded(blockPos, 3))
            return;

        if (level.getMaxLocalRawBrightness(blockPos.above()) < 9)
            return;

        BlockState candyGrassBlockState = this.defaultBlockState();
        BlockState candyDirtBlockState = MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState();

        for (int i = 0; i < 4; i++) {
            BlockPos randomBlockPos = blockPos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3,
                    random.nextInt(3) - 1);

            // Spreads candy grass blocks across candy dirt blocks
            if (level.getBlockState(randomBlockPos).is(MainBlocks.CANDY_DIRT_BLOCK.get())
                    && canPropagate(candyGrassBlockState, level, randomBlockPos)) {
                level.setBlockAndUpdate(randomBlockPos, candyGrassBlockState);
            }

            // Spreads candy dirt blocks across grass blocks and dirt blocks
            if ((level.getBlockState(randomBlockPos).is(Blocks.GRASS_BLOCK)
                    || level.getBlockState(randomBlockPos).is(Blocks.DIRT))
                    && canPropagate(candyGrassBlockState, level, randomBlockPos)
                    && randomSpreadChance(level, random)) {
                level.setBlockAndUpdate(randomBlockPos, candyDirtBlockState);
                spreadCandylands(level, blockPos, randomBlockPos);
            }

            // Spreads down dirt blocks
            if (randomBlockPos.getX() == blockPos.getX() && randomBlockPos.getZ() == blockPos.getZ()
                    && randomBlockPos.getY() + 1 == blockPos.getY()
                    && level.getBlockState(randomBlockPos).is(Blocks.DIRT)) {
                level.setBlockAndUpdate(randomBlockPos, candyDirtBlockState);
            }
        }
    }

    private void spreadCandylands(ServerLevel level, BlockPos blockPos, BlockPos randomBlockPos) {
        var chunk = level.getChunk(randomBlockPos);

        Holder<Biome> biome = level.registryAccess().holderOrThrow(MainBiomes.LICORICE_FOREST);

        chunk.fillBiomesFromNoise(
                makeResolver(chunk, BoundingBox.fromCorners(blockPos, randomBlockPos).inflatedBy(3, 8, 3), biome,
                        (oldBiome) -> true),
                level.getChunkSource().randomState().sampler());
        chunk.markUnsaved();
        level.getChunkSource().chunkMap.resendBiomesForChunks(List.of(chunk));
    }

    private static boolean randomSpreadChance(ServerLevel level, RandomSource random) {
        int value = level.getGameRules().getInt(MainGameRules.RULE_CANDY_SPREAD_CHANCE);

        if (value <= 0) {
            return true;
        }

        return random.nextInt(Math.clamp(value, 1, Integer.MAX_VALUE)) == 0;
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

    private static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockAbove = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockAbove);
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(state, blockstate, Direction.UP, blockstate.getLightBlock());
            return i < 15;
        }
    }
}
