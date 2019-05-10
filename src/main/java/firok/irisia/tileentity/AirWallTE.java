package firok.irisia.tileentity;

import firok.irisia.Irisia;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class AirWallTE extends TileEntity
{
	private String owner=null;
	public String getOwner(){return owner;}
	public void setOwner(String o){owner=o;}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		owner=nbt.hasKey("owner")?nbt.getString("owner"):null;
	}
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		if(owner!=null)nbt.setString("owner",owner);
	}

	// constructor
	public AirWallTE()
	{
		this(null);
	}
	public AirWallTE(String owner)
	{
		this.owner=owner;
	}
}
