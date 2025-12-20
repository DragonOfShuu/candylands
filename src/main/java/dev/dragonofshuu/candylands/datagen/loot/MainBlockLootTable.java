package dev.dragonofshuu.candylands.datagen.loot;

import java.util.Set;

import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class MainBlockLootTable extends BlockLootSubProvider {

    public MainBlockLootTable(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, lookupProvider);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MainBlocks.BLOCKS.getEntries()
                .stream()
                .map(e -> (Block) e.value())
                .toList();
    }

    @Override
    protected void generate() {
        dropSelf(MainBlocks.CANDY_GRASS_BLOCK.get());
    }

    public void dropSlab(Block block) {
        add(block, newBlock -> createSlabItemTable(newBlock));
    }

    public void dropDoor(Block block) {
        add(block, newBlock -> createDoorTable(newBlock));
    }
}
