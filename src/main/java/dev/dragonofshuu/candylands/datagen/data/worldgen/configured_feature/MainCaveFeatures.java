package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import java.util.List;

import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class MainCaveFeatures {

    // public static final ResourceKey<ConfiguredFeature<?, ?>>
    // ENCHANTED_CRIMSON_VINES
    // = ModFeatures.createKey("enchanted_crimson_vines");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
        // ModFeatures.register(bootstrap, ENCHANTED_CRIMSON_VINES,
        // Feature.BLOCK_COLUMN,
        // new BlockColumnConfiguration(
        // List.of(
        // BlockColumnConfiguration.layer(
        // new WeightedListInt(
        // SimpleWeightedRandomList.<IntProvider>builder()
        // .add(UniformInt.of(0, 19), 2)
        // .add(UniformInt.of(0, 2), 3)
        // .add(UniformInt.of(0, 6), 10).build()
        // ),
        // // Generate the body of the plant every time
        // BlockStateProvider.simple(ModBlocks.ENCHANTED_CRIMSON_VINES_BODY.get())
        // ),
        // // Generate the bottom of the plant
        // BlockColumnConfiguration.layer(
        // ConstantInt.of(1),
        // BlockStateProvider.simple(ModBlocks.ENCHANTED_CRIMSON_VINES.get())
        // )
        // ),
        // Direction.DOWN,
        // BlockPredicate.ONLY_IN_AIR_PREDICATE,
        // true
        // )
        // );
    }
}
