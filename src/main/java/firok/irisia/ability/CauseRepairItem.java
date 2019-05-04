package firok.irisia.ability;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class CauseRepairItem
{
	/**
	 * 修复指定物品
	 * (修复数值为 -1 )
	 */
	public static boolean To(ItemStack itemStack,EntityLivingBase enlb)
	{
		return To(itemStack,enlb,1);
	}

	/**
	 * 修复指定物品
	 * (修复数值为 -abs(damage) )
	 */
	public static boolean To(ItemStack itemStack,EntityLivingBase enlb,int damage)
	{
		boolean shouldRepair=itemStack.getItemDamage()>0;
		if(shouldRepair) itemStack.damageItem(damage>0?-damage:damage,enlb);
		return shouldRepair;
	}
}
