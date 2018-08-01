package firok.irisia.fluid;

import firok.irisia.Irisia;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class LiquidOfRichCharge extends Fluid
{
	public static final ResourceLocation still = new ResourceLocation(Irisia.MODID + ":" + "fluid/liquid_of_rich_charge_still");
    public static final ResourceLocation flowing = new ResourceLocation(Irisia.MODID + ":" + "fluid/liquid_of_rich_charge_flow");
    
	public LiquidOfRichCharge()
	{
		super("mud");
    	this.setDensity(1000); // 密度1倍于水
    	this.setViscosity(500); // 粘度0.5倍于水
    	this.setLuminosity(2); // 亮度4
    	this.setTemperature(600); // 水温2倍于水
    	this.setGaseous(false); // 不是气体
	}
}
