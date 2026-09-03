package com.xiaoyue.celestial_enchantments.content.effects;

import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import com.xiaoyue.celestial_enchantments.data.CELang;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface EnchEffectEntry {

	static MobEffectInstance create(Holder<MobEffect> effect, int dur, int amp) {
		boolean ambient = false, visible = false;
		boolean showIcon = effect.unwrapKey()
				.map(k -> !k.location().getNamespace().equals(CelestialEnchantments.MODID))
				.orElse(true);
		return new MobEffectInstance(effect, dur, amp, ambient, visible, showIcon);
	}

	MobEffectInstance ins(int lv);

	MutableComponent comp(int lv, boolean alt);

	static EnchEffectEntry all(Supplier<Holder<MobEffect>> eff, IntSupplier dur) {
		return new All(eff, dur);
	}

	static EnchEffectEntry dur(Supplier<Holder<MobEffect>> eff, IntSupplier dur, IntSupplier level) {
		return new Dur(eff, dur, level);
	}

	static EnchEffectEntry amp(Supplier<Holder<MobEffect>> eff, IntSupplier dur) {
		return new Amp(eff, dur, 1);
	}

	static EnchEffectEntry amp(Supplier<Holder<MobEffect>> eff, IntSupplier dur, int factor) {
		return new Amp(eff, dur, factor);
	}

	record All(Supplier<Holder<MobEffect>> eff, IntSupplier dur) implements EnchEffectEntry {

		@Override
		public MobEffectInstance ins(int lv) {
			return create(eff.get(), dur.getAsInt() * 20 * lv, lv - 1);
		}

		@Override
		public MutableComponent comp(int lv, boolean alt) {
			return !alt ? CELang.eff(ins(lv)) : CELang.EFF_ALL.get(
					CELang.eff(ins(lv), false, false),
					CELang.num(lv, dur.getAsInt(), true),
					CELang.num(lv, 1, true));
		}

	}

	record Dur(Supplier<Holder<MobEffect>> eff, IntSupplier dur, IntSupplier level) implements EnchEffectEntry {

		@Override
		public MobEffectInstance ins(int lv) {
			return create(eff.get(), dur.getAsInt() * 20 * lv, level.getAsInt());
		}

		@Override
		public MutableComponent comp(int lv, boolean alt) {
			return !alt ? CELang.eff(ins(lv)) : CELang.EFF_DUR.get(
					CELang.eff(ins(lv), true, false),
					CELang.num(lv, dur.getAsInt(), true));
		}

	}

	record Amp(Supplier<Holder<MobEffect>> eff, IntSupplier dur, int factor) implements EnchEffectEntry {

		@Override
		public MobEffectInstance ins(int lv) {
			return create(eff.get(), dur.getAsInt() * 20, lv * factor - 1);
		}

		@Override
		public MutableComponent comp(int lv, boolean alt) {
			return !alt ? CELang.eff(ins(lv)) : CELang.EFF_AMP.get(
					CELang.eff(ins(lv), false, true),
					CELang.num(lv, factor, true));
		}

	}

}
