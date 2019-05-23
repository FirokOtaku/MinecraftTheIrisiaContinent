package firok.irisia.ability;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CausePlantGrowth
{
	public static boolean At(World world, int x, int y, int z)
	{
		if(world.isRemote)
			return false;

		Block block = world.getBlock(x, y, z);

		if (block instanceof IGrowable)
		{
			IGrowable igrowable = (IGrowable)block;

			if (igrowable.func_149851_a(world, x, y, z, world.isRemote))
			{
				if (igrowable.func_149852_a(world, world.rand, x, y, z))
				{
					igrowable.func_149853_b(world, world.rand, x, y, z);
				}
				return true;
			}
		}
		return false;
	}
}
