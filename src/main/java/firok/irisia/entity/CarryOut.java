package firok.irisia.entity;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CarryOut
{
	public static class Carrier extends Entity
	{
		public double startX;
		public double startY;
		public double startZ;
		public Carrier(World world)
		{
			this(world,0,0,0);
		}
		public Carrier(World world,double x,double y,double z)
		{
			super(world);
			this.motionX=this.motionY=this.motionZ=0;
			this.setPosition(x,y,z);
			startX=posX;
			startY=posY;
			startZ=posZ;
		}

		@Override
		public void onEntityUpdate()
		{
			super.onEntityUpdate();
			this.posY-=0.05;
			if(!worldObj.isRemote && startY-this.posY>=5)
			{
				this.setDead();
				entityDropItem(new ItemStack(Items.apple),0);
			}
		}

		@Override
		protected void entityInit()
		{
			;
		}

		@Override
		protected void readEntityFromNBT(NBTTagCompound nbt)
		{
			this.posX=nbt.getDouble("posX");
			this.posY=nbt.getDouble("posY");
			this.posZ=nbt.getDouble("posZ");
			this.startX=nbt.getDouble("startX");
			this.startY=nbt.getDouble("startY");
			this.startZ=nbt.getDouble("startZ");
		}

		@Override
		protected void writeEntityToNBT(NBTTagCompound nbt)
		{
			nbt.setDouble("posX",posX);
			nbt.setDouble("posY",posY);
			nbt.setDouble("posZ",posZ);
			nbt.setDouble("startX",startX);
			nbt.setDouble("startY",startY);
			nbt.setDouble("startZ",startZ);
		}

	}
}
