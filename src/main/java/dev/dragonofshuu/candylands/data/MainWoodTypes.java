package dev.dragonofshuu.candylands.data;

import java.util.Map;
import java.util.stream.Stream;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MainWoodTypes {
    private static final Map<String, WoodType> TYPES = new Object2ObjectArrayMap<>();

    public static final WoodType LICORICE = register(new WoodType("licorice", MainBlockSetTypes.LICORICE));

    public static WoodType register(WoodType woodType) {
        TYPES.put(woodType.name(), woodType);
        return woodType;
    }

    public static Stream<WoodType> values() {
        return TYPES.values().stream();
    }
}
