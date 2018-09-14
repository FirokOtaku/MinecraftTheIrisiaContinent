package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.block.DecorationBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class DecorationItems
{
	// public static ItemBlock Stones; // note ItemBlock貌似不用实例化 这里以后可能删掉
	static
	{
//		Stones=new ItemBlock(DecorationBlocks.Stones)
//		{
//			// public final Block field_150939_a;
//			@SideOnly(Side.CLIENT)
//			private IIcon field_150938_b;
//
//			/**
//			 * Returns 0 for /terrain.png, 1 for /gui/items.png
//			 */
//			@SideOnly(Side.CLIENT)
//			public int getSpriteNumber()
//			{
//				return this.field_150939_a.getItemIconName() != null ? 1 : 0;
//			}
//
//			/**
//			 * Gets an icon index based on an item's damage value
//			 */
//			@SideOnly(Side.CLIENT)
//			public IIcon getIconFromDamage(int meta)
//			{
//				return field_150939_a.getIcon(-1,meta); // -1 : side
//			}
//
//			public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ)
//			{
//				Block block = world.getBlock(x, y, z);
//
//				if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
//				{
//					meta = 1;
//				}
//				else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
//				{
//					if (meta == 0)
//					{
//						--y;
//					}
//
//					if (meta == 1)
//					{
//						++y;
//					}
//
//					if (meta == 2)
//					{
//						--z;
//					}
//
//					if (meta == 3)
//					{
//						++z;
//					}
//
//					if (meta == 4)
//					{
//						--x;
//					}
//
//					if (meta == 5)
//					{
//						++x;
//					}
//				}
//
//				if (stack.stackSize == 0)
//				{
//					return false;
//				}
//				else if (!player.canPlayerEdit(x, y, z, meta, stack))
//				{
//					return false;
//				}
//				else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
//				{
//					return false;
//				}
//				else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, meta, player, stack))
//				{
//					int i1 = this.getMetadata(stack.getItemDamage());
//					int j1 = this.field_150939_a.onBlockPlaced(world, x, y, z, meta, hitX, hitY, hitZ, i1);
//
//					if (placeBlockAt(stack, player, world, x, y, z, meta, hitX, hitY, hitZ, j1))
//					{
//						world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
//						--stack.stackSize;
//					}
//
//					return true;
//				}
//				else
//				{
//					return false;
//				}
//			}
//
//			@Override
//			public int getMetadata(int meta)
//			{
//				return meta;
//			}
//
//			/**
//			 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
//			 * different nameTops based on their damage or NBT.
//			 */
//			@Override
//			public String getUnlocalizedName(ItemStack p_77667_1_)
//			{
//				return this.field_150939_a.getUnlocalizedName();
//			}
//
//			@Override
//			/**
//			 * Returns the unlocalized name of this item.
//			 */
//			public String getUnlocalizedName()
//			{
//				return this.field_150939_a.getUnlocalizedName();
//			}
//
//			/**
//			 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
//			 */
//			@SideOnly(Side.CLIENT)
//			public void getSubItems(Item item, CreativeTabs tab, List list)
//			{
//				this.field_150939_a.getSubBlocks(item, tab, list);
//			}
//
//			@SideOnly(Side.CLIENT)
//			public void registerIcons(IIconRegister p_94581_1_)
//			{
//				String s = this.field_150939_a.getItemIconName();
//
//				if (s != null)
//				{
//					this.field_150938_b = p_94581_1_.registerIcon(s);
//				}
//			}
//
//			public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
//			{
//				int meta=stack.getItemDamage();
//				if (!world.setBlock(x, y, z, field_150939_a, meta, 3))
//				{
//					return false;
//				}
//				if (world.getBlock(x, y, z) == field_150939_a)
//				{
//					field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
//					field_150939_a.onPostBlockPlaced(world, x, y, z, meta);
//				}
//				return true;
//			}
//		};
//		Stones.setHasSubtypes(true);
	}

	public static class StoneClass extends ItemBlock
	{
		// public final Block field_150939_a;
		@SideOnly(Side.CLIENT)
		private IIcon field_150938_b;

		public StoneClass()
		{
			super(DecorationBlocks.Stones);
			this.setHasSubtypes(true);
		}
		public StoneClass(Block b)
		{
			this();
		}

		/**
		 * Returns 0 for /terrain.png, 1 for /gui/items.png
		 */
		@SideOnly(Side.CLIENT)
		public int getSpriteNumber()
		{
			return this.field_150939_a.getItemIconName() != null ? 1 : 0;
		}

		/**
		 * Gets an icon index based on an item's damage value
		 */
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int meta)
		{
			return field_150939_a.getIcon(-1,meta); // -1 : side
		}

		@Override
		public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
		{
			if (stack.stackSize == 0)
			{
				return false;
			}

			Block block = world.getBlock(x, y, z);

			if (side == 0)
			{
				--y;
			}
			if (side == 1)
			{
				++y;
			}
			if (side == 2)
			{
				--z;
			}
			if (side == 3)
			{
				++z;
			}
			if (side == 4)
			{
				--x;
			}
			if (side == 5)
			{
				++x;
			}

			if (!player.canPlayerEdit(x, y, z, side, stack))
			{
				return false;
			}
			else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
			{
				return false;
			}
			else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, stack))
			{
				int i1 = this.getMetadata(stack.getItemDamage());
				int j1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, i1);

				if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, j1))
				{
					world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
					--stack.stackSize;
				}

				return true;
			}
			else
			{
				return false;
			}
		}

		@Override
		public int getMetadata(int meta)
		{
			return meta;
		}

		/**
		 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
		 * different nameTops based on their damage or NBT.
		 */
		@Override
		public String getUnlocalizedName(ItemStack p_77667_1_)
		{
			return this.field_150939_a.getUnlocalizedName();
		}

		@Override
		/**
		 * Returns the unlocalized name of this item.
		 */
		public String getUnlocalizedName()
		{
			return this.field_150939_a.getUnlocalizedName();
		}

		/**
		 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
		 */
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item item, CreativeTabs tab, List list)
		{
			this.field_150939_a.getSubBlocks(item, tab, list);
		}

		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister p_94581_1_)
		{
			String s = this.field_150939_a.getItemIconName();

			if (s != null)
			{
				this.field_150938_b = p_94581_1_.registerIcon(s);
			}
		}

		public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
		{
			int meta=stack.getItemDamage();
			if (!world.setBlock(x, y, z, field_150939_a, meta, 3))
			{
				return false;
			}
			if (world.getBlock(x, y, z) == field_150939_a)
			{
				field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
				field_150939_a.onPostBlockPlaced(world, x, y, z, meta);
			}
			return true;
		}
	}
	public static class LogClass extends ItemBlock
	{
		// public final Block field_150939_a;
		@SideOnly(Side.CLIENT)
		private IIcon field_150938_b;

		public LogClass()
		{
			super(DecorationBlocks.Logs);
			this.setHasSubtypes(true);
		}
		public LogClass(Block b)
		{
			this();
		}

		/**
		 * Returns 0 for /terrain.png, 1 for /gui/items.png
		 */
		@SideOnly(Side.CLIENT)
		public int getSpriteNumber()
		{
			return this.field_150939_a.getItemIconName() != null ? 1 : 0;
		}

		/**
		 * Gets an icon index based on an item's damage value
		 */
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int meta)
		{
			return field_150939_a.getIcon(-1,meta); // -1 : side
		}

		@Override
		public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
		{
			if (stack.stackSize == 0)
			{
				return false;
			}

			Block block = world.getBlock(x, y, z);

			if (side == 0)
			{
				--y;
			}
			if (side == 1)
			{
				++y;
			}
			if (side == 2)
			{
				--z;
			}
			if (side == 3)
			{
				++z;
			}
			if (side == 4)
			{
				--x;
			}
			if (side == 5)
			{
				++x;
			}

			if (!player.canPlayerEdit(x, y, z, side, stack))
			{
				return false;
			}
			else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
			{
				return false;
			}
			else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, stack))
			{
				int i1 = this.getMetadata(stack.getItemDamage());
				int j1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, i1);

				if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, j1))
				{
					world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
					--stack.stackSize;
				}

				return true;
			}
			else
			{
				return false;
			}
		}

		@Override
		public int getMetadata(int meta)
		{
			return meta;
		}

		/**
		 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
		 * different nameTops based on their damage or NBT.
		 */
		@Override
		public String getUnlocalizedName(ItemStack p_77667_1_)
		{
			return this.field_150939_a.getUnlocalizedName();
		}

		@Override
		/**
		 * Returns the unlocalized name of this item.
		 */
		public String getUnlocalizedName()
		{
			return this.field_150939_a.getUnlocalizedName();
		}

		/**
		 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
		 */
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item item, CreativeTabs tab, List list)
		{
			this.field_150939_a.getSubBlocks(item, tab, list);
		}

		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister p_94581_1_)
		{
			String s = this.field_150939_a.getItemIconName();

			if (s != null)
			{
				this.field_150938_b = p_94581_1_.registerIcon(s);
			}
		}

		public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
		{
			int meta=stack.getItemDamage();
			if (!world.setBlock(x, y, z, field_150939_a, meta, 3))
			{
				return false;
			}
			if (world.getBlock(x, y, z) == field_150939_a)
			{
				field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
				field_150939_a.onPostBlockPlaced(world, x, y, z, meta);
			}
			return true;
		}
	}
	public static class BrickClass extends ItemBlock
	{
		// public final Block field_150939_a;
		@SideOnly(Side.CLIENT)
		private IIcon field_150938_b;

		public BrickClass()
		{
			super(DecorationBlocks.Bricks);
			this.setHasSubtypes(true);
		}
		public BrickClass(Block b)
		{
			this();
		}

		/**
		 * Returns 0 for /terrain.png, 1 for /gui/items.png
		 */
		@SideOnly(Side.CLIENT)
		public int getSpriteNumber()
		{
			return this.field_150939_a.getItemIconName() != null ? 1 : 0;
		}

		/**
		 * Gets an icon index based on an item's damage value
		 */
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int meta)
		{
			return field_150939_a.getIcon(-1,meta); // -1 : side
		}

		@Override
		public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
		{
			if (stack.stackSize == 0)
			{
				return false;
			}

			Block block = world.getBlock(x, y, z);

			if (side == 0)
			{
				--y;
			}
			if (side == 1)
			{
				++y;
			}
			if (side == 2)
			{
				--z;
			}
			if (side == 3)
			{
				++z;
			}
			if (side == 4)
			{
				--x;
			}
			if (side == 5)
			{
				++x;
			}

			if (!player.canPlayerEdit(x, y, z, side, stack))
			{
				return false;
			}
			else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
			{
				return false;
			}
			else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, stack))
			{
				int i1 = this.getMetadata(stack.getItemDamage());
				int j1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, i1);

				if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, j1))
				{
					world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
					--stack.stackSize;
				}

				return true;
			}
			else
			{
				return false;
			}
		}

		@Override
		public int getMetadata(int meta)
		{
			return meta;
		}

		/**
		 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
		 * different nameTops based on their damage or NBT.
		 */
		@Override
		public String getUnlocalizedName(ItemStack p_77667_1_)
		{
			return this.field_150939_a.getUnlocalizedName();
		}

		@Override
		/**
		 * Returns the unlocalized name of this item.
		 */
		public String getUnlocalizedName()
		{
			return this.field_150939_a.getUnlocalizedName();
		}

		/**
		 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
		 */
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item item, CreativeTabs tab, List list)
		{
			this.field_150939_a.getSubBlocks(item, tab, list);
		}

		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister p_94581_1_)
		{
			String s = this.field_150939_a.getItemIconName();

			if (s != null)
			{
				this.field_150938_b = p_94581_1_.registerIcon(s);
			}
		}

		public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
		{
			int meta=stack.getItemDamage();
			if (!world.setBlock(x, y, z, field_150939_a, meta, 3))
			{
				return false;
			}
			if (world.getBlock(x, y, z) == field_150939_a)
			{
				field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
				field_150939_a.onPostBlockPlaced(world, x, y, z, meta);
			}
			return true;
		}
	}
}
