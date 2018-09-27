package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class Tools
{
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
