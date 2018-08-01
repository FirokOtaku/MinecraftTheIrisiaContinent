package firok.irisia.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLoader
{
    // public static Block grassBlock = new BlockGrassBlock();
	// 功能性方块
	
	// 蘑菇
	// public static Block mushroomEnderMushroom=new MushroomEnderMushroom();



    public BlockLoader(FMLPreInitializationEvent event)
    {
        // register(grassBlock, "grass_block", unlocalizedName);
    	//register(CreativeTabsLoader.irisiaBlock,mushroomEnderMushroom,"mushroom_ender_mushroom","mushroomEnderMushroom");
	    register(CreativeTabs.tabBlock,OresAndMetal.OreMithril,"oreMithril","ore_mithril");
	    register(CreativeTabs.tabBlock,OresAndMetal.OreAdamantium,"oreAdamantium","ore_adamantium");
	    register(CreativeTabs.tabBlock,OresAndMetal.OreFlumetal,"oreFlumetal","ore_flumetal");

	    register(CreativeTabs.tabBlock,OresAndMetal.BlockMithril,"blockMithril","block_mithril");
	    register(CreativeTabs.tabBlock,OresAndMetal.BlockAdamantium,"blockAdamantium","block_damantium");
	    register(CreativeTabs.tabBlock,OresAndMetal.BlockFlumetal,"blockFlumetal","block_flumetal");
	    register(CreativeTabs.tabBlock,OresAndMetal.BlockSolita,"blockSolita","block_solita");
    }

    private static void register(@Nullable CreativeTabs tab,Block block, String unlocalizedName,String textureName)
    {
//        GameRegistry.registerBlock(block.setRegistryName(name));
//        block.setUnlocalizedName(unlocalizedName);
//        if(tab!=null)
//        	block.setCreativeTab(tab);
        if(tab!=null) block.setCreativeTab(tab);

        block.
            setBlockName(unlocalizedName).
            setBlockTextureName(textureName);

        GameRegistry.registerBlock(block,textureName);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
//        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
//        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
    }
    
    private static void register(Block block, ItemBlock itemBlock, String name)
    {
//        GameRegistry.registerBlock(block.setRegistryName(name), (Class<? extends ItemBlock>) null);
//        GameRegistry.registerItem(itemBlock.setRegistryName(name));
//        GameData.getBlockItemMap().put(block, itemBlock);
    }
}