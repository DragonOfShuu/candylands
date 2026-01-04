package dev.dragonofshuu.candylands.datagen.models;

import net.minecraft.client.data.models.model.TexturedModel;

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
