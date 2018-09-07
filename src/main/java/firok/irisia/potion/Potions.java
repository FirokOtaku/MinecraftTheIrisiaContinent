package firok.irisia.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.entities.ITaintedMob;

import java.awt.*;
import java.util.List;

import static firok.irisia.DamageSources.ElectrostaticDamage;

public class Potions
{
	// common potion
	public static Potion Love;
	public static Potion Electrostatic;
	public static Potion Cold;
	public static Potion SpaceUnstable;
	// event potion
	public static Potion Wise;
	public static Potion Folly;
	public static Potion MagicAmplificative;
	public static Potion MagicResistance;
	public static Potion Thresholded;
	public static Potion Ethereal;
	public static Potion Ninjia;
	public static Potion WindRanger;
	// unimplemented potion

	public static Potion Indomitable;
	public static Potion Avarice;

	public static Potion Militaristic;
	public static Potion Painbound;
	public static Potion Lifecursed;
	public static Potion Lifeblessed;
	public static Potion Teared;
	public static Potion Happy;

	public static class PotionLove extends Potion
	{
		public PotionLove(int id)
		{
			super(id,false,Color.red.getRGB());
			this.setPotionName("irisia.potion.love");
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
	public static class PotionElectrostatic extends Potion
	{
		public PotionElectrostatic(int id)
		{
			super(id,false,Color.blue.getRGB());
			this.setPotionName("irisia.potion.electrostatic");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			World world=target.worldObj;
			if(world.isRemote)
				return;

			double x=target.posX,y=target.posY,z=target.posZ;
			int radius=level*2;
			List entities=world.getEntitiesWithinAABBExcludingEntity(target,
					AxisAlignedBB.getBoundingBox
					(x-3+radius,y-3+radius,z-3+radius,
					x+3+radius,y+3+radius,z+3+radius));

			for(Object obj:entities)
			{
				if(obj instanceof EntityLivingBase && world.rand.nextFloat()>level*0.1)
				{
					EntityLivingBase enlb=(EntityLivingBase)obj;
					enlb.attackEntityFrom(ElectrostaticDamage,level);
				}
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%40==0;
		}
	}
	public static class PotionCold extends Potion
	{
		public PotionCold(int id)
		{
			super(id,false,Color.blue.getRGB());
			this.setPotionName("irisia.potion.cold");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			World world=target.worldObj;
			if(world.isRemote)
				return;

			double x=target.posX,y=target.posY,z=target.posZ;
			int radius=level*2;
			List entities=world.getEntitiesWithinAABBExcludingEntity(target,
					AxisAlignedBB.getBoundingBox
							(x-3+radius,y-3+radius,z-3+radius,
									x+3+radius,y+3+radius,z+3+radius));

			for(Object obj:entities)
			{
				if(obj instanceof EntityLivingBase && world.rand.nextFloat()>0.2+level*0.2)
				{
					EntityLivingBase enlb=(EntityLivingBase)obj;
					enlb.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,40,level));
				}
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%20==0;
		}
	}
	public static class PotionSpaceUnstable extends Potion
	{
		public PotionSpaceUnstable(int id)
		{
			super(id,false,Color.blue.getRGB());
			this.setPotionName("irisia.potion.space_unstable");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			World world=target.worldObj;
			if(world.isRemote)
				return;

			firok.irisia.ability.CauseTeleportation.teleportEntityRandomly(target,level*5);
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%40==0;
		}
	}



	public static class EventPotion extends Potion // 这种状态效果是靠event驱动的
	{
		public EventPotion(int id,boolean isBad,int rgb,String name)
		{
			super(id,isBad,rgb);
			this.setPotionName("irisia.potion."+name);
		}
		@Override
		public void performEffect(EntityLivingBase target, int par2) {
			;
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return false;
		}
	}
	public abstract static class CustomPotion extends Potion
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
