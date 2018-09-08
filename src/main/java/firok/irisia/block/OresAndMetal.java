package firok.irisia.block;

import firok.irisia.item.RawMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class OresAndMetal
{
	public final static BlockOre OreMithril;
	public final static BlockOre OreAdamantium;
	public final static BlockOre OreFlumetal;
	public final static BlockOre OreElfStone;
	public final static BlockOre OreDwartCoal;
	public final static BlockOre OreHotStone;
	public final static BlockCompressed BlockMithril;
	public final static BlockCompressed BlockAdamantium;
	public final static BlockCompressed BlockFlumetal;
	public final static BlockCompressed BlockDwartSteel;
	public final static BlockCompressed BlockElfStone;
	public final static BlockCompressed BlockDwartCoal;
	public final static BlockCompressed BlockHotStone;
	public final static BlockCompressed BlockSolita;
	public final static BlockCompressed BlockMogiga;

	static
	{
		OreMithril=(Ore)new Ore(3);

		OreAdamantium=(Ore)new Ore(3);

		OreFlumetal=(Ore)new Ore(3);

		OreElfStone=(Ore)new Ore(2);

		OreDwartCoal=(Ore)new Ore(1);

		OreHotStone=(Ore)new Ore(1);



		BlockMithril=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockAdamantium=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockFlumetal=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockDwartSteel=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockElfStone=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockDwartCoal=(BlockCompressed)new BlockCompressed(MapColor.brownColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockHotStone=(BlockCompressed)new BlockCompressed(MapColor.redColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypePiston);

		BlockSolita=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);

		BlockMogiga=(BlockCompressed)new BlockCompressed(MapColor.silverColor).
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypeMetal);
	}

	public static class Ore extends BlockOre
	{
		public Ore()
		{
			super();
			setHardness(5.0F);
			setResistance(10.0F);
			setStepSound(Block.soundTypePiston);
		}
		public Ore(int harvestLevel)
		{
			this();
			setHarvestLevel(null,harvestLevel);
		}

		@Override
		public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
		{
			if(this==OreElfStone) return RawMaterials.ElfStone;
			if(this==OreDwartCoal) return RawMaterials.DwartCoal;
			if(this==OreHotStone) return RawMaterials.HotStone;

			return Item.getItemFromBlock(this);
		}

		/**
		 * Returns the quantity of items to drop on block destruction.
		 */
		@Override
		public int quantityDropped(Random rand)
		{
			return this == OreElfStone ? 1 + rand.nextInt(3) :
					this == OreDwartCoal ? 1+rand.nextInt(3) :
					this == OreHotStone?2+rand.nextInt(3) :
							1;
		}

		private Random rand = new Random();
		@Override
		public int getExpDrop(IBlockAccess world, int p_149690_5_, int p_149690_7_)
		{
			if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this))
			{
				int exp = 0;

				if (this == OreElfStone)
				{
					exp = MathHelper.getRandomIntegerInRange(rand, 2, 5);
				}
				else if(this==OreDwartCoal)
				{
					exp = MathHelper.getRandomIntegerInRange(rand, 2, 3);
				}
				else if(this==OreHotStone)
				{
					exp = MathHelper.getRandomIntegerInRange(rand, 2, 5);
				}
				else if (this == Blocks.quartz_ore)
				{
					exp = MathHelper.getRandomIntegerInRange(rand, 2, 5);
				}

				return exp;
			}
			return 0;
		}

		/**
		 * Determines the damage on the item the block drops. Used in cloth and wood.
		 */
		@Override
		public int damageDropped(int meta)
		{
			return 0;
		}
	}
}
