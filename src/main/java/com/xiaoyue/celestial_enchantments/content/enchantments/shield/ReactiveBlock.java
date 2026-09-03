package com.xiaoyue.celestial_enchantments.content.enchantments.shield;

import com.xiaoyue.celestial_enchantments.content.generic.ShieldEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;

public class ReactiveBlock extends ShieldEnch {

	private static int knockback() {
		return CEModConfig.SERVER.ench.shield.reactiveBlockKnockback.get();
	}

	public ReactiveBlock() {
		super(Rarity.RARE, EnchData.normal(3, SHIELD));
	}

	@Override
	public void onShieldBlock(LivingShieldBlockEvent event, LivingEntity attacker, LivingEntity entity, int level) {
		attacker.knockback(level * knockback(), entity.getX() - attacker.getX(), entity.getZ() - attacker.getZ());
	}

	@Override
	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key, CELang.num(lv, knockback(), alt));
	}

}
