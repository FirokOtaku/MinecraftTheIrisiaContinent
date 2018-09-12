package firok.irisia.command;

import java.util.LinkedList;
import java.util.List;

import firok.irisia.Irisia;
import firok.irisia.Util;
import firok.irisia.entity.Npcs;
import firok.irisia.entity.Throwables;
import firok.irisia.item.Consumables;
import firok.irisia.item.EquipmentUniqueBaubles;
import firok.irisia.potion.Potions;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandLoader
{
	private void show(ICommandSender sender, String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		if(player.worldObj.isRemote)
			return;

		ItemStack held=player.getHeldItem();
		Item item=held.getItem();
		String str=item.getClass().getName();
		player.addChatComponentMessage(new ChatComponentText(str));
	}
	private void add_exp(ICommandSender sender,String[] args)
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
	private void eff(ICommandSender sender, String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;

		int time=200;
		try{time=Integer.parseInt(args[2]);}catch (Exception e){;}
		int level=0;
		try{level=Integer.parseInt(args[3]);}catch (Exception e){;}

		if("love".equals(args[1]))
		{
			player.addPotionEffect(new PotionEffect(Potions.Love.id,time,level));
		}
		else if("nj".equals(args[1]))
		{
			player.addPotionEffect(new PotionEffect(Potions.Ninjia.id,time,level));
		}
		else if("wr".equals(args[1]))
		{
			player.addPotionEffect(new PotionEffect(Potions.WindRanger.id,time,level));
		}
		else if("ts".equals(args[1]))
		{
			player.addPotionEffect(new PotionEffect(Potions.Thresholded.id,time,level));
		}
		else if("e".equals(args[1]))
		{
			player.addPotionEffect(new PotionEffect(Potions.Ethereal.id,time,level));
		}
	}
	private void gashapon(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		ItemStack held=player.getHeldItem();
		if(held==null||held.getItem()!=Consumables.Gashapon) return;
		NBTTagCompound tag=held.hasTagCompound()?held.getTagCompound():new NBTTagCompound();
		String key=args[1];
		float bonus=0;try{bonus=Float.parseFloat(args[2]);}catch (Exception e){}
		tag.setString("key",key);
		tag.setFloat("bonus",bonus);
		held.setTagCompound(tag);
	}
	private void bauble(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		int slotOrder=Integer.parseInt(args[1]);
		IInventory inventory=baubles.api.BaublesApi.getBaubles(player);
		ItemStack isGet=inventory.getStackInSlot(slotOrder);
		Irisia.log(isGet.toString(),player);
		if(isGet!=null)
		{
			Item item= isGet.getItem();
			Irisia.log(item.getUnlocalizedName(),player);
			if(item instanceof EquipmentUniqueBaubles.IBaubleAbility)
			{
				((EquipmentUniqueBaubles.IBaubleAbility)item).doAbility(isGet,player);
			}
		}
	}
	private void coin(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		if("count".equals(args[1]))
		{
			Util.CoinCounter coins=new Util.CoinCounter(player);
			Irisia.log(coins.toString(),player);
			coins.optimize();
			Irisia.log("最优化之后 : ",player);
			Irisia.log(coins.toString(),player);
		}
		else if("set".equals(args[1]))
		{
			Util.CoinCounter coins=new Util.CoinCounter(Integer.parseInt(args[2]));
			Irisia.log(coins.toString(),player);
			coins.optimize();
			Irisia.log("最优化之后 : ",player);
			Irisia.log(coins.toString(),player);
			coins.apply(player);
		}
	}
	private void invsee(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		InventoryPlayer inv=player.inventory;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<inv.getSizeInventory();i++)
		{
			ItemStack is=inv.getStackInSlot(i);
			if(is==null) continue;
			sb.append("   slot");sb.append(i);sb.append(" : ");
			sb.append(is.toString());
		}
		Irisia.log(sb.toString(),player);
	}
	private void tb(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		Irisia.log("summon a thunder ball !",player);
		player.worldObj.spawnEntityInWorld(new Throwables.EntityThunderBall(player.worldObj,player,0.2f));
	}
	private void sound(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		player.worldObj.playSoundAtEntity(player,args[1],1,1);
	}
	private void tt(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		if("kill".equals(args[1]))
		{
			List entities=player.worldObj.getEntitiesWithinAABBExcludingEntity(
					player,
					AxisAlignedBB.getBoundingBox(
							player.posX-64,player.posY-64,player.posZ-64,
							player.posX+64,player.posY+64,player.posZ+64),
					new IEntitySelector(){

						@Override
						public boolean isEntityApplicable(Entity entity)
						{
							return entity instanceof Npcs.TestTarget;
						}
					});

			for(Object obj:entities)
			{
				Npcs.TestTarget target=(Npcs.TestTarget)obj;
				target.setDead();
				Irisia.log("target removed",player);
			}
		}
		else if("spawn".equals(args[1]))
		{
			player.worldObj.spawnEntityInWorld(
					new Npcs.TestTarget(player.worldObj,player.posX,player.posY,player.posZ));
			Irisia.log("target spawned",player);
		}
	}
    public CommandLoader(FMLServerStartingEvent event)
    {
	    event.registerServerCommand(new ICommand()
	    {
		    @Override
		    public void processCommand(ICommandSender sender, String[] args)
		    {
		    	StringBuffer sb=new StringBuffer();
		    	for(String arg:args)
			    {
			    	sb.append(arg);
			    	sb.append(' ');
			    }
			    Irisia.log(sb,(EntityPlayer)sender);
			    try
			    {
				    if("show".equals(args[0]))
				    {
					    show(sender,args);
				    }
				    else if("eff".equals(args[0]))
				    {
					    eff(sender,args);
				    }
				    else if("add_exp".equals(args[0]))
				    {
					    add_exp(sender,args);
				    }
				    else if("gashapon".equals(args[0]))
				    {
					    gashapon(sender,args);
				    }
				    else if("bauble".equals(args[0]))
				    {
				    	bauble(sender,args);
				    }
				    else if("invsee".equals(args[0]))
				    {
				    	invsee(sender,args);
				    }
				    else if("coin".equals(args[0]))
				    {
				    	coin(sender,args);
				    }
				    else if("tb".equals(args[0]))
				    {
				    	tb(sender,args);
				    }
				    else if("sound".equals(args[0]))
				    {
				    	sound(sender,args);
				    }
				    else if("tt".equals(args[0]))
				    {
				    	tt(sender,args);
				    }
			    }
			    catch (Exception exception)
			    {
				    exception.printStackTrace();
				    Irisia.log(exception,(EntityPlayer)sender);
			    }
		    }

		    @Override
		    public String getCommandName()
		    {
			    return "irisia";
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