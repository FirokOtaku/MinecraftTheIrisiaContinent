package firok.irisia.ability;

import java.util.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CauseGrowth
{
	// 输入树苗的位置  树干高度  树木高度  树冠半径  树冠形状编码 树干方块组 树叶方块组
	public static void BaseAt(World worldIn,int heightWoodsIn,int heightTreeIn,int radiusLeavesIn,BlockGroup blockWoodIn,BlockGroup blockLeaveIn,boolean ignoreEnvironmentIn,boolean ignoreSubBlocksIn)
	{
//		LinkedList<BlockPos> posWoods=new LinkedList<BlockPos>();
//		LinkedList<BlockPos> posLeaves=new LinkedList<BlockPos>();

		// 添加方块位置到表里
			// 在这里添加能不能生长的逻辑 比如是否覆盖别的方块 是否超过地图高度
		
		
		// 根据方块位置生成方块
//		for(BlockPos tempPos:posWoods)
//		{
//			worldIn.setBlockState(tempPos, ignoreSubBlocksIn? blockWoodIn.getRandomBlock():blockWoodIn.getFirstBlock());
//		}
//		for(BlockPos tempPos:posLeaves)
//		{
//			worldIn.setBlockState(tempPos, ignoreSubBlocksIn? blockLeaveIn.getRandomBlock():blockWoodIn.getFirstBlock());
//		}
	}
	
	static public boolean canGrow(int heightWoodsIn,int heightTreeIn,int radiusLeavesIn)
	{
		return true;
	}
	
	static public class BlockGroup
	{
//		public LinkedList<BlockItem> blocks;
//		public int sumWeight;
//		public Random rand;
//
//		public BlockGroup(Random randIn)
//		{
//			blocks=new LinkedList<BlockItem>();
//			sumWeight=0;
//			rand=randIn;
//		}
//		public void addBlock(IBlockState blockIn,int weightIn)
//		{
//			blocks.add(new BlockItem(blockIn,weightIn));
//			sumWeight+=weightIn;
//		}
//		public boolean hasBlock(Block blockIn)
//		{
//			for(BlockItem tempBlock:blocks)
//			{
//				if(tempBlock.block==blockIn)
//					return true;
//			}
//			return false;
//		}
//		public int getWeight(IBlockState blockIn)
//		{
//			for(BlockItem tempBlock:blocks)
//			{
//				if(tempBlock.block==blockIn)
//					return tempBlock.weight;
//			}
//			return 0;
//		}
//		public IBlockState getRandomBlock()
//		{
//			if(blocks.size()==1)
//				return blocks.get(0).block;
//			else
//			{
//				int weightNow=rand.nextInt(sumWeight);
//				for(BlockItem tempBlock:blocks)
//				{
//					weightNow-=tempBlock.weight;
//					if(weightNow<=0)
//						return tempBlock.block;
//				}
//			}
//			return Blocks.AIR.getDefaultState();
//		}
//		public IBlockState getFirstBlock()
//		{
//			if(blocks.size()>=1)
//				return blocks.get(0).block;
//			else
//				return Blocks.AIR.getDefaultState();
//		}
//
//		static private class BlockItem
//		{
//			public IBlockState block;
//			public int weight;
//			public BlockItem(IBlockState blockIn,int weightIn)
//			{
//				block=blockIn;
//				weight=weightIn;
//			}
//		}
	}
}
