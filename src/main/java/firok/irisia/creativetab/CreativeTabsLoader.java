package firok.irisia.creativetab;

import firok.irisia.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader {
	public static CreativeTabs irisia;
	public static CreativeTabs irisiaEqui;
	public static CreativeTabs irisiaTool;
	public static CreativeTabs irisiaMaterial;
	public static CreativeTabs irisiaBlock;
	public static CreativeTabs irisiaTC;

    public CreativeTabsLoader(FMLPreInitializationEvent event)
    {
    	irisia = new Irisia();
    	irisiaMaterial=new IrisiaMaterial();
    	irisiaBlock=new IrisiaBlock();
    	irisiaTool=new IrisiaTool();
    	irisiaEqui=new IrisiaEqui();
    	irisiaTC=new IrisiaTc();
    }
    
    public static class Irisia extends CreativeTabs {
    	public Irisia()
    	{
    		super("irisia");
    	}

        @Override
        public Item getTabIconItem()
        {
        	return Items.stone_axe;
        }
    }
    public static class IrisiaTool extends CreativeTabs {
    	public IrisiaTool()
    	{
    		super("irisia.tool");
    	}

        @Override
        public Item getTabIconItem()
        {
        	return Items.stone_axe;
        }
    }
    public static class IrisiaMaterial extends CreativeTabs
    {
    	public IrisiaMaterial()
    	{
    		super("irisia.material");
    	}

        @Override
        public Item getTabIconItem()
        {
        	return Items.stone_axe;
        }
    }
    public static class IrisiaEqui extends CreativeTabs
    {
    	public IrisiaEqui()
    	{
    		super("irisia.equi");
    	}

        @Override
        public Item getTabIconItem()
        {
        	return Items.stone_axe;
        }
    }
//    public static class IrisiaTool extends CreativeTabs
//    {
//    	public IrisiaTool()
//    	{
//    		super("irisia.tool");
//    	}
//
//        @Override
//        public Item getTabIconItem()
//        {
//        	return ItemLoader.pickaxeDwartSteel;
//        }
//    }
    public static class IrisiaBlock extends CreativeTabs
    {
    	public IrisiaBlock()
    	{
    		super("irisia.block");
    	}

        @Override
        public Item getTabIconItem()
        {
	        return Items.stone_axe;
        }
    }
    public static class IrisiaTc extends CreativeTabs
    {
    	public IrisiaTc()
    	{
    		super("irisia.tc");
    	}

        @Override
        public Item getTabIconItem()
        {
        	return Items.stone_axe;
        }
    }
    
}
