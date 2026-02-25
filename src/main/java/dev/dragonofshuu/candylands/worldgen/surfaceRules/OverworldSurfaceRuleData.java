package dev.dragonofshuu.candylands.worldgen.surfaceRules;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;

public class OverworldSurfaceRuleData {
    private static final SurfaceRules.RuleSource COTTON_CANDY_GRASS_BLOCK = makeStateRule(
            MainBlocks.CANDY_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource COTTON_CANDY_DIRT_BLOCK = makeStateRule(
            MainBlocks.CANDY_DIRT_BLOCK.get());
    private static final SurfaceRules.RuleSource LICORICE_GRASS_BLOCK = makeStateRule(
            MainBlocks.LICORICE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource LICORICE_DIRT_BLOCK = makeStateRule(
            MainBlocks.LICORICE_DIRT_BLOCK.get());

    public static RuleSource makeRules() {
        return SurfaceRules.sequence(SurfaceRules.ifTrue(
                SurfaceRules.isBiome(MainBiomes.COTTON_CANDY_PLAINS),
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        COTTON_CANDY_GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                                        COTTON_CANDY_DIRT_BLOCK)))),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(MainBiomes.LICORICE_FOREST),
                        SurfaceRules.ifTrue(
                                SurfaceRules.abovePreliminarySurface(),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.ON_FLOOR,
                                                LICORICE_GRASS_BLOCK),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.UNDER_FLOOR,
                                                LICORICE_DIRT_BLOCK)))));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
