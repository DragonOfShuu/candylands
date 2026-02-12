package dev.dragonofshuu.candylands.feature.custom;

import java.util.Iterator;

import org.slf4j.Logger;

import com.mojang.serialization.Codec;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.phys.Vec3;

public class ArchFeature extends Feature<BlockStateConfiguration> {
    private static Logger LOGGER = CandyLands.LOGGER;


    public ArchFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(
            FeaturePlaceContext<BlockStateConfiguration> placeContext) {

        BlockPos blockpos = placeContext.origin();
        WorldGenLevel worldgenlevel = placeContext.level();
        RandomSource randomsource = placeContext.random();

        BlockPos surfaceBlockPos = findSurfaceBlockPos(blockpos, worldgenlevel);
        if (surfaceBlockPos == null) {
            return false;
        }

        BlockState blockToPlace = placeContext.config().state;

        double randomHeight = randomsource.nextDouble() * 1.4 + 3.6;
        double randomLength = randomsource.nextDouble() * 0.2 + 0.15;
        double randomRotation = randomsource.nextDouble() * Math.PI * 2;

        FollowAlongArchIterator archIterator = new FollowAlongArchIterator(
                surfaceBlockPos.getCenter(), randomHeight, randomLength,
                randomRotation);

        drawArch(worldgenlevel, blockToPlace, archIterator);

        return true;
    }

    private void drawArch(WorldGenLevel worldgenlevel, BlockState blockToPlace,
            FollowAlongArchIterator archIterator) {
        Boolean breakFlag = false;
        while (archIterator.hasNext() && !breakFlag) {
            Vec3 marker = archIterator.next();
            var markerBlockPos = new BlockPos((int) Math.floor(marker.x()),
                    (int) Math.floor(marker.y()), (int) Math.floor(marker.z()));
            if (isDirt(worldgenlevel.getBlockState(markerBlockPos))
                    || isStone(worldgenlevel.getBlockState(markerBlockPos))
                    || markerBlockPos.getY() <= worldgenlevel.getMinY() + 6) {
                breakFlag = true;
            }
            makeBlob(worldgenlevel, marker, blockToPlace, 4);
        }
    }

    private BlockPos findSurfaceBlockPos(BlockPos pos,
            WorldGenLevel worldgenlevel) {
        for (var blockpos = pos.immutable(); blockpos.getY() > worldgenlevel
                .getMinY() + 6; blockpos = blockpos.below()) {
            if (!worldgenlevel.isEmptyBlock(blockpos.below())) {
                BlockState blockstate = worldgenlevel
                        .getBlockState(blockpos.below());
                if (isDirt(blockstate) || isStone(blockstate)) {
                    return blockpos;
                }
            }
        }

        return null;
    }

    private boolean makeBlob(WorldGenLevel worldgenlevel, Vec3 pos,
            BlockState blockToPlace, int radius) {
        var flooredBlockPos = new BlockPos((int) Math.floor(pos.x()),
                (int) Math.floor(pos.y()), (int) Math.floor(pos.z()));
        var ceiledBlockPos = new BlockPos((int) Math.ceil(pos.x()),
                (int) Math.ceil(pos.y()), (int) Math.ceil(pos.z()));

        for (BlockPos blockpos : BlockPos.betweenClosed(
                flooredBlockPos.offset(-radius, -radius, -radius),
                ceiledBlockPos.offset(radius, radius, radius))) {
            if (blockpos.distToCenterSqr(pos) <= (double) (radius * radius)) {
                worldgenlevel.setBlock(blockpos, blockToPlace, 3);
            }
        }

        return true;
    }

    class FollowAlongArchIterator implements Iterator<Vec3> {
        private final double height;
        private final double length;
        private final double rotationRad;
        private final Vec3 startMarker;
        private int x = 0;

        public FollowAlongArchIterator(Vec3 startMarker, double height,
                double length, double rotationRad) {
            this.startMarker = startMarker;
            this.height = height;
            this.length = length;
            this.rotationRad = rotationRad;
        }

        @Override
        public boolean hasNext() {
            return true; // Infinite iterator
        }

        @Override
        public Vec3 next() {
            // double y = -(Math.pow(x - length, 2) - Math.pow(height,
            // 2));
            double y = -Math.pow(length * x - height, 2) + Math.pow(height, 2);
            Vec3 marker = new Vec3(startMarker.x() + x * Math.cos(rotationRad),
                    startMarker.y() + y,
                    startMarker.z() + x * Math.sin(rotationRad));
            x++;
            CandyLands.LOGGER.debug("Placing next at: " + marker.toString());
            return marker;
        }

        public void reset() {
            x = 0;
        }
    }
}
