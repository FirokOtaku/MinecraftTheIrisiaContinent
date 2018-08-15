package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.items.ItemRing;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.research.PlayerKnowledge;

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

	public static EquipmentSets.Belt DwartBelt; // 矮人腰带
	public static EquipmentSets.Ring KingRing; // 人王指环
	public static EquipmentSets.Ring ElfRing; // 精灵指环
	public static EquipmentSets.Amulet TeethAmulet; // 兽牙项链


	// amulets
	public final static EquipmentSets.Amulet PhotosynthesisAmulet; // 光合护身符

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
