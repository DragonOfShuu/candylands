package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainMiscOverworldFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class MainMiscOverworldPlacements {
    public static final ResourceKey<PlacedFeature> CANDY_CANE_ARCH = MainPlacedFeatures
            .createKey("candy_cane_arch");

    public static void register(BootstrapContext<PlacedFeature> bootstrap,
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesHolder) {

        Holder<ConfiguredFeature<?, ?>> candyCaneArchHolder = configuredFeaturesHolder
                .getOrThrow(MainMiscOverworldFeatures.CANDY_CANE_ARCH);
        PlacementUtils.register(bootstrap, CANDY_CANE_ARCH, candyCaneArchHolder,
                new PlacementModifier[] { RarityFilter.onAverageOnceEvery(2),
                        PlacementUtils.HEIGHTMAP, InSquarePlacement.spread(),
                        BiomeFilter.biome() });
    }
}
