package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.item.Foods;
import firok.irisia.item.HerbSeeds;
import firok.irisia.item.RawMaterials;
import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;
import java.util.Random;

public class HerbsAndMushroom
{
	public final static Herb DeathGrassHerb;
	public final static Herb MoonGrassHerb;
	public final static Herb SpicyRootHerb;
	public final static Herb InkrGrassHerb;
	public final static Herb AppleGrassHerb;
	public final static Herb MonaHerb;
	public final static Herb StarousHerb;
	public final static Herb BrownWheat;
	public final static Herb HuckTeaHerb;

	public final static Herb TestWater;

	public final static Mushroom ShadowMushroom;
	static
	{
		DeathGrassHerb =new Herb(EnumPlantType.Cave, Items.gold_ingot,
				3,1,1,
				null,0,3,new int[]{2,4},1);
		MoonGrassHerb =new Herb(EnumPlantType.Plains,Items.diamond,
				3,1,1,
				null,0,3,new int[]{2,4},1);
		SpicyRootHerb =new Herb(EnumPlantType.Desert,Items.apple,
				3,1,1,
				null,0,3,new int[]{2,4},1);

		InkrGrassHerb =new Herb(EnumPlantType.Plains,RawMaterials.Ink,
				2,1,1,
				null,0,3,new int[]{3,5},1);
		AppleGrassHerb =new Herb(EnumPlantType.Plains,Items.apple,
				1,1,0,
				null,0,3,new int[]{3,6},1);
		MonaHerb =new Herb(EnumPlantType.Plains,Foods.MonaFruitBerry,
				1,1,0,
				null,0,3,new int[]{3,6},1);
		BrownWheat=new Herb(EnumPlantType.Crop,RawMaterials.BrownWheat,
				1,2,1,
				null,0,3,new int[]{3,6},1);
		HuckTeaHerb=new Herb(EnumPlantType.Plains, Foods.HuckTeaLeafBerry,
				2,4,0,
				null,0,3,new int[]{3,6},1);
		StarousHerb=new Herb(EnumPlantType.Plains,Foods.StarousFruitBerry,
				1,1,0,
				null,0,3,new int[]{3,6},1);

		// 测试用 for debug
		// fixme 目前 如果药草类型是水生 并不能种到水上
		TestWater=new Herb(EnumPlantType.Water,Items.apple,
				3,1,1,
				null,0,3,new int[]{2,4},1);

		// 蘑菇 mushrooms
		ShadowMushroom =(Mushroom) (new Mushroom(){
			@Override
			public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity)
			{
				if(entity instanceof EntityLivingBase)
				{
					EntityLivingBase enlb=(EntityLivingBase)entity;
					// 5秒失明Ⅰ
					enlb.addPotionEffect(new PotionEffect(Potion.blindness.getId(),100,0,false));
				}
			}
		})
				.setHardness(0.0F)
				.setStepSound(Block.soundTypeGrass)
				.setLightLevel(0.125F);
	}

	public static class Mushroom extends BlockMushroom
	{
		public Mushroom()
		{
			super();
		}
	}

	public static class Herb extends BlockCrops
	{
		public final EnumPlantType type;
		protected HerbSeeds.Seed itemSeeds; // 种子物品
		public HerbSeeds.Seed seed()
		{
			return itemSeeds;
		}
		protected Item itemDrops; // 掉落的物品
		protected int maxDrops; // 最大掉落
		protected int minDrops; // 最小掉落
		protected int forturePromots; // 每级时运加成

		protected Item itemDropsSubs; // 额外掉落
		protected float rateDropsSubs; // 额外掉落几率

		protected int stages; // 有几个成长阶段
		protected int[] stageGrows; // 第几个阶段成长 这个数组长度比stages小1
		protected int growSpeeds; // 生长速度
		protected IIcon[] icons; // 材质

		public Herb(Item itemDrop,
		            int maxDrop, int minDrop, int forturePromot,

		            int stage, int[] stageGrow, int growSpeed)
		{
			this(null,null,0,0,0,
					1,new int[]{},0);
			itemSeeds=new HerbSeeds.Seed(this);
		}

		public Herb(
				HerbSeeds.Seed itemSeed, Item itemDrop,
		             int maxDrop, int minDrop, int forturePromot,

		             int stage, int[] stageGrow, int growSpeed)
		{
			this(itemSeed,itemDrop,maxDrop,minDrop,forturePromot,null,0,stage,stageGrow,growSpeed);
		}

		public Herb(
					HerbSeeds.Seed itemSeed, Item itemDrop,
					int maxDrop, int minDrop, int forturePromot,

					Item itemDropsSub, float rateDropsSub,

					int stage, int[] stageGrow, int growSpeed)
		{
			this(EnumPlantType.Plains,itemSeed,itemDrop,maxDrop,minDrop,forturePromot,itemDropsSub,rateDropsSub,stage,stageGrow,growSpeed);
		}

		public Herb(
				EnumPlantType plantType, Item itemDrop,
				int maxDrop, int minDrop, int forturePromot,

				Item itemDropsSub, float rateDropsSub,

				int stage, int[] stageGrow, int growSpeed)
		{
			this(plantType,null,itemDrop,maxDrop,minDrop,forturePromot,itemDropsSub,rateDropsSub,stage,stageGrow,growSpeed);
			itemSeeds=new HerbSeeds.Seed(this);
		}

		public Herb(
				EnumPlantType plantType,

				HerbSeeds.Seed itemSeed, Item itemDrop,
		        int maxDrop, int minDrop, int forturePromot,

				Item itemDropsSub, float rateDropsSub,

				int stage, int[] stageGrow, int growSpeed)
		{

			itemSeeds=itemSeed;
			// if(itemDrop==null) throw new RuntimeException("items dropped by HerbBlock cannot be null");
			itemDrops=itemDrop;
			minDrops=minDrop>0?minDrop:0;
			maxDrops=maxDrop>=minDrops?maxDrop:minDrops;
			forturePromots=forturePromot>0?forturePromot:0;

			rateDropsSubs=rateDropsSub>=0&&rateDropsSub<=1?rateDropsSub:0;
			itemDropsSubs=itemDropsSub;

			stages=stage>0?stage:4;
			stageGrows= stages-1==stageGrow.length?stageGrow:new int[]{3,6,9};

			growSpeeds=growSpeed>0&&growSpeed<16?growSpeed:1;

			icons=new IIcon[stages];

			type=plantType;
		}

		@Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
		{
//			if(player.worldObj.isRemote)
//				return false;
//			int meta=world.getBlockMetadata(x,y,z);
			//System.out.println("meta : "+meta+" max : "+this.stageGrows[stageGrows.length-1]);
			return false;
		}

		@Override
		public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
		{
//			System.out.println("onBlockHarvested 函数执行");
//			System.out.println("x y z : "+x+","+y+","+z+" ; meta : "+meta);
			this.dropBlockAsItemWithChance(world,x,y,z,meta,0,0);
		}
		@Override
		public void onBlockPreDestroy(World p_149725_1_, int p_149725_2_, int p_149725_3_, int p_149725_4_, int p_149725_5_)
		{
			// System.out.println("onBlockPreDestroy 函数执行");
		}

		@Override
		public boolean canPlaceBlockOn(Block block)
		{
			switch (type)
			{
				case Plains:
					return block==Blocks.dirt||block==Blocks.grass||block==Blocks.farmland;
				case Water:
					return block==Blocks.water;
				case Desert:
					return block==Blocks.sand;
				case Crop:
					return block==Blocks.farmland;
				case Cave:
					return block==Blocks.stone;
				case Beach:
					return block==Blocks.sand;
				case Nether:
					return block==Blocks.netherrack||block==Blocks.soul_sand;

				default:
					return false;
			}
		}

		@Override
		public void updateTick(World world, int x, int y, int z, Random rand)
		{
			super.updateTick(world, x, y, z, rand);
			if (world.getBlockLightValue(x, y + 1, z) >= 9)
			{
				int l = world.getBlockMetadata(x, y, z);

				if (l < this.stageGrows[stageGrows.length-1])
				{
					int metaNew=l+rand.nextInt(growSpeeds);
					world.setBlockMetadataWithNotify(x, y, z, metaNew, 2);
				}
			}
		}

		@Override
		public Item getItemDropped(int meta, Random rand, int p_149650_3_)
		{
			return meta >= this.stageGrows[stageGrows.length-1]
					? this.func_149865_P() : this.func_149866_i();
		}

		@Override
		public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float rate, int fortune)
		{
			if (!world.isRemote && !world.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
			{
				//getDrops(World world, int x, int y, int z, int metadata, int fortune)
				ArrayList<ItemStack> items = getDrops(world, x, y, z, meta, fortune);
				rate = ForgeEventFactory.fireBlockHarvesting(items, world, this, x, y, z, meta, fortune, rate, false, harvesters.get());

				for (ItemStack item : items)
				{
					if (world.rand.nextFloat() <= rate)
					{
						this.dropBlockAsItem(world, x, y, z, item);
					}
				}
			}
		}

		@Override
		public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
		{
			ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

			// System.out.println("getDrops 函数执行");

			// Item itemSeeds; // 种子物品
			// Item itemDrops; // 掉落的物品
			// int maxDrops; // 最大掉落
			// int minDrops; // 最小掉落
			// int forturePromots; // 每级时运加成
			//
			// Item itemDropsSubs; // 额外掉落
			// float rateDropsSubs; // 额外掉落几率
			if(itemSeeds!=null)
				ret.add(new ItemStack(this.itemSeeds,world.rand.nextInt(2)));

			if(itemDrops!=null && metadata >= this.stageGrows[stageGrows.length-1])
			{
				int dropnow=minDrops;
				if(maxDrops>minDrops)
					dropnow+=world.rand.nextInt(maxDrops-minDrops);
				dropnow+=forturePromots*fortune;
				ret.add(new ItemStack(this.itemDrops,dropnow));
			}

			if(itemDropsSubs!=null && metadata >= this.stageGrows[stageGrows.length-1]
					&& world.rand.nextFloat()<rateDropsSubs)
			{
				ret.add(new ItemStack(this.itemDropsSubs));
			}

			return ret;
		}

		// 本次施肥是否成功 随机的
		// shouldFertilize
		public boolean func_149852_a(World world, Random rand, int x, int y, int z)
		{
			return true;
		}
		// 感觉是用来检查目标有没有成熟 能否施肥的硬性要求
		// canFertilize
		@Override
		public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_)
		{
			return world.getBlockMetadata(x, y, z) < stageGrows[stageGrows.length-1];
		}

		// 生长的方法
		// fertilize
		public void func_149863_m(World world, int x, int y, int z)
		{
			int toGrow = world.getBlockMetadata(x, y, z) + world.rand.nextInt(growSpeeds+1);

			int maxGrow=stageGrows[stageGrows.length-1];
			if (toGrow > maxGrow)
			{
				toGrow = maxGrow;
			}

			// System.out.println("fertilize  toGrow : "+toGrow+"   maxGrow : "+maxGrow);

			world.setBlockMetadataWithNotify(x, y, z, toGrow, 2);
		}

		// 看起来是获得种子
		// getSeed
		@Override
		protected Item func_149866_i()
		{
			return itemSeeds;
		}

		// 看起来是获得产物
		// getCrop
		@Override
		protected Item func_149865_P()
		{
			return itemDrops;
		}

		// 掉多少东西
		@Override
		public int quantityDropped(Random p_149745_1_)
		{
			return 1;
		}

		/**
		 * Gets the block's texture. Args: side, meta
		 */
		@SideOnly(Side.CLIENT)
		public IIcon getIcon(int side, int meta) // todo 以后要改材质注册的问题
		{
			for(int i=0;i<stageGrows.length;i++)
			{
				if(meta>=stageGrows[i])
					return icons[i];
			}
			return icons[0];
		}
		@SideOnly(Side.CLIENT)
		public void registerBlockIcons(IIconRegister iconRegister)
		{
			for (int i = 0; i < this.icons.length; ++i)
			{
				this.icons[i]
						= iconRegister.registerIcon(
								this.getTextureName() + "_stage_" + i);
			}
		}
	}

}
