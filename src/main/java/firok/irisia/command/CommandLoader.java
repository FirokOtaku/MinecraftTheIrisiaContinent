package firok.irisia.command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.potion.PotionLoader;
import firok.irisia.potion.Potions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandLoader
{
    public CommandLoader(FMLServerStartingEvent event)
    {
//        event.registerServerCommand(new Ench());
//        event.registerServerCommand(new Totem());
//        event.registerServerCommand(new WarpSeer());
//        event.registerServerCommand(new EffectPotion());
	    // show
	    event.registerServerCommand(new ICommand()
	    {
		    @Override
		    public String getCommandName()
		    {
			    return "show";
		    }

		    @Override
		    public String getCommandUsage(ICommandSender p_71518_1_)
		    {
			    return "use this cmd to test game";
		    }

		    @Override
		    public List getCommandAliases()
		    {
			    return new LinkedList();
		    }

		    @Override
		    public void processCommand(ICommandSender sender, String[] args)
		    {
				try
				{
					EntityPlayer player=(EntityPlayer)sender;
					if(player.worldObj.isRemote)
						return;

					ItemStack held=player.getHeldItem();
					Item item=held.getItem();
					String str=item.getClass().getName();
					player.addChatComponentMessage(new ChatComponentText(str));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		    }

		    @Override
		    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		    {
			    return true;
		    }

		    @Override
		    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		    {
			    return new LinkedList();
		    }

		    @Override
		    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		    {
			    return false;
		    }

		    @Override
		    public int compareTo(Object o)
		    {
			    return 0;
		    }
	    });

	    // add_exp
	    event.registerServerCommand(new ICommand()
	    {
		    @Override
		    public String getCommandName()
		    {
			    return "add_exp";
		    }

		    @Override
		    public String getCommandUsage(ICommandSender p_71518_1_)
		    {
			    return "add exp to juices";
		    }

		    @Override
		    public List getCommandAliases()
		    {
			    return new LinkedList();
		    }

		    @Override
		    public void processCommand(ICommandSender sender, String[] args)
		    {
			    try
			    {
				    EntityPlayer player=(EntityPlayer)sender;
				    if(player.worldObj.isRemote)
					    return;

				    ItemStack held=player.getHeldItem();
				    NBTTagCompound nbt=new NBTTagCompound();
				    held.writeToNBT(nbt);
				    if(!nbt.hasKey("tag"))
				    {
				    	nbt.setTag("tag",new NBTTagCompound());
				    }
				    NBTTagCompound tag=(NBTTagCompound)nbt.getTag("tag");
				    int exp=tag.hasKey("exp")?tag.getInteger("exp")+10:10;
				    tag.setInteger("exp",exp);

				    tag.setByteArray("tastes",new byte[]{1,2,3,4,5});

				    tag.setIntArray("pids",new int[]{Potion.damageBoost.id,Potion.digSlowdown.id});
				    tag.setIntArray("pts",new int[]{100,200});
				    tag.setIntArray("pls",new int[]{0,2});
				    tag.setIntArray("prs",new int[]{40,55});

				    tag.setFloat("antiD",0.2f);
				    tag.setFloat("antiP",0.35f);

				    nbt.setTag("tag",tag);

				    held.readFromNBT(nbt);
			    }
			    catch(Exception e)
			    {
				    e.printStackTrace();
				    StringBuffer sb=new StringBuffer();
				    for(StackTraceElement stackTrace : e.getStackTrace())
				        sender.addChatMessage(new ChatComponentText(stackTrace.toString()));
			    }
		    }

		    @Override
		    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		    {
			    return true;
		    }

		    @Override
		    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		    {
			    return new LinkedList();
		    }

		    @Override
		    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		    {
			    return false;
		    }

		    @Override
		    public int compareTo(Object o)
		    {
			    return 0;
		    }
	    });

	    // eff_love
	    event.registerServerCommand(new ICommand()
	    {
		    @Override
		    public String getCommandName()
		    {
			    return "eff_love";
		    }

		    @Override
		    public String getCommandUsage(ICommandSender p_71518_1_)
		    {
			    return "give love effect";
		    }

		    @Override
		    public List getCommandAliases()
		    {
			    return new LinkedList();
		    }

		    @Override
		    public void processCommand(ICommandSender sender, String[] args)
		    {
			    try
			    {
				    EntityPlayer player=(EntityPlayer)sender;
				    if(player.worldObj.isRemote)
					    return;

				    player.addPotionEffect(new PotionEffect(Potions.love.id,200,0));
			    }
			    catch(Exception e)
			    {
				    e.printStackTrace();
				    StringBuffer sb=new StringBuffer();
				    for(StackTraceElement stackTrace : e.getStackTrace())
					    sender.addChatMessage(new ChatComponentText(stackTrace.toString()));
			    }
		    }

		    @Override
		    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		    {
			    return true;
		    }

		    @Override
		    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		    {
			    return new LinkedList();
		    }

		    @Override
		    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		    {
			    return false;
		    }

		    @Override
		    public int compareTo(Object o)
		    {
			    return 0;
		    }
	    });
    }
}