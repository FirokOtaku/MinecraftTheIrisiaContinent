package firok.irisia.block;

import firok.irisia.creativetab.CreativeTabsLoader;
import firok.irisia.item.RawMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class OresAndMetal
{
	public final static BlockOre OreMithril;
	public final static BlockOre OreAdamantium;
	public final static BlockOre OreFlumetal;
	public final static BlockOre OreElfStone;
	public final static BlockCompressed BlockMithril;
	public final static BlockCompressed BlockAdamantium;
	public final static BlockCompressed BlockFlumetal;
	public final static BlockCompressed BlockDwartSteel;
	public final static BlockCompressed BlockElfStone;
	public final static BlockCompressed BlockSolita;
	public final static BlockCompressed BlockMogiga;

	static
	{
		OreMithril=(Ore)new Ore().
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypePiston);
		OreMithril.setHarvestLevel(null,3);

		OreAdamantium=(Ore)new Ore().
			setHardness(5.0F).
			setResistance(10.0F).
			setStepSound(Block.soundTypePiston);
		OreAdamantium.setHarvestLevel(null,3);

		OreFlumetal=(Ore)new Ore().
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypePiston);
		OreFlumetal.setHarvestLevel(null,3);

		OreElfStone=(Ore)new Ore().
				setHardness(5.0F).
				setResistance(10.0F).
				setStepSound(Block.soundTypePiston);
		OreElfStone.setHarvestLevel(null,3);



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
		}

		@Override
		public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
		{
			if(this==OreElfStone) return RawMaterials.ElfStone;

			return Item.getItemFromBlock(this);
		}

		/**
		 * Returns the quantity of items to drop on block destruction.
		 */
		@Override
		public int quantityDropped(Random p_149745_1_)
		{
			return this == OreElfStone ? 2 + p_149745_1_.nextInt(2) : 1;
		}

		private Random rand = new Random();
		@Override
		public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
		{
			if (this.getItemDropped(p_149690_5_, rand, p_149690_7_) != Item.getItemFromBlock(this))
			{
				int j1 = 0;

				if (this == OreElfStone)
				{
					j1 = MathHelper.getRandomIntegerInRange(rand, 2, 5);
				}
				else if (this == Blocks.quartz_ore)
				{
					j1 = MathHelper.getRandomIntegerInRange(rand, 2, 5);
				}

				return j1;
			}
			return 0;
		}

		/**
		 * Determines the damage on the item the block drops. Used in cloth and wood.
		 */
		@Override
		public int damageDropped(int p_149692_1_)
		{
			return 0;
		}
	}
}
