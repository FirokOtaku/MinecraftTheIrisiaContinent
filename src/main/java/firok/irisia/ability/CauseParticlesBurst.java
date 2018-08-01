package firok.irisia.ability;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CauseParticlesBurst {
	// 粒子是随机方向的
	// 数量 速度
	public static void CenteredAt(World worldIn,float radiusIn,EntityFX particleIn,int amount,float speed)
	{
		// worldIn.spawnParticle(particleIn, radiusIn, posIn.getX(), posIn.getY(), posIn.getZ(), xSpeed, ySpeed, zSpeed);
	}
	public static void CenteredAt(World worldIn,Entity entityIn,float radiusIn,EntityFX particleIn,int amount,float speed)
	{
		CenteredAt(worldIn,radiusIn,particleIn,amount,speed);
	}
}
