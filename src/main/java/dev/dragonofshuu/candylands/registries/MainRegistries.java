package dev.dragonofshuu.candylands.registries;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.registries.spread.MainSpreadFunctions;
import dev.dragonofshuu.candylands.registries.spread.SpreadFunction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = CandyLands.MODID)
public class MainRegistries {
    public static final ResourceKey<Registry<SpreadFunction>> SPREAD_FUNCTION_REGISTRY_KEY = ResourceKey
            .createRegistryKey(
                    ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, "spread_functions"));
    public static final Registry<SpreadFunction> SPREAD_FUNCTION = new RegistryBuilder<>(
            SPREAD_FUNCTION_REGISTRY_KEY)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, "default_spread_function"))
            .create();

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        MainSpreadFunctions.registerNewRegister(event);
    }
}
