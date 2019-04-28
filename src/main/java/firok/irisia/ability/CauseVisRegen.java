package firok.irisia.ability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

import java.util.Map;

public class CauseVisRegen
{
	private static ItemStack getWandStackInInv(EntityPlayer player)
	{
		for(int i=0;i<player.inventory.mainInventory.length;i++)
		{
			ItemStack tempStack=player.inventory.mainInventory[i];
			if(tempStack!=null && tempStack.getItem()== ConfigItems.itemWandCasting)
			{
				return tempStack;
			}
		}
		return null;
	}
	public static void AtStack(ItemStack itemStack,AspectList regen)
	{
		ItemWandCasting wand=(ItemWandCasting)itemStack.getItem();
		for(Map.Entry<Aspect,Integer> entry:regen.aspects.entrySet())
		{
			wand.addVis(itemStack,entry.getKey(),entry.getValue(),true);
		}
	}
	public static boolean AtPlayer(EntityPlayer player,AspectList regen)
	{
		ItemStack stack=getWandStackInInv(player);
		if(stack!=null)
			AtStack(stack,regen);
		return stack!=null;
	}
}
