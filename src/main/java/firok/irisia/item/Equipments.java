package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class Equipments
{
	public final static EquipmentSet BoneSet;
	public final static EquipmentSet MithrilSet;
	public final static EquipmentSet AdamantiumSet;
	public final static EquipmentSet FlumetalSet;
	public final static EquipmentSet SpectreSet;
	public final static EquipmentSet DwartSteelSet;
	public final static EquipmentSet LifeWoodSet;

	static
	{
		BoneSet=new EquipmentSet(Materials.BoneArmor,Materials.BoneTool);
		MithrilSet=new EquipmentSet(Materials.MithrilArmor,Materials.MithrilTool);
		AdamantiumSet=new EquipmentSet(Materials.AdamantiumArmor,Materials.AdamantiumTool);
		FlumetalSet=new EquipmentSet(Materials.FlumetalArmor,Materials.FlumetalTool);
		SpectreSet=new EquipmentSet(Materials.SpectreArmor,Materials.SpectreTool);
		DwartSteelSet=new EquipmentSet(Materials.DwartSteelArmor,Materials.DwartSteelTool);
		LifeWoodSet=new EquipmentSet(Materials.LifeWoodArmor,Materials.LifeWoodTool);
	}
	/** Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots */

	public static class EquipmentSet
	{
		public final ItemSword Sword;
		public final ItemPickaxe Pickaxe;
		public final ItemAxe Axe;
		public final ItemSpade Spade;
		public final ItemHoe Hoe;

		public final ItemArmor Helmet;
		public final ItemArmor Chestplate;
		public final ItemArmor Leggings;
		public final ItemArmor Boots;

		public final Ring Ring;
		public final Amulet Amulet;
		public final Belt Belt;

		public EquipmentSet(ItemArmor.ArmorMaterial am, Item.ToolMaterial tm)
		{
			Sword=new ItemSword(tm);
			Pickaxe=new ItemPickaxe(tm){};
			Axe=new ItemAxe(tm){};
			Spade=new ItemSpade(tm);
			Hoe=new ItemHoe(tm);

			Helmet=new ItemArmor(am, am.ordinal(), 0);
			Chestplate=new ItemArmor(am, am.ordinal(), 0);
			Leggings=new ItemArmor(am, am.ordinal(), 0);
			Boots=new ItemArmor(am, am.ordinal(), 0);
			Ring=new Ring();
			Amulet=new Amulet();
			Belt=new Belt();
		}
	}
	private static abstract class ItemBauble extends Item implements IBauble
	{
		@Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
		{
			if (!par2World.isRemote) {
				InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(par3EntityPlayer);

				for(int i = 0; i < baubles.getSizeInventory(); ++i) {
					if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, par1ItemStack)) {
						baubles.setInventorySlotContents(i, par1ItemStack.copy());
						if (!par3EntityPlayer.capabilities.isCreativeMode) {
							par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, (ItemStack)null);
						}

						this.onEquipped(par1ItemStack, par3EntityPlayer);
						break;
					}
				}
			}

			return par1ItemStack;
		}

		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			;
		}

		@Override
		public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			if (!entityLivingBase.worldObj.isRemote)
			{
				entityLivingBase.worldObj
						.playSoundAtEntity(entityLivingBase, "random.orb", 0.1F, 1.3F);
			}
		}

		@Override
		public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			;
		}

		@Override
		public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			return true;
		}

		@Override
		public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			return true;
		}
	}

	public static class Ring extends ItemBauble // 指环
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}
	public static class Amulet extends ItemBauble // 护身符
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.AMULET;
		}
	}
	public static class Belt extends ItemBauble // 腰带
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.BELT;
		}
	}
}
