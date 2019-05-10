package firok.irisia.block;

import firok.irisia.Irisia;
import firok.irisia.item.Tools;
import firok.irisia.tileentity.OrientedMetalInfusionerTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OrientedMetalInfusioner
{
	public static Block OrientedMetalInfusioner=new OrientedMetalInfusionerBlock();
	public static class OrientedMetalInfusionerBlock extends BlockContainer
	{
		public OrientedMetalInfusionerBlock()
		{
			super(Material.iron);
		}

		@Override
		public boolean hasTileEntity()
		{
			return true;
		}

		@Override
		public TileEntity createTileEntity(World world, int metadata)
		{
			return new OrientedMetalInfusionerTE(world);
		}

		@Override
		public TileEntity createNewTileEntity(World world, int metadata)
		{
			return createTileEntity(world,metadata);
		}

		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
		{
			if(world.isRemote) return false;



//			return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
			ItemStack itemStack=player.getHeldItem();
			TileEntity te=world.getTileEntity(x,y,z);
			if(te==null||!(te instanceof OrientedMetalInfusionerTE)) // 先进行数据检查 如果没有te就设定一个
			{
				te=new OrientedMetalInfusionerTE(world,x,y,z);
				world.setTileEntity(x,y,z,te);
			}
			OrientedMetalInfusionerTE teOri=(OrientedMetalInfusionerTE)te;

			if(player.isSneaking())
			{
				NBTTagCompound nbt=new NBTTagCompound();
				teOri.writeToNBT(nbt);
				Irisia.log(nbt.toString(),player);
				return true;
			}

			boolean action=false;
			ItemStack stack2drop=null;
			if(teOri.hasOrienter()) // 如果机器里面有转换器 弹出转换器
			{
				stack2drop=teOri.getStackOrienter();
				teOri.setStackOrienter(null);
				action=true;
			}

			if(itemStack!=null) // 如果不是空手 且物品是转换器 尝试放入转换器 或者替换机器内的转换器
			{
				Item item=itemStack.getItem();
				if(item instanceof Tools.MetalInfusionOrienter)
				{
					if(!player.capabilities.isCreativeMode)
						itemStack.stackSize--;

					ItemStack stack2insert=new ItemStack(item);
					teOri.setStackOrienter(stack2insert);
					action=true;
				}
			}

			if(stack2drop!=null) player.entityDropItem(stack2drop,0.5f);

			return action;
		}
	}
}
