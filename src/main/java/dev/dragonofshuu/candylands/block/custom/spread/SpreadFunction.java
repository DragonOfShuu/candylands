package dev.dragonofshuu.candylands.block.custom.spread;

public class SpreadFunction {
    public static void applySpread(SpreadContext context, SpreadRules rules) {
        if (rules.isSmart) {
            spreadSmart(context, rules);
        } else {
            spreadDumb(context, rules);
        }
    }

    private static void spreadDumb(SpreadContext context, SpreadRules rules) {

    }

    private static void spreadSmart(SpreadContext context, SpreadRules rules) {

    }
}
