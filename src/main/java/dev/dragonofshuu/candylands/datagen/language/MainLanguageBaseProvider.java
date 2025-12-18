package dev.dragonofshuu.candylands.datagen.language;

import java.util.function.Supplier;

import dev.dragonofshuu.candylands.CandyLands;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class MainLanguageBaseProvider extends LanguageProvider {
    public MainLanguageBaseProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    public void addTooltipBlock(Supplier<? extends Block> block, String tooltip) {
        addTooltip(block.get(), tooltip);
    }

    public void addTooltipBlock(Supplier<? extends Block> block, String name, String tooltip) {
        addBlock(block, name);
        addTooltip(block.get().asItem(), tooltip);
    }

    public void addTooltipItem(Supplier<? extends Item> item, String tooltip) {
        addTooltip(item.get(), tooltip);
    }

    public void addTooltipItem(Supplier<? extends Item> item, String name, String tooltip) {
        addItem(item, name);
        addTooltip(item.get(), tooltip);
    }

    public void addTooltip(Block block, String tooltip) {
        add(block.getDescriptionId() + ".tooltip", tooltip);
    }

    public void addTooltip(Item item, String tooltip) {
        add(item.getDescriptionId() + ".tooltip", tooltip);
    }

    public void addSound(Holder<SoundEvent> sound, String subtitle) {
        add("sound." + sound.getRegisteredName().replace(':', '.'), subtitle);
    }

    public void addBiome(ResourceKey<Biome> biome, String name) {
        add("biome." + CandyLands.MODID + "." + biome.location().getPath(), name);
    }
}
