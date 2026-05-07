package dev.dragonofshuu.candylands.event;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = CandyLands.MODID)
public class PlayerChangedDimensionHandler {
    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        // Check if player went to the nether
        boolean isNether = event.getTo() == Level.NETHER;

        // If they did, say something in chat
        if (!isNether) return;
        event.getEntity().displayClientMessage(
                Component.translatable("message.candylands.enter_nether"), false);
    }
}
