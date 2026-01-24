package dev.dragonofshuu.candylands.block.custom.bases;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.dragonofshuu.candylands.block.custom.CandyVerticalSpread;
import dev.dragonofshuu.candylands.registries.MainRegistries;
import dev.dragonofshuu.candylands.registries.spread.MainSpreads;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadFunction;
import dev.dragonofshuu.candylands.registries.spread.SpreadMemoizer;
import dev.dragonofshuu.candylands.registries.spread.SpreadRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class MemoizedSpreadBlock extends Block {
    public static final MapCodec<MemoizedSpreadBlock> CODEC = simpleCodec(MemoizedSpreadBlock::new);
    // public static final MapCodec<MemoizedSpreadBlock> CODEC =
    // RecordCodecBuilder.mapCodec((instance) -> {
    // return instance.group(
    // ResourceKey.codec(MainRegistries.SPREAD_FUNCTION.key()).fieldOf("spread_function")
    // .forGetter((block) -> {
    // return block.spreadFunctionKey;
    // }),
    // propertiesCodec())
    // .apply(instance,
    // (spreadFunctionKey, properties) -> new MemoizedSpreadBlock(spreadFunctionKey,
    // properties));
    // });

    private static final int MAX_SPREAD_ATTEMPTS = 3;
    private static final IntegerProperty SPREAD_ATTEMPTS = IntegerProperty.create("spread_attempts", 0, 3);
    private static final SpreadMemoizer SPREAD_MEMOIZER = new SpreadMemoizer(SPREAD_ATTEMPTS,
            MAX_SPREAD_ATTEMPTS);

    // public ResourceKey<SpreadFunction> spreadFunctionKey;

    public MemoizedSpreadBlock(Properties properties) {
        super(properties);
        // this.spreadFunctionKey = spreadFunctionKey;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(SPREAD_ATTEMPTS);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return SPREAD_MEMOIZER.canTrySpread(state);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess,
            BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return SPREAD_MEMOIZER.updateShapeSpreadBlock(state, neighborState);
    }

    @Override
    protected final void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!isRandomlyTicking(state)) {
            throw new IllegalStateException("randomTick called on non-randomly ticking block state");
        }

        if (!preSpreadTick(state, level, pos, random)) {
            return;
        }

        Consumer<SpreadContext> onCantSpread = (context) -> {
            SPREAD_MEMOIZER.setSpreadAttempts(level, state, pos, (old) -> old + 1);
        };

        spreadTick(state, level, pos, onCantSpread, random);
    }

    protected boolean preSpreadTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        return true;
    }

    protected void spreadTick(BlockState state, ServerLevel level, BlockPos pos, Consumer<SpreadContext> cantSpreadHook,
            RandomSource random) {
        // SpreadFunction spreadFunction =
        // level.registryAccess().get(this.spreadFunctionKey).get().value();
        // spreadFunction.tick(state, level, pos, cantSpreadHook, (context) -> {
        // }, random);
    }

    @Override
    public MapCodec<? extends MemoizedSpreadBlock> codec() {
        return CODEC;
    }
}
