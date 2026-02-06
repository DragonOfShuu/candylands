package dev.dragonofshuu.candylands.datagen;

import java.util.ArrayList;
import java.util.List;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.data.MainBlockFamilies;
import dev.dragonofshuu.candylands.datagen.models.MainModelTemplates;
import dev.dragonofshuu.candylands.datagen.models.MainTexturedModel;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.BlockModelGenerators.BlockFamilyProvider;
import net.minecraft.client.data.models.BlockModelGenerators.PlantType;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MainModelProvider extends ModelProvider {
    private final List<Block> skipGeneratingModelsFor = new ArrayList<>();

    public MainModelProvider(PackOutput output) {
        super(output, CandyLands.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels,
            ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(MainBlocks.CANDY_DIRT_BLOCK.get());
        simpleBlockState(blockModels, MainBlocks.CANDY_GRASS_BLOCK);
        createTrivialTranslucentCube(blockModels, MainBlocks.CANDY_ICE_BLOCK);

        blockModels.createTrivialCube(MainBlocks.CANDY_CANE_COBBLESTONE.get());

        createCutoutCrossblockWithDefaultItem(blockModels,
                MainBlocks.CANDY_CANE_FLOWER.get(), PlantType.NOT_TINTED);
        createCutoutCrossblockWithDefaultItem(blockModels,
                MainBlocks.CANDY_CANE_SHORT_GRASS.get(), PlantType.NOT_TINTED);

        var licoriceWood = MainBlocks.LICORICE_LOG.get();
        blockModels.woodProvider(licoriceWood).log(licoriceWood);
        createTrivialCutoutCube(blockModels, MainBlocks.LICORICE_LEAVES);
        createCutoutCrossblockWithDefaultItem(blockModels,
                MainBlocks.LICORICE_SPROUT.get(), PlantType.NOT_TINTED);

        ignoreBlockModelGen(MainBlocks.CANDY_CANE_ROCK_DOOR);
        createCutoutDoor(blockModels, MainBlocks.CANDY_CANE_ROCK_DOOR.get());

        MainBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> applyIgnorance(
                        blockModels.family(family.getBaseBlock()))
                                .generateFor(family));
    }

    private void createTrivialCutoutCube(BlockModelGenerators blockModels,
            DeferredBlock<? extends Block> block) {
        blockModels.createTrivialBlock(block.get(),
                MainTexturedModel.CUBE_ALL_CUTOUT);
    }

    // private void
    // createTrivialCutoutMippedCube(BlockModelGenerators
    // blockModels,
    // Block block) {
    // blockModels.createTrivialBlock(block,
    // MainTexturedModel.CUBE_ALL_CUTOUT_MIPPED);
    // }

    private void createTrivialTranslucentCube(BlockModelGenerators blockModels,
            DeferredBlock<? extends Block> block) {
        blockModels.createTrivialBlock(block.get(),
                MainTexturedModel.CUBE_ALL_TRANSLUCENT);
    }

    private void simpleBlockState(BlockModelGenerators blockModels,
            DeferredBlock<? extends Block> block) {
        ResourceLocation parentLocation = asBlockParentResource(block);
        blockModels.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(block.get(),
                        BlockModelGenerators.plainVariant(parentLocation)));
    }

    private void createCutoutCrossblockWithDefaultItem(
            BlockModelGenerators blockModels, Block block,
            PlantType plantType) {

        blockModels.registerSimpleItemModel(block.asItem(),
                plantType.createItemModel(blockModels, block));
        createCutoutCrossblock(blockModels, block, plantType);
    }

    private void createCutoutCrossblock(BlockModelGenerators blockModels,
            Block block, PlantType plantType) {

        TextureMapping texturemapping = plantType.getTextureMapping(block);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(
                ExtendedModelTemplateBuilder.of(plantType.getCross())
                        .renderType("minecraft:cutout").build().create(block,
                                texturemapping, blockModels.modelOutput));
        blockModels.blockStateOutput
                .accept(MultiVariantGenerator.dispatch(block, multivariant));
    }

    public void createCutoutDoor(BlockModelGenerators blockModels,
            Block doorBlock) {
        TextureMapping texturemapping = TextureMapping.door(doorBlock);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_BOTTOM_LEFT_CUTOUT.create(doorBlock,
                        texturemapping, blockModels.modelOutput));
        MultiVariant multivariant1 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_BOTTOM_LEFT_OPEN_CUTOUT.create(
                        doorBlock, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant2 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_BOTTOM_RIGHT_CUTOUT.create(doorBlock,
                        texturemapping, blockModels.modelOutput));
        MultiVariant multivariant3 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_BOTTOM_RIGHT_OPEN_CUTOUT.create(
                        doorBlock, texturemapping, blockModels.modelOutput));
        MultiVariant multivariant4 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_TOP_LEFT_CUTOUT.create(doorBlock,
                        texturemapping, blockModels.modelOutput));
        MultiVariant multivariant5 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_TOP_LEFT_OPEN_CUTOUT.create(doorBlock,
                        texturemapping, blockModels.modelOutput));
        MultiVariant multivariant6 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_TOP_RIGHT_CUTOUT.create(doorBlock,
                        texturemapping, blockModels.modelOutput));
        MultiVariant multivariant7 = BlockModelGenerators.plainVariant(
                MainModelTemplates.DOOR_TOP_RIGHT_OPEN_CUTOUT.create(doorBlock,
                        texturemapping, blockModels.modelOutput));
        blockModels.registerSimpleFlatItemModel(doorBlock.asItem());
        blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(
                doorBlock, multivariant, multivariant1, multivariant2,
                multivariant3, multivariant4, multivariant5, multivariant6,
                multivariant7));
    }

    private void ignoreBlockModelGen(DeferredBlock<Block> candyCaneRockDoor) {
        skipGeneratingModelsFor.add(candyCaneRockDoor.get());
    }

    private BlockFamilyProvider applyIgnorance(
            BlockFamilyProvider familyProvider) {
        skipGeneratingModelsFor
                .forEach(familyProvider.skipGeneratingModelsFor::add);
        return familyProvider;
    }

    private <T extends Block> String asBlockParent(DeferredBlock<T> block) {
        String newLocation = new StringBuilder()
                .append(block.getId().getNamespace()).append(':')
                .append("block/").append(block.getId().getPath()).toString();
        return newLocation;
    }

    private <T extends Block> ResourceLocation asBlockParentResource(
            DeferredBlock<T> block) {
        return ResourceLocation.parse(asBlockParent(block));
    }
}
