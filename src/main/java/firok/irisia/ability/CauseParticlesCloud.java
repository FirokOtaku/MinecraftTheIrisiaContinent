package firok.irisia.ability;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CauseParticlesCloud {
	public static void CenteredAt(World worldIn,EntityFX particleIn,float dencityIn,float xSpeed,float ySpeed,float zSpeed)
	{
		return;
	}
	public static void CenteredAt(World worldIn,Entity entityIn,EntityFX particleIn,float dencityIn,float xSpeed,float ySpeed,float zSpeed)
	{
		CenteredAt(worldIn,particleIn,dencityIn,xSpeed,ySpeed,zSpeed);
	}
}
