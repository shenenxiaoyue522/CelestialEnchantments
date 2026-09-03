package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class LightArmorKiller extends AttackEnch {

	private static int armor() {
		return CEModConfig.SERVER.ench.weapon.lightArmorKillerArmor.get();
	}

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.lightArmorKillerDamage.get();
	}

	public LightArmorKiller() {
		super(Rarity.UNCOMMON, EnchData.treasure(3, A75));
	}

	@Override
	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {
		if (target.getArmorValue() <= armor()) {
			data.addHurtModifier(DamageModifier.multBase(lv * (float) atk(), damageId()));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, atk(), alt), CELang.num(armor()));
	}

}
