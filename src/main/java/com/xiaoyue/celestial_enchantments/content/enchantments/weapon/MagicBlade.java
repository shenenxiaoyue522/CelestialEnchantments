package com.xiaoyue.celestial_enchantments.content.enchantments.weapon;

import com.xiaoyue.celestial_core.data.CCDamageTypes;
import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2core.events.SchedulerHandler;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class MagicBlade extends AttackEnch {

	private static double atk() {
		return CEModConfig.SERVER.ench.weapon.magicBladeDamage.get();
	}

	public MagicBlade() {
		super(Rarity.RARE, EnchData.special(3, A300));
	}

	@Override
	public void onDamageTargetFinal(LivingEntity user, LivingEntity target, DamageData.DefenceMax data, int lv) {
		SchedulerHandler.schedule(() -> target.hurt(CCDamageTypes.magic(user), data.getDamageFinal() * (float) atk() * lv));
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, atk(), alt));
	}

}
