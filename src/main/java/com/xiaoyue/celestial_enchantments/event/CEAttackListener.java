package com.xiaoyue.celestial_enchantments.event;

import com.xiaoyue.celestial_enchantments.content.generic.AttackEnch;
import com.xiaoyue.celestial_enchantments.content.generic.BowEnch;
import com.xiaoyue.celestial_enchantments.content.generic.DefenceEnch;
import com.xiaoyue.celestial_enchantments.content.generic.TridentEnch;
import com.xiaoyue.celestial_enchantments.utils.IEnchUtils;
import dev.xkmc.l2core.init.reg.ench.LegacyEnchantment;
import dev.xkmc.l2damagetracker.contents.attack.AttackListener;
import dev.xkmc.l2damagetracker.contents.attack.DamageData;
import dev.xkmc.l2damagetracker.init.data.L2DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class CEAttackListener implements AttackListener {

	@Override
	public boolean onAttack(DamageData.Attack data) {
		var target = data.getTarget();
		for (var ent : IEnchUtils.getArmorEnch(target).entrySet()) {
			if (ent.getKey() instanceof DefenceEnch def) {
				if (def.onAttacked(target, data, ent.getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onHurt(DamageData.Offence data) {
		var user = data.getAttacker();
		var target = data.getTarget();
		if (data.getSource().getDirectEntity() instanceof Arrow arrow) {
			ItemStack bow = CEGeneralEventHandler.getBowInfo(arrow);
			if (bow.isEnchanted()) {
				for (var ent : EnchantmentHelper.getEnchantmentsForCrafting(bow).entrySet()) {
					var attr = LegacyEnchantment.firstOf(ent.getKey(), BowEnch.class);
					if (attr != null) {
						attr.hurtTarget(arrow, target, Math.min(attr.getMaxLevel(), ent.getIntValue()), data);
					}
				}
			}
		} else if (data.getSource().getDirectEntity() instanceof ThrownTrident trident) {
			ItemStack item = trident.getWeaponItem();
			if (item.isEnchanted()) {
				for (var ent : EnchantmentHelper.getEnchantmentsForCrafting(item).entrySet()) {
					var attr = LegacyEnchantment.firstOf(ent.getKey(), TridentEnch.class);
					if (attr != null) {
						attr.hurtTarget(trident, target, Math.min(attr.getMaxLevel(), ent.getIntValue()), data);
					}
				}
			}
		} else if (user != null) {
			for (var ent : IEnchUtils.getEnch(user, EquipmentSlot.MAINHAND).entrySet()) {
				if (data.getSource().is(L2DamageTypes.DIRECT)) {
					if (ent.getKey() instanceof AttackEnch atk) {
						atk.onHurtTarget(user, target, data, ent.getValue());
					}
					if (ent.getKey() instanceof TridentEnch atk) {
						atk.hurtTarget(null, target, ent.getValue(), data);
					}
				}
			}
		}
	}

	@Override
	public void onDamage(DamageData.Defence data) {
		var target = data.getTarget();
		for (var ent : IEnchUtils.getArmorEnch(target).entrySet()) {
			if (ent.getKey() instanceof DefenceEnch def) {
				def.onDamaged(target, data, ent.getValue());
			}
		}
	}

	@Override
	public void onDamageFinalized(DamageData.DefenceMax data) {
		var user = data.getAttacker();
		var target = data.getTarget();
		if (data.getSource().getDirectEntity() instanceof Arrow arrow) {
			ItemStack bow = CEGeneralEventHandler.getBowInfo(arrow);
			if (bow.isEnchanted()) {
				for (var ent : EnchantmentHelper.getEnchantmentsForCrafting(bow).entrySet()) {
					var attr = LegacyEnchantment.firstOf(ent.getKey(), BowEnch.class);
					if (attr != null) {
						attr.onDamageTargetFinal(arrow, target, Math.min(attr.getMaxLevel(), ent.getIntValue()), data);
					}
				}
			}
		} else if (user != null) {
			for (var ent : IEnchUtils.getEnch(user, EquipmentSlot.MAINHAND).entrySet()) {
				if (ent.getKey() instanceof AttackEnch atk && data.getSource().is(L2DamageTypes.DIRECT)) {
					atk.onDamageTargetFinal(user, target, data, ent.getValue());
				}
			}
		}
		for (var ent : IEnchUtils.getArmorEnch(target).entrySet()) {
			if (ent.getKey() instanceof DefenceEnch def) {
				def.onDamagedFinal(target, data, ent.getValue());
			}
		}
	}

}
