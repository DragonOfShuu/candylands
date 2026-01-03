package dev.dragonofshuu.candylands.datagen.data.worldgen.biome;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class MainBiomes {
    public static final ResourceKey<Biome> LICORICE_FOREST = createKey("licorice_forest");

    public static ResourceKey<Biome> createKey(String key) {
        return ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath(CandyLands.MODID, key));
    }
}
