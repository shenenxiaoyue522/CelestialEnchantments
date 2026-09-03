package com.xiaoyue.celestial_enchantments.content.generic;

import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

public interface AttributeEnchantment {

	void addAttributes(int lv, ItemAttributeModifierEvent event);

}
