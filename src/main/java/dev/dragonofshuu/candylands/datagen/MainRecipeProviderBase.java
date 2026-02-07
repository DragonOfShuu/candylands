package dev.dragonofshuu.candylands.datagen;

import dev.dragonofshuu.candylands.data.MainBlockFamilies;
// import dev.dragonofshuu.block.ModBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.ItemLike;

public abstract class MainRecipeProviderBase extends RecipeProvider {
    protected MainRecipeProviderBase(Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void generateRecipes(BlockFamily blockFamily,
            FeatureFlagSet requiredFeatures) {
        super.generateRecipes(blockFamily, requiredFeatures);

        if (MainBlockFamilies.isFamilyStone(blockFamily)) {
            stoneBasedBlockFamilyRecipes(blockFamily);
        }
    }

    /**
     * Cooked and baked by yours truly
     */
    public void stoneBasedBlockFamilyRecipes(BlockFamily blockFamily) {
        ItemLike materialBase = blockFamily.getBaseBlock();
        ItemLike stairs = blockFamily.get(BlockFamily.Variant.STAIRS);
        ItemLike slab = blockFamily.get(BlockFamily.Variant.SLAB);
        ItemLike wall = blockFamily.get(BlockFamily.Variant.WALL);

        if (stairs != null)
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs,
                    materialBase);
        if (slab != null)
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab,
                    materialBase, 2);
        if (wall != null)
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall,
                    materialBase);
    }
}
