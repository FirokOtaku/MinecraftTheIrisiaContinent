package firok.irisia.thaum;

import firok.irisia.block.AuraCompresser;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.config.ConfigBlocks;

public class IrisiaWandTriggerManager implements IWandTriggerManager
{
	public final static int EventCreateAuraCompresser=0;
	private boolean createAuraCompresser(World world, ItemStack itemStack, EntityPlayer entityPlayer,
	                                     final int x, final int y, final int z, final int side, final int event)
	{
		// 检查方块结构
		if(
				// 奥术石砖
				isBlock(world,x-1,y,z,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x+1,y,z,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x,y,z-1,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x,y,z+1,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x-1,y-2,z,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x+1,y-2,z,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x,y-2,z-1,ConfigBlocks.blockCosmeticSolid,6)
				&& isBlock(world,x,y-2,z+1,ConfigBlocks.blockCosmeticSolid,6)
//				&& isBlock(world,x,y-2,z,ConfigBlocks.blockCosmeticSolid,6)

				// 银树木头 竖直
				&& isBlock(world,x-1,y,z-1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x-1,y-1,z-1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x-1,y-2,z-1,ConfigBlocks.blockMagicalLog,1)

				&& isBlock(world,x-1,y,z+1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x-1,y-1,z+1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x-1,y-2,z+1,ConfigBlocks.blockMagicalLog,1)

				&& isBlock(world,x+1,y,z-1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x+1,y-1,z-1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x+1,y-2,z-1,ConfigBlocks.blockMagicalLog,1)

				&& isBlock(world,x+1,y,z+1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x+1,y-1,z+1,ConfigBlocks.blockMagicalLog,1)
				&& isBlock(world,x+1,y-2,z+1,ConfigBlocks.blockMagicalLog,1)
			)
		{
			// 核心
			setBlock(world,x,y,z,AuraCompresser.BlockCompresser,15);
			// 底面
//			setBlock(world,x,y-2,z,AuraCompresser.BlockCompresser,14);
			// 4支柱
			setBlock(world,x-1,y,z-1,AuraCompresser.BlockCompresser,1);
			setBlock(world,x-1,y-1,z-1,AuraCompresser.BlockCompresser,1);
			setBlock(world,x-1,y-2,z-1,AuraCompresser.BlockCompresser,1);

			setBlock(world,x-1,y,z+1,AuraCompresser.BlockCompresser,2);
			setBlock(world,x-1,y-1,z+1,AuraCompresser.BlockCompresser,2);
			setBlock(world,x-1,y-2,z+1,AuraCompresser.BlockCompresser,2);

			setBlock(world,x+1,y,z-1,AuraCompresser.BlockCompresser,3);
			setBlock(world,x+1,y-1,z-1,AuraCompresser.BlockCompresser,3);
			setBlock(world,x+1,y-2,z-1,AuraCompresser.BlockCompresser,3);

			setBlock(world,x+1,y,z+1,AuraCompresser.BlockCompresser,4);
			setBlock(world,x+1,y-1,z+1,AuraCompresser.BlockCompresser,4);
			setBlock(world,x+1,y-2,z+1,AuraCompresser.BlockCompresser,4);
			// 4外接 上
			setBlock(world,x-1,y,z,AuraCompresser.BlockCompresser,9);
			setBlock(world,x+1,y,z,AuraCompresser.BlockCompresser,10);
			setBlock(world,x,y,z+1,AuraCompresser.BlockCompresser,11);
			setBlock(world,x,y,z-1,AuraCompresser.BlockCompresser,12);
			// 4外接 下
			setBlock(world,x-1,y-2,z,AuraCompresser.BlockCompresser,5);
			setBlock(world,x+1,y-2,z,AuraCompresser.BlockCompresser,6);
			setBlock(world,x,y-2,z+1,AuraCompresser.BlockCompresser,7);
			setBlock(world,x,y-2,z-1,AuraCompresser.BlockCompresser,8);

//			-1
//			-2
		}
		return false;
	}
	private static boolean isBlock(World world, int x, int y, int z, Block block,int meta)
	{
		return world.getBlock(x,y,z)==block&&world.getBlockMetadata(x,y,z)==meta;
	}
	private static void setBlock(World world,int x,int y,int z,Block block,int meta)
	{
		world.setBlock(x,y,z,block,meta,2);
	}

	// todo high 实现这几个接口
	public static boolean checkAuraCompresser(World world,int cx,int cy,int cz)
	{
		return false;
	}
	public static void buildAuraCompresser(World world,int cx,int cy,int cz)
	{
		;
	}
	public static void removeAuraCompresser(World world,int cx,int cy,int cz)
	{
		;
	}


	@Override
	public boolean performTrigger(World world, ItemStack itemStack, EntityPlayer entityPlayer,
	                              int x, int y, int z, int side, int event)
	{
		switch(event)
		{
			case EventCreateAuraCompresser:
				return createAuraCompresser(world, itemStack, entityPlayer, x, y, z, side, event);

			default:
				return false;
		}
	}
}
