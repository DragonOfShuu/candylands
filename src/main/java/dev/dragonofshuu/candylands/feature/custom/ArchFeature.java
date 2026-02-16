package dev.dragonofshuu.candylands.feature.custom;

import com.mojang.serialization.Codec;

import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
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

        // Define the spacing of the arches (e.g., every 64 blocks)
        int spacing = 64;
        int currentGridX = Math.floorDiv(origin.getX(), spacing);
        int currentGridZ = Math.floorDiv(origin.getZ(), spacing);

        for (int cellX = -3; cellX <= 3; cellX++) {
            for (int cellZ = -3; cellZ <= 3; cellZ++) {
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

                iterateThroughChunk(level, origin, archHeight, archWidth,
                        archCenter, angle);
            }
        }
        return true;
    }

    private void iterateThroughChunk(WorldGenLevel level, BlockPos origin,
            double archHeight, double archWidth, BlockPos archCenter,
            double angle) {
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
                        level.setBlock(mutable, MainBlocks.CANDY_CANE_ROCK.get()
                                .defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    // private boolean makeBlob(WorldGenLevel worldgenlevel,
    // Vec3 pos,
    // BlockState blockToPlace, int radius) {
    // var flooredBlockPos = new BlockPos((int)
    // Math.floor(pos.x()),
    // (int) Math.floor(pos.y()), (int) Math.floor(pos.z()));
    // var ceiledBlockPos = new BlockPos((int)
    // Math.ceil(pos.x()),
    // (int) Math.ceil(pos.y()), (int) Math.ceil(pos.z()));

    // for (BlockPos blockpos : BlockPos.betweenClosed(
    // flooredBlockPos.offset(-radius, -radius, -radius),
    // ceiledBlockPos.offset(radius, radius, radius))) {
    // if (blockpos.distToCenterSqr(pos) <= (double) (radius *
    // radius)) {
    // worldgenlevel.setBlock(blockpos, blockToPlace, 3);
    // }
    // }

    // return true;
    // }
}
