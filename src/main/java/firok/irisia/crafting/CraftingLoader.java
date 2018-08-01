package firok.irisia.crafting;

import firok.irisia.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingLoader
{
    public CraftingLoader()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe()
    {
    	/*
    	GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.goldenEgg), new Object[]
    			{
    					"###", "#*#", "###", '#', Items.gold_ingot, '*', Items.egg
    			});
    	GameRegistry.addShapelessRecipe(new ItemStack(), BlockLoader.grassBlock)
    	*/
    	// 索利塔金属合成
//    	GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.materialSolitaIngot,9),
//    			ItemLoader.materialAdamantiumIngot,
//    			ItemLoader.materialMagicPowder,ItemLoader.materialMagicPowder,
//    			Items.iron_ingot,Items.iron_ingot,Items.iron_ingot,
//    			Items.iron_ingot,Items.iron_ingot,Items.iron_ingot);
//    	// 魔伽金属合成
//    	GameRegistry.addShapelessRecipe(new ItemStack(ItemLoader.materialMagigaIngot,9),
//    			ItemLoader.materialMithrilIngot,
//    			ItemLoader.materialMagicPowder,ItemLoader.materialMagicPowder,
//    			Items.iron_ingot,Items.iron_ingot,Items.iron_ingot,
//    			Items.iron_ingot,Items.iron_ingot,Items.iron_ingot);
    }

    private static void registerSmelting()
    {
    	;
    }

    private static void registerFuel()
    {
        GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
                // return ItemLoader.itemVillagersCharcoal != fuel.getItem() ? 0 : 1200;
            	return 1;
            }
        });
    }
    private static void registerIron()
    {
    	;
    }
}
