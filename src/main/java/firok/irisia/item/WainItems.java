package firok.irisia.item;

import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WainItems
{
	public static ItemSword AliothTheInfinity;
	static
	{
		AliothTheInfinity=new AliothTheInfinity();
	}

	// 无限之剑《玉衡》（The Infinity） Alioth
	public static class AliothTheInfinity extends ItemSword
	{
		public AliothTheInfinity()
		{
			super(Materials.Alioth);
		}

		@Override
		public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		{
			if(world.isRemote || !Irisia.IN_DEV)
				return itemStack;

			NBTTagCompound tag;
			if(itemStack.hasTagCompound())
			{
				tag=itemStack.getTagCompound();
			}
			else
			{
				tag=new NBTTagCompound();
			}

			long lastSwing;
			if(tag.hasKey("lastSwing"))
			{
				lastSwing=tag.getLong("lastSwing");
			}
			else
			{
				lastSwing=Minecraft.getSystemTime()-30000;
			}
			lastSwing-=300000;

			Irisia.log(new StringBuffer().append("lastSwing : ").append(lastSwing),player);

			tag.setLong("lastSwing",lastSwing);
			itemStack.setTagCompound(tag);
			return itemStack;
		}
		@Override
		public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
		{
			return false;
		}
		@Override
		public boolean hitEntity(ItemStack itemStack, EntityLivingBase en1, EntityLivingBase en2)
		{
			if(en1.worldObj.isRemote)
				return false;

			//itemStack.damageItem(1, en2);
			NBTTagCompound tag;
			if(itemStack.hasTagCompound())
			{
				tag=itemStack.getTagCompound();
			}
			else
			{
				tag=new NBTTagCompound();
			}

			long lastSwing;
			long nowTime=Minecraft.getSystemTime();
			if(tag.hasKey("lastSwing"))
			{
				lastSwing=tag.getLong("lastSwing");
			}
			else
			{
				lastSwing=nowTime;
			}

			int extraDamage=(int)((nowTime-lastSwing)/30000); // 30秒+1
			if(extraDamage>60)
				extraDamage=60;

			Irisia.log(new StringBuffer()
							.append("lastSwing : ")
							.append(lastSwing)
							.append(", nowTime : ")
							.append(nowTime)
							.append(", extraDamage : ")
							.append(extraDamage),
					(EntityPlayer) en2);

			float hp=en1.getHealth();
			if(hp>extraDamage)
				en1.setHealth(hp-extraDamage);
			else
			{
				en1.onDeath(DamageSources.AliothDamage);
				en1.setDead();
			}

			tag.setLong("lastSwing",nowTime);
			itemStack.setTagCompound(tag);
			return true;
		}
		@Override
		public boolean onBlockDestroyed(ItemStack itemStack, World world, Block block, int x, int y, int z, EntityLivingBase en)
		{
			return false;
		}
	}
}
