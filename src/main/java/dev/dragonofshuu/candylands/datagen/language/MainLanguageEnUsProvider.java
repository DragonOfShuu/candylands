package dev.dragonofshuu.candylands.datagen.language;

import dev.dragonofshuu.candylands.CandyLands;
import dev.dragonofshuu.candylands.block.MainBlocks;
import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import net.minecraft.data.PackOutput;

public class MainLanguageEnUsProvider extends MainLanguageBaseProvider {
    public MainLanguageEnUsProvider(PackOutput output) {
        super(
                // Provided by the GatherDataEvent.
                output,
                // Your mod id.
                CandyLands.MODID,
                // The locale to use. You may use multiple language
                // providers for different
                // locales.
                "en_us");
    }

    // "itemGroup.candylands": "Example Mod Tab",

    // "candylands.configuration.title": "Candy Lands Configs",
    // "candylands.configuration.section.candylands.common.toml":
    // "Candy Lands
    // Configs",
    // "candylands.configuration.section.candylands.common.toml.title":
    // "Candy Lands
    // Configs",
    // "candylands.configuration.items": "Item List"

    @Override
    protected void addTranslations() {
        add(MainBlocks.CANDY_GRASS_BLOCK.get(), "Candy Grass Block");
        add(MainBlocks.CANDY_DIRT_BLOCK.get(), "Candy Dirt Block");
        add(MainBlocks.CANDY_ICE_BLOCK.get(), "Candy Ice Block");

        add(MainBlocks.LICORICE_GRASS_BLOCK.get(), "Licorice Grass Block");
        add(MainBlocks.LICORICE_DIRT_BLOCK.get(), "Licorice Dirt Block");

        add(MainBlocks.CANDY_CANE_ROCK.get(), "Candy Cane Rock");
        add(MainBlocks.CANDY_CANE_COBBLESTONE.get(), "Candy Cane Cobblestone");

        add(MainBlocks.CANDY_CANE_FLOWER.get(), "Candy Cane Flower");
        add(MainBlocks.CANDY_CANE_SHORT_GRASS.get(), "Candy Cane Short Grass");

        add(MainBlocks.LICORICE_LOG.get(), "Licorice Log");
        add(MainBlocks.LICORICE_LEAVES.get(), "Licorice Leaves");
        add(MainBlocks.LICORICE_SPROUT.get(), "Licorice Sprout");
        add(MainBlocks.LICORICE_WOOD.get(), "Licorice Wood");

        add(MainBlocks.LICORICE_PLANKS.get(), "Licorice Planks");
        add(MainBlocks.LICORICE_SLAB.get(), "Licorice Slab");
        add(MainBlocks.LICORICE_BUTTON.get(), "Licorice Button");
        add(MainBlocks.LICORICE_PRESSURE_PLATE.get(),
                "Licorice Pressure Plate");
        add(MainBlocks.LICORICE_FENCE.get(), "Licorice Fence");
        add(MainBlocks.LICORICE_FENCE_GATE.get(), "Licorice Fence Gate");
        add(MainBlocks.LICORICE_STAIRS.get(), "Licorice Stairs");

        add(MainBlocks.CANDY_CANE_ROCK_SLAB.get(), "Candy Cane Rock Slab");
        add(MainBlocks.CANDY_CANE_ROCK_STAIRS.get(), "Candy Cane Rock Stairs");
        add(MainBlocks.CANDY_CANE_ROCK_WALL.get(), "Candy Cane Rock Wall");
        add(MainBlocks.CANDY_CANE_ROCK_FENCE.get(), "Candy Cane Rock Fence");
        add(MainBlocks.CANDY_CANE_ROCK_FENCE_GATE.get(),
                "Candy Cane Rock Fence Gate");
        add(MainBlocks.CANDY_CANE_ROCK_DOOR.get(), "Candy Cane Rock Door");
        add(MainBlocks.CANDY_CANE_ROCK_TRAPDOOR.get(),
                "Candy Cane Rock Trapdoor");
        add(MainBlocks.CANDY_CANE_ROCK_BUTTON.get(), "Candy Cane Rock Button");
        add(MainBlocks.CANDY_CANE_ROCK_PRESSURE_PLATE.get(),
                "Candy Cane Rock Pressure Plate");

        addBiome(MainBiomes.LICORICE_FOREST, "Licorice Forest");

        add("CandyLands.configuration.title", "Candy Lands Configs");
        add("CandyLands.configuration.section.candylands.common.toml",
                "Candy Lands Configs");
        add("CandyLands.configuration.section.candylands.common.toml.title",
                "Candy Lands Configs");
        add("CandyLands.configuration.items", "Item List");

        add("gamerule.candySpreadChance", "Candy Spread Chance");
    }
}
