package com.xiaoyue.celestial_enchantments.content.enchantments.tool;

import com.xiaoyue.celestial_enchantments.content.generic.ToolEnch;
import com.xiaoyue.celestial_enchantments.data.CELang;
import com.xiaoyue.celestial_enchantments.data.CEModConfig;
import com.xiaoyue.celestial_enchantments.data.EnchData;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

public class ExperienceProspector extends ToolEnch {

    private static double chance() {
        return CEModConfig.SERVER.ench.tool.experienceProspectorChance.get();
    }

    private static int exp() {
        return CEModConfig.SERVER.ench.tool.experienceProspectorExp.get();
    }

    public ExperienceProspector() {
        super(Rarity.VERY_RARE, Type.DIGGER , EnchData.specialHigh(4, TOOL));
    }

    public static void onBlockBreak(Player player, BlockDropsEvent event, int lv) {
        if (player.getRandom().nextDouble() <= chance() * lv) {
            event.setDroppedExperience(event.getDroppedExperience() + exp() * lv);
        }
    }

    @Override
    public Component desc(int lv, String key, boolean alt) {
        return CELang.ench(key, CELang.perc(lv, chance(), alt), CELang.num(lv, exp(), alt));
    }
}
