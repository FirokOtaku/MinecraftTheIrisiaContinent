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


@SuppressWarnings({"unused","deprecation"})
public class ItemLoader
{
	public static ArrayList<Item> items=new ArrayList<>();
	private static ArrayList<Item> itemsTc=new ArrayList<>();
	
	public ItemLoader(FMLPreInitializationEvent event)
    {
    	registerItems();
    }

	private static void registerRawMaterials()
	{
		register(IrisiaCreativeTabs.material,RawMaterials.ShardOfLove,"material_shard_of_love","ShardOfLove");
		register(IrisiaCreativeTabs.material,RawMaterials.Bezoar,"material_bezoar","Bezoar");
		register(IrisiaCreativeTabs.material,RawMaterials.IcyGel,"material_icy_gel","IcyGel");
		register(IrisiaCreativeTabs.material,RawMaterials.WolfFur,"material_wolf_fur","WolfFur");
		register(IrisiaCreativeTabs.material,RawMaterials.IcyWolfFur,"material_icy_wolf_fur","IcyWolfFur");
		register(IrisiaCreativeTabs.material,RawMaterials.ScorchingWolfFur,"material_scorching_wolf_fur","ScorchingWolfFur");
		register(IrisiaCreativeTabs.material,RawMaterials.HeavyCashmere,"material_heavy_cashmere","HeavyCashmere");
		register(IrisiaCreativeTabs.material,RawMaterials.TortoiseShell,"material_tortoise_shell","TortoiseShell");
		register(IrisiaCreativeTabs.material,RawMaterials.BoneShard,"material_bone_shard","BoneShard");
		register(IrisiaCreativeTabs.material,RawMaterials.CasinosBadge,"material_casinos_badge","CasinosBadge");
		register(IrisiaCreativeTabs.material,RawMaterials.UnicornHorn,"material_unicorn_horn","UnicornHorn");
		register(IrisiaCreativeTabs.material,RawMaterials.UnicornBlood,"material_unicorn_blood","UnicornBlood");
		register(IrisiaCreativeTabs.material,RawMaterials.SlimeCore,"material_slime_core","SlimeCore");
		register(IrisiaCreativeTabs.material,RawMaterials.BrownWheat,"material_brown_wheat","BrownWheat");
		register(IrisiaCreativeTabs.material,RawMaterials.SoulCrystal,"material_soul_crystal","SoulCrystal");
		register(IrisiaCreativeTabs.material,RawMaterials.DwartCoal,"material_dwart_coal","DwartCoal");
		register(IrisiaCreativeTabs.material,RawMaterials.HotStone,"material_hot_stone","HotStone");
		register(IrisiaCreativeTabs.material,RawMaterials.TerrestrialDoveFeather,"material_terrestrial_dove_feather","TerrestrialDoveFeather");
		register(IrisiaCreativeTabs.material,RawMaterials.MagicalPaper,"material_magical_paper","MagicalPaper");
		register(IrisiaCreativeTabs.material,RawMaterials.Ink,"material_ink","Ink");
		register(IrisiaCreativeTabs.material,RawMaterials.InkBottle,"material_ink_bottle","InkBottle");
		register(IrisiaCreativeTabs.material,RawMaterials.PaperPile,"material_paper_pile","PaperPile");
		register(IrisiaCreativeTabs.material,RawMaterials.PaperBigPile,"material_paper_big_pile","PaperBigPile");
		register(IrisiaCreativeTabs.material,RawMaterials.Nugget,"material_nugget","Nugget");
		register(IrisiaCreativeTabs.material,RawMaterials.StormBall,"material_storm_ball","StormBall");
		register(IrisiaCreativeTabs.material,RawMaterials.ChargedStormBall,"material_charged_storm_ball","ChargedStormBall");


		register(IrisiaCreativeTabs.material,RawMaterials.DwartFlour,"material_dwart_flour","DwartFlour");
		register(IrisiaCreativeTabs.material,RawMaterials.ElfStone,"material_elf_stone","ElfStone");
		register(IrisiaCreativeTabs.material,RawMaterials.FlumetalIngot,"material_flumetal_ingot","FlumetalIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.SolitaIngot,"material_solita_ingot","SolitaIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.MogigaIngot,"material_mogiga_ingot","MogigaIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.DwartSteelIngot,"material_dwart_steel_ingot","DwartSteelIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.MithrilIngot,"material_mithril_ingot","MithrilIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.AdamantiumIngot,"material_adamantium_ingot","AdamantiumIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.SlimeIngot,"material_slime_ingot","SlimeIngot");
		register(IrisiaCreativeTabs.material,RawMaterials.InvalidRunicRing,"material_invalid_runic_ring","InvalidRunicRing");
		register(IrisiaCreativeTabs.material,RawMaterials.LostPage,"material_lost_page","LostPage");
		register(IrisiaCreativeTabs.material,RawMaterials.GoldenSilk,"material_golden_silk","GoldenSilk");
		register(IrisiaCreativeTabs.material,RawMaterials.DiamondSilk,"material_diamond_silk","DiamondSilk");
		register(IrisiaCreativeTabs.material,RawMaterials.MultiCoreBrain,"material_multicore_brain","MultiCoreBrain");

		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinCopper,"coin_copper","CoinCopper");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinCopperPile,"coin_copper_pile","CoinCopperPile");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinSilver,"coin_silver","CoinSilver");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinSilverPile,"coin_silver_pile","CoinSilverPile");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinGold,"coin_gold","CoinGold");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinGoldPile,"coin_gold_pile","CoinGoldPile");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinIrisia,"coin_irisia","CoinIrisia");
		register(IrisiaCreativeTabs.irisia,RawMaterials.CoinIrisiaPile,"coin_irisia_pile","CoinIrisiaPile");

		register(IrisiaCreativeTabs.material,RawMaterials.AncientMachinePart,"ancient_machine_part","AncientMachinePart");
		register(IrisiaCreativeTabs.material,RawMaterials.AncientThaumicNote,"ancient_thaumic_note","AncientThaumicNote");
		register(IrisiaCreativeTabs.material,RawMaterials.AncientFossilFragment,"ancient_fossil_fragment","AncientFossilFragment");
		register(IrisiaCreativeTabs.material,RawMaterials.AncientBrokenSword,"ancient_broken_sword","AncientBrokenSword");

	}
	private static void registerEquipments()
	{
		register(IrisiaCreativeTabs.equi,EquipmentSets.AdamantiumSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.MithrilSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.BoneSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.SpectreSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.SolitaSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.MogigaSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.DwartSteelSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.WindRangerSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.DwartMinerSet);
		register(IrisiaCreativeTabs.equi,EquipmentSets.StormSet);
		// registerLoot(IrisiaCreativeTabs.equi,EquipmentSets.LifeWoodSet); // 已经转移
		register(IrisiaCreativeTabs.equi,EquipmentSets.FlumetalSet);
		// 注册生命木套装
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodSword,"sword_lifewood","LifewoodSword");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodHelmet,"helmet_lifewood","LifewoodHelmet");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodChestplate,"chestplate_lifewood","LifewoodChestplate");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodLeggings,"leggings_lifewood","LifewoodLeggings");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodBoots,"boots_lifewood","LifewoodBoots");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodAxe,"axe_lifewood","LifewoodAxe");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodPickaxe,"pickaxe_lifewood","LifewoodPickaxe");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodHoe,"hoe_lifewood","LifewoodHoe");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.LifewoodSpade,"spade_lifewood","LifewoodSpade");
		// 注册史莱姆套装
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.SlimeHelmet,"helmet_slime","SlimeHelmet");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.SlimeChestplate,"chestplate_slime","SlimeChestplate");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.SlimeLeggings,"leggings_slime","SlimeLeggings");
		register(IrisiaCreativeTabs.equi,EquipmentAutoRepair.SlimeBoots,"boots_slime","SlimeBoots");
	}
	private static void registerWains()
	{
		// 北斗
		register(IrisiaCreativeTabs.equi,WainItems.AliothTheInfinity,"wain_alioth","WainAlioth");

	}
	private static void registerWands()
	{
		// 法杖套装
		register(IrisiaCreativeTabs.tc,Wands.LifeWoodSet);
		register(IrisiaCreativeTabs.tc,Wands.SpectreSet);
		register(IrisiaCreativeTabs.tc,Wands.ItemNodeRod,"wand_rod_node","NodeRod");
		register(IrisiaCreativeTabs.tc,Wands.ItemCreativeRod,"wand_rod_creative","CreativeRod");
		register(IrisiaCreativeTabs.tc,Wands.AdamantiumSet);
		register(IrisiaCreativeTabs.tc,Wands.MithrilSet);
		register(IrisiaCreativeTabs.tc,Wands.SolitaSet);
		register(IrisiaCreativeTabs.tc,Wands.MogigaSet);
	}
	private static void registerFoods()
	{
		// 食物
		register(IrisiaCreativeTabs.food,Foods.VilligerFood,"villiger_food","VilligerFood");
		register(IrisiaCreativeTabs.food,Foods.MixedSweet,"mixed_sweet","MixedSweet");
		register(IrisiaCreativeTabs.food,Foods.BerryNull,"berry_null","NullBerry");

	}
	private static void registerBaubles()
	{
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.SylphBelt,"belt_sylph","SylphBelt");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.MidasRelic,"ring_midas_relic","MidasRelicRing");

		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.FortuneRing,"ring_fortune","FortuneRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.MinersRing,"ring_miner","MinersRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.ThrivingRing,"ring_thriving","ThrivingRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.InsaneRing,"ring_insane","InsaneRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.LucidRing,"ring_lucid","LucidRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.LoveRing,"ring_love","LoveRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.MidasRelic,"ring_midas_relic","MidasRelicRing");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.PhotosynthesisAmulet,"ring_photosynthesis","PhotosynthesisAmulet");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DwartTravellerBelt,"belt_dwart_traveller","DwartTravellerBelt");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.MermaidBelt,"belt_mermaid","MermaidBelt");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.TwelveMagicalPowerAmulet,"amulet_twelve_magical","TwelveMagicalAmulet");

		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DimVisRingEarth,"ring_visring_earth_dim","DimVisRingEarth");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DimVisRingFire,"ring_visring_fire_dim","DimVisRingFire");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DimVisRingWater,"ring_visring_water_dim","DimVisRingWater");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DimVisRingAir,"ring_visring_air_dim","DimVisRingAir");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DimVisRingOrder,"ring_visring_order_dim","DimVisRingOrder");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.DimVisRingEntropy,"ring_visring_entropy_dim","DimVisRingEntropy");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.VisRingEarth,"ring_visring_earth","VisRingEarth");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.VisRingFire,"ring_visring_fire","VisRingFire");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.VisRingWater,"ring_visring_water","VisRingWater");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.VisRingAir,"ring_visring_air","VisRingAir");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.VisRingOrder,"ring_visring_order","VisRingOrder");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.VisRingEntropy,"ring_visring_entropy","VisRingEntropy");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.GlowVisRingEarth,"ring_visring_earth_glow","GlowVisRingEarth");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.GlowVisRingFire,"ring_visring_fire_glow","GlowVisRingFire");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.GlowVisRingWater,"ring_visring_water_glow","GlowVisRingWater");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.GlowVisRingAir,"ring_visring_air_glow","GlowVisRingAir");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.GlowVisRingOrder,"ring_visring_order_glow","GlowVisRingOrder");
		register(IrisiaCreativeTabs.equi,EquipmentUniqueBaubles.GlowVisRingEntropy,"ring_visring_entropy_glow","GlowVisRingEntropy");

		register(IrisiaCreativeTabs.equi,EquipmentAntiBuffBaubles.AntiPoisonRing,"ring_ati_poison","AntiPoisonRing");
		register(IrisiaCreativeTabs.equi,EquipmentAntiBuffBaubles.HolyCrossAmulet,"amulet_holy_cross","HolyCrossAmulet");

		register(IrisiaCreativeTabs.equi,EquipmentBuffBaubles.GeneralEmblem,"amulet_general_emblem","GeneralEmblem");
		register(IrisiaCreativeTabs.equi,EquipmentBuffBaubles.GiantBelt,"belt_giant","GiantBelt");
		register(IrisiaCreativeTabs.equi,EquipmentBuffBaubles.ManaBoostRing,"ring_mana_boost","ManaBoostRing");
		register(IrisiaCreativeTabs.equi,EquipmentBuffBaubles.ManaInterferingRing,"ring_mana_interfering","ManaInterferingRing");
	}
	private static void registerConsumables()
	{
		// 消耗品
		register(IrisiaCreativeTabs.irisia,Consumables.Gashapon,"cons_gashapon","Gashapon");
		register(IrisiaCreativeTabs.irisia,Consumables.ThaumicNote,"cons_thaumic_note","ThaumicNote");
	}
	private static void registerConsumableWeapons()
	{
		register(IrisiaCreativeTabs.irisia,ConsumableWeapons.ThrowableWeapons,"consw_tws","ThrowableWeapons");
	}
	private static void registerWeapons()
	{
		register(IrisiaCreativeTabs.equi,Weapons.FlailWood,"weapon_flail_wood","WeaponFlailWood");
		register(IrisiaCreativeTabs.equi,Weapons.FlailIron,"weapon_flail_iron","WeaponFlailIron");
		register(IrisiaCreativeTabs.equi,Weapons.FlailGold,"weapon_flail_gold","WeaponFlailGold");
		register(IrisiaCreativeTabs.equi,Weapons.FlailDiamond,"weapon_flail_diamond","WeaponFlailDiamond");
		register(IrisiaCreativeTabs.equi,Weapons.FlailVoidMetal,"weapon_flail_void_metal","WeaponFlailVoidMetal");
		register(IrisiaCreativeTabs.equi,Weapons.FlailAdamantium,"weapon_flail_adamantium","WeaponFlailAdamantium");
		register(IrisiaCreativeTabs.equi,Weapons.FlailMithril,"weapon_flail_mithril","WeaponFlailMithril");
		register(IrisiaCreativeTabs.equi,Weapons.FlailSolita,"weapon_flail_solita","WeaponFlailSolita");
		register(IrisiaCreativeTabs.equi,Weapons.FlailMogiga,"weapon_flail_mogiga","WeaponFlailMogiga");
		register(IrisiaCreativeTabs.equi,Weapons.FlailBone,"weapon_flail_bone","WeaponFlailBone");

		register(IrisiaCreativeTabs.equi,Weapons.VoidRunicLongBow,"weapon_void_runic_long_bow","WeaponVoidRunicLongBow");
		register(IrisiaCreativeTabs.equi,Weapons.MercurialBlade,"weapon_mercurial_blade","WeaponMercurialBlade");
	}
	private static void registerTools()
	{
		register(IrisiaCreativeTabs.irisia,Tools.ArmorStorageBox,"tool_armor_storage_box","ToolArmorStorageBox");
	}
//	private static void registerSeeds()
//	{
//		register(IrisiaCreativeTabs.irisia, HerbSeeds.DeathGrassSeed,"seed_death_grass","DeathGrassSeed");
//		register(IrisiaCreativeTabs.irisia, HerbSeeds.MoonGrassSeed,"seed_moon_grass","MoonGrassSeed");
//	}
	protected static void registerItems()
    {
	    registerEquipments();

	    registerWains();

	    registerWands();

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
