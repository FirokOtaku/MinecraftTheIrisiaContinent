package firok.irisia.ability;

import java.lang.reflect.Constructor;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class CauseSummon
{
	public static void CenteredAt(World worldIn,double xIn,double yIn,double zIn,Class<? extends Entity> entityTypeIn)
	{
		if(worldIn.isRemote)
			return;
		try {
			Constructor cons=entityTypeIn.getConstructor(World.class);
			
			if(cons!=null)
			{
				Entity entity=(Entity)cons.newInstance(worldIn);
				entity.setPosition(xIn, yIn, zIn);
				worldIn.spawnEntityInWorld(entity);
			}
			else
				System.out.println("召唤实体出现错误");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("召唤实体出现错误");
		}
	}
	public static void CenteredAt(World worldIn,Class<? extends Entity> entityTypeIn)
	{
		CenteredAt(worldIn,entityTypeIn);
	}
}
