package dev.dragonofshuu.candylands.datagen.tagProviders;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.data.MainBlockFamilies;
import dev.dragonofshuu.candylands.data.MainWoodTypes;
import dev.dragonofshuu.candylands.util.MainTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

public class MainBlockTagsProvider extends BlockTagsProvider {
        public MainBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
                super(output, lookupProvider, CandyLands.MODID);
        }

        @Override
        protected void addTags(Provider provider) {
                tag(BlockTags.SWORD_EFFICIENT)
                                .add(MainBlocks.LICORICE_LEAVES.get());

                tag(BlockTags.MINEABLE_WITH_SHOVEL)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get());

                tag(BlockTags.MINEABLE_WITH_AXE)
                                .addAll(MainBlockFamilies.getWoodBlockFamilies()
                                                .flatMap(family -> family.getVariants().values().stream()))
                                .add(MainBlocks.LICORICE_LOG.get());

                tag(BlockTags.MINEABLE_WITH_HOE)
                                .add(MainBlocks.LICORICE_LEAVES.get());

                tag(BlockTags.NEEDS_STONE_TOOL)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get());

                tag(BlockTags.REPLACEABLE_BY_TREES)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.LICORICE_LEAVES.get());

                tag(BlockTags.REPLACEABLE)
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get())
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get());

                tag(BlockTags.AZALEA_GROWS_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.AZALEA_ROOT_REPLACEABLE)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.SCULK_REPLACEABLE)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.LUSH_GROUND_REPLACEABLE)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.VALID_SPAWN)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.FOXES_SPAWNABLE_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.DRY_VEGETATION_MAY_PLACE_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.BIG_DRIPLEAF_PLACEABLE)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.ENDERMAN_HOLDABLE)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.FROGS_SPAWNABLE_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.RABBITS_SPAWNABLE_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.MOSS_REPLACEABLE)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.SCULK_REPLACEABLE_WORLD_GEN)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.WOLVES_SPAWNABLE_ON)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                blockTagTypes();

        }

        private void blockTagTypes() {
                tag(BlockTags.DIRT)
                                .add(MainBlocks.CANDY_DIRT_BLOCK.get())
                                .add(MainBlocks.CANDY_GRASS_BLOCK.get());

                tag(BlockTags.LEAVES)
                                .add(MainBlocks.LICORICE_LEAVES.get());

                tag(BlockTags.SAPLINGS)
                                .add(MainBlocks.LICORICE_SPROUT.get());

                tag(BlockTags.LOGS)
                                .add(MainBlocks.LICORICE_LOG.get());

                tag(BlockTags.LOGS_THAT_BURN)
                                .add(MainBlocks.LICORICE_LOG.get());

                tag(Tags.Blocks.NATURAL_LOGS)
                                .add(MainBlocks.LICORICE_LOG.get());

                tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                                .add(MainBlocks.LICORICE_LOG.get());

                tag(Tags.Blocks.OVERWORLD_NATURAL_LOGS)
                                .add(MainBlocks.LICORICE_LOG.get());

                tag(BlockTags.WOODEN_BUTTONS)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.BUTTON));

                tag(BlockTags.WOODEN_PRESSURE_PLATES)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.PRESSURE_PLATE));

                tag(BlockTags.FENCES)
                                .addAll(MainBlockFamilies.getVariantFromBlockFamilies(Variant.FENCE));

                tag(Tags.Blocks.FENCES)
                                .addAll(MainBlockFamilies.getVariantFromBlockFamilies(Variant.FENCE));

                tag(BlockTags.WOODEN_FENCES)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.FENCE));

                tag(Tags.Blocks.FENCES_WOODEN)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.FENCE));

                tag(BlockTags.FENCE_GATES)
                                .addAll(MainBlockFamilies.getVariantFromBlockFamilies(Variant.FENCE_GATE));

                tag(BlockTags.UNSTABLE_BOTTOM_CENTER)
                                .addAll(MainBlockFamilies.getVariantFromBlockFamilies(Variant.FENCE_GATE));

                tag(Tags.Blocks.FENCE_GATES)
                                .addAll(MainBlockFamilies.getVariantFromBlockFamilies(Variant.FENCE_GATE));

                tag(Tags.Blocks.FENCE_GATES_WOODEN)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.FENCE_GATE));

                tag(BlockTags.WOODEN_SLABS)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.SLAB));

                tag(BlockTags.WOODEN_STAIRS)
                                .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.STAIRS));

                tag(BlockTags.PLANKS)
                                .addAll(MainBlockFamilies.getBaseBlocksFromWoodBlockFamilies());

                // tag(BlockTags.WOODEN_DOORS)
                // .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.DOOR));

                // tag(BlockTags.WOODEN_TRAPDOORS)
                // .addAll(MainBlockFamilies.getVariantFromWoodBlockFamilies(Variant.TRAPDOOR));
        }
}
