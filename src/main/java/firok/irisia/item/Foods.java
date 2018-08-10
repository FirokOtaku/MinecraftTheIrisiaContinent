package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

import java.util.List;

public class Foods
{
	public final static InformationItemFood VilligerFood; // 村人干粮
	public final static InformationItemFood IcyGummyJelly; // 冰软糖
	public final static InformationItemFood HuckTeaLeaf; // 哈克茶叶
	public final static InformationItemFood SibertStem; // 希伯特茎
	public final static InformationItemFood Breadfruit; // 面包果
	public final static InformationItemFood ElfBean; // 精灵豆
	public final static InformationItemFood HardBread; // 硬面包
	public final static InformationItemFood DwartBread; // 矮人面包
	public final static InformationItemFood ElfBread; // 精灵面包

	public final static InformationItemFood PotionMpLesser; // 蓝药
	public final static InformationItemFood PotionMp;
	public final static InformationItemFood PotionMpGreater;
	public final static InformationItemFood PotionMpSuper;

	static
	{
		VilligerFood=new InformationItemFood();
		IcyGummyJelly=new InformationItemFood();
		HardBread=new InformationItemFood();
		DwartBread=new InformationItemFood();
		ElfBread=new InformationItemFood();
		SibertStem=new InformationItemFood();
		HuckTeaLeaf=new InformationItemFood();
		Breadfruit=new InformationItemFood();
		ElfBean=new InformationItemFood();

		PotionMpLesser=new InformationItemFood()
		{
			@Override
			protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
			{
				super.onFoodEaten(itemStack,world,player);
				if(world.isRemote)
					return;
			}
		};
		PotionMp=new InformationItemFood();
		PotionMpGreater=new InformationItemFood();
		PotionMpSuper=new InformationItemFood();
	}


	public static class InformationItemFood extends ItemFood
	{
		public final int UseDuration;
		//public final String[] lines;
		public final boolean isDrink;
		public final Potion[] potions;
		public final int[] durs;
		public final byte[] levels;
		public final float[] props;
		public InformationItemFood()
		{
			this(32,0,0,false);
		}

		public InformationItemFood(int useTime,int amountHeal,int amountSaturation,boolean isWolfFood)
		{
			this(useTime,amountHeal,amountSaturation,isWolfFood,false);
		}
		public InformationItemFood(int useTime,int amountHeal,int amountSaturation,boolean isWolfFood,boolean isDrink)
		{
			this(useTime,amountHeal,amountSaturation,isWolfFood,isDrink,new Potion[0],new int[0],new byte[0],new float[0]);
		}
		public InformationItemFood(int useTime,int amountHeal,int amountSaturation,boolean isWolfFood,boolean isDrink,
		                           Potion[] ps,int[] ds,byte[] ls,float[] prs)
		{
			super(amountHeal,amountSaturation,isWolfFood);

			this.UseDuration=useTime;
			this.isDrink=isDrink;
//			if(l!=null && l.length>0)
//				lines=l.clone();
//			else
//				lines=new String[0];

			if(ps.length>0 && ps.length==ds.length&& ds.length==ls.length && ls.length==prs.length)
			{
				potions=ps.clone();
				durs=ds.clone();
				levels=ls.clone();
				props=prs.clone();
			}
			else
			{
				potions=new Potion[0];
				durs=new int[0];
				levels=new byte[0];
				props=new float[0];
			}
		}

//		@Override
//		public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
//		{
//			--itemStack.stackSize;
//			player.getFoodStats().func_151686_a(this, itemStack);
//			world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
//			this.onFoodEaten(itemStack, world, player);
//			return itemStack;
//		}

		@Override
		protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
		{
			if(world.isRemote)
				return;

			for(int i=0;i<potions.length;i++)
			{
				if(world.rand.nextFloat()>=props[i])
					continue;

				player.addPotionEffect(
						new PotionEffect(
								potions[i].getId(),
								durs[i],
								levels[i]));
			}
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
//			for(String line:lines)
//			{
//				p_77624_3_.add(line);
//			}
		}

		@Override
		public int getMaxItemUseDuration(ItemStack p_77626_1_)
		{
			return UseDuration;
		}
	}
}
