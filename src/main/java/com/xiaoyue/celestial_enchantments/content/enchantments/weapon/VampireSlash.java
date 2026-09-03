package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class VampireSlash extends AttackEnch {

	private static double heal() {
		return CEModConfig.SERVER.ench.weapon.vampireSlashHealRate.get();
	}

	public VampireSlash() {
		super(Rarity.UNCOMMON, EnchData.treasure(5, INFLICT));
	}

	@Override
	public void onDamageTargetFinal(LivingEntity user, LivingEntity target, DamageData.DefenceMax data, int lv) {
		user.heal(data.getDamageFinal() * lv * (float) heal());
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, heal(), alt));
	}

}
