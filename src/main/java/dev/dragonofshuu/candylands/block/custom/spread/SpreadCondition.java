package dev.dragonofshuu.candylands.block.custom.spread;

public interface SpreadCondition {
    boolean canSpread(SpreadContext context);

    public static SpreadCondition always() {
        return context -> true;
    }
}