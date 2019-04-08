package firok.irisia.ability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class CauseVisLeak
{
	private static final AspectList visCost=new AspectList()
			.add(Aspect.ORDER,40)
			.add(Aspect.ENTROPY,40)
			.add(Aspect.FIRE,40)
			.add(Aspect.WATER,40)
			.add(Aspect.EARTH,40)
			.add(Aspect.AIR,40);
	public static void AtPlayer(EntityPlayer p)
	{
		AtPlayer(p,visCost);
	}
	public static void AtPlayer(EntityPlayer p,AspectList cost)
	{
		ItemStack itemStack=p.getHeldItem();
		if(itemStack!=null&&itemStack.getItem() instanceof ItemWandCasting)
		{
			ItemWandCasting wand = (ItemWandCasting)itemStack.getItem();
			ItemStack focustack=wand.getFocusItem(itemStack);
			wand.consumeAllVis(itemStack,p,cost,!p.worldObj.isRemote, false);
		}
	}
}
