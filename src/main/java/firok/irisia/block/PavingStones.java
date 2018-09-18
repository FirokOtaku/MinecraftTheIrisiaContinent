package firok.irisia.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockFluidPure;

public class PavingStones
{
	public static Block Flame;
	public static Block Shake;

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

				if(world.isRemote)
					return;

				e.fallDistance=0;
				e.motionY+=5;
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
}
