package dev.dragonofshuu.candylands.feature.custom;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.levelgen.feature.BlockBlobFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class AdjustedBlobFeature extends BlockBlobFeature {
    public AdjustedBlobFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

}
