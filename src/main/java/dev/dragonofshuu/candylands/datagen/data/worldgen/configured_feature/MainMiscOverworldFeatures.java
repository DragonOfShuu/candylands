package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.feature.MainFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class MainMiscOverworldFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICORICE_ARCH = MainConfiguredFeatures
            .createKey("licorice_arch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CANDY_CANE_BLOB = MainConfiguredFeatures
            .createKey("candy_cane_blob");

    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context) {
        MainConfiguredFeatures.register(context, LICORICE_ARCH,
                MainFeatures.ARCH.get(), new BlockStateConfiguration(
                        MainBlocks.LICORICE_WOOD.get().defaultBlockState()));
        MainConfiguredFeatures.register(context, CANDY_CANE_BLOB,
                Feature.FOREST_ROCK, new BlockStateConfiguration(
                        MainBlocks.CANDY_CANE_ROCK.get().defaultBlockState()));
    }
}
