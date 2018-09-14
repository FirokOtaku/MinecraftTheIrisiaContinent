package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class DecorationBlocks
{
	public final static Block CorrosedBlock; // 被腐蚀的方块
	public final static Block Loess; // 黄土
	public final static Block BlackDirt; // 黑土
	public final static Block Stones;
	public final static Block Bricks;
	public final static Block Logs;
	static
	{
		CorrosedBlock=new Block(Material.sand){};
		Loess=new Block(Material.sand){};
		BlackDirt=new Block(Material.ground){};
		Stones=new BlockStones();
		Bricks=new BlockBricks();
		Logs=new BlockLogs();
	}
	// todo 以后尝试把这里再封装一层 把这几个类写成一个
	public static class BlockStones extends BlockStone
	{
		public BlockStones()
		{
			super();
			this.setBlockTextureName("decor_stones");
			this.setBlockName("DecorStones");
		}

		public static byte subTypes=4;
		public static IIcon[] icons =new IIcon[subTypes];
		public static String[] names =new String[]{
				"irisia:decor_black_stone",
				"irisia:decor_brown_stone",
				"irisia:decor_blue_stone",
				"irisia:decor_elf_stone"};
		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iir)
		{
			for(byte i=0;i<subTypes;i++)
			{
				icons[i]=iir.registerIcon(names[i]);
			}
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta)
		{
			return meta>=0&&meta<subTypes?icons[meta]:Blocks.stone.getIcon(side,meta);
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
		{
			return getIcon(side,world.getBlockMetadata(x, y, z));
		}

		/**
		 * Determines the damage on the item the block drops. Used in cloth and wood.
		 */
		public int damageDropped(int meta)
		{
			return meta;
		}

		@Override // note 被itemBlock放置的时候执行的东西 用来给方块设置meta
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
		{
			int meta=itemStack.getItemDamage();
			if(meta<0) meta=0;
			if(meta>=subTypes) meta=0;
			world.setBlockMetadataWithNotify(x,y,z,meta,2);
		}
		@Override // note 这个方法暂时不知道干啥的 以后可能删掉 // low 也许可以研究一下楼梯stairs的实现
		/**
		 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
		 */
		public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
		{
			// world.setBlockMetadataWithNotify(x,y,z,meta,2);
			return meta;
		}
		/**
		 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
		 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
		 */
		@Override
		protected ItemStack createStackedBlock(int meta)
		{
			return new ItemStack(this,1,meta);
		}
		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
		{
			if(Irisia.IN_DEV && player.isSneaking()) // note 这里以后删掉
			{
				int meta=world.getBlockMetadata(x,y,z);
				if(meta<subTypes-1) meta++;
				else meta=0;
				world.setBlockMetadataWithNotify(x,y,z,meta,2);
				return true;
			}
			return false;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void getSubBlocks(Item item, CreativeTabs tab, List list)
		{
			for(byte i=0;i<subTypes;i++)
			{
				list.add(new ItemStack(this,1,i));//BlockStairs
			}
		}
		/**
		 * Get the block's damage value (for use with pick block).
		 */
		@Override
		public int getDamageValue(World world, int x, int y, int z)
		{
			return world.getBlockMetadata(x, y, z);
		}
		@Override
		public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
		{
			return Item.getItemFromBlock(Stones);
		}
	}
	public static class BlockLogs extends BlockLog // fixme 这个类完全没写完
	{
		public BlockLogs()
		{
			super();
			this.setBlockTextureName("decor_woods");
			this.setBlockName("DecorWoods");
		}
		public static byte subTypes=4;
		public static IIcon[] iconTops =new IIcon[subTypes];
		public static IIcon[] iconSides=new IIcon[subTypes];
		public static String[] nameTops =new String[]{
				"irisia:decor_black_stone",
				"irisia:decor_brown_stone",
				"irisia:decor_blue_stone",
				"irisia:decor_elf_stone"};
		public static String[] nameSides=new String[]{

		};
		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iir)
		{
			for(byte i=0;i<subTypes;i++)
			{
				iconTops[i]=iir.registerIcon(nameTops[i]);
				iconSides[i]=iir.registerIcon(nameSides[i]);
			}
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta)
		{
			return meta>=0&&meta<subTypes? iconTops[meta]:Blocks.stone.getIcon(side,meta);
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
		{
			return getIcon(side,world.getBlockMetadata(x, y, z));
		}

		/**
		 * Determines the damage on the item the block drops. Used in cloth and wood.
		 */
		public int damageDropped(int meta)
		{
			return meta;
		}

		@Override // note 被itemBlock放置的时候执行的东西 用来给方块设置meta
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
		{
			int meta=itemStack.getItemDamage();
			if(meta<0) meta=0;
			if(meta>=subTypes) meta=0;
			world.setBlockMetadataWithNotify(x,y,z,meta,2);
		}
		@Override // note 这个方法暂时不知道干啥的 以后可能删掉 // low 也许可以研究一下楼梯stairs的实现
		/**
		 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
		 */
		public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
		{
			// world.setBlockMetadataWithNotify(x,y,z,meta,2);
			return meta;
		}
		/**
		 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
		 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
		 */
		@Override
		protected ItemStack createStackedBlock(int meta)
		{
			return new ItemStack(this,1,meta);
		}
		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
		{
			if(Irisia.IN_DEV && player.isSneaking()) // note 这里以后删掉
			{
				int meta=world.getBlockMetadata(x,y,z);
				if(meta<subTypes-1) meta++;
				else meta=0;
				world.setBlockMetadataWithNotify(x,y,z,meta,2);
				return true;
			}
			return false;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void getSubBlocks(Item item, CreativeTabs tab, List list)
		{
			for(byte i=0;i<subTypes;i++)
			{
				list.add(new ItemStack(this,1,i));//BlockStairs
			}
		}
		/**
		 * Get the block's damage value (for use with pick block).
		 */
		@Override
		public int getDamageValue(World world, int x, int y, int z)
		{
			return world.getBlockMetadata(x, y, z);
		}
		@Override
		public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
		{
			return Item.getItemFromBlock(Stones);
		}
	}
	public static class BlockBricks extends Block
	{
		public BlockBricks()
		{
			super(Material.rock);
			setHardness(2.0F);setResistance(10.0F);setStepSound(soundTypePiston);
			setBlockName("decor_bricks");
			setBlockTextureName("DecorBricks");
		}
		public static byte subTypes=4;
		public static IIcon[] icons =new IIcon[subTypes];
		public static String[] names =new String[]{
				"irisia:decor_black_brick",
				"irisia:decor_brown_brick",
				"irisia:decor_blue_brick",
				"irisia:decor_elf_brick"};
		@Override
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iir)
		{
			for(byte i=0;i<subTypes;i++)
			{
				icons[i]=iir.registerIcon(names[i]);
			}
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta)
		{
			return meta>=0&&meta<subTypes?icons[meta]:Blocks.brick_block.getIcon(side,meta);
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
		{
			return getIcon(side,world.getBlockMetadata(x, y, z));
		}

		/**
		 * Determines the damage on the item the block drops. Used in cloth and wood.
		 */
		public int damageDropped(int meta)
		{
			return meta;
		}

		@Override // note 被itemBlock放置的时候执行的东西 用来给方块设置meta
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
		{
			int meta=itemStack.getItemDamage();
			if(meta<0) meta=0;
			if(meta>=subTypes) meta=0;
			world.setBlockMetadataWithNotify(x,y,z,meta,2);
		}
		@Override // note 这个方法暂时不知道干啥的 以后可能删掉 // low 也许可以研究一下楼梯stairs的实现
		/**
		 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
		 */
		public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
		{
			// world.setBlockMetadataWithNotify(x,y,z,meta,2);
			return meta;
		}
		/**
		 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
		 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
		 */
		@Override
		protected ItemStack createStackedBlock(int meta)
		{
			return new ItemStack(this,1,meta);
		}
		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
		{
			if(Irisia.IN_DEV && player.isSneaking()) // note 这里以后删掉
			{
				int meta=world.getBlockMetadata(x,y,z);
				if(meta<subTypes-1) meta++;
				else meta=0;
				world.setBlockMetadataWithNotify(x,y,z,meta,2);
				return true;
			}
			return false;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void getSubBlocks(Item item, CreativeTabs tab, List list)
		{
			for(byte i=0;i<subTypes;i++)
			{
				list.add(new ItemStack(this,1,i));//BlockStairs
			}
		}
		/**
		 * Get the block's damage value (for use with pick block).
		 */
		@Override
		public int getDamageValue(World world, int x, int y, int z)
		{
			return world.getBlockMetadata(x, y, z);
		}
		@Override
		public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
		{
			return Item.getItemFromBlock(Bricks);
		}
	}
}
