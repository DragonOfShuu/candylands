package dev.dragonofshuu.candylands.event;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.custom.IOnJump;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

@EventBusSubscriber(modid = CandyLands.MODID)
public class BlockJumpEventHandler {
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        // The block the player is inside of
        Block blockInside = entity.getInBlockState().getBlock();
        Block blockOn = entity.getBlockStateOn().getBlock();

        if (blockInside instanceof IOnJump) {
            ((IOnJump) blockInside).OnJumpIn(entity);
        }
        if (blockOn instanceof IOnJump) {
            ((IOnJump) blockOn).OnJumpOn(entity);
        }
    }
}
