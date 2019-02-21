package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.tileentity.AuraCompresserTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.*;

import java.util.Random;

public class AuraCompresser
{
	public static final Block BlockCompresser=new CompresserBlock();

	public static class CompresserBlock extends BlockContainer
	{
		IIcon[] icons=new IIcon[16];
		protected CompresserBlock()
		{
			super(Material.iron);
			this.setHardness(5.0F);
			this.setResistance(500.0F);
			this.setLightLevel(0.2F);
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		@Override
		public Item getItemDropped(int meta, Random rand, int foru)
		{
			switch(meta)
			{
				case 1: case 2: case 3: case 4:
					return Item.getItemFromBlock(ConfigBlocks.blockMagicalLog);
				case 5: case 6: case 7: case 8:
				case 9: case 10: case 11: case 12:
					return Item.getItemFromBlock(ConfigBlocks.blockCosmeticSolid);
				case 15:
					return Item.getItemFromBlock(ConfigBlocks.blockMetalDevice);
				default:
					return null;
			}
		}

		@Override
		public int damageDropped(int meta)
		{
			switch(meta)
			{
				case 1: case 2: case 3: case 4:
					return ConfigBlocks.blockMagicalLog.damageDropped(1);
				case 5: case 6: case 7: case 8:
				case 9: case 10: case 11: case 12:
					return ConfigBlocks.blockCosmeticSolid.damageDropped(6);
				case 15:
					return ConfigBlocks.blockMetalDevice.damageDropped(9);
				default:
					return super.damageDropped(meta);
			}
		}

		public int quantityDropped(Random par1Random) {
			return 1;
		}
		public boolean hasTileEntity(int metadata) {
			return metadata==15;
		}

		@Override
		public void onBlockPreDestroy(World world,final int x,final int y,final int z,final int meta)
		{
			// super.onBlockPreDestroy(world, x, y, z, meta);
			int cx=x,cz=z,cy=y;
			boolean coreFound=false;
			switch(meta)
			{
				case 1:
					cx=x+1;
					cz=z+1;
					break;
				case 2:
					cx=x+1;
					cz=z-1;
					break;
				case 3:
					cx=x-1;
					cz=z+1;
					break;
				case 4:
					cx=x-1;
					cz=z-1;
					break;
				case 5:
					cx=x+1;
					break;
				case 6:
					cx=x-1;
					break;
				case 7:
					cz=z-1;
					break;
				case 8:
					cz=z+1;
					break;
				case 15:
					coreFound=true;
					break;
				default:
					break;
			}
			if(meta!=15)
			{
				if(world.getBlockMetadata(cx,cy,cz)==15)
				{
					coreFound=true;
				}
				if(world.getBlockMetadata(cx,cy+1,cz)==15)
				{
					cy=cy+1;
					coreFound=true;
				}
				else if(world.getBlockMetadata(cx,cy+2,cz)==15)
				{
					cy=cy+2;
					coreFound=true;
				}
			}
			Irisia.log(String.format("cx=%d,cy=%d,cz=%d",cx,cy,cz));

			if(coreFound)
			{
				TileEntity te=world.getTileEntity(cx,cy,cz);
				if(te!=null)
				{
					AuraCompresserTE acte=(AuraCompresserTE)te;
					acte.remove();
				}
			}
		}


		@Override
		public void registerBlockIcons(IIconRegister iir)
		{
			for(int i=0;i<icons.length;i++)
			{
				this.icons[i]=iir.registerIcon("irisia:compresser"+i);
			}
		}

		@SideOnly(Side.CLIENT)
		public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
			return icons[world.getBlockMetadata(x,y,z)];
		}

		public IIcon calculateIcon(IBlockAccess world,int x,int y,int z,int side)
		{
			return icons[0];
		}

		public TileEntity createTileEntity(World world, int metadata)
		{
			return metadata==15?new AuraCompresserTE():null;
		}

		@Override
		public TileEntity createNewTileEntity(World world, int metadata)
		{
			return metadata==15?new AuraCompresserTE():null;
		}
	}
}
