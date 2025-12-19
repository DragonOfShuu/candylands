package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainPlacedFeatures {
    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrap) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolder = bootstrap.lookup(Registries.CONFIGURED_FEATURE);

        MainPlacedOres.register(bootstrap, configuredFeatureHolder);
        MainPlacedCaveFeatures.register(bootstrap, configuredFeatureHolder);
    }

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, key));
    }
}
