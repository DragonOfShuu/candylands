package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class MainFeatures extends FeatureUtils {
  public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
    MainCaveFeatures.bootstrap(bootstrap);
    MainOreFeatures.bootstrap(bootstrap);
    MainTreeFeatures.bootstrap(bootstrap);
  }

  public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE,
        ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, name));
  }
}
