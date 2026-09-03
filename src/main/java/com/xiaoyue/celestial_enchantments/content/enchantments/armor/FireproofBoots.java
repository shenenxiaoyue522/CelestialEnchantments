package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.generic.DefenceEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class FireproofBoots extends DefenceEnch {

  private static double damageMult() {
		return CEModConfig.SERVER.ench.armor.fireproofBootsReduction.get();
	}

	public FireproofBoots() {
		super(Rarity.COMMON, Type.FEET, EnchData.normal(1, EFFECT));
	}

	@Override
	public void onDamagedImpl(LivingEntity user, DamageData.Defence data, int lv) {
		if (data.getSource().is(DamageTypeTags.IS_FIRE)) {
			data.addDealtModifier(DamageModifier.multTotal(1 - (float) damageMult(), damageId()));
		}
	}

	@Override
	public boolean onAttackedImpl(LivingEntity user, DamageData.Attack data, int lv) {
		return data.getSource().is(DamageTypes.HOT_FLOOR);
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(damageMult()));
	}
}
