package com.xiaoyue.celestial_enchantments.data;

import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import com.xiaoyue.celestial_enchantments.register.CEEnchantments;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.xiaoyue.celestial_enchantments.CelestialEnchantments.MODID;

public class CEBookModelGen extends ItemModelProvider {
    public CEBookModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (String id : CEEnchantments.ALL_ENCH.keySet()) {
            Integer index = CEEnchantments.ALL_ENCH.get(id);
            basicItem(CelestialEnchantments.loc("book_" + index))
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", CelestialEnchantments.loc("item/book/book_" + index));
        }
    }
}
