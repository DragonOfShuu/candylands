package dev.dragonofshuu.candylands.datagen;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.data.MainBlockFamilies;
import dev.dragonofshuu.candylands.util.MainTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;

public class MainRecipeProvider extends MainRecipeProviderBase {

    protected MainRecipeProvider(Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        this.generateForEnabledBlockFamilies(FeatureFlagSet.of(FeatureFlags.VANILLA));
        this.planksFromLog(MainBlocks.LICORICE_PLANKS.asItem(), MainTags.Items.LICORICE_LOGS, 4);
    }

    protected void generateForEnabledBlockFamilies(FeatureFlagSet enabledFeatures) {
        MainBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe)
                .forEach(family -> this.generateRecipes(family, enabledFeatures));
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
