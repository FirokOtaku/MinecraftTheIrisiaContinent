package firok.irisia.item;

import firok.irisia.Irisia;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class Materials
{
	/*CLOTH(5, new int[]{1, 3, 2, 1}, 15),
        CHAIN(15, new int[]{2, 5, 4, 1}, 12),
        IRON(15, new int[]{2, 6, 5, 2}, 9),
        GOLD(7, new int[]{2, 5, 3, 1}, 25),
        DIAMOND(33, new int[]{3, 8, 6, 3}, 10);*/
	public static final ItemArmor.ArmorMaterial BoneArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":BONE",
					6, new int[]{ 1, 3, 2, 1 }, 12);
	static{BoneArmor.customCraftingMaterial=Items.bone;}

	public static final ItemArmor.ArmorMaterial SpectreArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":SPECTRE",
					8, new int[]{ 1, 3, 2, 1 }, 14);
	static{SpectreArmor.customCraftingMaterial=Items.ghast_tear;}

	public static final ItemArmor.ArmorMaterial MithrilArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":MITHRIL",
					30, new int[]{ 4, 8, 7, 4 }, 10);
	static{MithrilArmor.customCraftingMaterial= RawMaterials.MithrilIngot;}

	public static final ItemArmor.ArmorMaterial AdamantiumArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":ADAMANTIUM",
					8, new int[]{ 2, 5, 4, 1 }, 35);
	static{AdamantiumArmor.customCraftingMaterial=RawMaterials.AdamantiumIngot;}

	public static final ItemArmor.ArmorMaterial FlumetalArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":FLUMETAL",
					13, new int[]{ 2, 5, 4, 2 }, 10);
	static{FlumetalArmor.customCraftingMaterial=RawMaterials.FlumetalIngot;}

	public static final ItemArmor.ArmorMaterial DwartSteelArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":DWARTSTELL",
					18, new int[]{ 3, 6, 6, 3 }, 8);
	static{DwartSteelArmor.customCraftingMaterial=RawMaterials.DwartSteelIngot;}

	public static final ItemArmor.ArmorMaterial LifeWoodArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":LIFEWOOD",
					6, new int[]{ 1, 3, 2, 1 }, 12);
	static{LifeWoodArmor.customCraftingMaterial= Item.getItemFromBlock(Blocks.log);}

	public static final ItemArmor.ArmorMaterial MogigaArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":MOGIGA",
					9, new int[]{ 3, 6, 4, 2 }, 45);
	static{MogigaArmor.customCraftingMaterial=RawMaterials.MogigaIngot;}

	public static final ItemArmor.ArmorMaterial SolitaArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":SOLITA",
					44, new int[]{ 4, 10, 8, 4 }, 10);
	static{SolitaArmor.customCraftingMaterial=RawMaterials.SolitaIngot;}

	public static final ItemArmor.ArmorMaterial SlimeArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":SOLITA",
					8, new int[]{ 1, 2, 2, 1 }, 14);
	static{SlimeArmor.customCraftingMaterial=Items.slime_ball;}

	public static final ItemArmor.ArmorMaterial WolfFurArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":WOLFFUR",
					4, new int[]{ 2, 3, 2, 1 }, 15);
	static{WolfFurArmor.customCraftingMaterial=RawMaterials.WolfFur;}

	public static final ItemArmor.ArmorMaterial IcyWolfFurArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":ICYWOLFFUR",
					5, new int[]{ 2, 3, 2, 1 }, 17);
	static{IcyWolfFurArmor.customCraftingMaterial=RawMaterials.IcyWolfFur;}

	public static final ItemArmor.ArmorMaterial StormArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":STORM",
					15, new int[]{ 2, 5, 4, 1 }, 12);
	static{StormArmor.customCraftingMaterial=RawMaterials.StormMetalIngot;}

	public static final ItemArmor.ArmorMaterial DarkIronArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":DARKIRON",
					5,new int[]{2,5,4,1}, 12);
	static{DarkIronArmor.customCraftingMaterial=RawMaterials.DarkMetalIngot;}


	public static final ItemArmor.ArmorMaterial LuxIronArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":LUXIRON",
					5,new int[]{2,5,4,1}, 12);
	static{LuxIronArmor.customCraftingMaterial=RawMaterials.LuxMetalIngot;}

	public static final ItemArmor.ArmorMaterial PhotosynthesisMetalArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":PHOTOSYNTHESIS",
					5,new int[]{2,5,4,1}, 12);
	static{LuxIronArmor.customCraftingMaterial=RawMaterials.LuxMetalIngot;}


	static
	{
		// note 这个用不到了 直接扔renderIndex一个4就好了
//		RenderingRegistry.addNewArmourRendererPrefix("bone");
//		RenderingRegistry.addNewArmourRendererPrefix("spectre");
//		RenderingRegistry.addNewArmourRendererPrefix("mithril");
//		RenderingRegistry.addNewArmourRendererPrefix("adamantium");
//		RenderingRegistry.addNewArmourRendererPrefix("flumetal");
//		RenderingRegistry.addNewArmourRendererPrefix("dwart");
//		RenderingRegistry.addNewArmourRendererPrefix("lifewood");
//		RenderingRegistry.addNewArmourRendererPrefix("mogiga");
//		RenderingRegistry.addNewArmourRendererPrefix("solita");
//		RenderingRegistry.addNewArmourRendererPrefix("wolffur");
//		RenderingRegistry.addNewArmourRendererPrefix("icywolffur");
	}


	public static final Item.ToolMaterial ObsidianTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":OBSIDIAN",
					3,590,3.5F,3.0F,8)
			.setRepairItem(new ItemStack(Blocks.obsidian));
	public static final Item.ToolMaterial BoneTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":BONE",
					0, 39, 1.5F, 0.0F, 16)
			.setRepairItem(new ItemStack(Items.bone));

	public static final Item.ToolMaterial SpectreTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":SPECTRE",
					1, 39, 2.5F, 2.0F, 18)
			.setRepairItem(new ItemStack(Items.ghast_tear));

	public static final Item.ToolMaterial MithrilTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":MITHRIL",
					3, 2000, 12.0F, 5.0F, 5)
			.setRepairItem(new ItemStack(RawMaterials.MithrilIngot));

	public static final Item.ToolMaterial AdamantiumTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":ADAMANTIUM",
					3, 300, 5.0F, 0.0F, 27)
			.setRepairItem(new ItemStack(RawMaterials.AdamantiumIngot));

	public static final Item.ToolMaterial FlumetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":FLUMETAL",
					2, 240, 5.5F, 1.5F, 16)
			.setRepairItem(new ItemStack(RawMaterials.FlumetalIngot));

	public static final Item.ToolMaterial DwartSteelTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":DWARTSTEEL",
					2, 500, 8.0F, 3.0F, 13)
			.setRepairItem(new ItemStack(RawMaterials.DwartSteelIngot));

	public static final Item.ToolMaterial LifeWoodTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":LIFEWOOD",
					0, 59, 2.0F, 0.0F, 17);

	public static final Item.ToolMaterial MogigaTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":MOGIGA",
					3, 460, 6.5F, 2.5F, 43)
			.setRepairItem(new ItemStack(RawMaterials.MogigaIngot));

	public static final Item.ToolMaterial SolitaTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":SOLITA",
					3, 5500, 15.0F, 8F, 8)
			.setRepairItem(new ItemStack(RawMaterials.SolitaIngot));

	public static final Item.ToolMaterial DarkMetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":DARKIRON",
					3,1200,12f,2,14)
			.setRepairItem(new ItemStack(RawMaterials.DarkMetalIngot)); // todo low 改一下性能参数

	public static final Item.ToolMaterial VibrhythmMetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":VIBRHYTHM",
					3,1200,12f,2,14)
			.setRepairItem(new ItemStack(RawMaterials.VibrhythmMetalIngot)); // todo low 改一下性能参数

	public static final Item.ToolMaterial LuxMetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":LUX",
					3,1200,12f,2,14)
			.setRepairItem(new ItemStack(RawMaterials.LuxMetalIngot)); // todo low 改一下性能参数

	public static final Item.ToolMaterial PhotosynthesisMetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":PHOTOSYNTHESIS",
					3,1200,12f,2,14)
			.setRepairItem(new ItemStack(RawMaterials.LuxMetalIngot)); // todo low 改一下性能参数

	public static final Item.ToolMaterial GreenCrystalMetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":GREENCRYSTAL",
					3,1800,12f,2,14)
					.setRepairItem(new ItemStack(Items.emerald)); // todo low 改一下性能参数

	// 独有材料
	public static final Item.ToolMaterial Alioth =
			EnumHelper.addToolMaterial(Irisia.MODID+":ALIOTH",
					5,3200,32F,10,20)
			.setRepairItem(new ItemStack(RawMaterials.SolitaIngot));

	public static final EnumRarity WainRarity=EnumHelper.addRarity("Wain",EnumChatFormatting.DARK_PURPLE,"Epic");
}
