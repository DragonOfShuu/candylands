package dev.dragonofshuu.candylands.datagen.tagProviders;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class MainBlockTagsProvider extends BlockTagsProvider {
    public MainBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
        super(output, lookupProvider, CandyLands.MODID);
    }

    @Override
    protected void addTags(Provider provider) {

    }
}
