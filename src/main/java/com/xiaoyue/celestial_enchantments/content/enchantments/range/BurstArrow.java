package com.xiaoyue.celestial_enchantments.content.enchantments.range;

import com.xiaoyue.celestial_enchantments.content.generic.BowEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2core.events.SchedulerHandler;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;

public class BurstArrow extends BowEnch {

  private static double chance() {
		return CEModConfig.SERVER.ench.ranged.burstArrowChance.get();
	}

  private static double strength() {
		return CEModConfig.SERVER.ench.ranged.burstArrowStrength.get();
	}

	public BurstArrow() {
		super(Rarity.VERY_RARE, EnchData.treasure(3, ARROW_EFFECT));
	}

	@Override
	public void hurtTarget(Arrow arrow, LivingEntity target, int lv, DamageData.Offence data) {
		if (chance(target, chance() * lv)) {
			SchedulerHandler.schedule(() -> target.level().explode(arrow, arrow.getX(), arrow.getY(), arrow.getZ(), (float) strength(),
					Level.ExplosionInteraction.NONE));
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, chance(), alt));
	}

}
