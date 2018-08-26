package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Wands
{
	public final static WandSet LifeWoodSet;
	public final static WandSet SpectreSet;
	public final static Item ItemNodeRod;
	public final static Rod NodeRod;
	static
	{
		LifeWoodSet=new WandSet("lifewood",150,10,20,20);
		SpectreSet=new WandSet("spectre",200,15,30,30);

		// String mn,int capacity,ItemStack itemStackRod,int craftCost,
		// IWandRodOnUpdate onUpdate, boolean needResearch, @Nullable String researchNeeded
		// FIXME 这里的itemstack以后要换成对的
		ItemNodeRod=new ItemRod();
		NodeRod =new Rod("node", 60, new ItemStack(ItemNodeRod), 50,
				new IWandRodOnUpdate()
				{
					final Aspect[] aspects=new Aspect[]
							{Aspect.FIRE, Aspect.WATER, Aspect.AIR,
							Aspect.EARTH, Aspect.ORDER, Aspect.ENTROPY};
					@Override
					public void onUpdate(ItemStack itemstack, EntityPlayer player)
					{
						if(player.worldObj.isRemote)
							return;

						if(player.ticksExisted%60==0)
							for(Aspect as:aspects)
							{
								if(((ItemWandCasting)itemstack.getItem())
										.getVis(itemstack, as) <
										((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
								((ItemWandCasting)itemstack.getItem()).addVis(itemstack, as, 1, true);

							}
					}
				},
		false,null);

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
		public Rod(String mn,int capacity,ItemStack itemStackRod,int craftCost)
		{
			this(mn,capacity,itemStackRod,craftCost,false,null);
		}
		public Rod(String mn, int capacity, ItemStack itemStackRod, int craftCost, boolean needResearch, @Nullable String researchNeeded)
		{
			super(mn,capacity,itemStackRod,craftCost);
			this.needResearch=needResearch;
			this.researchNeeded=researchNeeded;
		}
		public Rod(String mn,int capacity,ItemStack itemStackRod,int craftCost,IWandRodOnUpdate onUpdate)
		{
			super(mn,capacity,itemStackRod,craftCost,onUpdate);
			this.needResearch=false;
			this.researchNeeded=null;
		}
		public Rod(String mn,int capacity,ItemStack itemStackRod,int craftCost,IWandRodOnUpdate onUpdate, boolean needResearch, @Nullable String researchNeeded)
		{
			super(mn,capacity,itemStackRod,craftCost,onUpdate);
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
