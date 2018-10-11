package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.inventory.GuiElementLoader;
import firok.irisia.item.RawMaterials;
import firok.irisia.tileentity.BerryMixerTE;
import firok.irisia.tileentity.LockedChestTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

public class MachineBlocks
{
	public final static Block StormCollector;
	static
	{
		StormCollector =new Block(Material.iron)
		{
			{
				this.setTickRandomly(true);
			}

			public boolean hasRod(World world,int x,int y,int z) // todo 现在是天线高 以后可能改成基座的样子
			{
				return
						world.getBlock(x,y+1,z)== Blocks.iron_bars
								&& world.getBlock(x,y+2,z)== Blocks.iron_bars;
			}
			//			private void r()
//			{
//				this.updateTick();
//			}
			// meta: 0-没装电池 1-装了电池 2-有电状态
			@Override
			public void updateTick(World world, int x, int y, int z, Random rand)
			{
				if(world.isRemote||!world.isThundering()) return;
				int meta=world.getBlockMetadata(x,y,z);
				if(meta==1 && hasRod(world,x,y,z))
				{
					EntityLightningBolt l=new EntityLightningBolt(world,x,y,z);
					world.spawnEntityInWorld(l);
					world.setBlockMetadataWithNotify(x,y,z,2,2);
				}
			}
			@Override
			public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
			{
				if(!world.isRemote)
				{
					int meta=world.getBlockMetadata(x,y,z);
					if(meta==0)
					{
						ItemStack held=player.getHeldItem();

						if(held!=null&&held.getItem()==RawMaterials.StormBall&&held.stackSize>0)
						{
							if(!player.capabilities.isCreativeMode)
							{
								held.stackSize--;
								player.inventory.markDirty();
							}
							world.setBlockMetadataWithNotify(x,y,z,1,2);
						}
					}
					else if(meta==1)
					{
						world.setBlockMetadataWithNotify(x,y,z,0,2);
						world.spawnEntityInWorld(new EntityItem(world,x,y,z,new ItemStack(RawMaterials.StormBall)));
					}
					else if(meta==2)
					{
						world.setBlockMetadataWithNotify(x,y,z,0,2);
						world.spawnEntityInWorld(new EntityItem(world,x,y,z,new ItemStack(RawMaterials.ChargedStormBall)));
					}
				}

				return true;
			}
			protected IIcon icon0;
			protected IIcon icon1;
			protected IIcon icon2;
			@SideOnly(Side.CLIENT)
			public void registerBlockIcons(IIconRegister iir)
			{
				this.icon0 = iir.registerIcon("irisia:block_storm_collector0");
				this.icon1 = iir.registerIcon("irisia:block_storm_collector1");
				this.icon2 = iir.registerIcon("irisia:block_storm_collector2");
			}
			@Override
			@SideOnly(Side.CLIENT)
			public IIcon getIcon(int side, int meta)
			{
				switch (meta)
				{
					default:case 0:return icon0;
					case 1:return icon1;
					case 2:return icon2;
				}
			}
//			@Override
//			@SideOnly(Side.CLIENT)
//			public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
//			{
//				return this.getIcon(side, world.getBlockMetadata(x, y, z));
//			}
		};
	}
	public final static BlockContainer BerryMixer;
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
		protected InventoryMachine(Material material){super(material);}
	}

}
