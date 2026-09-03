package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class MisfortuneBlade extends AttackEnch {

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.misfortuneBladeDamage.get();
	}

	public MisfortuneBlade() {
		super(Rarity.RARE, EnchData.normal(3, A30));
	}

	@Override
	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {
		if (target.hasEffect(MobEffects.POISON)) {
			data.addHurtModifier(DamageModifier.multBase(lv * (float) atk(), damageId()));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.eff(MobEffects.POISON), CELang.perc(lv, atk(), alt));
	}
}
