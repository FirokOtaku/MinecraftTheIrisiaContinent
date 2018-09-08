package firok.irisia.item;

import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
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

		@Override
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
}
