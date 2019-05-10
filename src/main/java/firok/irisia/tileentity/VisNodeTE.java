package firok.irisia.tileentity;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.research.ScanResult;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.items.ItemCompassStone;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.tiles.TileNode;

import java.awt.*;
import java.util.*;
import java.util.List;
// fixme 这个te的初始化有问题 还不能初始化灵气
public class VisNodeTE extends TileEntity implements INode, IWandable
{
	long lastActive = 0L;
	AspectList aspects = new AspectList();
	AspectList aspectsBase = new AspectList();
	public static HashMap<String, ArrayList<Integer>> locations = new HashMap();
	private NodeType nodeType;
	private NodeModifier nodeModifier;
	int count;
	int regeneration;
	int wait;
	String id;
	byte nodeLock;
	boolean catchUp;
	public Entity drainEntity;
	public MovingObjectPosition drainCollision;
	public int drainColor;
	public Color targetColor;
	public Color color;

	public VisNodeTE() {
		this.nodeType = NodeType.NORMAL;
		this.nodeModifier = null;
		this.count = 0;
		this.regeneration = -1;
		this.wait = 0;
		this.id = null;
		this.nodeLock = 0;
		this.catchUp = false;
		this.drainEntity = null;
		this.drainCollision = null;
		this.drainColor = 16777215;
		this.targetColor = new Color(16777215);
		this.color = new Color(16777215);
	}
	public VisNodeTE(Random random)
	{
		this();
	}

	public String getId() {
		if (this.id == null) {
			this.id = this.generateId();
		}

		return this.id;
	}

	public String generateId() {
		this.id = this.worldObj.provider.dimensionId + ":" + this.xCoord + ":" + this.yCoord + ":" + this.zCoord;
		if (this.worldObj != null && locations != null) {
			ArrayList<Integer> t = new ArrayList();
			t.add(this.worldObj.provider.dimensionId);
			t.add(this.xCoord);
			t.add(this.yCoord);
			t.add(this.zCoord);
			locations.put(this.id, t);
		}

		return this.id;
	}

	public void onChunkUnload() {
		if (locations != null) {
			locations.remove(this.id);
		}

		super.onChunkUnload();
	}


	public void updateEntity() {
		;
	}

	public void nodeChange() {
		this.regeneration = -1;
		this.markDirty();
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	public boolean canUpdate() {
		return true;
	}

	public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
		return -1;
	}

