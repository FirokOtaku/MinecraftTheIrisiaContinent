package firok.irisia.common;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import firok.irisia.client.KeyLoader;
import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.item.ItemLoader;
import firok.irisia.potion.PotionLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

import static net.minecraft.client.Minecraft.getMinecraft;


@SuppressWarnings("static-method")
public class EventLoader
{
    public EventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }


    private static boolean hasNoticed=false;
    @SubscribeEvent
    public void onPlayerChat(net.minecraftforge.event.ServerChatEvent e)
    {
    	if(Irisia.IN_DEV && hasNoticed)
    		return;

	    try
	    {
		    EntityClientPlayerMP player=Minecraft.getMinecraft().thePlayer;
		    if(player!=null && player.worldObj.isRemote)
		    {
			    player.addChatMessage(new ChatComponentText("警告：你正在使用一份开发版mod，此版本mod仅用于调试之用。"));
			    player.addChatMessage(new ChatComponentText("此版本中出现的任何内容都不会得到保证，请勿将本版本mod用于正式游玩。"));
			    hasNoticed=true;
		    }
	    }
	    catch (Exception e2)
	    {
		    ;
	    }
    }
}