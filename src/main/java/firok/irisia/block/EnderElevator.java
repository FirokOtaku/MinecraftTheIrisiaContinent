package firok.irisia.block;

import firok.irisia.Irisia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;

import java.util.ArrayList;
import java.util.List;

public class EnderElevator // fixme 整个电梯运行有问题 寻找实体的范围可能太小了 另外不能传送玩家
{
	public static final Block ElevatorPlatform;
	public static final Block ElevatorController;

	private static Block[] blockConnectors;
	public static boolean isConnector(Block block)
	{
		for(Block b:blockConnectors)
		{
			if(b==block)
			{
				return true;
			}
		}
		return false;
	}

	static
	{
		ElevatorPlatform=new Block(Material.iron){};
		ElevatorController=new Block(Material.iron){
			@Override
			public boolean onBlockActivated(World world,final int x,final int y,final int z, EntityPlayer player, int side, float subX, float subY, float subZ)
			{
				if(world.isRemote || y<=0)
					return false;

				ItemStack held=player.getHeldItem();
				if(held==null||held.getItem()!= ConfigItems.itemWandCasting)
					return false;

				final int cx=x;
				final int cy=y-1;
				final int cz=z;

				//Irisia.log("center : "+cx+","+cy+","+cz,player);

				Block[][] blocks=new Block[5][5];
				blocks[2][2]=world.getBlock(cx,cy,cz);
				//Irisia.log("is platform : "+(blocks[2][2]==ElevatorPlatform),player);
				if(blocks[2][2]!=ElevatorPlatform)
					return false;

				byte radius=-1;

				for(int x1=cx-2;x1<=cx+2;x1++) // 找最外圈
				{
					for(int z1=cz-2;z1<=cz+2;z1++)
					{
						if(blocks[x1-cx+2][z1-cz+2]==null)
							blocks[x1-cx+2][z1-cz+2]=world.getBlock(x1,cy,z1);

						if(blocks[x1-cx+2][z1-cz+2]!=ElevatorPlatform)
							break;

						if(x1==cx+2 && z1==cz+2)
							radius=2;
					}
				}
				if(radius<=0)
					for(int x2=cx-1;x2<=cx+1;x2++) // 内圈
					{
						for(int z2=cz-1;z2<=cz+1;z2++)
						{
							if(blocks[x2-cx+2][z2-cz+2]==null)
								blocks[x2-cx+2][z2-cz+2]=world.getBlock(x2,cy,z2);

							if(blocks[x2-cx+2][z2-cz+2]!=ElevatorPlatform)
								break;

							if(x2==cx+1 && z2==cz+1)
								radius=1;
						}
					}
				//Irisia.log("radius : "+radius,player);
				if(radius<=0)
					return false;

				// 找上层或者下层的核心
				boolean isDown=player.isSneaking();
				int y2found=-1;
				if(isDown)
				{
					for(int cy2=y-2;cy2>=0&&cy2>=cy-radius*5-4;cy2--)
					{
						//Irisia.log(cy2+" : "+world.getBlock(cx,cy2,cz).getLocalizedName(),player);
						if(world.getBlock(cx,cy2,cz)==ElevatorController)
						{
							y2found=cy2;
							break;
						}
					}
				}
				else
				{
					for(int cy2=y+1;cy2<=256&&cy2<=cy+radius*5+4;cy2++)
					{
						//Irisia.log(cy2+" : "+world.getBlock(cx,cy2,cz).getLocalizedName(),player);
						if(world.getBlock(cx,cy2,cz)==ElevatorController)
						{
							y2found=cy2;
							break;
						}
					}
				}
				//Irisia.log("y2 found : "+y2found,player);
				if(y2found<0)
					return false;

				int lenMove=y2found-cy;

				List entities=world.getEntitiesWithinAABBExcludingEntity(null,
						AxisAlignedBB.getBoundingBox
								(cx-radius-1,cy-1,cz-radius-1,
						cx+radius,cy+3,cz+radius));

//				Irisia.log("list size : "+entities.size(),player);
//				Irisia.log("move len : "+lenMove,player);

				for(Object obj:entities)
				{
					if(obj instanceof EntityLivingBase)
					{
						EntityLivingBase en=(EntityLivingBase)obj;
						en.setPositionAndUpdate(en.posX,en.posY+lenMove,en.posZ);
					}
					else
					{
						Entity en=(Entity)obj;
						en.setPosition(en.posX,en.posY+lenMove,en.posZ);
					}
				}

				return true;
			}
		};
	}
}
