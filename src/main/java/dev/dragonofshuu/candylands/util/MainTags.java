package dev.dragonofshuu.candylands.util;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MainTags {
    public static class Blocks {
        public static final TagKey<Block> DIRT = tag("dirt");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, name));
        }

    }

    public static class Items {
        // public static final TagKey<Item> DIRT = tag("dirt");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, name));
        }

    }
}
