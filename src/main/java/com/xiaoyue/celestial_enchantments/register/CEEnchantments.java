package com.xiaoyue.celestial_enchantments.register;

import com.xiaoyue.celestial_enchantments.CelestialEnchantments;
import com.xiaoyue.celestial_enchantments.content.enchantments.armor.*;
import com.xiaoyue.celestial_enchantments.content.enchantments.curse.*;
import com.xiaoyue.celestial_enchantments.content.enchantments.range.*;
import com.xiaoyue.celestial_enchantments.content.enchantments.shield.ConstraintsShield;
import com.xiaoyue.celestial_enchantments.content.enchantments.shield.HolyShield;
import com.xiaoyue.celestial_enchantments.content.enchantments.shield.ReactiveBlock;
import com.xiaoyue.celestial_enchantments.content.enchantments.shield.ScorchingShield;
import com.xiaoyue.celestial_enchantments.content.enchantments.tool.*;
import com.xiaoyue.celestial_enchantments.content.enchantments.trident.ExplosiveHalberd;
import com.xiaoyue.celestial_enchantments.content.enchantments.trident.SharpHalberdTip;
import com.xiaoyue.celestial_enchantments.content.enchantments.weapon.*;
import com.xiaoyue.celestial_enchantments.content.generic.CEBaseEnchantment;
import dev.xkmc.l2core.init.reg.ench.EnchColor;
import dev.xkmc.l2core.init.reg.ench.EnchReg;
import dev.xkmc.l2core.init.reg.ench.EnchVal;
import dev.xkmc.l2core.init.reg.simple.Reg;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.EnchantmentTags;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class CEEnchantments {

	public static final EnchReg REG = EnchReg.of(new Reg(CelestialEnchantments.MODID), CelestialEnchantments.REGISTRATE);

	public static int index = 1;
	public static final LinkedHashMap<String, Integer> ALL_ENCH = new LinkedHashMap<>();

	private static final Set<String> SMALL = Set.of("in", "of", "the", "a", "and");

	private static String parse(String id) {
		return StringUtils.capitalize(Arrays.stream(id.split("_"))
				.map(e -> SMALL.contains(e) ? e : StringUtils.capitalize(e))
				.collect(Collectors.joining(" ")));
	}

	// weapons
	public static final EnchVal.Legacy<ApoptosisBlade> WITHERING_BLADE = reg("withering_blade", ApoptosisBlade::new, "Inflict %s"); // effect
	public static final EnchVal.Legacy<UpwardPick> UPWARD_PICK = reg("upward_pick", UpwardPick::new, "Inflict %s"); // effect
	public static final EnchVal.Legacy<VampireSlash> VAMPIRE_SLASH = reg("vampire_slash", VampireSlash::new, "On attack, heals %s of the damage dealt");// effect

	public static final EnchVal.Legacy<UnstableBlade> UNSTABLE_BLADE = reg("unstable_blade", UnstableBlade::new, "%s chance to increase damage by %s"); // A30, 22.5%
	public static final EnchVal.Legacy<FierceSlash> FIERCE_SLASH = reg("fierce_slash", FierceSlash::new, "Melee damage against the same target is increased by %s"); // A30, 24%
	public static final EnchVal.Legacy<Revenge> REVENGE = reg("revenge", Revenge::new, "Damage to targets that recently attacked the user is increased by %s"); // A30, 24%
	public static final EnchVal.Legacy<HiddenBlade> HIDDEN_BLADE = reg("hidden_blade", HiddenBlade::new, "When under %s, increase damage by %s"); // A30, 30%
	public static final EnchVal.Legacy<MisfortuneBlade> MISFORTUNE_BLADE = reg("misfortune_blade", MisfortuneBlade::new, "If the target is under %s, increase damage by %s"); // A30, 36%
	public static final EnchVal.Legacy<KnightSlash> KNIGHT_SLASH = reg("knight_slash", KnightSlash::new, "While mounting something, increase damage by %s"); // A30, 45%

	public static final EnchVal.Legacy<DeathBlow> FATAL_BLOW = reg("fatal_blow", DeathBlow::new, "+%s crit damage"); // A75, 75%
	public static final EnchVal.Legacy<LightArmorKiller> LIGHT_ARMOR_KILLER = reg("light_armor_killer", LightArmorKiller::new, "Increase %s damage to targets with less than %s armor points"); // A75, 75%
	public static final EnchVal.Legacy<QuickStepPuncture> QUICK_STEP_PUNCTURE = reg("quick_step_puncture", QuickStepPuncture::new, "While sprinting, increase damage by %s and knock back the target");// A75, 80%
	public static final EnchVal.Legacy<ClusterAwareness> CLUSTER_AWARENESS = reg("cluster_awareness", ClusterAwareness::new, "For every entity of the same type around the user, increase damage by %s");// A75, 9% per ally

	public static final EnchVal.Legacy<QuenchedBlade> QUENCHED_BLADE = reg("quenched_blade", QuenchedBlade::new, "When attacking a burning target, extinguish the target's flame and increase damage by %s for every remaining seconds of fire, up to %s"); //A300, 144%
	public static final EnchVal.Legacy<WordsOfWisdom> WORDS_OF_WISDOM = reg("words_of_wisdom", WordsOfWisdom::new, "Increase damage by %s for every exp level, up to %s");// A300, 180%

	public static final EnchVal.Legacy<DestructionCrack> DESTRUCTION_CRACK = reg("destruction_crack", DestructionCrack::new, "Target will not be able to have invulnerable frames for %s seconds");// Effect
	public static final EnchVal.Legacy<SuppressionBlade> SUPPRESSION_BLADE = reg("suppression_blade", SuppressionBlade::new, "Target will not be able to heal for %s seconds");// Effect
	public static final EnchVal.Legacy<DeathBlade> DEATH_BLADE = reg("death_blade", DeathBlade::new, "Increase damage by %s of the target's maximum health");//A300, up to 4% of target max
	public static final EnchVal.Legacy<MagicBlade> MAGIC_BLADE = reg("magic_blade", MagicBlade::new, "When you hit a target, deal additional magic damage equal to %s of the original damage"); // A300, 100%
	public static final EnchVal.Legacy<VoidChain> VOID_CHAIN = reg("void_chain", VoidChain::new, "When you hit a target, deal %s of the damage as abyss damage to surrounding monsters");// A300,
	public static final EnchVal.Legacy<ShadowTouch> SHADOW_TOUCH = reg("shadow_touch", ShadowTouch::new, "When attacking in places with brightness less than %s, deal an additional %s point of abyss damage to the target"); // A300
	public static final EnchVal.Legacy<TheHandOfThief> THE_HAND_OF_THIEF = reg("hand_of_thief", TheHandOfThief::new, "When you kill a target, it will drop all its equipment"); // Effect

	// all armor
	public static final EnchVal.Legacy<VoidProtection> VOID_PROTECTION = reg("void_protection", VoidProtection::new, "Reduce void damage by %s");// A
	public static final EnchVal.Legacy<AbyssalContact> ABYSSAL_CONTACT = reg("abyssal_contact", AbyssalContact::new, "Reduce abyssal damage by %s, but increase other damage by %s");// A
	public static final EnchVal.Legacy<SolidArmor> SOLID_ARMOR = reg("solid_armor", SolidArmor::new, "When the wearer's health is less than half, the damage received is reduced by %s");//A
	public static final EnchVal.Legacy<FlameStrike> HEARTH_SUPPORT = reg("flame_strike", FlameStrike::new, "Burn attacker for %s seconds when attacked");//B
	public static final EnchVal.Legacy<DestroyResonance> DESTROY_RESONANCE = reg("destroy_resonance", DestroyResonance::new, "After being attacked, deals %s of received damage to surrounding targets within %s blocks");//B
	public static final EnchVal.Legacy<FrostThorns> FROST_THORNS = reg("frost_thorns", FrostThorns::new, "Deals %s damage to the attacker when hurt, and inflict %s");//B
	public static final EnchVal.Legacy<TraumaAbsorption> TRAUMA_ABSORPTION = reg("trauma_absorption", TraumaAbsorption::new, "Restore %s of lost health when attacked");//B
	// head
	public static final EnchVal.Legacy<EyeInShadow> EYE_IN_SHADOW = reg("eyes_in_the_shadows", EyeInShadow::new, "Gain %2$s in places where brightness is less than %1$s");//C
	public static final EnchVal.Legacy<HaveNiceDream> HAVE_NICE_DREAM = reg("have_a_nice_dream", HaveNiceDream::new, "After waking up from bed, obtain %s and %s");//C
	public static final EnchVal.Legacy<CorruptScholar> CORRUPT_SCHOLAR = reg("corrupt_scholar", CorruptScholar::new, "+%s Exp pickup, but when you picks up exp, there is a %s chance to gain harmful effects");//D
	public static final EnchVal.Legacy<KnowledgeScholar> KNOWLEDGE_SCHOLAR = reg("knowledge_scholar", KnowledgeScholar::new, "+%s Exp pickup");//D
	public static final EnchVal.Legacy<MoonBlessing> MOON_BLESSING = reg("moon_blessing", MoonBlessing::new, "At night, gain %s");//C
	public static final EnchVal.Legacy<SunBlessing> SUN_BLESSING = reg("sun_blessing", SunBlessing::new, "In daytime, gain %s");//C
	public static final EnchVal.Legacy<RainAndDewGrace> RAIN_AND_DEW_GRACE = reg("rain_and_dew_grace", RainAndDewGrace::new, "Gain %s when wet");//C
	// chest
	public static final EnchVal.Legacy<GiftOfThunderGod> GIFT_OF_THUNDER_GOD = reg("gift_of_thunder_god", GiftOfThunderGod::new, "When struck by lightning, recover %s of lost life, then gain %s and %s");//C
	public static final EnchVal.Legacy<TheSourceOfSin> THE_SOURCE_OF_SIN = reg("the_source_of_sin", TheSourceOfSin::new, "When attacked, there is a %s chance that attacker will be targeted by surrounding mobs within %s blocks");//B TODO
	public static final EnchVal.Legacy<EchoEffect> ECHO_EFFECT = reg("echo_effect", EchoEffect::new, "When receiving a deadly attack, block the damage and restore %s of maximum health. Cooldown: %s seconds");//A
	public static final EnchVal.Legacy<OriginOfLife> ORIGIN_OF_LIFE = reg("origin_of_life", OriginOfLife::new, "When healed, there is a %s chance to gain %s or %s");//C
	public static final EnchVal.Legacy<LifeShield> LIFE_SHIELD = reg("life_shield", LifeShield::new, "When healed, gain absorption equal to %s of amount healed, up to %s of max health");//C
	public static final EnchVal.Legacy<TurtleAssimilation> TURTLE_ASSIMILATION = reg("turtle_assimilation", TurtleAssimilation::new, "Gain %s and %s when sneaking");//C
	public static final EnchVal.Legacy<CelestialShelter> CELESTIAL_SHELTER = reg("celestial_shelter", CelestialShelter::new, "When damaged, a single attack can only deal up to %s of the wearer's maximum health");//A
	// legs
	public static final EnchVal.Legacy<PotionAffinity> POTION_AFFINITY = reg("potion_affinity", PotionAffinity::new, "The duration of an obtained potion effect will be increased by %s");//C
	public static final EnchVal.Legacy<PureBody> PURE_BODY = reg("pure_body", PureBody::new, "Continuously clears potion effects from you");//C
	public static final EnchVal.Legacy<DolphinAssimilation> DOLPHIN_ASSIMILATION = reg("dolphin_assimilation", DolphinAssimilation::new, "Gain %s when wet");//C
	public static final EnchVal.Legacy<LeopardAssimilation> LEOPARD_ASSIMILATION = reg("leopard_assimilation", LeopardAssimilation::new, "Gain %s and %s when sprinting");//C
	public static final EnchVal.Legacy<CorruptionBody> CORRUPTION_BODY = reg("corruption_body", CorruptionBody::new, "When attacked, inflict attacker with %s");//B
	public static final EnchVal.Legacy<HolyPrayer> HOLY_PRAYER = reg("holy_prayer", HolyPrayer::new, "When you are attacked while sneaking, gain %s");//B
	// feet
	public static final EnchVal.Legacy<DimensionExplorer> DIMENSION_EXPLORER = reg("dimension_explorer", DimensionExplorer::new, "When you switch dimensions, gain %s, %s, and %s");//C
	public static final EnchVal.Legacy<FireproofBoots> FIREPROOF_BOOTS = reg("fireproof_boots", FireproofBoots::new, "Gain immunity to hot floor damage and reduce fire damage taken by %s");//A
	public static final EnchVal.Legacy<FleetOfFoot> FLEET_OF_FOOT = reg("fleet_of_foot", FleetOfFoot::new, "+%s Movement Speed");//C
	public static final EnchVal.Legacy<RabbitAssimilation> RABBIT_ASSIMILATION = reg("rabbit_assimilation", RabbitAssimilation::new, "Gain %s when sneaking");//C
	// death
	public static final EnchVal.Legacy<PartingWish> PARTING_WISH = reg("parting_wish", PartingWish::new, "Upon death, surrounding players within %s blocks heal %s of their max health");
	public static final EnchVal.Legacy<DeathHatred> DEATH_HATRED = reg("death_hatred", DeathHatred::new, "Upon death, deals %s magic damage to surrounding creatures within %s blocks");
	public static final EnchVal.Legacy<DeathPact> DEATH_PACT = reg("death_pact", DeathPact::new, "When nearby players within %s blocks die with [%s], there is a %s chance to gain %s, %s, %s, and %s. Otherwise, you will die together");

	// bow
	public static final EnchVal.Legacy<ArrowOfTraction> ARROW_OF_TRACTION = reg("arrow_of_traction", ArrowOfTraction::new, "After hitting target, drag all entities within a radius of %s grid with the target as the center to the target");
	public static final EnchVal.Legacy<ArrowStorm> ARROW_STORM = reg("arrow_storm", ArrowStorm::new, "Dealing %s of original damage as magical damage to enemies around the target upon hit");
	public static final EnchVal.Legacy<BurstArrow> BURST_ARROW = reg("explosive_arrow", BurstArrow::new, "%s chance explode when hitting target, but does not break blocks");
	public static final EnchVal.Legacy<MorningStar> MORNING_STAR = reg("lightning_arrow", MorningStar::new, "After arrow hits a target, %s chance to strike the target with lightning");
	public static final EnchVal.Legacy<SharpArrow> SHARP_ARROW = reg("sharp_arrow", SharpArrow::new, "+%s Arrow Damage");
	public static final EnchVal.Legacy<DivineProjection> DIVINE_PROJECTION = reg("divine_projection", DivineProjection::new, "The further the target is from the attacker, the higher damage output is");

	// shield
	public static final EnchVal.Legacy<ConstraintsShield> CONSTRAINTS_SHIELD = reg("constraints_shield", ConstraintsShield::new, "After blocking %s damage, next blocking will reflect %s damage as magic damage");
	public static final EnchVal.Legacy<HolyShield> HOLY_SHIELD = reg("holy_shield", HolyShield::new, "Gain %s after shielding attacks");
	public static final EnchVal.Legacy<ReactiveBlock> REACTIVE_BLOCK = reg("reactive_block", ReactiveBlock::new, "After blocking an attack, knock back attacker with strength of %s");
	public static final EnchVal.Legacy<ScorchingShield> SCORCHING_SHIELD = reg("scorching_shield", ScorchingShield::new, "Using a shield to block an attack will cause the attacker to burn for %s seconds");

	// trident
	public static final EnchVal.Legacy<SharpHalberdTip> SHARP_HALBERD_TIP = reg("sharp_halberd_tip", SharpHalberdTip::new, "+%s melee and throwing damage");
	public static final EnchVal.Legacy<ExplosiveHalberd> EXPLOSIVE_HALBERD = reg("explosive_halberd", ExplosiveHalberd::new, "The thrown trident has a %s chance to explode");

	// tool
	public static final EnchVal.Legacy<PhotoDynamic> PHOTO_DYNAMIC = reg("photo_dynamic", PhotoDynamic::new, "In places where brightness level is above %s, gain +%s mining speed");
	public static final EnchVal.Legacy<ExperienceProspector> EXPERIENCE_PROSPECTOR = reg("experience_prospector", ExperienceProspector::new, "When digging blocks, there is a %s chance to drop %s experience additionally");
	public static final EnchVal.Legacy<MoonPower> MOON_POWER = reg("moon_power", MoonPower::new, "Increases the digging speed according to the moon's phase, up to %s on the full moon");
	// axe
	public static final EnchVal.Legacy<LeafCutting> LEAF_CUTTING = reg("leaf_cutting", LeafCutting::new, "This tool is capable of instantly destroying leaves");
	// hoe
	public static final EnchVal.Legacy<AccelerateGrowth> ACCELERATE_GROWTH = reg("accelerate_growth", AccelerateGrowth::new, "Right click to fertilize crops");

	// generic
	public static final EnchVal.Legacy<BornInShadow> BORN_IN_SHADOW = reg("born_in_shadow", BornInShadow::new, "Recover %2$s durability every %3$s seconds in places where brightness level is less than %1$s");
	public static final EnchVal.Legacy<Photosynthesis> PHOTOSYNTHESIS = reg("photosynthesis", Photosynthesis::new, "Recover %2$s durability every %3$s seconds in places where brightness level is greater than %1$s");

	// curse 1 breakable, 2 armors, 2 weapons, 1 head, 1 chest, 1 leg, 1 tool
	public static final EnchVal.Legacy<DestructionCurse> DESTRUCTION_CURSE = reg("destruction_curse", DestructionCurse::new, "Increase explosion damage received by %s, and increase other incoming damage by %s");
	public static final EnchVal.Legacy<OvereatingCurse> OVEREATING_CURSE = reg("overeating_curse", OvereatingCurse::new, "Gain %s");
	public static final EnchVal.Legacy<CurseOfWeakness> CURSE_OF_WEAKNESS = reg("curse_of_weakness", CurseOfWeakness::new, "-%s of attack damage");
	public static final EnchVal.Legacy<PowerlessCurse> POWERLESS_CURSE = reg("powerless_curse", PowerlessCurse::new, "-%s mining speed");
	public static final EnchVal.Legacy<DraggingCurse> DRAGGING_CURSE = reg("dragging_curse", DraggingCurse::new, "Gain %s");
	public static final EnchVal.Legacy<HeavyCurse> CURSE_OF_GRAVITY = reg("curse_of_gravity", HeavyCurse::new, "-%s of attack speed");
	public static final EnchVal.Legacy<WitheringCurse> CURSE_OF_WITHERING = reg("curse_of_withering", WitheringCurse::new, "-%s max health");
	public static final EnchVal.Legacy<IlliteracyCurse> CURSE_OF_ILLITERACY = reg("curse_of_illiteracy", IlliteracyCurse::new, "-%s Exp pickup");
	public static final EnchVal.Legacy<CorrosionCurse> CURSE_OF_CORROSION = reg("cures_of_corrosion", CorrosionCurse::new, "When equipped, lose %s durability every %s seconds until it has less than half durability");

	private static <T extends CEBaseEnchantment> EnchVal.Legacy<T> reg(String id, Supplier<T> sup, String desc) {
		index = index + 1;
		ALL_ENCH.put(id, index);
		T instance = sup.get();
		instance.setID(id);
		EnchVal.Legacy<T> val = REG.enchLegacy(id, parse(id), desc, b -> configure(b, instance), () -> instance);
		instance.setVal(val);
		return val;
	}

	private static <T extends CEBaseEnchantment> EnchVal.Builder configure(EnchVal.Builder b, T instance) {
		var config = instance.config;
		b.items(instance.category)
				.cost(config.level().baseCost(), config.level().extraCost(), config.level().extraCost() * 2)
				.anvilCost(1)
				.maxLevel(config.level().maxLv())
				.weight(instance.rarity.weight)
				.group(instance.group)
				.color(new EnchColor(config.group().color(), ChatFormatting.GRAY));
		if (!config.group().multi()) {
			b.exclusive(config.group().tag());
		}
		for (var key : config.group().excl()) {
			b.exclusive(key);
		}
		for (var key : instance.extraExclusions()) {
			b.exclusive(key);
		}
		if (config.level().curse()) {
			b.tags(EnchantmentTags.CURSE);
		}
		return b;
	}

	public static void register() {
	}

}
