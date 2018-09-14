package firok.irisia.block;

import javax.annotation.Nullable;

import firok.irisia.Irisia;
import firok.irisia.common.IrisiaCreativeTabs;
import firok.irisia.item.DecorationItems;
import firok.irisia.item.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import sun.font.Decoration;

import java.util.ArrayList;

public class BlockLoader
{
	public static ArrayList<Block> blocks=new ArrayList<>();
	private static void registerDecorations()
	{
		register(IrisiaCreativeTabs.block,DecorationBlocks.Stones,DecorationItems.StoneClass.class,"decor_stones");

		register(IrisiaCreativeTabs.block,DecorationBlocks.Bricks,DecorationItems.BrickClass.class,"decor_bricks");
	}
	private static void registerOres()
	{
		register(IrisiaCreativeTabs.block,OresAndMetal.OreMithril,"oreMithril","ore_mithril");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreAdamantium,"oreAdamantium","ore_adamantium");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreFlumetal,"oreFlumetal","ore_flumetal");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreElfStone,"oreElfStone","ore_elfstone");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreDwartCoal,"oreDwartCoal","ore_dwartcoal");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreHotStone,"oreHotStone","ore_hotstone");


		register(IrisiaCreativeTabs.block,OresAndMetal.BlockMithril,"blockMithril","block_mithril");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockAdamantium,"blockAdamantium","block_damantium");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockFlumetal,"blockFlumetal","block_flumetal");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockSolita,"blockSolita","block_solita");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockMogiga,"blockMogiga","block_mogiga");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockDwartSteel,"blockDwartSteel","block_dwartsteel");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockElfStone,"blockElfStone","block_elfstone");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockDwartCoal,"blockDwartCoal","block_dwartcoal");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockHotStone,"blockHotStone","block_hotstone");
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
		register(IrisiaCreativeTabs.block, ArcaneStelas.StelaSpeed,"blockStelaSpeed","block_stela_speed");
		register(IrisiaCreativeTabs.block, ArcaneStelas.StelaJump,"blockStelaJump","block_stela_jump");
		register(IrisiaCreativeTabs.block, ArcaneStelas.StelaResistance,"blockStelaResistance","block_stela_resistance");
		register(IrisiaCreativeTabs.block, ArcaneStelas.StelaDigSpeed,"blockStelaDigSpeed","block_stela_digspeed");
	}
	private static void registerPavingStones()
	{
		register(IrisiaCreativeTabs.block,PavingStones.Shake,"blockPavingStoneShake","block_paving_stone_shake");
		register(IrisiaCreativeTabs.block,PavingStones.Flame,"blockPavingStoneFlame","block_paving_stone_flame");
	}
	private static void registerMatchines()
	{
		register(IrisiaCreativeTabs.block,MachineBlocks.BerryMixer,"machineBerryMixer","machine_berry_mixer");
	}

    public BlockLoader(FMLPreInitializationEvent event)
    {
	    registerDecorations();

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
    { // info 这个是最主要的注册方法
//        GameRegistry.registerBlock(block.setRegistryName(name));
//        block.setUnlocalizedName(unlocalizedName);
//        if(tab!=null)
//        	block.setCreativeTab(tab);
	    if(block==null)
		    return;

        if(tab!=null) block.setCreativeTab(tab);

        block.
            setBlockName(unlocalizedName).
            setBlockTextureName(Irisia.MODID+":"+textureName);

        GameRegistry.registerBlock(block,textureName);

	    blocks.add(block);
    }
    private static void register(@Nullable CreativeTabs tab,Block block,Class<? extends ItemBlock> itemBlockClass,String tn)
    { // note 现在不知道这个方法能不能用 以后再测试一下
    	if(tab!=null)
    		block.setCreativeTab(tab);

    	GameRegistry.registerBlock(block,itemBlockClass,Irisia.MODID+':'+tn);
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