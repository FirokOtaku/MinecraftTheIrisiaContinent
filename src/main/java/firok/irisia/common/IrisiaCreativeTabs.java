package firok.irisia.common;

import firok.irisia.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemBlock;

public class IrisiaCreativeTabs
{
	public static CreativeTabs irisia;
	public static CreativeTabs irisiaEqui;
	public static CreativeTabs irisiaMaterial;
	public static CreativeTabs irisiaBlock;
	public static CreativeTabs irisiaFood;
	public static CreativeTabs irisiaTC;

	public static void load(FMLPreInitializationEvent event)
	{
		irisia = new CreativeTabs("irisia"){
			@Override
			public Item getTabIconItem()
			{
				return Items.diamond;
			}
		};
		irisiaMaterial = new CreativeTabs("irisiaMaterial"){
			@Override
			public Item getTabIconItem()
			{
				return Items.bone;
			}
		};
		irisiaBlock = new CreativeTabs("irisiaBlock"){
			@Override
			public Item getTabIconItem()
			{
				return ItemBlock.getItemFromBlock(Blocks.stone);
			}
		};
		irisiaFood = new CreativeTabs("irisiaFood"){
			@Override
			public Item getTabIconItem()
			{
				return Items.apple;
			}
		};
		irisiaEqui = new CreativeTabs("irisiaEqui"){
			@Override
			public Item getTabIconItem()
			{
				return Items.iron_chestplate;
			}
		};
		irisiaTC = new CreativeTabs("irisiaTC"){
			@Override
			public Item getTabIconItem()
			{
				return Items.book;
			}
		};
	}
}
