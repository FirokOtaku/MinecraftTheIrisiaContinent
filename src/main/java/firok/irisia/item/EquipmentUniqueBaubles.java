package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.items.ItemRing;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.ability.CauseTeleportation;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.research.PlayerKnowledge;

import java.util.List;

public class EquipmentUniqueBaubles
{
	// baubles
	public static EquipmentSets.Ring PhaseRing; // 月相戒
	public static EquipmentSets.Ring TwilightRing; // 暮光戒
	public final static EquipmentSets.Ring FortuneRing; // 幸运指环
	public final static EquipmentSets.Ring MinersRing; // 矿工指环
	public final static EquipmentSets.Ring GuardiansRing; // 守卫者指环

	public final static EquipmentSets.Ring ThrivingRing; // 茁壮指环
	public final static EquipmentSets.Ring InsaneRing; // 癫狂指环
	public static EquipmentSets.Ring ScarletRing; // 猩红指环
	public final static EquipmentSets.Ring LucidRing; // 清明指环
	public final static EquipmentSets.Ring LoveRing; // 爱情指环

	public final static EquipmentSets.Belt DwartTravellerBelt; // 矮人旅行者腰带
	public static EquipmentSets.Amulet SpeAmulet; // 诅咒之护符
	public static EquipmentSets.Amulet CoreAmulet; // 遥控护符

	public static EquipmentSets.Belt DwartBelt; // 矮人腰带
	public static EquipmentSets.Ring KingRing; // 人王指环
	public static EquipmentSets.Ring ElfRing; // 精灵指环
	public static EquipmentSets.Amulet TeethAmulet; // 兽牙项链


	static
	{
		FortuneRing=new EquipmentSets.Ring()
		{
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 40 ==0)
				{
//					EntityPlayer p=(EntityPlayer)enlb;
//					p.addChatComponentMessage(new ChatComponentText("Worn Tick"+enlb.ticksExisted%20));
				}
			}
		};

		MinersRing=new EquipmentSets.Ring()
		{
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 160 ==0)
				{
					ItemStack hold=enlb.getHeldItem();
					if(hold!=null && hold.getItem() instanceof ItemPickaxe)
					{
						// 急迫2
						enlb.addPotionEffect(
								new PotionEffect(
										Potion.digSpeed.getId(),
										180,
										1));
					}
				}
			}
		};

		GuardiansRing=new EquipmentSets.Ring()
		{
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 160 ==0)
				{
					ItemStack hold=enlb.getHeldItem();
					if(hold!=null && hold.getItem() instanceof ItemPickaxe)
					{
						// 抗性提升2
						enlb.addPotionEffect(
								new PotionEffect(
										Potion.resistance.getId(),
										180,
										1));
					}
				}
			}
		};


		ThrivingRing=new EquipmentSets.Ring(){
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 80 ==0)
				{
					double x=enlb.posX,y=enlb.posY,z=enlb.posZ;
					if(applyBonemeal(enlb.worldObj,(int)x,(int)y,(int)z,(EntityPlayer)enlb))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x, (int)y, (int)z, 0);
					}
					if(applyBonemeal(enlb.worldObj,(int)x-1,(int)y,(int)z,(EntityPlayer)enlb))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x-1, (int)y, (int)z, 0);
					}
					if(applyBonemeal(enlb.worldObj,(int)x+1,(int)y,(int)z,(EntityPlayer)enlb))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x+1, (int)y, (int)z, 0);
					}
					if(applyBonemeal(enlb.worldObj,(int)x,(int)y,(int)z-1,(EntityPlayer)enlb))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x, (int)y, (int)z-1, 0);
					}
					if(applyBonemeal(enlb.worldObj,(int)x,(int)y,(int)z+1,(EntityPlayer)enlb))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x, (int)y, (int)z+1, 0);
					}
				}
			}
		}; // 茁壮指环
		InsaneRing=new EquipmentSets.Ring(){
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 160 ==0)
				{
					try
					{
						EntityPlayer player=(EntityPlayer) enlb;
						String playerName=player.getCommandSenderName();
						PlayerKnowledge know=Thaumcraft.proxy.getPlayerKnowledge();
						int warpSticky=know.getWarpSticky(playerName);
						int warpTemp=know.getWarpTemp(playerName);
						// player.addChatComponentMessage(new ChatComponentText("perm:"+warpPerm+" sticky:"+warpSticky+" temp:"+warpTemp));
						if(warpSticky<100)
							know.setWarpSticky(playerName,warpSticky+1);
						if(warpTemp<100)
							know.setWarpTemp(playerName,warpTemp+1);
					}
					catch(Exception exception)
					{
						;
					}
				}
			}
		}; // 癫狂指环
