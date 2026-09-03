package com.xiaoyue.celestial_enchantments.content.effects;

import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Corruption extends MobEffect {

	public Corruption() {
		super(MobEffectCategory.HARMFUL, 16750848);
		this.addAttributeModifier(Attributes.ARMOR, CelestialEnchantments.loc("soul_shatter"),
				-3.0, AttributeModifier.Operation.ADD_VALUE);
	}

}
