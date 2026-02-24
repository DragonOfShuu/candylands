package dev.dragonofshuu.candylands.block.custom.bases;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.registries.MainRegistries;
import dev.dragonofshuu.candylands.registries.spread.SpreadContext;
import dev.dragonofshuu.candylands.registries.spread.SpreadFunction;
import dev.dragonofshuu.candylands.util.MainGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.lighting.LightEngine;

public class SnowySpreadBlock extends CustomMemoizedSpreadBlock {
    public static final MapCodec<SnowySpreadBlock> CODEC = RecordCodecBuilder
            .mapCodec(instance -> instance.group(
                    Block.Properties.CODEC.fieldOf("properties")
                            .forGetter(b -> b.properties()),
                    ResourceKey
                            .codec(MainRegistries.SPREAD_FUNCTION_REGISTRY_KEY)
                            .fieldOf("spread_function")
                            .forGetter(b -> b.spreadFunction),
                    ResourceKey.codec(Registries.BLOCK).fieldOf("dirt_block")
                            .forGetter(b -> b.dirtBlock))
                    .apply(instance, SnowySpreadBlock::new));

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    private final ResourceKey<Block> dirtBlock;

    public SnowySpreadBlock(Properties properties,
            ResourceKey<SpreadFunction> spreadFunction,
            ResourceKey<Block> dirtBlock) {
        super(properties, spreadFunction);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(SNOWY, false));
        this.dirtBlock = dirtBlock;
    }

    protected BlockState updateShape(BlockState p_56644_, LevelReader p_374564_,
            ScheduledTickAccess p_374201_, BlockPos p_56648_,
            Direction p_56645_, BlockPos p_56649_, BlockState p_56646_,
            RandomSource p_374447_) {
        return p_56645_ == Direction.UP
                ? (BlockState) p_56644_.setValue(SNOWY,
                        isSnowySetting(p_56646_))
                : super.updateShape(p_56644_, p_374564_, p_374201_, p_56648_,
                        p_56645_, p_56649_, p_56646_, p_374447_);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel()
                .getBlockState(context.getClickedPos().above());
        return (BlockState) this.defaultBlockState().setValue(SNOWY,
                isSnowySetting(blockstate));
    }

    protected static boolean isSnowySetting(BlockState state) {
        return state.is(BlockTags.SNOW);
    }

    @Override
    protected void createBlockStateDefinition(
            Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SNOWY);
    }

    @Override
    protected boolean preSpreadTick(BlockState currentBlockState,
            ServerLevel level, BlockPos blockPos, RandomSource random) {
        if (!canBeGrass(currentBlockState, level, blockPos)) {
            // Forge: prevent loading unloaded chunks when checking
            // neighbor's light and
            // spreading
            if (!level.isAreaLoaded(blockPos, 1))
                return false;

            BlockState dirtState = BuiltInRegistries.BLOCK
                    .getValueOrThrow(dirtBlock).defaultBlockState();
            level.setBlockAndUpdate(blockPos, dirtState);
        }

        if (level.getMaxLocalRawBrightness(blockPos.above()) < 9)
            return false;
        return true;
    }

    public static boolean canPropagate(SpreadContext.Target context) {
        return canPropagate(context.sourceState(), context.level(),
                context.targetPos());
    }

    private static boolean canPropagate(BlockState state, LevelReader level,
            BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos)
                && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public static boolean randomSpreadChance(SpreadContext context) {
        return randomSpreadChance(context.level(), context.random());
    }

    private static boolean randomSpreadChance(ServerLevel level,
            RandomSource random) {
        int value = level.getGameRules()
                .getInt(MainGameRules.RULE_CANDY_SPREAD_CHANCE);

        if (value <= 0) {
            return true;
        }

        return random.nextInt(Math.clamp(value, 1, Integer.MAX_VALUE)) == 0;
    }

    private static boolean canBeGrass(BlockState state, LevelReader levelReader,
            BlockPos pos) {
        BlockPos blockAbove = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockAbove);
        if (blockstate.is(Blocks.SNOW)
                && (Integer) blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(state, blockstate,
                    Direction.UP, blockstate.getLightBlock());
            return i < 15;
        }
    }
}
