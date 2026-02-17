package dev.dragonofshuu.candylands.block.custom;

public interface IOnFall {
    /**
     * Called when an entity falls on the block
     * 
     * @param entity The entity that fell on the block
     */
    public void OnFallOn(net.minecraft.world.entity.LivingEntity entity);

    /**
     * Called when an entity falls in the block
     * 
     * @param entity The entity that fell in the block
     */
    public void OnFallIn(net.minecraft.world.entity.LivingEntity entity);
}
