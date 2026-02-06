package dev.dragonofshuu.candylands.datagen.models;

import net.minecraft.client.data.models.model.ModelTemplates;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;

public class MainModelTemplates {
    public static ExtendedModelTemplate DOOR_BOTTOM_LEFT_CUTOUT = ModelTemplates.DOOR_BOTTOM_LEFT
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_BOTTOM_LEFT_OPEN_CUTOUT = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_BOTTOM_RIGHT_CUTOUT = ModelTemplates.DOOR_BOTTOM_RIGHT
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_BOTTOM_RIGHT_OPEN_CUTOUT = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_TOP_LEFT_CUTOUT = ModelTemplates.DOOR_TOP_LEFT
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_TOP_LEFT_OPEN_CUTOUT = ModelTemplates.DOOR_TOP_LEFT_OPEN
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_TOP_RIGHT_CUTOUT = ModelTemplates.DOOR_TOP_RIGHT
            .extend().renderType("minecraft:cutout").build();
    public static ExtendedModelTemplate DOOR_TOP_RIGHT_OPEN_CUTOUT = ModelTemplates.DOOR_TOP_RIGHT_OPEN
            .extend().renderType("minecraft:cutout").build();
}
