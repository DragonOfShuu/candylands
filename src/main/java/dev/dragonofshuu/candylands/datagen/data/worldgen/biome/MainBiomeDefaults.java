package dev.dragonofshuu.candylands.datagen.data.worldgen.biome;

import javax.annotation.Nullable;

import dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature.MainPlacedVegetationFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class MainBiomeDefaults {
    public static Biome biome(boolean hasPercipitation, float temperature,
            float downfall, MobSpawnSettings.Builder mobSpawnSettings,
            BiomeGenerationSettings.Builder generationSettings,
            @Nullable Music backgroundMusic) {
        return biome(hasPercipitation, temperature, downfall, 4159204, 329011,
                null, null, mobSpawnSettings, generationSettings,
                backgroundMusic);
    }

    public static Biome biome(boolean hasPrecipitation, float temperature,
            float downfall, int waterColor, int waterFogColor,
            @Nullable Integer grassColorOverride,
            @Nullable Integer foliageColorOverride,
            MobSpawnSettings.Builder mobSpawnSettings,
            BiomeGenerationSettings.Builder generationSettings,
            @Nullable Music backgroundMusic) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .waterColor(waterColor).waterFogColor(waterFogColor)
                .fogColor(12638463).skyColor(calculateSkyColor(temperature))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(backgroundMusic);
        if (grassColorOverride != null) {
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);
        }

        if (foliageColorOverride != null) {
            biomespecialeffects$builder
                    .foliageColorOverride(foliageColorOverride);
        }

        return new Biome.BiomeBuilder().hasPrecipitation(hasPrecipitation)
                .temperature(temperature).downfall(downfall)
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    public static void addDefaultCandyGrass(
            BiomeGenerationSettings.Builder generationSettings) {
        generationSettings.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                MainPlacedVegetationFeatures.PATCH_CANDY_GRASS);
    }

    public static void addDefaultCandyCaneFlowers(
            BiomeGenerationSettings.Builder generationSettings) {
        generationSettings.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                MainPlacedVegetationFeatures.FLOWER_CANDY_CANE);
    }

    public static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
