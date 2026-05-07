package dev.dragonofshuu.candylands.feature.custom;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.phys.Vec3;

public class ArchFeature extends Feature<BlockStateConfiguration> {

    public ArchFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(
            FeaturePlaceContext<BlockStateConfiguration> placeContext) {

        WorldGenLevel level = placeContext.level();
        BlockPos real_origin = placeContext.origin();
        ChunkPos chunkPos = new ChunkPos(real_origin);
        BlockPos origin = chunkPos.getMiddleBlockPosition(real_origin.getY());

        BlockState blockState = placeContext.config().state;

        // Define the spacing of the arches (e.g., every 64 blocks)
        int spacing = 64;
        int currentGridX = Math.floorDiv(origin.getX(), spacing);
        int currentGridZ = Math.floorDiv(origin.getZ(), spacing);

        buildCellifiedArches(level, blockState, origin, spacing, currentGridX,
                currentGridZ, 3);
        return true;
    }

    private void buildCellifiedArches(WorldGenLevel level,
            BlockState blockState, BlockPos origin, int spacing,
            int currentGridX, int currentGridZ, int count) {
        for (int cellX = -count; cellX <= count; cellX++) {
            for (int cellZ = -count; cellZ <= count; cellZ++) {
                int gridX = (currentGridX + cellX) * spacing;
                int gridZ = (currentGridZ + cellZ) * spacing;

                RandomSource random = RandomSource
                        .create(level.getSeed() ^ (gridX * 9223372036854775807L)
                                ^ (gridZ * 4294967291L));

                // Randomize arch properties
                double angle = random.nextDouble() * Math.PI; // Random rotation
                double archHeight = 3.6d + random.nextDouble() * 1.4d;
                double archWidth = 0.10d + random.nextDouble() * 0.2d;
                int archCenterX = gridX + spacing / 2 + random.nextInt(spacing);
                int archCenterZ = gridZ + spacing / 2 + random.nextInt(spacing);
                BlockPos archCenter = new BlockPos(archCenterX,
                        random.nextInt(40) + 70, archCenterZ);

                iterateThroughChunk(level, blockState, origin, archHeight,
                        archWidth, archCenter, angle);
            }
        }
    }

    private void iterateThroughChunk(WorldGenLevel level, BlockState blockState,
            BlockPos origin, double archHeight, double archWidth,
            BlockPos archCenter, double angle) {
        int checkSize = 32; // Check a 32x32 area around the chunk center
        for (int x = 0; x < checkSize; x++) {
            for (int z = 0; z < checkSize; z++) {
                int worldX = origin.getX() + x - checkSize / 2;
                int worldZ = origin.getZ() + z - checkSize / 2;

                // Calculate distance from the arch's center line
                double dx = worldX - archCenter.getX();
                double dz = worldZ - archCenter.getZ();

                double localX = dx * Math.cos(angle) - dz * Math.sin(angle);
                double localZ = dx * Math.sin(angle) + dz * Math.cos(angle);

                // Calculate the IDEAL Y for this localX
                double idealY = archCenter.getY() + archHeight
                        - Math.pow(localX * archWidth, 2);

                if (Math.abs(localZ) < 1.5) {
                    var position = new Vec3(worldX, idealY, worldZ);
                    // var radius = Math.max(0,
                    // 3 - (int) Math.abs((localZ + localX) / 2));
                    var radius = 4;
                    makeBlob(level, position, blockState, radius);
                }
            }
        }
    }

    private boolean makeBlob(WorldGenLevel worldgenlevel, Vec3 pos,
            BlockState blockToPlace, int radius) {
        if (radius <= 0) {
            return false;
        }
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
}
