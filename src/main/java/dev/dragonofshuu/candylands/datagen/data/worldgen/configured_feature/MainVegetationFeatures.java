package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import java.util.List;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature.MainPlacedTreeFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainVegetationFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_LICORICE = MainConfiguredFeatures
            .createKey("trees_licorice");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CANDY_GRASS = MainConfiguredFeatures
            .createKey("patch_candy_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_CANDY_CANE = MainConfiguredFeatures
            .createKey("flower_candy_cane");

    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatureHolder = context
                .lookup(Registries.PLACED_FEATURE);
        var licoriceTreePlaced = placedFeatureHolder
                .getOrThrow(MainPlacedTreeFeatures.LICORICE_CHECKED);
        MainConfiguredFeatures.register(context, TREES_LICORICE,
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
                        new WeightedPlacedFeature(licoriceTreePlaced, 0.01f)),
                        licoriceTreePlaced));
        MainConfiguredFeatures.register(context, PATCH_CANDY_GRASS,
                Feature.RANDOM_PATCH,
                MainConfiguredFeatures.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(
                                MainBlocks.CANDY_CANE_SHORT_GRASS.get()))));
        MainConfiguredFeatures.register(context, FLOWER_CANDY_CANE,
                Feature.FLOWER,
                new RandomPatchConfiguration(96, 7, 3,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider
                                        .simple(MainBlocks.CANDY_CANE_FLOWER
                                                .get())))));
    }
}
