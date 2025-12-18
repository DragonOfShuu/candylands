package dev.dragonofshuu.candylands.datagen;

import javax.annotation.Nullable;

// import dev.dragonofshuu.block.ModBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public abstract class MainRecipeProviderBase extends RecipeProvider {
    protected MainRecipeProviderBase(Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    /**
     * Cooked and baked by yours truly
     */
    public void stoneBasedBlockFamilyRecipes(
            String unlockName,
            ItemLike materialBase,
            @Nullable ItemLike stairs,
            @Nullable ItemLike slab,
            @Nullable ItemLike wall,
            @Nullable ItemLike fence,
            @Nullable ItemLike fenceGate,
            @Nullable ItemLike door,
            @Nullable ItemLike button,
            @Nullable ItemLike pressurePlate,
            @Nullable ItemLike trapdoor) {
        if (stairs != null)
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs, materialBase);
        if (slab != null)
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab, materialBase, 2);
        if (wall != null)
            stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall, materialBase);

        blockFamilyRecipes(
                unlockName,
                materialBase,
                stairs,
                slab,
                wall,
                fence,
                fenceGate,
                door,
                button,
                pressurePlate,
                trapdoor);
    }

    public void stoneBasedBlockFamilyRecipes(
            ItemLike materialBase,
            @Nullable ItemLike stairs,
            @Nullable ItemLike slab,
            @Nullable ItemLike wall,
            @Nullable ItemLike fence,
            @Nullable ItemLike fenceGate,
            @Nullable ItemLike door,
            @Nullable ItemLike button,
            @Nullable ItemLike pressurePlate,
            @Nullable ItemLike trapdoor) {
        String unlockName = "has_" + materialBase.asItem().toString();
        stoneBasedBlockFamilyRecipes(
                unlockName,
                materialBase,
                stairs,
                slab,
                wall,
                fence,
                fenceGate,
                door,
                button,
                pressurePlate,
                trapdoor);
    }

    public void blockFamilyRecipes(
            String unlockName,
            ItemLike materialBase,
            @Nullable ItemLike stairs,
            @Nullable ItemLike slab,
            @Nullable ItemLike wall,
            @Nullable ItemLike fence,
            @Nullable ItemLike fenceGate,
            @Nullable ItemLike door,
            @Nullable ItemLike button,
            @Nullable ItemLike pressurePlate,
            @Nullable ItemLike trapdoor) {
        if (stairs != null)
            stairBuilder(stairs, Ingredient.of(materialBase))
                    .unlockedBy(unlockName, has(materialBase))
                    .save(this.output);

        if (slab != null)
            slab(RecipeCategory.BUILDING_BLOCKS, slab, materialBase);

        if (wall != null)
            wall(RecipeCategory.BUILDING_BLOCKS, wall, materialBase);

        if (fence != null)
            fenceBuilder(fence, Ingredient.of(materialBase))
                    .unlockedBy(unlockName, has(materialBase))
                    .save(this.output);

        if (fenceGate != null)
            fenceGateBuilder(fenceGate, Ingredient.of(materialBase))
                    .unlockedBy(unlockName, has(materialBase))
                    .save(this.output);

        if (door != null)
            doorBuilder(door, Ingredient.of(materialBase))
                    .unlockedBy(unlockName, has(materialBase))
                    .save(this.output);

        if (button != null)
            buttonBuilder(button, Ingredient.of(materialBase))
                    .unlockedBy(unlockName, has(materialBase))
                    .save(this.output);

        if (pressurePlate != null)
            pressurePlate(pressurePlate, materialBase);

        if (trapdoor != null)
            trapdoorBuilder(trapdoor, Ingredient.of(materialBase))
                    .unlockedBy(unlockName, has(materialBase))
                    .save(this.output);
    }

    public void blockFamilyRecipes(
            RecipeOutput recipeOutput,
            @Nullable ItemLike materialBase,
            @Nullable ItemLike stairs,
            @Nullable ItemLike slab,
            @Nullable ItemLike wall,
            @Nullable ItemLike fence,
            @Nullable ItemLike fenceGate,
            @Nullable ItemLike door,
            @Nullable ItemLike button,
            @Nullable ItemLike pressurePlate,
            @Nullable ItemLike trapdoor) {
        blockFamilyRecipes(
                "has_" + materialBase.asItem().toString(),
                materialBase,
                stairs,
                slab,
                wall,
                fence,
                fenceGate,
                door,
                button,
                pressurePlate,
                trapdoor);
    }
}
