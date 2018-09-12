package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EquipmentSets
{
	public final static EquipmentSet BoneSet;
	public final static EquipmentSet MithrilSet;
	public final static EquipmentSet AdamantiumSet;
	public final static EquipmentSet FlumetalSet;
	public final static EquipmentSet SpectreSet;
	public final static EquipmentSet DwartSteelSet;
	public final static EquipmentSet LifeWoodSet; // 已经转移
	public final static EquipmentSet SolitaSet;
	public final static EquipmentSet MogigaSet;

	public final static ArmorSet WolfFurSet;
	public final static ArmorSet IcyWolfFurSet;

	public final static EffectArmorSet WindRangerSet;
	public final static EffectArmorSet DwartMinerSet;
	public final static EffectArmorSet GarrisonSet;
	public final static EffectArmorSet NinjiaSet;

	static
	{
		BoneSet=new EquipmentSet("bone",Materials.BoneArmor,Materials.BoneTool);
		MithrilSet=new EquipmentSet("mithril",Materials.MithrilArmor,Materials.MithrilTool);
		AdamantiumSet=new EquipmentSet("adamantium",Materials.AdamantiumArmor,Materials.AdamantiumTool);
		FlumetalSet=new EquipmentSet("flumetal",Materials.FlumetalArmor,Materials.FlumetalTool);
		SpectreSet=new EquipmentSet("spectre",Materials.SpectreArmor,Materials.SpectreTool);
		DwartSteelSet=new EquipmentSet("dwartsteel",Materials.DwartSteelArmor,Materials.DwartSteelTool);
		LifeWoodSet=new EquipmentSet("lifewood",Materials.LifeWoodArmor,Materials.LifeWoodTool,false,false,false,true); // 转移到 EquipmentAutoRepair
		SolitaSet=new EquipmentSet("solita",Materials.SolitaArmor,Materials.SolitaTool);
		MogigaSet=new EquipmentSet("mogiga",Materials.MogigaArmor,Materials.MogigaTool);

		WolfFurSet=new ArmorSet("wolffur",Materials.WolfFurArmor);
		IcyWolfFurSet=new ArmorSet("icywolffur",Materials.IcyWolfFurArmor);

		WindRangerSet=new EffectArmorSet("windranger",ItemArmor.ArmorMaterial.CLOTH);
		DwartMinerSet=new EffectArmorSet("dwartminer",ItemArmor.ArmorMaterial.IRON);
		GarrisonSet=new EffectArmorSet("garrison",ItemArmor.ArmorMaterial.CHAIN);
		NinjiaSet=new EffectArmorSet("ninjia",ItemArmor.ArmorMaterial.CLOTH);
	}
	/** Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots */

	public static class EquipmentSet
	{
		public final ItemArmor.ArmorMaterial armorMaterial;
		public final Item.ToolMaterial toolMaterial;

		public final boolean hasWeapon;
		public final ItemSword Sword;

		public final boolean hasTools;
		public final ItemPickaxe Pickaxe;
		public final ItemAxe Axe;
		public final ItemSpade Spade;
		public final ItemHoe Hoe;

		public final boolean hasArmor;
		public final ItemArmor Helmet;
		public final ItemArmor Chestplate;
		public final ItemArmor Leggings;
		public final ItemArmor Boots;

		public final boolean hasBaubles;
		public final Ring Ring;
		public final Amulet Amulet;
		public final Belt Belt;

		public final String materialName;

		public EquipmentSet(String mn,ItemArmor.ArmorMaterial am, Item.ToolMaterial tm)
		{
			this(mn,am,tm,true,true,true,true);
		}
		public EquipmentSet(String mn,ItemArmor.ArmorMaterial am, Item.ToolMaterial tm,boolean hasW,boolean hasT,boolean hasA,boolean hasB)
		{
			materialName=mn;
			armorMaterial=am;
			toolMaterial=tm;

			if(hasWeapon=hasW)
				Sword=new ItemSword(tm);
			else
				Sword=null;

			if(hasTools=hasT)
			{
				Pickaxe=new ItemPickaxe(tm){};
				Axe=new ItemAxe(tm){};
				Spade=new ItemSpade(tm);
				Hoe=new ItemHoe(tm);
			}
			else
			{
				Pickaxe=null;
				Axe=null;
				Spade=null;
				Hoe=null;
			}

			if(hasArmor=hasA)
			{
				Helmet=new ItemArmor(am, am.ordinal(), 0);
				Chestplate=new ItemArmor(am, am.ordinal(), 1);
				Leggings=new ItemArmor(am, am.ordinal(), 2);
				Boots=new ItemArmor(am, am.ordinal(), 3);
			}
			else
			{
				Helmet=null;
				Chestplate=null;
				Leggings=null;
				Boots=null;
			}

			if(hasBaubles=hasB)
			{
				Ring=new Ring();
				Amulet=new Amulet();
				Belt=new Belt();
			}
			else
			{
				Ring=null;
				Amulet=null;
				Belt=null;
			}
		}
	}

	public static class ArmorSet
	{
		public final String materialName;
		public final ItemArmor.ArmorMaterial armorMaterial;
		public final ItemArmor Helmet;
		public final ItemArmor Chestplate;
		public final ItemArmor Leggings;
		public final ItemArmor Boots;

		public ArmorSet(String mn,ItemArmor.ArmorMaterial armorMaterial)
		{
			this.armorMaterial=armorMaterial;
			Helmet=new ItemArmor(armorMaterial, armorMaterial.ordinal(), 0);
			Chestplate=new ItemArmor(armorMaterial, armorMaterial.ordinal(), 1);
			Leggings=new ItemArmor(armorMaterial, armorMaterial.ordinal(), 2);
			Boots=new ItemArmor(armorMaterial, armorMaterial.ordinal(), 3);
			this.materialName=mn;
		}
		protected ArmorSet(String mn,
		                   ItemArmor.ArmorMaterial armorMaterial,
		                   ItemArmor helmet,ItemArmor chestplate,
		                   ItemArmor leggings,ItemArmor boots)
		{
			this.materialName=mn;
			this.armorMaterial=armorMaterial;
			this.Helmet=helmet;
			this.Chestplate=chestplate;
			this.Leggings=leggings;
			this.Boots=boots;
		}
	}
	public static class EffectArmorSet extends ArmorSet
	{
		public EffectArmorSet(String mn,ItemArmor.ArmorMaterial armorMaterial)
		{
			super(mn,armorMaterial,
					new EffectArmorPart(armorMaterial, 0),
					new EffectArmorPart(armorMaterial, 1),
					new EffectArmorPart(armorMaterial, 2),
					new EffectArmorPart(armorMaterial, 3));
			((EffectArmorPart)this.Helmet).set=this;
			((EffectArmorPart)this.Chestplate).set=this;
			((EffectArmorPart)this.Leggings).set=this;
			((EffectArmorPart)this.Boots).set=this;
		}
		public static class EffectArmorPart extends ItemArmor
		{
			public EffectArmorSet set;
			public EffectArmorPart(ItemArmor.ArmorMaterial armorMaterial,int type)
			{
				super(armorMaterial,armorMaterial.ordinal(),type);
			}
		}
	}


	public static abstract class ItemBauble extends Item implements IBauble
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
/*public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
        if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % 20 == 0 && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase)entity);
        }

    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        super.onArmorTick(world, player, armor);
        if (!world.isRemote && armor.getItemDamage() > 0 && player.ticksExisted % 20 == 0) {
            armor.damageItem(-1, player);
        }

    }*/