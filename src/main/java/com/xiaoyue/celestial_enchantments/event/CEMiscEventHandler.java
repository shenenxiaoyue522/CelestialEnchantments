package com.xiaoyue.celestial_enchantments.event;

import com.xiaoyue.celestial_enchantments.content.enchantments.armor.DimensionExplorer;
import com.xiaoyue.celestial_enchantments.content.enchantments.armor.PotionAffinity;
import com.xiaoyue.celestial_enchantments.content.enchantments.tool.AccelerateGrowth;
import com.xiaoyue.celestial_enchantments.content.enchantments.tool.ExperienceProspector;
import com.xiaoyue.celestial_enchantments.register.CEEffects;
import com.xiaoyue.celestial_enchantments.register.CEEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

import static com.xiaoyue.celestial_enchantments.CelestialEnchantments.MODID;

@EventBusSubscriber(modid = MODID)
public class CEMiscEventHandler {

	@SubscribeEvent
	public static void onLevelChange(PlayerEvent.PlayerChangedDimensionEvent event) {
		Player player = event.getEntity();
		int lv = EnchantmentHelper.getEnchantmentLevel(CEEnchantments.DIMENSION_EXPLORER.holder(), player);
		if (lv > 0) DimensionExplorer.onLevelChange(player, lv);
	}

	@SubscribeEvent
	public static void onAddedEffect(MobEffectEvent.Added event) {
		LivingEntity entity = event.getEntity();
		int lv = EnchantmentHelper.getEnchantmentLevel(CEEnchantments.POTION_AFFINITY.holder(), entity);
		if (lv > 0) PotionAffinity.onAddedEffect(entity, lv, event.getEffectInstance());
	}

	@SubscribeEvent
	public static void onRightBlockEvent(PlayerInteractEvent.RightClickBlock event) {
		Player player = event.getEntity();
		int lv = EnchantmentHelper.getEnchantmentLevel(CEEnchantments.ACCELERATE_GROWTH.holder(), player);
		if (lv > 0) AccelerateGrowth.onRightBlockEvent(event, lv);
	}

	@SubscribeEvent
	public static void onLivingHeal(LivingHealEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity.hasEffect(CEEffects.SUPPRESSED.val())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onBlockBreak(BlockDropsEvent event) {
		if (!(event.getBreaker() instanceof Player player)) return;
		int lv = EnchantmentHelper.getEnchantmentLevel(CEEnchantments.EXPERIENCE_PROSPECTOR.holder(), player);
		if (lv > 0) ExperienceProspector.onBlockBreak(player, event, lv);
	}
}
