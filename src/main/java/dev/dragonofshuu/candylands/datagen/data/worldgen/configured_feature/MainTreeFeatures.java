package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class MainTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICORICE = MainFeatures.createKey("licorice");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        MainFeatures.register(context, LICORICE, Feature.TREE, createStraightBlobTree(
                MainBlocks.LICORICE_LOG.get(),
                MainBlocks.LICORICE_LEAVES.get(),
                3,
                2,
                0,
                4).build());
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(
            Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int heightRandB, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(logBlock),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(leavesBlock),
                new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }
}
