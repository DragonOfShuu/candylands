package dev.dragonofshuu.candylands.block.custom;

import java.util.function.Consumer;

import org.slf4j.Logger;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.registries.spread.MainSpreads;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadMemoizer;
import dev.dragonofshuu.candylands.util.MainGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.lighting.LightEngine;

public class CandyGrassBlock extends Block implements BonemealableBlock {
    public static final MapCodec<CandyGrassBlock> CODEC = simpleCodec(CandyGrassBlock::new);
    public static final int MAX_SPREAD_ATTEMPTS = 3;
    public static final IntegerProperty SPREAD_ATTEMPTS = IntegerProperty.create("spread_attempts", 0, 3);
    // private static final Logger LOGGER = CandyLands.LOGGER;
    private static final SpreadMemoizer SPREAD_MEMOIZER = new SpreadMemoizer(SPREAD_ATTEMPTS,
            MAX_SPREAD_ATTEMPTS);

    public CandyGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any()
                .setValue(SPREAD_ATTEMPTS, Integer.valueOf(0)));
    }

    @Override
    public MapCodec<CandyGrassBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(SPREAD_ATTEMPTS);
    }

    // public int getSpreadAttempts(BlockState state) {
    // return state.getValue(SPREAD_ATTEMPTS);
    // }

    // public BlockState getStateWithSpreadAttempts(int attempts) {
    // return this.defaultBlockState().setValue(SPREAD_ATTEMPTS,
    // Math.min(Integer.valueOf(attempts), MAX_SPREAD_ATTEMPTS));
    // }

    // public void setSpreadAttempts(Level level, BlockPos pos, int attempts) {
    // level.setBlockAndUpdate(pos, this.getStateWithSpreadAttempts(attempts));
    // }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess,
            BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return SPREAD_MEMOIZER.updateShapeSpreadBlock(state, neighborState);
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

    public static boolean canPropagate(SpreadContext.Target context) {
        return canPropagate(context.sourceState(), context.level(), context.targetPos());
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
            level.setBlockAndUpdate(blockPos,
                    MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState());
        }

        // if (this.getSpreadAttempts(currentBlockState) >= MAX_SPREAD_ATTEMPTS) {
        // return;
        // }
        if (!SPREAD_MEMOIZER.canTrySpread(currentBlockState)) {
            return;
        }

        // Forge: prevent loading unloaded chunks when checking neighbor's light and
        // spreading
        if (!level.isAreaLoaded(blockPos, 3))
            return;

        if (level.getMaxLocalRawBrightness(blockPos.above()) < 9)
            return;

        Consumer<SpreadContext> onCantSpread = (context) -> {
            SPREAD_MEMOIZER.setSpreadAttempts(level, currentBlockState, blockPos, (old) -> old + 1);
        };
        MainSpreads.CANDY_GRASS_SPREAD.get().tick(currentBlockState, level, blockPos, onCantSpread, (context) -> {
        }, random);
    }

    public static boolean randomSpreadChance(SpreadContext context) {
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
