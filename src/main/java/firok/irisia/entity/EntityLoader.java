package firok.irisia.entity;

import firok.irisia.Irisia;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

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
		registerEntity(100,Throwables.EntityStone.class,"entity_stone",
				64,1,true);
		registerEntity(101,Throwables.EntityRunicArrow.class,"entity_runic_arrow",
				64,10,true);
		registerEntity(102,Throwables.EntityThunderBall.class,"entity_thunder_ball",
				64,1,true);
		registerEntity(103,Throwables.EntityMagicalDirtBall.class,"entity_magical_dirt_ball",
				32,1,true);
		registerEntity(104,Throwables.EntityWitherOrb.class,"entity_wither_orb",
				32,1,true);

		registerEntity(0,Npcs.TestTarget.class,"entity_test_target",
				64,1,true);
		registerEntity(1,CarryOut.Carrier.class,"entity_carrier",
				24,1,true);

		registerEntity(200,Summons.SummonedIronGolem.class,"entity_summoned_iron_golem",
				32,3,true);
//		registerEntity(200,Monsters.GhostKnocker.class,"monster_ghost_knocker",
//				32,1,false); // fixme 这个实体有问题 暂时不注册
	}
	
	private static void registerEntity(int id,Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {
		// registerEntity(Entity.class,nameOfEntity,idOfEntity,mod.class,trackingRange,updateFrequency,sendsVelocityUpdates
        EntityRegistry.registerModEntity(entityClass, name, id, Irisia.instance,
		        trackingRange,
		        updateFrequency,
                sendsVelocityUpdates);
    }

    private static void registerSpawn(String name, int weight, int min, int max,
                                      EnumCreatureType spawnList, BiomeGenBase biomes)
    {
    	EntityRegistry.addSpawn(name,weight,min,max,spawnList,biomes);
    }
	
	private static void registerEntityEgg(Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary)
    {
//        EntityRegistry.registerEgg(entityClass, eggPrimary, eggSecondary);
    }
}
