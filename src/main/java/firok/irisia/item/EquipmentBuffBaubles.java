package firok.irisia.item;

import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.potion.Potions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

import java.util.List;

public class EquipmentBuffBaubles
{
	private static final int[] SingleLowLvs =new int[]{0};
	private static final int[] SingleMidLvs =new int[]{1};
	private static final int[] SingleHighLvs=new int[]{2};
	public static final BuffAmulet GeneralEmblem;
	public static final BuffBelt GiantBelt;
	public static final BuffRing ManaBoostRing;
	public static final BuffRing ManaInterferingRing;
	static
	{
		GeneralEmblem =new BuffAmulet(new Potion[]{Potions.Leaderly}, SingleMidLvs);
		GiantBelt =new BuffBelt(new Potion[]{Potion.damageBoost}, SingleLowLvs);
		ManaBoostRing =new BuffRing(new Potion[]{Potions.MagicAmplificative}, SingleLowLvs);
		ManaInterferingRing=new BuffRing(new Potion[]{Potions.MagicResistance}, SingleLowLvs);
	}


	public static abstract class BuffBauble extends EquipmentSets.ItemBauble
	{
		public final Potion[] buffs;
		public final int[] lvs;
		protected BuffBauble(Potion[] buffs,int[] lvs)
		{
			if(buffs==null||lvs==null||buffs.length!=lvs.length) // 嘛 就抛出个异常玩
				throw new RuntimeException("状态效果饰品初始化参数有误");
			this.buffs=buffs;
			this.lvs=lvs;
		}
		protected BuffBauble()
		{
			this(Irisia.noPotion,Irisia.noInt);
		}
		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase enlb)
		{
			if(enlb.worldObj.isRemote||enlb.ticksExisted%80!=0||buffs.length==0)
				return;
			for(int i=0;i<buffs.length;i++)
			{
				enlb.addPotionEffect(new PotionEffect(buffs[i].id,85,lvs[i]));
			}
		}
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean p_77624_4_)
		{
			if(buffs.length==0)
				return;
			info.add(StatCollector.translateToLocal(Keys.InfoBuffTitle));
			for(int i=0;i<buffs.length;i++)
			{
				info.add(StatCollector.translateToLocal(buffs[i].getName())+Irisia.getRomeInt(lvs[i])); // todo low 这里以后可能改成罗马数字形式
			}
//			try
//			{
//				for(Potion p:buffs)
//				{
//					info.add(StatCollector.translateToLocal(p.getName()));
//				}
//			}
//			catch (Exception e)
//			{
//				if(this==GiantBelt) Irisia.log("giant belt exception ",player);
//				else if(this==GeneralEmblem) Irisia.log("general emblem exception ",player);
//				else if(this==ManaBoostRing) Irisia.log("mana boost ring exception ",player);
//				else if(this==ManaInterferingRing) Irisia.log("mana interfering ring exception",player);
//				else Irisia.log("else exception",player);
//			}
		}
	}
	public static class BuffRing extends BuffBauble
	{
		public BuffRing(Potion[] buffs,int[] lvs)
		{
			super(buffs,lvs);
		}
		public BuffRing()
		{
			super();
		}
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}
	public static class BuffBelt extends BuffBauble
	{
		public BuffBelt(Potion[] buffs,int[] lvs)
		{
			super(buffs,lvs);
		}
		public BuffBelt()
		{
			super();
		}
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.BELT;
		}
	}
	public static class BuffAmulet extends BuffBauble
	{
		public BuffAmulet(Potion[] buffs,int[] lvs)
		{
			super(buffs,lvs);
		}
		public BuffAmulet()
		{
			super();
		}
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.AMULET;
		}
	}
}
