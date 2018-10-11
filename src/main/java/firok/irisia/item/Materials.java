package firok.irisia.item;

import cpw.mods.fml.client.registry.RenderingRegistry;
import firok.irisia.Irisia;
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

	public static final ItemArmor.ArmorMaterial SpectreArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":SPECTRE",
					8, new int[]{ 1, 3, 2, 1 }, 14);

	public static final ItemArmor.ArmorMaterial MithrilArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":MITHRIL",
					30, new int[]{ 4, 8, 7, 4 }, 10);

	public static final ItemArmor.ArmorMaterial AdamantiumArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":ADAMANTIUM",
					8, new int[]{ 2, 5, 4, 1 }, 35);

	public static final ItemArmor.ArmorMaterial FlumetalArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":FLUMETAL",
					13, new int[]{ 2, 5, 4, 2 }, 10);

	public static final ItemArmor.ArmorMaterial DwartSteelArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":DWARTSTELL",
					18, new int[]{ 3, 6, 6, 3 }, 8);

	public static final ItemArmor.ArmorMaterial LifeWoodArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":LIFEWOOD",
					6, new int[]{ 1, 3, 2, 1 }, 12);

	public static final ItemArmor.ArmorMaterial MogigaArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":MOGIGA",
					9, new int[]{ 3, 6, 4, 2 }, 45);

	public static final ItemArmor.ArmorMaterial SolitaArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":SOLITA",
					44, new int[]{ 4, 10, 8, 4 }, 10);

	public static final ItemArmor.ArmorMaterial SlimeArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":SOLITA",
					8, new int[]{ 1, 2, 2, 1 }, 14);

	public static final ItemArmor.ArmorMaterial WolfFurArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":WOLFFUR",
					8, new int[]{ 1, 2, 2, 1 }, 15);

	public static final ItemArmor.ArmorMaterial IcyWolfFurArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":ICYWOLFFUR",
					8, new int[]{ 1, 2, 2, 1 }, 15);

	public static final ItemArmor.ArmorMaterial StormArmor =
			EnumHelper.addArmorMaterial(Irisia.MODID+":STORM",
					8, new int[]{ 1, 2, 2, 1 }, 15);

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


	public static final Item.ToolMaterial BoneTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":BONE",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(Items.bone));

	public static final Item.ToolMaterial SpectreTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":SPECTRE",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(Items.ghast_tear));

	public static final Item.ToolMaterial MithrilTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":MITHRIL",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(RawMaterials.MithrilIngot));

	public static final Item.ToolMaterial AdamantiumTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":ADAMANTIUM",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(RawMaterials.AdamantiumIngot));

	public static final Item.ToolMaterial FlumetalTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":FLUMETAL",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(RawMaterials.FlumetalIngot));

	public static final Item.ToolMaterial DwartSteelTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":DWARTSTEEL",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(RawMaterials.DwartSteelIngot));

	public static final Item.ToolMaterial LifeWoodTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":LIFEWOOD",
					3, 16, 16.0F, 0.0F, 10);

	public static final Item.ToolMaterial MogigaTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":MOGIGA",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(RawMaterials.MogigaIngot));

	public static final Item.ToolMaterial SolitaTool =
			EnumHelper.addToolMaterial(Irisia.MODID+":SOLITA",
					3, 16, 16.0F, 0.0F, 10)
			.setRepairItem(new ItemStack(RawMaterials.SolitaIngot));


	// 独有材料
	public static final Item.ToolMaterial Alioth =
			EnumHelper.addToolMaterial(Irisia.MODID+":ALIOTH",
					5,3200,32F,10,20)
			.setRepairItem(new ItemStack(RawMaterials.SolitaIngot));

	public static final EnumRarity WainRarity=EnumHelper.addRarity("Wain",EnumChatFormatting.DARK_PURPLE,"Epic");
}
