package firok.irisia.potion;

import firok.irisia.Irisia;
import firok.irisia.common.ConfigLoader;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PotionLoader
{
	
	public static final ResourceLocation res = new ResourceLocation(Irisia.MODID + ":" + "textures/gui/potion.png");
	
	
    // public static Potion potionFallProtection;
	
//	public static Potion wise			=new Wise();
//	public static Potion folly			=new Folly();
//	public static Potion spaceUnstable	=new SpaceUnstable();
//	public static Potion indomitable	=new Indomitable();
//	public static Potion avarice		=new Avarice();
//	public static Potion thresholded	=new Thresholded();
//	public static Potion militaristic	=new Militaristic();
//	public static Potion painbound		=new Painbound();
//	public static Potion lifecursed		=new Lifecursed();
//	public static Potion lifeblessed	=new Lifeblessed();
//	public static Potion teared			=new Teared();
//	public static Potion love			=new Love();
//	public static Potion electrostatic	=new Electrostatic();
	

    public PotionLoader(FMLPreInitializationEvent event)
    {
        // potionFallProtection = new PotionFallProtection();
//    	registerPotion(ConfigLoader.idPotionWise,"wise",wise);
//    	registerPotion(ConfigLoader.idPotionFool,"folly",folly);
//    	registerPotion(ConfigLoader.idPotionSpaceUnstable,"space_unstable",spaceUnstable);
//    	registerPotion(ConfigLoader.idPotionIndomitable,"indomitable",indomitable);
//    	registerPotion(ConfigLoader.idPotionAvarice,"avarice",avarice);
//    	registerPotion(ConfigLoader.idPotionThresholded,"thresholded",thresholded);
//    	registerPotion(ConfigLoader.idPotionMilitaristic,"militaristic",militaristic);
//    	registerPotion(ConfigLoader.idPotionPainbound,"painbound",painbound);
//    	registerPotion(ConfigLoader.idPotionLifecursed,"lifecurse",lifecursed);
//    	registerPotion(ConfigLoader.idPotionLifeblessed,"lifeblessed",lifeblessed);
//    	registerPotion(ConfigLoader.idPotionTeared,"teared",teared);
//    	registerPotion(ConfigLoader.idPotionLove,"love",love);
//    	registerPotion(ConfigLoader.idElectrostatic,"electrostatic",electrostatic);
    }
    
    private void registerPotion(int potionIdIn,String potionNameIn,Potion potionIn)
    {
    	// Potion.REGISTRY.register(1, new ResourceLocation("speed"), (new Potion(false, 8171462)).setPotionName("effect.moveSpeed").setIconIndex(0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2).setBeneficial());
//    	Potion.REGISTRY.register(potionIdIn,new ResourceLocation(Irisia.MODID+":"+potionNameIn), potionIn);
//    	potionIn.setPotionName(potionNameIn);
    }
}
