package dev.dragonofshuu.candylands.registries.spread;

import java.util.List;

import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Blocks;

public class MainBaseSpreadFunctions {
    protected static final SpreadFunction CANDY_VERTICAL_SPREAD_SPREADER = SpreadFunction
            .make()
            .usingDefaultSpreadRule(SpreadRules.spreadRules()
                    .setMaxDistances(new Vec3i(0, 3, 0))
                    .setBiome(MainBiomes.LICORICE_FOREST).smart())
            .useSpreaders((defaultSpreader) -> List.of(defaultSpreader.extend()
                    .addConversion(Blocks.DIRT.defaultBlockState(),
                            MainBlocks.CANDY_DIRT_BLOCK.get()
                                    .defaultBlockState())
                    .addConversion(Blocks.ICE.defaultBlockState(),
                            MainBlocks.CANDY_ICE_BLOCK.get()
                                    .defaultBlockState())
                    .setMinMaxConversions(1, 5)));

    protected static final SpreadFunction LICORICE_VERTICAL_SPREAD_SPREADER = SpreadFunction
            .make()
            .usingDefaultSpreadRule(SpreadRules.spreadRules()
                    .setMaxDistances(new Vec3i(0, 3, 0))
                    .setBiome(MainBiomes.LICORICE_FOREST).smart())
            .useSpreaders((defaultSpreader) -> List.of(defaultSpreader.extend()
                    .addConversion(Blocks.DIRT.defaultBlockState(),
                            MainBlocks.LICORICE_DIRT_BLOCK.get()
                                    .defaultBlockState())
                    // .addConversion(Blocks.ICE.defaultBlockState(),
                    // MainBlocks.LICORICE_ICE_BLOCK.get()
                    // .defaultBlockState())
                    .setMinMaxConversions(1, 5)));
}
