package firok.irisia.block;

import firok.irisia.tileentity.BlockConverterTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;

import java.util.HashMap;

public class BlockConverter
{
	public static final String Lava="CLava";
	public static final String Ice="CIce";
	public static final BlockConverterBlock ConverterLava;
	public static final BlockConverterBlock ConverterIce;
	static
	{
		ConverterLava=new BlockConverterBlock(Lava, Blocks.stone,0,Blocks.lava,0,Aspect.FIRE,8);
		ConverterIce=new BlockConverterBlock(Ice,Blocks.water,0,Blocks.ice,0,Aspect.ORDER,2);
	}

	public static class BlockConverterBlock extends BlockContainer
	{
		private static HashMap<String,BlockConverterBlock> converters=new HashMap<>();
		public static BlockConverterBlock getConverter(String type)
		{
			return converters.get(type);
		}

		String type;
		public Block originBlock;
		public int originMeta;
		public Block targetBlock;
		public int targetMeta;
		public Aspect aspectNeed;
		public int amountNeed;
		public BlockConverterBlock(String type,Block fromBlock,int fromMeta,Block toBlock,int toMeta,Aspect aspectNeed,int amountNeed)
		{
			super(Material.iron);
			this.originBlock=fromBlock;
			this.originMeta=fromMeta;
			this.targetBlock=toBlock;
			this.targetMeta=toMeta;
			this.aspectNeed=aspectNeed;
			this.amountNeed=amountNeed;
			this.type=type;
			converters.put(type,this); // 注册到转换器表
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
			return new BlockConverterTE(world,type,meta);
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
