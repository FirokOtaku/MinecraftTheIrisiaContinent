package firok.irisia.inventory;

import firok.irisia.item.HandItemInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IHandInv
{
	HandItemInv getInv(EntityPlayer entityPlayer, ItemStack itemStack);
}
