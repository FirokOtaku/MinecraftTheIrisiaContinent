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

public class EquipmentUniqueBaubles
{
	// baubles
	public static EquipmentSets.Ring PhaseRing; // 月相戒指
	public static EquipmentSets.Ring TwilightRing; // 暮光戒指
	public final static EquipmentSets.Ring FortuneRing; // 幸运戒指


	// amulets
	public final static EquipmentSets.Amulet PhotosynthesisAmulet; // 光合护身符

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

		PhotosynthesisAmulet=new EquipmentSets.Amulet()
		{
			@Override
			public void onWornTick(ItemStack stack, EntityLivingBase entity)
			{
				if(entity.worldObj.isRemote
						|| entity.worldObj.getTotalWorldTime()%60!=0
						|| entity.worldObj.getBlockLightValue_do
						((int)entity.posX,(int)entity.posY,(int)entity.posZ,true)<9)
					return;

				entity.heal(1);
			}
		};
	}
}
