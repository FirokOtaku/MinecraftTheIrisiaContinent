package firok.irisia.command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.potion.PotionLoader;
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
    }

    
//    public static class Ench extends CommandBase
//    {
//
//    	@Override
//    	public String getCommandName() {
//    		return "ench";
//    	}
//
//    	@Override
//    	public String getCommandUsage(ICommandSender sender) {
//    		return "ench !";
//    	}
//
//    	@Override
//    	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//    		;
////    		World world=sender.getEntityWorld();
////    		BlockPos pos=sender.getPosition();
////    		TotemBase totem=new firok.irisia.entity.totem.TotemBase(world,
////    				pos.getX(), pos.getY(), pos.getZ());
////    		if(sender instanceof Entity)
////    		{
////    			totem.setUUID(((Entity) sender).getUniqueID());
////    		}
////    		world.spawnEntityInWorld(totem);
//    		try
//    		{
//    			World world=sender.getEntityWorld();
//    			EntityPlayer player=(EntityPlayer)sender;
//    			ItemStack held=player.getHeldItem();
//    			if(held==null||held.stackSize<=0)
//    				return;
//    			HashMap<Enchantment,Integer> enchs=new HashMap<Enchantment,Integer>();
//    			enchs.put(EnchantmentLoader.culling, 2);
//    			EnchantmentHelper.setEnchantments(enchs, held);
//    		}
//    		catch(Exception e)
//    		{
//    			sender.addChatMessage(new net.minecraft.util.ChatComponentText(e.getMessage()));
//    		}
//    	}
//
//    	@Override
//        public int getRequiredPermissionLevel()
//        {
//            return 1;
//        }
//
//    }

//    public static class Totem extends CommandBase
//    {
//    	@Override
//    	public String getCommandName() {
//    		return "totem";
//    	}
//
//    	@Override
//    	public String getCommandUsage(ICommandSender sender) {
//    		return "totem !";
//    	}
//
//    	@Override
//    	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//    		;
//    		World world=sender.getEntityWorld();
//    		BlockPos pos=sender.getPosition();
//    		TotemBase totem=new firok.irisia.entity.totem.TotemBase(world,
//    				pos.getX(), pos.getY(), pos.getZ());
//    		if(sender instanceof Entity)
//    		{
//    			totem.setUUID(((Entity) sender).getUniqueID());
//    		}
//    		world.spawnEntityInWorld(totem);
//
//    	}
//
//    	@Override
//        public int getRequiredPermissionLevel()
//        {
//            return 1;
//        }
//
//    }

//	public static class WarpSeer extends CommandBase
//	{
//		@Override
//		public String getCommandName() {
//			return "warpseer";
//		}
//
//		@Override
//		public String getCommandUsage(ICommandSender sender) {
//			return "warpseer !";
//		}
//
//		@Override
//		public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//			try
//			{
//				World world=sender.getEntityWorld();
//				BlockPos pos=sender.getPosition();
//				Entity ent=new firok.irisia.entity.npc.tc.WarpSeer(world);
//				ent.setPosition(pos.getX(),pos.getY(),pos.getZ());
//				world.spawnEntityInWorld(ent);
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		@Override
//		public int getRequiredPermissionLevel()
//		{
//			return 1;
//		}
//
//	}
//
//    public static class EffectPotion extends CommandBase
//    {
//    	@Override
//    	public String getCommandName() {
//    		return "tear";
//    	}
//
//    	@Override
//    	public String getCommandUsage(ICommandSender sender) {
//    		return "test !";
//    	}
//
//    	@Override
//    	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
//    		;
//    		World world=sender.getEntityWorld();
//    		BlockPos pos=sender.getPosition();
//    		TotemBase totem=new firok.irisia.entity.totem.TotemBase(world,
//    				pos.getX(), pos.getY(), pos.getZ());
//    		if(sender instanceof Entity)
//    		{
//    			// 16秒 3级
//    			PotionEffect pef=new PotionEffect(PotionLoader.teared,320,2,false,true);
//    			EntityLivingBase b=(EntityLivingBase)sender;
//    			b.addPotionEffect(pef);
//    		}
//
//    	}
//
//    	@Override
//        public int getRequiredPermissionLevel()
//        {
//            return 1;
//        }
//
//    }

}