package dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainPlacedTreeFeatures {
        public static final ResourceKey<PlacedFeature> LICORICE_CHECKED = MainPlacedFeatures
                        .createKey("licorice_tree");

        public static void register(BootstrapContext<PlacedFeature> bootstrap,
                        HolderGetter<ConfiguredFeature<?, ?>> configuredFeaturesHolder) {
                Holder<ConfiguredFeature<?, ?>> licoriceTreeHolder = configuredFeaturesHolder
                                .getOrThrow(MainTreeFeatures.LICORICE);
                PlacementUtils.register(bootstrap, LICORICE_CHECKED,
                                licoriceTreeHolder,
                                PlacementUtils.filteredByBlockSurvival(MainBlocks.LICORICE_SPROUT.get()));
        }
}
