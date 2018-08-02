package firok.irisia.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockMushroom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class HerbsAndMushroom
{
	public final static Herb DeathGrass;
	public final static Herb MoonGrass;
	public final static Herb SpicyRoot;

	public final static Mushroom ShadowMushroom;

	static
	{
		DeathGrass=new Herb();
		MoonGrass=new Herb();
		SpicyRoot=new Herb();

		ShadowMushroom =(Mushroom) (new Mushroom(){
			@Override
			public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity)
			{
				if(entity instanceof EntityLivingBase)
				{
					EntityLivingBase enlb=(EntityLivingBase)entity;
					// 5秒失明Ⅰ
					enlb.addPotionEffect(new PotionEffect(Potion.blindness.getId(),100,0,false));
				}
			}
		})
				.setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass)
				.setLightLevel(0.125F);
	}

	public static class Herb extends BlockBush
	{
		public Herb()
		{
			super();
		}
		@Override
		public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
		{
			if(this==DeathGrass) return EnumPlantType.Plains;
			else if(this==MoonGrass) return EnumPlantType.Plains;
			else if(this==SpicyRoot) return EnumPlantType.Desert;
			else return EnumPlantType.Plains;
		}
	}
	public static class Mushroom extends BlockMushroom
	{
		public Mushroom()
		{
			super();
		}
	}

}
