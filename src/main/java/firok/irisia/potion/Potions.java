package firok.irisia.potion;

import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.ability.CauseDamage;
import firok.irisia.common.EntitySelectors;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;

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
	public static Potion VisLeaking;
	public static Potion Corroded;
	public static Potion Cursed;
	public static Potion Militaristic;
	public static Potion Plaguing;
	// event potion
	public static Potion Wise;
	public static Potion Folly;
	public static Potion MagicAmplificative;
	public static Potion MagicResistance;
	public static Potion Thresholded;
	public static Potion Ethereal;
	public static Potion Ninjia;
	public static Potion WindRanger;
	public static Potion Leaderly;
	public static Potion Midas;
	// unimplemented potion

	public static Potion Indomitable;
	public static Potion Avarice;

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
	public static class PotionVisLeaking extends Potion // fixme 还没做完
	{
		public PotionVisLeaking(int id)
		{
			super(id,false,Color.blue.getRGB());
			this.setPotionName("irisia.potion.vis_leaking");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			ItemStack stackHeld=target.getHeldItem();
			if(stackHeld!=null && stackHeld.getItem() instanceof ItemWandCasting)
			{
				// ((ItemWandCasting)stackHeld.getItem()).
				for(Aspect as:Irisia.arrayPrimalAspect)
				{// fixme 这里以后改掉
					((ItemWandCasting)stackHeld.getItem()).addRealVis(stackHeld,as,level*50+50,true);
				}//thaumcraft.common.items.wands.foci.ItemFocusFire
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%20==0;
		}
	}
	public static class PotionCorroded extends Potion
	{
		public PotionCorroded(int id)
		{
			super(id,false,Color.gray.getRGB());
			this.setPotionName("irisia.potion.corroded");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			if(!target.worldObj.isRemote &&target instanceof EntityPlayer)
			{
				InventoryPlayer inv=((EntityPlayer) target).inventory;
				for(byte i=0;i<inv.armorInventory.length;i++)
				{
					ItemStack stackInInv=inv.armorInventory[i];
					if(stackInInv==null) continue;

					int nowDamage=stackInInv.getItemDamage();
					int maxDamage=stackInInv.getMaxDamage();
					int toDamage=level*2+2;
					if(nowDamage+toDamage>=maxDamage)
					{
						stackInInv.damageItem(toDamage,target);
						inv.armorInventory[i]=null;
					}
					else
					{
						stackInInv.damageItem(toDamage,target);
					}
					inv.markDirty();
				}
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%10==0;
		}
	}
	public static class PotionCursed extends Potion
	{
		public PotionCursed(int id)
		{
			super(id,false,Color.black.getRGB());
			this.setPotionName("irisia.potion.cursed");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			if(!target.worldObj.isRemote)
			{
				target.worldObj.playSoundAtEntity(target,Keys.SoundCreepy,1,1);
				CauseDamage.toLiving(target,DamageSources.CursedDamage,6+level*6,true);
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick<=1;
		}
	}
	public static class PotionMilitaristic extends Potion
	{
		public PotionMilitaristic(int id)
		{
			super(id,false,Color.red.getRGB());
			this.setPotionName("irisia.potion.militaristic");
		}
		@Override
		public void performEffect(EntityLivingBase target, int level) {
			if(!target.worldObj.isRemote)
			{
				target.worldObj.playSoundAtEntity(target,Keys.SoundCreepy,1,1);
				CauseDamage.toLiving(target,DamageSources.MilitaristicDamage,4+level*4,true);
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick<=1;
		}
	}
	public static class PotionPlaguing extends Potion
	{
		public PotionPlaguing(int id)
		{
			super(id,false,Color.green.getRGB());
			this.setPotionName("irisia.potion.plaguing ");
		}
		@Override
		public void performEffect(EntityLivingBase entity, int level) {
			if(!entity.worldObj.isRemote)
			{
				List entities=entity.worldObj.getEntitiesWithinAABBExcludingEntity(entity,
						AxisAlignedBB.getBoundingBox(-7.5,-7.5,-7.5,7.5,7.5,7.5),
						EntitySelectors.SelectEntityLivingBaseAlive);
				int level2add=level-1;
				for(Object obj:entities)
				{
					if(entity.worldObj.rand.nextFloat()<0.1+0.08*level)
					{
						EntityLivingBase enlb=(EntityLivingBase)obj;
						if(enlb.getActivePotionEffect(Potions.Plaguing)==null)
							enlb.addPotionEffect(new PotionEffect(Potions.Plaguing.id,1200,level2add));
					}
				}

				if(level<4)
					entity.attackEntityFrom(DamageSources.PlagueDamage,entity.worldObj.rand.nextInt(6));
			}
		}
		@Override
		public boolean isReady(int tick, int par2)
		{
			return tick%200==0;
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
