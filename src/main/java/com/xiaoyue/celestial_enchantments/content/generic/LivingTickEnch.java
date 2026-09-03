package com.xiaoyue.celestial_enchantments.content.generic;

import dev.xkmc.l2core.base.effects.EffectUtil;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public interface LivingTickEnch {

	void onLivingTick(EntityTickEvent.Post event, LivingEntity entity, int level);

	default void selfEffect(LivingEntity entity, Holder<MobEffect> eff, int lv) {
		EffectUtil.refreshEffect(entity, new MobEffectInstance(eff, 40, lv, true, true), entity);
	}

}
