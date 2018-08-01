package firok.irisia.ability;

import net.minecraft.entity.Entity;

public class CauseMotion {
	// 让实体的运动速度发生改变
	// 推动实体
	public static void excute(Entity entityIn,float xSpeed,float ySpeed,float zSpeed)
	{
		entityIn.motionX+=xSpeed;
		entityIn.motionY+=ySpeed;
		entityIn.motionZ+=zSpeed;
	}
}
