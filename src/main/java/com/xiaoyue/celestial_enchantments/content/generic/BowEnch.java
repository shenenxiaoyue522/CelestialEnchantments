package com.xiaoyue.celestial_enchantments.content.generic;

import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import com.xiaoyue.celestial_enchantments.data.EnchGroup;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.enchantment.Enchantments;

public class BowEnch extends CEBaseEnchantment {

	public static final EnchGroup ARROW = EnchGroup.of(CELang.ARROW, ChatFormatting.DARK_AQUA, Enchantments.POWER);
	public static final EnchGroup ARROW_EFFECT = EnchGroup.of(CELang.ARROW_EFFECT, ChatFormatting.DARK_GREEN, Enchantments.FLAME);

	protected BowEnch(Rarity rarity, EnchData config) {
		super(rarity, Type.BOW, config);
	}

	public void hurtTarget(Arrow arrow, LivingEntity target, int lv, DamageData.Offence data) {
	}

	public void onDamageTargetFinal(Arrow arrow, LivingEntity target, int lv, DamageData.DefenceMax data) {
	}

}
