package dev.dragonofshuu.candylands.item;

import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.item.custom.DescriptableItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MainItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CandyLands.MODID);

    public static DeferredItem<Item> registerItem(String name, Properties properties, String descKey) {
        return registerItem(name, properties, descKey, true);
    }

    public static DeferredItem<Item> registerItem(String name, Properties properties, String descKey,
            boolean requiresShift) {
        DeferredItem<Item> itemToReturn = registerItem(name,
                () -> new DescriptableItem(properties, descKey, requiresShift));
        return itemToReturn;
    }

    public static DeferredItem<Item> registerItem(String name, Properties properties) {
        return ITEMS.register(name, () -> new Item(properties));
    }

    public static <T extends Item> DeferredItem<T> registerItem(String name, Supplier<T> item) {
        DeferredItem<T> itemToReturn = ITEMS.register(name, item);
        return itemToReturn;
    }

    public static IEventBus register(IEventBus bus) {
        ITEMS.register(bus);
        return bus;
    }
}
