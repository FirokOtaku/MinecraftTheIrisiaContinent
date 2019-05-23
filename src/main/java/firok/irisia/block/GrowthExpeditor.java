package firok.irisia.block;

import firok.irisia.tileentity.GrowthExpeditorTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GrowthExpeditor
{
	public static final Block GrowthExpeditor =new GrowthExpeditorBlock();
	public static class GrowthExpeditorBlock extends BlockContainer
	{
		protected GrowthExpeditorBlock()
		{
			super(Material.iron);
		}

		@Override
		public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
		{
//			return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
			return side;
		}

		@SuppressWarnings("deprecation")
		@Override
		public boolean hasTileEntity()
		{
			return true;
		}

		@Override
		public TileEntity createNewTileEntity(World world, int meta)
		{
			return new GrowthExpeditorTE(world,meta);
		}
		@Override
		public TileEntity createTileEntity(World world, int metadata)
		{
			return createNewTileEntity(world, metadata);
		}

		IIcon iconFacing;
		IIcon iconOpposite;
		IIcon icon;
		@Override
		public IIcon getIcon(int side, int meta)
		{
			return side==meta?iconFacing:
					side==ForgeDirection.getOrientation(meta).getOpposite().ordinal()?iconOpposite:
							icon;
		}
		@Override
		public void registerBlockIcons(IIconRegister iir)
		{
//			super.registerBlockIcons(iir);
			iconFacing=iir.registerIcon(getTextureName()+"_facing");
			iconOpposite=iir.registerIcon(getTextureName()+"_opposite");
			icon=iir.registerIcon(getTextureName());
		}
	}
}
