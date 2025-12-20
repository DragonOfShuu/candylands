package dev.dragonofshuu.candylands.block;

import java.util.function.Function;
import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.custom.DescriptableBlockItem;
import dev.dragonofshuu.candylands.item.MainItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MainBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CandyLands.MODID);

    public static final DeferredBlock<Block> CANDY_GRASS_BLOCK = registerBlock("candy_grass_block",
            Block::new,
            () -> BlockBehaviour.Properties.of().strength(0.6f).friction(0.6f));

    // private static <T extends Block> DeferredBlock<T> registerBlock(String name,
    // Function<Properties, ? extends Block> block,
    // BlockBehaviour.Properties properties, String descKey) {
    // DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
    // registerBlockItem(name, toReturn, descKey);
    // return toReturn;
    // }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
            Function<BlockBehaviour.Properties, ? extends T> block,
            Supplier<BlockBehaviour.Properties> properties) {

        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // private static <T extends Block> DeferredBlock<T> registerBlock(String name,
    // Supplier<T> block,
    // BlockBehaviour.Properties properties) {
    // DeferredBlock<T> toReturn = BLOCKS.register(name, block);
    // registerBlockItem(name, toReturn);
    // return toReturn;
    // }

    // private static <T extends Block> DeferredBlock<T> registerBlock(String name,
    // Supplier<T> block, String descKey) {
    // DeferredBlock<T> toReturn = BLOCKS.register(name, block);
    // registerBlockItem(name, toReturn, descKey);
    // return toReturn;
    // }

    // private static <T extends Block> DeferredBlock<T> registerBlock(String name,
    // Supplier<T> block) {
    // DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
    // registerBlockItem(name, toReturn);
    // return toReturn;
    // }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<T> block) {
        return MainItems.ITEMS.registerSimpleBlockItem(name, block::get);
    }

    // @SuppressWarnings("unused")
    // private static <T extends Block> DeferredItem<BlockItem>
    // registerBlockItem(String name, DeferredBlock<T> block,
    // Properties properties) {
    // return MainItems.ITEMS.register(name, () -> new BlockItem(block.get(),
    // properties));
    // }

    // private static <T extends Block> DeferredItem<Item> registerBlockItem(String
    // name, DeferredBlock<T> block,
    // String descKey) {
    // return MainItems.ITEMS.register(name,
    // () -> new DescriptableBlockItem(block.get(), new Item.Properties(),
    // descKey));
    // }

    public static IEventBus register(IEventBus bus) {
        BLOCKS.register(bus);
        return bus;
    }
}
