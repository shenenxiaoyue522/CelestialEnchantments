package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.effects.EnchEffectEntry;
import com.xiaoyue.celestial_enchantments.content.generic.ArmorEnch;
import com.xiaoyue.celestial_enchantments.content.generic.LivingHealEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;

public class OriginOfLife extends ArmorEnch implements LivingHealEnch {

	private static double chance() {
		return CEModConfig.SERVER.ench.armor.originOfLifeChance.get();
	}

	private static int dur() {
		return CEModConfig.SERVER.ench.armor.originOfLifeDuration.get();
	}

	private static final EnchEffectEntry MOVE = EnchEffectEntry.amp(() -> MobEffects.MOVEMENT_SPEED, OriginOfLife::dur);
	private static final EnchEffectEntry RES = EnchEffectEntry.amp(() -> MobEffects.DAMAGE_RESISTANCE, OriginOfLife::dur);

	public OriginOfLife() {
		super(Rarity.RARE, Type.CHEST, EnchData.normal(3, EFFECT));
	}

	@Override
	public void onLivingHeal(LivingHealEvent event, LivingEntity entity, int level) {
		if (chance(entity, chance() * level)) {
			if (chance(entity, 0.5)) {
				entity.addEffect(MOVE.ins(level), entity);
			} else {
				entity.addEffect(RES.ins(level), entity);
			}
		}
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.perc(lv, chance(), alt), MOVE.comp(lv, alt), RES.comp(lv, alt));
	}

}
