package firok.irisia.tileentity;

import firok.irisia.Irisia;
import firok.irisia.world.multi.Structures;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.config.ConfigBlocks;

import java.util.Random;

public class AuraCompresserTE extends TileEntity implements IEssentiaTransport // ,IAspectContainer
{
	// air
	// earth
	// fire
	// water
	// order
	// entropy
	private int vis=0;
	private String visType=null;
	private boolean exist=true;

	private int process=0;
	private boolean isProcessing=false;

	private static final int maxVis=64;

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.vis=nbt.getInteger("vis");
		String tempType=nbt.getString("type");
		this.visType=tempType.equals("null")?null:tempType;

	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("vis",this.vis);
		nbt.setString("type",this.visType==null?"null":this.visType);
	}

	private void transBlock(Block block)
	{
		Block blockBelow=worldObj.getBlock(this.xCoord,this.yCoord-1,this.zCoord);
		if(blockBelow==Blocks.stone)
		{
			worldObj.setBlock(this.xCoord,this.yCoord-1,this.zCoord,block);
			worldObj.markBlockForUpdate(this.xCoord,this.yCoord-1,this.zCoord);
		}
	}

	public void remove()
	{
		if(!exist) return;
		exist=false;
		Structures.AuraCompressorBase.buildAt(worldObj,xCoord,yCoord,zCoord);
	}
//	@Override
//	public boolean canUpdate()
//	{
//		return worldObj.getWorldTime()%20==0;
//	}

	public void updateEntity()
	{
		super.updateEntity();
		if (this.worldObj.getWorldTime()%20==0 && !this.worldObj.isRemote)
		{
			if(maxVis-vis>=1)
			{
				Aspect as=drawEssentia();
				if(as!=null)
				{
					Irisia.log(worldObj.getWorldTime()/20+" 吸到了 "+as.getTag());
					if(this.visType==null)
					{
						Irisia.log(worldObj.getWorldTime()/20+" 设定type "+as.getTag());
						this.visType=as.getTag();
					}
					this.vis++;
					Irisia.log(worldObj.getWorldTime()/20+" 目前有 "+vis);
				}
			}

			Block blockBelow=worldObj.getBlock(this.xCoord,this.yCoord-1,this.zCoord);

			if(blockBelow== Blocks.stone && vis>=8)
			{
				vis-=8;
				Irisia.log("当前的类型 : ["+visType+"]");
				switch(this.visType)
				{
					case "ignis":
						transBlock(Blocks.lava);
						break;
					case "aqua":
						transBlock(Blocks.water);
						break;
					case "ordo":
						transBlock(Blocks.quartz_block);
						break;
					case "perditio":
						transBlock(Blocks.sand);
						break;
					case "terra":
						transBlock(Blocks.obsidian);
						break;
					case "aer":
						transBlock(Blocks.dirt);
						break;
				}
				if(vis==0)
					visType=null;
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
		return face==ForgeDirection.UP;
	}

	/**
	 * Is this side used to input essentia?
	 * @param face
	 * @return
	 */
	public boolean canInputFrom(ForgeDirection face)
	{
		return face==ForgeDirection.UP;
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
		return null;
	}
	public int getSuctionAmount(ForgeDirection face) {
		return face==ForgeDirection.UP&&this.vis<maxVis?128:0;
	}
	/**
	 * remove the specified amount of essentia from this transport tile
	 * @return how much was actually taken
	 */
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		if(face== ForgeDirection.UP && aspect.getTag().equals(this.visType))
		{
			if(amount<this.vis)
			{
				this.vis-=amount;
			}
			else // amount >= this.vis
			{
				amount=this.vis;
				this.vis=0;
				this.visType=null;
			}
			return amount;
		}
		return 0;
	}
	/**
	 * add the specified amount of essentia to this transport tile
	 * @return how much was actually added
	 */
	public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		String tag=aspect.getTag();
		if(this.visType==null||this.visType.equals(tag))
		{
			this.visType=tag;
			if(amount+vis>maxVis)
			{
				this.vis=maxVis;
				return maxVis-vis;
			}
			else
			{
				this.vis+=amount;
				return amount;
			}
		}
		return 0;
	}
	/**
	 * What type of essentia this contains
	 * @param face
	 * @return
	 */
	public Aspect getEssentiaType(ForgeDirection face)
	{
		if(visType==null) return null;
		switch (this.visType)
		{
			case "air": return Aspect.AIR;
			case "earth": return Aspect.EARTH;
			case "fire": return Aspect.FIRE;
			case "water": return Aspect.WATER;
			case "order": return Aspect.ORDER;
			case "entropy": return Aspect.ENTROPY;
			default: return null;
		}
	}
	/**
	 * How much essentia this block contains
	 */
	public int getEssentiaAmount(ForgeDirection loc) {
		return this.vis;
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
	public boolean renderExtendedTube() {
		return false;
	}

	private Aspect drawEssentia()
	{
		TileEntity te = ThaumcraftApiHelper
				.getConnectableTile(this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.UP);
		if (te != null)
		{
			IEssentiaTransport ic = (IEssentiaTransport)te;
			if (!ic.canOutputTo(ForgeDirection.DOWN))
			{
				return null;
			}

			if (ic.getSuctionAmount(ForgeDirection.DOWN) < this.getSuctionAmount(ForgeDirection.UP))
			{
				if(this.visType!=null)
				{
					Aspect as=Aspect.getAspect(this.visType);
					if(ic.takeEssentia(as, 1, ForgeDirection.DOWN) == 1)
						return as;
				}
				else // type == null
				{
					if(ic.takeEssentia(Aspect.FIRE, 1, ForgeDirection.DOWN) == 1)
						return Aspect.FIRE;
					else if(ic.takeEssentia(Aspect.WATER, 1, ForgeDirection.DOWN) == 1)
						return Aspect.WATER;
					else if(ic.takeEssentia(Aspect.AIR, 1, ForgeDirection.DOWN) == 1)
						return Aspect.AIR;
					else if(ic.takeEssentia(Aspect.EARTH, 1, ForgeDirection.DOWN) == 1)
						return Aspect.EARTH;
					else if(ic.takeEssentia(Aspect.ORDER, 1, ForgeDirection.DOWN) == 1)
						return Aspect.ORDER;
					else if(ic.takeEssentia(Aspect.ENTROPY, 1, ForgeDirection.DOWN) == 1)
						return Aspect.ENTROPY;
				}

				return null;
			}
		}

		return null;
	}
/*
	// IAspectContainer
	@Override
	public AspectList getAspects()
	{
		return visType==null?null:new AspectList().add(Aspect.getAspect(visType),vis);
	}

	@Override
	public void setAspects(AspectList aspectList)
	{
		Aspect[] pri=aspectList.getPrimalAspects();
		if(pri.length>0)
		{
			this.visType=pri[0].getTag();
			this.vis=aspectList.getAmount(pri[0]);

			if(this.vis>maxVis)
			{
				this.vis=maxVis;
			}
		}
	}

	@Override
	public boolean doesContainerAccept(Aspect aspect)
	{
		return visType==null?false:aspect.getTag().equals(visType);
	}

	@Override
	public int addToContainer(Aspect aspect, int i)
	{

		return 0;
	}

	@Override
	public boolean takeFromContainer(Aspect aspect, int i)
	{
		return false;
	}

	@Override
	public boolean takeFromContainer(AspectList aspectList)
	{
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect aspect, int i)
	{
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList aspectList)
	{
		return false;
	}

	@Override
	public int containerContains(Aspect aspect)
	{
		return aspect.getTag().equals(visType)?vis:0;
	}
	*/
}
