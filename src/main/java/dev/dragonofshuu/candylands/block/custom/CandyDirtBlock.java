package dev.dragonofshuu.candylands.block.custom;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CandyDirtBlock extends Block {

    public static final MapCodec<CandyDirtBlock> CODEC = simpleCodec(CandyDirtBlock::new);

    @Override
    public MapCodec<CandyDirtBlock> codec() {
        return CODEC;
    }

    public CandyDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState currentBlockState, ServerLevel level,
            BlockPos blockPos, RandomSource random) {
        // Forge: prevent loading unloaded chunks when checking neighbor's light and
        // spreading
        if (!level.isAreaLoaded(blockPos, 2))
            return;

        BlockState candyDirtBlockState = this.defaultBlockState();

        for (int i = 0; i < 4; i++) {
            BlockPos randomBlockPos = blockPos.offset(0, random.nextInt(5) - 3,
                    0);

            if (level.getBlockState(randomBlockPos).is(Blocks.DIRT)) {
                level.setBlockAndUpdate(randomBlockPos, candyDirtBlockState);
            }
        }
    }
}
