package dev.dragonofshuu.candylands.registries.spread;

import java.util.Collection;
import java.util.List;

import net.minecraft.world.level.block.state.BlockState;

public record SpreadConverter(BlockState toState,
        Collection<SpreadCondition.Target> conditions) {
    public static SpreadConverter of(BlockState toState,
            SpreadCondition.Target condition) {
        return new SpreadConverter(toState, List.of(condition));
    }

    public static SpreadConverter of(BlockState toState,
            Collection<SpreadCondition.Target> conditions) {
        return new SpreadConverter(toState, conditions);
    }

    public static SpreadConverter of(BlockState toState) {
        return new SpreadConverter(toState,
                List.of(SpreadCondition.Target.always()));
    }

    public boolean canConvert(SpreadContext.Target context) {
        if (!context.level().isLoaded(context.targetPos()))
            return false;
        for (SpreadCondition.Target condition : this.conditions) {
            if (!condition.canSpread(context)) {
                return false;
            }
        }
        return true;
    }
}
