package dev.dragonofshuu.candylands.worldgen;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.worldgen.regions.LicoriceRegion;
import dev.dragonofshuu.candylands.worldgen.surfaceRules.LicoriceSurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import terrablender.api.Region;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class MainBiomeWorldgen {

    public static void registerAll() {
        registerRegion("licorice", LicoriceRegion::new, 20);

        registerSurfaceRule(SurfaceRuleManager.RuleCategory.OVERWORLD,
                LicoriceSurfaceRuleData::makeRules);
    }

    private static void registerRegion(String name,
            BiFunction<ResourceLocation, Integer, Region> regionSupplier,
            int weight) {
        Regions.register(regionSupplier.apply(
                ResourceLocation.fromNamespaceAndPath("candylands", name),
                weight));
    }

    private static void registerSurfaceRule(
            SurfaceRuleManager.RuleCategory category,
            Supplier<RuleSource> getRuleSource) {
        SurfaceRuleManager.addSurfaceRules(category, CandyLands.MODID,
                getRuleSource.get());
    }
}
