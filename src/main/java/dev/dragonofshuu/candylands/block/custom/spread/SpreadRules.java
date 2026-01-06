package dev.dragonofshuu.candylands.block.custom.spread;

import java.util.Collection;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadRules {
    protected Block sourceBlock = null;
    protected List<Block> targetBlocks = List.of();
    protected List<BlockState> convertToBlocks = List.of();
    protected Vec3i maxDistances = new Vec3i(1, 1, 1);
    protected List<SpreadCondition> conditions = List.of();
    protected Holder<Biome> biome = null;
    protected SpreadChance getSpreadChanceFunction = (context) -> 0.0d;

    public static SpreadRules spreadRules() {
        return new SpreadRules();
    }

    public SpreadRules setSourceBlock(Block sourceBlock) {
        this.sourceBlock = sourceBlock;
        return this;
    }

    public SpreadRules addTargetBlock(Block targetBlock) {
        this.targetBlocks.add(targetBlock);
        return this;
    }

    public SpreadRules addTargetBlocks(Collection<Block> targetBlocks) {
        this.targetBlocks.addAll(targetBlocks);
        return this;
    }

    public SpreadRules addConvertToBlock(BlockState candyGrassBlockState) {
        this.convertToBlocks.add(candyGrassBlockState);
        return this;
    }

    public SpreadRules addConvertToBlocks(Collection<BlockState> convertToBlocks) {
        this.convertToBlocks.addAll(convertToBlocks);
        return this;
    }

    public SpreadRules setMaxDistances(Vec3i maxDistances) {
        this.maxDistances = maxDistances;
        return this;
    }

    public SpreadRules addCondition(SpreadCondition condition) {
        this.conditions.add(condition);
        return this;
    }

    public SpreadRules addConditions(Collection<SpreadCondition> conditions) {
        this.conditions.addAll(conditions);
        return this;
    }

    public SpreadRules setBiome(Holder<Biome> biome) {
        this.biome = biome;
        return this;
    }

    public SpreadRules setGetSpreadChanceFunction(SpreadChance spreadChance) {
        this.getSpreadChanceFunction = spreadChance;
        return this;
    }

    public LockedSpreadRules getLockedRules() {
        return new LockedSpreadRules() {
            @Override
            public SpreadRules extend() {
                return SpreadRules.this.extend();
            }
        };
    }

    public SpreadRules extend() {
        return new SpreadRules()
                .setSourceBlock(this.sourceBlock)
                .addTargetBlocks(this.targetBlocks)
                .addConvertToBlocks(this.convertToBlocks)
                .setMaxDistances(this.maxDistances)
                .addConditions(this.conditions)
                .setBiome(this.biome);
    }
}
