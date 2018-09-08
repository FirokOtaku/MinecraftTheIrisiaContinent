package firok.irisia.block;

import javax.annotation.Nullable;

import firok.irisia.common.IrisiaCreativeTabs;
import firok.irisia.item.HerbSeeds;
import firok.irisia.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLoader
{
	private static void registerOres()
	{
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreMithril,"oreMithril","ore_mithril");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreAdamantium,"oreAdamantium","ore_adamantium");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreFlumetal,"oreFlumetal","ore_flumetal");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreElfStone,"oreElfStone","ore_elfstone");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreDwartCoal,"oreDwartCoal","ore_dwartcoal");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.OreHotStone,"oreHotStone","ore_hotstone");


		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockMithril,"blockMithril","block_mithril");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockAdamantium,"blockAdamantium","block_damantium");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockFlumetal,"blockFlumetal","block_flumetal");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockSolita,"blockSolita","block_solita");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockMogiga,"blockMogiga","block_mogiga");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockDwartSteel,"blockDwartSteel","block_dwartsteel");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockElfStone,"blockElfStone","block_elfstone");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockDwartCoal,"blockDwartCoal","block_dwartcoal");
		register(IrisiaCreativeTabs.irisiaBlock,OresAndMetal.BlockHotStone,"blockHotStone","block_hotstone");


	}
	private static void registerHerbs()
	{
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.DeathGrass,"DeathGrass","death_grass");
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.MoonGrass,"MoonGrass","moon_grass");
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.SpicyRoot,"SpicyRoot","spicy_root");
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.ShadowMushroom,"blockShadowMushroom","block_shadow_mushroom");

		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.TestWater,"TestWater","test_water");

	}
	private static void registerArcaneStelas()
	{
		register(IrisiaCreativeTabs.irisiaBlock, ArcaneStelas.StelaSpeed,"blockStelaSpeed","block_stela_speed");
		register(IrisiaCreativeTabs.irisiaBlock, ArcaneStelas.StelaJump,"blockStelaJump","block_stela_jump");
		register(IrisiaCreativeTabs.irisiaBlock, ArcaneStelas.StelaResistance,"blockResistance","block_stela_resistance");
		register(IrisiaCreativeTabs.irisiaBlock, ArcaneStelas.StelaDigSpeed,"blockDigSpeed","block_stela_digspeed");
	}
	private static void registerPavingStones()
	{
		register(IrisiaCreativeTabs.irisiaBlock,PavingStones.Shake,"blockPavingStoneShake","block_paving_stone_shake");
		register(IrisiaCreativeTabs.irisiaBlock,PavingStones.Flame,"blockPavingStoneFlame","block_paving_stone_flame");
	}
	private static void registerMatchines()
	{
		register(IrisiaCreativeTabs.irisiaBlock,MachineBlocks.BerryMixer,"machineBerryMixer","machine_berry_mixer");
	}

    public BlockLoader(FMLPreInitializationEvent event)
    {
        registerOres();

	    registerHerbs();

	    registerArcaneStelas();

	    registerPavingStones();

	    registerMatchines();
    }

    private static void register(@Nullable CreativeTabs tab, HerbsAndMushroom.Herb herb, String unlocalizedName, String textureName)
    {
	    if(herb==null)
		    return;

	    if(tab!=null) herb.setCreativeTab(tab);

	    register(tab,(Block)herb,unlocalizedName+"Herb","herb_"+textureName);
	    ItemLoader.register(tab,herb.seed(),"seed_"+textureName,unlocalizedName+"Seed");
    }
    private static void register(@Nullable CreativeTabs tab,Block block, String unlocalizedName,String textureName)
    {
//        GameRegistry.registerBlock(block.setRegistryName(name));
//        block.setUnlocalizedName(unlocalizedName);
//        if(tab!=null)
//        	block.setCreativeTab(tab);
	    if(block==null)
		    return;

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

	private static void registerMyBlock(Block block, Class<? extends ItemBlock> pickup, BlockSlab singleSlab, BlockSlab doubleSlab, boolean isDouble) {
		GameRegistry.registerBlock(block, pickup, block.getUnlocalizedName(), singleSlab, doubleSlab, isDouble);
	}

	private static void registerMyBlock(Block block, Class<? extends ItemBlock> pickup, Block blockAgain, String[] names) {
		GameRegistry.registerBlock(block, pickup, block.getUnlocalizedName(), blockAgain, names);
	}

	private static void registerMyBlock(Block block, Class<? extends ItemBlock> pickup)
	{
		GameRegistry.registerBlock(block, pickup, block.getUnlocalizedName());
	}
}