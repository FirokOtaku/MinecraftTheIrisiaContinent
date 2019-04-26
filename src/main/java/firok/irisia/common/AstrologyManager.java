package firok.irisia.common;

import net.minecraft.world.World;
import java.util.Random;

/**
 * 星象数据管理器
 */
public class AstrologyManager
{
	private static long lastDay=-1;
	private static float factor=0;
	private static long getDay(long worldTime)
	{
		return worldTime%24000;
	}

	/**
	 * 获取星象因数
	 * @param world 世界对象
	 * @return 星象因子 ( -1 < ret < 1 )
	 */
	public static float getFactor(World world)
	{
		return getFactor(world.getWorldTime(),world.getSeed());
	}

	/**
	 * 获取星象因数
	 * @param worldTotalTime 世界时间
	 * @param seed 世界种子
	 * @return 星象因子 ( -1 < ret < 1 )
	 */
	private static float getFactor(long worldTotalTime,long seed)
	{
		long day=worldTotalTime/24000;
		if(lastDay==day) return factor;
		lastDay=day;
		factor=(float)(new Random(day).nextGaussian());
		return factor;
	}

	/**
	 * 获取带有类型的星象因数
	 * @param world 世界对象
	 * @param side 类型
	 * @return 星象加成因子 ( 0 <= ret < 1 )
	 */
	public static float getFactorSide(World world,boolean side)
	{
		float temp=getFactor(world);
		return side?
				(temp>0?temp:0)
				:(temp<0?temp:0);
	}

}
