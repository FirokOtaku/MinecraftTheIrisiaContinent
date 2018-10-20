package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.block.OresAndMetal;
import firok.irisia.block.SpecialDecorations;
import firok.irisia.entity.Summons;
import firok.irisia.entity.Throwables;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;

import java.awt.*;

public class Focus
{
	public static final AspectList defaultNoCost=new AspectList();
	//focus.upgrade.potency.name=潜能
	//focus.upgrade.potency.text=这种升级能够提升法杖核心的威力或伤害.
	// 额外的伤害效果视核心类型而有所差异,但一般来说增幅为每级20%.
	//focus.upgrade.frugal.name=朴素
	//focus.upgrade.frugal.text=这种升级能够减免使用法杖核心时的部分魔力消耗,
	// 每级减免20%.
	//focus.upgrade.treasure.name=瑰宝
	//focus.upgrade.treasure.text=这种升级效果类似时运或掠夺附魔,
	// 战利品将来得又好又多.
	//focus.upgrade.enlarge.name=增广
	//focus.upgrade.enlarge.text=这种升级能够增加法杖核心作用的范围,
	// 或者增加其影响的目标数量.
	//focus.upgrade.extend.name=固本
	//focus.upgrade.extend.text=这种升级能够增加升级可能造成的各种效果的持续时间.
	//focus.upgrade.architect.name=匠心
	//focus.upgrade.architect.text=这种升级能够使得某些法杖核心的效果范围更具可控性.额外的生效范围将会被显示出来,而且你可以使用法杖控制快捷键(默认为G)来切换其大小,同时按下潜行则可以调整你每次能够切换多大范围.

	// 核心-蛛后 岩浆
	public static final FocusUpgradeType ugtLava=new FocusUpgradeType(401,
			new ResourceLocation("irisia","textures/foci/lava.png"),
			"focus.upgrade.lava.name",
			"focus.upgrade.lava.text",
			(new AspectList()).add(Aspect.FIRE, 1));
	// 核心-蛛后 土黄
	public static final FocusUpgradeType ugtDirty=new FocusUpgradeType(402,
			new ResourceLocation("irisia","textures/foci/dirty.png"),
			"focus.upgrade.dirty.name",
			"focus.upgrade.dirty.text",
			(new AspectList()).add(Aspect.EARTH, 1));
	// 核心-蛛后 毒性
	public static final FocusUpgradeType ugtPoisonous=new FocusUpgradeType(403,
			new ResourceLocation("irisia","textures/foci/poisonous.png"),
			"focus.upgrade.poisonous.name",
			"focus.upgrade.poisonous.text",
			(new AspectList()).add(Aspect.ENTROPY, 1));
	// todo 机械炼成的升级 因为不知道还有什么金属 以后可能开放出去
	// 核心-机械炼成 神秘升级
	public static final FocusUpgradeType ugtThaumium=new FocusUpgradeType(404,
			new ResourceLocation("irisia","textures/foci/thaumium.png"),
			"focus.upgrade.thaumium.name",
			"focus.upgrade.thaumium.text",
			(new AspectList()).add(Aspect.ORDER, 1));
	// 核心-机械炼成 虚空升级
	public static final FocusUpgradeType ugtVoid=new FocusUpgradeType(405,
			new ResourceLocation("irisia","textures/foci/void.png"),
			"focus.upgrade.void.name",
			"focus.upgrade.void.text",
			(new AspectList()).add(Aspect.ORDER, 1));
	// 核心-机械炼成 索利塔升级
	public static final FocusUpgradeType ugtSolita=new FocusUpgradeType(406,
			new ResourceLocation("irisia","textures/foci/solita.png"),
			"focus.upgrade.solita.name",
			"focus.upgrade.solita.text",
			(new AspectList()).add(Aspect.ORDER, 1));

	public final static FocusBase Test=new FocusBase(new AspectList().add(Aspect.WATER,1).add(Aspect.FIRE,1), "FT", null, Color.white.getRGB(),
			1000, true)
	{
		@Override
		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
		{
			if(!world.isRemote)
			{
				Irisia.log("focus right click "+System.currentTimeMillis(),player);
				Irisia.log(itemstack.toString(),player);
				if(itemstack.hasTagCompound())
				{
					Irisia.log(itemstack.getTagCompound().toString());
				}
			}
			return itemstack;
		}

		@Override
		public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count)
		{
			if(!p.worldObj.isRemote)
			{
				Irisia.log("using focus tick : count - "+count+" time -"+System.currentTimeMillis(),p);
				Irisia.log(stack.toString(),p);
				if(stack.hasTagCompound())
				{
					Irisia.log(stack.getTagCompound().toString(),p);
				}
			}
		}

