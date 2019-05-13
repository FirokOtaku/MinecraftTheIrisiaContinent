package firok.irisia.tileentity;

import firok.irisia.block.BlockConverter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;

import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockConverterTE extends TileEntity implements IEssentiaTransport
{
	public String type=null;
	public BlockConverter.BlockConverterBlock converter;
	public ForgeDirection facing=ForgeDirection.UNKNOWN;
	public Aspect aspect;
	public int amount;
	public static final int MaxAmount=64;

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.type=nbt.getString("type");
		this.converter=BlockConverter.BlockConverterBlock.getConverter(this.type);
		this.aspect=nbt.hasKey("aspect")?Aspect.getAspect(nbt.getString("aspect")):null;
		this.amount=nbt.getInteger("amount");
		this.facing= ForgeDirection.getOrientation(nbt.getInteger("facing"));
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setString("type",this.type);
		if(aspect!=null) nbt.setString("aspect",aspect.getTag());
		nbt.setInteger("amount",amount);
		nbt.setInteger("facing",facing.ordinal());
	}

	public BlockConverterTE(World world, String type, int meta)
	{
		this.worldObj=world;
		this.type=type;
		this.converter=BlockConverter.BlockConverterBlock.getConverter(type);
		this.facing=ForgeDirection.getOrientation(meta);
	}
	public BlockConverterTE(World world,String type, int meta,int x,int y,int z)
	{
		this(world,type,meta);
		this.xCoord=x;
		this.yCoord=y;
		this.zCoord=z;
	}
	public static final int IntervalTick=2;
//	@Override
//	public boolean canUpdate()
//	{
//		return true;
//	}
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(worldObj.getTotalWorldTime()%IntervalTick==0)
		{
			Aspect aspectDrawed=drawEssentiaFrom(facing.getOpposite(),converter.aspectNeed);
			if(aspectDrawed!=null)
			{
				addEssentia(aspectDrawed,1,ForgeDirection.UNKNOWN);
			}

			if(hasEnoughAspect() && hasCertainBlock())
			{
				transBlock();
			}
		}
	}

	private IEssentiaTransport getConnectableTile(ForgeDirection dir) // 查看指定方向能否吸取源质
	{
		TileEntity ret=ThaumcraftApiHelper.getConnectableTile(worldObj, xCoord, yCoord, zCoord, dir);
		return ret!=null && ((IEssentiaTransport)ret).canOutputTo(dir.getOpposite())?
				(IEssentiaTransport)ret:null;
	}
	private Aspect drawEssentiaFrom(ForgeDirection dir,Aspect aspect2draw) // 从一个方向的管道吸取指定类型的源质
	{ // 如果吸到了 就返回吸到的源质
		if(this.amount>=MaxAmount) return null;
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


	private void transBlock()
	{
		this.amount-=converter.amountNeed;
		if(amount<0) amount=0;

		worldObj.setBlock(
				xCoord+facing.offsetX,
				yCoord+facing.offsetY,
				zCoord+facing.offsetZ,
				converter.targetBlock,converter.targetMeta<0?0:converter.targetMeta,2);
	}

	public boolean hasEnoughAspect()
	{
		return this.aspect==converter.aspectNeed&&this.amount>=converter.amountNeed;
	}
	public boolean hasCertainBlock()
	{
		return worldObj.getBlock(
				xCoord+facing.offsetX,
				yCoord+facing.offsetY,
				zCoord+facing.offsetZ)
				==converter.originBlock
				&&
				(converter.originMeta<0 || worldObj.getBlockMetadata(
						xCoord+facing.offsetX,
						yCoord+facing.offsetY,
						zCoord+facing.offsetZ)
						==converter.originMeta);
	}


	@Override
	public boolean isConnectable(ForgeDirection forgeDirection)
	{
		return forgeDirection==facing.getOpposite();
	}
	@Override
	public boolean canInputFrom(ForgeDirection forgeDirection)
	{
		return isConnectable(forgeDirection);
	}
	@Override
	public boolean canOutputTo(ForgeDirection forgeDirection)
	{
		return false;
	}
	@Override
	public void setSuction(Aspect aspect, int i)
	{}

	@Override
	public Aspect getSuctionType(ForgeDirection forgeDirection)
	{
		return converter!=null?converter.aspectNeed:null;
	}

	@Override
	public int getSuctionAmount(ForgeDirection forgeDirection)
	{
		return canInputFrom(forgeDirection)?64:0;
	}

	@Override
	public int takeEssentia(Aspect aspect, int i, ForgeDirection forgeDirection)
	{
		if(aspect==this.aspect && this.amount>0)
		{
			this.amount--;
			return 1;
		}
		return 0;
	}

	@Override
	public int addEssentia(Aspect aspect, int i, ForgeDirection forgeDirection)
	{
		if(this.aspect==aspect)
		{
			this.amount++;
		}
		else // this.aspect != aspect
		{
			this.aspect=aspect;
			this.amount=1;
		}
		return 1;
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection forgeDirection)
	{
		return this.aspect;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection forgeDirection)
	{
		return amount;
	}

	@Override
	public int getMinimumSuction()
	{
		return 0;
	}

	@Override
	public boolean renderExtendedTube()
	{
		return false;
	}
}
