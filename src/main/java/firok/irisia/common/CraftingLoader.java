package firok.irisia.common;

import cpw.mods.fml.common.IFuelHandler;
import firok.irisia.item.EquipmentSets;
import firok.irisia.item.RawMaterials;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.config.ConfigItems;

public class CraftingLoader
{
    public CraftingLoader()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    // register all
    protected static void registerRecipe()
    {
        // armor sets
        registerEquipmentSetCrafting(EquipmentSets.AdamantiumSet,RawMaterials.AdamantiumIngot);
        registerEquipmentSetCrafting(EquipmentSets.MithrilSet,RawMaterials.MithrilIngot);
        registerEquipmentSetCrafting(EquipmentSets.MogigaSet,RawMaterials.MogigaIngot);
        registerEquipmentSetCrafting(EquipmentSets.SolitaSet,RawMaterials.SolitaIngot);
        registerEquipmentSetCrafting(EquipmentSets.FlumetalSet,RawMaterials.FlumetalIngot);
        registerEquipmentSetCrafting(EquipmentSets.DwartSteelSet,RawMaterials.DwartSteelIngot);
        registerEquipmentSetCrafting(EquipmentSets.BoneSet,Items.bone);

        // coins
        registerCoinCrafting();

        registerRawMaterialCrafting();
    	/*
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.goldenEgg), new Object[]
    			{
    					"###", "#*#", "###", '#', Items.gold_ingot, '*', Items.egg
    			});
    	GameRegistry.addShapelessRecipe(new ItemStack(), BlockLoader.grassBlock)
    	*/
    }

    protected static void registerSmelting()
    {
    	;
    }

    protected static void registerFuel()
    {
//        GameRegistry.registerFuelHandler(new IFuelHandler()
//        {
//            @Override
//            public int getBurnTime(ItemStack fuel)
//            {
//                // return ItemLoader.itemVillagersCharcoal != fuel.getItem() ? 0 : 1200;
//            	return 1;
//            }
//        });
        // 树苗　　100
        // 木板　　200
        // 煤炭　　1600
        // 烈焰棒　2400
        // 煤炭块　16000
        // 岩浆桶　20000
        GameRegistry.registerFuelHandler(fuel ->
        {
            if(fuel==null) return 0;
            Item item=fuel.getItem();
            if(item==RawMaterials.DwartCoal || item==RawMaterials.HotBlood)
                return 2400;

            if(item==RawMaterials.HotStone)
                return 4800;

            return 0;
        });
    }



