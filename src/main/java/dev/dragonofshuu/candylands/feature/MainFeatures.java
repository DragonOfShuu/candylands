package dev.dragonofshuu.candylands.feature;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.feature.custom.AdjustedBlobFeature;
import dev.dragonofshuu.candylands.feature.custom.ArchFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MainFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister
            .create(Registries.FEATURE, CandyLands.MODID);

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BLOB_ROCK = register(
            "blob_rock",
            new AdjustedBlobFeature(BlockStateConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> ARCH = register(
            "arch", new ArchFeature(BlockStateConfiguration.CODEC));

    public static <FC extends FeatureConfiguration> DeferredHolder<Feature<?>, Feature<FC>> register(
            String name, Feature<FC> feature) {
        return FEATURES.register(name, () -> feature);
    }

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
    }
}
