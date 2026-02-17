package dev.dragonofshuu.candylands.feature.custom;

import com.mojang.serialization.Codec;

import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

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
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = origin.getX() + x - 8;
                int worldZ = origin.getZ() + z - 8;

                // Calculate distance from the arch's center line
                double dx = worldX - archCenter.getX();
                double dz = worldZ - archCenter.getZ();

                double localX = dx * Math.cos(angle) - dz * Math.sin(angle);
                double localZ = dx * Math.sin(angle) + dz * Math.cos(angle);

                // Calculate the IDEAL Y for this localX
                double idealY = archCenter.getY() + archHeight
                        - Math.pow(localX * archWidth, 2);

                // Iterate through a small vertical range to fill the
                // arch's "body"
                for (int y = -3; y <= 3; y++) {
                    int worldY = (int) Math.round(idealY) + y;

                    // Calculate distance from the current block to the ideal
                    // arch point (localX, idealY, 0)
                    // We use localZ here because in "local space" the arch is
                    // always at Z=0
                    double distSq = Math.pow(worldY - idealY, 2)
                            + Math.pow(localZ, 2);

                    // If distance is less than radius squared (e.g., radius of
                    // 4.0)
                    if (distSq < 4.0) {
                        mutable.set(worldX, worldY, worldZ);
                        level.setBlock(mutable, blockState, 2);
                    }
                }
            }
        }
    }
}
