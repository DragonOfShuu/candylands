package dev.dragonofshuu.candylands.datagen;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.data.MainBlockFamilies;
import dev.dragonofshuu.candylands.datagen.models.MainTexturedModel;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.BlockModelGenerators.PlantType;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MainModelProvider extends ModelProvider {
    public MainModelProvider(PackOutput output) {
        super(output, CandyLands.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(MainBlocks.CANDY_DIRT_BLOCK.get());
        simpleBlockState(blockModels, MainBlocks.CANDY_GRASS_BLOCK);

        var licoriceWood = MainBlocks.LICORICE_LOG.get();
        blockModels.woodProvider(licoriceWood).log(licoriceWood);
        createTrivialCutoutCube(blockModels, MainBlocks.LICORICE_LEAVES);
        createCutoutCrossblockWithDefaultItem(blockModels, MainBlocks.LICORICE_SPROUT.get(), PlantType.NOT_TINTED);

        MainBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> blockModels.family(family.getBaseBlock()).generateFor(family));
    }

    private void createTrivialCutoutCube(BlockModelGenerators blockModels,
            DeferredBlock<? extends Block> block) {
        blockModels.createTrivialBlock(block.get(), MainTexturedModel.CUBE_ALL_CUTOUT);
    }

    private void simpleBlockState(BlockModelGenerators blockModels, DeferredBlock<? extends Block> block) {
        ResourceLocation parentLocation = asBlockParentResource(block);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get(),
                BlockModelGenerators.plainVariant(parentLocation)));
    }

    private void createCutoutCrossblockWithDefaultItem(BlockModelGenerators blockModels,
            Block block, PlantType plantType) {

        blockModels.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockModels, block));
        createCutoutCrossblock(blockModels, block, plantType);
    }

    private void createCutoutCrossblock(BlockModelGenerators blockModels,
            Block block, PlantType plantType) {

        TextureMapping texturemapping = plantType.getTextureMapping(block);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(ExtendedModelTemplateBuilder
                .of(plantType.getCross())
                .renderType("minecraft:cutout").build().create(block, texturemapping, blockModels.modelOutput));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, multivariant));
    }

    private <T extends Block> String asBlockParent(DeferredBlock<T> block) {
        String newLocation = new StringBuilder()
                .append(block.getId().getNamespace())
                .append(':')
                .append("block/")
                .append(block.getId().getPath())
                .toString();
        return newLocation;
    }

    private <T extends Block> ResourceLocation asBlockParentResource(DeferredBlock<T> block) {
        return ResourceLocation.parse(asBlockParent(block));
    }
}
