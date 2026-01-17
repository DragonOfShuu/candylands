package dev.dragonofshuu.candylands.event;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.spread.MainSpreads;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(modid = CandyLands.MODID)
public class NewRegistryEventHandler {
    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        MainSpreads.registerNewRegister(event);
    }
}
