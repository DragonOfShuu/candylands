package dev.dragonofshuu.candylands.spread.spread;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public record SpreadContext(BlockState sourceState, ServerLevel level, BlockPos blockPos, RandomSource random) {
}
