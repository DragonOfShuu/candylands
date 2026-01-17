package dev.dragonofshuu.candylands.spread;

import java.util.List;
import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.block.custom.CandyGrassBlock;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import dev.dragonofshuu.candylands.spread.spread.SpreadConverter;
import dev.dragonofshuu.candylands.spread.spread.SpreadFunction;
import dev.dragonofshuu.candylands.spread.spread.SpreadRules;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class MainSpreads {
    public static final ResourceKey<Registry<SpreadFunction>> SPREAD_FUNCTION_REGISTRY_KEY = ResourceKey
            .createRegistryKey(
                    ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, "spread_functions"));
    public static final Registry<SpreadFunction> SPREAD_FUNCTION_REGISTRY = new RegistryBuilder<>(
            SPREAD_FUNCTION_REGISTRY_KEY)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, "default_spread_function"))
            .create();

    public static final DeferredRegister<SpreadFunction> SPREAD_FUNCTIONS = DeferredRegister
            .create(SPREAD_FUNCTION_REGISTRY, CandyLands.MODID);

    public static final Supplier<SpreadFunction> CANDY_GRASS_SPREAD = SPREAD_FUNCTIONS.register("candy_grass_spread",
            () -> SpreadFunction.make()
                    .usingDefaultSpreadRule(
                            SpreadRules.spreadRules()
                                    .addCondition(CandyGrassBlock::randomSpreadChance)
                                    .setMaxDistances(new Vec3i(2, 3, 2))
                                    .smart())
                    .useSpreaders((defaultSpreader) -> List.of(
                            defaultSpreader.extend()
                                    .addConversion(MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState(),
                                            SpreadConverter.of(MainBlocks.CANDY_GRASS_BLOCK.get().defaultBlockState(),
                                                    CandyGrassBlock::canPropagate))
                                    .addConversion(Blocks.DIRT.defaultBlockState(),
                                            MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState())
                                    .setMinMaxConversions(3, 20)
                                    .setBiome(MainBiomes.LICORICE_FOREST),
                            defaultSpreader.extend()
                                    .addConversion(Blocks.GRASS_BLOCK.defaultBlockState(),
                                            MainBlocks.CANDY_DIRT_BLOCK.get().defaultBlockState())
                                    .setMinMaxConversions(1, 5))));

    /**
     * This registers the new customer registry.
     * 
     * @param event
     */
    public static void registerNewRegister(NewRegistryEvent event) {
        event.register(SPREAD_FUNCTION_REGISTRY);
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
