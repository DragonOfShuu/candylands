package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import java.util.List;

import dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature.MainPlacedTreeFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainVegetationFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_CANDY = MainFeatures.createKey("trees_candy");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolder =
        // context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> placedFeatureHolder = context.lookup(Registries.PLACED_FEATURE);
        var licoriceTreePlaced = placedFeatureHolder.getOrThrow(MainPlacedTreeFeatures.LICORICE_CHECKED);
        FeatureUtils.register(context, TREES_CANDY, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(
                        List.of(new WeightedPlacedFeature(licoriceTreePlaced, 0.01f)),
                        licoriceTreePlaced));
    }
}
