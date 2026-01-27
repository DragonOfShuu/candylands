package dev.dragonofshuu.candylands.block.custom;

import java.util.function.Consumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.custom.bases.MemoizedSpreadBlock;
import dev.dragonofshuu.candylands.registries.MainRegistries;
import dev.dragonofshuu.candylands.registries.spread.MainSpreadFunctions;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadMemoizer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CandyVerticalSpread extends MemoizedSpreadBlock {
    public static final MapCodec<CandyVerticalSpread> CODEC = RecordCodecBuilder.mapCodec((p_432677_) -> {
        return p_432677_.group(ResourceKey.codec(Registries.BIOME).fieldOf("biome").forGetter((p_304391_) -> {
            return p_304391_.biome;
        }), propertiesCodec()).apply(p_432677_, CandyVerticalSpread::new);
    });

    private final ResourceKey<Biome> biome;

    public CandyVerticalSpread(ResourceKey<Biome> biome, Properties properties) {
        super(properties);
        this.biome = biome;
    }

    @Override
    protected void spreadTick(BlockState state, ServerLevel level, BlockPos pos, Consumer<SpreadContext> cantSpreadHook,
            RandomSource random) {
        MainSpreadFunctions.VERTICAL_ONLY_SPREAD.get().tick(state, level, pos, random);
    }
}
