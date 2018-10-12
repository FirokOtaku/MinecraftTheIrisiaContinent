package firok.irisia.common;

import java.util.LinkedList;
import java.util.List;

import firok.irisia.Irisia;
import firok.irisia.SomeCodes;
import firok.irisia.Util;
import firok.irisia.entity.CarryOut;
import firok.irisia.entity.Monsters;
import firok.irisia.entity.Npcs;
import firok.irisia.entity.Throwables;
import firok.irisia.item.Consumables;
import firok.irisia.item.EquipmentUniqueBaubles;
import firok.irisia.potion.Potions;
import firok.irisia.world.gen.Gen;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import thaumcraft.api.aspects.Aspect;

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
		switch (args[1])
		{
			case "love":
				player.addPotionEffect(new PotionEffect(Potions.Love.id,time,level));
				break;
			case "nj":
				player.addPotionEffect(new PotionEffect(Potions.Ninjia.id,time,level));
				break;
			case "wr":
				player.addPotionEffect(new PotionEffect(Potions.WindRanger.id,time,level));
				break;
			case "ts":
				player.addPotionEffect(new PotionEffect(Potions.Thresholded.id,time,level));
				break;
			case "e":
				player.addPotionEffect(new PotionEffect(Potions.Ethereal.id,time,level));
				break;
			case "visl":
				player.addPotionEffect(new PotionEffect(Potions.VisLeaking.id,time,level));
				break;
			case "cor":
				player.addPotionEffect(new PotionEffect(Potions.Corroded.id,time,level));
				break;
			case "cursed":
				player.addPotionEffect(new PotionEffect(Potions.Cursed.id,time,level));
				break;
			case "mil":
				player.addPotionEffect(new PotionEffect(Potions.Militaristic.id,time,level));
				break;
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
	private void summon(ICommandSender sender, String[] args)
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
							return entity instanceof Npcs.TestTarget
									|| entity instanceof Monsters.GhostKnocker;
						}
					});

			for(Object obj:entities)
			{
				Entity target=(Entity)obj;
				target.setDead();
				Irisia.log("entity removed",player);
			}
		}
		else if("spawn".equals(args[1]))
		{
			if("tt".equals(args[2]))
			{
				player.worldObj.spawnEntityInWorld(
						new Npcs.TestTarget(player.worldObj,player.posX,player.posY,player.posZ));
				Irisia.log("target spawned",player);
			}
			else if("gk".equals(args[2]))
			{
				player.worldObj.spawnEntityInWorld(
						new Monsters.GhostKnocker(player.worldObj,player.posX,player.posY,player.posZ));
				Irisia.log("chost knocker spawned",player);
			}
		}
	}
	private void tnote(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		ItemStack itemStack=new ItemStack(Consumables.ThaumicNote,1);
		NBTTagCompound tag=new NBTTagCompound();
		if("1".equals(args[1]))
		{
			tag.setString("a1",Aspect.AIR.getTag());
			tag.setShort("c1",(short)10);
			itemStack.setTagCompound(tag);
		}
		else if("2".equals(args[1]))
		{
			tag.setString("a1",Aspect.FIRE.getTag());
			tag.setShort("c1",(short)15);
			itemStack.setTagCompound(tag);
		}
		else if("3".equals(args[1]))
		{
			tag.setString("a1",Aspect.AIR.getTag());
			tag.setShort("c1",(short)20);
			tag.setString("a2",Aspect.EARTH.getTag());
			tag.setShort("c2",(short)20);
			itemStack.setTagCompound(tag);
		}
		player.entityDropItem(itemStack,0);
	}
	private void maze(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		// args[0]==maze
		int lenX=10,lenY=5,lenZ=10,levelY=3;
		if(args.length>3)
		{
			lenX=Integer.parseInt(args[1]);
			lenY=Integer.parseInt(args[2]);
			lenZ=Integer.parseInt(args[3]);
			levelY=Integer.parseInt(args[4]);
		}
		Gen.genSimpleTowerAt(player.worldObj,
				(int)player.posX,(int)player.posY,(int)player.posZ,
				player.worldObj.rand,lenX,lenY,lenZ,levelY,
				Blocks.stone,Blocks.stonebrick);
	}
	private void carryout(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		player.worldObj.spawnEntityInWorld(new CarryOut.Carrier(player.worldObj,player.posX,player.posY+10,player.posZ));
	}
	private void world(ICommandSender sender, String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		if("tp1".equals(args[1]))
		{//player.worldObj.getPlayerList().transferPlayerToDimension(player, dimensionID, teleporterInstance);
			player.travelToDimension(ConfigLoader.idDim);
		}
		else if("tp2".equals(args[1]))
		{
			player.travelToDimension(1);
		}
		else if("tp".equals(args[1]))
		{
			player.travelToDimension(Integer.parseInt(args[2]));
		}
		else if("id".equals(args[1]))
		{
			Irisia.log("World Provider Id : "+player.worldObj.provider.dimensionId,player);
			Irisia.log("World Provider Name : "+player.worldObj.getProviderName(),player);
		}
	}
	private void draw(ICommandSender sender,String[] args)
	{
		EntityPlayer player=(EntityPlayer)sender;
		int size=Integer.parseInt(args[1]);
		byte dir=Byte.parseByte(args[2]);
		StringBuilder text=new StringBuilder();
		for(int i=3;i<args.length;i++)
		{
			text.append(args[i]);text.append(' ');
		}
		Gen.genTextAt(player.worldObj,(int)player.posX,(int)player.posY+3,(int)player.posZ,text.toString(),size,dir);
	}
	private void lang()
	{
		try
		{
			SomeCodes.lang3();
		}
		catch (Exception e)
		{
			;
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
			    	switch(args[0])
				    {
					    case "show":
						    show(sender,args);
					    	break;
					    case "eff":
					    	eff(sender,args);
					    	break;
					    case "add_exp":
						    add_exp(sender,args);
					    	break;
					    case "gashapon":
						    gashapon(sender,args);
						    break;
					    case "bauble":
					        bauble(sender,args);
					        break;
					    case "invsee":
					        invsee(sender,args);
					        break;
					    case "coin":
					        coin(sender,args);
					        break;
					    case "tb":
					        tb(sender,args);
					        break;
					    case "sound":
						    sound(sender,args);
				            break;
					    case "summon":
				    	    summon(sender,args);
				            break;
					    case "tnote":
						    tnote(sender,args);
						    break;
					    case "maze":
					        maze(sender,args);
					        break;
					    case "carryout":
						    carryout(sender,args);
						    break;
					    case "world":
					    	world(sender,args);
					    	break;
					    case "draw":
					    	draw(sender,args);
					    	break;
					    case "lang":
					    	lang();
					    	break;
				    }
				    Irisia.log("执行完毕",(EntityPlayer)sender);
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
		    public boolean canCommandSenderUseCommand(ICommandSender sender)
		    { // todo 这里以后可能去掉indev之类的 单纯只有创造模式能用
			    return Irisia.IN_DEV||((EntityPlayer)sender).capabilities.isCreativeMode;
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
		    	if(o==this)
		    		return 0;

			    return -1;
		    }
	    });
    }
}