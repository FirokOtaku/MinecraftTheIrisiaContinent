package firok.irisia.ability;

import firok.irisia.DamageSources;
import firok.irisia.common.EntitySelectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

import java.util.*;

public class CauseLightningChain
{
	@SuppressWarnings("unchecked")
	public static void FromEntity(Entity source,float radius,
	                              float damageMax,float damageReduce,
	                              int countTargetMax,float rate)
	{
		World world=source.worldObj;
		if(world.isRemote) return;

		List<EntityLivingBase> entitiesCaused=new ArrayList<>();
		List<EntityLivingBase> entities=(List<EntityLivingBase>)world.getEntitiesWithinAABBExcludingEntity(source,
				AxisAlignedBB.getBoundingBox(
						source.posX-radius,source.posY-radius,source.posZ-radius,
						source.posX+radius,source.posY+radius,source.posZ+radius),
				EntitySelectors.SelectEntityMonstersAlive);
		genChain(source.worldObj.rand,source,
				entities,entitiesCaused,
				countTargetMax,rate,
				damageMax,damageReduce);
	}
	// 产生闪电链
	private static void genChain(Random rand,Entity source,
	                             List<EntityLivingBase> entities,
	                             List<EntityLivingBase> entitiesCaused,
	                             int countTargetMax,float rate,
	                             float damageNow,float damageReduce)
	{
		// 判断是否继续执行
		if(entitiesCaused.size()>=countTargetMax||damageNow<=0||rate<=0||entities.size()<=0) return;

		for(EntityLivingBase enlb:entities)
		{
			if(rand.nextFloat()>=rate && !(entitiesCaused.contains(enlb)))
			{
				entitiesCaused.add(enlb);
				enlb.attackEntityFrom(DamageSources.LightningDamege,damageNow);
				genLightning(source,enlb);
				genChain(rand,enlb,entities,entitiesCaused,
						countTargetMax,rate,
						damageNow-damageReduce,damageReduce);
			}
		}
	}
	// 产生闪电粒子
	private static void genLightning(Entity source,Entity target)
	{
		Thaumcraft.proxy.bolt(source.worldObj,source,target);
	}
}
