package dev.dragonofshuu.candylands.datagen.data;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

public class MainDataPackProvider extends DatapackBuiltinEntriesProvider {

    public MainDataPackProvider(PackOutput output, CompletableFuture<Provider> registries) {
        super(output, registries, getRegistries(), Set.of(CandyLands.MODID));
    }

    public static RegistrySetBuilder getRegistries() {
        return new RegistrySetBuilder();
        // .add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrap)
        // .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
        // .add(Registries.BIOME, ModBiomeData::bootstrap)
        // .add(Registries.TEMPLATE_POOL, ModStructurePoolData::bootstrap)
        // .add(Registries.STRUCTURE, ModStructureData::bootstrap)
        // .add(Registries.STRUCTURE_SET, ModStructureSetData::bootstrap)
        // .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);
    }
}
