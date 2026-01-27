package dev.dragonofshuu.candylands.block.custom;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.registries.spread.MainSpreadFunctions;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadMemoizer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CandyIceBlock extends IceBlock {
    public static final MapCodec<CandyIceBlock> CODEC = simpleCodec(CandyIceBlock::new);

    private static final IntegerProperty SPREAD_ATTEMPTS = IntegerProperty.create("spread_attempts", 0, 3);
    private static final int MAX_SPREAD_ATTEMPTS = 3;
    private static final SpreadMemoizer SPREAD_MEMOIZER = new SpreadMemoizer(SPREAD_ATTEMPTS,
            MAX_SPREAD_ATTEMPTS);

    public CandyIceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return SPREAD_MEMOIZER.canTrySpread(state);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(SPREAD_ATTEMPTS);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess,
            BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return SPREAD_MEMOIZER.updateShapeSpreadBlock(state, neighborState);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Consumer<SpreadContext> onCantSpread = (context) -> {
            SPREAD_MEMOIZER.setSpreadAttempts(level, state, pos, (old) -> old + 1);
        };
        MainSpreadFunctions.ICE_SPREAD.get().tick(state, level, pos, onCantSpread, (c) -> {
        }, random);
    }

    @Override
    public MapCodec<? extends IceBlock> codec() {
        return CODEC;
    }
}
