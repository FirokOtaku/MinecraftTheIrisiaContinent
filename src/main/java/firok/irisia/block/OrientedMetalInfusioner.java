package firok.irisia.block;

import firok.irisia.Irisia;
import firok.irisia.item.Tools;
import firok.irisia.tileentity.OrientedMetalInfusionerTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OrientedMetalInfusioner
{
	public static final Block OrientedMetalInfusioner=new OrientedMetalInfusionerBlock();
	public static class OrientedMetalInfusionerBlock extends BlockContainer
	{
		public OrientedMetalInfusionerBlock()
		{
			super(Material.iron);
		}

		public void breakBlock(World world, int x, int y, int z, Block block, int meta)
		{
			if(!world.isRemote)
			{
				TileEntity te=world.getTileEntity(x,y,z);
				if(te!=null && te instanceof OrientedMetalInfusionerTE)
				{
					OrientedMetalInfusionerTE te2=(OrientedMetalInfusionerTE)te;
					if(te2.hasOrienter())
					{
						ItemStack stack2drop=te2.getStackOrienter();
						world.spawnEntityInWorld(new EntityItem(world,x,y,z,stack2drop));
					}
				}
			}
			super.breakBlock(world, x, y, z, block, meta);
		}

		@SuppressWarnings("deprecation")
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
			ItemStack heldStack=player.getHeldItem();
			TileEntity te=world.getTileEntity(x,y,z);
			if(te==null||!(te instanceof OrientedMetalInfusionerTE)) // 先进行数据检查 如果没有te就设定一个
			{
				te=new OrientedMetalInfusionerTE(world,x,y,z);
				world.setTileEntity(x,y,z,te);
			}
			OrientedMetalInfusionerTE teOri=(OrientedMetalInfusionerTE)te;

			boolean action=false;
			ItemStack stack2drop=null;

			if(player.isSneaking())
			{
				if(teOri.hasOrienter()) // 如果机器里面有转换器 弹出转换器
				{
					stack2drop=teOri.getStackOrienter();
					teOri.setStackOrienter(null);
					action=true;
				}

				if(heldStack!=null) // 如果不是空手 且物品是转换器 尝试放入转换器 或者替换机器内的转换器
				{
					Item item=heldStack.getItem();
					if(item instanceof Tools.MetalInfusionOrienter)
					{
						if(!player.capabilities.isCreativeMode)
							heldStack.stackSize--;

						ItemStack stack2insert=new ItemStack(item);
						teOri.setStackOrienter(stack2insert);
						action=true;
					}
				}
			}

			NBTTagCompound nbt=new NBTTagCompound();
			teOri.writeToNBT(nbt);
			Irisia.log(nbt.toString(),player);

			if(stack2drop!=null) player.entityDropItem(stack2drop,0.5f);

			return action;
		}
	}
}
