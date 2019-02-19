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

import java.util.ArrayList;

public class BlockLoader
{
	public static ArrayList<Block> blocks=new ArrayList<>();
	private static void registerDecorations()
	{
		register(IrisiaCreativeTabs.block,DecorationBlocks.Stones,DecorationItems.StoneClass.class,"decor_stones");

		register(IrisiaCreativeTabs.block,DecorationBlocks.Bricks,DecorationItems.BrickClass.class,"decor_bricks");
		register(IrisiaCreativeTabs.block,DecorationBlocks.DeathDirt,"DeathDirt","death_dirt");

		register(IrisiaCreativeTabs.block,DecorationBlocks.MagicalDirt,"MagicalDirt","magical_dirt");

		register(IrisiaCreativeTabs.block,VisNode.BlockVisNode,"VisNode","vis_node");

		register(IrisiaCreativeTabs.block,SpecialDecorations.AncientDoor,"AncientDoor","ancient_door");
		register(IrisiaCreativeTabs.block,SpecialDecorations.AncientDoorKeyhole,"AncientDoorKeyHole","ancient_door_keyhole");

		register(IrisiaCreativeTabs.block,SpecialDecorations.PoisonousCobweb,"PoisonousCobweb","decor_poisonous_cobweb");
		register(IrisiaCreativeTabs.block,SpecialDecorations.DirtyCobweb,"DirtyCobweb","decor_dirty_cobweb");
		register(IrisiaCreativeTabs.block,SpecialDecorations.LavaCobweb,"LavaCobweb","decor_lava_cobweb");

		register(IrisiaCreativeTabs.block,SpecialDecorations.ArcaneShield,"ArcaneShield","decor_arcane_shield");
		register(IrisiaCreativeTabs.block,SpecialDecorations.MagicLight,"MagicLight","decor_magic_light");
		register(IrisiaCreativeTabs.block,SpecialDecorations.AirWall,"AirWall","decor_air_wall");

	}
	private static void registerOres()
	{
		register(IrisiaCreativeTabs.block,OresAndMetal.OreMithril,"oreMithril","ore_mithril");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreAdamantium,"oreAdamantium","ore_adamantium");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreFlumetal,"oreFlumetal","ore_flumetal");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreElfStone,"oreElfStone","ore_elfstone");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreDwartCoal,"oreDwartCoal","ore_dwartcoal");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreHotStone,"oreHotStone","ore_hotstone");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreSolarCrystal,"oreSolarCrystal","ore_solar_crystal");
		register(IrisiaCreativeTabs.block,OresAndMetal.OreLunarCrystal,"oreLunarCrystal","ore_lunar_crystal");

		register(IrisiaCreativeTabs.block,OresAndMetal.BlockMithril,"blockMithril","block_mithril");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockAdamantium,"blockAdamantium","block_damantium");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockFlumetal,"blockFlumetal","block_flumetal");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockSolita,"blockSolita","block_solita");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockMogiga,"blockMogiga","block_mogiga");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockDwartSteel,"blockDwartSteel","block_dwartsteel");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockElfStone,"blockElfStone","block_elfstone");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockDwartCoal,"blockDwartCoal","block_dwartcoal");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockHotStone,"blockHotStone","block_hotstone");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockSolarCrystal,"blockSolarCrystal","block_solar_crystal");
		register(IrisiaCreativeTabs.block,OresAndMetal.BlockLunarCrystal,"blockLunarCrystal","block_lunar_crystal");

	}
	private static void registerHerbs()
	{
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.DeathGrassHerb,"DeathGrassHerb","death_grass");
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.MoonGrassHerb,"MoonGrassHerb","moon_grass");
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.SpicyRootHerb,"SpicyRootHerb","spicy_root");
		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.ShadowMushroom,"blockShadowMushroom","block_shadow_mushroom");

		register(IrisiaCreativeTabs.irisia, HerbsAndMushroom.TestWater,"TestWater","test_water");

		register(IrisiaCreativeTabs.block,HerbsAndMushroom.InkrGrassHerb,"InkrGrassHerb","inkr_grass");
		register(IrisiaCreativeTabs.block,HerbsAndMushroom.AppleGrassHerb,"AppleGrassHerb","apple_grass");
		register(IrisiaCreativeTabs.block,HerbsAndMushroom.MonaHerb,"MonaHerb","mona_grass");
		register(IrisiaCreativeTabs.block,HerbsAndMushroom.BrownWheat,"BrownWheat","brown_wheat");
		register(IrisiaCreativeTabs.block,HerbsAndMushroom.StarousHerb,"StarousHerb","starous_herb");
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

		register(IrisiaCreativeTabs.block,PavingStones.BluringFlame,"blockBluringPavingStoneFlame","block_bluring_paving_stone_flame");
		register(IrisiaCreativeTabs.block,PavingStones.BluringShake,"blockBluringPavingStoneShake","block_bluring_paving_stone_shake");
		register(IrisiaCreativeTabs.block,PavingStones.BluringTraveller,"blockBluringPavingStoneTraveller","block_bluring_paving_stone_traveller");
	}
	private static void registerMachines()
	{
		register(IrisiaCreativeTabs.block,MachineBlocks.StormCollector,"machineStormCollector","machine_storm_collector");
		register(IrisiaCreativeTabs.block,MachineBlocks.BerryMixer,"machineBerryMixer","machine_berry_mixer");
		register(IrisiaCreativeTabs.block,MachineBlocks.LockedChest,"machineLockedChest","machine_locked_chest");

		register(IrisiaCreativeTabs.block,EnderElevator.ElevatorController,"machine_ender_elevator_controller","MachineEnderElevatorController");
		register(IrisiaCreativeTabs.block,EnderElevator.ElevatorPlatform,"machine_ender_elevator_platform","MachineEnderElevatorPlatform");

	}

    public BlockLoader(FMLPreInitializationEvent event)
    {
	    registerDecorations();

        registerOres();

	    registerHerbs();

	    registerArcaneStelas();

	    registerPavingStones();

	    registerMachines();
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