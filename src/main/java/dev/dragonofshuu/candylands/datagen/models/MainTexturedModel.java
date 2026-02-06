package dev.dragonofshuu.candylands.datagen.models;

import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public class MainTexturedModel {
    public static final TexturedModel.Provider CUBE_ALL_CUTOUT = TexturedModel.CUBE
            .updateTemplate(template -> template.extend()
                    .renderType("minecraft:cutout").build());

    public static final TexturedModel.Provider CUBE_ALL_CUTOUT_MIPPED = TexturedModel.CUBE
            .updateTemplate(template -> template.extend()
                    .renderType("minecraft:cutout_mipped").build());

    public static final TexturedModel.Provider CUBE_ALL_TRANSLUCENT = TexturedModel.CUBE
            .updateTemplate(template -> template.extend()
                    .renderType("minecraft:translucent").build());
}