//		ScarletRing; // 猩红指环
		LucidRing=new EquipmentSets.Ring(){
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 160 ==0)
				{
					try
					{
						EntityPlayer player=(EntityPlayer) enlb;
						String playerName=player.getCommandSenderName();
						PlayerKnowledge know=Thaumcraft.proxy.getPlayerKnowledge();
						int warpSticky=know.getWarpSticky(playerName);
						int warpTemp=know.getWarpTemp(playerName);
						// player.addChatComponentMessage(new ChatComponentText("perm:"+warpPerm+" sticky:"+warpSticky+" temp:"+warpTemp));
						know.setWarpSticky(playerName,warpSticky-(player.worldObj.rand.nextFloat()>0.4?0:1));
						know.setWarpTemp(playerName,warpTemp-1);
					}
					catch(Exception exception)
					{
						;
					}
				}
			}
		}; // 清明指环

		// love ring
		LoveRing=new AbilityRing()
		{
			@Override
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player1)
			{
				if(world.isRemote)
					return itemStack;

				if(player1.isSneaking()) // 两边都是潜行才有效
				{
					EntityPlayer player2=world.getClosestPlayerToEntity(player1,5);
					if(player2==null) return itemStack;

					ItemStack player2held=player2.getHeldItem();
					if(player2.isSneaking()
							&& player2held!=null
							&& player2held.getItem()==LoveRing)
					{
						NBTTagCompound tag1=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
						NBTTagCompound tag2=player2held.hasTagCompound()?player2held.getTagCompound():new NBTTagCompound();

						tag1.setString("target",player2.getCommandSenderName());
						tag2.setString("target",player1.getCommandSenderName());
						itemStack.setTagCompound(tag1);
						player2held.setTagCompound(tag2);

						player1.addChatComponentMessage(new ChatComponentText(
								new StringBuffer("你已经与")
										.append(player2.getCommandSenderName())
										.append("结下契约")
										.toString()
						));
						player2.addChatComponentMessage(new ChatComponentText(
								new StringBuffer("你已经与")
										.append(player1.getCommandSenderName())
										.append("结下契约")
										.toString()
						));
					}
				}

				return itemStack;
			}
			@Override
			public void doAbility(ItemStack itemStack, EntityPlayer player)
			{
				if(player.worldObj.isRemote)
					return;

				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				if(tag.hasKey("target"))
				{
					String player2name=tag.getString("target");
					EntityPlayer player2=player.worldObj.getPlayerEntityByName(player2name);
					if(player2!=null)
					{
						IInventory inv=BaublesApi.getBaubles(player2);
						if(inv==null)
							return;

						for(int i=0;i<inv.getSizeInventory();i++)
						{
							ItemStack player2itemStack=inv.getStackInSlot(i);
							if(player2itemStack==null || player2itemStack.getItem()!=LoveRing)
								continue;

							NBTTagCompound tag2=player2itemStack.hasTagCompound()?player2itemStack.getTagCompound():new NBTTagCompound();
							if(tag2.hasKey("target") && tag2.getString("target").equals(player.getCommandSenderName()))
							{
								CauseTeleportation.teleportEntity(player,player2,false,false);
							}
						}
					}
				}
			}
		}; // 爱情指环


		PhotosynthesisAmulet=new EquipmentSets.Amulet()
		{
			@Override
			public void onWornTick(ItemStack stack, EntityLivingBase entity)
			{
				if(entity.worldObj.isRemote
						|| entity.worldObj.getTotalWorldTime()%60!=0
						|| entity.worldObj.getBlockLightValue_do
						((int)entity.posX,(int)entity.posY,(int)entity.posZ,true)<9)
					return;

				entity.heal(1);
			}
		};

		DwartTravellerBelt=new AbilityBelt() // FIXME 重新加载游戏之后会消失
		{
			public final String[] slotKeys=new String[]
					{"slot0","slot1","slot2",
					"slot3","slot4","slot5",
					"slot6","slot7","slot8"};
			public ItemStack[] getStacksFromNBT(NBTTagCompound nbt)
			{
				ItemStack[] ret=new ItemStack[9];
				if(nbt==null)
					return ret;
				byte i=0;
				for(String slotKey:slotKeys)
				{
					if(!nbt.hasKey(slotKey))
						ret[i]=null;
					else
						try
						{
							ret[i]=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(slotKey));
						}
						catch (Exception e)
						{
							ret[i]=null;
						}
					i++;
				}
				return ret;
			}
			public NBTTagCompound getNBTFromStacks(ItemStack[] stacks)
			{
				NBTTagCompound ret=new NBTTagCompound();
				for(int i=0;i<9;i++)
				{
					if(stacks[i]==null)
						;
					else
					{
						NBTTagCompound slotNbt=new NBTTagCompound();
						stacks[i].writeToNBT(slotNbt);
						ret.setTag(slotKeys[i],slotNbt);
					}
				}
				return ret;
			}
			@Override
			public void doAbility(ItemStack isBelt, EntityPlayer player)
			{
				if(player.worldObj.isRemote)
					return;
				NBTTagCompound tag=isBelt.hasTagCompound()?isBelt.getTagCompound():new NBTTagCompound();
				NBTTagCompound tagNew=new NBTTagCompound();
				InventoryPlayer inventory=player.inventory;
				for(int i=0;i<9;i++)
				{
					String slotKey=slotKeys[i];
					try
					{
						ItemStack is_inInv=inventory.getStackInSlot(i);
						ItemStack is_inBelt=tag.hasKey(slotKey)?
								ItemStack.loadItemStackFromNBT(tag.getCompoundTag(slotKey)):null;
						Irisia.log(is_inInv==null?"null inv":is_inInv.toString(),player);
						Irisia.log(is_inBelt==null?"null belt":is_inBelt.toString(),player);
						if(is_inInv!=null)
						{
							NBTTagCompound itemSlotInv=new NBTTagCompound();
							is_inInv.writeToNBT(itemSlotInv);
							tagNew.setTag(slotKey,itemSlotInv);
						}
						inventory.setInventorySlotContents(i,is_inBelt);
					}
					catch (Exception e)
					{
						if(Irisia.IN_DEV)
							Irisia.log(e,player);
					}
				}
				isBelt.setTagCompound(tagNew);
			}

			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean p_77624_4_)
			{
				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				for(String slotKey:slotKeys)
				{
					if(tag.hasKey(slotKey))
					{
						info.add(slotKey);
						info.add(ItemStack.loadItemStackFromNBT((NBTTagCompound)
								tag.getTag(slotKey)).toString());
					}
				}
			}
		}; // 矮人旅行者腰带

	}


	// amulets
	public final static EquipmentSets.Amulet PhotosynthesisAmulet; // 光合护身符

	public static interface IBaubleAbility
	{
		public void doAbility(ItemStack itemStack,EntityPlayer player);
	}
	public static abstract class AbilityRing extends EquipmentSets.Ring implements IBaubleAbility
	{
		;
	}
	public static abstract class AbilityBelt extends EquipmentSets.Belt implements IBaubleAbility
	{
		;
	}
	public static abstract class AbilityAmulet extends EquipmentSets.Amulet implements IBaubleAbility
	{
		;
	}


	public static boolean applyBonemeal( World world, int x, int y, int z, EntityPlayer player)
	{
		if(world.isRemote)
			return false;

		Block block = world.getBlock(x, y, z);

		if (block instanceof IGrowable)
		{
			IGrowable igrowable = (IGrowable)block;

			if (igrowable.func_149851_a(world, x, y, z, world.isRemote))
			{
				if (igrowable.func_149852_a(world, world.rand, x, y, z))
				{
					igrowable.func_149853_b(world, world.rand, x, y, z);
				}
				return true;
			}
		}

		return false;
	}
}
