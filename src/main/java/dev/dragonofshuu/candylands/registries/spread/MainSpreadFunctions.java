package dev.dragonofshuu.candylands.registries.spread;

import java.util.List;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.block.custom.CandyGrassBlock;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import dev.dragonofshuu.candylands.registries.MainRegistries;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;

public class MainSpreadFunctions {
    public static final DeferredRegister<SpreadFunction> SPREAD_FUNCTIONS = DeferredRegister
            .create(MainRegistries.SPREAD_FUNCTION, CandyLands.MODID);

    public static final DeferredHolder<SpreadFunction, SpreadFunction> CANDY_GRASS_SPREAD = SPREAD_FUNCTIONS
            .register("candy_grass_spread", () -> SpreadFunction.make()
                    .usingDefaultSpreadRule(SpreadRules.spreadRules()
                            .addCondition(CandyGrassBlock::randomSpreadChance)
                            .setMaxDistances(new Vec3i(2, 3, 2)).smart())
                    .useSpreaders((defaultSpreader) -> List.of(
                            defaultSpreader.extend().addBlockConversion(
                                    MainBlocks.CANDY_DIRT_BLOCK.get(),
                                    SpreadConverter.of(
                                            MainBlocks.CANDY_GRASS_BLOCK.get()
                                                    .defaultBlockState(),
                                            CandyGrassBlock::canPropagate))
                                    .addBlockConversion(Blocks.DIRT,
                                            MainBlocks.CANDY_DIRT_BLOCK.get()
                                                    .defaultBlockState())
                                    .addBlockConversion(Blocks.ICE,
                                            MainBlocks.CANDY_ICE_BLOCK.get()
                                                    .defaultBlockState())
                                    .setMinMaxConversions(3, 20)
                                    .setBiome(MainBiomes.LICORICE_FOREST),
                            defaultSpreader.extend().addBlockConversion(
                                    Blocks.GRASS_BLOCK,
                                    SpreadConverter.of(
                                            MainBlocks.CANDY_DIRT_BLOCK.get()
                                                    .defaultBlockState(),
                                            CandyGrassBlock::canPropagate))
                                    .setMinMaxConversions(1, 5))));

    public static final DeferredHolder<SpreadFunction, SpreadFunction> VERTICAL_ONLY_SPREAD = SPREAD_FUNCTIONS
            .register("vertical_only_spread",
                    () -> MainBaseSpreadFunctions.VERTICAL_SPREAD_SPREADER
                            .extend());

    public static final DeferredHolder<SpreadFunction, SpreadFunction> ICE_SPREAD = SPREAD_FUNCTIONS
            .register("ice_spread",
                    () -> MainBaseSpreadFunctions.VERTICAL_SPREAD_SPREADER
                            .extend()
                            .useSpreaders((defaultSpreader) -> List
                                    .of(defaultSpreader.extend()
                                            .setMaxDistances(new Vec3i(2, 3, 2))
                                            .setBiome(null)
                                            .addBlockConversions(
                                                    List.of(Blocks.ICE,
                                                            Blocks.PACKED_ICE,
                                                            Blocks.BLUE_ICE,
                                                            Blocks.FROSTED_ICE),
                                                    MainBlocks.CANDY_ICE_BLOCK
                                                            .get()
                                                            .defaultBlockState())
                                            .setMinMaxConversions(1, 5))));

    /**
     * This registers the new custom registry.
     * 
     * @param event
     */
    public static void registerNewRegister(NewRegistryEvent event) {
        event.register(MainRegistries.SPREAD_FUNCTION);
    }

    /**
     * Registers the spreads on the mod event bus.
     * 
     * @param bus
     */
    public static void register(IEventBus bus) {
        SPREAD_FUNCTIONS.register(bus);
    }
}
