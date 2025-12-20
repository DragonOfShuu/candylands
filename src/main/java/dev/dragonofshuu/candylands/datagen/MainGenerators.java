package dev.dragonofshuu.candylands.datagen;

import java.util.concurrent.CompletableFuture;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.datagen.data.MainDataPackProvider;
import dev.dragonofshuu.candylands.datagen.language.MainLanguageEnUsProvider;
import dev.dragonofshuu.candylands.datagen.tagProviders.MainBiomeTagsProvider;
import dev.dragonofshuu.candylands.datagen.tagProviders.MainBlockTagsProvider;
import dev.dragonofshuu.candylands.datagen.tagProviders.MainItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = CandyLands.MODID)
public class MainGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createDatapackRegistryObjects(MainDataPackProvider.getRegistries());

        // SERVER
        event.createProvider(MainRecipeProvider.Runner::new);

        event.createProvider(MainLootTableProvider::new);

        event.createProvider(MainBlockTagsProvider::new);

        event.createProvider(MainItemTagsProvider::new);

        event.createProvider(MainBiomeTagsProvider::new);

        // CLIENT
        event.createProvider(MainLanguageEnUsProvider::new);

        event.createProvider(MainModelProvider::new);

        event.createProvider(MainSoundProvider::new);
    }
}
