package firok.irisia.item;

import javax.annotation.Nullable;

import firok.irisia.Irisia;
import firok.irisia.common.IrisiaCreativeTabs;
import firok.irisia.mod.tc.Reg;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import java.util.*;

import firok.irisia.item.EquipmentSets.EquipmentSet;

import static firok.irisia.common.IrisiaCreativeTabs.*;


@SuppressWarnings({"unused","deprecation"})
public class ItemLoader
{
	public static ArrayList<Item> items=new ArrayList<>();
	private static ArrayList<Item> itemsTc=new ArrayList<>();
	
	public ItemLoader(FMLPreInitializationEvent event)
    {
    	registerItems();

    	register(null,InnerFunction.ITEM_TEXTURE,"INNER_FUNCTION","INNER_FUNCTION");
    }

	private static void registerRawMaterials()
	{
		register(material,RawMaterials.ShardOfLove,"material_shard_of_love","ShardOfLove");
		register(material,RawMaterials.Bezoar,"material_bezoar","Bezoar");
		register(material,RawMaterials.IcyGel,"material_icy_gel","IcyGel");
		register(material,RawMaterials.WolfFur,"material_wolf_fur","WolfFur");
		register(material,RawMaterials.IcyWolfFur,"material_icy_wolf_fur","IcyWolfFur");
		register(material,RawMaterials.ScorchingWolfFur,"material_scorching_wolf_fur","ScorchingWolfFur");
		register(material,RawMaterials.HeavyCashmere,"material_heavy_cashmere","HeavyCashmere");
		register(material,RawMaterials.TortoiseShell,"material_tortoise_shell","TortoiseShell");
		register(material,RawMaterials.BoneShard,"material_bone_shard","BoneShard");
		register(material,RawMaterials.CasinosBadge,"material_casinos_badge","CasinosBadge");
		register(material,RawMaterials.UnicornHorn,"material_unicorn_horn","UnicornHorn");
		register(material,RawMaterials.UnicornBlood,"material_unicorn_blood","UnicornBlood");
		register(material,RawMaterials.SlimeCore,"material_slime_core","SlimeCore");
		register(material,RawMaterials.BrownWheat,"material_brown_wheat","BrownWheat");
		register(material,RawMaterials.SoulCrystal,"material_soul_crystal","SoulCrystal");
		register(material,RawMaterials.DwartCoal,"material_dwart_coal","DwartCoal");
		register(material,RawMaterials.HotStone,"material_hot_stone","HotStone");
		register(material,RawMaterials.TerrestrialDoveFeather,"material_terrestrial_dove_feather","TerrestrialDoveFeather");
		register(material,RawMaterials.MagicalPaper,"material_magical_paper","MagicalPaper");
		register(material,RawMaterials.Ink,"material_ink","Ink");
		register(material,RawMaterials.InkBottle,"material_ink_bottle","InkBottle");
		register(material,RawMaterials.PaperPile,"material_paper_pile","PaperPile");
		register(material,RawMaterials.PaperBigPile,"material_paper_big_pile","PaperBigPile");
		register(material,RawMaterials.Nugget,"material_nugget","Nugget");
		register(material,RawMaterials.StormBall,"material_storm_ball","StormBall");
		register(material,RawMaterials.ChargedStormBall,"material_charged_storm_ball","ChargedStormBall");

		register(material,RawMaterials.DwartFlour,"material_dwart_flour","DwartFlour");
		register(material,RawMaterials.ElfStone,"material_elf_stone","ElfStone");
		register(material,RawMaterials.SolarCrystal,"material_solar_crystal","SolarCrystal");
		register(material,RawMaterials.LunarCrystal,"material_lunar_crystal","LunarCrystal");
		register(material,RawMaterials.GlitteringLunarCrystal,"material_glittering_solar_crystal","GlitteringSolarCrystal");
		register(material,RawMaterials.GlitteringSolarCrystal,"material_glittering_lunar_crystal","GlitteringLunarCrystal");
		register(material,RawMaterials.DarkMetalIngot,"material_dark_metal_ingot","DarkMetalIngot");
		register(material,RawMaterials.VibrhythmMetalIngot,"material_vibrhythm_metal_ingot","VibrhythmMetalIngot");
		register(material,RawMaterials.LuxMetalIngot,"material_lux_metal_ingot","LuxMetalIngot");
		register(material,RawMaterials.StormMetalIngot,"material_storm_metal_ingot","StormMetalIngot");
		register(material,RawMaterials.PhotosynthesisMetalIngot,"material_photosynthesis_metal_ingot","PhotosynthesisMetalIngot");
		register(material,RawMaterials.FlumetalIngot,"material_flumetal_ingot","FlumetalIngot");
		register(material,RawMaterials.SolitaIngot,"material_solita_ingot","SolitaIngot");
		register(material,RawMaterials.MogigaIngot,"material_mogiga_ingot","MogigaIngot");
		register(material,RawMaterials.DwartSteelIngot,"material_dwart_steel_ingot","DwartSteelIngot");
		register(material,RawMaterials.MithrilIngot,"material_mithril_ingot","MithrilIngot");
		register(material,RawMaterials.AdamantiumIngot,"material_adamantium_ingot","AdamantiumIngot");
		register(material,RawMaterials.SlimeIngot,"material_slime_ingot","SlimeIngot");
		register(material,RawMaterials.MagicalDust,"material_magical_dust","MagicalDust");
		register(material,RawMaterials.InvalidRunicRing,"material_invalid_runic_ring","InvalidRunicRing");
		register(material,RawMaterials.LostPage,"material_lost_page","LostPage");
		register(material,RawMaterials.GoldenSilk,"material_golden_silk","GoldenSilk");
		register(material,RawMaterials.DiamondSilk,"material_diamond_silk","DiamondSilk");
		register(material,RawMaterials.MultiCoreBrain,"material_multicore_brain","MultiCoreBrain");
		register(material,RawMaterials.AstrologicStone,"material_astrologic_stone","AstrologicStone");

		register(irisia,RawMaterials.CoinCopper,"coin_copper","CoinCopper");
		register(irisia,RawMaterials.CoinCopperPile,"coin_copper_pile","CoinCopperPile");
		register(irisia,RawMaterials.CoinSilver,"coin_silver","CoinSilver");
		register(irisia,RawMaterials.CoinSilverPile,"coin_silver_pile","CoinSilverPile");
		register(irisia,RawMaterials.CoinGold,"coin_gold","CoinGold");
		register(irisia,RawMaterials.CoinGoldPile,"coin_gold_pile","CoinGoldPile");
		register(irisia,RawMaterials.CoinIrisia,"coin_irisia","CoinIrisia");
		register(irisia,RawMaterials.CoinIrisiaPile,"coin_irisia_pile","CoinIrisiaPile");

		register(material,RawMaterials.AncientMachinePart,"ancient_machine_part","AncientMachinePart");
		register(material,RawMaterials.AncientThaumicNote,"ancient_thaumic_note","AncientThaumicNote");
		register(material,RawMaterials.AncientFossilFragment,"ancient_fossil_fragment","AncientFossilFragment");
		register(material,RawMaterials.AncientBrokenSword,"ancient_broken_sword","AncientBrokenSword");

	}
	private static void registerEquipments()
	{
		register(equi,EquipmentSets.AdamantiumSet);
		register(equi,EquipmentSets.MithrilSet);
		register(equi,EquipmentSets.BoneSet);
		register(equi,EquipmentSets.SpectreSet);
		register(equi,EquipmentSets.SolitaSet);
		register(equi,EquipmentSets.MogigaSet);
		register(equi,EquipmentSets.DwartSteelSet);
		register(equi,EquipmentSets.WindRangerSet);
		register(equi,EquipmentSets.DwartMinerSet);
		register(equi,EquipmentSets.StormSet);
		register(equi,EquipmentSets.PhoneixSet);
		// registerLoot(equi,EquipmentSets.LifeWoodSet); // 已经转移
		register(equi,EquipmentSets.FlumetalSet);
		register(equi,EquipmentSets.DarkIronArmorSet);
		register(equi,EquipmentSets.DarkIronToolSet);
		register(equi,EquipmentSets.VibrhythmToolSet);
		register(equi,EquipmentSets.LuxIronArmorSet);
		register(equi,EquipmentSets.LuxIronToolSet);
		register(equi,EquipmentSets.PhotosynthesisArmorSet);
		register(equi,EquipmentSets.PhotosynthesisToolSet);
		register(equi,EquipmentSets.GarrisonSet);
		// 注册生命木套装
		register(equi,EquipmentAutoRepair.LifewoodSword,"sword_lifewood","LifewoodSword");
		register(equi,EquipmentAutoRepair.LifewoodHelmet,"helmet_lifewood","LifewoodHelmet");
		register(equi,EquipmentAutoRepair.LifewoodChestplate,"chestplate_lifewood","LifewoodChestplate");
		register(equi,EquipmentAutoRepair.LifewoodLeggings,"leggings_lifewood","LifewoodLeggings");
		register(equi,EquipmentAutoRepair.LifewoodBoots,"boots_lifewood","LifewoodBoots");
		register(equi,EquipmentAutoRepair.LifewoodAxe,"axe_lifewood","LifewoodAxe");
		register(equi,EquipmentAutoRepair.LifewoodPickaxe,"pickaxe_lifewood","LifewoodPickaxe");
		register(equi,EquipmentAutoRepair.LifewoodHoe,"hoe_lifewood","LifewoodHoe");
		register(equi,EquipmentAutoRepair.LifewoodSpade,"spade_lifewood","LifewoodSpade");
		// 注册史莱姆套装
		register(equi,EquipmentAutoRepair.SlimeHelmet,"helmet_slime","SlimeHelmet");
		register(equi,EquipmentAutoRepair.SlimeChestplate,"chestplate_slime","SlimeChestplate");
		register(equi,EquipmentAutoRepair.SlimeLeggings,"leggings_slime","SlimeLeggings");
		register(equi,EquipmentAutoRepair.SlimeBoots,"boots_slime","SlimeBoots");
	}
	private static void registerWains()
	{
		// 北斗
		// 天权-无限之剑
		register(equi,WainItems.AliothTheInfinity,"wain_alioth","WainAlioth");
		// 天玑-回音护符
		register(equi,WainItems.PhecdaTheEcho,"wain_phecda","WainPhecda");
		// 天枢-脉冲之剑
		register(equi,WainItems.AlkaidTheImpulse,"wain_alkaid","WainAlkaid");
	}
	private static void registerWands()
	{
		// 法杖套装
		register(tc,Wands.LifeWoodSet);
		register(tc,Wands.SpectreSet);
		register(tc,Wands.ItemNodeRod,"wand_rod_node","NodeRod");
		register(tc,Wands.ItemCreativeRod,"wand_rod_creative","CreativeRod");
		register(tc,Wands.AdamantiumSet);
		register(tc,Wands.MithrilSet);
		register(tc,Wands.SolitaSet);
		register(tc,Wands.MogigaSet);

		// 散件
		register(tc,Wands.ItemWhitebeardStaffRod,"staff_rod_whitebeard","WhitebeardStaffRod");
		register(tc,Wands.ItemBlackbeardStaffRod,"staff_rod_blackbeard","BlackbeardStaffRod");
		register(tc,Wands.ItemGraybeardStaffRod,"staff_rod_graybeard","GraybeardStaffRod");

		// 升级杖芯
		register(tc,Wands.ItemAdvancedRodObsidian,"wand_rod_advanced_obsidian","AdvancedRodObsidian");
		register(tc,Wands.ItemAdvancedRodBone,"wand_rod_advanced_bone","AdvancedRodBone");
		register(tc,Wands.ItemAdvancedRodBlaze,"wand_rod_advanced_blaze","AdvancedRodBlaze");
		register(tc,Wands.ItemAdvancedRodIce,"wand_rod_advanced_ice","AdvancedRodIce");
		register(tc,Wands.ItemAdvancedRodQuartz,"wand_rod_advanced_quartz","AdvancedRodQuartz");
		register(tc,Wands.ItemAdvancedRodReed,"wand_rod_advanced_reed","AdvancedRodReed");
	}
	private static void registerFocus()
	{
		register(tc,Focus.Test,"focus_test","FocusTest");
		register(tc,Focus.SpiderQueen,"focus_spider_queen","FocusSpiderQueen");
		register(tc,Focus.Wither,"focus_wither","FocusWither");
		register(tc,Focus.MachineryAlchemy,"focus_machinery_alchemy","FocusMachineryAlchemy");
		register(tc,Focus.Supernova,"focus_supernova","FocusSupernova");
		register(tc,Focus.ArcaneShield,"focus_arcane_shield","FocusArcaneShield");
		register(tc,Focus.Bluring,"focus_bluring","FocusBluring");
	}
	private static void registerFoods()
	{
		// 食物
		register(food,Foods.HealingSalve,"healing_salve","HealingSalve");
		register(food,Foods.VilligerFood,"villiger_food","VilligerFood");
		register(food,Foods.MixedSweet,"mixed_sweet","MixedSweet");
		register(food,Foods.PoisonousApple,"poisonous_apple","PoisonousApple");

		register(food,Foods.HuckTeaLeafBerry,"berry_huck_tea_leaf","BerryHuckTeaLeaf");
		register(food,Foods.MonaFruitBerry,"berry_mona_fruit","BerryMonaFruit");
		register(food,Foods.SpicyRootBerry,"berry_spicy_root","BerrySpicyRoot");
		register(food,Foods.StarousFruitBerry,"berry_starous_fruit","berryStarousFruit");
	}
	private static void registerBaubles()
	{
		register(equi,EquipmentUniqueBaubles.SylphBelt,"belt_sylph","SylphBelt");
		register(equi,EquipmentUniqueBaubles.MidasRelic,"ring_midas_relic","MidasRelicRing");

		register(equi,EquipmentUniqueBaubles.FrostyStone,"amulet_frosty_stone","FrostyStone");
		register(equi,EquipmentUniqueBaubles.RevealingGem,"amulet_revealing_gem","GemRevealing");
		register(equi,EquipmentUniqueBaubles.FortuneRing,"ring_fortune","FortuneRing");
		register(equi,EquipmentUniqueBaubles.MinersRing,"ring_miner","MinersRing");
		register(equi,EquipmentUniqueBaubles.ThrivingRing,"ring_thriving","ThrivingRing");
		register(equi,EquipmentUniqueBaubles.InsaneRing,"ring_insane","InsaneRing");
		register(equi,EquipmentUniqueBaubles.LucidRing,"ring_lucid","LucidRing");
		register(equi,EquipmentUniqueBaubles.LoveRing,"ring_love","LoveRing");
		register(equi,EquipmentUniqueBaubles.PlagueStone,"amulet_plague_stone","AmuletPlagueStone");
		register(equi,EquipmentUniqueBaubles.PhotosynthesisAmulet,"ring_photosynthesis","PhotosynthesisAmulet");
		register(equi,EquipmentUniqueBaubles.DwartTravellerBelt,"belt_dwart_traveller","DwartTravellerBelt");
		register(equi,EquipmentUniqueBaubles.MermaidBelt,"belt_mermaid","MermaidBelt");
		register(equi,EquipmentUniqueBaubles.TwelveMagicalPowerAmulet,"amulet_twelve_magical","TwelveMagicalAmulet");

		register(equi,EquipmentUniqueBaubles.DimVisRingEarth,"ring_visring_earth_dim","DimVisRingEarth");
		register(equi,EquipmentUniqueBaubles.DimVisRingFire,"ring_visring_fire_dim","DimVisRingFire");
		register(equi,EquipmentUniqueBaubles.DimVisRingWater,"ring_visring_water_dim","DimVisRingWater");
		register(equi,EquipmentUniqueBaubles.DimVisRingAir,"ring_visring_air_dim","DimVisRingAir");
		register(equi,EquipmentUniqueBaubles.DimVisRingOrder,"ring_visring_order_dim","DimVisRingOrder");
		register(equi,EquipmentUniqueBaubles.DimVisRingEntropy,"ring_visring_entropy_dim","DimVisRingEntropy");
		register(equi,EquipmentUniqueBaubles.VisRingEarth,"ring_visring_earth","VisRingEarth");
		register(equi,EquipmentUniqueBaubles.VisRingFire,"ring_visring_fire","VisRingFire");
		register(equi,EquipmentUniqueBaubles.VisRingWater,"ring_visring_water","VisRingWater");
		register(equi,EquipmentUniqueBaubles.VisRingAir,"ring_visring_air","VisRingAir");
		register(equi,EquipmentUniqueBaubles.VisRingOrder,"ring_visring_order","VisRingOrder");
		register(equi,EquipmentUniqueBaubles.VisRingEntropy,"ring_visring_entropy","VisRingEntropy");
		register(equi,EquipmentUniqueBaubles.GlowVisRingEarth,"ring_visring_earth_glow","GlowVisRingEarth");
		register(equi,EquipmentUniqueBaubles.GlowVisRingFire,"ring_visring_fire_glow","GlowVisRingFire");
		register(equi,EquipmentUniqueBaubles.GlowVisRingWater,"ring_visring_water_glow","GlowVisRingWater");
		register(equi,EquipmentUniqueBaubles.GlowVisRingAir,"ring_visring_air_glow","GlowVisRingAir");
		register(equi,EquipmentUniqueBaubles.GlowVisRingOrder,"ring_visring_order_glow","GlowVisRingOrder");
		register(equi,EquipmentUniqueBaubles.GlowVisRingEntropy,"ring_visring_entropy_glow","GlowVisRingEntropy");

		register(equi,EquipmentAntiBuffBaubles.AntiPoisonRing,"ring_ati_poison","AntiPoisonRing");
		register(equi,EquipmentAntiBuffBaubles.HolyCrossAmulet,"amulet_holy_cross","HolyCrossAmulet");

		register(equi,EquipmentBuffBaubles.GeneralEmblem,"amulet_general_emblem","GeneralEmblem");
		register(equi,EquipmentBuffBaubles.GiantBelt,"belt_giant","GiantBelt");
		register(equi,EquipmentBuffBaubles.ManaBoostRing,"ring_mana_boost","ManaBoostRing");
		register(equi,EquipmentBuffBaubles.ManaInterferingRing,"ring_mana_interfering","ManaInterferingRing");
	}
	private static void registerConsumables()
	{
		// 消耗品
		register(irisia,Consumables.Gashapon,"cons_gashapon","Gashapon");
		register(irisia,Consumables.ThaumicNote,"cons_thaumic_note","ThaumicNote");
		register(irisia,Consumables.DustOfAppearance,"cons_dust_of_appearance","DustOfAppearance");
	}
	private static void registerConsumableWeapons()
	{
		register(irisia,ConsumableWeapons.ThrowableWeapons,"consw_tws","ThrowableWeapons");
		register(irisia,ConsumableWeapons.SmokeBomb,"consw_smoke_bomb","SmokeBomb");
	}
	private static void registerWeapons()
	{
		register(equi,Weapons.FlailWood,"weapon_flail_wood","WeaponFlailWood");
		register(equi,Weapons.FlailIron,"weapon_flail_iron","WeaponFlailIron");
		register(equi,Weapons.FlailGold,"weapon_flail_gold","WeaponFlailGold");
		register(equi,Weapons.FlailDiamond,"weapon_flail_diamond","WeaponFlailDiamond");
		register(equi,Weapons.FlailVoidMetal,"weapon_flail_void_metal","WeaponFlailVoidMetal");
		register(equi,Weapons.FlailAdamantium,"weapon_flail_adamantium","WeaponFlailAdamantium");
		register(equi,Weapons.FlailMithril,"weapon_flail_mithril","WeaponFlailMithril");
		register(equi,Weapons.FlailSolita,"weapon_flail_solita","WeaponFlailSolita");
		register(equi,Weapons.FlailMogiga,"weapon_flail_mogiga","WeaponFlailMogiga");
		register(equi,Weapons.FlailBone,"weapon_flail_bone","WeaponFlailBone");

		register(equi,Weapons.BoneStick,"weapon_bone_stick","WeaponBoneStick");
		register(equi,Weapons.GuardianSword,"weapon_guardian_sword","WeaponGuardianSword");
		register(equi,Weapons.Maquahuitl,"weapon_maquahuitl","WeaponMaquahuitl");
		register(equi,Weapons.VoidRunicLongBow,"weapon_void_runic_long_bow","WeaponVoidRunicLongBow");
		register(equi,Weapons.MercurialBlade,"weapon_mercurial_blade","WeaponMercurialBlade");
		register(equi,Weapons.NightBlade,"weapon_night_blade","WeaponNightBlade");
		register(equi,Weapons.BerserkerSword,"weapon_berserker_sword","WeaponBerserkerSword");
		register(equi,Weapons.KineticBlade,"weapon_kinetic_blade","WeaponKineticBlade");
		register(equi,Weapons.WarpingBlade,"weapon_warping_blade","WeaponWarpingBlade");
		register(equi,Weapons.SoulEater,"weapon_soul_eater","WeaponSoulEater");
		register(equi,Weapons.LunarDagger,"weapon_lunar_dagger","WeaponLunarDagger");
		register(equi,Weapons.Radiance,"weapon_radiance","WeaponRadiance");
		register(equi,Weapons.BurningSpear,"weapon_buring_spear","WeaponBuringSpear");
		register(equi,Weapons.IcyRoseSword,"weapon_icy_rose_sword","WeaponIcyRoseSword");
		register(equi,Weapons.GreenCrystalSword,"weapon_green_crystal_sword","WeaponGreenCrystalSword");
	}
	private static void registerTools()
	{
		register(irisia,Tools.ReturnCompass,"tool_return_compass","ToolReturnCompass");
		register(irisia,Tools.Debugger,"tool_debugger","ToolDebugger");
		register(irisia,Tools.Astrolabe,"tool_astrolabe","ToolAstrolabe");
		register(irisia,Tools.ArmorStorageBox,"tool_armor_storage_box","ToolArmorStorageBox");
		register(irisia,Tools.BaubleStorageBox,"tool_bauble_storage_box","ToolBaubleStorageBox");
		// register(irisia,Tools.MagicBag,"tool_magic_bag","ToolMagicBag");
		register(irisia,Tools.OrienterThaumium,"tool_orienter_thaumium","ToolOrienterThaumium");
		register(irisia,Tools.OrienterStorm,"tool_orienter_storm","ToolOrienterStorm");
		register(irisia,Tools.OrienterDark,"tool_orienter_dark","ToolOrienterDark");
		register(irisia,Tools.OrienterPhotosynthesis,"tool_orienter_photosynthesis","ToolOrienterPhotosynthesis");
		register(irisia,Tools.OrienterVibrhythm,"tool_orienter_vibrhythm","ToolOrienterVibrhythm");
	}
//	private static void registerSeeds()
//	{
//		register(irisia, HerbSeeds.DeathGrassSeed,"seed_death_grass","DeathGrassSeed");
//		register(irisia, HerbSeeds.MoonGrassSeed,"seed_moon_grass","MoonGrassSeed");
//	}
	protected static void registerItems()
    {
	    registerEquipments();

	    registerWains();

	    registerWands();

	    registerFocus();

	    registerRawMaterials();

	    registerFoods();

	    registerBaubles();

	    registerConsumables();

	    registerConsumableWeapons();

	    registerWeapons();

	    registerTools();

//	    registerSeeds();
    }

