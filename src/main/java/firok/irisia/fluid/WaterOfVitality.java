package firok.irisia.fluid;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import java.util.HashMap;

import firok.irisia.Irisia;

public class WaterOfVitality extends Fluid
{
	public static final ResourceLocation still = new ResourceLocation(Irisia.MODID + ":" + "fluid/water_of_vitality_still");
    public static final ResourceLocation flowing = new ResourceLocation(Irisia.MODID + ":" + "fluid/water_of_vitality_flow");
    
    protected static HashMap<Item,Class<? extends Entity>> summonRecipes=new HashMap<Item,Class<? extends Entity>>();

	public WaterOfVitality(boolean _addMinecraftSummonIn)
	{
		super("waterOfVitality");
    	this.setDensity(1000); // 密度1倍于水
    	this.setViscosity(1000); // 粘度1倍于水
    	this.setLuminosity(2); // 亮度2
    	this.setTemperature(300); // 水温与水相同
    	this.setGaseous(false); // 不是气体
    	
    	if(_addMinecraftSummonIn) // 添加原版的召唤
    		_addMinecraftSummon();
	}
	
	public static void addSummonRecipe(Item itemIn,Class<? extends Entity> entityTypeIn)
	{
		summonRecipes.put(itemIn, entityTypeIn);
	}
	public static boolean hasSummon(Item itemIn)
	{
		return summonRecipes.containsKey(itemIn);
	}
	public static Class<? extends Entity> getSummon(Item itemIn)
	{
		return summonRecipes.get(itemIn);
	}
	protected static void _addMinecraftSummon()
	{
		addSummonRecipe(Items.bone,EntitySkeleton.class);
		addSummonRecipe(Items.slime_ball,EntitySlime.class);
		addSummonRecipe(Items.rotten_flesh,EntityZombie.class);
		addSummonRecipe(Items.spider_eye,EntitySpider.class);
	}
}
