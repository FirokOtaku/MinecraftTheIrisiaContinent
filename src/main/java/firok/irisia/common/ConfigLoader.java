package firok.irisia.common;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ConfigLoader
{
    private static Configuration config;

    private static Logger logger;

    // public static int a=config.get(Configuration.CATEGORY_GENERAL, "diamondBurnTime", 100).getInt();

    // general // 全局
    public static int minEffectRadius=0;
    public static int incrementEffectRadius=1;
    public static int maxEffectRadius=3;
    public static float accumulatingProbability=0.4F;
    
    // Buff // 状态效果相关
    public static int idPotionWise=50;
    
    public static float percentDamageIncreasementPotionWise=2.0f;
    
    public static int idPotionFool=51;
    
    public static float percentDamageDiscreasementPotionFool=0.0f;
    
    public static int idPotionSpaceUnstable=52;
    
    public static int idPotionIndomitable=53;
    
    public static int idPotionAvarice=54;
    
    public static int idPotionThresholded=55;
    
    public static int idPotionMilitaristic=56;
    
    public static int idPotionPainbound=57;
    
    public static int idPotionLifecursed=58;
    
    public static int idPotionLifeblessed=59;
    
    public static int idPotionTeared=60;
    
    public static int idPotionLove=61;
    
    public static int idElectrostatic=62;
    
    // Enchantment // 附魔相关
    public static int idEnchantmentCulling=65;
    
    public static int idEnchantmentTearing=66;

    public static int idEnchantmentInscriptionCapacity=-1;

    public static int idEnchantmentShadowy=68;

    public static int idEnchantmentEnderGuarding=69;

    public static int idEnchantmentMagicProtection=70;
    
    // Item // 物品相关
    public static int itemStoneOfCoronaExplosionRadius=20;
    public static int itemStoneOfCoronaFuelTime=2000;
    
    ; // 活化之光
    
    // Mushroom // 蘑菇相关
    public static int mushroomEnderMushroomHorizontalRange=10;
    public static int mushroomEnderMushroomVerticalRange=3;
    public static int decorationEnderBlockHorizontalRange=5;
    
    // Grass // 草相关
    public static float grassElasticFernElasticity=5;
    // Flower // 花相关
    public static int flowerToxicFlowerEffectRadius=4;
    
    public static int defaultCorroseSpeed=8; // 默认腐蚀速度
    public static int flowerCorrosiveFlowerCorroseSpeed=2;
    public static int flowerCorrosiveFlowerCorroseRadius=4;
    
    public static int flowerWoodenRootFlowerGrowDepth=3;
    
    public static int flowerWiserFlowerXPAmount=8;

	public static int flowerFrostyLotusFreezeRadius=4;
    public static int flowerFrostyLotusFreezeSpeed=8;
    
    // Tree // 树相关
    public static int treeIronyWoodTotalGrowthStage=8;
    public static int treeIronyWoodAcceleratedByIronBlock=2;
    public static int treeIronyWoodAcceleratedByIronOre=1;
    
    // Display // 显示相关
    public static float displayParticleDencity;
    

    public ConfigLoader(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();
        load();
    }


    public static void load()
    {
        logger.info("Started loading config. ");
        // String comment;

        // comment = "How many seconds can a diamond burn in a furnace. ";
        // diamondBurnTime = config.get(Configuration.CATEGORY_GENERAL, "diamondBurnTime", 640, comment).getInt();

        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger()
    {
        return logger;
    }
}