package firok.irisia.entity;

import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.item.RawMaterials;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Monsters
{
	public static class GhostKnocker extends Entity
	{
		public GhostKnocker(World world)
		{
			this(world,0,0,0);
		}
		public GhostKnocker(World world, double x, double y, double z)
		{
			super(world);
			this.setPosition(x,y,z);
		}
		@Override
		protected void entityInit()
		{
			;
		}
		private long lastTimeFindDoor=-160;
		private boolean hasDoor=false;
		private int doorX,doorY,doorZ;
		public boolean isNeedFindDoor() // 是否需要找门
		{
			return !isDoorOK()||this.ticksExisted-lastTimeFindDoor>=160;
		}
		public boolean findDoor() // 用来找门
		{
			lastTimeFindDoor=this.ticksExisted;
			hasDoor=false;
			for(int x=(int)this.posX-4;x<=this.posX+4;x++)
			{
				for(int z=(int)this.posZ-4;z<=this.posZ+4;z++)
				{
					for(int y=(int)this.posY-2;y<=this.posY+2;y++)
					{
						if(worldObj.getBlock(x,y,z).getUnlocalizedName().contains("door"))
						{
							doorX=x;
							doorY=y;
							doorZ=z;
							Irisia.log("x,y,z = " + x + "," + y +","+ z);
							hasDoor=true;
							break;
						}
					}
				}
			}
			Irisia.log("找到门:"+hasDoor);
			return hasDoor;
		}
		public boolean isDoorOK()
		{
			return hasDoor&&worldObj.getBlock(doorX,doorY,doorZ)==Blocks.wooden_door;
		}
		public void playSoundAtDoor()
		{
			if(isDoorOK())
			{
				Irisia.log("play sound $ "+doorX+","+doorY+","+doorZ);
				worldObj.playSoundEffect(doorX,doorY,doorZ, Keys.SoundDoor,1,1);
			}
		}
		@Override
		public void onEntityUpdate()
		{
			super.onEntityUpdate();

//			if(this.worldObj.isRemote)
//				return;

			if(this.ticksExisted%80==0)
			{
				EntityPlayer player=worldObj.getClosestPlayer(posX,posY,posZ,20);
				if(player!=null)
					player.addPotionEffect(new PotionEffect(Potion.weakness.id,85,0));
			}

			if(isNeedFindDoor())
			{
				findDoor();
			}
			if(ticksExisted%120==0&&isDoorOK())
			{
				playSoundAtDoor();
			}
			if(ticksExisted>1200)
				setDead();
		}

		@Override
		protected void readEntityFromNBT(NBTTagCompound nbt)
		{
			this.posX=nbt.getDouble("posX");
			this.posY=nbt.getDouble("posY");
			this.posZ=nbt.getDouble("posZ");
			this.ticksExisted=nbt.getInteger("ticksExisted");
		}

		@Override
		protected void writeEntityToNBT(NBTTagCompound nbt)
		{
			nbt.setDouble("posX",posX);
			nbt.setDouble("posY",posY);
			nbt.setDouble("posZ",posZ);
			nbt.setInteger("ticksExisted",ticksExisted);
		}
	}
	public static class AncientCube extends EntityMob implements IRangedAttackMob
	{
		private static final IEntitySelector attackEntitySelector=(en)->en instanceof EntityPlayer;
		public int tick=200;
		public AncientCube(World world)
		{
			this(world,0,0,0);
		}
		public AncientCube(World world,double x,double y,double z)
		{
			super(world);
			this.setSize(1,1);
			this.setPositionAndUpdate(x,y,z);
			this.isImmuneToFire = true;
			this.getNavigator().setCanSwim(true);
			this.tasks.addTask(0, new EntityAISwimming(this));
//			this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
			this.tasks.addTask(5, new EntityAIWander(this, 3.0D));
			this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
			this.tasks.addTask(7, new EntityAILookIdle(this));
//			this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
//			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, attackEntitySelector));
			this.experienceValue = 5;
		}

		/**
		 * Attack the specified entity using a ranged attack.
		 */
		public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_)
		{
			this.func_82216_a(0, target);
		}
		private void func_82216_a(int speed, EntityLivingBase target)
		{
			this.func_82209_a(speed,
					target.posX, target.posY + (double)target.getEyeHeight() * 0.5D, target.posZ,
					speed == 0 && this.rand.nextFloat() < 0.001F);
		}
		private void func_82209_a(int speed, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_)
		{
			this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1014, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
			double fromX = this.getFromX(speed);
			double fromY = this.getFromY(speed);
			double fromZ = this.getFromZ(speed);
			double speedX = p_82209_2_ - fromX;
			double speedY = p_82209_4_ - fromY;
			double speedZ = p_82209_6_ - fromZ;
			EntityWitherSkull entitywitherskull = new EntityWitherSkull(this.worldObj, this, speedX, speedY, speedZ);

			if (p_82209_8_)
			{
				entitywitherskull.setInvulnerable(true);
			}

			entitywitherskull.posY = fromY;
			entitywitherskull.posX = fromX;
			entitywitherskull.posZ = fromZ;
			this.worldObj.spawnEntityInWorld(entitywitherskull);
		}
		private double getFromX(int p_82214_1_)
		{
			if (p_82214_1_ <= 0)
			{
				return this.posX;
			}
			else
			{
				float f = (this.renderYawOffset + (float)(180 * (p_82214_1_ - 1))) / 180.0F * (float)Math.PI;
				float f1 = MathHelper.cos(f);
				return this.posX + (double)f1 * 1.3D;
			}
		}
		private double getFromY(int p_82208_1_)
		{
			return p_82208_1_ <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
		}
		private double getFromZ(int p_82213_1_)
		{
			if (p_82213_1_ <= 0)
			{
				return this.posZ;
			}
			else
			{
				float f = (this.renderYawOffset + (float)(180 * (p_82213_1_ - 1))) / 180.0F * (float)Math.PI;
				float f1 = MathHelper.sin(f);
				return this.posZ + (double)f1 * 1.3D;
			}
		}


		/**
		 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
		 * use this to react to sunlight and start to burn.
		 */
		public void onLivingUpdate()
		{
//			this.updateArmSwingProgress();
//			float f = this.getBrightness(1.0F);
//
//			if (f > 0.5F)
//			{
//				this.entityAge += 2;
//			}
//
//			super.onLivingUpdate();
			super.onLivingUpdate();

//			tick--;
//			if(!worldObj.isRemote && tick<=0)
//			{
//				this.setDead();
//				worldObj.createExplosion(this,posX,posY,posZ,1,true);
//				Irisia.log("Ancient Cube Died.");
//			}

//			if(ticksExisted%20==0)
//			{
//				Irisia.log("cube : "+ticksExisted/20);
//			}
		}

		public void onUpdate()
		{
			super.onUpdate();
		}

		// 改了索敌范围
		protected Entity findPlayerToAttack()
		{
			EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 24.0D);
			return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
		}


		// 改了平A间隔
		protected void attackEntity(Entity en, float distance)
		{
			if (this.attackTime <= 0 && distance < 2.0F && en.boundingBox.maxY > this.boundingBox.minY && en.boundingBox.minY < this.boundingBox.maxY)
			{
				this.attackTime =80;
				this.attackEntityAsMob(en);
			}
		}

		@Override
		protected boolean interact(EntityPlayer player)
		{
			this.kill();
			return true;
		}

		protected void dropRareDrop(int p_70600_1_)
		{
			this.entityDropItem(new ItemStack(RawMaterials.AncientMachinePart), 0.0F);
		}

		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			this.tick=nbt.hasKey("tick")?nbt.getInteger("tick"):200;
		}

		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setInteger("tick",tick);
		}
	}

	public static class CrusherDog extends EntityWolf
	{
		public int cdCrush=120;
		public static int cdCrushMax=120;
		public CrusherDog(World world)
		{
			super(world);
		}
		public CrusherDog(World world,double x,double y,double z)
		{
			super(world);
			this.setPosition(x,y,z);
		}

		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setInteger("cdCrush",cdCrush);
		}
		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			this.cdCrush=nbt.getInteger("cdCrush");
		}

		@Override
		protected void updateAITasks()
		{
			super.updateAITasks();
			--cdCrush;
			if(cdCrush<=0&&ticksExisted%10!=0)
			{
				if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				{
					EntityLivingBase targetAttack=getAttackTarget();
					if(targetAttack==null||targetAttack.getDistanceToEntity(this)>10) return;

					int posX,posY,posZ;
					posY = MathHelper.floor_double(this.posY);
					posX = MathHelper.floor_double(this.posX);
					posZ = MathHelper.floor_double(this.posZ);
					boolean hasBreakBlock = false;

					for (int offsetX = -1; offsetX <= 1; ++offsetX)
					{
						for (int offsetZ = -1; offsetZ <= 1; ++offsetZ)
						{
							for (int offsetY = 0; offsetY <= 3; ++offsetY)
							{
								int targetX = posX + offsetX;
								int targetY = posY + offsetY;
								int targetZ = posZ + offsetZ;
								Block block = this.worldObj.getBlock(targetX, targetY, targetZ);

								if (!block.isAir(worldObj, targetX, targetY, targetZ)
										&& block.canEntityDestroy(worldObj, targetX, targetY, targetZ, this))
								{
									hasBreakBlock = this.worldObj.func_147480_a(targetX, targetY, targetZ, true)
											|| hasBreakBlock;
								}
							}
						}
					}

					if (hasBreakBlock)
					{
						this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1012, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
						cdCrush=cdCrushMax;
					}
				}
			}
		}
	}
}
