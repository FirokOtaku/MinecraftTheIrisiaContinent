package firok.irisia.ability;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class CauseEffect {
	// 以BlockPos为中心 float为半径 为周围的实体添加效果
//	public static void CenteredAt(Class<? extends Entity> classEntityIn,World worldIn,BlockPos posIn,float radiusIn,Potion potionIn,int durationIn,int amplifierIn)
//	{
////		// net.minecraft.potion.PotionEffect.PotionEffect(Potion potionIn, int durationIn, int amplifierIn)
////		List<Entity> listEntities=worldIn.getEntitiesWithinAABB
////				(classEntityIn,new AxisAlignedBB(posIn.getX()-radiusIn, posIn.getY()-radiusIn, posIn.getZ()-radiusIn, posIn.getX()+radiusIn, posIn.getY()+radiusIn, posIn.getZ()+radiusIn));
////		for(Entity entity_temp:listEntities)
////		{
////			if(entity_temp instanceof EntityLiving)
////			{
////				((EntityLiving) entity_temp).addPotionEffect(new PotionEffect(potionIn,durationIn,amplifierIn));
////			}
////		}
//
//	}
//	// 以Entity为中心
//	public static void CenteredAt(Class<? extends Entity> classEntityIn,World worldIn,Entity entityIn,float radiusIn,Potion potionIn,int durationIn,int amplifierIn)
//	{
////		CenteredAt(classEntityIn,worldIn,entityIn.getPosition(),radiusIn,potionIn,durationIn,amplifierIn);
//	}
}