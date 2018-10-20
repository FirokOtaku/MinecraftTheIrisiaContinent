package firok.irisia.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import firok.irisia.entity.Summons;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.util.ResourceLocation;

public class EntityRendererManager
{
	public static void init()
	{
		RenderingRegistry.registerEntityRenderingHandler(firok.irisia.entity.Npcs.TestTarget.class,new RenderVillager());

		RenderingRegistry.registerEntityRenderingHandler(Summons.SummonedIronGolem.class,
				new EntityRenderers.RenderSummonedGolem(new ResourceLocation("irisia","textures/entity/summoned_iron_golem.png")));
		RenderingRegistry.registerEntityRenderingHandler(Summons.SummonedThaumiumGolem.class,
				new EntityRenderers.RenderSummonedGolem(new ResourceLocation("irisia","textures/entity/summoned_thaumium_golem.png")));
		RenderingRegistry.registerEntityRenderingHandler(Summons.SummonedVoidGolem.class,
				new EntityRenderers.RenderSummonedGolem(new ResourceLocation("irisia","textures/entity/summoned_void_golem.png")));
		RenderingRegistry.registerEntityRenderingHandler(Summons.SummonedSolitaGolem.class,
				new EntityRenderers.RenderSummonedGolem(new ResourceLocation("irisia","textures/entity/summoned_solita_golem.png")));

	}
}
