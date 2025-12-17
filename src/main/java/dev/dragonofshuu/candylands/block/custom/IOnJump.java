package dev.dragonofshuu.candylands.block.custom;

import net.minecraft.world.entity.LivingEntity;

public interface IOnJump {
    public void OnJumpIn(LivingEntity entity);

    public void OnJumpOn(LivingEntity entity);
}