		@Override
		public void onPlayerStoppedUsingFocus(ItemStack itemstack, World world, EntityPlayer player, int count)
		{
			if(!world.isRemote)
			{
				Irisia.log("player stopped using focus count:"+count+" , time:"+System.currentTimeMillis(),player);
				Irisia.log(itemstack.toString(),player);
				if(itemstack.hasTagCompound())
				{
					Irisia.log(itemstack.getTagCompound().toString());
				}
			}
		}

		@Override
		public boolean onFocusBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				Irisia.log("focus block start break "+System.currentTimeMillis(),player);
				Irisia.log(itemstack.toString(),player);
				if(itemstack.hasTagCompound())
				{
					Irisia.log(itemstack.getTagCompound().toString());
				}
			}
			return false;
		}};
	// 法杖核心-蛛后
	public final static FocusBase SpiderQueen=new FocusBase(defaultNoCost,"FSQ",null,Color.WHITE.getRGB(),
			500,true)
	{
		private final AspectList costBase=(new AspectList().add(Aspect.AIR,30));
		private final AspectList costLava=(new AspectList().add(Aspect.FIRE,30));
		private final AspectList costDirty=(new AspectList().add(Aspect.EARTH,30));
		private final AspectList costPoisonous=(new AspectList().add(Aspect.ENTROPY,30));

		@Override
		public AspectList getVisCost(ItemStack stack)
		{
			if(this.isUpgradedWith(stack,ugtDirty))
				return costDirty;
			else if(this.isUpgradedWith(stack,ugtLava))
				return costLava;
			else if(this.isUpgradedWith(stack,ugtPoisonous))
				return costPoisonous;
			else
				return costBase;
		}
		@Override
		public int getActivationCooldown(ItemStack focusstack)
		{ // todo 以后会随着升级改变cd
			return wandCooldown;
		}

		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop)
		{ // todo low 现在没法升级
			ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();

			if(!world.isRemote)
			{
				if(mop!=null)
				{
					int x=mop.blockX,y=mop.blockY,z=mop.blockZ;
					if(p.getDistance(x,y,z)<20)
					{
						ItemStack focustack=wand.getFocusItem(itemstack);
						if (wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), !p.worldObj.isRemote, false))
						{
							Block b2set;
							if(isUpgradedWith(focustack,ugtDirty))
								b2set=SpecialDecorations.DirtyCobweb;
							else if(isUpgradedWith(focustack,ugtPoisonous))
								b2set=SpecialDecorations.PoisonousCobweb;
							else if(isUpgradedWith(focustack,ugtLava))
								b2set=SpecialDecorations.LavaCobweb;
							else
								b2set=Blocks.web;

							int radius=2+getUpgradeLevel(focustack,FocusUpgradeType.enlarge);

							for(int x2=-radius;x2<=radius;x2++)
							{
								for(int y2=-radius;y2<=radius;y2++)
								{
									for(int z2=-radius;z2<=radius;z2++)
									{
										if(world.rand.nextFloat()<0.4&&
												world.getBlock(x+x2,y+y2,z+z2)==Blocks.air)
										{
											world.setBlock(x+x2,y+y2,z+z2,b2set);
										}
									}
								}
							}
							p.worldObj.playSoundAtEntity(p,"mob.spider.say",1,1);
							p.swingItem();
						}
					}
				}
				else // mop == null
				{
					Irisia.tellPlayer("mop==null!",p);
				}
			}

			return itemstack;
		}

		public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
		{ // todo low 以后改
			return !type.equals(FocusUpgradeType.enlarge) ||
					this.isUpgradedWith(focusstack, ugtLava) ||
					this.isUpgradedWith(focusstack, ugtPoisonous)||
					this.isUpgradedWith(focusstack, ugtDirty);
		}

		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
		{ // todo low 以后改
			switch(rank)
			{
				case 1:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 2:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, ugtDirty, ugtLava, ugtPoisonous};
				case 3:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 4:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				case 5:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				default:
					return null;
			}
		}

	};
	// 法杖核心-凋零
	public final static FocusBase Wither=new FocusBase(defaultNoCost,"FW",null,Color.black.getRGB(),
			500,true)
	{
		private final AspectList costBase=(new AspectList().add(Aspect.ENTROPY,60));
		@Override
		public AspectList getVisCost(ItemStack stack)
		{
			return costBase;
		}

		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop)
		{ // todo low 现在没法升级
			ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();

			if(!world.isRemote)
			{
				ItemStack focustack=wand.getFocusItem(itemstack);
				if (wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), !p.worldObj.isRemote, false))
				{
					// todo 以后size跟升级联系起来
					Throwables.EntityWitherOrb orb=new Throwables.EntityWitherOrb(world,p,1);
					world.spawnEntityInWorld(orb);
					// fixme 以后改正这里的音效
					p.worldObj.playSoundAtEntity(p,"mob.spider.say",1,1);
					p.swingItem();
				}
			}

			return itemstack;
		}

		public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
		{ // todo low 以后改
			return !type.equals(FocusUpgradeType.enlarge);
		}

		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
		{ // todo low 以后改
			switch(rank)
			{
				case 1:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 2:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 3:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 4:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				case 5:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				default:
					return null;
			}
		}

	};
	// 法杖核心-机械炼成
	public final static FocusBase MachineryAlchemy=new FocusBase(defaultNoCost,"FMA",null,Color.ORANGE.getRGB(),
			3000,true)
	{
		private final AspectList costIron=(new AspectList().add(Aspect.ORDER,2000).add(Aspect.ENTROPY,2000).add(Aspect.FIRE,1000).add(Aspect.EARTH,1000));
		private final AspectList costThaumium=(new AspectList().add(Aspect.ORDER,2000).add(Aspect.ENTROPY,2000).add(Aspect.FIRE,1000).add(Aspect.EARTH,1000));
		private final AspectList costVoid=(new AspectList().add(Aspect.ORDER,2000).add(Aspect.ENTROPY,2000).add(Aspect.FIRE,1000).add(Aspect.EARTH,1000));
		private final AspectList costSolita=(new AspectList().add(Aspect.ORDER,2000).add(Aspect.ENTROPY,2000).add(Aspect.FIRE,1000).add(Aspect.EARTH,1000));

		@Override
		public AspectList getVisCost(ItemStack stack)
		{
			if(this.isUpgradedWith(stack,ugtThaumium))
				return costThaumium;
			else if(this.isUpgradedWith(stack,ugtVoid))
				return costVoid;
			else if(this.isUpgradedWith(stack,ugtSolita))
				return costSolita;
			else
				return costIron;
		}


		@Override
		public int getActivationCooldown(ItemStack focusstack)
		{ // todo 以后会随着升级改变cd
			return wandCooldown;
		}

		public boolean removeMetal(EntityPlayer player,Block metal,int need,boolean doit) // 从TC4学习一个
		{
			if(metal==null||need==0)return true;
			boolean hasEnough=false;
			InventoryPlayer inv=player.inventory;
			int count=0;
			for(int i=0;i<inv.getSizeInventory();i++)
			{
				ItemStack stackInInv=inv.getStackInSlot(i);
				if(stackInInv==null) continue;
				Item itemInStack=stackInInv.getItem();
				if(!(itemInStack instanceof ItemBlock)) continue;
				Block blockInStack=((ItemBlock)itemInStack).field_150939_a;
				if(blockInStack!=metal) continue;

				count+=stackInInv.stackSize;
				if(count>=need)
				{
					hasEnough=true;
					break;
				}
			}
			if(doit)
			{
				count=need;
				for(int i=0;count>0&&i<inv.getSizeInventory();i++)
				{
					ItemStack stackInInv=inv.getStackInSlot(i);
					if(stackInInv==null) continue;
					Item itemInStack=stackInInv.getItem();
					if(!(itemInStack instanceof ItemBlock)) continue;
					Block blockInStack=((ItemBlock)itemInStack).field_150939_a;
					if(blockInStack!=metal) continue;

					if(count>stackInInv.stackSize)
					{
						count-=stackInInv.stackSize;
						inv.setInventorySlotContents(i,null);
					}
					else
					{
						stackInInv.stackSize-=count;
						if(stackInInv.stackSize==0)
							inv.setInventorySlotContents(i,null);
						count=0;
					}
				}
				inv.markDirty();
			}
			return hasEnough;
		}

		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop)
		{ // todo low 现在没法升级
			ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();

			if(!world.isRemote)
			{
				ItemStack focustack=wand.getFocusItem(itemstack);


				EntityLivingBase e2spawn;
				Block m2remove;
				int c2remove=5;

				if(isUpgradedWith(focustack,ugtSolita))
				{
					e2spawn=new Summons.SummonedSolitaGolem(world,p.posX,p.posY,p.posZ);
					m2remove=OresAndMetal.BlockSolita;
				}
				else if(isUpgradedWith(focustack,ugtVoid))
				{
					e2spawn=new Summons.SummonedVoidGolem(world,p.posX,p.posY,p.posZ);
					m2remove=null;
				}
				else if(isUpgradedWith(focustack,ugtThaumium))
				{
					e2spawn=new Summons.SummonedThaumiumGolem(world,p.posX,p.posY,p.posZ);
					m2remove=null;
				}
				else
				{
					e2spawn=new Summons.SummonedIronGolem(world,p.posX,p.posY,p.posZ);
					m2remove=Blocks.iron_block;
				}


				if(removeMetal(p,m2remove,c2remove,false))
				{ // todo 以后这里写个直接移除金属块而且不检查数量的方法
					// note 或者在形参里加上个skipCheck
					if (removeMetal(p,m2remove,c2remove,true)&&wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), !p.worldObj.isRemote, false))
					{
						removeMetal(p,m2remove,c2remove,true);
						world.spawnEntityInWorld(e2spawn);
					}
				}

			}

			return itemstack;
		}

		public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
		{ // todo low 以后改
			return !type.equals(FocusUpgradeType.enlarge) ||
					this.isUpgradedWith(focusstack, ugtThaumium) ||
					this.isUpgradedWith(focusstack, ugtVoid)||
					this.isUpgradedWith(focusstack, ugtSolita);
		}

		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
		{ // todo low 以后改
			switch(rank)
			{
				case 1:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 2:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 3:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 4:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				case 5:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				default:
					return null;
			}
		}
	};
	// 法杖核心-奥术护罩
	public final static FocusBase ArcaneShield=new FocusBase(defaultNoCost,"FAS",null,Color.cyan.getRGB(),
			2000,true)
	{
		private final AspectList costBase=(new AspectList().add(Aspect.ORDER,200));

		@Override
		public AspectList getVisCost(ItemStack stack)
		{
			return costBase;
		}

		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop)
		{ // todo low 现在没法升级
			ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();

			if(!world.isRemote)
			{
				ItemStack focustack=wand.getFocusItem(itemstack);
				if (wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), !p.worldObj.isRemote, false))
				{
					// todo 以后size跟升级联系起来
					Throwables.EntityWitherOrb orb=new Throwables.EntityWitherOrb(world,p,1);
					world.spawnEntityInWorld(orb);
					// fixme 以后改正这里的音效
					p.worldObj.playSoundAtEntity(p,"mob.spider.say",1,1);
					p.swingItem();
				}
			}

			return itemstack;
		}

		public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
		{ // todo low 以后改
			return !type.equals(FocusUpgradeType.enlarge);
		}

		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
		{ // todo low 以后改
			switch(rank)
			{
				case 1:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 2:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 3:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
				case 4:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				case 5:
					return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
				default:
					return null;
			}
		}
	};

	public static abstract class FocusBase extends ItemFocusBasic
	{

		public final AspectList costBase;
		public final String sortingHelper;
		public final String iconPath;
		public final int focusColor;
		public final int wandCooldown;
		public final boolean isWave;

		public FocusBase(AspectList costBaseIn,String sortingHelperIn,String iconIn,int focusColorIn,
		                 int wandCooldown,boolean isWaveIn)
		{
			super();
			// this.setCreativeTab(IrisiaCreativeTabs.tc);
			costBase= costBaseIn==null?defaultNoCost:costBaseIn;
			sortingHelper= sortingHelperIn==null?"FB":sortingHelperIn;
			iconPath= iconIn==null?"":iconIn;
			focusColor= focusColorIn < 0 ? 15794175 : focusColorIn;
			this.wandCooldown = wandCooldown < 0 ? 100 : wandCooldown;
			isWave = isWaveIn;
		}

		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister ir) {
			this.icon = ir.registerIcon(iconPath);
		}

		public String getSortingHelper(ItemStack itemstack) {
			return sortingHelper + super.getSortingHelper(itemstack);
		}

		@Override
		public int getFocusColor(ItemStack itemstack) {
			return focusColor;
		}

		@Override
		public AspectList getVisCost(ItemStack itemstack) {
			return costBase;
		}

		@Override
		public int getActivationCooldown(ItemStack focusstack) {
			return wandCooldown;
		}

		@Override
		public WandFocusAnimation getAnimation(ItemStack itemstack) {
			return isWave?WandFocusAnimation.WAVE:WandFocusAnimation.CHARGE;
		}

		// public abstract ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition);
		// public abstract void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count);
		// public abstract void onPlayerStoppedUsingFocus(ItemStack wandstack, World world, EntityPlayer player, int count);
		// public abstract boolean onFocusBlockStartBreak(ItemStack wandstack, int x, int y, int z, EntityPlayer player);

		public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
		{
			return false;
		}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
			return null;
		}

	}
}
