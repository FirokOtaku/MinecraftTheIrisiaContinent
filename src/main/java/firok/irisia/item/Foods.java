package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

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

	public final static Berry BerryNull;
	public final static MixedSweet MixedSweet;

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

		BerryNull=new Berry();
		MixedSweet =new MixedSweet();
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

	public static class Berry extends InformationItemFood
	{
		public final int[] tastes;
		public final int antiD;
		public final int antiP;
		public final int exp;
		public final int expl;
		public final int[] mps;
		public final int mpl;
		public final int[] pids; // 实例化之后 不要往这里面写数据
		public final int[] pts;
		public final int[] pls;
		public final int[] prs;

		private final int[] nullArray=new int[0];


		public Berry(int[] taste,int antiD,int antiP,
		             int exp,int expl,
		             int[] mps,int mpl,
		             int[] pids,int[] pts,int[] pls,int[] prs)
		{
			super(28,1,0,false);
			tastes= taste==null||taste.length!=5?new int[]{0,0,0,0,0}:taste.clone();
			this.antiD=antiD;
			this.antiP=antiP;
			this.exp=exp;
			this.expl=expl;
			this.mps=mps==null||mps.length!=6?new int[]{0,0,0,0,0,0}:mps.clone();
			this.mpl=mpl;

			if(pids==null||pts==null||pls==null||prs==null||pids.length!=pts.length||pts.length!=pls.length||pls.length!=prs.length)
			{
				this.pids=nullArray;
				this.pts=nullArray;
				this.pls=nullArray;
				this.prs=nullArray;
			}
			else
			{
				this.pids=pids.clone();
				this.pts=pts.clone();
				this.pls=pls.clone();
				this.prs=prs.clone();
			}
		}
		public Berry(int[] taste,int antiD,int antiP,int exp,int expl)
		{
			this(taste,antiD,antiP,exp,expl,null,0,null,null,null,null);
		}
		public Berry(int[] taste,int[] pids,int[] pts,int[] pls,int[] prs)
		{
			this(taste,0,0,0,0,null,0,pids,pts,pls,prs);
		}
		public Berry(int[] tastes)
		{
			this(tastes,0,0,0,0,null,0,null,null,null,null);
		}
		public Berry()
		{
			this(null,0,0,0,0,null,0,null,null,null,null);
		}
	}
	public static class MixedSweet extends InformationItemFood
	{
		public MixedSweet()
		{
			super(28,2,0,false,true);
		}

		/*
		树果
		http://wiki.52poke.com/wiki/%E6%A0%91%E6%9E%9C

		poke 方块
		http://wiki.52poke.com/wiki/%E5%AE%9D%E5%8F%AF%E6%96%B9%E5%9D%97

		tag:
			exp: int 经验
			expl: int 经验等级

			tastes: byte[] 口味

			pids: int[] 药水id
			pts: int[] 药水持续tick
			prs: float[] 药水出现几率

			mps: int[] 恢复的魔力量
			mpl: int 安全承载魔力量
		*/
		@Override
		protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
		{
			super.onFoodEaten(itemStack,world,player);

			if(world.isRemote)
				return;

			try
			{
				NBTTagCompound nbt=new NBTTagCompound();
				itemStack.writeToNBT(nbt);

				NBTTagCompound tag=(NBTTagCompound)nbt.getTag("tag");
				if(tag==null) return;

				Random rand=world.rand;


				int exp=tag.getInteger("exp");
				int expl=tag.getInteger("expl");
				int[] mps=tag.getIntArray("mps"); mps= mps.length==6?mps:new int[6];
				int mpl=tag.getInteger("mpl");

				// 增加经验
				player.addExperience(exp);
				player.addExperienceLevel(expl);
				// 恢复魔力
				float totalMpDebuff=0;
				for(int mp:mps)
				{
					if(mp>mpl)
						totalMpDebuff+=0.2;
				}


				byte[] tastes=tag.getByteArray("tastes"); tastes= tastes.length==5? tastes: new byte[5];

				float totalDebuffRate=0-tag.getFloat("antiD");
				float totalPoisonRate=0-tag.getFloat("antiP");
				// 计算debuff几率
				for(byte taste : tastes)
				{
					if(taste==3)
					{
						totalDebuffRate+=0.1;
					}
					else if(taste==4)
					{
						totalDebuffRate+=0.15;
						totalPoisonRate+=0.1;
					}
					else if(taste>=5)
					{
						totalDebuffRate+=0.25;
						totalPoisonRate+=0.2;
					}
				}
				// 增加debuff
				if(rand.nextFloat()<totalDebuffRate)
				{
					player.addPotionEffect(new PotionEffect(Potion.confusion.id,200,0));
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,100,0));
				}
				// 中毒
				if(rand.nextFloat()<totalPoisonRate)
				{
					player.addPotionEffect(new PotionEffect(Potion.poison.id,200,0));
				}

				int[] pids=tag.getIntArray("pids");
				int[] pts=tag.getIntArray("pts");
				int[] pls=tag.getIntArray("pls");
				int[] prs=tag.getIntArray("prs");
				if(pids.length!=pts.length || pts.length!=prs.length || prs.length!=pls.length) // 检查合法性
					return;

				// 增加额外药水效果
				for(int i=0;i<pids.length;i++)
				{
					if(rand.nextFloat()<prs[i])
					{
						try
						{
							player.addPotionEffect(new PotionEffect(pids[i],pts[i],pls[i]));
						}
						catch (Exception e)
						{
							;
						}
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Irisia.log(e.getStackTrace(),player);
			}
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
		{
			super.addInformation(itemStack,player,list,flag);

			try
			{
				NBTTagCompound nbt=new NBTTagCompound();
				itemStack.writeToNBT(nbt);

				NBTTagCompound tag=(NBTTagCompound)nbt.getTag("tag");
				if(tag==null) return;


				int exp=tag.getInteger("exp");
				int expl=tag.getInteger("expl");
				int[] mps=tag.getIntArray("mps"); mps= mps.length==6?mps:new int[6];
				int mpl=tag.getInteger("mpl");

				// 增加经验
				list.add("Exp : "+exp);
				list.add("ExpL : "+expl);
				// 恢复魔力
				;


				byte[] tastes=tag.getByteArray("tastes"); tastes= tastes.length==5? tastes: new byte[5];
				if(tastes[0]>0) list.add(toLevel(tastes[0])+"甜");
				if(tastes[1]>0) list.add(toLevel(tastes[1])+"酸");
				if(tastes[2]>0) list.add(toLevel(tastes[2])+"苦");
				if(tastes[3]>0) list.add(toLevel(tastes[3])+"涩");
				if(tastes[4]>0) list.add(toLevel(tastes[4])+"辣");


				float totalDebuffRate=tag.getFloat("antiD");
				float totalPoisonRate=tag.getFloat("antiP");

				list.add("异常状态抗性 : "+totalDebuffRate*100+"%");
				list.add("毒抗性 : "+totalPoisonRate*100+"%");


				int[] pids=tag.getIntArray("pids");
				int[] pts=tag.getIntArray("pts");
				int[] pls=tag.getIntArray("pls");
				int[] prs=tag.getIntArray("prs");
				if(pids.length!=pts.length || pts.length!=prs.length || prs.length!=pls.length) // 检查合法性
					return;

				// 增加额外药水效果
				for(int i=0;i<pids.length;i++)
				{
					list.add("Lv."+pls[i]+" "+StatCollector.translateToLocal(Potion.potionTypes[pids[i]].getName()) +" - "+pts[i]/20+"s "+prs[i]+"%");
				}
			}
			catch (Exception e)
			{
				;
			}
		}
		private String toLevel(byte level)
		{
			if(level<=0)
				return "";
			if(level==1)
				return "略";
			if(level==2)
				return "";
			if(level==3)
				return "非常";
			if(level==4)
				return "极";
			else
				return "过";
		}
	}
}
