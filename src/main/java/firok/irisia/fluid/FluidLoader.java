package firok.irisia.fluid;

import java.util.LinkedList;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class FluidLoader
{
	public static LinkedList<Fluid> fluids;
	
	// 这里所有的static类型Fluid实例不能直接使用
	// 而是转交给getFluid()方法获得安全的液体
	public static Fluid mud=new Mud();
	public static Fluid waterOfVitality=new WaterOfVitality(true);
	public static Fluid liquidOfRichCharge=new LiquidOfRichCharge();
	
	public FluidLoader(FMLPreInitializationEvent event)
	{
		if(fluids==null)
			fluids=new LinkedList<Fluid>();
		
		registerFluid(event,mud);
		registerFluid(event,waterOfVitality);
		registerFluid(event,liquidOfRichCharge);
	}
	
	protected void registerFluid(FMLPreInitializationEvent event,Fluid fluidIn)
	{
		if (FluidRegistry.isFluidRegistered(fluidIn))
        {
            event.getModLog().info("Found fluid {}, the registration is canceled. ", fluidIn.getName());
            fluidIn = FluidRegistry.getFluid(fluidIn.getName());
        }
        else
        {
            FluidRegistry.registerFluid(mud);
            fluids.add(fluidIn);
        }
	}
	
	public static Fluid getFluid(Fluid fluidIn)
	{
		for(Fluid tempFluid:fluids)
		{
			if(tempFluid.getName().equals(fluidIn.getName()))
			{
				return tempFluid;
			}
		}
		return fluidIn;
	}
}
