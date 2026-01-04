package dev.dragonofshuu.candylands.block;

import java.util.function.Function;
import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.custom.FlammableRotatedPillarBlock;
import dev.dragonofshuu.candylands.block.grower.MainTreeGrower;
import dev.dragonofshuu.candylands.data.MainBlockSetTypes;
import dev.dragonofshuu.candylands.data.MainWoodTypes;
import dev.dragonofshuu.candylands.item.MainItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MainBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CandyLands.MODID);

        // ------ Candy Dirt Types ------
        public static final DeferredBlock<Block> CANDY_GRASS_BLOCK = registerBlock("candy_grass_block",
                        CandyGrassBlock::new,
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_PINK)
                                        .requiresCorrectToolForDrops());

        public static final DeferredBlock<Block> CANDY_DIRT_BLOCK = registerBlock("candy_dirt_block",
                        CandyDirtBlock::new,
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).randomTicks()
                                        .mapColor(MapColor.CRIMSON_NYLIUM)
                                        .requiresCorrectToolForDrops());

        public static final DeferredBlock<Block> CANDY_ICE_BLOCK = registerBlock("candy_ice_block",
                        CandyIceBlock::new,
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FROSTED_ICE));

        // ------ Licorice Wood Set ------
        public static final DeferredBlock<Block> LICORICE_LOG = registerBlock("licorice_log",
                        FlammableRotatedPillarBlock::new,
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_SPROUT = registerBlock("licorice_sprout",
                        properties -> new SaplingBlock(MainTreeGrower.LICORICE, properties),
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).sound(SoundType.GRASS)
                                        .mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_LEAVES = registerBlock("licorice_leaves",
                        properties -> new TintedParticularParticleLeavesBlock(0.1F, 0xab85cc, properties),
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_PINK)
                                        .sound(SoundType.CHERRY_LEAVES));

        // ------ Licorice Wood Set - Building Blocks ------
        public static final DeferredBlock<Block> LICORICE_PLANKS = registerBlock("licorice_planks",
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_BUTTON = registerBlock("licorice_button",
                        properties -> new ButtonBlock(MainBlockSetTypes.LICORICE, 30, properties),
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_PRESSURE_PLATE = registerBlock("licorice_pressure_plate",
                        properties -> new PressurePlateBlock(MainBlockSetTypes.LICORICE, properties),
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                                        .mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_FENCE = registerBlock("licorice_fence",
                        FenceBlock::new,
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_FENCE_GATE = registerBlock("licorice_fence_gate",
                        properties -> new FenceGateBlock(MainWoodTypes.LICORICE, properties),
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                                        .mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_SLAB = registerBlock("licorice_slab",
                        SlabBlock::new,
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_PINK));
        public static final DeferredBlock<Block> LICORICE_STAIRS = registerBlock("licorice_stairs",
                        properties -> new StairBlock(LICORICE_PLANKS.get().defaultBlockState(), properties),
                        () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).mapColor(MapColor.COLOR_PINK));

        // ------ Utility Methods ------
        private static DeferredBlock<Block> registerBlock(String name,
                        Supplier<BlockBehaviour.Properties> properties) {
                DeferredBlock<Block> toReturn = registerBlock(name, Block::new, properties);
                return toReturn;
        }

        private static <T extends Block> DeferredBlock<T> registerBlock(String name,
                        Function<BlockBehaviour.Properties, ? extends T> block,
                        Supplier<BlockBehaviour.Properties> properties) {

                DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name,
                        DeferredBlock<T> block) {
                return MainItems.ITEMS.registerSimpleBlockItem(name, block::get);
        }

        public static IEventBus register(IEventBus bus) {
                BLOCKS.register(bus);
                return bus;
        }
}
