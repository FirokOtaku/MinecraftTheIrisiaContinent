package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DecorationBlocks
{
	public final static Block CorrosedBlock; // 被腐蚀的方块
//	public final static Block Loess; // 黄土

	public final static Block RichMagicGrass; // 富魔力草
	public final static Block RichMagicStone; // 富魔力石

	public final static Block LuxGrass; // 荧光草


	static
	{
		CorrosedBlock=new CustomSand();
		RichMagicGrass=new CustomDirt();
		RichMagicStone=new CustomStone();
		LuxGrass=new CustomDirt()
				.setLightLevel(8);
	}
	public static class CustomSand extends Block
	{
		public CustomSand()
		{
			super(Material.sand);
			this.setHardness(0.5F);
			this.setStepSound(soundTypeSand);
		}
	}
	public static class CustomLog extends Block
	{
		public CustomLog()
		{
			super(Material.wood);
			this.setHardness(2.0F);
			this.setStepSound(soundTypeWood);
		}
	}
	public static class CustomLeave extends Block implements IShearable
	{
		int[] adjacentTreeBlocks;

		public CustomLeave() {
			super(Material.leaves);
			this.setTickRandomly(true);
			this.setHardness(0.2F);
			this.setLightOpacity(1);
			this.setStepSound(soundTypeGrass);
		}

//		@SideOnly(Side.CLIENT)
//		public void registerBlockIcons(IIconRegister ir) {
//		}
//
//		public IIcon getIcon(int par1, int par2) {
//			int idx = !Blocks.leaves.isOpaqueCube() ? 0 : 1;
//			return (par2 & 1) == 1 ? this.icon[idx + 2] : this.icon[idx];
//		}


		@SideOnly(Side.CLIENT)
		public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
			Block block = world.getBlock(x, y, z);
			return Blocks.leaves.isOpaqueCube() && block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
		}

		@SideOnly(Side.CLIENT)
		public int getBlockColor() {
			return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
		}

		@SideOnly(Side.CLIENT)
		public int getRenderColor(int par1) {
			return (par1 & 1) == 0 ? ColorizerFoliage.getFoliageColorBasic() : 8952234;
		}

		@SideOnly(Side.CLIENT)
		public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
			int meta = world.getBlockMetadata(x, y, z);
			if ((meta & 1) == 1) {
				return 8952234;
			} else {
				int var6 = 0;
				int var7 = 0;
				int var8 = 0;

				for(int var9 = -1; var9 <= 1; ++var9) {
					for(int var10 = -1; var10 <= 1; ++var10) {
						int biomeGen = world.getBiomeGenForCoords(x + var10, z + var9).getBiomeFoliageColor(x, y, z);
						var6 += (biomeGen & 16711680) >> 16;
						var7 += (biomeGen & '\uff00') >> 8;
						var8 += biomeGen & 255;
					}
				}

				return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
			}
		}

		@Override
		public int getLightValue(IBlockAccess world, int x, int y, int z)
		{
			return lightValue;
		}

		public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
			byte offsetXs = 1;
			int offsetXs2 = 2;
			if (world.checkChunksExist(x - offsetXs2, y - offsetXs2, z - offsetXs2, x + offsetXs2, y + offsetXs2, z + offsetXs2)) {
				for(int offsetX = -offsetXs; offsetX <= offsetXs; ++offsetX) {
					for(int offsetY = -offsetXs; offsetY <= offsetXs; ++offsetY) {
						for(int offsetZ = -offsetXs; offsetZ <= offsetXs; ++offsetZ) {
							Block blockNearby = world.getBlock(x + offsetX, y + offsetY, z + offsetZ);
							if (blockNearby != Blocks.air) {
								blockNearby.beginLeavesDecay(world, x + offsetX, y + offsetY, z + offsetZ);
							}
						}
					}
				}
			}

		}

		public void updateTick(World world, int x, int y, int z, Random rand) {
			if (!world.isRemote) {
				int meta = world.getBlockMetadata(x, y, z);
				byte var7 = 4;
				int var8 = 5;
				byte var9 = 32;
				int var10 = var9 * var9;
				int var11 = var9 / 2;
				if (this.adjacentTreeBlocks == null) {
					this.adjacentTreeBlocks = new int[var9 * var9 * var9];
				}

				int var12;
				if (world.checkChunksExist(x - var8, y - var8, z - var8, x + var8, y + var8, z + var8)) {
					var12 = -var7;

					label117:
					while(true) {
						int var13;
						int var14;
						if (var12 > var7) {
							var12 = 1;

							while(true) {
								if (var12 > 4) {
									break label117;
								}

								for(var13 = -var7; var13 <= var7; ++var13) {
									for(var14 = -var7; var14 <= var7; ++var14) {
										for(int var15 = -var7; var15 <= var7; ++var15) {
											if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1) {
												if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
													this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
												}

												if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
													this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
												}

												if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2) {
													this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
												}

												if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2) {
													this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
												}

												if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2) {
													this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
												}

												if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2) {
													this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
												}
											}
										}
									}
								}

								++var12;
							}
						}

						for(var13 = -var7; var13 <= var7; ++var13) {
							for(var14 = -var7; var14 <= var7; ++var14) {
								Block block = world.getBlock(x + var12, y + var13, z + var14);
								if (block != null && block.canSustainLeaves(world, x + var12, y + var13, z + var14)) {
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
								} else if (block != null && block.isLeaves(world, x + var12, y + var13, z + var14)) {
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
								} else {
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
								}
							}
						}

						++var12;
					}
				}

				var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];
				if (var12 >= 0) {
					world.setBlock(x, y, z, this, meta & -9, 3);
				} else {
					this.removeLeaves(world, x, y, z);
				}
			}

		}

		@SideOnly(Side.CLIENT)
		public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
			if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
				double var6 = (double)((float)par2 + par5Random.nextFloat());
				double var8 = (double)par3 - 0.05D;
				double var10 = (double)((float)par4 + par5Random.nextFloat());
				par1World.spawnParticle("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
			}

		}

		private void removeLeaves(World par1World, int par2, int par3, int par4) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
		private int dropRate=100;

		public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float flag, int par7) {
			if (!world.isRemote && world.rand.nextInt(dropRate) == 0) {
				this.dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, 0));
			}

		}

		public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
			super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
		}

		public int damageDropped(int par1) {
			return 0;
		}

		public int quantityDropped(Random par1Random) {
			return 0;
		}

		public Item getItemDropped(int par1, Random par2Random, int par3) {
			return Item.getItemById(0);
		}

		public boolean isOpaqueCube() {
			return Blocks.leaves.isOpaqueCube();
		}

		public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
			return true;
		}

		public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
			ArrayList<ItemStack> ret = new ArrayList();
			ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
			return ret;
		}

		public void beginLeavesDecay(World world, int x, int y, int z) {
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
		}

		public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
			return true;
		}

		@SuppressWarnings("deprecation")
		public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
			int md = world.getBlockMetadata(x, y, z);
			return new ItemStack(this, 1, md & 1);
		}

		public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
			return 60;
		}

		public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
			return 30;
		}
	}
	public static class CustomDirt extends Block
	{
		public CustomDirt()
		{
			super(Material.ground);
			this.setHardness(0.5F);
			this.setStepSound(soundTypeGravel);
		}
	}
	public static class CustomStone extends Block
	{
		public CustomStone()
		{
			super(Material.rock);
		}
	}




	public final static Block DeathDirt; // 亡者之壤
	static
	{
		DeathDirt=new Block(Material.sand){
			public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
			{
				if(world.isRemote)
					return false;

				if(Irisia.IN_DEV && player.capabilities.isCreativeMode) // todo 以后去掉
				{
					int meta=world.getBlockMetadata(x,y,z);
					if(meta<10)
					{
						world.setBlockMetadataWithNotify(x,y,z,meta+1,2);
						Irisia.log(String.valueOf(meta+1),player);
						return true;
					}
					else
					{ // todo 这里以后改掉
						world.spawnEntityInWorld(new EntityItem(world,x,y+1,z,new ItemStack(Items.apple)));
						world.setBlockMetadataWithNotify(x,y,z,0,2);
						return true;
					}
				}

				return false;
			}
		};
		DeathDirt.setHardness(0.5F).setStepSound(Block.soundTypeGravel);
	}
}
