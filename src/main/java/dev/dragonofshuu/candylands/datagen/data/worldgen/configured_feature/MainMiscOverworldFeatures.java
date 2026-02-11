package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.feature.MainFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class MainMiscOverworldFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CANDY_CANE_ARCH = MainConfiguredFeatures
            .createKey("candy_cane_arch");

    public static void bootstrap(
            BootstrapContext<ConfiguredFeature<?, ?>> context) {
        MainConfiguredFeatures.register(context, CANDY_CANE_ARCH,
                MainFeatures.ARCH.get(), new BlockStateConfiguration(
                        MainBlocks.CANDY_CANE_ROCK.get().defaultBlockState()));
    }
}
