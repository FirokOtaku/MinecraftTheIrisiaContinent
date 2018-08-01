package firok.irisia.entity;

import firok.irisia.Irisia;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityLoader
{
	public EntityLoader()
	{
		// Entity.class, nameOfEntity, trackingRange, updateFrequency, sendsVelocityUpdates
		// 实体class 实体名称 追踪距离 更新频率 是否速度更新
		// registerEntity(EntityTowerOrb.class,"TowerOrb",64,3,true);
//		registerEntity(201,OrbCharge.class,"OrbCharge",64,2,true);
//		registerEntity(202,MagicCrystalFragment.class,"MagicCrystalFragment",64,2,true);
//
//		registerEntity(101,TotemBase.class,"TotemBase",64,1,true);
//
//		registerEntity(301,WarpSeer.class,"WarpSeer",32,5,true);
	}
	
	private static void registerEntity(int id,Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {
		// registerEntity(Entity.class,nameOfEntity,idOfEntity,mod.class,trackingRange,updateFrequency,sendsVelocityUpdates
        EntityRegistry.registerModEntity(entityClass, name, id, Irisia.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
	
	private static void registerEntityEgg(Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary)
    {
//        EntityRegistry.registerEgg(entityClass, eggPrimary, eggSecondary);
    }
}
