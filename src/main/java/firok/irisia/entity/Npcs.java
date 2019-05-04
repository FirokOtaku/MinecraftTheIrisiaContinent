package firok.irisia.entity;

import firok.irisia.Irisia;
import firok.irisia.common.EventLoader;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import thaumcraft.common.blocks.BlockAiry;

public class Npcs
{
	public static class TestTarget extends EntityVillager
	{
		public TestTarget(World world)
		{
			this(world,0,0,0);
		}
		public TestTarget(World world,double x,double y,double z)
		{
			super(world);
			this.setPosition(x,y,z);

		}
		@Override
		public void onUpdate()
		{
			super.onUpdate();
		}

		@Override
		protected void damageEntity(DamageSource source, float amount)
		{
			if (!this.isEntityInvulnerable())
			{
				amount = ForgeHooks.onLivingHurt(this, source, amount);
				if (amount <= 0) return;

				EntityPlayer player=worldObj.getClosestPlayerToEntity(this,128);
				if(player!=null)
				{
					Irisia.log(EventLoader.toString(source),player);
					Irisia.log(amount,player);
				}
			}
		}

		@Override
		public boolean interact(EntityPlayer player)
		{
			if(!this.worldObj.isRemote)
				setDead();
			return true;
		}
	}
}
