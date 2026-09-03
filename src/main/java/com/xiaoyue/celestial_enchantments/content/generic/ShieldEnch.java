package com.xiaoyue.celestial_enchantments.content.generic;

import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import com.xiaoyue.celestial_enchantments.data.EnchGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;

public class ShieldEnch extends CEBaseEnchantment {

	public static final EnchGroup SHIELD = EnchGroup.simple(CELang.SHIELD, ChatFormatting.DARK_AQUA);

	protected ShieldEnch(Rarity rarity, EnchData config) {
		super(rarity, Type.SHIELD, config);
	}

	public void onShieldBlock(LivingShieldBlockEvent event, LivingEntity attacker, LivingEntity entity, int level) {

	}

}
