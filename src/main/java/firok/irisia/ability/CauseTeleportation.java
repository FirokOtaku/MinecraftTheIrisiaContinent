package firok.irisia.ability;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CauseTeleportation {
	// 随机传送
	public static void teleportEntityRandomly(Entity entityIn,int teleportHorizontalRange)
	{
		teleportEntityRandomly(entityIn,teleportHorizontalRange,true,true);
	}
	
	public static void teleportEntityRandomly(Entity entityIn,int teleportHorizontalRange,boolean spawnParticle,boolean playSound)
	{
//		try
//		{
//			World world=entityIn.getEntityWorld();
////			if(world.isRemote)
////				return;
//
//			boolean playsound=playSound && entityIn instanceof EntityPlayer;
//
//			if(spawnParticle)
//			{
//				world.spawnParticle(EnumParticleTypes.PORTAL,
//						entityIn.posX, entityIn.posY, entityIn.posZ, 0, 0, 0, null);
//				world.spawnParticle(EnumParticleTypes.PORTAL,
//						entityIn.posX, entityIn.posY, entityIn.posZ, 0, 0, 0, null);
//			}
//
//			if(playsound)
//				world.playSound((EntityPlayer) entityIn, entityIn.posX, entityIn.posY, entityIn.posZ,
//						SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1, 1);
//
//			Random rand=world.rand;
//			int x=centerIn.getX()-teleportHorizontalRange+rand.nextInt(2*teleportHorizontalRange);
//			int z=centerIn.getZ()-teleportHorizontalRange+rand.nextInt(2*teleportHorizontalRange);
//			// 找到传送范围内的一个位置最顶端的方块
//			BlockPos topPos=world.getTopSolidOrLiquidBlock(new BlockPos(x,centerIn.getY(),z));
//			// 传送实体
//			entityIn.setPosition(x, topPos.getY()+2, z);
//
//			if(playsound)
//				world.playSound((EntityPlayer) entityIn, entityIn.posX, entityIn.posY, entityIn.posZ,
//						SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1, 1);
//
//			if(spawnParticle)
//			{
//				world.spawnParticle(EnumParticleTypes.PORTAL,
//						entityIn.posX, entityIn.posY, entityIn.posZ, 0, 0, 0, null);
//				world.spawnParticle(EnumParticleTypes.PORTAL,
//						entityIn.posX, entityIn.posY, entityIn.posZ, 0, 0, 0, null);
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}

	public static void teleportEntity(Entity en1,Entity en2,boolean spawnParticle,boolean playSound)
	{
		if(en1.worldObj.isRemote)
			return;

		en1.setPosition(en2.posX,en2.posY,en2.posZ);
	}
	public static void teleportEntity(Entity en1,Entity en2)
	{
		teleportEntity(en1,en2,true,true);
	}
}
