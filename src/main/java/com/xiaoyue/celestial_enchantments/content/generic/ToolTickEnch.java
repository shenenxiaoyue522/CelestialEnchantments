package com.xiaoyue.celestial_enchantments.content.generic;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

public interface ToolTickEnch {

	void onLivingTick(EntityTickEvent.Post event, ItemStack stack, int level);

}
