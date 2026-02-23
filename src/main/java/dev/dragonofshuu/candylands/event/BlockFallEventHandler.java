package dev.dragonofshuu.candylands.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

import org.slf4j.Logger;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.custom.IOnFall;

@EventBusSubscriber(modid = CandyLands.MODID)
public class BlockFallEventHandler {
    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();
        // The block the player is inside of
        Block blockInside = entity.getInBlockState().getBlock();
        Block blockOn = entity.getBlockStateOn().getBlock();
        if (blockInside instanceof IOnFall) {
            ((IOnFall) blockInside).OnFallIn(entity);
        }
        if (blockOn instanceof IOnFall) {
            ((IOnFall) blockOn).OnFallOn(entity);
        }
    }
}
