package dev.dragonofshuu.candylands.datagen.language;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.data.PackOutput;

public class MainLanguageEnUsProvider extends MainLanguageBaseProvider {
    public MainLanguageEnUsProvider(PackOutput output) {
        super(
                // Provided by the GatherDataEvent.
                output,
                // Your mod id.
                CandyLands.MODID,
                // The locale to use. You may use multiple language providers for different
                // locales.
                "en_us");
    }

    // "itemGroup.candylands": "Example Mod Tab",

    // "candylands.configuration.title": "Candy Lands Configs",
    // "candylands.configuration.section.candylands.common.toml": "Candy Lands
    // Configs",
    // "candylands.configuration.section.candylands.common.toml.title": "Candy Lands
    // Configs",
    // "candylands.configuration.items": "Item List"

    @Override
    protected void addTranslations() {
        add(MainBlocks.CANDY_GRASS_BLOCK.get(), "Candy Grass Block");
        add(MainBlocks.CANDY_DIRT_BLOCK.get(), "Candy Dirt Block");

        add(MainBlocks.LICORICE_WOOD.get(), "Licorice Wood");
        add(MainBlocks.LICORICE_LEAVES.get(), "Licorice Leaves");
        add(MainBlocks.LICORICE_SPROUT.get(), "Licorice Sprout");

        add(MainBlocks.LICORICE_PLANKS.get(), "Licorice Planks");
        add(MainBlocks.LICORICE_SLAB.get(), "Licorice Slab");
        add(MainBlocks.LICORICE_BUTTON.get(), "Licorice Button");
        add(MainBlocks.LICORICE_PRESSURE_PLATE.get(), "Licorice Pressure Plate");
        add(MainBlocks.LICORICE_FENCE.get(), "Licorice Fence");
        add(MainBlocks.LICORICE_FENCE_GATE.get(), "Licorice Fence Gate");
        add(MainBlocks.LICORICE_STAIRS.get(), "Licorice Stairs");

        add("CandyLands.configuration.title", "Candy Lands Configs");
        add("CandyLands.configuration.section.candylands.common.toml", "Candy Lands Configs");
        add("CandyLands.configuration.section.candylands.common.toml.title", "Candy Lands Configs");
        add("CandyLands.configuration.items", "Item List");
    }
}
