package firok.irisia.tileentity;

import firok.irisia.Irisia;
import firok.irisia.block.PavingStones;
import firok.irisia.item.Foods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

public class BerryMixerTE extends TileEntity implements ISidedInventory
{
	private byte mixerLevel;
	private ItemStack[] mixerItemStacks;
	// 0 糖 1 产物 3-5 原材料
	// 2+3 2+5
	// 5 7
	private int mixerProcess; // 当前工作了多少tick 1级需要8秒=160tick 2级需要5秒=100tick

	// tile entity
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.mixerLevel=nbt.getByte("mixerLevel");
		this.mixerProcess=nbt.getInteger("mixerProcess");
		this.mixerItemStacks= this.mixerLevel==1? new ItemStack[5]:new ItemStack[7];

		if(!nbt.hasKey("items"))
			return;

		NBTTagCompound items=nbt.getCompoundTag("items");
		for(byte i=0;i<this.mixerItemStacks.length;i++)
		{
			try
			{
				if(items.hasKey("item_"+i))
				{
					this.mixerItemStacks[i] = ItemStack.loadItemStackFromNBT(items.getCompoundTag("item_" + i));
					Irisia.log(items.getCompoundTag("item_"+i).toString());
				}
				else
				{
					this.mixerItemStacks[i] = null;
					Irisia.log("null item at "+i);
				}
			}
			catch (Exception e)
			{
				Irisia.log("树果混合器: 从NBT加载物品出错");
				Irisia.log(e);
			}
		}
	}
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setByte("mixerLevel",this.mixerLevel);
		nbt.setInteger("mixerProcess",this.mixerProcess);
		NBTTagCompound items=new NBTTagCompound();
		for(byte i=0;i<this.mixerItemStacks.length;i++)
		{
			if(this.mixerItemStacks[i]!=null)
			{
				NBTTagCompound item=new NBTTagCompound();
				this.mixerItemStacks[i].writeToNBT(item);
				items.setTag("item_"+i,item);
			}
//			else
//			{
//				// items.setTag("item_"+i,null);
//			}
		}
		nbt.setTag("items",items);
	}

	// constructor
	public BerryMixerTE()
	{
		this((byte)1);
	}
	public BerryMixerTE(byte mixerLevel)
	{
		this.mixerLevel=mixerLevel;
		if(this.mixerLevel<1||this.mixerLevel>2)
			this.mixerLevel=1;


		if(this.mixerLevel==1)
		{
			mixerItemStacks=new ItemStack[5];
		}
		else if(this.mixerLevel==2)
		{
			mixerItemStacks=new ItemStack[7];
		}
	}

	/// ISidedInventory
	private final int[] slotsSugar=new int[]{0};
	private final int[] slotsOutput=new int[]{1};
	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return side == 0 ? new int[]{1} // bottom
		: (side == 1 ? new int[]{0} : // top
			this.mixerLevel==1?new int[]{3,4,5}:new int[]{3,4,5,6,7} // side
		);
	}
	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int p_102007_3_)
	{
		return this.isItemValidForSlot(slot, itemStack);
	}
	@Override
	public boolean canExtractItem(int slot, ItemStack p_102008_2_, int p_102008_3_)
	{
		return slot==1;
	}
	/// IInventory
	@Override
	public int getSizeInventory()
	{
		switch(mixerLevel)
		{
			case 1:
				return 5;
			case 2:
				return 7;
		}
		return 5;
	}
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return this.mixerItemStacks[slot];
	}
	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
	{
		return null;
	}
	@Override // 还不知道这个方法有啥用
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.mixerItemStacks[slot] != null)
		{
			ItemStack itemstack = this.mixerItemStacks[slot];
			this.mixerItemStacks[slot] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack)
	{
		this.mixerItemStacks[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}
	@Override
	public String getInventoryName()
	{
		return "Berry Mixer";
	}
	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return
				this.worldObj.getTileEntity(
						this.xCoord,
						this.yCoord,
						this.zCoord) != this ? false :
						p_70300_1_.getDistanceSq(
								(double)this.xCoord + 0.5D,
								(double)this.yCoord + 0.5D,
								(double)this.zCoord + 0.5D) <= 32.0D;
	}
	@Override
	public void openInventory()
	{
		;
	}
	@Override
	public void closeInventory()
	{
		;
	}
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		if(itemStack==null)
			return false;

		Item item=itemStack.getItem();
		if(slot==0 && item== Items.sugar) // 放糖
			return true;
		if(slot==1) // 这个是产物
			return false;

		if(this.mixerLevel==1 && slot>=2 && slot<=4 && item instanceof Foods.Berry)
			return true;
		if(this.mixerLevel==2 && slot>=2 && slot<=6 && item instanceof Foods.Berry)
			return true;


		return false;
	}

	// berry mixer
	public boolean isProcessing()
	{
		return this.mixerProcess > 0;
	}

	private int countRawMaterials()
	{
		int ret=0;

		if(mixerItemStacks[2]!=null && mixerItemStacks[2].stackSize>=1)
			ret++;
		if(mixerItemStacks[3]!=null && mixerItemStacks[3].stackSize>=1)
			ret++;
		if(mixerItemStacks[4]!=null && mixerItemStacks[4].stackSize>=1)
			ret++;

		if(this.mixerLevel==2)
		{
			if(mixerItemStacks[5]!=null && mixerItemStacks[5].stackSize>=1)
				ret++;
			if(mixerItemStacks[6]!=null && mixerItemStacks[6].stackSize>=1)
				ret++;
		}

		return ret;
	}
	public boolean canProcess()
	{
		if(mixerItemStacks[0]==null || mixerItemStacks[0].stackSize<=0)
			return false;

		if(countRawMaterials()==0)
			return false;

		if(this.mixerItemStacks[1]!=null)
		{
			NBTTagCompound nbt1=new NBTTagCompound();
			this.mixerItemStacks[1].writeToNBT(nbt1);
			NBTTagCompound nbt2=new NBTTagCompound();
			tryProcess().writeToNBT(nbt2);
			if(!nbt1.equals(nbt2))
				return false;
		}

		return true;
	}

	public void processOne()
	{
		if(canProcess())
		{
			this.mixerProcess =0;
			if(this.mixerItemStacks!=null)
				this.mixerItemStacks[0].stackSize--;
			if(this.mixerItemStacks[0].stackSize==0)
				this.mixerItemStacks[0]=null;

			for(byte i=2;i<this.mixerItemStacks.length;i++)
			{
				if(this.mixerItemStacks[i]!=null)
					this.mixerItemStacks[i].stackSize--;
				if(this.mixerItemStacks[i].stackSize==0)
					this.mixerItemStacks[i]=null;
			}

			if(this.mixerItemStacks[1]!=null)
				this.mixerItemStacks[1].stackSize++;
			else
				this.mixerItemStacks[1]=tryProcess();
		}
	}

	public ItemStack tryProcess()
	{
		ItemStack ret=new ItemStack(new Foods.MixedSweet(),1);
		NBTTagCompound nbt=new NBTTagCompound();

		byte[] tastes=new byte[5];
		int antiD=0;
		int antiP=0;
		int exp=0;
		int expl=0;
		int[] mps=new int[6];
		int mpl=0;

		ArrayList<Integer> pids=new ArrayList<Integer>();
		ArrayList<Integer> pts=new ArrayList<Integer>();
		ArrayList<Integer> pls=new ArrayList<Integer>();
		ArrayList<Integer> prs=new ArrayList<Integer>();

		for(byte i=2;i<this.mixerItemStacks.length;i++)
		{
			Item item;
			Foods.Berry berry;
			if(mixerItemStacks[i]!=null && (item=mixerItemStacks[i].getItem()) instanceof Foods.Berry)
			{
				berry=(Foods.Berry)item;

				antiD+=berry.antiD;
				antiP+=berry.antiP;
				exp+=berry.exp;
				expl+=berry.expl;
				if(berry.mpl>mpl) mpl=berry.mpl;

				mps[5]+=berry.mps[5];
				for(byte i1=0;i1<5;i1++)
				{
					tastes[i1]+=berry.tastes[i1];
					mps[i1]+=berry.mps[i1];
				}

				for(byte i2=0;i2<berry.pids.length;i2++)
				{
					int pid_this_time=berry.pids[i2];
					int pts_this_time=berry.pts[i2];
					int pls_this_time=berry.pls[i2];
					int prs_this_time=berry.prs[i2];
					int pid_loc_found=-1;
					if((pid_loc_found=pids.indexOf(pid_this_time))>=0)
					{
						if(pls.get(pid_loc_found)<pls_this_time)
						{
							pts.set(pid_loc_found,pts_this_time);
							pls.set(pid_loc_found,pls_this_time);
							prs.set(pid_loc_found,prs_this_time);
						}
						else if(pls.get(pid_loc_found)==pls_this_time)
						{
							int pts_found=pts.get(pid_loc_found);
							int prs_found=prs.get(pid_loc_found);
							pts.set(pid_loc_found,pts_found>pts_this_time?pts_found:pts_this_time);
							prs.set(pid_loc_found,prs_found>prs_this_time?prs_found:prs_this_time);
						}
					}
					else
					{
						pids.add(pid_this_time);
						pts.add(pts_this_time);
						prs.add(prs_this_time);
						pls.add(pls_this_time);
					}
				}
			}
		}
		if(!nbt.hasKey("tag"))
		{
			nbt.setTag("tag",new NBTTagCompound());
		}
		NBTTagCompound tag=(NBTTagCompound)nbt.getTag("tag");
		tag.setInteger("exp",exp);
		tag.setInteger("expl",expl);
		tag.setByteArray("tastes",tastes);
		tag.setInteger("antiD",antiD);
		tag.setInteger("antiP",antiP);
		tag.setInteger("mpl",mpl);
		tag.setIntArray("mps",mps);

		int pidSize=pids.size();
		int[] pids_to_add=new int[pidSize];
		int[] pls_to_add=new int[pidSize];
		int[] prs_to_add=new int[pidSize];
		int[] pts_to_add=new int[pidSize];
		for(byte i3=0;i3<pidSize;i3++)
		{
			pids_to_add[i3]=pids.get(i3);
			pls_to_add[i3]=pls.get(i3);
			prs_to_add[i3]=prs.get(i3);
			pts_to_add[i3]=pts.get(i3);
		}
		tag.setIntArray("pids",pids_to_add);
		tag.setIntArray("pts",pts_to_add);
		tag.setIntArray("pls",pls_to_add);
		tag.setIntArray("prs",prs_to_add);

		nbt.setTag("tag",tag);

		ret.readFromNBT(nbt);
		return ret;
	}
}