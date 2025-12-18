package dev.dragonofshuu.candylands.datagen.tagProviders;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class MainBiomeTagsProvider extends BiomeTagsProvider {
    public MainBiomeTagsProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, provider, CandyLands.MODID);
    }

    @Override
    protected void addTags(Provider provider) {

    }

    @SuppressWarnings("unused")
    @SafeVarargs
    private void tagOptional(TagKey<Biome> tag, ResourceKey<Biome>... biomes) {
        for (ResourceKey<Biome> biome : biomes) {
            tag(tag).addOptional(biome);
        }
    }
}
