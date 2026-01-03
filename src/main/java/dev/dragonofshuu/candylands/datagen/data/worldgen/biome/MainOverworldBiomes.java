package dev.dragonofshuu.candylands.datagen.data.worldgen.biome;

import javax.annotation.Nullable;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainOverworldBiomes {
    @Nullable
    private static Music NORMAL_MUSIC = null;

    public static Biome licoriceForest(HolderGetter<PlacedFeature> placed_features_holder,
            HolderGetter<ConfiguredWorldCarver<?>> configured_carver_holder) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, 1,
                new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 10));
        mobspawnsettings$builder.addSpawn(MobCategory.MONSTER, 4,
                new MobSpawnSettings.SpawnerData(EntityType.CAVE_SPIDER, 5, 6));
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder(
                placed_features_holder, configured_carver_holder);
        globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        // BiomeDefaultFeatures.addMountainForestTrees(biomegenerationsettings$builder);

        // BiomeDefaultFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        // BiomeDefaultFeatures.addDefaultGrass(biomegenerationsettings$builder);
        // BiomeDefaultFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder, true);
        // BiomeDefaultFeatures.addInfestedStone(biomegenerationsettings$builder);

        var specialEffects = new BiomeSpecialEffects.Builder()
                .waterColor(0x9a21eb)
                .waterFogColor(0xbc10e3)
                .fogColor(0xd8a8e3)
                .skyColor(0xffffff)
                .grassColorOverride(0xfbb7ec)
                .foliageColorOverride(0xab85cc)
                .dryFoliageColorOverride(0xfbb7ec)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(NORMAL_MUSIC);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-0.5F)
                .downfall(0.4F)
                .specialEffects(specialEffects.build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
    }

    private static Biome biome(
            boolean hasPercipitation,
            float temperature,
            float downfall,
            MobSpawnSettings.Builder mobSpawnSettings,
            BiomeGenerationSettings.Builder generationSettings,
            @Nullable Music backgroundMusic) {
        return biome(hasPercipitation, temperature, downfall, 4159204, 329011, null, null, mobSpawnSettings,
                generationSettings, backgroundMusic);
    }

    private static Biome biome(
            boolean hasPrecipitation,
            float temperature,
            float downfall,
            int waterColor,
            int waterFogColor,
            @Nullable Integer grassColorOverride,
            @Nullable Integer foliageColorOverride,
            MobSpawnSettings.Builder mobSpawnSettings,
            BiomeGenerationSettings.Builder generationSettings,
            @Nullable Music backgroundMusic) {
        BiomeSpecialEffects.Builder biomespecialeffects$builder = new BiomeSpecialEffects.Builder()
                .waterColor(waterColor)
                .waterFogColor(waterFogColor)
                .fogColor(12638463)
                .skyColor(calculateSkyColor(temperature))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(backgroundMusic);
        if (grassColorOverride != null) {
            biomespecialeffects$builder.grassColorOverride(grassColorOverride);
        }

        if (foliageColorOverride != null) {
            biomespecialeffects$builder.foliageColorOverride(foliageColorOverride);
        }

        return new Biome.BiomeBuilder()
                .hasPrecipitation(hasPrecipitation)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects(biomespecialeffects$builder.build())
                .mobSpawnSettings(mobSpawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    protected static int calculateSkyColor(float temperature) {
        float $$1 = temperature / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
}
