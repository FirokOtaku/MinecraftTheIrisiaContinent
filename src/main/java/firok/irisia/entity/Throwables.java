package firok.irisia.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.DamageSources;
import firok.irisia.block.DecorationBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.apache.commons.lang3.text.translate.EntityArrays;
import thaumcraft.common.Thaumcraft;
import java.util.List;
import firok.irisia.Util;

public class Throwables
{
	public static class EntitySupernovaOrb extends EntityThrowable
	{
		private int size=1;
		private int countRemoved=0;
		public int maxCountRemoved(){return size*8;}

		public EntitySupernovaOrb(World world)
		{
			super(world);
		}
		public EntitySupernovaOrb(World world,double x,double y,double z,int size)
		{
			super(world, x, y, z);
			this.size=size<1?1:size;
//			this.motionX*=this.size*2f;
//			this.motionY*=this.size*2f;
//			this.motionZ*=this.size*2f;
		}
		public EntitySupernovaOrb(World world, double x, double y, double z)
		{
			this(world, x, y, z,1);
		}
		public EntitySupernovaOrb(World world,EntityLivingBase enlb,int size)
		{
			super(world,enlb);
			this.size=size<1?1:size;
//			this.motionX*=this.size*2f;
//			this.motionY*=this.size*2f;
//			this.motionZ*=this.size*2f;
		}

