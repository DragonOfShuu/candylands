package dev.dragonofshuu.candylands.spread.spread;

import java.util.Collection;
import java.util.List;

import net.minecraft.world.level.block.state.BlockState;

public record SpreadConverter(BlockState toState, Collection<SpreadCondition> conditions) {
    public static SpreadConverter of(BlockState toState, SpreadCondition condition) {
        return new SpreadConverter(toState, List.of(condition));
    }

    public static SpreadConverter of(BlockState toState, Collection<SpreadCondition> conditions) {
        return new SpreadConverter(toState, conditions);
    }

    public static SpreadConverter of(BlockState toState) {
        return new SpreadConverter(toState, List.of(SpreadCondition.always()));
    }

    public boolean canConvert(SpreadContext context) {
        for (SpreadCondition condition : this.conditions) {
            if (!condition.canSpread(context)) {
                return false;
            }
        }
        return true;
    }
}
