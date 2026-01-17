package dev.dragonofshuu.candylands.spread.spread;

public interface SpreadCondition {
    boolean canSpread(SpreadContext context);

    public static SpreadCondition always() {
        return context -> true;
    }
}