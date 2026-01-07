package dev.dragonofshuu.candylands.block.custom.spread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadFunctionBuilder {
    protected SpreadRules defaultSpreadRules = SpreadRules.spreadRules();
    protected List<SpreadRules> spreaders = new ArrayList<SpreadRules>();

    public static SpreadFunctionBuilder make() {
        return new SpreadFunctionBuilder();
    }

    public SpreadFunctionBuilder usingDefaultSpreadRule(SpreadRules spreadRules) {
        this.defaultSpreadRules = spreadRules;
        return this;
    }

    public SpreadFunctionBuilder useSpreaders(Function<LockedSpreadRules, Collection<SpreadRules>> spreaders) {
        // Baking it immediately after setting so that
        // the programmer can reuse the `withDefaultSpreadrule`
        // function
        spreaders
                .apply(this.defaultSpreadRules.getLockedRules())
                .forEach(this.spreaders::add);
        return this;
    }

    public void tick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        var spreadContext = new SpreadContext(currentBlockState, level, blockPos, random);
        spreadIt(this.spreaders, spreadContext);
    }

    protected void spreadIt(List<SpreadRules> spreaders, SpreadContext context) {

    }
}
