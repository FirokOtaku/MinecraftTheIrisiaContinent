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
        int customPotions = 20;
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
