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

public class CottonCandyRegion extends Region {

    public CottonCandyRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
            Consumer<Pair<ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.PLAINS, MainBiomes.COTTON_CANDY_PLAINS);
            builder.replaceBiome(Biomes.SUNFLOWER_PLAINS,
                    MainBiomes.COTTON_CANDY_PLAINS);
        });
    }
}
