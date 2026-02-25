package dev.dragonofshuu.candylands.datagen.data.worldgen.biome;

import javax.annotation.Nullable;

import dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature.MainMiscOverworldPlacements;
import dev.dragonofshuu.candylands.datagen.data.worldgen.placed_feature.MainPlacedVegetationFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MainOverworldBiomes {
    @Nullable
    private static Music NORMAL_MUSIC = null;

    public static Biome licoriceForest(
            HolderGetter<PlacedFeature> placed_features_holder,
            HolderGetter<ConfiguredWorldCarver<?>> configured_carver_holder) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        mobspawnsettings$builder.addSpawn(MobCategory.CREATURE, 1,
                new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 10));
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder(
                placed_features_holder, configured_carver_holder);
        globalOverworldGeneration(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addFeature(
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                MainMiscOverworldPlacements.LICORICE_ARCH);
        biomegenerationsettings$builder.addFeature(
                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                MainMiscOverworldPlacements.CANDY_CANE_BLOB);
        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures
                .addDefaultSoftDisks(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                MainPlacedVegetationFeatures.TREES_LICORICE);

        MainBiomeDefaults.addDefaultCandyGrass(biomegenerationsettings$builder);
        MainBiomeDefaults
                .addDefaultCandyCaneFlowers(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(
                biomegenerationsettings$builder, true);

        var specialEffects = new BiomeSpecialEffects.Builder()
                .waterColor(0xbd3a62).waterFogColor(0xbd3a62).fogColor(0xbd3a62)
                .skyColor(0xbd3a62).grassColorOverride(0xbd3a62)
                .foliageColorOverride(0xbd3a62)
                .dryFoliageColorOverride(0xbd3a62)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(NORMAL_MUSIC);

        return new Biome.BiomeBuilder().hasPrecipitation(true)
                .temperature(-1.5F).downfall(0.4F)
                .specialEffects(specialEffects.build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    public static Biome cottonCandyPlains(
            HolderGetter<PlacedFeature> placed_features_holder,
            HolderGetter<ConfiguredWorldCarver<?>> configured_carver_holder) {
        MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(mobspawnsettings$builder);
        BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder(
                placed_features_holder, configured_carver_holder);
        globalOverworldGeneration(biomegenerationsettings$builder);

        // Local Features -- Placeholder

        BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
        BiomeDefaultFeatures
                .addDefaultSoftDisks(biomegenerationsettings$builder);
        biomegenerationsettings$builder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,
                MainPlacedVegetationFeatures.TREES_LICORICE);

        BiomeDefaultFeatures.addDefaultExtraVegetation(
                biomegenerationsettings$builder, true);

        var specialEffects = new BiomeSpecialEffects.Builder()
                .waterColor(0x9a21eb).waterFogColor(0xbc10e3).fogColor(0xd8a8e3)
                .skyColor(0xffffff).grassColorOverride(0xfbb7ec)
                .foliageColorOverride(0xab85cc)
                .dryFoliageColorOverride(0xfbb7ec)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(NORMAL_MUSIC);

        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(1.5F)
                .downfall(0.4F).specialEffects(specialEffects.build())
                .mobSpawnSettings(mobspawnsettings$builder.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    public static void globalOverworldGeneration(
            BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
    }
}
