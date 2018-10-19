package firok.irisia.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import firok.irisia.entity.Summons;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderVillager;

public class EntityRendererManager
{
	public static void init()
	{
		RenderingRegistry.registerEntityRenderingHandler(firok.irisia.entity.Npcs.TestTarget.class,new RenderVillager());
		RenderingRegistry.registerEntityRenderingHandler(Summons.SummonedIronGolem.class,new RenderIronGolem());
	}
}