		@Override
		public void onUpdate()
		{
			super.onUpdate();

			if(!worldObj.isRemote&&this.ticksExisted%5==0)
			{
				// todo 现在只去除水方块 以后加上点燃实体
				for(int x=(int)posX-1;x<=(int)posX+1;x++)
				{
					for(int y=(int)posY-1;y<=(int)posY+1;y++)
					{
						for(int z=(int)posZ-1;z<=(int)posZ+1;z++)
						{
							Block b2remove=worldObj.getBlock(x,y,z);
							if(b2remove==Blocks.water||b2remove==Blocks.flowing_water)
							{
								worldObj.setBlockToAir(x,y,z);
								countRemoved++;

								if(countRemoved>=maxCountRemoved())
								{
									this.setDead();
									// todo 这里以后可能改一下效果
									worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 6f, true);
								}
							}
						}
					}
				}
			}
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if(mop!=null)
			{
				int cx=mop.blockX,cy=mop.blockY,cz=mop.blockZ;
				float power=(maxCountRemoved()-countRemoved)/4;
				if(power>=1)
				{
					int tpower=(int)power;
					for(int x=cx-tpower;x<=cx+tpower;x++)
					{
						for(int y=cy-tpower;y<=cy+tpower;y++)
						{
							for(int z=cz-tpower;z<=cy+tpower;z++)
							{
								Block b2lava=worldObj.getBlock(x,y,z);
								if(b2lava instanceof BlockStone)
								{
									worldObj.setBlock(x,y,z,Blocks.lava);
								}
							}
						}
					}
				}
				if(power>0)
					worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 6f, true);

			}
			if(!worldObj.isRemote)
				this.setDead();

		}

		@Override
		protected float getGravityVelocity()
		{
			return 0.01f;
		}

		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			size=nbt.hasKey("sizeOrb")?nbt.getInteger("sizeOrb"):1;
			countRemoved=nbt.hasKey("countRemoved")?nbt.getInteger("countRemoved"):0;
		}
		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setInteger("sizeOrb",size);
			nbt.setInteger("countRemoved",countRemoved);
		}
	}
	public static class EntityWitherOrb extends EntityThrowable
	{
		private float size=1;
		public EntityWitherOrb(World world)
		{
			super(world);
		}
		public EntityWitherOrb(World world,double x,double y,double z,float size)
		{
			super(world, x, y, z);
			this.size=size<1?1:size;
//			this.motionX*=this.size*2f;
//			this.motionY*=this.size*2f;
//			this.motionZ*=this.size*2f;
		}
		public EntityWitherOrb(World world, double x, double y, double z)
		{
			this(world, x, y, z,1);
		}
		public EntityWitherOrb(World world,EntityLivingBase enlb,float size)
		{
			super(world,enlb);
			this.size=size<1?1:size;
//			this.motionX*=this.size*2f;
//			this.motionY*=this.size*2f;
//			this.motionZ*=this.size*2f;
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if(mop!=null)
			{
				if(mop.typeOfHit==MovingObjectPosition.MovingObjectType.ENTITY)
				{
					if(mop.entityHit!=null && mop.entityHit instanceof EntityLivingBase)
					{
						int dur=(int)(80*size);if(dur<20)dur=20;
						int lv=(int)(size/2);if(lv<0)lv=0;if(lv>4)lv=4;
						((EntityLivingBase) mop.entityHit)
								.addPotionEffect(new PotionEffect(Potion.wither.id,dur,lv));
					}
				}
				else
				{
					int cx=mop.blockX,cy=mop.blockY,cz=mop.blockZ;
					int radius=(int)size;
					for(int x=cx-radius;x<=cx+radius;x++)
					{
						for(int y=cy-radius;y<=cy+radius;y++)
						{
							for(int z=cz-radius;z<=cz+radius;z++)
							{
								Block b2c=worldObj.getBlock(x,y,z);
								if(b2c instanceof BlockLeavesBase)
								{
									worldObj.setBlockToAir(x,y,z);
								}
//								else if(b2c==Blocks.dirt && worldObj.getBlockMetadata(x,y,z)==1)
//								{
//									worldObj.setBlockMetadataWithNotify(x,y,z,2,2);
//								}
							}
						}
					}
				}
			}
			if(!worldObj.isRemote)
				this.setDead();

		}

		@Override
		protected float getGravityVelocity()
		{
			return 0.01f;
		}
		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			size=nbt.hasKey("sizeOrb")?nbt.getFloat("sizeOrb"):1;
		}
		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setFloat("sizeOrb",size);
		}
	}
	public static class EntityRunicArrow extends EntityThrowable
	{
		private float size=1;
		public EntityRunicArrow(World world)
		{
			super(world);
		}
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
		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			size=nbt.hasKey("sizeOrb")?nbt.getFloat("sizeOrb"):1;
		}
		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setFloat("sizeOrb",size);
		}
	}
	public static class EntityStone extends EntityThrowable
	{
		private int size=1;
		public EntityStone(World world)
		{
			super(world);
		}
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
		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			size=nbt.hasKey("sizeOrb")?nbt.getInteger("sizeOrb"):1;
		}
		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setInteger("sizeOrb",size);
		}
	}
	public static class EntityBurningSpearArrow extends EntityThrowable
	{
		private double fromPosX,fromPosY,fromPosZ; // 开始位置
		private int countCharge; // 充能数量
		private EntityLivingBase thrower; // 投掷者
		private boolean shouldPull;

		private static final double DAMAGE_MAX=24; // 最大伤害
		private static final double DISTANCE_MAX=16; // 最远距离 最小伤害
		private static final double DISTANCE_MIN=4; // 最近距离 最大伤害
		private static final double FACTOR_DISTANCE_MAX=0.15; // 最远距离 攻击力缩减0.4倍
		private static final double FACTOR_DISTANCE_MIN=1.0; // 最近距离 攻击力不变
		private static final int CHARGE_MAX=30; // 最大充能时间
		private static final int CHARGE_MIN=10; // 最小充能时间
		private static final double FACTOR_CHARGE_MAX=1.4; // 最大充能之后 攻击力变为1.4倍
		private static final double FACTOR_CHARGE_MIN=0.5; // 小于最小充能 攻击力为0.5倍


		private double getDistance()
		{
			return this.getDistance(fromPosX,fromPosY,fromPosZ);
		}
		private double getFactorDistance()
		{
//			return distance<=DISTANCE_MIN?FACTOR_DISTANCE_MIN:
//					distance>=DISTANCE_MAX?FACTOR_DISTANCE_MAX:
//					FACTOR_DISTANCE_MAX+(FACTOR_DISTANCE_MIN-FACTOR_DISTANCE_MAX)*(distance-DISTANCE_MIN);
//
			return Util.getValueWithFactor(getDistance(),FACTOR_DISTANCE_MIN,FACTOR_DISTANCE_MAX,DISTANCE_MIN,DISTANCE_MAX);
		}
		private double getFactorCharge()
		{
//			return countCharge<=CHARGE_MIN?FACTOR_CHARGE_MIN:
//					countCharge>=CHARGE_MAX?FACTOR_CHARGE_MAX:
//					FACTOR_CHARGE_MIN+(FACTOR_CHARGE_MAX-FACTOR_CHARGE_MIN)*(countCharge-CHARGE_MIN);
			return Util.getValueWithFactor(countCharge,FACTOR_CHARGE_MIN,FACTOR_CHARGE_MAX,CHARGE_MIN,CHARGE_MAX);
		}
		private double getDamage()
		{
			return DAMAGE_MAX*getFactorDistance()*getFactorCharge();
		}
		public boolean pullThrower()
		{
			if(shouldPull && thrower!=null && !worldObj.isRemote
					&& thrower.worldObj==this.thrower.worldObj
					&& thrower.isEntityAlive()
					&& this.getDistanceToEntity(thrower)<=2*DISTANCE_MAX)
			{
				double mX=this.posX-thrower.posX;
				double mY=this.posY-thrower.posY;
				double mZ=this.posZ-thrower.posZ;
				// fixme low 需要修复这个地方 现在拉不动玩家
				thrower.motionX+=MathHelper.sqrt_double(mX);
				thrower.motionY+=MathHelper.sqrt_double(mY);
//				thrower.motionY+=mY/2;
				thrower.motionZ+=MathHelper.sqrt_double(mZ);
			}
			return shouldPull;
		}

		public EntityBurningSpearArrow(World world)
		{
			super(world);
		}
		public EntityBurningSpearArrow(World world,int charge,EntityLivingBase thrower,boolean shouldPull)
		{
			super(world,thrower);
			this.fromPosX=thrower.posX;
			this.fromPosY=thrower.posY;
			this.fromPosZ=thrower.posZ;
			this.countCharge=charge;
			this.thrower=thrower;
			this.shouldPull=shouldPull;
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if (mop.entityHit != null && mop.entityHit instanceof EntityLivingBase)
			{
				pullThrower();
				mop.entityHit.attackEntityFrom(DamageSources.BurningSpearDamage, (float)getDamage());
				mop.entityHit.setFire(2);
			}

			if(!worldObj.isRemote)
				this.setDead();
		}

		@Override
		protected float getGravityVelocity()
		{
			return 0.02f;
		}
		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			fromPosX=nbt.hasKey("fromPosX")?nbt.getDouble("fromPosX"):0;
			fromPosY=nbt.hasKey("fromPosY")?nbt.getDouble("fromPosY"):0;
			fromPosZ=nbt.hasKey("fromPosZ")?nbt.getDouble("fromPosZ"):0;
			countCharge=nbt.hasKey("charge")?nbt.getInteger("charge"):0;
		}
		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setDouble("fromPosX",fromPosX);
			nbt.setDouble("fromPosY",fromPosY);
			nbt.setDouble("fromPosZ",fromPosZ);
			nbt.setInteger("charge",countCharge);
		}
	}
	public static class EntityThunderBall extends EntityThrowable
	{
		public EntityThunderBall(World world)
		{
			super(world);
		}
		public EntityThunderBall(World world, EntityLivingBase thrower, float speed)
		{
			super(world,thrower);
			this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * speed);
			this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * speed);
			this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * speed);
			this.setLocationAndAngles(thrower.posX,thrower.posY,thrower.posZ,thrower.rotationYaw,thrower.rotationPitch);
		}

		@Override
		public void onUpdate()
		{
			super.onUpdate();

			;

			if(this.ticksExisted % 4 ==0)
			{
				worldObj.spawnParticle("flame",posX,posY,posZ,0,0,0);
				//Thaumcraft.proxy.
			}

			if(this.ticksExisted % 10 ==0)
			{
				Thaumcraft.proxy.sparkle((float)posX + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F,
						(float)posY + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F,
						(float)posZ + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F,
						2.0F + worldObj.rand.nextFloat(),
						2, 0.05F + worldObj.rand.nextFloat() * 0.05F);
				//Thaumcraft.proxy.
			}
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if(mop.entityHit!=null)
			{
				Entity en=mop.entityHit;
				en.worldObj.playSoundEffect(en.posX, en.posY, en.posZ, "thaumcraft:shock", 0.25F, 1.0F);
				if(en instanceof EntityLivingBase)
				{
					EntityLivingBase enlb=(EntityLivingBase)en;
					enlb.attackEntityFrom(DamageSources.LightningDamege,1);
				}
			}
		}

		protected float getGravityVelocity()
		{
			return 0F;
		}
	}
	public static class EntityMagicalDirtBall extends EntityThrowable
	{
		public EntityMagicalDirtBall(World world)
		{
			super(world);
		}
		public EntityMagicalDirtBall(World world, EntityLivingBase thrower, float speed)
		{
			super(world,thrower);
			this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * speed);
			this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * speed);
			this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * speed);
			this.setLocationAndAngles(thrower.posX,thrower.posY,thrower.posZ,thrower.rotationYaw,thrower.rotationPitch);
		}

		@Override
		protected void onImpact(MovingObjectPosition mop)
		{
			if(worldObj.isRemote)
				return;

			worldObj.playSoundEffect(posX,posY,posZ,DecorationBlocks.MagicalDirt.stepSound.getBreakSound(),1,1);
			int cx=(int)posX;
			int cy=(int)posY;
			int cz=(int)posZ;
			if(mop.typeOfHit== MovingObjectPosition.MovingObjectType.BLOCK)
			{
				List entities=worldObj.getEntitiesWithinAABBExcludingEntity(this,
						AxisAlignedBB.getBoundingBox(
								cx-2,cy-2,cz-2,
								cx+2,cy+2,cz+2));
				for(Object obj:entities)
				{
					Entity entity = (Entity)obj;
					entity.motionY+=0.5;
				}
			}
			else if(mop.typeOfHit== MovingObjectPosition.MovingObjectType.ENTITY)
			{
				;
			}
			setMagicalDirt(worldObj,cx,cy,cz);
			setMagicalDirt(worldObj,cx-1,cy,cz);
			setMagicalDirt(worldObj,cx+1,cy,cz);
			setMagicalDirt(worldObj,cx,cy+1,cz);
			setMagicalDirt(worldObj,cx,cy-1,cz);
			setMagicalDirt(worldObj,cx,cy,cz+1);
			setMagicalDirt(worldObj,cx,cy,cz-1);

			setDead();
		}
		@Override
		protected float getGravityVelocity()
		{
			return 0.02F;
		}

		public static void setMagicalDirt(World world,int x,int y,int z)
		{
			if(world.getBlock(x,y,z)== Blocks.air)
			{
				world.setBlock(x,y,z,DecorationBlocks.MagicalDirt);
			}
		}
	}
	public static class EntityMagicLightBlockBall extends EntityThrowable
	{ // fixme high 还没写
		private int size=1;
		private float gra=0;
		public EntityMagicLightBlockBall(World world)
		{
			super(world);
		}
		public EntityMagicLightBlockBall(World world,double x,double y,double z,int size)
		{
			super(world, x, y, z);
			this.size=size<1?1:size;
			this.motionX*=1-this.size*0.1f;
			this.motionY*=1-this.size*0.1f;
			this.motionZ*=1-this.size*0.1f;
		}
		public EntityMagicLightBlockBall(World world, double x, double y, double z)
		{
			this(world, x, y, z,1);
		}
		public EntityMagicLightBlockBall(World world,EntityLivingBase enlb,int size)
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
		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			size=nbt.hasKey("sizeOrb")?nbt.getInteger("sizeOrb"):1;
		}
		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setInteger("sizeOrb",size);
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