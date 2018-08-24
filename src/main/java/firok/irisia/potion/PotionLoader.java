package firok.irisia.potion;

import firok.irisia.Irisia;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.Utils;

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
        Logger log=event.getModLog();
        int customPotions = 10;
        int potionOffset = Potion.potionTypes.length;
        int start = 0;
        log.info("自定义药水种类 : " + potionOffset);

        if (potionOffset < 128 - customPotions) {
            log.info("扩张药水列表长度至 : " + (potionOffset + customPotions));
            Potion[] potionTypes = new Potion[potionOffset + customPotions];
            System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, potionOffset);
            Utils.setPrivateFinalValue((Class)Potion.class, (Object)null, potionTypes, new String[]{"potionTypes", "field_76425_a", "a"});
            start = potionOffset++ - 1;
        } else {
            start = -1;
        }


        // love
        if ((start= getNextPotionId(start)) >= 0) {
            int potionLoveID = start;
            Potions.love = new Potions.Love(potionLoveID);
            log.info("Initializing PotionLove with id " + start);
        }
    }
    private static int getNextPotionId(int start)
    {
        if (Potion.potionTypes != null && start > 0 && start < Potion.potionTypes.length && Potion.potionTypes[start] == null)
        {
            return start;
        }
        else
        {
            ++start;
            if (start < 128) {
                start = getNextPotionId(start);
            } else {
                start = -1;
            }

            return start;
        }
    }
}
