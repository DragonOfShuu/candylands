package dev.dragonofshuu.candylands.worldgen.surfaceRules;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;

public class LicoriceSurfaceRuleData {
    // private static final SurfaceRules.RuleSource DIRT =
    // makeStateRule(
    // Blocks.DIRT);
    // private static final SurfaceRules.RuleSource GRASS_BLOCK
    // = makeStateRule(
    // Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource CANDY_GRASS_BLOCK = makeStateRule(
            MainBlocks.CANDY_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource CANDY_DIRT_BLOCK = makeStateRule(
            MainBlocks.CANDY_DIRT_BLOCK.get());

    public static RuleSource makeRules() {
        // SurfaceRules.ConditionSource isAtOrAboveWaterLevel =
        // SurfaceRules
        // .waterBlockCheck(-1, 0);

        return SurfaceRules.sequence(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(MainBiomes.LICORICE_FOREST),
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        CANDY_GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                                        CANDY_DIRT_BLOCK))))
        // SurfaceRules.ifTrue(SurfaceRules.isBiome(MainBiomes.COLD_BLUE),
        // BLUE_TERRACOTTA),

        // Default to a grass and dirt surface
        // SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
