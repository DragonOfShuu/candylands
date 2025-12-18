package dev.dragonofshuu.candylands.datagen;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
// import dev.dragonofshuu.block.ModBlocks;
// import dev.dragonofshuu.items.ModItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

public class MainRecipeProvider extends RecipeProvider {

    protected MainRecipeProvider(Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {

    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new MainRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "CandyLands Recipes";
        }
    }
}
