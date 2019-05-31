package firok.irisia.item;

import baubles.api.BaublesApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.block.OresAndMetal;
import firok.irisia.common.AstrologyManager;
import firok.irisia.inventory.GuiElementLoader;
import firok.irisia.inventory.IHandInv;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;

import java.util.List;

@SuppressWarnings("unchecked")
public class Tools
{
	public static final MetalInfusionOrienter OrienterThaumium;
	public static final MetalInfusionOrienter OrienterStorm;
	public static final MetalInfusionOrienter OrienterDark;
	public static final MetalInfusionOrienter OrienterPhotosynthesis;
	public static final MetalInfusionOrienter OrienterVibrhythm;
	public static final MetalInfusionOrienter OrienterLux;
	static
	{
		OrienterThaumium=new MetalInfusionOrienter(new AspectList().add(Aspect.MAGIC,32), ConfigBlocks.blockCosmeticSolid,4);
		OrienterStorm=new MetalInfusionOrienter(new AspectList().add(Aspect.WEATHER,32),OresAndMetal.BlockStormMetal,0);
		OrienterDark=new MetalInfusionOrienter(new AspectList().add(Aspect.DARKNESS,32),OresAndMetal.BlockDarkMetal,0);
		OrienterPhotosynthesis=new MetalInfusionOrienter(new AspectList().add(Aspect.PLANT,32),OresAndMetal.BlockPhotosynthesisMetal,0);
		OrienterVibrhythm=new MetalInfusionOrienter(new AspectList().add(Aspect.MOTION,32),OresAndMetal.BlockVibrhythmMetal,0);
		OrienterLux=new MetalInfusionOrienter(new AspectList().add(Aspect.LIGHT,32),OresAndMetal.BlockLuxMetal,0);
	}
	public static class MetalInfusionOrienter extends Item
	{
//		public int costTicks=40;
		public AspectList costAspects;
		public boolean need(Aspect aspect)
		{
			return costAspects !=null&& costAspects.getAmount(aspect)>0;
		}
		public Block productBlock=null;
		public int productMeta=0;
		public MetalInfusionOrienter(AspectList costAspects, Block productBlock, int productMeta)
		{
			this.costAspects=costAspects.copy();
			this.productBlock=productBlock;
			this.productMeta=productMeta;
		}
	}
	public final static Item Debugger; // todo low 以后可能删掉
	static
	{
		Debugger=new Item(){
			{
				this.setMaxDamage(0);
				this.setNoRepair();
			}
			private int getMode(ItemStack stack)
			{
				NBTTagCompound nbt=stack.hasTagCompound()?stack.getTagCompound():new NBTTagCompound();
				return nbt.hasKey("mode")?nbt.getInteger("mode"):0;
			}
			private String getModeName(int mode)
			{
				switch(mode)
				{
					case 1: return "物品调试";
					case 2: return "世界调试";
					case 3: return "玩家调试";
					case 4: return "矿物调试";
					case 0: default:return "关闭 仅调试方块";
				}
			}
			private boolean pass(World world,EntityPlayer player)
			{
				if(!Irisia.IN_DEV||world.isRemote||!player.capabilities.isCreativeMode)
					return false;
				return true;
			}
			public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
			{
				if(pass(player.worldObj,player))
				{
					Irisia.tellPlayer("sender name "+entity.getCommandSenderName(),player);
					Irisia.tellPlayer("uuid "+entity.getUniqueID(),player);
					if(entity instanceof EntityLivingBase)
						Irisia.tellPlayer("hp "+((EntityLivingBase)entity).getHealth()+" / "+((EntityLivingBase)entity).getMaxHealth(),player);
				}
				return true;
			}
			public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
			{
				if(getMode(stack)==0)
				{
					Block b=world.getBlock(x,y,z);
					int meta=world.getBlockMetadata(x,y,z);
					Irisia.log("side : "+side,player);
					Irisia.log("subX : "+hitX,player);
					Irisia.log("subY : "+hitY,player);
					Irisia.log("subZ : "+hitZ,player);
					Irisia.log("Class : "+b.getClass().getName(),player);
					Irisia.log("u : "+b.getUnlocalizedName(),player);
					Irisia.log("l : "+b.getLocalizedName(),player);
					Irisia.log("meta : "+meta,player);
					try
					{
						if(b.hasTileEntity(meta))
						{
							TileEntity te=world.getTileEntity(x,y,z);
							Irisia.log("te n :"+te.getClass().getName(),player);
							NBTTagCompound nbt=new NBTTagCompound();
							te.writeToNBT(nbt);
							Irisia.log(nbt.toString(),player);
						}
					}
					catch (Exception e)
					{
						Irisia.log(e);
					}
					return true;
				}
				return false;
			}
			public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
			{
				if(!Irisia.IN_DEV||world.isRemote||!player.capabilities.isCreativeMode)
					return stack;

				NBTTagCompound nbt=stack.hasTagCompound()?stack.getTagCompound():new NBTTagCompound();
				int mode=nbt.hasKey("mode")?nbt.getInteger("mode"):0;
				if(player.isSneaking()) // change mode
				{
					mode++;if(mode>4)mode=0;
					nbt.setInteger("mode",mode);
					stack.setTagCompound(nbt);
				}
				else // do debug
				{
					switch(mode)
					{
						case 1: // item
							InventoryPlayer inv=player.inventory;
							final int size=inv.getSizeInventory();
							for(int i=0;i<size;i++)
							{
								ItemStack stackInSlot=inv.getStackInSlot(i);
								if(i>=1&&stackInSlot!=null && stackInSlot.getItem()==Debugger)
								{
									ItemStack stackBehind=inv.getStackInSlot(i-1);
									if(stackBehind!=null)
									{
										Irisia.tellPlayer(stackBehind.getUnlocalizedName(),player);
										Irisia.tellPlayer("damage:"+stackBehind.getItemDamage(),player);
										Irisia.tellPlayer("class:"+stackBehind.getItem().getClass().getTypeName(),player);
										Irisia.tellPlayer("nbt:"+(stackBehind.hasTagCompound()?stackBehind.getTagCompound().toString():"null"),player);
									}
								}
							}
							break;
						case 2: // world
							WorldInfo info=world.getWorldInfo();
							Irisia.tellPlayer("world name "+info.getWorldName(),player);
							Irisia.tellPlayer("provider name "+world.getProviderName(),player);
							Irisia.tellPlayer("total time "+info.getWorldTotalTime(),player);
							Irisia.tellPlayer("time "+info.getWorldTime(),player);
							Irisia.tellPlayer("rain time "+info.getRainTime(),player);
							Irisia.tellPlayer("thunder time "+info.getThunderTime(),player);
							Irisia.tellPlayer("provider dimension "+world.provider.dimensionId,player);
							Irisia.tellPlayer("generator options "+info.getGeneratorOptions(),player);
							break;
						case 3: // player
							Irisia.tellPlayer("sender name "+player.getCommandSenderName(),player);
							Irisia.tellPlayer("display name "+player.getDisplayName(),player);
							Irisia.tellPlayer("uuid "+player.getUniqueID(),player);
							Irisia.tellPlayer("hp "+player.getHealth()+" / "+player.getMaxHealth(),player);
							Irisia.tellPlayer("yaw camera "+player.cameraYaw,player);
							Irisia.tellPlayer("yaw rotation head "+player.rotationYawHead,player);
							Irisia.tellPlayer("pitch camera "+player.cameraPitch,player);
							Irisia.tellPlayer("pitch rotation "+player.rotationPitch,player);
							break;
						case 4: // ores
							final int px=(int)player.posX,py=(int)player.posY,pz=(int)player.posZ;
							int amountOreBlock=0;
							for(int tx=px-5;tx<=px+5;tx++)
							{
								for(int tz=pz-5;tz<=pz+5;tz++)
								{
									for(int ty=py-5;ty<=py+5;ty++)
									{
										Block b=world.getBlock(tx,ty,tz);
										if(b instanceof OresAndMetal.Ore)
										{
											Irisia.tellPlayer(String.format("%d,%d,%d : %s",tx,ty,tz,b.getLocalizedName()),player);
											amountOreBlock++;
										}
									}
								}
							}
							Irisia.tellPlayer(String.format("搜寻完毕,共找到%d块Irisia矿物",amountOreBlock),player);
							break;
					}
				}
				return stack;
			}
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean flag)
			{
				NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				int mode=nbt.hasKey("mode")?nbt.getInteger("mode"):0;
				info.add(getModeName(getMode(itemStack)));
				info.add(StatCollector.translateToLocal(Keys.InfoCreativeOnly));
			}
			public String getItemStackDisplayName(ItemStack itemStack)
			{
				return "调试工具 "+getModeName(getMode(itemStack));
			}
		};
	}

	public final static Item ReturnCompass;
	static
	{
		ReturnCompass =new Item(){
			{
				this.setMaxStackSize(1);
				this.setMaxDamage(0);
			}
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				if(world.isRemote) return itemStack;

//				boolean isCreative=player.capabilities.isCreativeMode;
//				Irisia.tellPlayer("当前世界"+player.worldObj.getWorldInfo().getVanillaDimension(),player);

				NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();

				if(player.isSneaking()) // change stack's nbt
				{
					nbt.setInteger("world",world.provider.dimensionId);
					nbt.setDouble("posX",player.posX);
					nbt.setDouble("posY",player.posY);
					nbt.setDouble("posZ",player.posZ);
					Irisia.tellPlayerKey(Keys.InfoReturnWandHaveBind,player);
				}
				else // change player's pos
				{
					boolean can=true;
					// check cooldown
					if(nbt.hasKey("cd"))
					{
						if(nbt.getInteger("cd")>0)
						{
							if(!player.capabilities.isCreativeMode)
							{
								can=false;
								Irisia.tellPlayerKey(Keys.InfoReturnWandNotReady,player);
							}
						}
					}
					else
					{
						nbt.setInteger("cd",0);
					}
					// check pos data

					if(!nbt.hasKey("world")||
							!nbt.hasKey("posX")||
							!nbt.hasKey("posY")||
							!nbt.hasKey("posZ"))
					{
						can=false;
						Irisia.tellPlayerKey(Keys.InfoReturnWandNotBind,player);
					}
					else
					{
						int worldid=nbt.getInteger("world");
						if(worldid !=(world.provider.dimensionId))
						{
							can=false;
							Irisia.tellPlayerKey(Keys.InfoReturnWandWrongWorld,player);
						}
					}

					if(can)
					{
						nbt.setInteger("cd",60);
						double posX=nbt.getDouble("posX");
						double posY=nbt.getDouble("posY");
						double posZ=nbt.getDouble("posZ");
						player.setPositionAndUpdate(posX,posY,posZ);
					}
				}

				itemStack.setTagCompound(nbt);

				return itemStack;
			}
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean flag)
			{
				NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				int cd=0;
				if(nbt.hasKey("cd"))
					cd=nbt.getInteger("cd");
				info.add(StatCollector.translateToLocal(Keys.InfoReturnWandRemain)+cd);

				if(nbt.hasKey("world")
						&&nbt.hasKey("posX")
						&&nbt.hasKey("posY")
						&&nbt.hasKey("posZ"))
				{
					StringBuilder str=new StringBuilder(StatCollector.translateToLocal(Keys.InfoReturnWandBindTo));
					str.append(' ');str.append(nbt.getInteger("world"));
					str.append(" : ");str.append((long)nbt.getDouble("posX"));
					str.append(',');str.append((long)nbt.getDouble("posY"));
					str.append(',');str.append((long)nbt.getDouble("posZ"));

					info.add(str.toString());
				}
				else
				{
					info.add(StatCollector.translateToLocal(Keys.InfoReturnWandNotBind));
				}

			}

			public void onUpdate(ItemStack itemStack, World world, Entity entity, int meta, boolean falg)
			{
				if(!world.isRemote&&entity.ticksExisted%20==0)
				{
					if(itemStack.hasTagCompound())
					{
						NBTTagCompound nbt=itemStack.getTagCompound();
						if(nbt.hasKey("cd"))
						{
							int cd=nbt.getInteger("cd");
							if(cd>0) cd--;
							nbt.setInteger("cd",cd);
						}
						itemStack.setTagCompound(nbt);
					}
				}
			}

		};
	}

	public static final Item Astrolabe;
	static
	{
		Astrolabe=new Item()
		{
			@Override
			public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
			{
				if(!world.isRemote)
					Irisia.tellPlayer("星罗万象!参数:"+AstrologyManager.getFactor(player.worldObj),player);
				return stack;
			}
		};
	}
	private static final String[] items=new String[]{"item1","item2","item3","item4"};
	private static final String[] names=new String[]{"name1","name2","name3","name4"};
	public final static Item ArmorStorageBox; // 装备收纳盒
	public final static Item BaubleStorageBox; // 饰品收纳盒
	public final static Item Bell; // 铃铛
	static
	{
		ArmorStorageBox=new Item(){
			{
				this.setMaxDamage(0);
				this.setMaxStackSize(1);
			}
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				try
				{
					InventoryPlayer inv=player.inventory;
					NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
					byte i=-1;
					if(player.isSneaking()) // 潜行
					{
						if(nbt.hasNoTags()) // 没东西 就从身上脱下装备装进去
						{
							for(ItemStack stackInSlot:inv.armorInventory)
							{
								i++;
								if(stackInSlot==null) continue;
								NBTTagCompound itemInSlotNBT=stackInSlot.writeToNBT(new NBTTagCompound());
								String nameInSlot=stackInSlot.getDisplayName();
								nbt.setString(names[i],nameInSlot);
								nbt.setTag(items[i],itemInSlotNBT);
								inv.armorInventory[i]=null;
								inv.markDirty();
							}
						}
						else // 有东西就取出来
						{
							for(i=0;i<4;i++)
							{
								if(!nbt.hasKey(names[i])) continue;
								ItemStack stackInBox=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(items[i]));
								if(!inv.addItemStackToInventory(stackInBox))
									player.entityDropItem(stackInBox,0);
								nbt.removeTag(names[i]);
								nbt.removeTag(items[i]);
							}
						}
					}
					else // 非潜行
					{
						for(i=0;i<4;i++)
						{
							ItemStack stackInBox=null;
							ItemStack stackInSlot=null;
							if(nbt.hasKey(names[i]))
								stackInBox=ItemStack.loadItemStackFromNBT(nbt.getCompoundTag(items[i]));
							stackInSlot=inv.armorInventory[i];

							inv.armorInventory[i]=stackInBox;
							if(stackInSlot==null)
							{
								nbt.removeTag(names[i]);
								nbt.removeTag(items[i]);
							}
							else
							{
								nbt.setString(names[i],stackInSlot.getDisplayName());
								nbt.setTag(items[i],stackInSlot.writeToNBT(new NBTTagCompound()));
							}
						}
					}
					inv.markDirty();
					itemStack.setTagCompound(nbt);
				}
				catch (Exception e)
				{
					Irisia.log(e,player);
					Irisia.log(e);
				}
				return itemStack;
			}
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean flag)
			{ // todo 以后改成使用方法 另外加上Util.那个接口
				if(!itemStack.hasTagCompound())
					return;
				NBTTagCompound nbt=itemStack.getTagCompound();
				for(byte i=0;i<4;i++)
				{
					if(nbt.hasKey(names[i]))
					{
						info.add(nbt.getString(names[i]));
					}
				}
			}
		};
		BaubleStorageBox=new Item(){
			{
				this.setMaxDamage(0);
				this.setMaxStackSize(1);
			}
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				if(world.isRemote) return itemStack;

				try
				{
					IInventory inv=BaublesApi.getBaubles(player);
					NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();

					int size=inv.getSizeInventory();
//					Irisia.log("处理之前nbt:"+nbt.toString());

					if(player.isSneaking()) // 潜行
					{
//						Irisia.log("潜行状态");
//						Irisia.log("noTags? "+nbt.hasNoTags());
						if(nbt.hasNoTags()) // 没东西 就从身上脱下装备装进去
						{
							for(int i=0;i<size;i++)
							{
								ItemStack stackInSlot=inv.getStackInSlot(i);
								if(stackInSlot==null) continue;
								NBTTagCompound stackInSlotNbt=stackInSlot.writeToNBT(new NBTTagCompound());
								String stackInSlotName=stackInSlot.getDisplayName();

//								Irisia.log(i+" : stackInSlotName : "+stackInSlotName);

								nbt.setTag(items[i],stackInSlotNbt);
								nbt.setString(names[i],stackInSlotName);

								inv.setInventorySlotContents(i,null);
							}
						}
						else // 有东西就取出来
						{
							for(int i=0;i<size;i++)
							{
								if(!nbt.hasKey(names[i]) || inv.getStackInSlot(i)!=null) continue;
								NBTTagCompound stackInInvNbt=nbt.getCompoundTag(items[i]);
//								Irisia.log(i+" : 取出的东西是 : "+stackInInvNbt.toString());
								ItemStack stackInInvItem=ItemStack.loadItemStackFromNBT(stackInInvNbt);
								inv.setInventorySlotContents(i,stackInInvItem);
								nbt.removeTag(names[i]);
								nbt.removeTag(items[i]);
							}
						}
					}
					else // 非潜行
					{
						for(int i=0;i<size;i++)
						{
							ItemStack stackInInv=inv.getStackInSlot(i);

							boolean hasStackInInv=stackInInv!=null;
							boolean hasStackInBox=nbt.hasKey(names[i]);
//							Irisia.log("hasStackInInv: "+hasStackInInv+" , hasStackInBox: "+hasStackInBox);

							if(!(hasStackInBox||hasStackInInv)) continue;

							ItemStack stackInBoxItem=null;
							if(hasStackInBox)
							{
								NBTTagCompound stackInBoxNbt=nbt.getCompoundTag(items[i]);
								stackInBoxItem=ItemStack.loadItemStackFromNBT(stackInBoxNbt);
//								Irisia.log(i+" : 盒子里的东西是: "+stackInBoxNbt.toString());
								nbt.removeTag(names[i]);
								nbt.removeTag(items[i]);
							}

							if(stackInInv!=null)
							{
								nbt.setTag(items[i],stackInInv.writeToNBT(new NBTTagCompound()));
								nbt.setString(names[i],stackInInv.getDisplayName());
//								Irisia.log(i+" : 物品栏里的东西是: "+stackInInv.getDisplayName());
								inv.setInventorySlotContents(i,null);
							}
							if(stackInBoxItem!=null)
							{
								inv.setInventorySlotContents(i,stackInBoxItem);
							}
						}
					}
					inv.markDirty();
					itemStack.setTagCompound(nbt);
//					Irisia.log("处理之后nbt:"+nbt.toString());
				}
				catch (Exception e)
				{
					Irisia.log(e,player);
					Irisia.log(e);
				}
				return itemStack;
			}
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean flag)
			{ // todo 以后改成使用方法 另外加上Util.那个接口
				if(!itemStack.hasTagCompound())
					return;
				NBTTagCompound nbt=itemStack.getTagCompound();
				if(nbt.hasNoTags()) return;
				for(byte i=0;i<4;i++)
				{
					if(nbt.hasKey(names[i]))
					{
						info.add(nbt.getString(names[i]));
					}
				}
			}

		};

		Bell=new Item(){
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				if(world.isRemote)
					player.worldObj.playSoundAtEntity(player,Keys.SoundBell,1,1);
				return itemStack;
			}
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean flag)
			{
				; // todo 以后加上点什么东西
			}
		};
	}
}
