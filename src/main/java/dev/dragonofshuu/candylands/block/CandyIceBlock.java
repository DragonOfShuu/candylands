package dev.dragonofshuu.candylands.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.IceBlock;

public class CandyIceBlock extends IceBlock {
    public static final MapCodec<CandyIceBlock> CODEC = simpleCodec(CandyIceBlock::new);

    public CandyIceBlock(Properties properties) {
        super(properties);
    }
}
