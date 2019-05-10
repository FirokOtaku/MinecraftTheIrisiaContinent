package firok.irisia.tileentity;

import firok.irisia.item.Tools;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaTransport;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class OrientedMetalInfusionerTE extends TileEntity implements IEssentiaTransport // ,IAspectContainer
{
	public OrientedMetalInfusionerTE(World world,int x,int y,int z)
	{
		this(world);
		this.xCoord=x;
		this.yCoord=y;
		this.zCoord=z;
	}
	public OrientedMetalInfusionerTE(World world)
	{
		this();
		this.worldObj=world;
	}
	public OrientedMetalInfusionerTE()
	{
		super();
	}

	public static final int intervalTickDraw =2; // 吸取源质的时间间隔
	public static final int intervalTickTrans =10;

	ItemStack stackOrienter; // 转换器
	public ItemStack getStackOrienter()
	{
		return stackOrienter;
	}
	public void setStackOrienter(ItemStack orienter)
	{
		if(orienter==null) this.stackOrienter=null;
		else if(!(orienter.getItem() instanceof Tools.MetalInfusionOrienter)) return;
		else this.stackOrienter=orienter;
	}
	public boolean hasOrienter(){return stackOrienter!=null;}
	public Tools.MetalInfusionOrienter getOrienter(){return stackOrienter==null?null:(Tools.MetalInfusionOrienter)stackOrienter.getItem();}

	AspectList aspects=new AspectList(); // 机器里面所有的源质
	public AspectList getAspects(){return aspects.copy();}
	private boolean exist=true;
	private int process=0; // 机器运转进度
	private boolean isProcessing=false;
	public boolean hasEnoughAspects() // 判断有没有足够的源质
	{
		return getAspectNeed()==null;
	}
	public Aspect getAspectNeed() // 获取下一种需要的源质
	{
		if(hasOrienter())
		{
			Tools.MetalInfusionOrienter orienter=getOrienter();
			for(Aspect aspectNeed:orienter.costAspects.getAspects())
			{
				if(this.aspects.getAmount(aspectNeed)<orienter.costAspects.getAmount(aspectNeed))
				{
					return aspectNeed;
				}
			}
		}
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		aspects=new AspectList();
		if(nbt.hasKey("aspects"))
			aspects.readFromNBT(nbt.getCompoundTag("aspects"));
		stackOrienter=nbt.hasKey("orienter")?
				ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("orienter")):
				null;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagCompound nbtAspects=new NBTTagCompound();
		aspects.writeToNBT(nbtAspects);
		nbt.setTag("aspects",nbtAspects);
		if(stackOrienter!=null)
		{
			NBTTagCompound nbtOrienter=new NBTTagCompound();
			stackOrienter.writeToNBT(nbtOrienter);
			nbt.setTag("orienter",nbtOrienter);
		}
	}

	private void transBlock()
	{
		if(hasOrienter())
		{
			Block blockBelow=worldObj.getBlock(this.xCoord,this.yCoord+1,this.zCoord);
			if(blockBelow==Blocks.iron_block)
			{
				Tools.MetalInfusionOrienter orienter=getOrienter();
				worldObj.setBlock(xCoord,yCoord+1,zCoord,
						orienter.productBlock,orienter.productMeta,2);
				worldObj.markBlockForUpdate(this.xCoord,this.yCoord+1,this.zCoord);

				for(Aspect aspectNeed:orienter.costAspects.getAspects())
				{
					this.aspects.add(aspectNeed,-orienter.costAspects.getAmount(aspectNeed));
				}
			}
		}
	}
	public void remove()
	{
		if(!exist) return;
		exist=false;
	}

	public void updateEntity()
	{
		super.updateEntity();
		if(worldObj.isRemote) return;
		long time=worldObj.getTotalWorldTime();
		if(time% intervalTickDraw ==0) // 吸取一次源质
		{
			Aspect aspectDrawed=drawEssentia();
			if(aspectDrawed!=null)
			{
				aspects.add(aspectDrawed,1);
			}

			if(hasEnoughAspects())
			{
				transBlock();
			}
		}
	}
	/**
	 * Is this tile able to connect to other vis users/sources on the specified side?
	 * @param face
	 * @return
	 */
	public boolean isConnectable(ForgeDirection face)
	{
		return face==ForgeDirection.EAST
		|| face==ForgeDirection.WEST
		|| face==ForgeDirection.NORTH
		|| face==ForgeDirection.SOUTH;
	}

	/**
	 * Is this side used to input essentia?
	 * @param face
	 * @return
	 */
	public boolean canInputFrom(ForgeDirection face)
	{
		return isConnectable(face);
//		return false;
	}

	/**
	 * Is this side used to output essentia?
	 * @param face
	 * @return
	 */
	public boolean canOutputTo(ForgeDirection face)
	{
		return false;
//		return face==ForgeDirection.UP;
	}


	public void setSuction(Aspect aspect, int amount)
	{
	}
	public Aspect getSuctionType(ForgeDirection face)
	{
		return getAspectNeed();
	}
	public int getSuctionAmount(ForgeDirection face) {
		return isConnectable(face)?128:0;
	}
	/**
	 * remove the specified amount of essentia from this transport tile
	 * @return how much was actually taken
	 */
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		if(aspects.getAmount(aspect)>0)
		{
			aspects.add(aspect,-1);
			return 1;
		}
		return 0;
	}
	/**
	 * add the specified amount of essentia to this transport tile
	 * @return how much was actually added
	 */
	public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		aspects.add(aspect,1);
		return 1;
	}
	/**
	 * What type of essentia this contains
	 * @param face
	 * @return
	 */
	public Aspect getEssentiaType(ForgeDirection face)
	{
		return getAspectNeed();
	}
	/**
	 * How much essentia this block contains
	 */
	public int getEssentiaAmount(ForgeDirection loc) {
		return aspects.getAmount(getAspectNeed());
	}
	/**
	 * Essentia will not be drawn from this container unless the suction exceeds this amount.
	 * @return the amount
	 */
	public int getMinimumSuction()
	{
		return 0;
	}
	/**
	 * Return true if you want the conduit to extend a little further into the block.
	 * Used by jars and alembics that have smaller than normal hitboxes
	 * @return
	 */
	public boolean renderExtendedTube()
	{
		return false;
	}

	private IEssentiaTransport getConnectableTile(ForgeDirection dir) // 查看指定方向能否吸取源质
	{
		TileEntity ret=ThaumcraftApiHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, dir);
		return ret!=null && ((IEssentiaTransport)ret).canOutputTo(dir.getOpposite())?
				(IEssentiaTransport)ret:null;
	}
	private Aspect drawEssentiaFrom(ForgeDirection dir,Aspect aspect2draw) // 从一个方向的管道吸取指定类型的源质
	{ // 如果吸到了 就返回吸到的源质
		IEssentiaTransport tile2draw=getConnectableTile(dir);
		if(tile2draw!=null)
		{
			ForgeDirection dirOp=dir.getOpposite();
			if(tile2draw.getSuctionAmount(dirOp)<this.getSuctionAmount(dir))
			{
				if(aspect2draw!=null)
				{
					if(tile2draw.takeEssentia(aspect2draw, 1, dirOp) > 0)
						return aspect2draw;
				}
			}
		}
		return null;
	}
	private Aspect drawEssentia() // 尝试从周围吸取源质
	{
		Aspect aspect2draw=getAspectNeed();
		if(aspect2draw!=null)
		{
			if(drawEssentiaFrom(EAST,aspect2draw)!=null||
				drawEssentiaFrom(WEST,aspect2draw)!=null||
				drawEssentiaFrom(SOUTH,aspect2draw)!=null||
				drawEssentiaFrom(NORTH,aspect2draw)!=null)
				return aspect2draw;
		}
		return null;
	}
}