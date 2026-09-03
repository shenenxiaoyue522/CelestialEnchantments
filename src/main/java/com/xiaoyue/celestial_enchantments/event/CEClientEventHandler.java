package com.xiaoyue.celestial_enchantments.event;

import com.xiaoyue.celestial_enchantments.content.generic.CEBaseEnchantment;
import com.xiaoyue.celestial_enchantments.data.CELang;
import dev.xkmc.l2core.init.reg.ench.LegacyEnchantment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static com.xiaoyue.celestial_enchantments.CelestialEnchantments.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class CEClientEventHandler {

	@SubscribeEvent
	public static void renderTooltip(RenderTooltipEvent.Color event) {
		ItemStack itemStack = event.getItemStack();
		if (itemStack.getItem() instanceof EnchantedBookItem) {
			for (var holder : EnchantmentHelper.getEnchantmentsForCrafting(itemStack).keySet()) {
				if (LegacyEnchantment.firstOf(holder, CEBaseEnchantment.class) != null) {
					event.setBorderStart(0xFF87CEFA);
					event.setBorderEnd(0xFF87CEFA);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void modifyItemTooltip(ItemTooltipEvent event) {
		if (Screen.hasAltDown()) return;
		ItemStack stack = event.getItemStack();
		if (!stack.isEnchanted() && !stack.is(Items.ENCHANTED_BOOK)) return;
		for (var holder : EnchantmentHelper.getEnchantmentsForCrafting(stack).keySet()) {
			if (LegacyEnchantment.firstOf(holder, CEBaseEnchantment.class) != null) {
				event.getToolTip().add(CELang.alt());
				return;
			}
		}
	}
}
