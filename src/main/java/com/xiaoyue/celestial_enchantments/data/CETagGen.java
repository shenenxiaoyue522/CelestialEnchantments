package com.xiaoyue.celestial_enchantments.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.xiaoyue.celestial_artifacts.register.CAItems;
import com.xiaoyue.celestial_core.register.CCItems;
import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CETagGen {

	public static final TagKey<Item> BASIC_INGREDIENTS = TagKey.create(Registries.ITEM, CelestialEnchantments.loc("basic_ingredients"));
	public static final TagKey<Item> ADVANCED_INGREDIENTS = TagKey.create(Registries.ITEM, CelestialEnchantments.loc("advanced_ingredients"));
	public static final TagKey<Item> LEGENDARY_INGREDIENTS = TagKey.create(Registries.ITEM, CelestialEnchantments.loc("legendary_ingredients"));

	public static void onItemTagGen(RegistrateItemTagsProvider pvd) {
		pvd.addTag(BASIC_INGREDIENTS).add(CCItems.TREASURE_FRAGMENT.get(), CCItems.VOID_ESSENCE.get(),
						CCItems.FIRE_ESSENCE.get(), CCItems.OCEAN_ESSENCE.get(), CCItems.DEATH_ESSENCE.get())
				.addOptional(CAItems.THE_END_DUST.getId());
		pvd.addTag(ADVANCED_INGREDIENTS).add(CCItems.MIDNIGHT_FRAGMENT.get(), CCItems.MIDNIGHT_FRAGMENT.get(),
				CCItems.SHULKER_SCRAP.get(), CCItems.WARDEN_SCLERITE.get())
				.addOptional(CAItems.NEBULA_CUBE.getId());
		pvd.addTag(LEGENDARY_INGREDIENTS).add(CCItems.HEART_FRAGMENT.get(), CCItems.PURE_NETHER_STAR.get(),
				CCItems.SOARING_WINGS.get());
	}
}
