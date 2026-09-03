package com.xiaoyue.celestial_enchantments.content.generic;

import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;

public class DefenceEnch extends ArmorEnch {

	protected DefenceEnch(Rarity rarity, Type type, EnchData data) {
		super(rarity, type, data);
	}

	public boolean onAttacked(LivingEntity user, DamageData.Attack data, int lv) {
		return !data.getSource().is(DamageTypeTags.BYPASSES_ENCHANTMENTS) &&
				onAttackedImpl(user, data, lv);
	}

	protected boolean onAttackedImpl(LivingEntity user, DamageData.Attack data, int lv) {
		return false;
	}

	public void onDamaged(LivingEntity user, DamageData.Defence data, int lv) {
		if (!data.getSource().is(DamageTypeTags.BYPASSES_ENCHANTMENTS)) {
			onDamagedImpl(user, data, lv);
		}
	}

	protected void onDamagedImpl(LivingEntity user, DamageData.Defence data, int lv) {

	}

	public void onDamagedFinal(LivingEntity user, DamageData.DefenceMax data, int lv) {

	}

}
