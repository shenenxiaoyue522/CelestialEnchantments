package com.xiaoyue.celestial_enchantments.content.table;

import com.google.common.collect.Lists;
import com.xiaoyue.celestial_enchantments.content.generic.CEBaseEnchantment;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchType;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CelestialEnchantmentHelper {

	private static double extraRollPerAffinity() {
		return CEModConfig.SERVER.table.extraRollPerAffinity.get();
	}

	private static double extraWeightPerAffinity() {
		return CEModConfig.SERVER.table.extraWeightPerAffinity.get();
	}

	private static double bookCurseChance() {
		return CEModConfig.SERVER.table.bookCurseChance.get();
	}

	private static double noCurseAffinity() {
		return CEModConfig.SERVER.table.noCurseAffinity.get();
	}

	private static double baseCurseChance() {
		return CEModConfig.SERVER.table.baseCurseChance.get();
	}

	private static double weightCurseReduction() {
		return CEModConfig.SERVER.table.weightCurseReduction.get();
	}

	private static double maxCurseChance() {
		return CEModConfig.SERVER.table.maxCurseChancePerRank.get();
	}

	private static int maxLevel(CEBaseEnchantment ce, int level) {
		int lv = ce.getMaxLevel();
		while (lv > 0 && level < ce.getMinCost(lv)) lv--;
		return lv;
	}

	public static List<CelestialEnchIns> selectEnchantment(
			RandomSource rand, ItemStack stack, int slot, int level,
			Set<CEBaseEnchantment> ban, int averager, int levelBonus
	) {
		List<CelestialEnchIns> list = Lists.newArrayList();
		int val = stack.getItem().getEnchantmentValue();
		if (val <= 0) return list;
		boolean isBook = stack.is(Items.BOOK);
		List<CelestialEnchIns> curse = new ArrayList<>();
		List<CelestialEnchIns> basic = new ArrayList<>();
		List<CelestialEnchIns> advance = new ArrayList<>();
		List<CelestialEnchIns> legendary = new ArrayList<>();

		level += val / 2 + Math.max(0, val - 10) / 2 + Math.max(0, val - 20) / 2;
		averager += (int) (val * extraWeightPerAffinity());

		for (CEBaseEnchantment ce : CEBaseEnchantment.getCache()) {
			if (ce.isEnabled() && (stack.is(ce.category) || isBook)) {
				var max = Math.min(ce.getMaxLevel(), maxLevel(ce, level) + levelBonus);
				if (max <= 0) continue;
				if (ban.contains(ce)) continue;
				var ins = new CelestialEnchIns(ce, max, averager);
				if (ce.config.level().type() == EnchType.CURSE) {
					curse.add(ins);
				}
				if (ce.config.level().type() == EnchType.NORMAL) {
					basic.add(ins);
				}
				if (ce.config.level().type() == EnchType.TREASURE) {
					advance.add(ins);
				}
				if (ce.config.level().type() == EnchType.LEGENDARY) {
					legendary.add(ins);
				}
			}
		}

		if (isBook) {
			if (rand.nextDouble() < bookCurseChance() - averager * weightCurseReduction()) {
				roll(0, curse, list, rand, true);
				if (!list.isEmpty()) return list;
			}
		}

		if (slot >= 2) {
			roll(val - 20, legendary, list, rand, isBook);
			if (list.isEmpty()) return list;
		}
		if (slot >= 1) {
			roll(val - 10, advance, list, rand, isBook);
			if (list.isEmpty()) return list;
		}
		if (slot >= 0) {
			roll(val, basic, list, rand, isBook);
			if (list.isEmpty()) return list;
		}
		double rankChance = Math.min(maxCurseChance(), (noCurseAffinity() - val) * baseCurseChance());
		double chance = (slot + 1) * rankChance - averager * weightCurseReduction();
		if (!isBook && rand.nextDouble() < chance) {
			roll(0, curse, list, rand, false);
		}
		return list;
	}

	private static void roll(int val, List<CelestialEnchIns> avail, List<CelestialEnchIns> list, RandomSource rand, boolean isBook) {
		if (val < 0) val = 0;
		double rolls = 1 + rand.nextDouble() * val * extraRollPerAffinity();
		int roll = (int) rolls;
		if (rand.nextDouble() < rolls - roll) roll++;
		for (int i = 0; i < roll; i++) {
			if (!list.isEmpty() && isBook) return;
			avail.removeIf(e -> CelestialEnchantmentHelper.incompatible(e, list));
			WeightedRandom.getRandomItem(rand, avail).ifPresent(list::add);
		}
	}

	private static boolean incompatible(CelestialEnchIns e, List<CelestialEnchIns> list) {
		for (var x : list) {
			if (!Enchantment.areCompatible(x.enchantment.getHolder(), e.enchantment.getHolder())) {
				return true;
			}
		}
		return false;
	}


}
