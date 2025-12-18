package dev.dragonofshuu.candylands.datagen;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class MainSoundProvider extends SoundDefinitionsProvider {

    protected MainSoundProvider(PackOutput output) {
        super(output, CandyLands.MODID);
    }

    @Override
    public void registerSounds() {
        // add(getName(), null);
    }
}
