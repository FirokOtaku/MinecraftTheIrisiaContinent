package firok.irisia.entity;

import firok.irisia.Colors;
import firok.irisia.Irisia;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import static net.minecraft.entity.EntityList.*;

public class EntityLoader
{
	public EntityLoader()
	{
		// Entity.class, nameOfEntity, trackingRange, updateFrequency, sendsVelocityUpdates
		// 实体class 实体名称 追踪距离 更新频率 是否同步数据
		// registerEntity(EntityTowerOrb.class,"TowerOrb",64,3,true);
//		registerEntity(201,OrbCharge.class,"OrbCharge",64,2,true);
//		registerEntity(202,MagicCrystalFragment.class,"MagicCrystalFragment",64,2,true);
//
//		registerEntity(101,TotemBase.class,"TotemBase",64,1,true);
//
//		registerEntity(301,WarpSeer.class,"WarpSeer",32,5,true);
		registerEntity(0,Npcs.TestTarget.class,"entity_test_target",
				64,1,true);
		registerEntity(1,CarryOut.Carrier.class,"entity_carrier",
				24,1,true);

		registerEntity(30,Throwables.EntityStone.class,"entity_stone",
				64,1,true);
		registerEntity(31,Throwables.EntityRunicArrow.class,"entity_runic_arrow",
				64,10,true);
		registerEntity(32,Throwables.EntityThunderBall.class,"entity_thunder_ball",
				64,1,true);
		registerEntity(33,Throwables.EntityMagicalDirtBall.class,"entity_magical_dirt_ball",
				32,1,true);
		registerEntity(34,Throwables.EntityWitherOrb.class,"entity_wither_orb",
				32,1,true);
		registerEntity(35,Throwables.EntitySupernovaOrb.class,"entity_supernova_orb",
				32,1,true);
		registerEntity(36,Throwables.EntityBurningSpearArrow.class,"entity_burning_spear",
				32,1,true);

		registerEntity(60,Summons.SummonedIronGolem.class,"entity_summoned_iron_golem",
				32,3,true,
				Colors.Gray,Colors.Brown);
		registerEntity(61,Summons.SummonedThaumiumGolem.class,"entity_summoned_thaumium_golem",
				32,3,true,
				Colors.Azure,Colors.Brown);
		registerEntity(62,Summons.SummonedVoidGolem.class,"entity_summoned_void_golem",
				32,3,true,
				Colors.Purple,Colors.Brown);
		registerEntity(63,Summons.SummonedSolitaGolem.class,"entity_summoned_solita_golem",
				32,3,true,
				Colors.YellowGreen,Colors.Brown);

		registerEntity(90,Creatures.HarmlessSlime.class,"entity_harmless_slime",
							24,3,true);
		registerEntity(200,Monsters.GhostKnocker.class,"monster_ghost_knocker",
				32,1,false);
		registerEntity(120,Monsters.AncientCube.class,"entity_ancient_cube",
				32,3,true);
	}

	private static void registerEntity(int id,Class<? extends Entity> entityClass,
	                                          String name, int trackingRange,int updateFrequency, boolean sendsVelocityUpdates,
	                                          int colorMain,int colorSub)
	{
//		entityEggs.put(Integer.valueOf(id),
//				new EntityEggInfo(id, colorMain, colorSub));
		// fixme 这里有问题 注册的时候id会重复 不知道应该怎么处理

		registerEntity(id, entityClass, name, trackingRange, updateFrequency, sendsVelocityUpdates);
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
