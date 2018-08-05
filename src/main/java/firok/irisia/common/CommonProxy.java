package firok.irisia.common;

import cpw.mods.fml.common.event.*;
import firok.irisia.Irisia;
import firok.irisia.block.BlockLoader;
import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.item.*;
import org.apache.logging.log4j.Level;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    	System.out.println("Ahh, finally, you come here!");
	    System.out.println("Here is the new world of Thaumic!");

	    if(Irisia.IN_DEV)
	    {
		    event.getModLog().log(Level.WARN,
				    "Warning：You are using an in-dev-version mod which is just used for testing.\n" +
						    "Any contents in this mod could be unstable and they will not be guaranteed.\n" +
						    "DO NOT use this mod in formal gaming");
	    	event.getModLog().log(Level.WARN,
				    "警告：你正在使用一份开发版mod，此版本mod仅用于调试之用。\n" +
				    "此版本中出现的任何内容都不会得到保证，请勿将本版本mod用于正式游玩。");
	    }

//    	new ConfigLoader(event);
    	IrisiaCreativeTabs.load(event);
    	new ItemLoader(event);
//    	new FluidLoader(event);
    	new BlockLoader(event);
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
    	new EnchantmentLoader();
//
    	new EventLoader();
//
//    	// TODO
//    	//
//    	new firok.irisia.mod.tc.aspect.AspectLoader();
//    	new firok.irisia.mod.tc.research.CateLoader();
		firok.irisia.common.TcContent.init();
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