package firok.irisia.tileentity;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import firok.irisia.Irisia;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLoader
{
	public TileEntityLoader(FMLPreInitializationEvent event)
	{
		registerTileEntity(BerryMixerTE.class, "MetalFurnace");
	}

	public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
	{
		GameRegistry.registerTileEntity(tileEntityClass, Irisia.MODID + ":" + id);
	}
}
