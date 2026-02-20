package dev.dragonofshuu.candylands.block.custom.bases;

import dev.dragonofshuu.candylands.registries.spread.SpreadFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class SnowySpreadBlock extends CustomMemoizedSpreadBlock {
    public static final BooleanProperty SNOWY;

    public SnowySpreadBlock(Properties properties,
            ResourceKey<SpreadFunction> spreadFunction) {
        super(properties, spreadFunction);
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

    // protected void
    // createBlockStateDefinition(StateDefinition.Builder<Block,
    // BlockState> builder) {
    // builder.add(new Property[]{SNOWY});
    // }
    @Override
    protected void createBlockStateDefinition(
            Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SNOWY);
    }

    static {
        SNOWY = BlockStateProperties.SNOWY;
    }
}
