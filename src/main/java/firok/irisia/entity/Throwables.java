package firok.irisia.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.DamageSources;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.apache.commons.lang3.text.translate.EntityArrays;

import java.util.List;

public class Throwables
{
	public static class EntityRunicArrow extends EntityThrowable
	{
		private float size=1;
		public EntityRunicArrow(World world,double x,double y,double z,float size)
		{
			super(world, x, y, z);
			this.size=size<1?1:size;
			this.motionX*=this.size*2f;
			this.motionY*=this.size*2f;
			this.motionZ*=this.size*2f;
		}
		public EntityRunicArrow(World world, double x, double y, double z)
		{
			this(world, x, y, z,1);
		}
		public EntityRunicArrow(World world,EntityLivingBase enlb,float size)
		{
			super(world,enlb);
			this.size=size<1?1:size;
			this.motionX*=this.size*2f;
			this.motionY*=this.size*2f;
			this.motionZ*=this.size*2f;
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if (mop.entityHit != null && mop.entityHit instanceof EntityLivingBase)
			{
				mop.entityHit.attackEntityFrom(DamageSources.MagicAmplificativeDamage, size*size*3);
				// ((EntityLivingBase)mop.entityHit) .addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,40,size-1));
				mop.entityHit.playSound("thaumcraft:jacobs",1,1); // todo 以后换成别的声音
			}

			if(!worldObj.isRemote)
				this.setDead();
		}

		@Override
		protected float getGravityVelocity()
		{
			return 0.01f;
		}
	}
	public static class EntityStone extends EntityThrowable
	{
		private int size=1;
		public EntityStone(World world,double x,double y,double z,int size)
		{
			super(world, x, y, z);
			this.size=size<1?1:size;
			this.motionX*=1-this.size*0.1f;
			this.motionY*=1-this.size*0.1f;
			this.motionZ*=1-this.size*0.1f;
		}
		public EntityStone(World world, double x, double y, double z)
		{
			this(world, x, y, z,1);
		}
		public EntityStone(World world,EntityLivingBase enlb,int size)
		{
			super(world,enlb);
			this.size=size<1?1:size;
			this.motionX*=1-this.size*0.1f;
			this.motionY*=1-this.size*0.1f;
			this.motionZ*=1-this.size*0.1f;
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if (mop.entityHit != null && mop.entityHit instanceof EntityLivingBase)
			{
				mop.entityHit.attackEntityFrom(DamageSources.StoneDamage, size*size*2);
				((EntityLivingBase)mop.entityHit)
						.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,40,size-1));
			}

			if(!worldObj.isRemote)
				this.setDead();
		}

		@Override
		protected float getGravityVelocity()
		{
			return (size+1)*(size+1)*0.01f;
		}
	}
	// NOTE 暂时没用 以后可能删掉
	public static class ThrowableBase extends EntityThrowable
	{
		protected float gravity=0.03F;

		public ThrowableBase(World world)
		{
			super(world);
		}
		public ThrowableBase(World world,EntityLivingBase enlb)
		{
			super(world,enlb);
		}
		public ThrowableBase(World world,double x,double y,double z)
		{
			super(world,x,y,z);
		}
		public ThrowableBase(World world,double x,double y,double z,float gravity)
		{
			super(world,x,y,z);
			this.gravity=gravity;
		}

		@Override
		protected float getGravityVelocity()
		{
			return gravity;
		}

		@Override
		protected void onImpact(MovingObjectPosition mbp)
		{
			;
		}

//		@Override
//		protected void entityInit()
//		{
//			;
//		}

//		@Override
//		public void readEntityFromNBT(NBTTagCompound nbt)
//		{
//			super.readEntityFromNBT(nbt);
//		}
//
//		@Override
//		public void writeEntityToNBT(NBTTagCompound nbt)
//		{
//			super.writeEntityToNBT(nbt);
//		}

//		@Override
//		public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
//		{
//			super.setThrowableHeading(p_70186_1_, p_70186_3_,p_70186_5_, p_70186_7_, p_70186_8_);
//		}

	}
}