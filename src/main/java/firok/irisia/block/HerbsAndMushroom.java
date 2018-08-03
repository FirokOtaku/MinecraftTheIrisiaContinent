package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;
import java.util.Random;

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

	public static class Herb extends BlockCrops
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

		@SideOnly(Side.CLIENT)
		private IIcon[] field_149867_a;

		/**
		 * is the block grass, dirt or farmland
		 */
		protected boolean canPlaceBlockOn(Block block)
		{
			return block == Blocks.farmland;
		}

		/**
		 * Ticks the block if it's been scheduled
		 */
		public void updateTick(World world, int x, int y, int z, Random rand)
		{
			super.updateTick(world, x, y, z, rand);
			if (world.getBlockLightValue(x, y + 1, z) >= 9) // 检查亮度
			{
				int l = world.getBlockMetadata(x, y, z);

				if (l < 7)
				{
					float f = this.func_149864_n(world, x, y, z);

					if (rand.nextInt((int)(25.0F / f) + 1) == 0)
					{
						++l;
						world.setBlockMetadataWithNotify(x, y, z, l, 2);
					}
				}
			}
		}

		public void func_149863_m(World world, int x, int y, int z)
		{
			int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

			if (l > 7)
			{
				l = 7;
			}

			world.setBlockMetadataWithNotify(x, y, z, l, 2);
		}

		// 看起来是获得生长速度
		private float func_149864_n(World world, int x, int y, int z)
		{
			float f = 1.0F;
			Block block = world.getBlock(x, y, z - 1);
			Block block1 = world.getBlock(x, y, z + 1);
			Block block2 = world.getBlock(x - 1, y, z);
			Block block3 = world.getBlock(x + 1, y, z);
			Block block4 = world.getBlock(x - 1, y, z - 1);
			Block block5 = world.getBlock(x + 1, y, z - 1);
			Block block6 = world.getBlock(x + 1, y, z + 1);
			Block block7 = world.getBlock(x - 1, y, z + 1);
			boolean flag = block2 == this || block3 == this;
			boolean flag1 = block == this || block1 == this;
			boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

			for (int l = x - 1; l <= x + 1; ++l)
			{
				for (int i1 = z - 1; i1 <= z + 1; ++i1)
				{
					float f1 = 0.0F;

					if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this))
					{
						f1 = 1.0F;

						if (world.getBlock(l, y - 1, i1).isFertile(world, l, y - 1, i1))
						{
							f1 = 3.0F;
						}
					}

					if (l != x || i1 != z)
					{
						f1 /= 4.0F;
					}

					f += f1;
				}
			}

			if (flag2 || flag && flag1)
			{
				f /= 2.0F;
			}

			return f;
		}

		/**
		 * Gets the block's texture. Args: side, meta
		 */
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int p_149691_1_, int p_149691_2_)
		{
			if (p_149691_2_ < 0 || p_149691_2_ > 7)
			{
				p_149691_2_ = 7;
			}

			return this.field_149867_a[p_149691_2_];
		}

		@Override
		public int getRenderType()
		{
			return 1;
		}

		// 看起来是获得种子
		protected Item func_149866_i()
		{
			return Items.wheat_seeds;
		}

		// 看起来是获得产物
		protected Item func_149865_P()
		{
			return Items.wheat;
		}

		/**
		 * Drops the block items with a specified chance of dropping the specified items
		 */
		@Override
		public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int fortune)
		{
			if (!world.isRemote && !world.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
			{
				ArrayList<ItemStack> items = getDrops(world, x, y, z, meta, fortune);
				chance = ForgeEventFactory.fireBlockHarvesting
						(items, world, this, x, y, z, meta, fortune, chance, false, harvesters.get());

				for (ItemStack item : items)
				{
					if (world.rand.nextFloat() <= chance)
					{
						this.dropBlockAsItem(world, x, y, z, item);
					}
				}
			}
		}

		public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
		{
			return p_149650_1_ == 7 ? this.func_149865_P() : this.func_149866_i();
		}

		/**
		 * Returns the quantity of items to drop on block destruction.
		 */
		public int quantityDropped(Random p_149745_1_)
		{
			return 1;
		}

		public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
		{
			return p_149851_1_.getBlockMetadata(p_149851_2_, p_149851_3_, p_149851_4_) != 7;
		}

		public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
		{
			return true;
		}

		/**
		 * Gets an item for the block being called on. Args: world, x, y, z
		 */
		@SideOnly(Side.CLIENT)
		public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
		{
			return this.func_149866_i();
		}

		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister p_149651_1_)
		{
			this.field_149867_a = new IIcon[8];

			for (int i = 0; i < this.field_149867_a.length; ++i)
			{
				this.field_149867_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
			}
		}

		public void func_149853_b(World world, Random rand, int x, int y, int z)
		{
			this.func_149863_m(world, x, y, z);
		}

//
//
//		// 感觉是用来检查目标有没有成熟
//		public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_)
//		{
//			return world.getBlockMetadata(x, y, z) != 7;
//		}
//
//		// 不知道干啥的方法
//		@Override
//		public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
//		{
//			return true;
//		}
//
//		@Override
//		public void func_149853_b(World world, Random rand, int x, int y, int z)
//		{
//			this.func_149874_m(world, x, y, z);
//		}
//
//		public void func_149874_m(World world, int x, int y, int z)
//		{
//			int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
//
//			if (l > 7)
//			{
//				l = 7;
//			}
//
//			world.setBlockMetadataWithNotify(x, y, z, l, 2);
//		}
	}
	public static class Mushroom extends BlockMushroom
	{
		public Mushroom()
		{
			super();
		}
	}

}