    public static void register(@Nullable CreativeTabs tab,Item item,Irisia.NameObject nb)
    {
    	register(tab,item,nb.textureName,nb.unlocalizedName);
    }
	public static void register(@Nullable CreativeTabs tab, EquipmentSets.ArmorSet set)
    {
    	if(set==null)
    		return;

    	String fuName=Irisia.getFirstUpper(set.materialName);

	    register(tab,set.Helmet,"helmet_"+set.materialName, fuName+"Helmet");
	    register(tab,set.Chestplate,"chestplate_"+set.materialName, fuName+"Chestplate");
	    register(tab,set.Leggings,"leggings_"+set.materialName, fuName+"Leggings");
	    register(tab,set.Boots,"boots_"+set.materialName, fuName+"Boots");
    }
	public static void register(@Nullable CreativeTabs tab,EquipmentSet set)
    {
    	if(set==null) // 检查要注册的套装是不是空的
    		return;

    	String fuName=Irisia.getFirstUpper(set.materialName);

    	register(tab,set.Sword,"sword_"+set.materialName, fuName+"Sword");

    	register(tab,set.Pickaxe,"pickaxe_"+set.materialName, fuName+"Pickaxe");
    	register(tab,set.Axe,"axe_"+set.materialName, fuName+"Axe");
    	register(tab,set.Spade,"spade_"+set.materialName, fuName+"Spade");
    	register(tab,set.Hoe,"hoe_"+set.materialName, fuName+"Hoe");

    	register(tab,set.Helmet,"helmet_"+set.materialName, fuName+"Helmet");
    	register(tab,set.Chestplate,"chestplate_"+set.materialName, fuName+"Chestplate");
    	register(tab,set.Leggings,"leggings_"+set.materialName, fuName+"Leggings");
    	register(tab,set.Boots,"boots_"+set.materialName, fuName+"Boots");

    	register(tab,set.Amulet,"amulet_"+set.materialName, fuName+"Amulet");
    	register(tab,set.Belt,"belt_"+set.materialName, fuName+"Belt");
    	register(tab,set.Ring,"ring_"+set.materialName, fuName+"Ring");
    }
	public static void register(@Nullable CreativeTabs tab,Wands.WandSet set)
    {
    	if(set==null)
    		return;

	    String fuName=Irisia.getFirstUpper(set.materialName);
	    Irisia.log(new StringBuffer().append("注册法术套装 : ").append(fuName));
    	register(tab,set.Cap,"cap_"+set.materialName,fuName+"ItemCap");
    	register(tab,set.Rod,"rod_"+set.materialName,fuName+"ItemRod");
    }

