package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import java.util.List;

import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainCaveFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

public class MainPlacedCaveFeatures {
    // public static final ResourceKey<PlacedFeature> CAVE_ENCHANTED_CRIMSON_VINES =
    // ModPlacedFeatures.createKey("cave_enchanted_crimson_vines");

    public static void register(BootstrapContext<PlacedFeature> bootstrap,
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesHolder) {
        // var crimsonVinesHolder =
        // configuredFeaturesHolder.getOrThrow(ModCaveFeatures.ENCHANTED_CRIMSON_VINES);
        // bootstrap.register(CAVE_ENCHANTED_CRIMSON_VINES, new
        // PlacedFeature(crimsonVinesHolder, List.of(CountPlacement.of(188),
        // InSquarePlacement.spread(),
        // PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
        // EnvironmentScanPlacement.scanningFor(Direction.UP,
        // BlockPredicate.hasSturdyFace(Direction.DOWN),
        // BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        // RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome())));
    }
}
