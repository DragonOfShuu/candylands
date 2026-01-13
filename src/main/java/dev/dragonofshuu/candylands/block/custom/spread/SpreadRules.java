package dev.dragonofshuu.candylands.block.custom.spread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadRules {
    protected Block sourceBlock = null;
    protected List<Block> targetBlocks = new ArrayList<Block>();
    protected List<BlockState> convertToBlocks = new ArrayList<BlockState>();
    protected Vec3i maxDistances = new Vec3i(1, 1, 1);
    protected List<SpreadCondition> conditions = new ArrayList<SpreadCondition>();
    protected Holder<Biome> biome = null;
    protected boolean isSmart = false;

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
        if (targetBlocks == null || targetBlocks.isEmpty())
            return this;
        this.targetBlocks.addAll(targetBlocks);
        return this;
    }

    public SpreadRules addConvertToBlock(BlockState candyGrassBlockState) {
        this.convertToBlocks.add(candyGrassBlockState);
        return this;
    }

    public SpreadRules addConvertToBlocks(Collection<BlockState> convertToBlocks) {
        if (convertToBlocks == null || convertToBlocks.isEmpty())
            return this;
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
        if (conditions == null || conditions.isEmpty())
            return this;
        this.conditions.addAll(conditions);
        return this;
    }

    public SpreadRules setBiome(Holder<Biome> biome) {
        this.biome = biome;
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

    public SpreadRules isSmart(boolean isSmart) {
        this.isSmart = isSmart;
        return this;
    }

    public SpreadRules smart() {
        this.isSmart = true;
        return this;
    }

    public SpreadRules dumb() {
        this.isSmart = false;
        return this;
    }

    public SpreadRules extend() {
        return new SpreadRules()
                .setSourceBlock(this.sourceBlock)
                .addTargetBlocks(this.targetBlocks)
                .addConvertToBlocks(this.convertToBlocks)
                .setMaxDistances(this.maxDistances)
                .addConditions(this.conditions)
                .setBiome(this.biome)
                .isSmart(this.isSmart);
    }
}
