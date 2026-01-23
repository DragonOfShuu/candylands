package dev.dragonofshuu.candylands.registries.spread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class SpreadFunction {
    protected SpreadRules defaultSpreadRules = SpreadRules.spreadRules();
    protected List<SpreadRules> spreaders = new ArrayList<SpreadRules>();

    public static SpreadFunction make() {
        return new SpreadFunction();
    }

    public SpreadFunction usingDefaultSpreadRule(SpreadRules spreadRules) {
        this.defaultSpreadRules = spreadRules;
        return this;
    }

    public SpreadFunction useSpreaders(Function<LockedSpreadRules, Collection<SpreadRules>> spreaders) {
        // Baking it immediately after setting so that
        // the programmer can reuse the `withDefaultSpreadrule`
        // function
        spreaders
                .apply(this.defaultSpreadRules.getLockedRules())
                .forEach(this.spreaders::add);
        return this;
    }

    public void tick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        Consumer<SpreadContext> noop = (context) -> {
        };
        this.tick(currentBlockState, level, blockPos, noop, noop, level.getRandom());
    }

    public void tick(BlockState currentBlockState, ServerLevel level, BlockPos blockPos,
            Consumer<SpreadContext> onCantSpread, Consumer<SpreadContext> onDidSpread, RandomSource random) {
        var spreadContext = new SpreadContext(currentBlockState, blockPos, level, random);
        SpreadReturnType spreadReturn = spreadIt(this.spreaders, spreadContext);
        if (!spreadReturn.canSpread()) {
            onCantSpread.accept(spreadContext);
        }
        if (spreadReturn.didSpread()) {
            onDidSpread.accept(spreadContext);
        }
    }

    protected SpreadReturnType spreadIt(List<SpreadRules> spreaders, SpreadContext context) {
        var didSpread = false;
        var cantSpreadCount = 0;
        for (var spreadRules : spreaders) {
            SpreadReturnType spreadReturn = SpreadFunctionRunner.applySpread(context, spreadRules);
            didSpread = didSpread || spreadReturn.didSpread();
            if (!spreadReturn.canSpread() && !spreadReturn.isNoOp()) {
                cantSpreadCount++;
            }
            if (didSpread && spreadRules.cancelOnSuccess) {
                return new SpreadReturnType(true, true, false);
            }
        }

        // didspread, canspread, isnoop
        return new SpreadReturnType(didSpread, cantSpreadCount != spreaders.size(), false);
    }
}
