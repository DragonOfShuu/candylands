package dev.dragonofshuu.candylands.block.custom.bases;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.dragonofshuu.candylands.registries.MainRegistries;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CustomMemoizedSpreadBlock extends MemoizedSpreadBlock {
    public static final MapCodec<CustomMemoizedSpreadBlock> CODEC = RecordCodecBuilder
            .mapCodec(instance -> instance.group(
                    Block.Properties.CODEC.fieldOf("properties")
                            .forGetter(b -> b.properties()),
                    ResourceKey
                            .codec(MainRegistries.SPREAD_FUNCTION_REGISTRY_KEY)
                            .fieldOf("spread_function")
                            .forGetter(b -> b.spreadFunction))
                    .apply(instance, CustomMemoizedSpreadBlock::new));

    protected final ResourceKey<SpreadFunction> spreadFunction;

    public CustomMemoizedSpreadBlock(Properties properties,
            ResourceKey<SpreadFunction> spreadFunction) {
        super(properties);
        this.spreadFunction = spreadFunction;
    }

    @Override
    protected void spreadTick(BlockState state, ServerLevel level, BlockPos pos,
            Consumer<SpreadContext> cantSpreadHook, RandomSource random) {
        MainRegistries.SPREAD_FUNCTION.getValueOrThrow(spreadFunction)
                .tick(state, level, pos, cantSpreadHook, (c) -> {
                }, random);
    }

    @Override
    public MapCodec<? extends MemoizedSpreadBlock> codec() {
        return CODEC;
    }
}
