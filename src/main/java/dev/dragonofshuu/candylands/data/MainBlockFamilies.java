package dev.dragonofshuu.candylands.data;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import com.google.common.collect.Maps;

import dev.dragonofshuu.candylands.block.MainBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.world.level.block.Block;

public class MainBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    public static final String RECIPE_GROUP_PREFIX_STONE = "stone";
    public static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";
    public static final String RECIPE_UNLOCKED_BY_HAS_CANDY_ROCK = "has_candy_rock";

    public static final BlockFamily LICORICE_WOOD = familyBuilder(
            MainBlocks.LICORICE_PLANKS.get())
                    .slab(MainBlocks.LICORICE_SLAB.get())
                    .stairs(MainBlocks.LICORICE_STAIRS.get())
                    .fence(MainBlocks.LICORICE_FENCE.get())
                    .fenceGate(MainBlocks.LICORICE_FENCE_GATE.get())
                    .button(MainBlocks.LICORICE_BUTTON.get())
                    .pressurePlate(MainBlocks.LICORICE_PRESSURE_PLATE.get())
                    .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_PLANKS)
                    .recipeGroupPrefix(RECIPE_GROUP_PREFIX_WOODEN).getFamily();

    public static final BlockFamily CANDY_CANE_ROCK = familyBuilder(
            MainBlocks.CANDY_CANE_ROCK.get())
                    .slab(MainBlocks.CANDY_CANE_ROCK_SLAB.get())
                    .stairs(MainBlocks.CANDY_CANE_ROCK_STAIRS.get())
                    .wall(MainBlocks.CANDY_CANE_ROCK_WALL.get())
                    .fence(MainBlocks.CANDY_CANE_ROCK_FENCE.get())
                    .fenceGate(MainBlocks.CANDY_CANE_ROCK_FENCE_GATE.get())
                    .door(MainBlocks.CANDY_CANE_ROCK_DOOR.get())
                    .button(MainBlocks.CANDY_CANE_ROCK_BUTTON.get())
                    .pressurePlate(
                            MainBlocks.CANDY_CANE_ROCK_PRESSURE_PLATE.get())
                    .recipeUnlockedBy(RECIPE_UNLOCKED_BY_HAS_CANDY_ROCK)
                    .recipeGroupPrefix(RECIPE_GROUP_PREFIX_STONE).getFamily();

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(
                baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock,
                blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for "
                    + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<Block> getVariantFromBlockFamilies(Variant variant) {
        return getAllFamilies().map(family -> family.get(variant))
                .filter(v -> Objects.nonNull(v));
    }

    public static Stream<Block> getBaseBlocksFromBlockFamilies() {
        return getAllFamilies().map(family -> family.getBaseBlock())
                .filter(v -> Objects.nonNull(v));
    }

    public static Stream<Block> getVariantFromWoodBlockFamilies(
            Variant variant) {
        return getAllFamilies().filter(family -> isFamilyWooden(family))
                .map(family -> family.get(variant))
                .filter(v -> Objects.nonNull(v));
    }

    public static Stream<Block> getVariantFromStoneBlockFamilies(
            Variant variant) {
        return getAllFamilies().filter(family -> isFamilyStone(family))
                .map(family -> family.get(variant))
                .filter(v -> Objects.nonNull(v));
    }

    public static Stream<Block> getBaseBlocksFromWoodBlockFamilies() {
        return getAllFamilies().filter(family -> isFamilyWooden(family))
                .map(family -> family.getBaseBlock());
    }

    public static Stream<Block> getBaseBlocksFromStoneBlockFamilies() {
        return getAllFamilies().filter(family -> isFamilyStone(family))
                .map(family -> family.getBaseBlock());
    }

    public static Stream<BlockFamily> getWoodBlockFamilies() {
        return getAllFamilies().filter(family -> isFamilyWooden(family));
    }

    public static Stream<BlockFamily> getStoneBlockFamilies() {
        return getAllFamilies().filter(family -> isFamilyStone(family));
    }

    public static boolean isFamilyWooden(BlockFamily family) {
        return family.getRecipeGroupPrefix().orElse("")
                .equals(RECIPE_GROUP_PREFIX_WOODEN);
    }

    public static boolean isFamilyStone(BlockFamily family) {
        return family.getRecipeGroupPrefix().orElse("")
                .equals(RECIPE_GROUP_PREFIX_STONE);
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
