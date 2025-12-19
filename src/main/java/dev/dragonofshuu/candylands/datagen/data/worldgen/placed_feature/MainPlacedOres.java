package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import java.util.List;

import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainOreFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class MainPlacedOres {

    // public static final ResourceKey<PlacedFeature> ORE_KLAXAMITE =
    // ModPlacedFeatures.createKey("ore_klaxamite");
    // public static final ResourceKey<PlacedFeature> ORE_LIZALITE =
    // ModPlacedFeatures.createKey("ore_lizalite");

    public static void register(BootstrapContext<PlacedFeature> bootstrap,
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesHolder) {
        // Holder<ConfiguredFeature<?, ?>> klaxamiteHolder =
        // configuredFeaturesHolder.getOrThrow(ModOreFeatures.KLAXAMITE_ORE_FEATURE);
        // Holder<ConfiguredFeature<?, ?>> lizaliteHolder =
        // configuredFeaturesHolder.getOrThrow(ModOreFeatures.LIZALITE_ORE_FEATURE);

        // bootstrap.register(ORE_KLAXAMITE, new PlacedFeature(klaxamiteHolder,
        // commonOrePlacement(1,
        // HeightRangePlacement.triangle(VerticalAnchor.absolute(-55),
        // VerticalAnchor.absolute(14)))));
        // bootstrap.register(ORE_LIZALITE, new PlacedFeature(lizaliteHolder,
        // commonOrePlacement(3,
        // HeightRangePlacement.uniform(VerticalAnchor.absolute(-1),
        // VerticalAnchor.absolute(80)))));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement,
            PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    @SuppressWarnings("unused")
    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}
