package com.xiaoyue.celestial_enchantments.content.enchantments.armor;

import com.xiaoyue.celestial_enchantments.content.effects.EnchEffectEntry;
import com.xiaoyue.celestial_enchantments.content.generic.DefenceEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class FrostThorns extends DefenceEnch {

	private static int dmg() {
		return CEModConfig.SERVER.ench.armor.frostThornDamage.get();
	}

	private static int dur() {
		return CEModConfig.SERVER.ench.armor.frostThornDuration.get();
	}

	private static final EnchEffectEntry EFF = EnchEffectEntry.all(() -> MobEffects.MOVEMENT_SLOWDOWN, FrostThorns::dur);

	public FrostThorns() {
		super(Rarity.RARE, Type.ARMOR, EnchData.treasure(5, REACTIVE));
	}

	@Override
	public List<ResourceKey<Enchantment>> extraExclusions() {
		return List.of(Enchantments.THORNS);
	}

	@Override
	public void onDamagedFinal(LivingEntity user, DamageData.DefenceMax data, int lv) {
		if (data.getAttacker() == null || !data.getSource().is(L2DamageTypes.DIRECT)) return;
		DamageSource source = new DamageSource(user.damageSources().freeze().typeHolder());
		data.getAttacker().addEffect(EFF.ins(lv), data.getAttacker());
		if (!data.getSource().is(DamageTypeTags.AVOIDS_GUARDIAN_THORNS))
			data.getAttacker().hurt(source, lv * dmg());
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.num(lv, dmg(), alt), EFF.comp(lv, alt));
	}

}
