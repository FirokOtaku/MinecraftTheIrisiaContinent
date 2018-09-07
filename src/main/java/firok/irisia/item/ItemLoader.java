package firok.irisia.item;

import javax.annotation.Nullable;

import firok.irisia.Irisia;
import firok.irisia.block.HerbsAndMushroom;
import firok.irisia.common.IrisiaCreativeTabs;
import firok.irisia.mod.tc.Reg;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.*;

import firok.irisia.item.EquipmentSets.EquipmentSet;
import net.minecraft.item.ItemSeeds;


@SuppressWarnings({"unused","deprecation"})
public class ItemLoader
{
	private static LinkedList<Item> items;
	private static LinkedList<Item> itemsTc;
	
	public ItemLoader(FMLPreInitializationEvent event)
    {
        items=new LinkedList<Item>();
        itemsTc=new LinkedList<Item>();
        
    	registerItems();
    	registerRenders();
    }
	
	// static 非本地化名称= new 类名
    // public static Item itemRecipe=new ItemRecipe();
	// public static Item functionalRandomTicker=new FunctionalRandomTicker();

	// public static Item bucketMud=new BucketMud();
	private static void registerRawMaterials()
	{
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.ShardOfLove,"material_shard_of_love","ShardOfLove");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.Bezoar,"material_bezoar","Bezoar");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.IcyGel,"material_icy_gel","IcyGel");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.WolfFur,"material_wolf_fur","WolfFur");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.IcyWolfFur,"material_icy_wolf_fur","IcyWolfFur");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.ScorchingWolfFur,"material_scorching_wolf_fur","ScorchingWolfFur");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.HeavyCashmere,"material_heavy_cashmere","HeavyCashmere");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.TortoiseShell,"material_tortoise_shell","TortoiseShell");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.BoneShard,"material_bone_shard","BoneShard");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.CasinosBadge,"material_casinos_badge","CasinosBadge");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.UnicornHorn,"material_unicorn_horn","UnicornHorn");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.UnicornBlood,"material_unicorn_blood","UnicornBlood");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.SlimeCore,"material_slime_core","SlimeCore");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.BrownWheat,"material_brown_wheat","BrownWheat");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.SoulCrystal,"material_soul_crystal","SoulCrystal");


		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.DwartFlour,"material_dwart_flour","DwartFlour");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.ElfStone,"material_elf_stone","ElfStone");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.FlumetalIngot,"material_flumetal_ingot","FlumetalIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.SolitaIngot,"material_solita_ingot","SolitaIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.MogigaIngot,"material_mogiga_ingot","MogigaIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.DwartSteelIngot,"material_dwart_steel_ingot","DwartSteelIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.MithrilIngot,"material_mithril_ingot","MithrilIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.AdamantiumIngot,"material_adamantium_ingot","AdamantiumIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.SlimeIngot,"material_slime_ingot","SlimeIngot");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.InvalidRunicRing,"material_invalid_runic_ring","InvalidRunicRing");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.LostPage,"material_lost_page","LostPage");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.GoldenSilk,"material_golden_silk","GoldenSilk");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.DiamondSilk,"material_diamond_silk","DiamondSilk");
		register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.MultiCoreBrain,"material_multicore_brain","MultiCoreBrain");
	}
	private static void registerEquipments()
	{
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.AdamantiumSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.MithrilSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.BoneSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.SpectreSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.SolitaSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.MogigaSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.DwartSteelSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.WindRangerSet);
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.DwartMinerSet);
		// registerLoot(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.LifeWoodSet); // 已经转移
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.FlumetalSet);
		// 注册生命木套装
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodSword,"sword_lifewood","LifewoodSword");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodHelmet,"helmet_lifewood","LifewoodHelmet");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodChestplate,"chestplate_lifewood","LifewoodChestplate");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodLeggings,"leggings_lifewood","LifewoodLeggings");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodBoots,"boots_lifewood","LifewoodBoots");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodAxe,"axe_lifewood","LifewoodAxe");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodPickaxe,"pickaxe_lifewood","LifewoodPickaxe");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodHoe,"hoe_lifewood","LifewoodHoe");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.LifewoodSpade,"spade_lifewood","LifewoodSpade");
		// 注册史莱姆套装
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.SlimeHelmet,"helmet_slime","SlimeHelmet");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.SlimeChestplate,"chestplate_slime","SlimeChestplate");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.SlimeLeggings,"leggings_slime","SlimeLeggings");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentAutoRepair.SlimeBoots,"boots_slime","SlimeBoots");
	}
	private static void registerWains()
	{
		// 北斗
		register(IrisiaCreativeTabs.irisiaEqui,WainItems.AliothTheInfinity,"wain_alioth","WainAlioth");

	}
	private static void registerWands()
	{
		// 法杖套装
		register(IrisiaCreativeTabs.irisiaTC,Wands.LifeWoodSet);
		register(IrisiaCreativeTabs.irisiaTC,Wands.SpectreSet);
		register(IrisiaCreativeTabs.irisiaTC,Wands.ItemNodeRod,"rod_node","NodeRod");
		register(IrisiaCreativeTabs.irisiaTC,Wands.AdamantiumSet);
		register(IrisiaCreativeTabs.irisiaTC,Wands.MithrilSet);
		register(IrisiaCreativeTabs.irisiaTC,Wands.SolitaSet);
		register(IrisiaCreativeTabs.irisiaTC,Wands.MogigaSet);
	}
	private static void registerFoods()
	{
		// 食物
		register(IrisiaCreativeTabs.irisiaFood,Foods.VilligerFood,"villiger_food","VilligerFood");
		register(IrisiaCreativeTabs.irisiaFood,Foods.MixedSweet,"mixed_sweet","MixedSweet");
		register(IrisiaCreativeTabs.irisiaFood,Foods.BerryNull,"berry_null","NullBerry");

	}
	private static void registerBaubles()
	{
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.FortuneRing,"ring_fortune","FortuneRing");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.MinersRing,"ring_miner","MinersRing");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.ThrivingRing,"ring_thriving","ThrivingRing");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.InsaneRing,"ring_insane","InsaneRing");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.LucidRing,"ring_lucid","LucidRing");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.LoveRing,"ring_love","LoveRing");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.PhotosynthesisAmulet,"ring_photosynthesis","PhotosynthesisAmulet");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.DwartTravellerBelt,"belt_dwart_traveller","DwartTravellerBelt");
		register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueBaubles.TwelveMagicalPowerAmulet,"amulet_twelve_magical","TwelveMagicalAmulet");

	}
	private static void registerConsumables()
	{
		// 消耗品
		register(IrisiaCreativeTabs.irisia,Consumables.Gashapon,"cons_gashapon","Gashapon");

	}
	private static void registerConsumableWeapons()
	{
		register(IrisiaCreativeTabs.irisia,ConsumableWeapons.ThrowableWeapons,"consw_tws","ThrowableWeapons");
	}
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

//	    registerSeeds();
    }
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	// registerRender(tokenMoneyLow);
    	// registerRender(itemRecipe);
    	// registerRender(functionalRandomTicker);
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
        
        // items.add(item);
        
//        if(Loader.isModLoaded("Thaumcraft"))
//        	registerTcContent(item);
//        if(Loader.isModLoaded("Tinkers' Contruct"))
//        	registerTicContent(item);

    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
//        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
//        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item,int meta)
    {
//        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
//        ModelLoader.setCustomModelResourceLocation(item, meta, model);
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
