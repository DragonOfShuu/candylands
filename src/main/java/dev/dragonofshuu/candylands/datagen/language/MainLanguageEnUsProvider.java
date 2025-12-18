package dev.dragonofshuu.candylands.datagen.language;

import dev.dragonofshuu.candylands.CandyLands;
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

    @Override
    protected void addTranslations() {

    }
}
