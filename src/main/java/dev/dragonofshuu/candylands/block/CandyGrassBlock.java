package dev.dragonofshuu.candylands.block;

import com.mojang.serialization.MapCodec;

import dev.dragonofshuu.candylands.block.custom.IOnJump;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
// import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class CandyGrassBlock extends Block implements BonemealableBlock, IOnJump {
    public CandyGrassBlock(Properties properties) {
        super(properties);
    }

    public static final MapCodec<CandyGrassBlock> CODEC = simpleCodec(CandyGrassBlock::new);

    @Override
    public MapCodec<CandyGrassBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256229_, BlockPos p_256432_, BlockState p_255677_) {
        return p_256229_.getBlockState(p_256432_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_221275_, RandomSource p_221276_, BlockPos p_221277_,
            BlockState p_221278_) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_,
            BlockState p_221273_) {
        // BlockPos blockpos = p_221272_.above();
        // BlockState blockstate = Blocks.SHORT_GRASS.defaultBlockState();
        // Optional<Holder.Reference<PlacedFeature>> optional =
        // p_221270_.registryAccess()
        // .lookupOrThrow(Registries.PLACED_FEATURE)
        // .get(VegetationPlacements.GRASS_BONEMEAL);

        // label51:
        // for (int i = 0; i < 128; i++) {
        // BlockPos blockpos1 = blockpos;

        // for (int j = 0; j < i / 16; j++) {
        // blockpos1 = blockpos1.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3)
        // - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
        // if (!p_221270_.getBlockState(blockpos1.below()).is(this) ||
        // p_221270_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_221270_,
        // blockpos1)) {
        // continue label51;
        // }
        // }

        // BlockState blockstate1 = p_221270_.getBlockState(blockpos1);
        // if (blockstate1.is(blockstate.getBlock()) && p_221271_.nextInt(10) == 0) {
        // BonemealableBlock bonemealableblock =
        // (BonemealableBlock)blockstate.getBlock();
        // if (bonemealableblock.isValidBonemealTarget(p_221270_, blockpos1,
        // blockstate1)) {
        // bonemealableblock.performBonemeal(p_221270_, p_221271_, blockpos1,
        // blockstate1);
        // }
        // }

        // if (blockstate1.isAir()) {
        // Holder<PlacedFeature> holder;
        // if (p_221271_.nextInt(8) == 0) {
        // List<ConfiguredFeature<?, ?>> list =
        // p_221270_.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
        // if (list.isEmpty()) {
        // continue;
        // }

        // int k = p_221271_.nextInt(list.size());
        // holder = ((RandomPatchConfiguration)list.get(k).config()).feature();
        // } else {
        // if (!optional.isPresent()) {
        // continue;
        // }

        // holder = optional.get();
        // }

        // holder.value().place(p_221270_, p_221270_.getChunkSource().getGenerator(),
        // p_221271_, blockpos1);
        // }
        // }
    }

    @Override
    public BonemealableBlock.Type getType() {
        return BonemealableBlock.Type.NEIGHBOR_SPREADER;
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        if (!canBeGrass(currentBlockState, level, blockPos)) {
            if (!level.isAreaLoaded(blockPos, 1))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and
                        // spreading
            level.setBlockAndUpdate(blockPos, MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState());
        } else {
            if (!level.isAreaLoaded(blockPos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and
                        // spreading
            if (level.getMaxLocalRawBrightness(blockPos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for (int i = 0; i < 4; i++) {
                    BlockPos blockpos = blockPos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3,
                            random.nextInt(3) - 1);
                    if (level.getBlockState(blockpos).is(MainBlocks.CANDY_DIRT_BLOCK.get())
                            && canPropagate(blockstate, level, blockpos)) {
                        level.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }
        }
    }

    private static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = levelReader.getBlockState(blockpos);
        // if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS)
        // == 1) {
        // return true;
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(state, blockstate, Direction.UP, blockstate.getLightBlock());
            return i < 15;
        }
    }

    @Override
    public void OnJumpIn(LivingEntity entity) {

    }

    @Override
    public void OnJumpOn(LivingEntity entity) {
        
    }
}
