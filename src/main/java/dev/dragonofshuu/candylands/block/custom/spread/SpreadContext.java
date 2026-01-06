package dev.dragonofshuu.candylands.block.custom.spread;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadContext {
    public final BlockState sourceState;
    public final ServerLevel level;
    public final BlockPos blockPos;
    public final RandomSource random;

    public SpreadContext(BlockState sourceState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        this.sourceState = sourceState;
        this.level = level;
        this.blockPos = blockPos;
        this.random = random;
    }
}
