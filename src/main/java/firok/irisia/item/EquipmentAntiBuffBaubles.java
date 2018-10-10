package firok.irisia.item;

import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

import java.util.List;

public class EquipmentAntiBuffBaubles
{
	public static final AntiBuffRing AntiPoisonRing;
	public static final AntiBuffAmulet HolyCrossAmulet;
	static
	{
		AntiPoisonRing=new AntiBuffRing(new Potion[]{Potion.poison},20, Keys.SoundHeal1); // todo 这个声音听不太见 以后换掉
		HolyCrossAmulet=new AntiBuffAmulet(new Potion[]{Potion.poison,Potion.moveSlowdown,Potion.digSlowdown,Potion.weakness,Potion.blindness,Potion.confusion},40,Keys.SoundHeal1);
	}
	public abstract static class AntiBuffBauble extends EquipmentSets.ItemBauble
	{
		Potion[] buffs;
		int interval;
		String sound;
		protected AntiBuffBauble()
		{
			this(null,0);
		}
		protected AntiBuffBauble(Potion[] buffsIn,int intervalIn)
		{
			this(buffsIn,intervalIn,null);
		}
		protected AntiBuffBauble(Potion[] buffsIn,int intervalIn,String soundKey)
		{
			buffs=buffsIn==null||buffsIn.length==0?Irisia.noPotion:buffsIn;
			interval=intervalIn;
			sound=soundKey;
		}

		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean flag)
		{
			info.add(StatCollector.translateToLocal(Keys.InfoAntibuffInterval)+interval/20.0+'s');
			info.add(StatCollector.translateToLocal(Keys.InfoAntibuffTitle));
			for(Potion p:buffs)
			{
				info.add(StatCollector.translateToLocal(p.getName()));
			}
		}


		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase enlb)
		{
			if(enlb.worldObj.isRemote||enlb.ticksExisted%interval!=0||buffs.length==0)
				return;
			boolean healed=false;
			for(Potion p:buffs)
			{
				enlb.removePotionEffect(p.id);
				healed=true;
			}
			if(healed&&sound!=null)
				enlb.playSound(sound,1,1);
		}
	}
	public static class AntiBuffRing extends AntiBuffBauble
	{
		public AntiBuffRing()
		{
			super();
		}
		public AntiBuffRing(Potion[] buffsIn, int intervalIn)
		{
			super(buffsIn, intervalIn);
		}
		public AntiBuffRing(Potion[] buffsIn, int intervalIn, String soundKey)
		{
			super(buffsIn, intervalIn, soundKey);
		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}
	public static class AntiBuffAmulet extends AntiBuffBauble
	{
		public AntiBuffAmulet()
		{
			super();
		}
		public AntiBuffAmulet(Potion[] buffsIn, int intervalIn)
		{
			super(buffsIn, intervalIn);
		}
		public AntiBuffAmulet(Potion[] buffsIn, int intervalIn, String soundKey)
		{
			super(buffsIn, intervalIn, soundKey);
		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.AMULET;
		}
	}
	public static class AntiBuffBelt extends AntiBuffBauble
	{
		public AntiBuffBelt()
		{
			super();
		}
		public AntiBuffBelt(Potion[] buffsIn, int intervalIn)
		{
			super(buffsIn, intervalIn);
		}
		public AntiBuffBelt(Potion[] buffsIn, int intervalIn, String soundKey)
		{
			super(buffsIn, intervalIn, soundKey);
		}

		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.BELT;
		}
	}
}
