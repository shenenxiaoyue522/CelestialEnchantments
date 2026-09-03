package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class Revenge extends AttackEnch {

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.revengeDamage.get();
	}

	public Revenge() {
		super(Rarity.UNCOMMON, EnchData.treasure(3, A30));
	}

	@Override
	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {
		if (user.getLastAttacker() != null && user.getLastAttacker().is(target)) {
			data.addHurtModifier(DamageModifier.multBase(lv * (float) atk(), damageId()));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, atk(), alt));
	}

}
