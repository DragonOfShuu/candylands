package dev.dragonofshuu.candylands.block;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.custom.CandyGrassBlock;
import dev.dragonofshuu.candylands.block.custom.CandyIceBlock;
import dev.dragonofshuu.candylands.block.custom.CandyVerticalSpread;
import dev.dragonofshuu.candylands.block.custom.TintedParticularParticleLeavesBlock;
import dev.dragonofshuu.candylands.block.custom.bases.FlammableRotatedPillarBlock;
import dev.dragonofshuu.candylands.block.grower.MainTreeGrower;
import dev.dragonofshuu.candylands.data.MainBlockSetTypes;
import dev.dragonofshuu.candylands.data.MainWoodTypes;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import dev.dragonofshuu.candylands.item.MainItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MainBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister
            .createBlocks(CandyLands.MODID);

    // ------ Candy Dirt Types ------
    public static final DeferredBlock<Block> CANDY_GRASS_BLOCK = registerBlock(
            "candy_grass_block", CandyGrassBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .mapColor(MapColor.COLOR_PINK)
                    .requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> CANDY_DIRT_BLOCK = registerBlock(
            "candy_dirt_block",
            (properties) -> new CandyVerticalSpread(MainBiomes.LICORICE_FOREST,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                    .randomTicks().mapColor(MapColor.CRIMSON_NYLIUM)
                    .requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> CANDY_ICE_BLOCK = registerBlock(
            "candy_ice_block", CandyIceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.FROSTED_ICE)
                    .randomTicks());

    // ------ Candy Rock Types ------
    public static final DeferredBlock<Block> CANDY_CANE_ROCK = registerBlock(
            "candy_cane_rock",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .mapColor(MapColor.COLOR_RED)
                    .requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> CANDY_CANE_COBBLESTONE = registerBlock(
            "candy_cane_cobblestone",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
                    .mapColor(MapColor.COLOR_PINK)
                    .requiresCorrectToolForDrops());

    // ------ Candy Foliage Types ------
    public static final DeferredBlock<Block> CANDY_CANE_FLOWER = registerBlock(
            "candy_cane_flower",
            properties -> new FlowerBlock(MobEffects.SPEED, 5, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
                    .mapColor(MapColor.COLOR_RED));
    public static final DeferredBlock<Block> CANDY_CANE_SHORT_GRASS = registerBlock(
            "candy_cane_short_grass",
            properties -> new FlowerBlock(MobEffects.SPEED, 2, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .mapColor(MapColor.COLOR_RED));

    // ------ Licorice Wood Set ------
    public static final DeferredBlock<Block> LICORICE_LOG = registerBlock(
            "licorice_log", FlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_SPROUT = registerBlock(
            "licorice_sprout",
            properties -> new SaplingBlock(MainTreeGrower.LICORICE, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                    .sound(SoundType.GRASS).mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_LEAVES = registerBlock(
            "licorice_leaves",
            properties -> new TintedParticularParticleLeavesBlock(0.1F,
                    0xab85cc, properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .mapColor(MapColor.COLOR_PINK)
                    .sound(SoundType.CHERRY_LEAVES));
    public static final DeferredBlock<Block> LICORICE_WOOD = registerBlock(
            "licorice_wood", FlammableRotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .mapColor(MapColor.COLOR_PINK));

    // ------ Licorice Wood Set - Building Blocks ------
    public static final DeferredBlock<Block> LICORICE_PLANKS = registerBlock(
            "licorice_planks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_BUTTON = registerBlock(
            "licorice_button",
            properties -> new ButtonBlock(MainBlockSetTypes.LICORICE, 30,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_PRESSURE_PLATE = registerBlock(
            "licorice_pressure_plate",
            properties -> new PressurePlateBlock(MainBlockSetTypes.LICORICE,
                    properties),
            () -> BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_FENCE = registerBlock(
            "licorice_fence", FenceBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_FENCE_GATE = registerBlock(
            "licorice_fence_gate",
            properties -> new FenceGateBlock(MainWoodTypes.LICORICE,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_SLAB = registerBlock(
            "licorice_slab", SlabBlock::new, () -> BlockBehaviour.Properties
                    .ofFullCopy(Blocks.OAK_SLAB).mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> LICORICE_STAIRS = registerBlock(
            "licorice_stairs",
            properties -> new StairBlock(
                    LICORICE_PLANKS.get().defaultBlockState(), properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
                    .mapColor(MapColor.COLOR_PINK));

    // ------- Candy Cane Rock Set - Building Blocks ------
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_SLAB = registerBlock(
            "candy_cane_rock_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_STAIRS = registerBlock(
            "candy_cane_rock_stairs",
            properties -> new StairBlock(
                    CANDY_CANE_ROCK.get().defaultBlockState(), properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_WALL = registerBlock(
            "candy_cane_rock_wall", properties -> new WallBlock(properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_WALL)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_FENCE = registerBlock(
            "candy_cane_rock_fence", FenceBlock::new,
            () -> BlockBehaviour.Properties
                    .ofFullCopy(Blocks.NETHER_BRICK_FENCE)
                    .mapColor(MapColor.COLOR_PINK).sound(SoundType.STONE));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_FENCE_GATE = registerBlock(
            "candy_cane_rock_fence_gate",
            properties -> new FenceGateBlock(Optional.empty(), properties,
                    Optional.of(SoundEvents.FENCE_GATE_OPEN),
                    Optional.of(SoundEvents.FENCE_GATE_CLOSE)),
            () -> BlockBehaviour.Properties
                    .ofFullCopy(Blocks.NETHER_BRICK_FENCE)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_DOOR = registerBlock(
            "candy_cane_rock_door",
            properties -> new DoorBlock(MainBlockSetTypes.CANDY_CANE_ROCK,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)
                    .mapColor(MapColor.COLOR_PINK).sound(SoundType.METAL));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_TRAPDOOR = registerBlock(
            "candy_cane_rock_trapdoor",
            properties -> new TrapDoorBlock(MainBlockSetTypes.CANDY_CANE_ROCK,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_TRAPDOOR)
                    .mapColor(MapColor.COLOR_PINK).sound(SoundType.METAL));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_BUTTON = registerBlock(
            "candy_cane_rock_button",
            properties -> new ButtonBlock(MainBlockSetTypes.CANDY_CANE_ROCK, 20,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)
                    .mapColor(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> CANDY_CANE_ROCK_PRESSURE_PLATE = registerBlock(
            "candy_cane_rock_pressure_plate",
            properties -> new PressurePlateBlock(
                    MainBlockSetTypes.CANDY_CANE_ROCK, properties),
            () -> BlockBehaviour.Properties
                    .ofFullCopy(Blocks.STONE_PRESSURE_PLATE)
                    .mapColor(MapColor.COLOR_PINK));

    // ------ Utility Methods ------
    private static DeferredBlock<Block> registerBlock(String name,
            Supplier<BlockBehaviour.Properties> properties) {
        DeferredBlock<Block> toReturn = registerBlock(name, Block::new,
                properties);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
            Function<BlockBehaviour.Properties, ? extends T> block,
            Supplier<BlockBehaviour.Properties> properties) {

        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block,
                properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(
            String name, DeferredBlock<T> block) {
        return MainItems.ITEMS.registerSimpleBlockItem(name, block::get);
    }

    public static IEventBus register(IEventBus bus) {
        BLOCKS.register(bus);
        return bus;
    }
}
