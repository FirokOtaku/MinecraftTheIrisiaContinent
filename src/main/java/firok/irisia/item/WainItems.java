package firok.irisia.item;

import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import firok.irisia.ability.CauseLightningChain;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class WainItems
{
	public final static ItemSword AliothTheInfinity;
	public final static EquipmentSets.Amulet PhecdaTheEcho;
	public final static ItemSword AlkaidTheImpulse;
	static
	{
		AliothTheInfinity =new AliothTheInfinity();
		PhecdaTheEcho =new PhecdaTheEcho();
		AlkaidTheImpulse=new AlkaidTheImpulse();
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
		@Override
		public EnumRarity getRarity(ItemStack itemStack)
		{
			return Materials.WainRarity;
		}
	}
	// 回音护符《天玑》(The Echo) Phecda
	public static class PhecdaTheEcho extends EquipmentUniqueBaubles.AbilityAmulet
	{
		@Override
		public void doAbility(ItemStack itemStack, EntityPlayer player)
		{ // 开启/关闭被动
			NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
			boolean isOn=nbt.hasKey("isOn")?nbt.getBoolean("isOn"):true;
			nbt.setBoolean("isOn",!isOn);
			itemStack.setTagCompound(nbt);
		}
		@Override
		public void onUpdate(ItemStack itemStack, World world, Entity entity, int flagInt, boolean flagBool)
		{ // 刷新技能cd 每十秒减1
			if(world.isRemote || entity.ticksExisted%200!=0) return;
			NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
			int cd=nbt.hasKey("cd")?nbt.getInteger("cd"):0;
			if(cd>0) cd--;
			nbt.setInteger("cd",cd);
			itemStack.setTagCompound(nbt);
		}
		@Override
		public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		{
			super.onItemRightClick(itemStack,world,player);
			if(world.isRemote)return itemStack;

			NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
			int cd=nbt.hasKey("cd")?nbt.getInteger("cd"):0;
			nbt.setInteger("cd",cd);
			boolean isOn=nbt.hasKey("isOn")?nbt.getBoolean("isOn"):true;
			nbt.setBoolean("isOn",!isOn);
			itemStack.setTagCompound(nbt);
			Irisia.log("isOn:"+isOn+" cd:"+cd,player); // 这里以后去掉

			return itemStack;
		}
	}
	// 脉冲之剑《天枢》(The Impulse) Alkaid
	public static class AlkaidTheImpulse extends ItemSword
	{
		public AlkaidTheImpulse()
		{
			super(ToolMaterial.IRON);
		}

		@Override
		public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
		{
//			NBTTagCompound nbt=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
//
//			long timeNow=target.worldObj.getTotalWorldTime();
//			long timeLast=nbt.hasKey("timeLast")?nbt.getLong("timeLast"):-1;
//			int keyLast=nbt.hasKey("keyLast")?nbt.getInteger("keyLast"):-1;
//
//			float damage=0;
//			target.attackEntityFrom(DamageSource.generic,damage);
//
//			if(damage>=15)
//				; // todo 以后播放一个音效
//
//			itemStack.damageItem(1, player);
//			return true;
			CauseLightningChain.FromEntity(target,5,10,2,5,0.5f);
			return super.hitEntity(itemStack, target, player);
		}

		//		@Override
//		public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
//		{
//			if(!world.isRemote && player.isSneaking())
//			{
//				NBTTagCompound tag=stack.hasTagCompound()?stack.getTagCompound():new NBTTagCompound();
//				short mutex=tag.hasKey("mutex")?tag.getShort("mutex"):0;
//				tag.setShort("mutex",mutex<MutexMax?(short)(mutex+1):0);
//				stack.setTagCompound(tag);
//			}
//			return super.onItemRightClick(stack, world, player);
//		}
//		@Override
//		public String getItemStackDisplayName(ItemStack itemStack)
//		{
//			int mutex=itemStack.hasTagCompound()?itemStack.getTagCompound().getInteger("mutex"):0x00;
//
//			return super.getItemStackDisplayName(itemStack)+(mutex);
//		}
		@Override
		public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_)
		{
			;
		}
	}
}
