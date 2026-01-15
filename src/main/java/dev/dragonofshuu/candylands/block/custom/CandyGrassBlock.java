package dev.dragonofshuu.candylands.block.custom;

import java.util.List;
import java.util.function.Predicate;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.block.custom.spread.SpreadRules;
import dev.dragonofshuu.candylands.block.custom.spread.SpreadContext;
import dev.dragonofshuu.candylands.block.custom.spread.SpreadConverter;
import dev.dragonofshuu.candylands.block.custom.spread.SpreadFunctionBuilder;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import dev.dragonofshuu.candylands.util.MainGameRules;
import net.minecraft.client.data.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Vec3i;
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
import net.minecraft.world.phys.Vec3;

public class CandyGrassBlock extends Block implements BonemealableBlock {
    private SpreadFunctionBuilder spreadFunction = null;

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

    private static boolean canPropagate(SpreadContext context) {
        return canPropagate(context.sourceState(), context.level(), context.blockPos());
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    protected SpreadFunctionBuilder getSpreadFunction(Level level) {
        if (spreadFunction != null) {
            return spreadFunction;
        }
        Holder<Biome> licorice_forest_biome = level.registryAccess().holderOrThrow(MainBiomes.LICORICE_FOREST);
        spreadFunction = SpreadFunctionBuilder.make()
                .usingDefaultSpreadRule(
                        SpreadRules.spreadRules()
                                .setSourceBlock(this)
                                .addCondition(CandyGrassBlock::randomSpreadChance)
                                .setMaxDistances(new Vec3i(2, 3, 2))
                                .smart())
                .useSpreaders((defaultSpreader) -> List.of(
                        defaultSpreader.extend()
                                .addConversion(MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState(),
                                        SpreadConverter.of(MainBlocks.CANDY_GRASS_BLOCK.get().defaultBlockState(),
                                                CandyGrassBlock::canPropagate))
                                .addConversion(Blocks.DIRT.defaultBlockState(),
                                        MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState())
                                .setMinMaxConversions(3, 20)
                                .setBiome(licorice_forest_biome),
                        defaultSpreader.extend()
                                .addConversion(Blocks.GRASS_BLOCK.defaultBlockState(),
                                        MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState())
                                .setMinMaxConversions(1, 5)));
        return spreadFunction;
    }

    @Override
    protected void randomTick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        if (!canBeGrass(currentBlockState, level, blockPos)) {
            // Forge: prevent loading unloaded chunks when checking neighbor's light and
            // spreading
            if (!level.isAreaLoaded(blockPos, 1))
                return;
            level.setBlockAndUpdate(blockPos,
                    MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState());
        }

        // Forge: prevent loading unloaded chunks when checking neighbor's light and
        // spreading
        if (!level.isAreaLoaded(blockPos, 3))
            return;

        if (level.getMaxLocalRawBrightness(blockPos.above()) < 9)
            return;

        getSpreadFunction(level).tick(currentBlockState, level, blockPos, random);
    }

    private static boolean randomSpreadChance(SpreadContext context) {
        return randomSpreadChance(context.level(), context.random());
    }

    private static boolean randomSpreadChance(ServerLevel level, RandomSource random) {
        int value = level.getGameRules().getInt(MainGameRules.RULE_CANDY_SPREAD_CHANCE);

        if (value <= 0) {
            return true;
        }

        return random.nextInt(Math.clamp(value, 1, Integer.MAX_VALUE)) == 0;
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