    // some functions
    protected static void registerRawMaterialCrafting()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.paper,8),RawMaterials.PaperPile);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.paper,64),RawMaterials.PaperBigPile);
        GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemInkwell,1),RawMaterials.Ink,Items.glass_bottle, Items.feather);
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.InkBottle),RawMaterials.Ink,Items.glass_bottle);
        GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemInkwell,1),RawMaterials.InkBottle,Items.feather);
    }
    protected static void registerCoinCrafting()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.CoinIrisiaPile),
                RawMaterials.CoinIrisia,RawMaterials.CoinIrisia,RawMaterials.CoinIrisia,RawMaterials.CoinIrisia,
                RawMaterials.CoinIrisia,RawMaterials.CoinIrisia,RawMaterials.CoinIrisia,RawMaterials.CoinIrisia);
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.CoinGoldPile),
                RawMaterials.CoinGold,RawMaterials.CoinGold,RawMaterials.CoinGold,RawMaterials.CoinGold,
                RawMaterials.CoinGold,RawMaterials.CoinGold,RawMaterials.CoinGold,RawMaterials.CoinGold);
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.CoinGold),
                RawMaterials.CoinSilverPile,RawMaterials.CoinSilverPile,RawMaterials.CoinSilverPile,RawMaterials.CoinSilverPile,
                RawMaterials.CoinSilverPile,RawMaterials.CoinSilverPile,RawMaterials.CoinSilverPile,RawMaterials.CoinSilverPile);
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.CoinSilverPile),
                RawMaterials.CoinSilver,RawMaterials.CoinSilver,RawMaterials.CoinSilver,RawMaterials.CoinSilver,
                RawMaterials.CoinSilver,RawMaterials.CoinSilver,RawMaterials.CoinSilver,RawMaterials.CoinSilver);
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.CoinSilver),
                RawMaterials.CoinCopperPile,RawMaterials.CoinCopperPile,RawMaterials.CoinCopperPile,RawMaterials.CoinCopperPile,
                RawMaterials.CoinCopperPile,RawMaterials.CoinCopperPile,RawMaterials.CoinCopperPile,RawMaterials.CoinCopperPile);
        GameRegistry.addShapelessRecipe(new ItemStack(RawMaterials.CoinCopperPile),
                RawMaterials.CoinCopper,RawMaterials.CoinCopper,RawMaterials.CoinCopper,RawMaterials.CoinCopper,
                RawMaterials.CoinCopper,RawMaterials.CoinCopper,RawMaterials.CoinCopper,RawMaterials.CoinCopper);
    }

    protected static void registerEquipmentSetCrafting(EquipmentSets.ArmorSet set, Item ingot)
    {
        registerHelmetCrafting(set.Helmet,ingot);
        registerChestCrafting(set.Chestplate,ingot);
        registerLeggingsCrafting(set.Leggings,ingot);
        registerBootsCrafting(set.Boots,ingot);
    }
    protected static void registerEquipmentSetCrafting(EquipmentSets.EquipmentSet set, Item ingot)
    {
        if(set==null)
            return;

        if(set.hasArmor)
        {
            registerHelmetCrafting(set.Helmet,ingot);
            registerChestCrafting(set.Chestplate,ingot);
            registerLeggingsCrafting(set.Leggings,ingot);
            registerBootsCrafting(set.Boots,ingot);
        }
        if(set.hasWeapon)
        {
            registerSwordCrafting(set.Sword,ingot);
        }
        if(set.hasTools)
        {
            registerPickaxeCrafting(set.Pickaxe,ingot);
            registerAxeCrafting(set.Axe,ingot);
            registerHoeCrafting(set.Hoe,ingot);
            registerSpadeCrafting(set.Spade,ingot);
        }
    }
    private static void registerHelmetCrafting(Item output,Item ingot)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                "###","# #","   ",'#',ingot);
    }
    private static void registerChestCrafting(Item output,Item ingot)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                "# #","###","###",'#',ingot);
    }
    private static void registerLeggingsCrafting(Item output,Item ingot)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                "###","# #","# #",'#',ingot);
    }
    private static void registerBootsCrafting(Item output,Item ingot)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                "   ","# #","# #",'#',ingot);
    }

    private static void registerSwordCrafting(Item output,Item ingot)
    {
        registerSwordCrafting(output,ingot,null);
    }
    private static void registerSwordCrafting(Item output,Item ingot,Item hilt)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{"#  ","#  ","*  ",'#',ingot,'*',Items.stick}:
                        new Object[]{"#  ","#  ","*  ",'#',ingot,'*',hilt});
    }

    private static void registerPickaxeCrafting(Item output,Item ingot)
    {
        registerPickaxeCrafting(output,ingot,null);
    }
    private static void registerPickaxeCrafting(Item output,Item ingot,Item hilt)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{"###"," * "," * ",'#',ingot,'*',Items.stick}:
                        new Object[]{"###"," * "," * ",'#',ingot,'*',hilt});
    }

    private static void registerAxeCrafting(Item output,Item ingot)
    {
        registerAxeCrafting(output,ingot,null);
    }
    private static void registerAxeCrafting(Item output,Item ingot,Item hilt)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{"## ","#* "," * ",'#',ingot,'*',Items.stick}:
                        new Object[]{"## ","#* "," * ",'#',ingot,'*',hilt});
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{" ##"," *#"," * ",'#',ingot,'*',Items.stick}:
                        new Object[]{" ##"," *#"," * ",'#',ingot,'*',hilt});
    }

    private static void registerHoeCrafting(Item output,Item ingot)
    {
        registerHoeCrafting(output,ingot,null);
    }
    private static void registerHoeCrafting(Item output,Item ingot,Item hilt)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{"## "," * "," * ",'#',ingot,'*',Items.stick}:
                        new Object[]{"## "," * "," * ",'#',ingot,'*',hilt});
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{" ##"," * "," * ",'#',ingot,'*',Items.stick}:
                        new Object[]{" ##"," * "," * ",'#',ingot,'*',hilt});
    }

    private static void registerSpadeCrafting(Item output,Item ingot)
    {
        registerSpadeCrafting(output,ingot,null);
    }
    private static void registerSpadeCrafting(Item output,Item ingot,Item hilt)
    {
        if(output==null) return;
        GameRegistry.addShapedRecipe(new ItemStack(output),
                hilt==null?
                        new Object[]{" # "," * "," * ",'#',ingot,'*',Items.stick}:
                        new Object[]{" # "," * "," * ",'#',ingot,'*',hilt});
    }

}
