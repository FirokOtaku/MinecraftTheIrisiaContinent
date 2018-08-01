package firok.irisia.fluid;

import firok.irisia.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class Mud extends Fluid
{
	
	public static final ResourceLocation still = new ResourceLocation(Irisia.MODID + ":" + "fluid/mud_still");
    public static final ResourceLocation flowing = new ResourceLocation(Irisia.MODID + ":" + "fluid/mud_flow");

    public Mud()
    {
    	super("mud");
    	this.setDensity(4000); // 密度4倍于水
    	this.setViscosity(10000); // 粘度10倍于水
    	this.setLuminosity(0); // 亮度0
    	this.setTemperature(300); // 水温与水相同
    	this.setGaseous(false); // 不是气体
    }
}
