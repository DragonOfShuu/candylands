package dev.dragonofshuu.candylands.block.custom;

import java.util.List;

import dev.dragonofshuu.candylands.common.IDescriptableItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class DescriptableBlockItem extends BlockItem implements IDescriptableItem {
	private final String SHIFT_TRANSLATION_KEY;
	private final boolean REQUIRES_SHIFT;

	/**
	 * Allows you to describe items
	 * 
	 * @param properties    Item properties
	 * @param textKey       The translation key for when the user holds shift
	 * @param requiresShift Determines if we need shift to show the content
	 */
	public DescriptableBlockItem(Block block, Properties properties, String textKey, boolean requiresShift) {
		super(block, properties);
		SHIFT_TRANSLATION_KEY = textKey;
		REQUIRES_SHIFT = requiresShift;
	}

	/**
	 * Press shift for more details
	 * 
	 * @param properties Item properties
	 * @param textKey    The translation key for when the user holds shift
	 */
	public DescriptableBlockItem(Block block, Properties properties, String textKey) {
		super(block, properties);
		SHIFT_TRANSLATION_KEY = textKey;
		REQUIRES_SHIFT = true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
			TooltipFlag tooltipFlag) {
		if (!REQUIRES_SHIFT) {
			tooltipComponents.add(Component.translatable(SHIFT_TRANSLATION_KEY));
			return;
		}

		tooltipComponents.add(Component.translatable(
				Minecraft.getInstance().hasShiftDown() ? SHIFT_TRANSLATION_KEY : "tooltip.candylands.press_shift"));
	}

	@Override
	public String getShiftTranslationKey() {
		return SHIFT_TRANSLATION_KEY;
	}

	@Override
	public boolean getRequiresShift() {
		return REQUIRES_SHIFT;
	}
}
