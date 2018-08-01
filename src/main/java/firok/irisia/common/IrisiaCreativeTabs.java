package firok.irisia.common;

import firok.irisia.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class IrisiaCreativeTabs
{
	public static CreativeTabs irisia;
	public static CreativeTabs irisiaEqui;
	public static CreativeTabs irisiaTool;
	public static CreativeTabs irisiaMaterial;
	public static CreativeTabs irisiaBlock;
	public static CreativeTabs irisiaTC;

	public static void load(FMLPreInitializationEvent event)
	{
		irisia = new CreativeTabs("irisia"){
			@Override
			public Item getTabIconItem()
			{
				return Items.stone_axe;
			}
		};
		irisiaMaterial = new CreativeTabs("irisiaMaterial"){
			@Override
			public Item getTabIconItem()
			{
				return Items.stone_axe;
			}
		};
		irisiaBlock = new CreativeTabs("irisiaBlock"){
			@Override
			public Item getTabIconItem()
			{
				return Items.stone_axe;
			}
		};
		irisiaTool = new CreativeTabs("irisiaTool"){
			@Override
			public Item getTabIconItem()
			{
				return Items.stone_axe;
			}
		};
		irisiaEqui = new CreativeTabs("irisiaEqui"){
			@Override
			public Item getTabIconItem()
			{
				return Items.stone_axe;
			}
		};
		irisiaTC = new CreativeTabs("irisiaTC"){
			@Override
			public Item getTabIconItem()
			{
				return Items.stone_axe;
			}
		};
	}
}
