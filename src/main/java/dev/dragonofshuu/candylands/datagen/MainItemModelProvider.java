package dev.dragonofshuu.candylands.datagen;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import net.neoforged.neoforge.registries.DeferredBlock;

public class MainItemModelProvider extends ModelProvider {
    public MainItemModelProvider(PackOutput output) {
        super(output, CandyLands.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // TODO
    }

    // private <T extends Block> String asBlockParent(DeferredBlock<T> block) {
    // String newLocation = new StringBuilder()
    // .append(block.getId().getNamespace())
    // .append(':')
    // .append("block/")
    // .append(block.getId().getPath())
    // .toString();
    // return newLocation;
    // }

    // private <T extends Block> ResourceLocation
    // asBlockParentResource(DeferredBlock<T> block) {
    // return ResourceLocation.parse(asBlockParent(block));
    // }
}
