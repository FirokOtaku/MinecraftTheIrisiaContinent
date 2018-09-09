package firok.irisia.item;

import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import firok.irisia.entity.Throwables;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import thaumcraft.api.ThaumcraftApi;

import java.util.List;

public class Weapons
{
	public static final FlailWeapon FlailWood;
	public static final FlailWeapon FlailIron;
	public static final FlailWeapon FlailGold;
	public static final FlailWeapon FlailDiamond;
	public static final FlailWeapon FlailVoidMetal;
	public static final FlailWeapon FlailAdamantium;
	public static final FlailWeapon FlailMithril;
	public static final FlailWeapon FlailSolita;
	public static final FlailWeapon FlailMogiga;
	public static final FlailWeapon FlailBone;

	public static RunicLongBowWeapon ThaumiumRunicLongBow;
	public static RunicLongBowWeapon VoidRunicLongBow;
	public static RunicLongBowWeapon AdamantiumLongBow;
	public static RunicLongBowWeapon MithrilLongBow;
	public static RunicLongBowWeapon SolitaLongBow;
	public static RunicLongBowWeapon MogigaLongBow;

	static
	{
		FlailWood=new FlailWeapon(Item.ToolMaterial.WOOD);
		FlailIron=new FlailWeapon(Item.ToolMaterial.IRON);
		FlailGold=new FlailWeapon(Item.ToolMaterial.GOLD);
		FlailDiamond=new FlailWeapon(Item.ToolMaterial.EMERALD);
		FlailVoidMetal=new FlailWeapon(ThaumcraftApi.toolMatVoid);
		FlailAdamantium=new FlailWeapon(Materials.AdamantiumTool);
		FlailMithril=new FlailWeapon(Materials.MithrilTool);
		FlailMogiga=new FlailWeapon(Materials.MogigaTool);
		FlailSolita=new FlailWeapon(Materials.SolitaTool);
		FlailBone=new FlailWeapon(Materials.BoneTool);

		VoidRunicLongBow=new RunicLongBowWeapon();
	}

	public static class FlailWeapon extends ItemSword
	{
		public final float damageFactor;
		public final float flownFactor;
		public FlailWeapon(ToolMaterial material)
		{
			this(material,true);
		}
		public FlailWeapon(ToolMaterial material,boolean use)
		{
			this(material,
					use?(material.getDamageVsEntity()+1):2f,
					use?material.getEfficiencyOnProperMaterial()/8:0.8f);
		}
		public FlailWeapon(ToolMaterial material,float damage,float flown)
		{
			super(material);
			damageFactor=damage;
			flownFactor=flown;
		}

		@Override // todo 这里以后用这个替换掉 public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
		public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		{
			if(world.isRemote)
				return itemStack;

			List entities=world.getEntitiesWithinAABBExcludingEntity(player,
					AxisAlignedBB.getBoundingBox(
							player.posX-2,player.posY-2,player.posZ-2,
							player.posX+2,player.posY+2,player.posZ+2));
			for(Object obj : entities)
			{
				// Irisia.log(((Entity)obj).toString(),player);
				if(obj instanceof EntityLivingBase)
				{
					// Irisia.log("hit !",player);
					itemStack.damageItem(1,player);
					EntityLivingBase enlb=(EntityLivingBase)obj;
					enlb.attackEntityFrom(DamageSources.StoneDamage,damageFactor);
					enlb.motionX+=flownFactor*(enlb.posX-player.posX);
					enlb.motionY+=flownFactor*(enlb.posY-player.posY)*0.3;
					enlb.motionZ+=flownFactor*(enlb.posZ-player.posZ);
				}
			}

			return itemStack;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemStack)
		{
			return 12;
		}
	}
	public static class RunicLongBowWeapon extends ItemBow
	{
		@Override
		public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usedDuration)
		{
			int j = this.getMaxItemUseDuration(itemStack) - usedDuration;

			ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, j);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
			{
				return;
			}
			j = event.charge;

			// flag判断是否创造模式/无限附魔=是否消耗弹药
			boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;

			if (flag)//if (flag || player.inventory.hasItem(Items.arrow))
			{
				float f = (float)j / 20.0F;
				f = (f * f + f * 2.0F) / 3.0F;

				if ((double)f < 0.1D)
				{
					return;
				}

				if (f > 1.0F)
				{
					f = 1.0F;
				}

				Throwables.EntityRunicArrow entityarrow = new Throwables.EntityRunicArrow(world, player, 3);
				// thaumcraft:craftfail 爆炸音
				// thaumcraft:jacobs 电流音
				// todo 以后换成别的声音
				world.playSoundAtEntity(player, "thaumcraft:craftfail", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

				if (!world.isRemote)
				{
					world.spawnEntityInWorld(entityarrow);
				}
			}
		}
	}
}
