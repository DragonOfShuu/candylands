package dev.dragonofshuu.candylands.datagen.tagProviders;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.data.MainBlockFamilies;
import dev.dragonofshuu.candylands.util.MainTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

public class MainItemTagsProvider extends ItemTagsProvider {
    public MainItemTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
        super(output, lookupProvider, CandyLands.MODID);
    }

    @Override
    protected void addTags(Provider provider) {
        tag(MainTags.Items.LICORICE_LOGS)
                .add(MainBlocks.LICORICE_LOG.get().asItem());

        tag(ItemTags.PLANKS)
                .addAll(MainBlockFamilies.getBaseBlocksFromWoodBlockFamilies().map(block -> block.asItem()));
    }

}
