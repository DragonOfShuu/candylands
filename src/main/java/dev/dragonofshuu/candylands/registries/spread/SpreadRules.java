package dev.dragonofshuu.candylands.registries.spread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadRules {
    // /* The blocks that can be targeted for spreading */
    // protected List<Block> targetBlocks = new ArrayList<Block>();
    // /* The blocks to which the target blocks will be converted */
    // protected List<BlockState> convertToBlocks = new ArrayList<BlockState>();
    protected HashMap<BlockState, SpreadConverter> conversionMap = new HashMap<BlockState, SpreadConverter>();
    protected HashMap<Block, SpreadConverter> blockConversionMap = new HashMap<Block, SpreadConverter>();
    /* The maximum distances in each direction for spreading */
    protected Vec3i maxDistances = new Vec3i(1, 1, 1);
    /*
     * The maximum number of attempts to spread (when using the dumb method)
     * 
     * To be more specific, this is the number of random positions that will be
     * sampled
     * within the defined maxDistances to find target blocks for conversion.
     * 
     * For example, grass blocks sample 4 positions around them to spread to.
     */
    protected int maxAttempts = 10;
    /* The maximum number of conversions that can occur during spreading */
    protected int maxConversions = 1;
    /* The minimum number of conversions that can occur during spreading */
    protected int minConversions = 1;
    /* The conditions that must be met for spreading to occur */
    protected List<SpreadCondition> conditions = new ArrayList<SpreadCondition>();
    /* The biome that is spread around */
    protected ResourceKey<Biome> biome = null;
    /* Whether to use smart spreading or dumb spreading */
    protected boolean isSmart = false;
    /* Cancel spreading if a spreader has already succeeded */
    protected boolean cancelOnSuccess = false;

    public static SpreadRules spreadRules() {
        return new SpreadRules();
    }

    public SpreadRules addConversion(BlockState from, BlockState to) {
        this.conversionMap.put(from, SpreadConverter.of(to));
        return this;
    }

    public SpreadRules addBlockConversion(Block from, BlockState to) {
        this.blockConversionMap.put(from, SpreadConverter.of(to));
        return this;
    }

    public SpreadRules addConversions(HashMap<BlockState, SpreadConverter> conversions) {
        if (conversions == null || conversions.isEmpty())
            return this;
        for (var entry : conversions.entrySet()) {
            this.conversionMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public SpreadRules addBlockConversions(HashMap<Block, SpreadConverter> conversions) {
        if (conversions == null || conversions.isEmpty())
            return this;
        for (var entry : conversions.entrySet()) {
            this.blockConversionMap.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public SpreadRules addConversions(Collection<BlockState> fromStates, Collection<BlockState> toStates) {
        if (fromStates == null || toStates == null)
            return this;
        int size = Math.min(fromStates.size(), toStates.size());
        if (size == 0)
            return this;
        List<BlockState> fromList = new ArrayList<BlockState>(fromStates);
        List<BlockState> toList = new ArrayList<BlockState>(toStates);
        for (int i = 0; i < size; i++) {
            this.conversionMap.put(fromList.get(i), SpreadConverter.of(toList.get(i)));
        }
        return this;
    }

    public SpreadRules addBlockConversions(Collection<Block> fromBlocks, Collection<BlockState> toStates) {
        if (fromBlocks == null || toStates == null)
            return this;
        int size = Math.min(fromBlocks.size(), toStates.size());
        if (size == 0)
            return this;
        List<Block> fromList = new ArrayList<Block>(fromBlocks);
        List<BlockState> toList = new ArrayList<BlockState>(toStates);
        for (int i = 0; i < size; i++) {
            this.blockConversionMap.put(fromList.get(i), SpreadConverter.of(toList.get(i)));
        }
        return this;
    }

    public SpreadRules addConversions(Collection<BlockState> states, BlockState toState) {
        if (states == null || states.isEmpty())
            return this;
        for (BlockState fromState : states) {
            this.conversionMap.put(fromState, SpreadConverter.of(toState));
        }
        return this;
    }

    public SpreadRules addBlockConversions(Collection<Block> blocks, BlockState toState) {
        if (blocks == null || blocks.isEmpty())
            return this;
        for (Block fromBlock : blocks) {
            this.blockConversionMap.put(fromBlock, SpreadConverter.of(toState));
        }
        return this;
    }

    public SpreadRules addConversions(BlockState fromState, Collection<BlockState> toStates) {
        if (toStates == null || toStates.isEmpty())
            return this;
        for (BlockState toState : toStates) {
            this.conversionMap.put(fromState, SpreadConverter.of(toState));
        }
        return this;
    }

    public SpreadRules addBlockConversions(Block fromBlock, Collection<BlockState> toStates) {
        if (toStates == null || toStates.isEmpty())
            return this;
        for (BlockState toState : toStates) {
            this.blockConversionMap.put(fromBlock, SpreadConverter.of(toState));
        }
        return this;
    }

    public SpreadRules addConversion(BlockState fromState, SpreadConverter toConverter) {
        this.conversionMap.put(fromState, toConverter);
        return this;
    }

    public SpreadRules addBlockConversion(Block fromBlock, SpreadConverter toConverter) {
        this.blockConversionMap.put(fromBlock, toConverter);
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

    public SpreadRules setBiome(ResourceKey<Biome> biome) {
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

    public SpreadRules setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

    public SpreadRules setMinMaxConversions(int minConversions, int maxConversions) {
        this.minConversions = minConversions;
        this.maxConversions = maxConversions;
        return this;
    }

    public SpreadRules cancelOnSuccess() {
        this.cancelOnSuccess = true;
        return this;
    }

    public SpreadRules dontCancelOnSuccess() {
        this.cancelOnSuccess = false;
        return this;
    }

    @SuppressWarnings("unchecked")
    public SpreadRules extend() {
        return new SpreadRules()
                // .addConversions((HashMap<BlockState, SpreadConverter>)
                // this.conversionMap.clone())
                // .addBlockConversions((HashMap<Block, SpreadConverter>)
                // this.blockConversionMap.clone())
                .addConversions(this.conversionMap)
                .addBlockConversions(this.blockConversionMap)
                .setMaxDistances(this.maxDistances)
                .addConditions(this.conditions)
                .setBiome(this.biome)
                .isSmart(this.isSmart)
                .setMaxAttempts(this.maxAttempts)
                .setMinMaxConversions(this.minConversions, this.maxConversions);
    }
}
