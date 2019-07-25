package firok.irisia.block;

import firok.irisia.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

import static firok.irisia.Util.BlockState.$;

public class Veins
{
	public static final Block VeinIron;
	public static final Block VeinGold;
	public static final Block VeinDiamond;
	static
	{
		VeinIron=new VeinBlock($(Blocks.iron_ore,0),0.1f); // todo 以后改一下生成几率
		VeinGold=new VeinBlock($(Blocks.gold_ore,0),0.1f);
		VeinDiamond=new VeinBlock($(Blocks.diamond_ore,0),0.1f);
	}

	public static class VeinBlock extends Block
	{
		public final float rate;
		public final Util.BlockState target;
		protected VeinBlock(Util.BlockState target,float rate)
		{
			super(Material.rock);
			this.setTickRandomly(true);
			this.setHarvestLevel(null,3);
			this.rate=rate;
			this.target=target;
		}

		private void transformBlock(final World world,final int cx,final int cy,final int cz,Random rand)
		{
			for(int tx=cx-1;tx<=cx+1;tx++)
			{
				for(int tz=cz-1;tz<=cz+1;tz++)
				{
					for(int ty=cy-1;ty<=cy+1&&ty<255;ty++)
					{
						// 只能转换富魔力石
						if(world.getBlock(tx,ty,tz)==DecorationBlocks.RichMagicStone && rand.nextFloat()<=rate)
						{
							world.setBlock(tx,ty,tz,target.block,target.meta,Util.BitSetBlockSendChangeToClients);
						}
					}
				}
			}
		}

		@Override
		public void updateTick(World world, int x, int y, int z, Random rand)
		{
			if(!world.isRemote)
			{
				transformBlock(world,x,y,z,rand);
			}
		}
	}
}
