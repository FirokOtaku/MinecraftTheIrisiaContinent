package firok.irisia.block;

import javax.annotation.Nullable;

import firok.irisia.common.IrisiaCreativeTabs;
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
    	//register(IrisiaCreativeTabs.irisiaBlock,mushroomEnderMushroom,"mushroom_ender_mushroom","mushroomEnderMushroom");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreMithril,"oreMithril","ore_mithril");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreAdamantium,"oreAdamantium","ore_adamantium");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreFlumetal,"oreFlumetal","ore_flumetal");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreElfStone,"oreElfStone","ore_elfstone");

	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockMithril,"blockMithril","block_mithril");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockAdamantium,"blockAdamantium","block_damantium");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockFlumetal,"blockFlumetal","block_flumetal");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockSolita,"blockSolita","block_solita");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockMogiga,"blockMogiga","block_mogiga");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockDwartSteel,"blockDwartSteel","block_dwartsteel");
	    register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockElfStone,"blockElfStone","block_elfstone");


	    register(IrisiaCreativeTabs.irisiaBlock, HerbsAndMushroom.DeathGrass,"blockDeathGrass","block_death_grass");
	    register(IrisiaCreativeTabs.irisiaBlock, HerbsAndMushroom.MoonGrass,"blockMoonGrass","block_moon_grass");
	    register(IrisiaCreativeTabs.irisiaBlock, HerbsAndMushroom.SpicyRoot,"blockSpicyRoot","block_spicy_root");
	    register(IrisiaCreativeTabs.irisiaBlock, HerbsAndMushroom.ShadowMushroom,"blockShadowMushroom","block_shadow_mushroom");

	    register(IrisiaCreativeTabs.irisiaBlock, ArcaneStelas.a1,"a1","a1");
	    register(IrisiaCreativeTabs.irisiaBlock, ArcaneStelas.a2,"a2","a2");

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