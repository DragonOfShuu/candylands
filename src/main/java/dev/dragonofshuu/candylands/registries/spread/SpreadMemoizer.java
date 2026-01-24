package dev.dragonofshuu.candylands.registries.spread;

import java.util.function.Function;

import dev.dragonofshuu.candylands.util.MainTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SpreadMemoizer {

    private IntegerProperty spreadAttemptsProperty;
    private int maxSpreadAttempts;

    public SpreadMemoizer(IntegerProperty spreadAttemptsProperty, int maxSpreadAttempts) {
        this.spreadAttemptsProperty = spreadAttemptsProperty;
        this.maxSpreadAttempts = maxSpreadAttempts;
    }

    private int getSpreadAttempts(BlockState state) {
        return state.getValue(this.spreadAttemptsProperty);
    }

    public boolean canTrySpread(BlockState state) {
        int attempts = getSpreadAttempts(state);
        return attempts < maxSpreadAttempts;
    }

    public BlockState incrementSpreadAttempts(BlockState state) {
        int attempts = getSpreadAttempts(state);
        return state.setValue(spreadAttemptsProperty, Math.min(attempts + 1, maxSpreadAttempts));
    }

    public BlockState resetSpreadAttempts(BlockState state) {
        return state.setValue(spreadAttemptsProperty, 0);
    }

    public BlockState updateShapeSpreadBlock(BlockState state, BlockState neighborState) {
        if (neighborState.is(MainTags.Blocks.CANDY_SPREADABLES)) {
            return state;
        }

        return resetSpreadAttempts(state);
    }

    public void setSpreadAttempts(Level level, BlockState state, BlockPos pos,
            Function<Integer, Integer> attemptsSupplier) {
        int attempts = attemptsSupplier.apply(getSpreadAttempts(state));
        level.setBlockAndUpdate(pos, state.setValue(spreadAttemptsProperty, attempts));
    }
}
