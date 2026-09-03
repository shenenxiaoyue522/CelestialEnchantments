package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.generic.ArmorEnch;
import com.xiaoyue.celestial_enchantments.content.generic.LivingTickEnch;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public class PureBody extends ArmorEnch implements LivingTickEnch {

	public PureBody() {
		super(Rarity.VERY_RARE, Type.LEGS, EnchData.bad(1, EFFECT));
	}

	@Override
	public void onLivingTick(EntityTickEvent.Post event, LivingEntity entity, int level) {
		if (!entity.getActiveEffects().isEmpty()) {
			entity.removeEffectsCuredBy(EffectCures.MILK);
		}
	}
}
