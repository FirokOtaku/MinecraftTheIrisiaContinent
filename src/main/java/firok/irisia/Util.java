package firok.irisia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.item.RawMaterials;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.awt.*;

public class Util {
	public static boolean canTick(World world,int tickInterval,int tickOffset)
	{
		return world==null?false:world.getTotalWorldTime()%tickInterval+tickOffset==0;
	}

	public static final byte[] Bits=new byte[]{1,2,4,8,16,32,64};
	public static final byte BitSetBlockUpdate=1;
	public static final byte BitSetBlockSendChangeToClients=2;
	public static final byte BitSetBlockPreventRerender=4;

	public static class BlockPos
	{
		public final int x,y,z;
		public final int hash;
		@Override public int hashCode()
		{
			return hash;
		}
		public BlockPos(int x, int y, int z)
		{
			this.x=x;
			this.y=y;
			this.z=z;
			hash=x*y*z;
		}
//		public BlockPos up(){return new BlockPos(x,y+1,z);}
//		public BlockPos down(){return new BlockPos(x,y-1,z);}
//		public BlockPos east(){return new BlockPos(x,y,z);}
//		public BlockPos west(){return new BlockPos(x,y,z);}
//		public BlockPos south(){return new BlockPos(x,y,z);}
//		public BlockPos north(){return new BlockPos(x,y,z);}
//
//		public BlockPos dir(int dir)
//		{
//			switch(dir)
//			{
//				case 0:
//				case 1:
//				case 2:
//				case 3:
//				case 5:
//				case 6:
//				default: return this;
//			}
//		}

		public static BlockPos $(int cx, int cy, int cz)
		{
			return new BlockPos(cx,cy,cz);
		}
	}
	public static class BlockState
	{
		public final Block block;
		public final int meta;
		@Override
		public int hashCode()
		{
			return block.hashCode();
		}
		public BlockState(Block block, int meta)
		{
			this.block=block;
			this.meta=meta;
		}

		public static BlockState $(Block block, int meta)
		{
			return new BlockState(block,meta);
		}
	}

	public enum ItemType
	{
		Item,
		Block,
		Helmet,
		Chestplate,
		Leggings,
		Boots,
		Amulet,
		Belt,
		Rings,
		Sword,
		Pickaxe,
		Axe,
		Hoe,
		Spade,
	}

	public static double getValueWithFactor(double range,
	                                        double minResult,double maxResult,
	                                        double minRange,double maxRange)
	{
		return range<minRange? minResult:
				range>maxRange? maxResult:
						(range-minRange)/(maxRange-minRange)*(maxResult-minResult)+minResult;
	}
	public static boolean isFriendly(Entity entity)
	{
		return entity!=null && !(entity instanceof EntityCreature) && !(entity instanceof IMob);
	}
	public static boolean attackWithCooldown(EntityLivingBase attacker, EntityLivingBase target,
	                                         ItemStack stackWeapon, int cd,
	                                         DamageSource damageSource, float damage, float damageMin)
	{
		boolean canAttack;

		long now=attacker.worldObj.getTotalWorldTime();
		NBTTagCompound nbt=stackWeapon.hasTagCompound()?stackWeapon.getTagCompound():new NBTTagCompound();
		long lastTimeAttack=nbt.hasKey("lastTimeAttack")?nbt.getLong("lastTimeAttack"):-1;

		canAttack=lastTimeAttack<0||now-lastTimeAttack>=cd;

		if(canAttack)
		{
			nbt.setLong("lastTimeAttack",now);
		}
		target.attackEntityFrom(damageSource,canAttack?damage:damageMin);
		stackWeapon.setTagCompound(nbt);

		return canAttack;
	}

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

	public static class ForceUnit
	{
		private double x,y,z;
		public double getX()
		{
			return x;
		}
		public double getY()
		{
			return y;
		}
		public double getZ()
		{
			return z;
		}
		public ForceUnit(double x, double y, double z)
		{
			this.x=x;
			this.y=y;
			this.z=z;
		}
		public ForceUnit with(ForceUnit f2)
		{
			return new ForceUnit(x+f2.x,y+f2.y,z+f2.z);
		}
	}
	// 这两个接口用来计算力度
	// note todo
	public static ForceUnit getForce2D(double x1,double z1,double x2,double z2,
	                                   double factorDistance,double factorForce,double maxForce)
	{
		return null;
	}
	public static ForceUnit getForce3D(double x1,double y1,double z1,double x2,double y2,double z2,double base,double factorForce)
	{
		return null;
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

	public static class DrawUnit
	{
		Graphics gra;
		public DrawUnit(Image image)
		{
			gra=image.getGraphics();
		}
		public DrawUnit(Graphics gra)
		{
			this.gra=gra;
		}
		public DrawUnit drawWithString(Color color,Font font,String text,int x,int y)
		{
			Color tc=gra.getColor();
			Font tf=gra.getFont();

			gra.setColor(color);
			gra.setFont(font);
			gra.drawString(text,x,y+font.getSize());

			gra.setColor(tc);
			gra.setFont(tf);

			return this;
		}
		public DrawUnit drawWithOval(Color color,int x,int y,int width,int height)
		{
			Color tc=gra.getColor();

			gra.drawOval(x,y,width,height);

			gra.setColor(tc);
			return this;
		}
		public DrawUnit drawWithLing(Color color,int x,int y,int x2,int y2)
		{
			Color tc=gra.getColor();

			gra.drawLine(x,y,x2,y2);

			gra.setColor(tc);
			return this;
		}
		public Graphics getGraphics()
		{
			return gra;
		}
	}

}
