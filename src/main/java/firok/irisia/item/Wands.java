package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Wands
{
	public final static Rod AdvancedRodObsidian;
	public final static ItemRod ItemAdvancedRodObsidian;
	public final static Rod AdvancedRodBone;
	public final static ItemRod ItemAdvancedRodBone;
	public final static Rod AdvancedRodBlaze;
	public final static ItemRod ItemAdvancedRodBlaze;
	public final static Rod AdvancedRodIce;
	public final static ItemRod ItemAdvancedRodIce;
	public final static Rod AdvancedRodQuartz;
	public final static ItemRod ItemAdvancedRodQuartz;
	public final static Rod AdvancedRodReed;
	public final static ItemRod ItemAdvancedRodReed;

	public final static WandSet LifeWoodSet;
	public final static WandSet SpectreSet;

	public final static WandSet AdamantiumSet;
	public final static WandSet MithrilSet;

	public final static WandSet SolitaSet;
	public final static WandSet MogigaSet;

	public final static Item ItemNodeRod;
	public final static Rod NodeRod;
	public final static Item ItemCreativeRod;
	public final static Rod CreativeRod;
	static
	{
		ItemAdvancedRodObsidian=new ItemRod();
		ItemAdvancedRodBone=new ItemRod();
		ItemAdvancedRodBlaze=new ItemRod();
		ItemAdvancedRodIce=new ItemRod();
		ItemAdvancedRodQuartz=new ItemRod();
		ItemAdvancedRodReed=new ItemRod();
		AdvancedRodObsidian=new Rod("arobsidian",80,new ItemStack(ItemAdvancedRodObsidian),30)
		{
			@Override
			public void setOnUpdate(IWandRodOnUpdate onUpdate)
			{
				super.setOnUpdate(onUpdate);
			}
		};

		AdvancedRodBone=new Rod("arbone",80,new ItemStack(ItemAdvancedRodBone),30);
		AdvancedRodBlaze=new Rod("arblaze",80,new ItemStack(ItemAdvancedRodBlaze),30);
		AdvancedRodIce=new Rod("arice",80,new ItemStack(ItemAdvancedRodIce),30);
		AdvancedRodQuartz=new Rod("arquartz",80,new ItemStack(ItemAdvancedRodQuartz),30);
		AdvancedRodReed=new Rod("arreed",80,new ItemStack(ItemAdvancedRodReed),30);


		LifeWoodSet=new WandSet("lifewood",150,10,20,20);
		SpectreSet=new WandSet("spectre",200,15,30,30);
		//String mn,int capacity,float discount,
		//		               List<Aspect> specials,float discountSpecial,
		//		               int craftCostRod,int craftCostCap,IWandRodOnUpdate onUpdate
		// TODO 这里以后可以优化
		AdamantiumSet=new WandSet("adamantium",150,1f,
				new ArrayList<Aspect>(),1f,100,100);
		MithrilSet=new WandSet("mithril",150,1f,
				new ArrayList<Aspect>(),1f,100,100);
		SolitaSet=new WandSet("solita",150,1f,
				new ArrayList<Aspect>(),1f,100,100);
		MogigaSet=new WandSet("mogiga",150,1f,
				new ArrayList<Aspect>(),1f,100,100);

		// String mn,int capacity,ItemStack itemStackRod,int craftCost,
		// IWandRodOnUpdate onUpdate, boolean needResearch, @Nullable String researchNeeded
		// FIXME 这里的itemstack以后要换成对的
		ItemNodeRod=new ItemRod();
		NodeRod =new Rod("node", 60, new ItemStack(ItemNodeRod), 50,
				(itemstack,player)->{
					if(player.worldObj.isRemote)
						return;

					if(player.ticksExisted%60==0)
					for(Aspect as:Irisia.arrayPrimalAspect)
					{
						if(((ItemWandCasting)itemstack.getItem())
								.getVis(itemstack, as) <
								((ItemWandCasting)itemstack.getItem()).getMaxVis(itemstack))
							((ItemWandCasting)itemstack.getItem()).addVis(itemstack, as, 1, true);

					}
				},false,null);
		ItemCreativeRod=new ItemRod(){
			@Override
			public void addInformation(ItemStack itemstack,EntityPlayer player,List list,boolean flag)
			{
				list.add("Creative Only");
			}
		};

		CreativeRod=new Rod("creative", 10000, new ItemStack(ItemCreativeRod), 0,
				(itemstack,player)->{
					if(player.worldObj.isRemote)
						return;

					if(player.ticksExisted%80==0)
					{
						ItemWandCasting wc=(ItemWandCasting)itemstack.getItem();
						for (Aspect as : Irisia.arrayPrimalAspect)
						{
							int visNow=wc.getVis(itemstack,as);
							if(visNow<10000)
							{
								wc.addVis(itemstack,as,10000-visNow-1,true);
							}
						}
					}
				},false,null);
	}

	public final static Item ItemWhitebeardStaffRod;
	public final static StaffRod WhitebeardStaffRod;
	public final static Item ItemBlackbeardStaffRod;
	public final static StaffRod BlackbeardStaffRod;
	public final static Item ItemGraybeardStaffRod;
	public final static StaffRod GraybeardStaffRod;
	static
	{
		ItemWhitebeardStaffRod =new ItemRod();
		WhitebeardStaffRod =new StaffRod("white_beard",150,new ItemStack(ItemWhitebeardStaffRod),30);
		ItemBlackbeardStaffRod =new ItemRod();
		BlackbeardStaffRod =new StaffRod("black_beard",150,new ItemStack(ItemBlackbeardStaffRod),30);
		ItemGraybeardStaffRod=new ItemRod();
		GraybeardStaffRod=new StaffRod("gray_beard",150,new ItemStack(ItemGraybeardStaffRod),30);
	}

	public static class WandSet
	{
		public final ItemRod Rod;
		public final ItemCap Cap;
		public final String materialName;
		public final WandRod wandRod;
		public final WandCap wandCap;
		public WandSet(String mn,int capacity,float discount,int craftCostRod,int craftCostCap)
		{
			this(mn,capacity,discount,new ArrayList<Aspect>(),1F,craftCostRod,craftCostCap);
		}
		public WandSet(String mn,int capacity,float discount,
		               List<Aspect> specials,float discountSpecial,
		               int craftCostRod,int craftCostCap)
		{
			this(mn,capacity,discount,specials,discountSpecial,craftCostRod,craftCostCap,null);
		}
		public WandSet(String mn,int capacity,float discount,
		               List<Aspect> specials,float discountSpecial,
		               int craftCostRod,int craftCostCap,IWandRodOnUpdate onUpdate)
		{
			materialName=mn;
			Rod=new ItemRod();
			Cap=new ItemCap();

			wandRod=onUpdate==null?
					new Rod(mn,capacity, new ItemStack(Rod),craftCostRod):
					new Rod(mn,capacity, new ItemStack(Rod),craftCostRod,onUpdate);

			wandCap=new Cap(mn,discount, specials, discountSpecial,
					new ItemStack(Cap), craftCostCap);
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

		private IIcon wand_icon;
		public ItemRod()
		{
			super();
			this.setHasSubtypes(false);
		}
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
			;
		}

		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister ir) {
			wand_icon = ir.registerIcon(this.iconString);
		}
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int meta) {
			return wand_icon==null?
					( meta < 50 ? this.iconWand[meta] : (meta < 100 ? this.iconStaff[meta - 50] : this.iconPrimalStaff))
					:wand_icon;
		}
	}
	public static class ItemCap extends ItemWandCap
	{
		IIcon rod_icon;
		public ItemCap()
		{
			super();
			this.setHasSubtypes(false);
		}
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
			;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister ir) {
			rod_icon = ir.registerIcon(iconString);
		}
		@Override
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int meta) {
			return rod_icon==null?this.icon[meta]:rod_icon;
		}
	}
}
