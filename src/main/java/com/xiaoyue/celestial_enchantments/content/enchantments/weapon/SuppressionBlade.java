package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import com.xiaoyue.celestial_enchantments.register.CEEffects;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class SuppressionBlade extends AttackEnch {

	private static int duration() {
		return CEModConfig.SERVER.ench.weapon.suppressionBladeEffectDuration.get();
	}

	public SuppressionBlade() {
		super(Rarity.UNCOMMON, EnchData.special(3, INFLICT));
	}

	@Override
	public void onDamageTargetFinal(LivingEntity user, LivingEntity target, DamageData.DefenceMax data, int lv) {
		target.addEffect(new MobEffectInstance(CEEffects.SUPPRESSED.val(), duration() * 20 * lv, 0, false, false, false), target);
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.num(lv, duration(), alt));
	}

}
