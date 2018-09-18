package firok.irisia.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemBlock;

public class IrisiaCreativeTabs
{
	public static CreativeTabs irisia;
	public static CreativeTabs equi;
	public static CreativeTabs material;
	public static CreativeTabs block;
	public static CreativeTabs food;
	public static CreativeTabs berry;
	public static CreativeTabs tc;

	public static void load(FMLPreInitializationEvent event)
	{
		irisia = new CreativeTabs("irisia"){
			@Override
			public Item getTabIconItem()
			{
				return Items.diamond;
			}
		};
		material = new CreativeTabs("material"){
			@Override
			public Item getTabIconItem()
			{
				return Items.bone;
			}
		};
		block = new CreativeTabs("block"){
			@Override
			public Item getTabIconItem()
			{
				return ItemBlock.getItemFromBlock(Blocks.stone);
			}
		};
		food = new CreativeTabs("food"){
			@Override
			public Item getTabIconItem()
			{
				return Items.apple;
			}
		};
		berry =new CreativeTabs("berry")
		{
			@Override
			public Item getTabIconItem()
			{
				return Items.bread;
			}
		};
		equi = new CreativeTabs("equi"){
			@Override
			public Item getTabIconItem()
			{
				return Items.iron_chestplate;
			}
		};
		tc = new CreativeTabs("tc"){
			@Override
			public Item getTabIconItem()
			{
				return Items.book;
			}
		};

	}
}
