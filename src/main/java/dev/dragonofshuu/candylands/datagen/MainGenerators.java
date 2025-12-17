package dev.dragonofshuu.candylands.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = "candylands")
public class MainGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // ExistingFileHelper existingFileHelper = event.getExistingFileHelper(); # Does
        // not work currently, may have been renamed

        // SERVER

        // generator.addProvider(
        // event.includeServer(),
        // new ModDataPackProvider(output, lookupProvider));

        // generator.addProvider(
        // event.includeServer(),
        // new ModRecipeProvider(output, lookupProvider));

        // generator.addProvider(
        // event.includeServer(),
        // new ModLootTableProvider(output, lookupProvider));

        // ModBlockTagsProvider blockTagProvider = generator.addProvider(
        // event.includeServer(),
        // new ModBlockTagsProvider(output, lookupProvider, existingFileHelper));

        // generator.addProvider(
        // event.includeServer(),
        // new ModItemTagsProvider(output, lookupProvider,
        // blockTagProvider.contentsGetter()));

        // generator.addProvider(
        // event.includeServer(),
        // new ModBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        // // CLIENT

        // generator.addProvider(
        // event.includeClient(),
        // new ModLanguageEnUsProvider(output));

        // generator.addProvider(
        // event.includeClient(),
        // new ModBlockstateProvider(output, existingFileHelper));

        // generator.addProvider(
        // event.includeClient(),
        // new ModItemModelProvider(output, existingFileHelper));

        // generator.addProvider(
        // event.includeClient(),
        // new ModSoundProvider(output, existingFileHelper));
    }
}
