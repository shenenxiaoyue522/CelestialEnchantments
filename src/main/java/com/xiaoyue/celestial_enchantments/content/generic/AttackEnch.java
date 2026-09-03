package com.xiaoyue.celestial_enchantments.content.generic;

import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

public class AttackEnch extends WeaponEnch {

	protected AttackEnch(Rarity rarity, EnchData data) {
		super(rarity, data);
	}

	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {

	}

	public void onDamageTargetFinal(LivingEntity user, LivingEntity target, DamageData.DefenceMax data, int lv) {

	}

	public void onKillEntity(LivingDeathEvent event, LivingEntity user, LivingEntity target, int lv) {
	}

}
