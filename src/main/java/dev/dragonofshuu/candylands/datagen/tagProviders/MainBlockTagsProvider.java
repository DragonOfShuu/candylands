package dev.dragonofshuu.candylands.datagen.tagProviders;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.util.MainTags;
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

        tag(BlockTags.DIRT)
                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

        tag(MainTags.Blocks.DIRT)
                .addTag(BlockTags.AZALEA_GROWS_ON)
                .addTag(BlockTags.AZALEA_ROOT_REPLACEABLE)
                .addTag(BlockTags.SCULK_REPLACEABLE)
                .addTag(BlockTags.DIRT)
                .addTag(BlockTags.LUSH_GROUND_REPLACEABLE)
                .addTag(BlockTags.VALID_SPAWN)
                .addTag(BlockTags.FOXES_SPAWNABLE_ON)
                .addTag(BlockTags.DRY_VEGETATION_MAY_PLACE_ON)
                .addTag(BlockTags.GOATS_SPAWNABLE_ON)
                .addTag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .addTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .addTag(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                .addTag(BlockTags.ENDERMAN_HOLDABLE)
                .addTag(BlockTags.FROGS_SPAWNABLE_ON)
                .addTag(BlockTags.PARROTS_SPAWNABLE_ON)
                .addTag(BlockTags.NETHER_CARVER_REPLACEABLES)
                .addTag(BlockTags.RABBITS_SPAWNABLE_ON)
                .addTag(BlockTags.MOSS_REPLACEABLE)
                .addTag(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .addTag(BlockTags.ARMADILLO_SPAWNABLE_ON)
                .addTag(BlockTags.BAMBOO_PLANTABLE_ON)
                .addTag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
                .addTag(BlockTags.WOLVES_SPAWNABLE_ON)
                .add(MainBlocks.CANDY_DIRT_BLOCK.get(), MainBlocks.CANDY_GRASS_BLOCK.get());
    }
}
