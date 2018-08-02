package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;
import thaumcraft.common.items.wands.*;

import java.util.List;

public class Wands
{
	// public final static thaumcraft.common.items.wands.ItemFocusPouch // 核心手袋
	public final static WandSet LifeWoodSet;
	public final static WandSet SpectreSet;
	static
	{
		LifeWoodSet=new WandSet("lifewood",150,10,20,20);
		SpectreSet=new WandSet("spectre",200,15,30,30);
	}
	public static class WandSet
	{
		public final Rod Rod;
		public final Cap Cap;
		public final String materialName;
		public final WandRod wandRod;
		public final WandCap wandCap;
		public WandSet(String mn,int capacity,int discount,int craftCostRod,int craftCostCap)
		{
			materialName=mn;
			Rod=new Rod();
			Cap=new Cap();
			wandRod=new WandRod(mn,capacity, new ItemStack(Rod),craftCostRod);
			wandCap=new WandCap(mn,discount, new ItemStack(Cap), craftCostCap);
		}
	}

	public static class Rod extends ItemWandRod
	{
		public Rod()
		{
			super();
			this.setHasSubtypes(false);
		}
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int meta) {
			return meta < 50 ? this.iconWand[meta] : (meta < 100 ? this.iconStaff[meta - 50] : this.iconPrimalStaff);
		}

		@SideOnly(Side.CLIENT)
		public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
			;
		}

	}
	public static class Cap extends ItemWandCap
	{
		public Cap()
		{
			super();

			this.setHasSubtypes(false);
		}

		@SideOnly(Side.CLIENT)
		public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
			;
		}
	}

	public static void run()
	{
		//thaumcraft.common.items.wands.ItemWandCap
	}
}
