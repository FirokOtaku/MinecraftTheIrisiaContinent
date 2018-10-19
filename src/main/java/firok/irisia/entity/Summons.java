package firok.irisia.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Summons
{
	public static class SummonedIronGolem extends EntityIronGolem
	{
		private long tick;
		public SummonedIronGolem(World world)
		{
			this(world,0,0,0);
		}
		public SummonedIronGolem(World world, double x,double y,double z)
		{
			this(world,x,y,z,200);
		}
		public SummonedIronGolem(World world,double x,double y,double z,long ticks)
		{
			super(world);
			this.setPosition(x,y,z);
			tick=ticks;
		}

		@Override
		public void onLivingUpdate()
		{
			super.onLivingUpdate();
			if(tick>0)
			{
				tick--;
			}
			else
			{
				this.setDead();
			}
		}

		@Override
		public void writeEntityToNBT(NBTTagCompound nbt)
		{
			super.writeEntityToNBT(nbt);
			nbt.setLong("liveTick",tick);
		}

		@Override
		public void readEntityFromNBT(NBTTagCompound nbt)
		{
			super.readEntityFromNBT(nbt);
			tick=nbt.hasKey("liveTick")?nbt.getLong("liveTick"):200;
		}
	}
}
