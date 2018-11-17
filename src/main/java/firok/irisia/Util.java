package firok.irisia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.item.RawMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Util {
	public static String getDes(Item itemIn)
	{
		return itemIn.getUnlocalizedName()+".des";
	}

	/*
		0.0784000015258789 走路
		0.25下落 慢
		0.45 下落 快
		1 长距离下落
	* */
	public static double getMotion(Entity en)
	{
		return Math.sqrt(en.motionX*en.motionX+en.motionY*en.motionY+en.motionZ*en.motionZ);
	}

	public static interface Informational
	{
		@SideOnly(Side.CLIENT)
		void loadInformation();
	}

	public static int countCoinCommon(EntityPlayer player) // 计算有多少普通硬币
	{
		if(player==null) return 0;

		int ret=0;
		InventoryPlayer inv=player.inventory;
		for(int i=0;i<inv.getSizeInventory();i++)
		{
			ItemStack stack=inv.getStackInSlot(i);
			if(stack==null) continue;

			Item item=stack.getItem();
			if(item==RawMaterials.CoinCopper)
				ret+=1;
			else if(item==RawMaterials.CoinCopperPile)
				ret+=8;
			else if(item==RawMaterials.CoinSilver)
				ret+=64;
			else if(item==RawMaterials.CoinSilverPile)
				ret+=512;
			else if(item==RawMaterials.CoinGold)
				ret+=4096;
			else if(item==RawMaterials.CoinGoldPile)
				ret+=32768;
		}

		return ret;
	}
	public static int countCoinIrisia(EntityPlayer player) // 计算有多少Irisia硬币
	{
		if(player==null) return 0;

		int ret=0;
		InventoryPlayer inv=player.inventory;
		for(int i=0;i<inv.getSizeInventory();i++)
		{
			ItemStack stack=inv.getStackInSlot(i);
			if(stack==null) continue;

			Item item=stack.getItem();
			if(item==RawMaterials.CoinIrisia)
				ret+=1;
			else if(item==RawMaterials.CoinIrisiaPile)
				ret+=8;
		}

		return ret;
	}

	public static class CoinCounter
	{
		private int copper=0;
		private int copperPile=0;
		private int silver=0;
		private int silverPile=0;
		private int gold=0;
		private int goldPile=0;

		public int getCopper()
		{
			return copper;
		}
		public void setCopper(int copper)
		{
			this.copper = copper;
		}
		public int getCopperPile()
		{
			return copperPile;
		}
		public void setCopperPile(int copperPile)
		{
			this.copperPile = copperPile;
		}
		public int getSilver()
		{
			return silver;
		}
		public void setSilver(int silver)
		{
			this.silver = silver;
		}
		public int getSilverPile()
		{
			return silverPile;
		}
		public void setSilverPile(int silverPile)
		{
			this.silverPile = silverPile;
		}
		public int getGold()
		{
			return gold;
		}
		public void setGold(int gold)
		{
			this.gold = gold;
		}
		public int getGoldPile()
		{
			return goldPile;
		}
		public void setGoldPile(int goldPile)
		{
			this.goldPile = goldPile;
		}

		public void setCopperTotal(int copper)
		{
			this.clear();
			this.copper = copper;
		}
		public void setCopperPileTotal(int copperPile)
		{
			this.clear();
			this.copperPile = copperPile;
		}
		public void setSilverTotal(int silver)
		{
			this.clear();
			this.silver = silver;
		}
		public void setSilverPileTotal(int silverPile)
		{
			this.clear();
			this.silverPile = silverPile;
		}
		public void setGoldTotal(int gold)
		{
			this.clear();
			this.gold = gold;
		}
		public void setGoldPileTotal(int goldPile)
		{
			this.clear();
			this.goldPile = goldPile;
		}

		public CoinCounter()
		{}
		public CoinCounter(int total)
		{
			this.copper=total;
			optimize();
		}
		public CoinCounter(EntityPlayer player)
		{
			if(player==null)
				return;
			InventoryPlayer inv=player.inventory;
			for(int i=0;i<inv.getSizeInventory();i++)
			{
				ItemStack stack=inv.getStackInSlot(i);
				if(stack==null) continue;

				Item item=stack.getItem();
				if(item==RawMaterials.CoinCopper)
					copper+=stack.stackSize;
				else if(item==RawMaterials.CoinCopperPile)
					copperPile+=stack.stackSize;
				else if(item==RawMaterials.CoinSilver)
					silver+=stack.stackSize;
				else if(item==RawMaterials.CoinSilverPile)
					silverPile+=stack.stackSize;
				else if(item==RawMaterials.CoinGold)
					gold+=stack.stackSize;
				else if(item==RawMaterials.CoinGoldPile)
					goldPile+=stack.stackSize;
			}
		}


		public int countTotal()
		{
			int ret=0;
			ret+=copper;
			ret+=copperPile*8;
			ret+=silver*64;
			ret+=silverPile*512;
			ret+=gold*4096;
			ret+=goldPile*32768;
			return ret;
		}
		public void optimize() // 最优化各种硬币的数量
		{
			int total=countTotal();
			int last=total;
			goldPile=total/32768;
			gold=(last-=32768*goldPile)/4096;
			silverPile=(last-=4096*gold)/512;
			silver=(last-=512*silverPile)/64;
			copperPile=(last-=64*silver)/8;
			copper=last-=8*copperPile;
		}
		public void clear()
		{
			copper=copperPile=silver=silverPile=gold=goldPile=0;
		}

		public void apply(EntityPlayer player)
		{
			if(player==null)
				return;

			InventoryPlayer inv=player.inventory;
			for(int i=0;i<inv.getSizeInventory();i++)
			{
				ItemStack stack=inv.getStackInSlot(i);
				if(stack==null) continue;

				Item item=stack.getItem();
				if(item==RawMaterials.CoinCopper
						|| item==RawMaterials.CoinCopperPile
						|| item==RawMaterials.CoinSilver
						|| item==RawMaterials.CoinSilverPile
						|| item==RawMaterials.CoinGold
						|| item==RawMaterials.CoinGoldPile)
					inv.setInventorySlotContents(i,null);
			}

			player.dropItem(RawMaterials.CoinCopper ,copper);
			player.dropItem(RawMaterials.CoinCopperPile,copperPile);
			player.dropItem(RawMaterials.CoinSilver,silver);
			player.dropItem(RawMaterials.CoinSilverPile,silverPile);
			player.dropItem(RawMaterials.CoinGold,gold);
			player.dropItem(RawMaterials.CoinGoldPile,goldPile);
		}

		@Override
		public String toString()
		{
			StringBuffer ret=new StringBuffer();
			ret.append("gold pile : ");ret.append(goldPile);ret.append('\n');
			ret.append("gold : ");ret.append(gold);ret.append('\n');
			ret.append("silver pile : ");ret.append(silverPile);ret.append('\n');
			ret.append("silver : ");ret.append(silver);ret.append('\n');
			ret.append("copper pile : ");ret.append(copperPile);ret.append('\n');
			ret.append("copper : ");ret.append(copper);ret.append('\n');
			return ret.toString();
		}
	}

}
