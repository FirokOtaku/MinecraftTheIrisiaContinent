package firok.irisia.common;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ConfigLoader
{
    private static Configuration config;

    private static Logger logger;

    // general // 全局
    public static int minEffectRadius=0;
    public LangItem LIminEffectRadius=new LangItem(
    		"各种效果的最小影响半径",
    		"");
    public static int incrementEffectRadius=1;
    public LangItem LIincrementEffectRadius=new LangItem(
    		"各种效果每次因为积累而增加的半径值",
    		"");
    public static int maxEffectRadius=3;
    public LangItem LImaxEffectRadius=new LangItem(
    		"各种效果的最大影响半径",
    		"");
    public static float accumulatingProbability=0.4F;
    public LangItem LIaccumulatingProbability=new LangItem(
    		"各种效果积累的概率",
    		"");
    
    // Buff // 状态效果相关
    public static int idPotionWise=50;
    public LangItem LIidPotionWise=new LangItem(
    		"智者状态效果的id",
    		"");
    
    public static float percentDamageIncreasementPotionWise=2.0f;
    public LangItem LIpercentDamageIncreasementPotionWise=new LangItem(
    		"智者状态下伤害增量百分比",
    		"");
    
    public static int idPotionFool=51;
    public LangItem LIidPotionFool=new LangItem(
    		"愚者状态效果的id",
    		"");
    
    public static float percentDamageDiscreasementPotionFool=0.0f;
    public LangItem LIpercentDamageDiscreasementPotionFool=new LangItem(
    		"愚者状态下伤害减量百分比",
    		"");
    
    public static int idPotionSpaceUnstable=52;
    public LangItem LIidPotionSpaceUnstable=new LangItem(
    		"空间不稳定状态效果的id",
    		"");
    
    public static int idPotionIndomitable=53;
    public LangItem LIidPotionIndomitable=new LangItem(
    		"不屈状态效果的id",
    		"");
    
    public static int idPotionAvarice=54;
    public LangItem LIidPotionAvarice=new LangItem(
    		"贪婪状态效果的id",
    		"");
    
    public static int idPotionThresholded=55;
    public LangItem LIidPotionThresholded=new LangItem(
    		"阈化状态效果的id",
    		"");
    
    public static int idPotionMilitaristic=56;
    public LangItem LIidPotionMilitaristic=new LangItem(
    		"黩武状态效果的id",
    		"");
    
    public static int idPotionPainbound=57;
    public LangItem LIidPotionPainbound=new LangItem(
    		"痛苦链接状态效果的id",
    		"");
    
    public static int idPotionLifecursed=58;
    public LangItem LIidPotionLifecursed=new LangItem(
    		"生命诅咒状态效果的id",
    		"");
    
    public static int idPotionLifeblessed=59;
    public LangItem LIidPotionLifeblessed=new LangItem(
    		"生命祝福状态效果的id",
    		"");
    
    public static int idPotionTeared=60;
    public LangItem LIidPotionTeared=new LangItem(
    		"被撕裂状态效果的id",
    		"");
    
    public static int idPotionLove=61;
    public LangItem LIidPotionLove=new LangItem(
    		"爱情状态效果的id",
    		"");
    
    public static int idElectrostatic=62;
    public LangItem LIidElectrostatic=new LangItem(
    		"静电状态效果的id",
    		"");
    
    // Enchantment // 附魔相关
    public static int idEnchantmentCulling=65;
    public LangItem LIidEnchantmentCulling=new LangItem(
    		"淘汰附魔的id",
    		"");
    
    public static int idEnchantmentTearing=66;
    public LangItem LIidEnchantmentTearing=new LangItem(
    		"撕裂附魔的id",
    		"");
    
    // Item // 物品相关
    public static int itemStoneOfCoronaExplosionRadius=20;
    public LangItem LIitemStoneOfCoronaExplosionPower=new LangItem(
    		"日冕之石的爆炸威力(并不是简单的爆炸半径)",
    		"");
    public static int itemStoneOfCoronaFuelTime=2000;
    public LangItem LIitemStoneOfCoronaFuelTime=new LangItem(
    		"日冕之石的燃烧时间",
    		"");
    
    ; // 活化之光
    
    // Mushroom // 蘑菇相关
    public static int mushroomEnderMushroomHorizontalRange=10;
    public LangItem LImushroomEnderMushroomHorizontalRange=new LangItem(
    		"末影菇横向传送距离",
    		"");
    public static int mushroomEnderMushroomVerticalRange=3;
    public LangItem LImushroomEnderMushroomVerticalRange=new LangItem(
    		"末影菇纵向传送距离",
    		"");
    public static int decorationEnderBlockHorizontalRange=5;
    public LangItem LIdecorationEnderBlockHorizontalRange=new LangItem(
    		"末影块横向传送距离",
    		"");
    
    // Grass // 草相关
    public static float grassElasticFernElasticity=5;
    public LangItem LIgrassElasticFernElasticity=new LangItem(
    		"弹力蕨的弹力","");
    
    // Flower // 花相关
    public static int flowerToxicFlowerEffectRadius=4;
    public LangItem LIflowerToxicFlowerEffectRadius=new LangItem(
    		"毒气花影响半径",
    		"");
    
    public static int defaultCorroseSpeed=8; // 默认腐蚀速度
    public static int flowerCorrosiveFlowerCorroseSpeed;
    public LangItem LIflowerCorrosiveFlowerCorroseSpeed=new LangItem(
    		"腐蚀之花腐蚀速度",
    		"");
    public static int flowerCorrosiveFlowerCorroseRadius=4;
    public LangItem LIflowerCorrosiveFlowerCorroseRadius=new LangItem(
    		"腐蚀之花腐蚀半径",
    		"");
    
    public static int flowerWoodenRootFlowerGrowDepth=3;
    public LangItem LIflowerWoodenRootFlowerGrowDepth=new LangItem(
    		"木根花生长深度",
    		"");
    
    public static int flowerWiserFlowerXPAmount=8;
    public LangItem LIflowerWiserFlowerXPAmount=new LangItem(
    		"智者花每次掉落的经验数量",
    		"");
    
    public static int flowerFrostyLotusFreezeRadius=4;
    public LangItem LIflowerFrostyLotusFreezeRadius=new LangItem(
    		"霜冻雪莲冻结半径",
    		"");
    public static int flowerFrostyLotusFreezeSpeed=8;
    public LangItem LIflowerFrostyLotusFreezeSpeed=new LangItem(
    		"霜冻雪莲冻结周围方块的速度",
    		"");
    
    // Tree // 树相关
    public static int treeIronyWoodTotalGrowthStage=8;
    public LangItem LItreeIronyWoodTotalGrowthStage=new LangItem(
    		"铁质木最大生长阶段",
    		"");
    public static int treeIronyWoodAcceleratedByIronBlock=2;
    public LangItem LItreeIronyWoodAcceleratedByIronBlock=new LangItem(
    		"铁块对于铁质木生长的加速",
    		"");
    public static int treeIronyWoodAcceleratedByIronOre=1;
    public LangItem LItreeIronyWoodAcceleratedByIronOre=new LangItem(
    		"铁矿石对于铁质木生长的加速",
    		"");
    
    // Display // 显示相关
    public static float displayParticleDencity;
    public LangItem LIdisplayParticleDencity=new LangItem(
    		"客户端粒子显示浓度系数(最低0.3,最高2.0)",
    		"");
    

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
    
    // 用来记录中英文信息的内部类
    protected class LangItem
    {
    	public String zh;
    	public String en;
    	public LangItem()
    	{
    		zh=en=new String("");
    	}
    	public LangItem(String zhIn,String enIn)
    	{
    		zh=zhIn;
    		en=enIn;
    	}
    }
}