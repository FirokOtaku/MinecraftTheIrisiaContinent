package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.block.SpecialDecorations;
import firok.irisia.entity.Throwables;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;

import java.awt.*;

public class Focus
{
	public static final AspectList defaultNoCost=new AspectList();
	// 核心-蛛后 岩浆
	public static final FocusUpgradeType ugtLava=new FocusUpgradeType(2001,
			new ResourceLocation("irisia","textures/foci/lava.png"),
			"focus.upgrade.lava.name",
			"focus.upgrade.lava.text",
			(new AspectList()).add(Aspect.FIRE, 1));
	// 核心-蛛后 土黄
	public static final FocusUpgradeType ugtDirty=new FocusUpgradeType(2002,
			new ResourceLocation("irisia","textures/foci/dirty.png"),
			"focus.upgrade.dirty.name",
			"focus.upgrade.dirty.text",
			(new AspectList()).add(Aspect.EARTH, 1));
	// 核心-蛛后 毒性
	public static final FocusUpgradeType ugtPoisonous=new FocusUpgradeType(2003,
			new ResourceLocation("irisia","textures/foci/poisonous.png"),
			"focus.upgrade.poisonous.name",
			"focus.upgrade.poisonous.text",
			(new AspectList()).add(Aspect.ENTROPY, 1));

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
		@Override
		public int getActivationCooldown(ItemStack focusstack)
		{ // todo 以后会随着升级改变cd
			return cooldown;
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

	public final static FocusBase Wither=new FocusBase(defaultNoCost,"FW",null,Color.black.getRGB(),
			500,true)
	{
		private final AspectList costBase=(new AspectList().add(Aspect.ENTROPY,60));
		@Override
		public AspectList getVisCost(ItemStack stack)
		{
			return costBase;
		}

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
		public final int cooldown;
		public final boolean isWave;

		public FocusBase(AspectList costBaseIn,String sortingHelperIn,String iconIn,int focusColorIn,
		                 int cooldownIn,boolean isWaveIn)
		{
			super();
			// this.setCreativeTab(IrisiaCreativeTabs.tc);
			costBase= costBaseIn==null?defaultNoCost:costBaseIn;
			sortingHelper= sortingHelperIn==null?"FB":sortingHelperIn;
			iconPath= iconIn==null?"":iconIn;
			focusColor= focusColorIn < 0 ? 15794175 : focusColorIn;
			cooldown= cooldownIn < 0 ? 100 : cooldownIn;
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
			return cooldown;
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
