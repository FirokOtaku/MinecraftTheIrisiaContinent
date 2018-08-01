package firok.irisia.command;

import java.util.HashMap;

import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.potion.PotionLoader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandLoader
{
    public CommandLoader(FMLServerStartingEvent event)
    {
    	System.out.println("No commands for now ! ");
//        event.registerServerCommand(new Ench());
//        event.registerServerCommand(new Totem());
//        event.registerServerCommand(new WarpSeer());
//        event.registerServerCommand(new EffectPotion());
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