package dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature;

import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class MainTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LICORICE = MainFeatures.createKey("licorice");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        MainFeatures.register(context, LICORICE, Feature.TREE,
                cherry(MainBlocks.LICORICE_LOG.get(), MainBlocks.LICORICE_LEAVES.get()).build());
    }

    // private static TreeConfiguration.TreeConfigurationBuilder
    // createStraightBlobTree(
    // Block logBlock, Block leavesBlock, int baseHeight, int heightRandA, int
    // heightRandB, int radius) {
    // return new TreeConfiguration.TreeConfigurationBuilder(
    // BlockStateProvider.simple(logBlock),
    // new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
    // BlockStateProvider.simple(leavesBlock),
    // new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3),
    // new TwoLayersFeatureSize(1, 0, 1));
    // }

    private static TreeConfiguration.TreeConfigurationBuilder cherry(Block logBlock, Block leavesBlock) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(logBlock),
                new CherryTrunkPlacer(
                        7,
                        1,
                        0,
                        new WeightedListInt(
                                WeightedList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1)
                                        .add(ConstantInt.of(3), 1).build()),
                        UniformInt.of(2, 4),
                        UniformInt.of(-4, -3),
                        UniformInt.of(-1, 0)),
                BlockStateProvider.simple(leavesBlock),
                new CherryFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F,
                        0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines();
    }
}
