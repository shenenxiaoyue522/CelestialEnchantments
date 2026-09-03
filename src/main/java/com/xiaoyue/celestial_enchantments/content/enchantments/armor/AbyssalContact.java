package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.generic.DefenceEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;

public class AbyssalContact extends DefenceEnch {

	private static double def() {
		return CEModConfig.SERVER.ench.armor.abyssalContactReduction.get();
	}

	private static double amp() {
		return CEModConfig.SERVER.ench.armor.abyssalContactAmplification.get();
	}

	public AbyssalContact() {
		super(Rarity.VERY_RARE, Type.ARMOR, EnchData.bad(4, PROTECT));
	}

	@Override
	public void onDamaged(LivingEntity user, DamageData.Defence data, int lv) {
		if (data.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY))
			return;
		if (data.getSource().is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
			data.addDealtModifier(DamageModifier.multTotal(Math.max(0, 1 - lv * (float) def()), damageId()));
		} else {
			data.addDealtModifier(DamageModifier.multTotal(1 + lv * (float) amp(), damageId()));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, def(), alt), CELang.perc(lv, amp(), alt));
	}

}
