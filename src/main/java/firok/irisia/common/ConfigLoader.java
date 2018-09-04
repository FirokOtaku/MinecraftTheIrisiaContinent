package firok.irisia.common;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ConfigLoader
{
    private static Configuration config;

    private static Logger logger;
    // general // 全局
    ;
    
    // Buff // 状态效果相关
    ;
    
    // Enchantment // 附魔相关
    public static int ideCulling;
    public static int ideGuarding;
    public static int ideFlown;
    public static int ideShadowy;
    public static int ideElastic;

    public static int idewCulling;
    public static int idewEnderGuarding;
    public static int idewFlown;
    public static int idewShadowy;
    public static int idewElastic;

    // 还没加载
    public static int idEnchantmentTearing;
    public static int idEnchantmentMagicProtection;
    
    // Item // 物品相关
    public static int itemStoneOfCoronaExplosionRadius=20;
    public static int itemStoneOfCoronaFuelTime=2000;
    
    ; // 活化之光
    
    // Mushroom // 蘑菇相关
    ;
    
    // Grass // 草相关
    //public static float grassElasticFernElasticity=5;
    // Flower // 花相关
    //public static int flowerToxicFlowerEffectRadius=4;
    
    // Tree // 树相关
    ;
    
    // Display // 显示相关
    public static float displayParticleDencity;

    public static void initConfig(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();

        logger.info("Starting loading configs 开始加载配置文件");

        initConfigEnchantments();

        initConfigDisplay();

        config.save();
        logger.info("Finished loading configs 配置文件加载完成");
    }

    private static void initConfigEnchantments()
    {
        ideCulling =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantment_Culling", 161,
                "淘汰附魔的id").getInt();
        ideGuarding =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantment_Guarding", 162,
                "末影守卫附魔的id").getInt();
        ideFlown =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantment_Flown", 163,
                "击飞附魔的id").getInt();
        ideShadowy =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantment_Shadowy", 164,
                "暗影附魔的id").getInt();
        ideElastic =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantment_Elastic", 165,
                "弹性附魔的id").getInt();

        idewCulling =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantmentWeight_Culling", 10,
                "淘汰附魔的比重").getInt();
        idewEnderGuarding =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantmentWeight_EnderGuarding", 10,
                "末影守卫附魔的比重").getInt();
        idewFlown =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantmentWeight_Flown", 10,
                "击飞附魔的比重").getInt();
        idewShadowy =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantmentWeight_Shadowy", 10,
                "暗影附魔的比重").getInt();
        idewElastic =config.get(Configuration.CATEGORY_GENERAL,
                "idEnchantmentWeight_Elastic", 10,
                "弹性附魔的比重").getInt();
    }
    private static void initConfigDisplay()
    {
        displayParticleDencity=config.getFloat(Configuration.CATEGORY_GENERAL,
                "displayParticleDencity",
                50f,0f,100f,
                "客户端属性:粒子密度");
    }


    public static Logger logger()
    {
        return logger;
    }


}