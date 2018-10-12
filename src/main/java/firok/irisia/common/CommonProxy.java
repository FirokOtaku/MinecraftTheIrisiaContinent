package firok.irisia.common;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.*;
import firok.irisia.Irisia;
import firok.irisia.SomeCodes;
import firok.irisia.block.BlockLoader;
import firok.irisia.inventory.GuiElementLoader;
import firok.irisia.item.*;
import firok.irisia.potion.PotionLoader;
import firok.irisia.tileentity.TileEntityLoader;
import firok.irisia.world.IrisiaWorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import static firok.irisia.common.ConfigLoader.idDim;

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

    	ConfigLoader.initConfig(event);
    	IrisiaCreativeTabs.load(event);

	    new PotionLoader(event);
    	new ItemLoader(event);
//    	new FluidLoader(event);
    	new BlockLoader(event);
//
//    	// TODO
//    	AchievementPage.registerAchievementPage(new PageIrisia());


//		new StructureTFMajorFeatureStart();
//// just call this so that we register structure IDs correctly
//
//    	// check for biome conflicts, load biomes
//		TFBiomeBase.assignBlankBiomeIds();
//		if (ConfigLoader.hasAssignedBiomeID) {
//			FMLLog.info("[TwilightForest] Twilight Forest mod has auto-assigned some biome IDs.  This will break any existing Twilight Forest saves.");
//			saveBiomeIds(null);
//		}
//		ConfigLoader.hasBiomeIdConflicts = TFBiomeBase.areThereBiomeIdConflicts();



//    	new OreDictionaryLoader(event);
//
//        new EntityLoader();
	    new TileEntityLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
    	HerbSeeds.postInit(); // 防止交叉引用初始化可能导致的问题

    	new CraftingLoader();
	    EnchantmentLoader.info();
//
    	new EventLoader();
    	//new firok.irisia.world.WorldLoader();
//
//    	// TODO
//    	//
//    	new firok.irisia.mod.tc.aspect.AspectLoader();
//    	new firok.irisia.mod.tc.research.CateLoader();
		firok.irisia.common.TcContent.init();
    	new GuiElementLoader();

	    // dimension provider
	    DimensionManager.registerProviderType(ConfigLoader.dimensionProviderID, IrisiaWorldProvider.class, false);

	    DimensionManager.registerDimension(idDim,ConfigLoader.dimensionProviderID);

	    // enter biomes into dictionary
	    //TFBiomeBase.registerWithBiomeDictionary();

	    GashaponManager.init();

	    LootManager.init();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
//    	new DimentionLoader();


	    // register dimension with Forge
	    if (!DimensionManager.isDimensionRegistered(idDim))
	    {
		    DimensionManager.registerDimension(idDim, ConfigLoader.dimensionProviderID);
	    }
	    else
	    {
		    FMLLog.warning("[Irisia] Irisia detected that the configured dimension id '%d' is being used.  Using backup ID.  It is recommended that you configure this mod to use a unique dimension ID.", idDim);
		    FMLLog.warning("[Irisia] Irisia 发现维度id '%d' 已经被占用.  已经改用备用维度id.  推荐为本mod使用一个唯一的维度id.", idDim);
		    DimensionManager.registerDimension(ConfigLoader.backupdimensionID, ConfigLoader.dimensionProviderID);
		    idDim = ConfigLoader.backupdimensionID;
	    }

	    if(Irisia.IN_DEV)
	    {
		    // SomeCodes.armorTexture();
	    }
    }
    
    public void serverStarting(FMLServerStartingEvent event)
    {
    	new CommandLoader(event);
    }

	private void saveBiomeIds(Configuration config) {
		config.get("biome", "biome.id.Lake", -1).set(ConfigLoader.idBiomeCrystalForest);

		config.save();
	}
}