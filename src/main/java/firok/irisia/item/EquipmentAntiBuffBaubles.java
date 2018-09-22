package firok.irisia.item;

import baubles.api.BaubleType;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class EquipmentAntiBuffBaubles
{
	public static final AntiBuffRing AntiPoisonRing;
	static
	{
		AntiPoisonRing=new AntiBuffRing(new Potion[]{Potion.poison},20, Keys.SoundHeal1); // todo 这个声音听不太见 以后换掉
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
