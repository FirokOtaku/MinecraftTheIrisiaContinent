package firok.irisia.world;

import cpw.mods.fml.common.IWorldGenerator;
import firok.irisia.Irisia;
import firok.irisia.block.HerbsAndMushroom;
import firok.irisia.block.OresAndMetal;
import firok.irisia.common.ConfigLoader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.*;
import thaumcraft.common.lib.world.biomes.BiomeHandler;
import thaumcraft.common.lib.world.dim.MazeThread;
import static firok.irisia.common.ConfigLoader.*;

import java.util.*;

public class IrisiaWorldGenerator implements IWorldGenerator
{
	/* 自己添加的内容 */
	public static BiomeGenBase biomeTown; // 城镇 没有怪物
	public static BiomeGenBase biomeElfForest; // 精灵森林
	public static BiomeGenBase biomeDwartVillage; // 矮人村落
	public static BiomeGenBase biomeCrystalForest; // 水晶森林
	/* 自己添加的内容 到这里为止 */

	public static BiomeGenBase biomeTaint;
	public static BiomeGenBase biomeEerie;
	public static BiomeGenBase biomeMagicalForest;
	public static BiomeGenBase biomeEldritchLands;

	public IrisiaWorldGenerator() {
	}

	public static void preInit()
	{
		// idBiomeCrystalForest
		if (BiomeGenBase.getBiomeGenArray()[ConfigLoader.idBiomeCrystalForest] != null) {
			ConfigLoader.idBiomeCrystalForest = ThaumcraftWorldGenerator.getFirstFreeBiomeSlot(ConfigLoader.idBiomeCrystalForest);
		}
		try
		{
			biomeCrystalForest=new BiomeGenCrystalForest(ConfigLoader.idBiomeCrystalForest);
		}catch (Exception e){Irisia.log("无法注册水晶森林生物群系");}
	}
	public static void init() {
		// 神秘的内容
//		BiomeGenTaint.blobs = new WorldGenBlockBlob(ConfigBlocks.blockTaint, 0);
//		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeMagicalForest, Config.biomeMagicalForestWeight));
//		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeMagicalForest, Config.biomeMagicalForestWeight));
//		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeTaint, Config.biomeTaintWeight));
//		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeTaint, Config.biomeTaintWeight));

		// fixme high 以后改成配置文件方式注册生物群系比重
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biomeCrystalForest,5));
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biomeCrystalForest,5));
	}

	public static void registerBiomes()
	{
		BiomeDictionary.registerBiomeType(biomeCrystalForest, new BiomeDictionary.Type[]
				{BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST});
	}

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		this.worldGeneration(random, chunkX, chunkZ, world, true);
		//Irisia.log(String.format("开始于%d,%d区块生成地形特征",chunkX,chunkZ));
	}

	public void worldGeneration(Random random, int chunkX, int chunkZ, World world, boolean newGen) {
		if(world.provider.dimensionId==0||world.provider.dimensionId==ConfigLoader.idDimIrisia)
		{
			//Irisia.log(String.format("%d,%d区块内部生成调用开始..",chunkX,chunkZ));
			this.generateIrisiaWorld(world, random, chunkX, chunkZ, newGen);
		}
		if (!newGen)
		{
			world.getChunkFromChunkCoords(chunkX, chunkZ).setChunkModified();
		}
	}
	private void generateIrisiaWorld(World world, Random random, int chunkX, int chunkZ, boolean newGen)
	{
//		this.generateVegetation(world, random, chunkX, chunkZ, newGen);
		this.generateOres(world, random, chunkX, chunkZ, newGen);
	}

	private void generateVegetation(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
		BiomeGenBase bgb = world.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);
		int randPosX = chunkX * 16 + random.nextInt(16);
		int randPosZ = chunkZ * 16 + random.nextInt(16);
		int randPosY = world.getHeightValue(randPosX, randPosZ);
		if (randPosY <= world.getActualHeight()) {
			if (world.getBiomeGenForCoords(randPosX, randPosZ).topBlock == Blocks.sand && world.getBiomeGenForCoords(randPosX, randPosZ).temperature > 1.0F && random.nextInt(30) == 0) {
				generateFlowers(world, random, randPosX, randPosY, randPosZ, 3);
			}

		}
	}

	private void generateOres(World world, Random random, int chunkX, int chunkZ, boolean newGen)
	{
		int amountReplaced=0;
		// fixme low 以后限定只能在对应生物群系生成矿物
		// BiomeGenBase bgb=world.getBiomeGenForCoords(chunkX,chunkZ);
		// 日耀水晶
		amountReplaced+=genOre(newGen,regenOGSolarCrystal,
				world,random,chunkX,chunkZ,
				30,60,
				OresAndMetal.OreSolarCrystal,0,amountOGSolarCrystal);
		// 月影水晶
		amountReplaced+=genOre(newGen,regenOGLunarCrystal,
				world,random,chunkX,chunkZ,
				30,60,
				OresAndMetal.OreLunarCrystal,0,amountOGLunarCrystal);
		// 秘银
		amountReplaced+=genOre(newGen,regenOGMithril,
				world,random,chunkX,chunkZ,
				5,50,
				OresAndMetal.OreMithril,0,amountOGMithril);
		// 精金
		amountReplaced+=genOre(newGen,regenOGAdamantium,
				world,random,chunkX,chunkZ,
				5,50,
				OresAndMetal.OreAdamantium,0,amountOGAdamantium);
		// 流铁
		amountReplaced+=genOre(newGen,regenOGFlumetal,
				world,random,chunkX,chunkZ,
				5,80,
				OresAndMetal.OreFlumetal,0,amountOGFlumetal);
		// 精灵石
		amountReplaced+=genOre(newGen,regenOGElfStone,
				world,random,chunkX,chunkZ,
				30,60,
				OresAndMetal.OreElfStone,0,amountOGElfStone);
		// 魔力矿
		amountReplaced+=genOre(newGen,regenOGMagical,
				world,random,chunkX,chunkZ,
				30,60,
				OresAndMetal.OreMagical,0,amountOGMagical);
		// Irisia.log(String.format("%d,%d区块共生成%d块irisia矿物",chunkX,chunkZ,amountReplaced));
//		if (Config.genAmber && (newGen || Config.regenAmber)) {
//			for(i = 0; i < 20; ++i) {
//				randPosX = chunkX * 16 + random.nextInt(16);
//				randPosZ = chunkZ * 16 + random.nextInt(16);
//				randPosY = world.getHeightValue(randPosX, randPosZ) - random.nextInt(25);
//				block2replace = world.getBlock(randPosX, randPosY, randPosZ);
//				if (block2replace != null && block2replace.isReplaceableOreGen(world, randPosX, randPosY, randPosZ, Blocks.stone)) {
//					world.setBlock(randPosX, randPosY, randPosZ, ConfigBlocks.blockCustomOre, 7, 2);
//				}
//			}
//		}

//		if (Config.genInfusedStone && (newGen || Config.regenInfusedStone)) {
//			for(i = 0; i < 8; ++i) {
//				randPosX = chunkX * 16 + random.nextInt(16);
//				randPosZ = chunkZ * 16 + random.nextInt(16);
//				randPosY = random.nextInt(Math.max(5, world.getHeightValue(randPosX, randPosZ) - 5));
//				int md = random.nextInt(6) + 1;
//				if (random.nextInt(3) == 0) {
//					Aspect tag = BiomeHandler.getRandomBiomeTag(world.getBiomeGenForCoords(randPosX, randPosZ).biomeID, random);
//					if (tag == null) {
//						md = 1 + random.nextInt(6);
//					} else if (tag == Aspect.AIR) {
//						md = 1;
//					} else if (tag == Aspect.FIRE) {
//						md = 2;
//					} else if (tag == Aspect.WATER) {
//						md = 3;
//					} else if (tag == Aspect.EARTH) {
//						md = 4;
//					} else if (tag == Aspect.ORDER) {
//						md = 5;
//					} else if (tag == Aspect.ENTROPY) {
//						md = 6;
//					}
//				}
//
//				try {
//					(new WorldGenMinable(ConfigBlocks.blockCustomOre, md, 6, Blocks.stone)).generate(world, random, randPosX, randPosY, randPosZ);
//				} catch (Exception var13) {
//					var13.printStackTrace();
//				}
//			}
//		}
	}

	private static int genOre(boolean isNewGen,boolean canNewGen,World world,Random rand,int chunkX,int chunkZ,int minY,int maxY,Block ore,int meta,int amount2gen)
	{
		if(isNewGen||canNewGen)
			return genOre(world,rand,chunkX,chunkZ,minY,maxY,ore,meta,amount2gen);
		return 0;
	}
	// 生成矿物
	private static int genOre(World world,Random rand,int chunkX,int chunkZ,int minY,int maxY,Block ore,int meta,int amount2gen)
	{
		if(amount2gen<=0||minY>=maxY) return 0;
		int amount=0;
		for(int i = 0; i < amount2gen; ++i) {
			int randPosX = chunkX * 16 + rand.nextInt(16);
			int randPosY = minY + rand.nextInt(maxY);
			int randPosZ = chunkZ * 16 + rand.nextInt(16);
			Block block2replace = world.getBlock(randPosX, randPosY, randPosZ);
			if (block2replace != null && block2replace!=Blocks.air && block2replace.isReplaceableOreGen(world, randPosX, randPosY, randPosZ, Blocks.stone)) {
				world.setBlock(randPosX, randPosY, randPosZ, ore, meta, 0);
				amount++;
			}
		}
		return amount;
	}


	public static boolean generateFlowers(World world, Random random, int x, int y, int z, int flower) {
		WorldGenerator flowers = new WorldGenCustomFlowers(ConfigBlocks.blockCustomPlant, flower);
		return flowers.generate(world, random, x, y, z);
	}

}
