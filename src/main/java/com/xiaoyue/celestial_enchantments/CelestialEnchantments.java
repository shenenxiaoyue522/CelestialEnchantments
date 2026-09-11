package com.xiaoyue.celestial_enchantments;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.providers.ProviderType;
import com.xiaoyue.celestial_enchantments.content.generic.CEBaseEnchantment;
import com.xiaoyue.celestial_enchantments.data.*;
import com.xiaoyue.celestial_enchantments.event.CEAttackListener;
import com.xiaoyue.celestial_enchantments.register.CEEffects;
import com.xiaoyue.celestial_enchantments.register.CEEnchantments;
import com.xiaoyue.celestial_enchantments.register.CEItems;
import com.xiaoyue.celestial_invoker.content.common.registrar.RegistrateExtra;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2core.init.reg.registrate.SimpleEntry;
import dev.xkmc.l2damagetracker.contents.attack.AttackEventHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(CelestialEnchantments.MODID)
@EventBusSubscriber(modid = CelestialEnchantments.MODID)
public class CelestialEnchantments {

	public static final String MODID = "celestial_enchantments";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
	public static final RegistrateExtra<L2Registrate> EXTRA = new RegistrateExtra<>(REGISTRATE);

	public static final SimpleEntry<CreativeModeTab> TAB_ENCHMIN = REGISTRATE.buildModCreativeTab(
			"min_enchantment", "Celestial Enchantments - Min Level",
			b -> b.icon(Items.BOOK::getDefaultInstance)
					.displayItems((params, output) -> {
						var enchants = params.holders().lookupOrThrow(Registries.ENCHANTMENT);
						CEBaseEnchantment.getCache().forEach(e ->
								output.accept(CEBaseEnchantment.makeBook(enchants.getOrThrow(e.getKey()), 1)));
					}));

	public static final SimpleEntry<CreativeModeTab> TAB_ENCHMAX = REGISTRATE.buildModCreativeTab(
			"max_enchantment", "Celestial Enchantments - Max Level",
			b -> b.icon(() -> EnchantedBookItem.createForEnchantment(new EnchantmentInstance(
							CEEnchantments.ACCELERATE_GROWTH.holder(), 1)))
					.withTabsBefore(TAB_ENCHMIN.key())
					.displayItems((params, output) -> {
						var enchants = params.holders().lookupOrThrow(Registries.ENCHANTMENT);
						CEBaseEnchantment.getCache().forEach(e ->
								output.accept(CEBaseEnchantment.makeBook(enchants.getOrThrow(e.getKey()), e.getMaxLevel())));
					}));

	public CelestialEnchantments() {
		CEEnchantments.register();
		CEEffects.register();
		CEItems.register();
		CEModConfig.init();
		AttackEventHandler.register(3560, new CEAttackListener());
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gatherData(GatherDataEvent event) {
		var gen = event.getGenerator();
		gen.addProvider(event.includeClient(), new CEBookModelGen(gen.getPackOutput(), event.getExistingFileHelper()));
		REGISTRATE.addDataGenerator(ProviderType.LANG, CELang::genLang);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CETagGen::onItemTagGen);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, CERecipeGen::onRecipeGen);
	}

	public static ResourceLocation loc(String id) {
		return ResourceLocation.fromNamespaceAndPath(MODID, id);
	}

}