	public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
		player.setItemInUse(wandstack, 2147483647);
		ItemWandCasting wand = (ItemWandCasting)wandstack.getItem();
		wand.setObjectInUse(wandstack, this.xCoord, this.yCoord, this.zCoord);
		return wandstack;
	}

	public AspectList getAspects() {
		return this.aspects;
	}

	public AspectList getAspectsBase() {
		return this.aspectsBase;
	}

	public void setAspects(AspectList aspects) {
		this.aspects = aspects;
		this.aspectsBase = aspects.copy();
	}

	public int addToContainer(Aspect aspect, int amount) {
		int left = amount + this.aspects.getAmount(aspect) - this.aspectsBase.getAmount(aspect);
		left = left > 0 ? left : 0;
		this.aspects.add(aspect, amount - left);
		return left;
	}

	public boolean takeFromContainer(Aspect aspect, int amount) {
		return this.aspects.reduce(aspect, amount);
	}

	public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve) {
		int min = preserve ? 1 : 0;
		ArrayList<Aspect> validaspects = new ArrayList();
		Aspect[] arr$ = this.aspects.getAspects();
		int len$ = arr$.length;

		for(int i$ = 0; i$ < len$; ++i$) {
			Aspect prim = arr$[i$];
			if (filter.getAmount(prim) > 0 && this.aspects.getAmount(prim) > min) {
				validaspects.add(prim);
			}
		}

		if (validaspects.size() == 0) {
			return null;
		} else {
			Aspect asp = (Aspect)validaspects.get(this.worldObj.rand.nextInt(validaspects.size()));
			if (asp != null && this.aspects.getAmount(asp) > min) {
				return asp;
			} else {
				return null;
			}
		}
	}

	public NodeType getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void setNodeModifier(NodeModifier nodeModifier) {
		this.nodeModifier = nodeModifier;
	}

	public NodeModifier getNodeModifier() {
		return this.nodeModifier;
	}

	public int getNodeVisBase(Aspect aspect) {
		return this.aspectsBase.getAmount(aspect);
	}

	public void setNodeVisBase(Aspect aspect, short nodeVisBase) {
		if (this.aspectsBase.getAmount(aspect) < nodeVisBase) {
			this.aspectsBase.merge(aspect, nodeVisBase);
		} else {
			this.aspectsBase.reduce(aspect, this.aspectsBase.getAmount(aspect) - nodeVisBase);
		}

	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
//		this.lastActive = nbttagcompound.getLong("lastActive");
//		AspectList al = new AspectList();
//		NBTTagList tlist = nbttagcompound.getTagList("AspectsBase", 10);
//
//		for(int j = 0; j < tlist.tagCount(); ++j) {
//			NBTTagCompound rs = tlist.getCompoundTagAt(j);
//			if (rs.hasKey("key")) {
//				al.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
//			}
//		}
//
//		Short oldBase = nbttagcompound.getShort("nodeVisBase");
//		this.aspectsBase = new AspectList();
//		if (oldBase > 0 && al.size() == 0) {
//			Aspect[] arr$ = this.aspects.getAspects();
//			int len$ = arr$.length;
//
//			for(int i$ = 0; i$ < len$; ++i$) {
//				Aspect a = arr$[i$];
//				this.aspectsBase.merge(a, oldBase);
//			}
//		} else {
//			this.aspectsBase = al.copy();
//		}
//
//		int regen = 600;
//		if (this.getNodeModifier() != null) {
//			switch(this.getNodeModifier()) {
//				case BRIGHT:
//					regen = 400;
//					break;
//				case PALE:
//					regen = 900;
//					break;
//				case FADING:
//					regen = 0;
//			}
//		}
//
//		long ct = System.currentTimeMillis();
//		int inc = regen * 75;
//		if (regen > 0 && this.lastActive > 0L && ct > this.lastActive + (long)inc) {
//			this.catchUp = true;
//		}

	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
//		nbttagcompound.setLong("lastActive", this.lastActive);
//		NBTTagList tlist = new NBTTagList();
//		nbttagcompound.setTag("AspectsBase", tlist);
//		Aspect[] arr$ = this.aspectsBase.getAspects();
//		int len$ = arr$.length;
//
//		for(int i$ = 0; i$ < len$; ++i$) {
//			Aspect aspect = arr$[i$];
//			if (aspect != null) {
//				NBTTagCompound f = new NBTTagCompound();
//				f.setString("key", aspect.getTag());
//				f.setInteger("amount", this.aspectsBase.getAmount(aspect));
//				tlist.appendTag(f);
//			}
//		}

	}

	public void readCustomNBT(NBTTagCompound nbttagcompound) {
		this.id = nbttagcompound.getString("nodeId");
		if (this.worldObj != null && locations != null) {
			ArrayList<Integer> t = new ArrayList();
			t.add(this.worldObj.provider.dimensionId);
			t.add(this.xCoord);
			t.add(this.yCoord);
			t.add(this.zCoord);
			locations.put(this.id, t);
		}

		this.setNodeType(NodeType.values()[nbttagcompound.getByte("type")]);
		byte mod = nbttagcompound.getByte("modifier");
		if (mod >= 0) {
			this.setNodeModifier(NodeModifier.values()[mod]);
		} else {
			this.setNodeModifier((NodeModifier)null);
		}

		this.aspects.readFromNBT(nbttagcompound);
		String de = nbttagcompound.getString("drainer");
		if (de != null && de.length() > 0 && this.getWorldObj() != null) {
			this.drainEntity = this.getWorldObj().getPlayerEntityByName(de);
			if (this.drainEntity != null) {
				this.drainCollision = new MovingObjectPosition(this.xCoord, this.yCoord, this.zCoord, 0, Vec3.createVectorHelper(this.drainEntity.posX, this.drainEntity.posY, this.drainEntity.posZ));
			}
		}

		this.drainColor = nbttagcompound.getInteger("draincolor");
	}

	public void writeCustomNBT(NBTTagCompound nbttagcompound) {
		if (this.id == null) {
			this.id = this.generateId();
		}

		if (this.worldObj != null && locations != null) {
			ArrayList<Integer> t = new ArrayList();
			t.add(this.worldObj.provider.dimensionId);
			t.add(this.xCoord);
			t.add(this.yCoord);
			t.add(this.zCoord);
			locations.put(this.id, t);
		}

		nbttagcompound.setString("nodeId", this.id);
		nbttagcompound.setByte("type", (byte)this.getNodeType().ordinal());
		nbttagcompound.setByte("modifier", this.getNodeModifier() == null ? -1 : (byte)this.getNodeModifier().ordinal());
		this.aspects.writeToNBT(nbttagcompound);
		if (this.drainEntity != null && this.drainEntity instanceof EntityPlayer) {
			nbttagcompound.setString("drainer", this.drainEntity.getCommandSenderName());
		}

		nbttagcompound.setInteger("draincolor", this.drainColor);
	}

	public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
		boolean mfu = false;
		ItemWandCasting wand = (ItemWandCasting)wandstack.getItem();
		MovingObjectPosition movingobjectposition = EntityUtils.getMovingObjectPositionFromPlayer(this.worldObj, player, true);
		int tap;
		int g;
		int b;
		if (movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			tap = movingobjectposition.blockX;
			g = movingobjectposition.blockY;
			b = movingobjectposition.blockZ;
			if (tap != this.xCoord || g != this.yCoord || b != this.zCoord) {
				player.stopUsingItem();
			}
		} else {
			player.stopUsingItem();
		}

		int amt;
		int rem;
		if (count % 5 == 0) {
			tap = 1;
			if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER1")) {
				++tap;
			}

			if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER2")) {
				++tap;
			}

			boolean preserve = !player.isSneaking() && ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODEPRESERVE") && !wand.getRod(wandstack).getTag().equals("wood") && !wand.getCap(wandstack).getTag().equals("iron");
			boolean success = false;
			Aspect aspect = null;
			if ((aspect = this.chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve)) != null) {
				amt = this.getAspects().getAmount(aspect);
				if (tap > amt) {
					tap = amt;
				}

				if (preserve && tap == amt) {
					--tap;
				}

				if (tap > 0) {
					rem = wand.addVis(wandstack, aspect, tap, !this.worldObj.isRemote);
					if (rem < tap) {
						this.drainColor = aspect.getColor();
						if (!this.worldObj.isRemote) {
							this.takeFromContainer(aspect, tap - rem);
							mfu = true;
						}

						success = true;
					}
				}
			}

			if (success) {
				this.drainEntity = player;
				this.drainCollision = movingobjectposition;
				this.targetColor = new Color(this.drainColor);
			} else {
				this.drainEntity = null;
				this.drainCollision = null;
			}

			if (mfu) {
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				this.markDirty();
			}
		}

		if (player.worldObj.isRemote) {
			tap = this.targetColor.getRed();
			g = this.targetColor.getGreen();
			b = this.targetColor.getBlue();
			int r2 = this.color.getRed() * 4;
			amt = this.color.getGreen() * 4;
			rem = this.color.getBlue() * 4;
			this.color = new Color((tap + r2) / 5, (g + amt) / 5, (b + rem) / 5);
		}

	}

	public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
		this.drainEntity = null;
		this.drainCollision = null;
	}

	public boolean takeFromContainer(AspectList ot) {
		return false;
	}

	public boolean doesContainerContainAmount(Aspect tag, int amount) {
		return false;
	}

	public boolean doesContainerContain(AspectList ot) {
		return false;
	}

	public int containerContains(Aspect tag) {
		return 0;
	}

	public boolean doesContainerAccept(Aspect tag) {
		return true;
	}
}
