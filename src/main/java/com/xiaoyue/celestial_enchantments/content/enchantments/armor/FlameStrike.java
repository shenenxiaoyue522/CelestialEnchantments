package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.generic.DefenceEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class FlameStrike extends DefenceEnch {

  private static int fireTime() {
	  return CEModConfig.SERVER.ench.armor.flameStrikeDuration.get();
	}

	public FlameStrike() {
		super(Rarity.RARE, Type.ARMOR, EnchData.normal(5, REACTIVE));
	}

	@Override
	public List<ResourceKey<Enchantment>> extraExclusions() {
		return List.of(Enchantments.THORNS);
	}

	@Override
	public void onDamagedFinal(LivingEntity user, DamageData.DefenceMax data, int lv) {
		if (data.getAttacker() == null || !data.getSource().is(L2DamageTypes.DIRECT)) return;
		data.getAttacker().setRemainingFireTicks(lv * fireTime());
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.num(lv, fireTime(), alt));
	}


}
