package dev.dragonofshuu.candylands.registries.spread;

public interface SpreadCondition {
    boolean canSpread(SpreadContext context);

    public interface Target {
        boolean canSpread(SpreadContext.Target context);

        public static SpreadCondition.Target always() {
            return context -> true;
        }
    }

    public static SpreadCondition always() {
        return context -> true;
    }
}