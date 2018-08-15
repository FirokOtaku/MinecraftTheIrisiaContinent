package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.common.IrisiaCreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;

public class Focus
{
	public static abstract class FocusAttrHandler
	{
		public abstract ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition);
		public abstract void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count);
		public abstract boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank);
		public abstract FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank);
	}
	public static final FocusAttrHandler defaultFocusAttrHandler;
	public static final AspectList defaultNoCost;
	static
	{
		defaultFocusAttrHandler =new FocusAttrHandler()
		{
			@Override
			public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
			{
				ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();

				player.setItemInUse(itemstack, Integer.MAX_VALUE);
				WandManager.setCooldown(player, -1);

				return itemstack;
			}

			@Override
			public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count)
			{
				;
			}

			@Override
			public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
				return false;
			}

			@Override
			public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
				return new FocusUpgradeType[]{};
			/*switch(rank) {
				case 1:
					return new FocusUpgradeType[]{};
				case 2:
					return new FocusUpgradeType[]{};
				case 3:
					return new FocusUpgradeType[]{};
				case 4:
					return new FocusUpgradeType[]{};
				case 5:
					return new FocusUpgradeType[]{};
				default:
					return null;
			}*/
			}
		};
		defaultNoCost =new AspectList();
	}

	public static class FocusBase extends ItemFocusBasic
	{

		public final AspectList costBase;
		public final String sortingHelper;
		public final String iconPath;
		public final int focusColor;
		public final int cooldown;
		public final boolean isWave;
		public final FocusAttrHandler focusAttrHandler;

		public FocusBase(AspectList costBaseIn,String sortingHelperIn,String iconIn,int focusColorIn,
		                 int cooldownIn,boolean isWaveIn,FocusAttrHandler focusAttrHandlerIn)
		{
			super();
			// this.setCreativeTab(IrisiaCreativeTabs.irisiaTC);
			costBase= costBaseIn==null?defaultNoCost:costBaseIn;
			sortingHelper= sortingHelperIn==null?"FB":sortingHelperIn;
			iconPath= iconIn==null?"":iconIn;
			focusColor= focusColorIn < 0 ? 15794175 : focusColorIn;
			cooldown= cooldownIn < 0 ? 100 : cooldownIn;
			isWave = isWaveIn;
			focusAttrHandler = focusAttrHandlerIn==null? defaultFocusAttrHandler :focusAttrHandlerIn;
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

		@Override
		public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
		{
			return focusAttrHandler.onFocusRightClick(itemstack,world,player,movingobjectposition);
		}
		@Override
		public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count) {
			focusAttrHandler.onUsingFocusTick(stack,p,count);
		}

		public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
			return focusAttrHandler.canApplyUpgrade(focusstack,player,type,rank);
		}

		@Override
		public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
			return focusAttrHandler.getPossibleUpgradesByRank(itemstack,rank);
		}

	}
}
