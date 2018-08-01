package firok.irisia.common;

import cpw.mods.fml.common.event.*;
import firok.irisia.item.*;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    	System.out.println("Ahh, finally, you come here, adventurer!");
	    System.out.println("Here is the new world of Thaumic!");


	    // class.forName("");
//        try
//        {
//            Class.forName("firok.irisia.DamageSources");
//            Class.forName("firok.irisia.item.Materials");
//
//            Class.forName("firok.irisia.block.Decorations");
//            Class.forName("firok.irisia.block.HerbsAndMushroom");
//            Class.forName("firok.irisia.block.OresAndMetal");
//
//            Class.forName("firok.irisia.item.Equipments");
//            Class.forName("firok.irisia.item.RawMaterials");
//        }
//        catch(ClassNotFoundException cnfe)
//        {
//            cnfe.printStackTrace();
//            FMLLog.log(Level.FATAL,"一些重要的类无法加载");
//            throw new RuntimeException(cnfe);
//        }
//    	new ConfigLoader(event);
    	IrisiaCreativeTabs.load(event);
    	new ItemLoader(event);
//    	new FluidLoader(event);
//    	new BlockLoader(event);
//
//    	// TODO
//    	AchievementPage.registerAchievementPage(new PageIrisia());
//
//    	new OreDictionaryLoader(event);
//    	new PotionLoader(event);
//
//        new EntityLoader();
    }

    public void init(FMLInitializationEvent event)
    {
//    	new CraftingLoader();
//    	new EnchantmentLoader();
//
//    	new EventLoader();
//
//    	// TODO
//    	//
//    	new firok.irisia.mod.tc.aspect.AspectLoader();
//    	new firok.irisia.mod.tc.research.CateLoader();
//
//    	new GuiElementLoader();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
//    	new DimentionLoader();
    }
    
    public void serverStarting(FMLServerStartingEvent event)
    {
//    	new CommandLoader(event);
    }
}