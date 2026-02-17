package dev.dragonofshuu.candylands.block.custom;

import java.util.function.Consumer;

import org.slf4j.Logger;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.registries.spread.MainSpreadFunctions;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadMemoizer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CandyIceBlock extends IceBlock implements IOnFall {
    public static final MapCodec<CandyIceBlock> CODEC = simpleCodec(
            CandyIceBlock::new);

    private static final IntegerProperty SPREAD_ATTEMPTS = IntegerProperty
            .create("spread_attempts", 0, 3);
    private static final int MAX_SPREAD_ATTEMPTS = 3;
    private static final SpreadMemoizer SPREAD_MEMOIZER = new SpreadMemoizer(
            SPREAD_ATTEMPTS, MAX_SPREAD_ATTEMPTS);

    private static final Logger LOGGER = CandyLands.LOGGER;

    public CandyIceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return SPREAD_MEMOIZER.canTrySpread(state);
    }

    @Override
    protected void createBlockStateDefinition(
            Builder<Block, BlockState> builder) {
        builder.add(SPREAD_ATTEMPTS);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level,
            ScheduledTickAccess scheduledTickAccess, BlockPos pos,
            Direction direction, BlockPos neighborPos, BlockState neighborState,
            RandomSource random) {
        return SPREAD_MEMOIZER.updateShapeSpreadBlock(state, neighborState);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos,
            RandomSource random) {
        Consumer<SpreadContext> onCantSpread = (context) -> {
            SPREAD_MEMOIZER.setSpreadAttempts(level, state, pos,
                    (old) -> old + 1);
        };
        MainSpreadFunctions.ICE_SPREAD.get().tick(state, level, pos,
                onCantSpread, (c) -> {
                }, random);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state,
            Entity entity) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        if (aboveState.getBlock().equals(Blocks.SNOW)
                && entity.getRandom().nextDouble() < 0.125) {
            level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
        }
    }

    @Override
    public MapCodec<? extends IceBlock> codec() {
        return CODEC;
    }

    @Override
    public void OnFallOn(LivingEntity entity) {
        Level level = entity.level();
        if (level.isClientSide())
            return;
        LOGGER.debug("Entity {} fell on CandyIceBlock at {}, in block {}",
                entity, entity.blockPosition().below(),
                level.getBlockState(entity.blockPosition()));
        BlockPos inPos = entity.blockPosition();
        BlockPos belowPos = entity.blockPosition().below();
        if (level.getBlockState(inPos).getBlock().equals(Blocks.SNOW)) {
            int radius = 5;
            LOGGER.debug(
                    "Entity is inside snow, melting nearby ice blocks in radius {}",
                    radius);
            BlockPos.betweenClosed(belowPos.offset(-radius, 0, -radius),
                    belowPos.offset(radius, 0, radius)).forEach((pos) -> {
                        if (level.getBlockState(pos).getBlock()
                                .equals(MainBlocks.CANDY_ICE_BLOCK.get())
                                && pos.distSqr(belowPos) <= radius) {
                            level.setBlock(pos,
                                    Blocks.WATER.defaultBlockState(), 3);
                        }
                    });
        }
    }

    @Override
    public void OnFallIn(LivingEntity entity) {

    }
}
