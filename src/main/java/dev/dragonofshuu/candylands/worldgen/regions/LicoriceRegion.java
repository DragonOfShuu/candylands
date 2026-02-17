package dev.dragonofshuu.candylands.worldgen.regions;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import dev.dragonofshuu.candylands.datagen.data.worldgen.biome.MainBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate.ParameterPoint;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class LicoriceRegion extends Region {

    public LicoriceRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
            Consumer<Pair<ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.SNOWY_TAIGA,
                    MainBiomes.LICORICE_FOREST);
            builder.replaceBiome(Biomes.SNOWY_PLAINS,
                    MainBiomes.LICORICE_FOREST);
            builder.replaceBiome(Biomes.TAIGA, MainBiomes.LICORICE_FOREST);
        });
    }
}
