package com.xiaoyue.celestial_enchantments;

import com.xiaoyue.celestial_enchantments.content.generic.CEBaseEnchantment;
import com.xiaoyue.celestial_enchantments.register.CEEnchantments;
import dev.xkmc.l2core.init.reg.ench.LegacyEnchantment;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = CelestialEnchantments.MODID, value = Dist.CLIENT)
public class CEnchantmentsClient {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> ItemProperties.register(Items.ENCHANTED_BOOK,
				ResourceLocation.fromNamespaceAndPath(CelestialEnchantments.MODID, "book"),
				(stack, clientLevel, entity, i) -> {
					int index = 0;
					int count = 0;
					for (var holder : EnchantmentHelper.getEnchantmentsForCrafting(stack).keySet()) {
						var ce = LegacyEnchantment.firstOf(holder, CEBaseEnchantment.class);
						if (ce != null) {
							index = CEEnchantments.ALL_ENCH.getOrDefault(ce.getID(), 0);
							count++;
						}
					}
					if (count >= 2) {
						return 222;
					}
					return index;
				}));
	}
}
