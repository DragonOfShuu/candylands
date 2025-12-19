package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import java.util.List;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class MainOreFeatures {
    // public static final ResourceKey<ConfiguredFeature<?, ?>>
    // KLAXAMITE_ORE_FEATURE
    // = ModFeatures.createKey("ore_klaxamite");
    // public static final ResourceKey<ConfiguredFeature<?, ?>> LIZALITE_ORE_FEATURE
    // = ModFeatures.createKey("ore_lizalite");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrap) {
        RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        // List<OreConfiguration.TargetBlockState> klaxamiteTargetList = List.of(
        // OreConfiguration.target(stoneOreReplaceables,
        // ModBlocks.KLAXAMITE_ORE.get().defaultBlockState()),
        // OreConfiguration.target(deepslateOreReplaceables,
        // ModBlocks.DEEPSLATE_KLAXAMITE_ORE.get().defaultBlockState())
        // );
        // bootstrap.register(KLAXAMITE_ORE_FEATURE, new
        // ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>(
        // Feature.ORE, new OreConfiguration(klaxamiteTargetList, 12, 0.0f)
        // ));

        // List<OreConfiguration.TargetBlockState> lizaliteTargetList = List.of(
        // OreConfiguration.target(stoneOreReplaceables,
        // ModBlocks.LIZALITE.get().defaultBlockState()),
        // OreConfiguration.target(deepslateOreReplaceables,
        // ModBlocks.LIZALITE.get().defaultBlockState())
        // );
        // bootstrap.register(LIZALITE_ORE_FEATURE, new
        // ConfiguredFeature<OreConfiguration, Feature<OreConfiguration>>(
        // Feature.ORE, new OreConfiguration(lizaliteTargetList, 30, 0.0f)
        // ));
    }
}
