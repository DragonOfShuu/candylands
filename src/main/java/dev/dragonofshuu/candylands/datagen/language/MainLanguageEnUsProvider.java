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
        add("CandyLands.configuration.title", "Candy Lands Configs");
        add("CandyLands.configuration.section.candylands.common.toml", "Candy Lands Configs");
        add("CandyLands.configuration.section.candylands.common.toml.title", "Candy Lands Configs");
        add("CandyLands.configuration.items", "Item List");
    }
}
