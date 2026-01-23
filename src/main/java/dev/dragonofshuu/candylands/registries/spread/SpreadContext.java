package dev.dragonofshuu.candylands.registries.spread;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public record SpreadContext(BlockState sourceState, BlockPos sourcePos, ServerLevel level, RandomSource random) {

    public SpreadContext.Target withTarget(BlockState targetState, BlockPos targetPos) {
        return new Target(this.sourceState, this.sourcePos, targetState, targetPos, this.level, this.random);
    }

    public SpreadContext.Target withTarget(BlockPos targetPos) {
        return new Target(this.sourceState, this.sourcePos, this.level.getBlockState(targetPos), targetPos, this.level,
                this.random);
    }

    public record Target(BlockState sourceState, BlockPos sourcePos, BlockState targetState,
            BlockPos targetPos, ServerLevel level, RandomSource random) {
    }
}
