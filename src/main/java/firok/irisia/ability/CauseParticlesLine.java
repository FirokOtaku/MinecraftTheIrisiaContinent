package firok.irisia.ability;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CauseParticlesLine
{
	public static void FromTo(World worldIn,EntityFX particleIn,float density)
	{
		;
	}
	
	public static void FromTo(World worldIn,Entity ent1,Entity ent2,EntityFX particleIn,float density)
	{
		FromTo(worldIn,particleIn,density);
	}
}
