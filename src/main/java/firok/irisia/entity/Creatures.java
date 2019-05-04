package firok.irisia.entity;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import thaumcraft.common.entities.monster.EntityThaumicSlime;

public class Creatures
{
	public static class HarmlessSlime extends EntityLiving implements IAnimals
	{
		public float squishAmount;
		public float squishFactor;
		public float prevSquishFactor;
		/** ticks until this slime jumps again */
		private int slimeJumpDelay;

		public HarmlessSlime(World world)
		{
			super(world);
			int size2set = 1 << this.rand.nextInt(3);
			this.yOffset = 0.0F;
			this.slimeJumpDelay = this.rand.nextInt(20) + 10;
			this.setSlimeSize(size2set);

		}
		protected void entityInit()
		{
			super.entityInit();
			this.dataWatcher.addObject(16, new Byte((byte)1));
		}
		protected void setSlimeSize(int size)
		{
			this.dataWatcher.updateObject(16, new Byte((byte)size));
			this.setSize(0.6F * (float)size, 0.6F * (float)size);
			this.setPosition(this.posX, this.posY, this.posZ);
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(size * size));
			this.setHealth(this.getMaxHealth());
			this.experienceValue = size;
		}
		public int getSlimeSize()
		{
			return this.dataWatcher.getWatchableObjectByte(16);
		}
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setInteger("Size", this.getSlimeSize() - 1);
		}
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			int sizeRead = nbt.getInteger("Size");
			if (sizeRead < 0)
			{
				sizeRead = 0;
			}
			this.setSlimeSize(sizeRead + 1);
		}
		protected String getSlimeParticle()
		{
			return "slime";
		}
		protected String getJumpSound()
		{
			return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
		}

		public void onUpdate()
		{
			this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
			this.prevSquishFactor = this.squishFactor;
			boolean flag = this.onGround;
			super.onUpdate();
			int i;

			if (this.onGround && !flag)
			{
				i = this.getSlimeSize();

				for (int j = 0; j < i * 8; ++j)
				{
					float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
					float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
					float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
					float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
					this.worldObj.spawnParticle(this.getSlimeParticle(), this.posX + (double)f2, this.boundingBox.minY, this.posZ + (double)f3, 0.0D, 0.0D, 0.0D);
				}

				if (this.makesSoundOnLand())
				{
					this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
				}

				this.squishAmount = -0.5F;
			}
			else if (!this.onGround && flag)
			{
				this.squishAmount = 1.0F;
			}

			this.alterSquishAmount();

			if (this.worldObj.isRemote)
			{
				i = this.getSlimeSize();
				this.setSize(0.6F * (float)i, 0.6F * (float)i);
			}
		}

		protected void updateEntityActionState()
		{
			this.despawnEntity();
			EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

			if (entityplayer != null)
			{
				this.faceEntity(entityplayer, 10.0F, 20.0F);
			}

			if (this.onGround && this.slimeJumpDelay-- <= 0)
			{
				this.slimeJumpDelay = this.getJumpDelay();

				if (entityplayer != null)
				{
					this.slimeJumpDelay /= 3;
				}

				this.isJumping = true;

				if (this.makesSoundOnJump())
				{
					this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
				}

				this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
				this.moveForward = (float)(1 * this.getSlimeSize());
			}
			else
			{
				this.isJumping = false;

				if (this.onGround)
				{
					this.moveStrafing = this.moveForward = 0.0F;
				}
			}
		}

		protected void alterSquishAmount()
		{
			this.squishAmount *= 0.6F;
		}
		protected boolean canDespawn()
		{
			return false;
		}

		protected int getJumpDelay()
		{
			return this.rand.nextInt(20) + 10;
		}
		protected HarmlessSlime createInstance()
		{
			return new HarmlessSlime(this.worldObj);
		}
		
		public void setDead()
		{
			int i = this.getSlimeSize();

			if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
			{
				int j = 2 + this.rand.nextInt(3);

				for (int k = 0; k < j; ++k)
				{
					float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
					float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
					HarmlessSlime entityslime = this.createInstance();
					entityslime.setSlimeSize(i / 2);
					entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
					this.worldObj.spawnEntityInWorld(entityslime);
				}
			}
			super.setDead();
		}
		protected String getHurtSound()
		{
			return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
		}
		protected String getDeathSound()
		{
			return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
		}
		protected Item getDropItem()
		{
			return this.getSlimeSize() == 1 ? Items.slime_ball : Item.getItemById(0);
		}
		protected float getSoundVolume()
		{
			return 0.4F * (float)this.getSlimeSize();
		}
		public int getVerticalFaceSpeed()
		{
			return 0;
		}
		protected boolean makesSoundOnJump()
		{
			return this.getSlimeSize() > 0;
		}
		protected boolean makesSoundOnLand()
		{
			return this.getSlimeSize() > 2;
		}
	}
}
