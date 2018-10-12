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
		material = new CreativeTabs("irisia_material"){
			@Override
			public Item getTabIconItem()
			{
				return Items.bone;
			}
		};
		block = new CreativeTabs("irisia_block"){
			@Override
			public Item getTabIconItem()
			{
				return ItemBlock.getItemFromBlock(Blocks.stone);
			}
		};
		food = new CreativeTabs("irisia_food"){
			@Override
			public Item getTabIconItem()
			{
				return Items.apple;
			}
		};
		berry =new CreativeTabs("irisia_berry")
		{
			@Override
			public Item getTabIconItem()
			{
				return Items.bread;
			}
		};
		equi = new CreativeTabs("irisia_equi"){
			@Override
			public Item getTabIconItem()
			{
				return Items.iron_chestplate;
			}
		};
		tc = new CreativeTabs("irisia_tc"){
			@Override
			public Item getTabIconItem()
			{
				return Items.book;
			}
		};

	}
}
