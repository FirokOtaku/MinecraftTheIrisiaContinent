package firok.irisia.item;

import javax.annotation.Nullable;

import firok.irisia.Irisia;
import firok.irisia.common.IrisiaCreativeTabs;
import firok.irisia.mod.tc.Reg;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.*;
import firok.irisia.item.EquipmentSets.EquipmentSet;


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

	public static void registerItems()
    {
    	// 注册的时候 使用静态成员+物品id
        // register(tokenMoneyLow, "token_money_low");
        // register(itemRecipe,"recipe");
        // register(IrisiaCreativeTabs.irisia,functionalRandomTicker,"functional_random_ticker","functionalRandomTicker");

        // register(IrisiaCreativeTabs.irisia,bucketMud,"bucket_mud","bucketMud");
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.AdamantiumSet);
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.MithrilSet);
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.BoneSet);
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.SpectreSet);
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.SolitaSet);
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.MogigaSet);
	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.DwartSteelSet);
	    // register(IrisiaCreativeTabs.irisiaEqui,EquipmentSets.LifeWoodSet); // 已经转移
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





	    register(IrisiaCreativeTabs.irisiaTC,Wands.LifeWoodSet);
	    register(IrisiaCreativeTabs.irisiaTC,Wands.SpectreSet);

	    register(IrisiaCreativeTabs.irisiaMaterial,RawMaterials.Bezoar,Irisia.transName("material","bezoar"));

	    register(IrisiaCreativeTabs.irisiaFood,Foods.VilligerFood,"villiger_food","VilligerFood");

	    register(IrisiaCreativeTabs.irisiaEqui,EquipmentUniqueRings.FortuneRing,"ring_fortune","FortuneRing");
    }
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	// registerRender(tokenMoneyLow);
    	// registerRender(itemRecipe);
    	// registerRender(functionalRandomTicker);
    }

    private static void register(@Nullable CreativeTabs tab,Item item,Irisia.NameObject nb)
    {
    	register(tab,item,nb.textureName,nb.unlocalizedName);
    }
    private static void register(@Nullable CreativeTabs tab,EquipmentSet set)
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
    private static void register(@Nullable CreativeTabs tab,Wands.WandSet set)
    {
    	if(set==null)
    		return;

	    String fuName=Irisia.getFirstUpper(set.materialName);
    	register(tab,set.Cap,"cap_"+set.materialName,fuName+"Cap");
    	register(tab,set.Rod,"rod_"+set.materialName,fuName+"Rod");
    }
    
	private static void register(@Nullable CreativeTabs tab,Item item, String name,String unlocalizedName)
    {
    	if(item==null || name==null || unlocalizedName==null) // 检查要注册的物品是不是空的
    		return;

	    item.setUnlocalizedName(unlocalizedName);
        GameRegistry.registerItem(item,name);
        if(tab!=null)
        	item.setCreativeTab(tab);
        item.setUnlocalizedName(unlocalizedName);
        
        items.add(item);
        
        if(Loader.isModLoaded("Thaumcraft"))
        	registerTcContent(item);
        if(Loader.isModLoaded("Tinkers' Contruct"))
        	registerTicContent(item);
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
 	
    // thaumcraft item register // 神秘时代注册
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
    
    // tinkers' item register // 匠魂注册
	private static void registerTicContent(Item item)
    {
    	boolean isTicItem=false;
    	
    	;
    }
}
