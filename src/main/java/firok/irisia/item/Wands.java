package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.*;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

public class Wands
{
	public final static WandSet LifeWoodSet;
	public final static WandSet SpectreSet;
	static
	{
		LifeWoodSet=new WandSet("lifewood",150,10,20,20);
		SpectreSet=new WandSet("spectre",200,15,30,30);
	}
	public static class WandSet
	{
		public final ItemRod Rod;
		public final ItemCap Cap;
		public final String materialName;
		public final WandRod wandRod;
		public final WandCap wandCap;
		public WandSet(String mn,int capacity,int discount,int craftCostRod,int craftCostCap)
		{
			materialName=mn;
			Rod=new ItemRod();
			Cap=new ItemCap();

			wandRod=new Rod(mn,capacity, new ItemStack(Rod),craftCostRod);
			wandCap=new Cap(mn,discount, new LinkedList<Aspect>(), 0.3F,
					new ItemStack(Cap), 10);
		}
	}
	public static class Rod extends WandRod
	{
		private boolean needResearch;
		private String researchNeeded;
		public Rod(String mn,int discount,ItemStack itemStackRod,int craftCost)
		{
			this(mn,discount,itemStackRod,craftCost,false,null);
		}
		public Rod(String mn, int discount, ItemStack itemStackRod, int craftCost, boolean needResearch, @Nullable String researchNeeded)
		{
			super(mn,discount,itemStackRod,craftCost);
			this.needResearch=needResearch;
			this.researchNeeded=researchNeeded;
		}
		@Override
		public String getResearch() {
			return needResearch&&researchNeeded!=null?researchNeeded:"ROD_wood";
		}
	}
	public static class Cap extends WandCap
	{
		private boolean needResearch;
		private String researchNeeded;
		public Cap (String tag, float discount, List<Aspect> specialAspects, float discountSpecial, ItemStack item, int craftCost) {
			super(tag,discount,specialAspects,discountSpecial,item,craftCost);
			needResearch=false;
			researchNeeded=null;
		}
		@Override
		public String getResearch() {
			return needResearch&&researchNeeded!=null?researchNeeded:"CAP_iron";
		}
	}
	public static class ItemRod extends ItemWandRod
	{
		private boolean needResearch;
		private String researchNeeded;
		public ItemRod()
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
	public static class ItemCap extends ItemWandCap
	{
		public ItemCap()
		{
			super();

			this.setHasSubtypes(false);
		}
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
			;
		}
	}
}
