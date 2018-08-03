package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.items.ItemRing;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EquipmentUniques
{
	public static EquipmentSets.Ring PhaseRing; // 月相戒指
	public static EquipmentSets.Ring TwilightRing; // 暮光戒指
	public static EquipmentSets.Ring FortuneRing; // 幸运戒指

	static
	{
		FortuneRing=new EquipmentSets.Ring()
		{
			@Override
			public void onWornTick(ItemStack is, EntityLivingBase enlb)
			{
				if(!enlb.worldObj.isRemote && enlb instanceof EntityPlayer && enlb.ticksExisted % 40 ==0)
				{
//					EntityPlayer p=(EntityPlayer)enlb;
//					p.addChatComponentMessage(new ChatComponentText("Worn Tick"+enlb.ticksExisted%20));
				}
			}

		};
	}

}
