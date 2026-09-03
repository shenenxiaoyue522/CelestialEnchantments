package com.xiaoyue.celestial_enchantments.content.table;

import com.xiaoyue.celestial_enchantments.content.generic.CEBaseEnchantment;
import net.minecraft.util.random.WeightedEntry;

public class CelestialEnchIns extends WeightedEntry.IntrusiveBase {

	public final CEBaseEnchantment enchantment;

	public final int level;

	public CelestialEnchIns(CEBaseEnchantment pEnchantment, int pLevel, int averager) {
		super(pEnchantment.rarity.weight + averager);
		this.enchantment = pEnchantment;
		this.level = pLevel;
	}
}
