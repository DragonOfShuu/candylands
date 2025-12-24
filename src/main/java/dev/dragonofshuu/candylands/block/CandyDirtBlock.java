package dev.dragonofshuu.candylands.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class CandyDirtBlock extends Block {
    public CandyDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        // Forge: prevent loading unloaded chunks when checking neighbor's light and
        // spreading
        if (!level.isAreaLoaded(blockPos, 2))
            return;

        BlockState candyDirtBlockState = this.defaultBlockState();

        for (int i = 0; i < 4; i++) {
            BlockPos randomBlockPos = blockPos.offset(0, random.nextInt(5) - 3,
                    0);

            if (level.getBlockState(randomBlockPos).is(Blocks.DIRT)) {
                level.setBlockAndUpdate(randomBlockPos, candyDirtBlockState);
            }
        }

    }

    // private static boolean canBeGrass(BlockState state, LevelReader levelReader,
    // BlockPos pos) {
    // BlockPos blockpos = pos.above();
    // BlockState blockstate = levelReader.getBlockState(blockpos);
    // if (blockstate.getFluidState().getAmount() == 8) {
    // return false;
    // } else {
    // int i = LightEngine.getLightBlockInto(state, blockstate, Direction.UP,
    // blockstate.getLightBlock());
    // return i < 15;
    // }
    // }

    // private static boolean canPropagate(BlockState state, LevelReader level,
    // BlockPos pos) {
    // BlockPos blockpos = pos.above();
    // return canBeGrass(state, level, pos) &&
    // !level.getFluidState(blockpos).is(FluidTags.WATER);
    // }
}
