package dev.dragonofshuu.candylands.util;

import net.minecraft.world.level.GameRules;

public class MainGameRules {
    public static final GameRules.Key<GameRules.IntegerValue> RULE_CANDY_SPREAD_CHANCE = register(
            "candySpreadChance",
            GameRules.Category.UPDATES,
            GameRules.IntegerValue.create(100));

    public static <T extends GameRules.Value<T>> GameRules.Key<T> register(String name, GameRules.Category category,
            GameRules.Type<T> type) {
        return GameRules.register(name, category, type);
    }

    public static void registerAll() {
        // Method to ensure the class is loaded and the static fields are initialized
    }
}