	public static void register(@Nullable CreativeTabs tab,Item item, String name,String unlocalizedName)
    {
    	if(item==null || name==null || unlocalizedName==null) // 检查要注册的物品是不是空的
    		return;

	    item.setUnlocalizedName(unlocalizedName);
	    item.setTextureName(Irisia.MODID+":"+name);
	    GameRegistry.registerItem(item,name,Irisia.MODID);
        if(tab!=null)
        	item.setCreativeTab(tab);
        
        items.add(item);
        
//        if(Loader.isModLoaded("Thaumcraft"))
//        	registerTcContent(item);
//        if(Loader.isModLoaded("Tinkers' Contruct"))
//        	registerTicContent(item);
    }
 	
    // thaumcraft item registerLoot // 神秘时代注册
    private static void registerTcContent(Item item)
    {
    	boolean isTcItem=false;
    	// 注册神秘时代相关内容
        if(item instanceof Reg.IregShapedArcaneRecipe)
        {
        	((Reg.IregShapedArcaneRecipe) item).regShapedArcaneRecipe();
        	isTcItem=true;
        }
        if(item instanceof Reg.IregShapelessArcaneRecipe)
        {
        	((Reg.IregShapelessArcaneRecipe) item).regShapelessArcaneRecipe();
        	isTcItem=true;
        }
        if(item instanceof Reg.IregInfusionCraftingRecipe)
        {
        	((Reg.IregInfusionCraftingRecipe) item).regInfusionCraftingRecipe();
        	isTcItem=true;
        }
        if(item instanceof Reg.IregMiscRecipe)
        {
        	((Reg.IregMiscRecipe) item).regMiscRecipe();
        	isTcItem=true;
        }
        if(item instanceof Reg.IregSmeltingBonus)
        {
        	((Reg.IregSmeltingBonus) item).regSmeltingBonus();
        	isTcItem=true;
        }
        if(item instanceof Reg.IregCrucibleRecipe)
        {
        	((Reg.IregCrucibleRecipe) item).regCrucibleRecipe();
        	isTcItem=true;
        }
        if(item instanceof Reg.IregTcSomething)
        {
        	((Reg.IregTcSomething) item).regTcSomething();
        	isTcItem=true;
        }
        
        if(isTcItem)
        	itemsTc.add(item);
    }
    
    // tinkers' item registerLoot // 匠魂注册
	private static void registerTicContent(Item item)
    {
    	boolean isTicItem=false;
    	
    	;
    }
}
