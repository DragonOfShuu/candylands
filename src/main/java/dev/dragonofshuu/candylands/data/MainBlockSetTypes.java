package dev.dragonofshuu.candylands.data;

import java.util.Map;
import java.util.stream.Stream;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class MainBlockSetTypes {
    private static final Map<String, BlockSetType> TYPES = new Object2ObjectArrayMap<>();

    public static final BlockSetType LICORICE = register(
            new BlockSetType("licorice", true, true, true, BlockSetType.PressurePlateSensitivity.EVERYTHING,
                    SoundType.WOOD,
                    SoundEvents.WOODEN_DOOR_CLOSE,
                    SoundEvents.WOODEN_DOOR_OPEN,
                    SoundEvents.WOODEN_TRAPDOOR_CLOSE,
                    SoundEvents.WOODEN_TRAPDOOR_OPEN,
                    SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.WOODEN_BUTTON_CLICK_OFF,
                    SoundEvents.WOODEN_BUTTON_CLICK_ON));

    public static BlockSetType register(BlockSetType value) {
        TYPES.put(value.name(), value);
        return value;
    }

    public static Stream<BlockSetType> values() {
        return TYPES.values().stream();
    }
}
