package dev.dragonofshuu.candylands.datagen.data.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainBiomeData {
    public static void bootstrap(BootstrapContext<Biome> bootstrap) {
        HolderGetter<PlacedFeature> placed_features_holder = bootstrap.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configured_carver_holder = bootstrap
                .lookup(Registries.CONFIGURED_CARVER);

        bootstrap.register(MainBiomes.LICORICE_FOREST,
                MainOverworldBiomes.licoriceForest(placed_features_holder, configured_carver_holder));
    }
}
