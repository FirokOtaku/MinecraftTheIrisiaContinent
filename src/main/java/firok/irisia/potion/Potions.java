package firok.irisia.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.entities.ITaintedMob;

import java.awt.*;

public class Potions
{
	public static Potion love;
	public static class Love extends Potion
	{
		public Love(int id)
		{
			super(id,false,Color.red.getRGB());

		}
		@Override
		public void performEffect(EntityLivingBase target, int par2) {
			World world=target.worldObj;
			if(world.isRemote)
				return;

			world.spawnParticle("heart",
					target.posX, target.posY, target.posZ,
					0, 1, 0);
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%20==0;
		}
	}
	public static class CustomPotion extends Potion
	{
		public CustomPotion(int id,boolean isBad,int color)
		{
			super(id,isBad,color);
		}
		@Override
		public void performEffect(EntityLivingBase target, int par2) {
			;
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%20==0;
		}
	}

}
