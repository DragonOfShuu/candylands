package dev.dragonofshuu.candylands.block.custom;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.block.custom.bases.MemoizedSpreadBlock;
import dev.dragonofshuu.candylands.registries.MainRegistries;
import dev.dragonofshuu.candylands.registries.spread.MainSpreads;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class CandyDirtBlock extends MemoizedSpreadBlock {
    public static final MapCodec<CandyDirtBlock> CODEC = simpleCodec(CandyDirtBlock::new);

    public CandyDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void spreadTick(BlockState state, ServerLevel level, BlockPos pos,
            Consumer<SpreadContext> cantSpreadHook,
            RandomSource random) {

        Consumer<SpreadContext> onDidSpread = (context) -> {
        };
        MainSpreads.VERTICAL_ONLY_SPREAD.get().tick(state, level, pos,
                cantSpreadHook, onDidSpread, random);
    }

    @Override
    public MapCodec<? extends MemoizedSpreadBlock> codec() {
        return CODEC;
    }
}
