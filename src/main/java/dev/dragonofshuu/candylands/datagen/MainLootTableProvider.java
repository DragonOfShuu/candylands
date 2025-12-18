package dev.dragonofshuu.candylands.datagen;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.datagen.loot.MainBlockLootTable;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class MainLootTableProvider extends LootTableProvider {

    public MainLootTableProvider(PackOutput output, CompletableFuture<Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(
                        MainBlockLootTable::new,
                        LootContextParamSets.BLOCK)),
                registries);
    }

}
