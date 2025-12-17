package dev.dragonofshuu.candylands.common;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public interface IDescriptableItem {
	default public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
		if (!getRequiresShift()) {
			tooltipComponents.add(Component.translatable(getShiftTranslationKey()));
			return;
		}

		tooltipComponents.add(Component.translatable(
				Minecraft.getInstance().hasShiftDown() ? getShiftTranslationKey() : "tooltip.candylands.press_shift"));
	}

	default public boolean getRequiresShift() {
		return true;
	}

	public String getShiftTranslationKey();
}
