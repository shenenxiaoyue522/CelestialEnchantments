package com.xiaoyue.celestial_enchantments.content.generic;

import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import com.xiaoyue.celestial_enchantments.data.EnchGroup;
import com.xiaoyue.celestial_enchantments.utils.IEnchUtils;
import dev.xkmc.l2core.init.reg.ench.CustomDescEnchantment;
import dev.xkmc.l2core.init.reg.ench.EnchColor;
import dev.xkmc.l2core.init.reg.ench.EnchVal;
import dev.xkmc.l2core.init.reg.ench.LegacyEnchantment;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.ArrayList;
import java.util.List;

public abstract class CEBaseEnchantment extends LegacyEnchantment implements CustomDescEnchantment {

	public enum Rarity {
		COMMON(10), UNCOMMON(5), RARE(2), VERY_RARE(1);
		public final int weight;

		Rarity(int weight) {
			this.weight = weight;
		}
	}

	public enum Type {
		ALL(ItemTags.DURABILITY_ENCHANTABLE, EquipmentSlotGroup.ANY, EquipmentSlot.values()),
		WEAPON(ItemTags.WEAPON_ENCHANTABLE, EquipmentSlotGroup.MAINHAND, EquipmentSlot.MAINHAND),
		DIGGER(ItemTags.MINING_ENCHANTABLE, EquipmentSlotGroup.MAINHAND, EquipmentSlot.MAINHAND),
		TRIDENT(ItemTags.TRIDENT_ENCHANTABLE, EquipmentSlotGroup.MAINHAND, EquipmentSlot.MAINHAND),
		HOE(IEnchUtils.HOE, EquipmentSlotGroup.HAND, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND),
		AXE(IEnchUtils.AXE, EquipmentSlotGroup.HAND, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND),
		BOW(IEnchUtils.BOW_AND_CROSSBOW, EquipmentSlotGroup.HAND, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND),
		SHIELD(IEnchUtils.SHIELD, EquipmentSlotGroup.HAND, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND),
		ARMOR(ItemTags.ARMOR_ENCHANTABLE, EquipmentSlotGroup.ARMOR, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET),
		HEAD(ItemTags.HEAD_ARMOR_ENCHANTABLE, EquipmentSlotGroup.HEAD, EquipmentSlot.HEAD),
		CHEST(ItemTags.CHEST_ARMOR_ENCHANTABLE, EquipmentSlotGroup.CHEST, EquipmentSlot.CHEST),
		LEGS(ItemTags.LEG_ARMOR_ENCHANTABLE, EquipmentSlotGroup.LEGS, EquipmentSlot.LEGS),
		FEET(ItemTags.FOOT_ARMOR_ENCHANTABLE, EquipmentSlotGroup.FEET, EquipmentSlot.FEET),
		;

		public final TagKey<Item> category;
		public final EquipmentSlotGroup group;
		public final EquipmentSlot[] slots;

		Type(TagKey<Item> category, EquipmentSlotGroup group, EquipmentSlot... slots) {
			this.category = category;
			this.group = group;
			this.slots = slots;
		}
	}

	public static final EnchGroup CURSE = EnchGroup.simple(CELang.CURSE, ChatFormatting.RED);

	private static final List<CEBaseEnchantment> CACHE = new ArrayList<>();

	public static List<CEBaseEnchantment> getCache() {
		return CACHE;
	}

	public static ItemStack makeBook(Holder<Enchantment> ench, int level) {
		return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ench, level));
	}

	public final Rarity rarity;
	public final EnchData config;
	public final EquipmentSlot[] slots;
	public final EquipmentSlotGroup group;
	public final TagKey<Item> category;

	private String id;
	private EnchVal val;

	protected CEBaseEnchantment(Rarity rarity, Type type, EnchData config) {
		this.rarity = rarity;
		this.slots = type.slots;
		this.group = type.group;
		this.category = type.category;
		this.config = config;
		CACHE.add(this);
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setVal(EnchVal val) {
		this.val = val;
	}

	public Holder<Enchantment> getHolder() {
		return val.holder();
	}

	public boolean isEnabled() {
		return CEModConfig.COMMON.enabled(this);
	}

	public int getMaxLevel() {
		return config.level().maxLv();
	}

	public int getMinCost(int lv) {
		return config.level().getMinCost(lv);
	}

	public int getMaxCost(int lv) {
		return config.level().getMaxCost(lv);
	}

	public boolean isCurse() {
		return config.level().curse();
	}

	public MutableComponent getName() {
		MutableComponent name = getHolder().value().description().copy();
		if (!isEnabled()) {
			return name.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.STRIKETHROUGH);
		} else if (isCurse()) {
			return name.withStyle(ChatFormatting.RED);
		} else if (config.bad()) {
			return name.withStyle(ChatFormatting.LIGHT_PURPLE);
		}
		return name.withStyle(ChatFormatting.AQUA);
	}

	public Component desc(int lv, String key, boolean alt) {
		return CELang.ench(key);
	}

	@Override
	public List<Component> descFull(int lv, String key, boolean alt, boolean isBook, EnchColor color) {
		if (!isEnabled()) {
			return List.of();
		}
		if (alt) {
			return List.of(desc(lv, key, true));
		}
		MutableComponent base = CELang.CELE.get().withStyle(ChatFormatting.DARK_AQUA).append(CommonComponents.SPACE);
		base = base.append(config.level().type().lang.get()).append(CommonComponents.SPACE);
		if (!isCurse()) {
			base = base.append(config.group().lang().get().withStyle(config.group().color())).append(CommonComponents.SPACE);
			if (config.bad()) {
				base = base.append(CELang.DOUBLE.get().withStyle(ChatFormatting.LIGHT_PURPLE)).append(CommonComponents.SPACE);
			}
		}
		if (!isBook) return List.of(base.append(desc(lv, key, false)));
		return List.of(base, desc(lv, key, false));
	}

	@Override
	public Component title(ItemStack stack, Component name, boolean alt, boolean isBook, EnchColor color) {
		if (!isEnabled()) {
			return name.copy().withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.STRIKETHROUGH);
		} else if (isCurse()) {
			return name.copy().withStyle(ChatFormatting.RED);
		} else if (config.bad()) {
			return name.copy().withStyle(ChatFormatting.LIGHT_PURPLE);
		} else {
			return name.copy().withStyle(ChatFormatting.AQUA);
		}
	}

	protected boolean chance(LivingEntity e, double chance) {
		return e.getRandom().nextDouble() < chance;
	}

	protected ResourceLocation damageId() {
		return CelestialEnchantments.loc(getID());
	}

	/**
	 * Extra vanilla enchantment keys this enchantment is mutually exclusive with.
	 */
	public List<ResourceKey<Enchantment>> extraExclusions() {
		return List.of();
	}

}
