package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainVegetationFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class MainPlacedVegetationFeatures {
    public static final ResourceKey<PlacedFeature> TREES_CANDY = MainPlacedFeatures
            .createKey("trees_candy");

    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter
            .forMaxDepth(0);

    private static Builder<PlacementModifier> treePlacementBase(
            PlacementModifier placement) {
        return ImmutableList.<PlacementModifier>builder().add(placement)
                .add(InSquarePlacement.spread()).add(TREE_THRESHOLD)
                .add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
                .add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(
            PlacementModifier placement) {
        return treePlacementBase(placement).build();
    }

    public static void register(BootstrapContext<PlacedFeature> bootstrap,
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolder) {
        var candyTrees = configuredFeatureHolder
                .getOrThrow(MainVegetationFeatures.TREES_CANDY);

        PlacementUtils.register(bootstrap, TREES_CANDY, candyTrees,
                treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    }
}
