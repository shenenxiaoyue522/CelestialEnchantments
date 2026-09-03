package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.effects.EnchEffectEntry;
import com.xiaoyue.celestial_enchantments.content.generic.DefenceEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import com.xiaoyue.celestial_enchantments.register.CEEffects;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;

public class CorruptionBody extends DefenceEnch {

	private static int dur() {
		return CEModConfig.SERVER.ench.armor.corruptionBodyDuration.get();
	}

	private static final EnchEffectEntry EFF = EnchEffectEntry.amp(CEEffects.CORRUPTION::val, CorruptionBody::dur);

	public CorruptionBody() {
		super(Rarity.UNCOMMON, Type.LEGS, EnchData.normal(5, REACTIVE));
	}

	@Override
	public void onDamagedFinal(LivingEntity user, DamageData.DefenceMax data, int lv) {
		if (data.getAttacker() == null || !data.getSource().is(L2DamageTypes.DIRECT)) return;
		data.getAttacker().addEffect(EFF.ins(lv), data.getAttacker());
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, EFF.comp(lv, alt));
	}

}
