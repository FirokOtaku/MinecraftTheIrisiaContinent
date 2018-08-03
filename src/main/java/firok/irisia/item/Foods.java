package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Foods
{
	public final static InformationItemFood VilligerFood;

	static
	{
		VilligerFood=new InformationItemFood();
	}


	public static class InformationItemFood extends ItemFood
	{
		public final int UseDuration;
		public final String[] lines;
		public final boolean isDrink;
		public InformationItemFood()
		{
			this(32,0,0,false,null);
		}
		public InformationItemFood(int useTime,int amountHeal,int amountSaturation,boolean isWolfFood)
		{
			this(useTime,amountHeal,amountSaturation,isWolfFood,null);
		}
		public InformationItemFood(int useTime,int amountHeal,int amountSaturation,boolean isWolfFood,String[] l)
		{
			this(useTime,amountHeal,amountSaturation,isWolfFood,false,l);
		}
		public InformationItemFood(int useTime,int amountHeal,int amountSaturation,boolean isWolfFood,boolean isDrink,String[] l)
		{
			super(amountHeal,amountSaturation,isWolfFood);
			this.UseDuration=useTime;
			this.isDrink=isDrink;
			if(l!=null && l.length>0)
				lines=l.clone();
			else
				lines=new String[0];
		}

		@Override
		public EnumAction getItemUseAction(ItemStack p_77661_1_)
		{
			return isDrink?EnumAction.drink:EnumAction.eat;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
		{
			for(String line:lines)
			{
				p_77624_3_.add(line);
			}
		}

		@Override
		public int getMaxItemUseDuration(ItemStack p_77626_1_)
		{
			return UseDuration;
		}
	}

	/*public ItemFood(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_)
    {
        this.itemUseDuration = 32;
        this.healAmount = p_i45339_1_;
        this.isWolfsFavoriteMeat = p_i45339_3_;
        this.saturationModifier = p_i45339_2_;
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    public ItemFood(int p_i45340_1_, boolean p_i45340_2_)
    {
        this(p_i45340_1_, 0.6F, p_i45340_2_);
    }*/
}
