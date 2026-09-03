package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.contents.attack.DamageModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class WordsOfWisdom extends AttackEnch {

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.wordsOfWisdomDamage.get();
	}

	private static int maxLv() {
		return CEModConfig.SERVER.ench.weapon.wordsOfWisdomMaxLevel.get();
	}

	public WordsOfWisdom() {
		super(Rarity.RARE, EnchData.treasure(3, A300));
	}

	@Override
	public void onHurtTarget(LivingEntity user, LivingEntity target, DamageData.Offence data, int lv) {
		if (user instanceof Player player) {
			float i = Math.min(player.experienceLevel, maxLv()) * (float) atk();
			data.addHurtModifier(DamageModifier.multBase(i * lv, damageId()));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.percSmall(lv, atk(), alt), CELang.perc(lv, atk() * maxLv(), alt));
	}
}
