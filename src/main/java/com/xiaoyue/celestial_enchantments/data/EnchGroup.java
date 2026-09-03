package com.xiaoyue.celestial_enchantments.data;

import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public record EnchGroup(boolean multi, CELang lang, ChatFormatting color, TagKey<Enchantment> tag, List<ResourceKey<Enchantment>> excl) {

	public static EnchGroup simple(CELang lang, ChatFormatting color) {
		return new EnchGroup(false, lang, color, tag(lang), List.of());
	}

	public static EnchGroup multi(CELang lang, ChatFormatting color) {
		return new EnchGroup(true, lang, color, tag(lang), List.of());
	}

	public static EnchGroup of(CELang lang, ChatFormatting color, ResourceKey<Enchantment>... excl) {
		return new EnchGroup(false, lang, color, tag(lang), Arrays.asList(excl));
	}

	private static TagKey<Enchantment> tag(CELang lang) {
		return TagKey.create(Registries.ENCHANTMENT,
				CelestialEnchantments.loc("exclusive/" + lang.name().toLowerCase(Locale.ROOT)));
	}

}
