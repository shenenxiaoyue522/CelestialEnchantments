package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class DeathBlade extends AttackEnch {

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.deathBladePercentDamage.get();
	}

	public DeathBlade() {
		super(Rarity.RARE, EnchData.specialHigh(5, A300));
	}

	@Override
	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {
		float add = target.getMaxHealth() * lv * (float) atk();
		data.addHurtModifier(DamageModifier.addExtra(add, damageId()));
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.percSmall(lv, atk(), alt));
	}

}
