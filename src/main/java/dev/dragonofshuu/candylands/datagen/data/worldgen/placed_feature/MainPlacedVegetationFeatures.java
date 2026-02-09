package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainVegetationFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class MainPlacedVegetationFeatures {
    public static final ResourceKey<PlacedFeature> TREES_LICORICE = MainPlacedFeatures
            .createKey("trees_licorice");
    public static final ResourceKey<PlacedFeature> PATCH_CANDY_GRASS = MainPlacedFeatures
            .createKey("patch_candy_grass");
    public static final ResourceKey<PlacedFeature> FLOWER_CANDY_CANE = MainPlacedFeatures
            .createKey("flower_candy_cane");

    public static void register(BootstrapContext<PlacedFeature> bootstrap,
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolder) {
        var licoriceTrees = configuredFeatureHolder
                .getOrThrow(MainVegetationFeatures.TREES_LICORICE);
        PlacementUtils.register(bootstrap, TREES_LICORICE, licoriceTrees,
                VegetationPlacements
                        .treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

        var patchCandyGrass = configuredFeatureHolder
                .getOrThrow(MainVegetationFeatures.PATCH_CANDY_GRASS);
        PlacementUtils.register(bootstrap, PATCH_CANDY_GRASS, patchCandyGrass,
                VegetationPlacements.worldSurfaceSquaredWithCount(7));

        var flowerCandyCane = configuredFeatureHolder
                .getOrThrow(MainVegetationFeatures.FLOWER_CANDY_CANE);
        PlacementUtils.register(bootstrap, FLOWER_CANDY_CANE, flowerCandyCane,
                new PlacementModifier[] {
                        NoiseThresholdCountPlacement.of(-0.8, 15, 4),
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome() });
    }
}
