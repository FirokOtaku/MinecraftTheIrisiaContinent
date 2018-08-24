package firok.irisia.block;

import firok.irisia.Irisia;
import firok.irisia.inventory.GuiElementLoader;
import firok.irisia.tileentity.BerryMixerTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class MachineBlocks
{
	public static BlockContainer BerryMixer;

	static
	{
		BerryMixer=new InventoryMachine()
		{
			@Override
			public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
			{
				return new BerryMixerTE((byte)1);
			}
			@Override
			public int getRenderType()
			{
				return 0;
			}
			@Override
			public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
			{
				if (world.isRemote)
				{
					return true;
				}
				else
				{
					BerryMixerTE berryMixerTE = (BerryMixerTE)world.getTileEntity(x, y, z);

					if (berryMixerTE != null)
					{
						player.openGui(Irisia.instance,GuiElementLoader.GUI_BERRY_MIXER,world,x,y,z);
					}

					return true;
				}
			}
			@Override
			public void onBlockPreDestroy(World world, int x, int y, int z, int meta)
			{
				dropItems(world,x,y,z);
			}

			public void dropItems(World world,int x,int y,int z)
			{
				if(world.isRemote)
					return;
				TileEntity te=world.getTileEntity(x,y,z);
				if(te==null)
					return;

				BerryMixerTE berryMixerTE=(BerryMixerTE)te;
				for(byte i=0;i<berryMixerTE.getSizeInventory();i++)
				{
					ItemStack itemStack=berryMixerTE.getStackInSlot(i);
					if(itemStack==null||itemStack.stackSize<=0)
						continue;

					world.spawnEntityInWorld(new EntityItem(world,x,y,z,berryMixerTE.getStackInSlot(i)));
				}
			}
		};
	}
	public abstract static class InventoryMachine extends BlockContainer
	{
		protected InventoryMachine()
		{
			super(Material.iron);
		}
	}

}
