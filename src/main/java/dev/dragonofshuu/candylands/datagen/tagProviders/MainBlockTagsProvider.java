package dev.dragonofshuu.candylands.datagen.tagProviders;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class MainBlockTagsProvider extends BlockTagsProvider {
        public MainBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
                super(output, lookupProvider, CandyLands.MODID);
        }

        @Override
        protected void addTags(Provider provider) {
                tag(BlockTags.MINEABLE_WITH_SHOVEL)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get());

                tag(BlockTags.NEEDS_STONE_TOOL)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get());

                tag(BlockTags.REPLACEABLE_BY_TREES)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.REPLACEABLE)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get());
        }
}
