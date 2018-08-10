package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;

public class EquipmentAutoRepair
{
	public final static AutoRepairSword LifewoodSword;
	public final static AutoRepairArmor LifewoodHelmet;
	public final static AutoRepairArmor LifewoodChestplate;
	public final static AutoRepairArmor LifewoodLeggings;
	public final static AutoRepairArmor LifewoodBoots;
	public final static AutoRepairPickaxe LifewoodPickaxe;
	public final static AutoRepairAxe LifewoodAxe;
	public final static AutoRepairHoe LifewoodHoe;
	public final static AutoRepairSpade LifewoodSpade;

	public final static AutoRepairArmor SlimeHelmet;
	public final static AutoRepairArmor SlimeChestplate;
	public final static AutoRepairArmor SlimeLeggings;
	public final static AutoRepairArmor SlimeBoots;

	static
	{
		LifewoodSword=new AutoRepairSword(Materials.LifeWoodTool,1,40);
		LifewoodHelmet=new AutoRepairArmor(Materials.LifeWoodArmor,0,1,40);
		LifewoodChestplate=new AutoRepairArmor(Materials.LifeWoodArmor,1,1,40);
		LifewoodLeggings=new AutoRepairArmor(Materials.LifeWoodArmor,2,1,40);
		LifewoodBoots=new AutoRepairArmor(Materials.LifeWoodArmor,3,1,40);
		LifewoodPickaxe=new AutoRepairPickaxe(Materials.LifeWoodTool,1,40);
		LifewoodAxe=new AutoRepairAxe(Materials.LifeWoodTool,1,40);
		LifewoodHoe=new AutoRepairHoe(Materials.LifeWoodTool,1,40);
		LifewoodSpade=new AutoRepairSpade(Materials.LifeWoodTool,1,40);

		SlimeHelmet=new SlimeArmor(Materials.SlimeArmor,0,2,40,1,40);
		SlimeChestplate=new SlimeArmor(Materials.SlimeArmor,1,2,40,2,40);
		SlimeLeggings=new SlimeArmor(Materials.SlimeArmor,2,2,40,1,40);
		SlimeBoots=new SlimeArmor(Materials.SlimeArmor,3,2,40,1,40);
	}

	public static class AutoRepairArmor extends ItemArmor
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairArmor(ItemArmor.ArmorMaterial am,int armorType)
		{
			super(am,am.ordinal(),armorType);
			repairSpeed=1;
			repairInterval=20;
		}
		public AutoRepairArmor(ItemArmor.ArmorMaterial am,int armorType,int rs,int ri)
		{
			super(am,am.ordinal(),armorType);
			repairSpeed=rs;
			repairInterval=ri;
		}

		public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
			return par2ItemStack.isItemEqual(new ItemStack(ConfigItems.itemResource, 1, 16)) ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
		}

		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}

		}

		@Override
		public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
			super.onArmorTick(world, player, armor);
			if (!world.isRemote && armor.getItemDamage() > 0 && player.ticksExisted % repairInterval == 0) {
				armor.damageItem(-repairSpeed, player);
			}

		}
	}

	public static class AutoRepairSword extends ItemSword
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairSword(ToolMaterial tm)
		{
			super(tm);
			repairSpeed=1;
			repairInterval=20;
		}
		public AutoRepairSword(ToolMaterial tm,int rs,int ri)
		{
			super(tm);
			repairSpeed=rs;
			repairInterval=ri;
		}
		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}

		}
	}

	public static class AutoRepairPickaxe extends ItemPickaxe
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairPickaxe(ToolMaterial tm)
		{
			super(tm);
			repairSpeed=1;
			repairInterval=20;
		}
		public AutoRepairPickaxe(ToolMaterial tm,int rs,int ri)
		{
			super(tm);
			repairSpeed=rs;
			repairInterval=ri;
		}
		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}

		}
	}
	public static class AutoRepairAxe extends ItemAxe
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairAxe(ToolMaterial tm)
		{
			super(tm);
			repairSpeed=1;
			repairInterval=20;
		}
		public AutoRepairAxe(ToolMaterial tm,int rs,int ri)
		{
			super(tm);
			repairSpeed=rs;
			repairInterval=ri;
		}
		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}

		}
	}
	public static class AutoRepairSpade extends ItemSpade
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairSpade(ToolMaterial tm)
		{
			super(tm);
			repairSpeed=1;
			repairInterval=20;
		}
		public AutoRepairSpade(ToolMaterial tm,int rs,int ri)
		{
			super(tm);
			repairSpeed=rs;
			repairInterval=ri;
		}
		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}

		}
	}
	public static class AutoRepairHoe extends ItemHoe
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairHoe(ToolMaterial tm)
		{
			super(tm);
			repairSpeed=1;
			repairInterval=20;
		}
		public AutoRepairHoe(ToolMaterial tm,int rs,int ri)
		{
			super(tm);
			repairSpeed=rs;
			repairInterval=ri;
		}
		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}

		}
	}

	// 史莱姆盔甲
	public static class SlimeArmor extends AutoRepairArmor
	{
		public final int heal;
		public final int healInterval;
		public SlimeArmor(ItemArmor.ArmorMaterial material,int type,int rs,int ri,int h,int hi)
		{
			super(material,type,rs,ri);
			this.heal =h;
			this.healInterval=hi;
		}

		@Override
		public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
			super.onArmorTick(world, player, armor);
			if (!world.isRemote && player.ticksExisted % healInterval == 0) {
				player.heal(heal);
			}
		}
	}

	// 饰品好像不会损坏 暂时没什么用
	public static abstract class AutoRepairBauble extends EquipmentSets.ItemBauble
	{
		public final int repairSpeed;
		public final int repairInterval;
		public AutoRepairBauble(int speed,int interval)
		{
			repairSpeed=speed;
			repairInterval=interval;
		}

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
		public void onWornTick(ItemStack stack, EntityLivingBase entity)
		{
			super.onWornTick(stack,entity);
			if (!entity.worldObj.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}
		}

		@Override
		public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
			super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
			if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % repairInterval == 0 && entity instanceof EntityLivingBase) {
				stack.damageItem(-repairSpeed, (EntityLivingBase)entity);
			}
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
	public static class AutoRepairRing extends EquipmentSets.ItemBauble
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}
	public static class AutoRepairBelt extends EquipmentSets.ItemBauble
	{

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.BELT;
		}
	}
	public static class AutoRepairAmulet extends EquipmentSets.ItemBauble
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.AMULET;
		}

		;
	}
}
