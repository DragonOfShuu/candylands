package dev.dragonofshuu.candylands.block.custom.spread;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

public class SpreadFunction {
    public static void applySpread(SpreadContext context, SpreadRules rules) {
        BlockState[] chosenBlocks;
        if (rules.isSmart) {
            chosenBlocks = spreadSmart(context, rules);
        } else {
            chosenBlocks = spreadDumb(context, rules);
        }
    }

    private static BlockState[] spreadDumb(SpreadContext context, SpreadRules rules) {
        BlockPos centerPos = context.blockPos;
        List<BlockState> potentialBlocks = new ArrayList<BlockState>();
        for (int i = 0; i < rules.maxAttempts; i++) {
            BlockPos targetPos = centerPos.offset(
                    context.random.nextInt(1 + rules.maxDistances.getX() * 2) - rules.maxDistances.getX(),
                    context.random.nextInt(1 + rules.maxDistances.getY() * 2) - rules.maxDistances.getY(),
                    context.random.nextInt(1 + rules.maxDistances.getZ() * 2) - rules.maxDistances.getZ());
            potentialBlocks.add(context.level.getBlockState(targetPos));
        }

        List<BlockState> potentialButConvertableBlocks = new ArrayList<BlockState>();
        for (BlockState blockState : potentialBlocks) {
            if (rules.targetBlocks.contains(blockState.getBlock())) {
                potentialButConvertableBlocks.add(blockState);
            }
        }

        List<BlockState> chosenBlocks = new ArrayList<BlockState>();
        for (int i = 0; i < Math.min(rules.maxConversions, potentialButConvertableBlocks.size()); i++) {
            int index = context.random.nextInt(potentialButConvertableBlocks.size());
            chosenBlocks.add(potentialButConvertableBlocks.remove(index));
        }

        return chosenBlocks.toArray(new BlockState[0]);
    }

    private static BlockState[] spreadSmart(SpreadContext context, SpreadRules rules) {
        BlockPos centerPos = context.blockPos;
        BoundingBox box = BoundingBox.fromCorners(
                centerPos.offset(-rules.maxDistances.getX(), -rules.maxDistances.getY(), -rules.maxDistances.getZ()),
                centerPos.offset(rules.maxDistances.getX(), rules.maxDistances.getY(), rules.maxDistances.getZ()));
        List<BlockState> potentialBlocks = new ArrayList<BlockState>();

        box.forAllCorners((blockPos) -> {
            BlockState blockState = context.level.getBlockState(blockPos);
            if (rules.targetBlocks.contains(blockState.getBlock())) {
                potentialBlocks.add(blockState);
            }
        });

        List<BlockState> chosenBlocks = new ArrayList<BlockState>();
        for (int i = 0; i < Math.min(rules.maxAttempts, potentialBlocks.size()); i++) {
            int index = context.random.nextInt(potentialBlocks.size());
            chosenBlocks.add(potentialBlocks.remove(index));
        }

        return chosenBlocks.toArray(new BlockState[0]);
    }
}
