package firok.irisia.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.common.EventLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class Summons
{
	public static abstract class SummonedGolem extends EntityIronGolem
	{
		protected int attackTimer;
		public SummonedGolem(World world)
		{
			super(world);
		}
		public SummonedGolem(World world,double x,double y,double z)
		{
			super(world);
			this.setPosition(x,y,z);
		}
		// todo 以后改一下掉落物
		protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
		{
			//int j = this.rand.nextInt(3);
			int k;

//			for (k = 0; k < j; ++k)
//			{
//				this.func_145778_a(Item.getItemFromBlock(Blocks.red_flower), 1, 0.0F);
//			}

			k = 3 + this.rand.nextInt(3);

			for (int l = 0; l < k; ++l)
			{
				this.dropItem(Items.iron_ingot, 1);
			}
		}
		@Override
		protected void updateAITick()
		{
			// note 不检查村庄
		}
		public void onDeath(DamageSource ds)
		{
			// Irisia.log(EventLoader.toString(ds));
			if (ForgeHooks.onLivingDeath(this, ds)) return;
			Entity entity = ds.getEntity();
			EntityLivingBase entitylivingbase = this.func_94060_bK();

			if (this.scoreValue >= 0 && entitylivingbase != null)
			{
				entitylivingbase.addToPlayerScore(this, this.scoreValue);
			}

			if (entity != null)
			{
				entity.onKillEntity(this);
			}

			this.dead = true;
			this.func_110142_aN().func_94549_h();

			if (!this.worldObj.isRemote)
			{
				int i = 0;

				if (entity instanceof EntityPlayer)
				{
					i = EnchantmentHelper.getLootingModifier((EntityLivingBase)entity);
				}

				captureDrops = true;
				capturedDrops.clear();
				int j = 0;

				if (this.func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
				{
					this.dropFewItems(this.recentlyHit > 0, i);
					this.dropEquipment(this.recentlyHit > 0, i);

					if (this.recentlyHit > 0)
					{
						j = this.rand.nextInt(200) - i;

						if (j < 5)
						{
							this.dropRareDrop(j <= 0 ? 1 : 0);
						}
					}
				}

				captureDrops = false;

				if (!ForgeHooks.onLivingDrops(this, ds, capturedDrops, i, recentlyHit > 0, j))
				{
					for (EntityItem item : capturedDrops)
					{
						worldObj.spawnEntityInWorld(item);
					}
				}
			}

			this.worldObj.setEntityState(this, (byte)3);
		}
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setBoolean("PlayerCreated", this.isPlayerCreated());
		}
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			this.setPlayerCreated(nbt.getBoolean("PlayerCreated"));
		}

		//		// note 不用管
//		public void onLivingUpdate()
//		{
//			super.onLivingUpdate();
//
//			if(this.regen>0&&this.ticksExisted%20==0)
//				this.heal(regen);
//		}
//		@SideOnly(Side.CLIENT)
//		public void handleHealthUpdate(byte meta)
//		{
//			if (meta == 4)
//			{
//				this.attackTimer = 10;
//				this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
//			}
//			else if (meta == 11)
//			{
//				this.holdRoseTick = 400;
//			}
//			else
//			{
//				super.handleHealthUpdate(meta);
//			}
//		}
//		@SideOnly(Side.CLIENT)
//		public int getAttackTimer()
//		{
//			return this.attackTimer;
//		}
//		// note 不用管
//		public void setHoldingRose(boolean flag)
//		{
//			this.holdRoseTick = flag ? 400 : 0;
//			this.worldObj.setEntityState(this, (byte)11);
//		}
//		protected String getHurtSound()
//		{
//			return "mob.irongolem.hit";
//		}
//		protected String getDeathSound()
//		{
//			return "mob.irongolem.death";
//		}
//		// sound : walk
//		protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
//		{
//			this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
//		}
//		protected void entityInit()
//		{
//			super.entityInit();
//			// this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
//		}

//		// note 不用管
//		protected void collideWithEntity(Entity entity)
//		{
//			if (entity instanceof IMob && this.getRNG().nextInt(20) == 0)
//			{
//				this.setAttackTarget((EntityLivingBase)entity);
//			}
//
//			super.collideWithEntity(entity);
//		}

//		// note 不用管
//		public boolean canAttackClass(Class entityClass)
//		{
//			return this.isPlayerCreated()
//					&& EntityPlayer.class.isAssignableFrom(entityClass) ?
//					false : super.canAttackClass(entityClass);
//		}
//		// note 不用管
//		public int getHoldRoseTick()
//		{
//			return this.holdRoseTick;
//		}
//		public boolean isPlayerCreated()
//		{
//			return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
//		}
//		public void setPlayerCreated(boolean flag)
//		{
//			byte b0 = this.dataWatcher.getWatchableObjectByte(16);
//
//			if (flag)
//			{
//				this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
//			}
//			else
//			{
//				this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
//			}
//		}
	}
	public static class SummonedIronGolem extends SummonedGolem
	{
		public SummonedIronGolem(World world)
		{
			super(world);
		}
		public SummonedIronGolem(World world,double x,double y,double z)
		{
			super(world,x,y,z);
		}
		@Override
		protected void applyEntityAttributes()
		{
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.25);
		}
	}
	public static class SummonedThaumiumGolem extends SummonedGolem
	{
		public SummonedThaumiumGolem(World world)
		{
			super(world);
		}
		public SummonedThaumiumGolem(World world,double x,double y,double z)
		{
			super(world,x,y,z);
		}
		@Override
		protected void applyEntityAttributes()
		{
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(150);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.3f);
		}
		@Override
		public boolean attackEntityAsMob(Entity entity)
		{
			this.attackTimer = 10;
			this.worldObj.setEntityState(this, (byte)4);
			boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 20);

			if (flag)
			{
				entity.motionY += 0.4000000059604645D;
			}

			this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
			return flag;
		}
	}
	public static class SummonedVoidGolem extends SummonedGolem
	{
		public SummonedVoidGolem(World world)
		{
			super(world);
		}
		public SummonedVoidGolem(World world,double x,double y,double z)
		{
			super(world,x,y,z);
		}
		@Override
		protected void applyEntityAttributes()
		{
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.35f);
		}
		@Override
		public void onLivingUpdate()
		{
			super.onLivingUpdate();
			if(this.ticksExisted%20==0)
				this.heal(2);
		}
		@Override
		public boolean attackEntityAsMob(Entity entity)
		{
			this.attackTimer = 10;
			this.worldObj.setEntityState(this, (byte)4);
			boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 25);

			if (flag)
			{
				entity.motionY += 0.4000000059604645D;
			}

			this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
			return flag;
		}

	}
	public static class SummonedSolitaGolem extends SummonedGolem
	{
		public SummonedSolitaGolem(World world)
		{
			super(world);
		}
		public SummonedSolitaGolem(World world,double x,double y,double z)
		{
			super(world,x,y,z);
		}
		@Override
		protected void applyEntityAttributes()
		{
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300);
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.3f);
		}
		@Override
		public boolean attackEntityAsMob(Entity entity)
		{
			this.attackTimer = 10;
			this.worldObj.setEntityState(this, (byte)4);
			boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 30);

			if (flag)
			{
				entity.motionY += 0.4000000059604645D;
			}

			this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
			return flag;
		}
	}

}
