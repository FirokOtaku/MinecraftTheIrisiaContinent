package firok.irisia.common;

import firok.irisia.achievement.PageIrisia;
import firok.irisia.block.BlockLoader;
import firok.irisia.command.CommandLoader;
import firok.irisia.crafting.CraftingLoader;
import firok.irisia.creativetab.CreativeTabsLoader;
import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.entity.EntityLoader;
import firok.irisia.fluid.FluidLoader;
import firok.irisia.inventory.GuiElementLoader;
import firok.irisia.item.ItemLoader;
import firok.irisia.mod.tc.research.*;
import firok.irisia.potion.PotionLoader;
import firok.irisia.world.dim.*;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.event.*;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
    	System.out.println("Ahh, finally, you come here, adventurer!");
	    System.out.println("Here is the new world of Thaumic!");

	    // class.forName("");

//    	new ConfigLoader(event);
//    	new CreativeTabsLoader(event);
//    	new ItemLoader(event);
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