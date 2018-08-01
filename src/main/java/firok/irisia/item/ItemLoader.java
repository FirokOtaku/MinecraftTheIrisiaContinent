package firok.irisia.item;

import javax.annotation.Nullable;

import firok.irisia.Util;
import firok.irisia.mod.tc.Reg;
import firok.irisia.mod.tc.Reg.*;
import firok.irisia.creativetab.CreativeTabsLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.*;


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
        // register(CreativeTabsLoader.irisia,functionalRandomTicker,"functional_random_ticker","functionalRandomTicker");

        // register(CreativeTabsLoader.irisia,bucketMud,"bucket_mud","bucketMud");
    }
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	// registerRender(tokenMoneyLow);
    	// registerRender(itemRecipe);
    	// registerRender(functionalRandomTicker);

    }
    
    
	private static void register(@Nullable CreativeTabs tab,Item item, String name,String unlocalizedName)
    {
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
