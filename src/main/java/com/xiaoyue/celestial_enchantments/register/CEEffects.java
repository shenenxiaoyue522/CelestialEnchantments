
package com.xiaoyue.celestial_enchantments.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import com.xiaoyue.celestial_enchantments.content.effects.Corruption;
import com.xiaoyue.celestial_enchantments.content.effects.Destructed;
import com.xiaoyue.celestial_enchantments.content.effects.Suppressed;
import dev.xkmc.l2core.init.reg.registrate.EffectEntry;
import net.minecraft.world.effect.MobEffect;

public class CEEffects {

	public static final EffectEntry<Corruption> CORRUPTION = genEffect("corruption", Corruption::new, "Reduce armor");
	public static final EffectEntry<Destructed> DESTRUCTED = genEffect("destructed", Destructed::new, "Remove invulnerable time after hit");
	public static final EffectEntry<Suppressed> SUPPRESSED = genEffect("suppressed", Suppressed::new, "Prevents healing");

	public CEEffects() {
	}

	private static <T extends MobEffect> EffectEntry<T> genEffect(String name, NonNullSupplier<T> sup, String desc) {
		RegistryEntry<MobEffect, T> entry = CelestialEnchantments.REGISTRATE.effect(name, sup, desc).lang(MobEffect::getDescriptionId).register();
		return new EffectEntry<>(entry);
	}

	public static void register() {
	}

}
