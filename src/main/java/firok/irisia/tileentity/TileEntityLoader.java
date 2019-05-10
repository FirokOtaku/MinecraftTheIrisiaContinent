package firok.irisia.tileentity;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import firok.irisia.Irisia;
import firok.irisia.block.VisNode;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLoader
{
	public TileEntityLoader(FMLPreInitializationEvent event)
	{
		registerTileEntity(OrientedMetalInfusionerTE.class,"OrientedMetalInfusioner");
		registerTileEntity(BerryMixerTE.class, "MetalFurnace");
		registerTileEntity(VisNodeTE.class,"VisNode");
		registerTileEntity(AirWallTE.class,"AirWall");
		registerTileEntity(AuraCompresserTE.class,"AuraCompressor");
	}

	public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
	{
		GameRegistry.registerTileEntity(tileEntityClass, Irisia.MODID + ":" + id);
	}
}
