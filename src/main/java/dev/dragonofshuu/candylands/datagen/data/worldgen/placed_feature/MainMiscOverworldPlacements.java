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
    public static final ResourceKey<PlacedFeature> LICORICE_ARCH = MainPlacedFeatures
            .createKey("licorice_arch");
    public static final ResourceKey<PlacedFeature> CANDY_CANE_BLOB = MainPlacedFeatures
            .createKey("candy_cane_blob");

    public static void register(BootstrapContext<PlacedFeature> bootstrap,
            HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesHolder) {

        Holder<ConfiguredFeature<?, ?>> licoriceArchHolder = configuredFeaturesHolder
                .getOrThrow(MainMiscOverworldFeatures.LICORICE_ARCH);
        Holder<ConfiguredFeature<?, ?>> candyCaneBlobHolder = configuredFeaturesHolder
                .getOrThrow(MainMiscOverworldFeatures.CANDY_CANE_BLOB);

        PlacementUtils.register(bootstrap, LICORICE_ARCH, licoriceArchHolder,
                new PlacementModifier[] { PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome() });
        PlacementUtils.register(bootstrap, CANDY_CANE_BLOB, candyCaneBlobHolder,
                new PlacementModifier[] { RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome() });
    }
}
