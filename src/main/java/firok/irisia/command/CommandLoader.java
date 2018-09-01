package firok.irisia.command;

import java.util.LinkedList;
import java.util.List;

import firok.irisia.Irisia;
import firok.irisia.item.Consumables;
import firok.irisia.item.EquipmentUniqueBaubles;
import firok.irisia.potion.Potions;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
	private void eff_love(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
			if(player.worldObj.isRemote)
				return;

		player.addPotionEffect(new PotionEffect(Potions.Love.id,200,0));
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
    public CommandLoader(FMLServerStartingEvent event)
    {
	    event.registerServerCommand(new ICommand()
	    {
		    @Override
		    public void processCommand(ICommandSender sender, String[] args)
		    {
			    try
			    {
				    if("show".equals(args[0]))
				    {
					    show(sender,args);
				    }
				    else if("eff_love".equals(args[0]))
				    {
					    eff_love(sender,args);
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