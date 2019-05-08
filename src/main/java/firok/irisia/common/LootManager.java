package firok.irisia.common;

import firok.irisia.item.RawMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class LootManager
{
	private static ArrayList<Class> entities;
	private static ArrayList<ItemStack> itemstacks;
	private static ArrayList<Integer> maxdrop;
	private static ArrayList<Integer> mindrop;
	private static ArrayList<Float> ratedrop;

	static
	{
		entities=new ArrayList<Class>();
		itemstacks=new ArrayList<ItemStack>();
		maxdrop=new ArrayList<Integer>();
		mindrop=new ArrayList<Integer>();
		ratedrop=new ArrayList<>();
	}
	public static void registerLoot(Class entityClass,Item itemDrop,boolean randomlyDrop)
	{
		registerLoot(entityClass,itemDrop,randomlyDrop?0:1,1);
	}
	public static void registerLoot(Class entityClass,Item itemDrop,int minDrop,int maxDrop)
	{
		registerLoot(entityClass,new ItemStack(itemDrop),minDrop,maxDrop);
	}
	public static void registerLoot(Class entityClass, ItemStack itemStackDrop, boolean randomlyDrop)
	{
		registerLoot(entityClass,itemStackDrop,randomlyDrop?0:1,1);
	}
	public static void registerLoot(Class entityClass,ItemStack itemStackDrop,int minDrop,int maxDrop)
	{
		registerLoot(entityClass,itemStackDrop,minDrop,maxDrop,1);
	} // 注册掉落物
	public static void registerLoot(Class entityClass,ItemStack itemStackDrop,int minDrop,int maxDrop,float rateDrop)
	{
		if(entityClass==null||itemStackDrop==null)
			return;

		if(rateDrop<=0)
			return;
		int _minDrop=minDrop>=0?minDrop:0;
		int _maxDrop=maxDrop>=_minDrop?maxDrop:_minDrop;
		entities.add(entityClass);
		itemstacks.add(itemStackDrop);
		maxdrop.add(_minDrop);
		mindrop.add(_maxDrop);
		ratedrop.add(rateDrop);
	} // 注册掉落物


	public static List<ItemStack> getLoot(Entity entity) // 获取一个实体的掉落物 这个返回结果是随机的
	{
		ArrayList<ItemStack> ret=new ArrayList<ItemStack>();

		if(entity==null) return ret;

		Random rand=entity.worldObj.rand;

		for(int i=0;i<entities.size();i++)
		{
			if(entities.get(i).isInstance(entity))
			{
				if(rand.nextFloat()>ratedrop.get(i))
					continue;
				ItemStack itemStackDrop=itemstacks.get(i).copy();
				int dropmin=mindrop.get(i);
				int dropmax=maxdrop.get(i);
				int dropnow=dropmin;
				if(dropmax>dropmin)
				{
					dropnow+=rand.nextInt(dropmax-dropmin+1);
				}
				if(dropnow==0)
					continue;
				itemStackDrop.stackSize=dropnow;
				ret.add(itemStackDrop);
			}
		}
//		System.out.println(entity.toString()+" origin :");
//		for(ItemStack is:ret)
//		{
//			System.out.println(is.toString());
//		}
		return ret;
	}
	public static void dropLoot(Entity entity) // 在世界里产生掉落物 用在实体死亡event
	{
		List<ItemStack> drops=getLoot(entity);
		//System.out.println(entity.toString()+" died :");
		for(ItemStack is:drops)
		{
			//System.out.println(is.toString());
			int times=is.stackSize;
			is.stackSize=1;
			for(int i=0;i<times;i++)
			{
				entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj,entity.posX,entity.posY,entity.posZ,is));
			}
		}
	}
	// todo 实现对外更仔细的功能控制接口 比如删除掉落物什么的

	static void init() // note 掉落物在这里注册
	{
		registerLoot(EntityCow.class,RawMaterials.Bezoar,0,2);
		registerLoot(EntitySlime.class,new ItemStack(RawMaterials.SlimeCore),1,1,0.05f);
	}
}
