package dev.dragonofshuu.candylands.block.grower;

import java.util.Map;
import java.util.Optional;

import dev.dragonofshuu.candylands.datagen.data.worldgen.configured_feature.MainTreeFeatures;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class MainTreeGrower {
    private static final Map<String, TreeGrower> CANDY_GROWERS = new Object2ObjectArrayMap<>();

    public static final TreeGrower LICORICE = register(
            "licorice",
            0.3f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(MainTreeFeatures.LICORICE),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());

    public static TreeGrower register(
            String name,
            float secondaryChance,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {

        var treeGrower = new TreeGrower(name, secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree,
                flowers, secondaryFlowers);
        CANDY_GROWERS.put(name, treeGrower);
        return treeGrower;
    }
}
