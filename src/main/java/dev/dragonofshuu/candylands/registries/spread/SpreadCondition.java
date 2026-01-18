package dev.dragonofshuu.candylands.registries.spread;

public interface SpreadCondition {
    boolean canSpread(SpreadContext context);

    public static SpreadCondition always() {
        return context -> true;
    }
}