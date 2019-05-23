package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.ability.CausePlantGrowth;
import firok.irisia.ability.CauseTeleportation;
import firok.irisia.common.EntitySelectors;
import firok.irisia.entity.Pets;
import firok.irisia.potion.Potions;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.PlayerKnowledge;

import java.util.ArrayList;
import java.util.List;

public class EquipmentUniqueBaubles
{
	// 本身没有什么特殊代码 靠Event驱动的饰品
	public final static EquipmentSets.Ring MidasRelic; // 迈达斯之遗
	public final static EquipmentSets.Belt SylphBelt; // 风精灵腰带
	static
	{
		MidasRelic=new EquipmentSets.Ring();
		SylphBelt=new EquipmentSets.Belt();
	}

	// baubles
	public static EquipmentSets.Ring PhaseRing; // 月相戒
	public static EquipmentSets.Ring TwilightRing; // 暮光戒
	public final static EquipmentSets.Ring FortuneRing; // 幸运指环
	public final static EquipmentSets.Ring MinersRing; // 矿工指环
	public final static EquipmentSets.Ring GuardiansRing; // 守卫者指环
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
	}

	public final static EquipmentSets.Ring ThrivingRing; // 茁壮指环
	public final static EquipmentSets.Ring InsaneRing; // 癫狂指环
	public static EquipmentSets.Ring ScarletRing; // 猩红指环
	public final static EquipmentSets.Ring LucidRing; // 清明指环
	public final static EquipmentSets.Ring LoveRing; // 爱情指环
	static
	{
		ThrivingRing=new EquipmentSets.Ring(){
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 80 ==0)
				{
					double x=enlb.posX,y=enlb.posY,z=enlb.posZ;
					if(CausePlantGrowth.At(enlb.worldObj,(int)x,(int)y,(int)z))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x, (int)y, (int)z, 0);
					}
					if(CausePlantGrowth.At(enlb.worldObj,(int)x-1,(int)y,(int)z))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x-1, (int)y, (int)z, 0);
					}
					if(CausePlantGrowth.At(enlb.worldObj,(int)x+1,(int)y,(int)z))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x+1, (int)y, (int)z, 0);
					}
					if(CausePlantGrowth.At(enlb.worldObj,(int)x,(int)y,(int)z-1))
					{
						enlb.worldObj.playAuxSFX(2005, (int)x, (int)y, (int)z-1, 0);
					}
					if(CausePlantGrowth.At(enlb.worldObj,(int)x,(int)y,(int)z+1))
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
	}

	public final static EquipmentSets.Amulet FrostyStone; // 冰霜之石
	public final static EquipmentSets.Amulet PlagueStone; // 瘟疫之石
	public final static EquipmentSets.Amulet RevealingGem;
	public final static EquipmentSets.Amulet PhotosynthesisAmulet; // 光合护身符
	public final static EquipmentSets.Belt DwartTravellerBelt; // 矮人旅行者腰带
	public final static EquipmentSets.Belt MermaidBelt; // 人鱼腰带
	public static EquipmentSets.Amulet SpeAmulet; // 诅咒之护符
	public static EquipmentSets.Amulet CoreAmulet; // 遥控护符
	public final static EquipmentSets.Belt ChargeBelt; // 电荷腰带
	public final static EquipmentSets.Amulet TwelveMagicalPowerAmulet; // 十二魔力护符
	static
	{
		FrostyStone=new EquipmentSets.Amulet();
		PlagueStone=new EquipmentSets.Amulet()
		{
			@Override
			public void onWornTick(ItemStack stack, EntityLivingBase entity)
			{
				if(entity.worldObj.isRemote
						|| entity.worldObj.getTotalWorldTime()%240!=0)
					return;

				entity.addPotionEffect(new PotionEffect(Potions.Plaguing.id,240,4));
			}
		};
		RevealingGem=new EquipmentSets.Amulet()
		{
			@Override
			public void onWornTick(ItemStack stack, EntityLivingBase entity)
			{
				if(entity.worldObj.isRemote
						|| entity.worldObj.getTotalWorldTime()%30!=0)
					return;

				List<EntityLivingBase> entities=entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity,
						AxisAlignedBB.getBoundingBox(entity.posX-5,entity.posY-4,entity.posZ-5,
								entity.posX+5,entity.posY+4,entity.posZ+5),
						EntitySelectors.SelectEntityLivingBaseAlive);
				for(EntityLivingBase enlb:entities)
				{
					enlb.removePotionEffect(Potion.invisibility.id);
				}
			}
		};
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

		DwartTravellerBelt=new AbilityBelt()
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

		MermaidBelt=new EquipmentSets.Belt()
		{
			@Override
			public void onWornTick(ItemStack stack, EntityLivingBase entity)
			{
				if(entity.worldObj.isRemote||entity.ticksExisted%80!=0)
					return;
				Block block=entity.worldObj.getBlock((int)entity.posX,(int)entity.posY,(int)entity.posZ); // fixme low 这里好像有问题 水的检测位置有问题
				if(block==Blocks.water||block==Blocks.flowing_water)
				{
					entity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,85,3));
					entity.addPotionEffect(new PotionEffect(Potion.waterBreathing.id,85,1));
				}
			}
		}; // 人鱼腰带

		ChargeBelt=new EquipmentSets.Belt()
		{
			@Override
			public void onWornTick(ItemStack stack,EntityLivingBase entity)
			{
				if(entity.worldObj.isRemote||entity.ticksExisted%80!=0)
					return;
				if(entity.worldObj.isThundering())
				{
					entity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,85,1));
					entity.addPotionEffect(new PotionEffect(Potion.damageBoost.id,85,1));
				}
			}
		}; // 电荷腰带

		TwelveMagicalPowerAmulet=new EquipmentSets.Amulet(){
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer)
				{
					long ticks=enlb.worldObj.getWorldTime();
					// 20min×60s×20tick= 24000 tick
					// 2000tick=2h
					//
					if(ticks%2000!=0)
						return;
					int h=(int)(ticks/2000%12);
					Irisia.log(String.valueOf(h),(EntityPlayer)enlb); // TOREMOVE 以后删掉
					switch (h)
					{
						case 0: // todo 还没确定具体加什么效果
							;
							break;
						case 1:
							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							break;
						case 6:
							break;
						case 7:
							break;
						case 8:
							break;
						case 9:
							break;
						case 10:
							break;
						case 11: default:
						break;
					}

				}
			}
		}; // 十二魔力护符
	}

	// 各种灵气指环
	public final static VisRing DimVisRingEarth;
	public final static VisRing DimVisRingFire;
	public final static VisRing DimVisRingWater;
	public final static VisRing DimVisRingAir;
	public final static VisRing DimVisRingOrder;
	public final static VisRing DimVisRingEntropy;
	public final static VisRing VisRingEarth;
	public final static VisRing VisRingFire;
	public final static VisRing VisRingWater;
	public final static VisRing VisRingAir;
	public final static VisRing VisRingOrder;
	public final static VisRing VisRingEntropy;
	public final static VisRing GlowVisRingEarth;
	public final static VisRing GlowVisRingFire;
	public final static VisRing GlowVisRingWater;
	public final static VisRing GlowVisRingAir;
	public final static VisRing GlowVisRingOrder;
	public final static VisRing GlowVisRingEntropy;
	static
	{
		List<Aspect> earth=new ArrayList<>();
		int intervalDim=120;
		int realCountDim=100;
		int interval=120;
		int realCount=200;
		int intervalGlow=120;
		int realCountGlow=300;
		earth.add(Aspect.EARTH);
		DimVisRingEarth=new VisRing(intervalDim,earth,realCountDim);
		VisRingEarth=new VisRing(interval,earth,realCount);
		GlowVisRingEarth=new VisRing(intervalGlow,earth,realCountGlow);

		List<Aspect> air=new ArrayList<>();
		air.add(Aspect.AIR);
		DimVisRingAir=new VisRing(intervalDim,air,realCountDim);
		VisRingAir=new VisRing(interval,air,realCount);
		GlowVisRingAir=new VisRing(intervalGlow,air,realCountGlow);

		List<Aspect> water=new ArrayList<>();
		water.add(Aspect.WATER);
		DimVisRingWater=new VisRing(intervalDim,water,realCountDim);
		VisRingWater=new VisRing(interval,water,realCount);
		GlowVisRingWater=new VisRing(intervalGlow,water,realCountGlow);

		List<Aspect> fire=new ArrayList<>();
		fire.add(Aspect.FIRE);
		DimVisRingFire=new VisRing(intervalDim,fire,realCountDim);
		VisRingFire=new VisRing(interval,fire,realCount);
		GlowVisRingFire=new VisRing(intervalGlow,fire,realCountGlow);

		List<Aspect> order=new ArrayList<>();
		order.add(Aspect.ORDER);
		DimVisRingOrder=new VisRing(intervalDim,order,realCountDim);
		VisRingOrder=new VisRing(interval,order,realCount);
		GlowVisRingOrder=new VisRing(intervalGlow,order,realCountGlow);

		List<Aspect> entropy=new ArrayList<>();
		entropy.add(Aspect.ENTROPY);
		DimVisRingEntropy=new VisRing(intervalDim,entropy,realCountDim);
		VisRingEntropy=new VisRing(interval,entropy,realCount);
		GlowVisRingEntropy=new VisRing(intervalGlow,entropy,realCountGlow);
	}

	public static EquipmentSets.Belt DwartBelt; // 矮人腰带
	public static EquipmentSets.Ring KingRing; // 人王指环
	public static EquipmentSets.Ring ElfRing; // 精灵指环
	public static EquipmentSets.Amulet TeethAmulet; // 兽牙项链
	static
	{}




	public static class VisRing extends VisBauble
	{
		public VisRing(int interval, List<Aspect> aspects, int realVisCount)
		{
			super(interval,aspects,realVisCount);
		}
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}
	public static abstract class VisBauble extends EquipmentSets.ItemBauble // info 给法杖恢复魔力
	{
		Aspect aspect;
		List<Aspect> aspects;
		int count;
		int interval;
		protected VisBauble()
		{
			this(8000,Irisia.noAspect,0);
		}
		public VisBauble(int interval, List<Aspect> aspects, int realVisCounts)
		{
			this.interval=interval>0?interval:8000;
			this.aspects=aspects==null?Irisia.noAspect:aspects;
			this.count=realVisCounts>0?realVisCounts:0;
		}

		@Override
		public void onWornTick(ItemStack itemStack,EntityLivingBase enlb)
		{
			if(enlb.worldObj.isRemote ||
					Irisia.noAspect==this.aspects ||
					enlb.ticksExisted%interval!=0 ||
					count==0 ||
					! (enlb instanceof EntityPlayer)
					)
				return;
			EntityPlayer player=(EntityPlayer)enlb;
			InventoryPlayer inv=player.inventory;
			for(int i=0;i<inv.getSizeInventory();i++)
			{
				ItemStack stackInSlot=inv.getStackInSlot(i);
				if(stackInSlot==null || stackInSlot.stackSize<=0 || !(stackInSlot.getItem() instanceof ItemWandCasting)) continue;
				ItemWandCasting cast=(ItemWandCasting)stackInSlot.getItem(); // info 在包里找法杖
				boolean canAdd=false;
				int maxVis=cast.getMaxVis(stackInSlot);
				for(Aspect as:aspects)
				{
					if(cast.getVis(stackInSlot,as)<maxVis)
					{
						canAdd=true;
						break;
					}
				}
				if(!canAdd)
					continue; // info 如果全部魔力都是满的 说明不能恢复
				for(Aspect as:aspects)
				{
					cast.addRealVis(stackInSlot,as,count,true);
				}
				break; // info 如果执行到这里说明已经恢复过一次魔力 直接break
			}
		}
	}

	// note 这个以后可能删掉 用反射可能比较浪费性能 所以以后可能直接换成匿名内部类 但是感觉那样可能浪费内存
	public static abstract class ParticleBauble extends EquipmentSets.ItemBauble
	{
		public final int interval;
		public final Class<EntityFX> fx;
		public ParticleBauble()
		{
			super();
			interval=0;
			fx=null;
			// fx.getConstructor(Integer.class).newInstance(1);
		}

		@Override
		public void onWornTick(ItemStack is, EntityLivingBase enlb)
		{
			; // todo 以后在这里渲染粒子
		}
	}
	public static class ParticleRing extends ParticleBauble
	{
		public ParticleRing()
		{
			super();
		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}
	public static class ParticleAmulet extends ParticleBauble
	{
		public ParticleAmulet()
		{
			super();
		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.AMULET;
		}
	}
	public static class ParticleBelt extends ParticleBauble
	{
		public ParticleBelt()
		{
			super();
		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.BELT;
		}
	}


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
}
