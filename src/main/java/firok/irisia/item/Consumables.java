package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.common.GashaponManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class Consumables
{
	public final static Item Gashapon;
	static
	{
		Gashapon=new Item()
		{
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List infos, boolean p_77624_4_)
			{
				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				String key=tag.hasKey("key")?tag.getString("key"):"null_table";
				float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;

				infos.add(new StringBuffer()
						.append("Key : ")
						.append(key)
						.toString());
				infos.add(new StringBuffer().append("Bonus :")
						.append(bonus*100)
						.append("%")
						.toString());
			}
			@Override
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				if(world.isRemote)
					return itemStack;

				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				String key=tag.hasKey("key")?tag.getString("key"):"null_table";
				float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;
				if(player.isSneaking())
				{
					List<ItemStack> rolls=GashaponManager.rollItemsFromTableWithBonus(key,bonus);
					for(ItemStack is:rolls)
					{
						player.entityDropItem(is,0);
					}
					if(!player.capabilities.isCreativeMode)
						itemStack.stackSize--;
				}
				else
				{
					player.addChatComponentMessage(new ChatComponentText(new StringBuffer()
							.append("Key : ")
							.append(key)
							.append(", Bonus :")
							.append(bonus*100)
							.append("%")
							.toString()));
				}
				return itemStack;
			}
		};
	}
}
