package dev.dragonofshuu.candylands.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TintedParticularParticleLeavesBlock extends LeavesBlock {
    /*
     * The purpose of the codec is basically
     * "how to serialize and deserialize this block's data".
     * It allows us to recreate the block with the same properties when loading from
     * disk or from network packets.
     */
    public static final MapCodec<TintedParticularParticleLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance")
                            .forGetter(old_instance -> old_instance.leafParticleChance),
                    ExtraCodecs.intRange(0, 16777215).fieldOf("leaf_color")
                            .forGetter(old_instance -> old_instance.leafColor),
                    propertiesCodec())
                    .apply(instance, TintedParticularParticleLeavesBlock::new));

    private int leafColor;

    public TintedParticularParticleLeavesBlock(float spawnChance, int color, BlockBehaviour.Properties properties) {
        super(spawnChance, properties);
        this.leafColor = color;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource random) {
        ColorParticleOption colorparticleoption = ColorParticleOption.create(ParticleTypes.TINTED_LEAVES,
                this.leafColor);
        ParticleUtils.spawnParticleBelow(level, blockPos, random, colorparticleoption);
    }

    @Override
    public MapCodec<? extends TintedParticularParticleLeavesBlock> codec() {
        return CODEC;
    }
}
