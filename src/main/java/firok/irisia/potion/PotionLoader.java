package firok.irisia.potion;

import firok.irisia.Irisia;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import thaumcraft.common.lib.utils.Utils;

import java.awt.*;

public class PotionLoader
{
	
	public static final ResourceLocation res = new ResourceLocation(Irisia.MODID + ":" + "textures/gui/potion.png");

    public PotionLoader(FMLPreInitializationEvent event)
    {
        Logger log=event.getModLog();
        int customPotions = 20; // 现在是21个
        int potionOffset = Potion.potionTypes.length;
        int start = 0;
        log.info("药水id偏移 : " + potionOffset);

        if (potionOffset < 128 - customPotions) {
            log.info("扩张药水列表长度至 : " + (potionOffset + customPotions));
            Potion[] potionTypes = new Potion[potionOffset + customPotions];
            System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, potionOffset);
            Utils.setPrivateFinalValue((Class)Potion.class, (Object)null, potionTypes, new String[]{"potionTypes", "field_76425_a", "a"});
            start = potionOffset++ - 1;
        } else {
            start = -1;
        }

        // Love
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Love = new Potions.PotionLove(start);
            log.info("Initializing PotionLove with id " + start);
        }
        // Cold
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Cold = new Potions.PotionCold(start);
            log.info("Initializing PotionCold with id " + start);
        }
        // Space Unstable
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.SpaceUnstable = new Potions.PotionSpaceUnstable(start);
            log.info("Initializing PotionSpaceUnstable with id " + start);
        }
        // Vis Leaking
        if((start=getNextPotionId(start))>=0){
            Potions.VisLeaking=new Potions.PotionVisLeaking(start);
            log.info("Initializing PotionVisLeaking with id " + start);
        }
        // Corroded
        if((start=getNextPotionId(start))>=0){
            Potions.Corroded=new Potions.PotionCorroded(start);
            log.info("Initializing PotionCorroded with id " + start);
        }
        // Cursed
        if((start=getNextPotionId(start))>=0){
            Potions.Cursed=new Potions.PotionCursed(start);
            log.info("Initializing PotionCursed with id " + start);
        }
        // Militaristic
        if((start=getNextPotionId(start))>=0){
            Potions.Militaristic=new Potions.PotionMilitaristic(start);
            log.info("Initializing PotionMilitaristic with id " + start);
        }
        // Plague
        if((start=getNextPotionId(start))>=0){
            Potions.Plaguing=new Potions.PotionPlaguing(start);
            log.info("Initializing PotionPlaguing with id " + start);
        }
        // Healing
        if((start=getNextPotionId(start))>=0){
            Potions.Healing=new Potions.PotionHealing(start);
            log.info("Initializing PotionHealing with id " + start);
        }


        // Wise
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Wise = new Potions.EventPotion(start,false,Color.cyan.getRGB(),"wise");
            log.info("Initializing PotionWise with id " + start);
        }
        // Folly
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Folly = new Potions.EventPotion(start,false,Color.red.getRGB(),"folly");
            log.info("Initializing PotionFolly with id " + start);
        }
        // Magic Amplificative
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.MagicAmplificative = new Potions.EventPotion(start,false,Color.GREEN.getRGB(),"magic_amplificative");
            log.info("Initializing PotionMagicAmplificative with id " + start);
        }
        // Magic Resistance
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.MagicResistance = new Potions.EventPotion(start,false,Color.GREEN.getRGB(),"magic_resistance");
            log.info("Initializing PotionMagicResistance with id " + start);
        }
        // Thresholded
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Thresholded = new Potions.EventPotion(start,false,Color.YELLOW.getRGB(),"thresholded");
            log.info("Initializing PotionThresholded with id " + start);
        }
        // Ethereal
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Ethereal = new Potions.EventPotion(start,false,Color.green.getRGB(),"ethereal");
            log.info("Initializing PotionEthereal with id " + start);
        }
        // Ninjia
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Ninjia = new Potions.EventPotion(start,false,Color.gray.getRGB(),"ninjia");
            log.info("Initializing PotionNinjia with id " + start);
        }
        // WindRanger
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.WindRanger = new Potions.EventPotion(start,false,Color.green.getRGB(),"windranger");
            log.info("Initializing PotionWindRanger with id " + start);
        }
        // Leaderly
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Leaderly = new Potions.EventPotion(start,false,Color.white.getRGB(),"leadely");
            log.info("Initializing PotionLeaderly with id " + start);
        }
        // Midas
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Midas = new Potions.EventPotion(start,true,Color.yellow.getRGB(),"midas");
            log.info("Initializing PotionMidas with id " + start);
        }
        // Echo
        if ((start= getNextPotionId(start)) >= 0) {
            Potions.Echo = new Potions.EventPotion(start,true,Color.yellow.getRGB(),"echo");
            log.info("Initializing PotionEcho with id " + start);
        }
//        // Fantasy
//        if ((start= getNextPotionId(start)) >= 0) {
//            Potions.Fantasy = new Potions.EventPotion(start,true,Color.green.getRGB(),"fantasy");
//            log.info("Initializing PotionFantasy with id " + start);
//        }
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
