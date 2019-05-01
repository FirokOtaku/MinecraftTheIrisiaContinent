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
import net.minecraft.block.BlockOre;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import java.util.List;

public class Tools
{
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
					case 1: return "物品调试(未添加)";
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
							break;
						case 2: // world
							Irisia.tellPlayer("world name "+world.getWorldInfo().getWorldName(),player);
							Irisia.tellPlayer("provider name "+world.getProviderName(),player);
							Irisia.tellPlayer("total time "+world.getWorldInfo().getWorldTotalTime(),player);
							Irisia.tellPlayer("time "+world.getWorldInfo().getWorldTime(),player);
							Irisia.tellPlayer("rain time "+world.getWorldInfo().getRainTime(),player);
							Irisia.tellPlayer("thunder time "+world.getWorldInfo().getThunderTime(),player);
							Irisia.tellPlayer("vanilla dimension "+world.getWorldInfo().getVanillaDimension(),player);
							Irisia.tellPlayer("generator options "+world.getWorldInfo().getGeneratorOptions(),player);
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
	static
	{
		ArmorStorageBox=new Item(){
			final String[] items=new String[]{"item1","item2","item3","item4"};
			final String[] names=new String[]{"name1","name2","name3","name4"};
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
		ArmorStorageBox.setMaxDamage(0);
		ArmorStorageBox.setMaxStackSize(1);
	}
}
