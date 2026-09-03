package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class UnstableBlade extends AttackEnch {

	private static double chance() {
		return CEModConfig.SERVER.ench.weapon.unstableBladeChance.get();
	}

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.unstableBladeDamage.get();
	}

	public UnstableBlade() {
		super(Rarity.COMMON, EnchData.normal(3, A30));
	}

	@Override
	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {
		if (user.getRandom().nextDouble() < lv * chance()) {
			data.addHurtModifier(DamageModifier.multBase(lv * (float) atk(), damageId()));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, chance(), alt), CELang.perc(lv, atk(), alt));
	}
}
