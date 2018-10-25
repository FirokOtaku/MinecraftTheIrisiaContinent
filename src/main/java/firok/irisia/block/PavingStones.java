package firok.irisia.block;

import firok.irisia.Irisia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class PavingStones
{
	public final static PavingStone Flame;
	public final static PavingStone Shake;
	static
	{
		Flame=new PavingStone(){
			@Override
			public void onEntityWalking(World world, int x, int y, int z, Entity e) {
				// super.onEntityWalking(world,x,y,z,e);

				if(world.isRemote)
					return;

				if(e instanceof EntityLivingBase)
				{
					EntityLivingBase enlb=(EntityLivingBase)e;
					enlb.setFire(1);
				}
			}
		};
		Shake=new PavingStone(){
			@Override
			public void onEntityWalking(World world, int x, int y, int z, Entity e) {
				// super.onEntityWalking(world,x,y,z,e);

				e.fallDistance=0;
				e.motionY+=2;
			}
		};
	}
	public final static BluringBlock BluringTraveller;
	public final static BluringBlock BluringShake;
	public final static BluringBlock BluringFlame;
	static
	{
		BluringTraveller =new BluringBlock(ConfigBlocks.blockCosmeticSolid,2)
		{
			@Override
			public void onInteractWith(World world, int x, int y, int z, Entity e)
			{
				if(world.isRemote) return;

				if(e instanceof EntityLivingBase)
				{
					((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 1));
					((EntityLivingBase)e).addPotionEffect(new PotionEffect(Potion.jump.id, 40, 0));
				}
			}
		};
		BluringShake=new BluringBlock(Shake,-1)
		{
			@Override
			public void onInteractWith(World world, int x, int y, int z, Entity e)
			{
				// super.onEntityWalking(world,x,y,z,e);

				e.fallDistance=0;
				e.motionY+=2;
			}
		};
		BluringFlame=new BluringBlock(Flame,-1)
		{
			@Override
			public void onInteractWith(World world, int x, int y, int z, Entity e)
			{
				if(world.isRemote)
					return;

				if(e instanceof EntityLivingBase)
				{
					EntityLivingBase enlb=(EntityLivingBase)e;
					enlb.setFire(1);
				}
			}
		};
	}

	public static class PavingStone extends Block
	{
		public PavingStone()
		{
			super(Material.rock);
			this.setResistance(10.0F);
			this.setHardness(2.0F);
			this.setStepSound(soundTypeStone);
		}
	}
	public static abstract class BluringBlock extends Block
	{
		public BluringBlock()
		{
			this(null);
		}
		public BluringBlock(Block source)
		{
			this(source,0);
		}
		public BluringBlock(Block source,int meta)
		{
			super(Material.rock);
			sourceBlock=source;
			sourceMeta=meta;
			this.setResistance(10.0F);
			this.setHardness(2.0F);
			this.setStepSound(soundTypeStone);
		}

		public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity en)
		{
			onInteractWith(world,x,y,z,en);
		}
		public void onEntityWalking(World world, int x, int y, int z, Entity e)
		{
			onInteractWith(world,x,y,z,e);
		}
		public abstract void onInteractWith(World world,int x,int y,int z,Entity en);

		protected Block sourceBlock;
		protected int sourceMeta;
		@Nullable
		public Block getSourceBlock(){return sourceBlock;}
		public int getSourceMeta(){return sourceMeta;}

		public void onDebluring(World world,int x,int y,int z)
		{
			if(sourceBlock==null) return;

			Irisia.log("block "+this.getLocalizedName()+" onDebluring!");

			if(sourceMeta>=0)
				world.setBlock(x,y,z,sourceBlock,sourceMeta,2);
			else
				world.setBlock(x,y,z,sourceBlock);
		}

		// 碰撞箱
		@Override
		public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity)
		{
			; // 没有碰撞箱
		}
		public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		public void setBlockBoundsForItemRender() {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
			return false;
		}
		public int getLightValue(IBlockAccess world, int x, int y, int z)
		{
			return getLightValue();
		}
		public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
		{
			return 2.0f;
		}
		public int quantityDropped(Random par1Random)
		{
			return 1;
		}
		public int damageDropped(int par1)
		{
			return par1;
		}
		public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
		{
			Thaumcraft.proxy.burst(world, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 1.0F);
			world.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "thaumcraft:craftfail", 1.0F, 1.0F, false);
			return true;
		}
		public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
		{
			return false;
		}
	}
}
