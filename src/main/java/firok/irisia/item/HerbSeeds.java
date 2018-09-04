package firok.irisia.item;

import firok.irisia.block.HerbsAndMushroom;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class HerbSeeds
{
	static
	{
		;
	}
	public static void postInit() // 防止交叉引用初始化可能导致的问题
	{
		;
	}

	public static class Seed extends Item implements IPlantable
	{
		private HerbsAndMushroom.Herb target;
		void setTarget(HerbsAndMushroom.Herb targetIn)
		{
			target=targetIn;
		}
		private Block soil;

		public Seed()
		{
			this(null,null);
		}
		public Seed(HerbsAndMushroom.Herb targetIn)
		{
			this(targetIn,null);
		}
		protected Seed(HerbsAndMushroom.Herb target, Block soil)
		{
			super();
			this.target=target;
			this.soil=soil;
		}
		/**
		 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
		 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
		 */
		// /**
		//     * Which side was hit. If its -1 then it went the full length of the ray trace. Bottom = 0, Top = 1, East = 2, West
		//     * = 3, North = 4, South = 5.
		//     */
		//    public int sideHit;
		public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit, float p_77648_8_, float p_77648_9_, float p_77648_10_)
		{
			if (sideHit != 1 || target==null)
			{
				return false;
			}
			else if (player.canPlayerEdit(x, y, z, sideHit, itemStack) && player.canPlayerEdit(x, y + 1, z, sideHit, itemStack))
			{
				if(!world.isAirBlock(x, y + 1, z))
					return false;

//				System.out.println("方块"+world.getBlock(x,y,z).toString());
//				System.out.println("能否放置"+target.canPlaceBlockOn(world.getBlock(x,y,z)));

				if (soil==null && target.canPlaceBlockOn(world.getBlock(x,y,z)))
				{
					world.setBlock(x, y + 1, z, this.target);
					--itemStack.stackSize;
					return true;
				}
				else if (soil!=null && soil==world.getBlock(x,y,z))
				{
					world.setBlock(x, y + 1, z, this.target);
					--itemStack.stackSize;
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}

		@Override
		public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
		{
			return target==null?EnumPlantType.Plains:target.getPlantType(world,x,y,z);
		}

		@Override
		public Block getPlant(IBlockAccess world, int x, int y, int z)
		{
			return target;
		}

		@Override
		public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
		{
			return 0;
		}
	}
}
