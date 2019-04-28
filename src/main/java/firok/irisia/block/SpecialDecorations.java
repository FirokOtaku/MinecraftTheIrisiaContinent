package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.inventory.MagicBagGui;
import firok.irisia.tileentity.AirWallTE;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.common.blocks.BlockAiry;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.List;
import java.util.Random;

public class SpecialDecorations
{
//	public static Block MazeCore;
//	static
//	{
//		//MazeCore=new Block
//	}
	public final static Block AncientDoorKeyhole;
	public final static Block AncientDoor;
	static
	{
		AncientDoor=new Block(Material.iron)
		{
			{
				this.setBlockUnbreakable();
				this.setResistance(36000);
			}
		};
		AncientDoorKeyhole =new Block(Material.iron)
		{
			{
				this.setBlockUnbreakable();
				this.setResistance(36000);
			}

			@Override
			public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ)
			{
				if(world.isRemote)
					return true;

				ItemStack held=player.getHeldItem();
				if(held!=null && held.getItem()==ConfigItems.itemEldritchObject && held.getItemDamage()==2)
				{
					if(!player.capabilities.isCreativeMode)
						held.stackSize--;

					world.setBlockToAir(x,y,z);
					// open the door ( remove all nearby door blocks
					for(int i=x-1;i<=x+1;i++)
					{
						for(int j=y-1;j<=y+1;j++)
						{
							for(int k=z-1;k<=z+1;k++)
							{
								if(world.getBlock(i,j,k)==AncientDoor)
									world.setBlockToAir(i,j,k);
							}
						}
					}
					world.playSoundEffect(x,y,z, Keys.SoundStoneFall,1,1);

					// play sound
				}

				return true;
			}
		};

	}

	public final static SpecialCobweb PoisonousCobweb;
	public final static SpecialCobweb DirtyCobweb;
	public final static SpecialCobweb LavaCobweb;
	public /*final*/ static Block CirrusGrass;
	static
	{
		PoisonousCobweb =new SpecialCobweb();
		DirtyCobweb=new SpecialCobweb();
		LavaCobweb=new SpecialCobweb();
	}
	public static class SpecialCobweb extends BlockWeb
	{
		public SpecialCobweb()
		{
			super();
			setLightOpacity(1);
		}

		@Override
		public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
		{
			entity.setInWeb();
			if(this== PoisonousCobweb)
			{
				if(entity.ticksExisted%20==0 && entity instanceof EntityLivingBase)
				{
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id,80,0));
				}
			}
			else if(this==LavaCobweb)
			{
				if(entity.ticksExisted%20==0 && entity instanceof EntityLivingBase)
				{
					entity.setFire(4);
				}
			}

		}
		@Override
		public float getBlockHardness(World world, int x, int y, int z)
		{
			return this==DirtyCobweb?6.0f:4.0f;
		}
		@Override
		public boolean isToolEffective(String type, int metadata)
		{
			return "sword".equals(type);
		}
	}

	public final static Catnip Catnip=new Catnip();
	public static class Catnip extends BlockGlass
	{
		public Catnip()
		{
			super(Material.glass,true);
			this.setTickRandomly(true);
		}

		@Override
		public void updateTick(World world, int x, int y, int z, Random rand)
		{
			if(!world.isRemote && rand.nextFloat()<0.05)
			{
				EntityOcelot cat=new EntityOcelot(world);
				cat.setPositionAndUpdate(x,y,z);
				world.spawnEntityInWorld(cat);
			}
		}
	}

	public final static Block ArcaneShield;
	static
	{
		ArcaneShield=new Block(Material.glass)
		{
			{
				// this.setTickRandomly(true);
				//ResearchManager
				this.setLightLevel(6);
				this.setLightOpacity(8);
			}

			// 碰撞箱
			public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity)
			{
				if (!(entity instanceof EntityPlayer))
				{
					super.addCollisionBoxesToList(world, x, y, z, box, list, entity);
				}

			}
			public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
			{
				return super.getCollisionBoundingBoxFromPool(world, x, y, z);
			}
			public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
			{
				return super.getSelectedBoundingBoxFromPool(world,x,y,z);
			}

			public int quantityDropped(Random rand)
			{
				return 0;
			}
			@SideOnly(Side.CLIENT)
			public int getRenderBlockPass()
			{
				return 0;
			}
			public boolean renderAsNormalBlock()
			{
				return false;
			}
			protected boolean canSilkHarvest()
			{
				return false;
			}
		};
	}

	public final static Block MagicLight;
	static
	{
		MagicLight=new BlockAir()
		{
			{
				this.setTickRandomly(true);
				this.setBlockBounds(0.3f,0.3f,0.3f,
						0.7f,0.7f,0.7f);
				this.setLightLevel(15);
			}
			public boolean isOpaqueCube()
			{
				return false;
			}


			@Override
			public void updateTick(World world, int x, int y, int z, Random rand)
			{
				if(!world.isRemote)
				{
					int meta=world.getBlockMetadata(x,y,z);
					if(meta==0)
					{
						world.setBlockToAir(x,y,z);
					}
				}
			}

		};
	}
	public final static Block AirWall;
	static
	{
		AirWall=new BlockContainer(Material.air)
		{
			{
				this.setTickRandomly(true);
				this.setBlockBounds(0,0,0,1,1,1);
			}
			@Override
			public void updateTick(World world, int x, int y, int z, Random rand)
			{
				world.setBlockToAir(x,y,z);
			}

			public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
			{
				TileEntity te=world.getTileEntity(x,y,z);
				if(te==null)
				{
					te=createNewTileEntity(world,0);
					((AirWallTE)te).setOwner(entity.getCommandSenderName());
					world.setTileEntity(x,y,z,te);
				}
				else
				{
					((AirWallTE)te).setOwner(entity.getCommandSenderName());
				}
			}



			// 碰撞箱
			public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity)
			{
				if(!(entity instanceof EntityPlayer))
				{
					super.addCollisionBoxesToList(world,x,y,z,box,list,entity);
				}
				else
				{
					TileEntity te=world.getTileEntity(x,y,z);
					if(te==null ||
							!(te instanceof AirWallTE)||
							!entity.getCommandSenderName().equals(((AirWallTE) te).getOwner()))
					{
						super.addCollisionBoxesToList(world,x,y,z,box,list,entity);
					}
				}
			}
			@SideOnly(Side.CLIENT)
			public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
			{
				return AxisAlignedBB.getBoundingBox(
						(double)x + this.minX, (double)y + this.minY, (double)z + this.minZ,
						(double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
			}

			public int quantityDropped(Random rand)
			{
				return 0;
			}
			@SideOnly(Side.CLIENT)
			public int getRenderBlockPass()
			{
				return 0;
			}
			public boolean renderAsNormalBlock()
			{
				return false;
			}
			protected boolean canSilkHarvest()
			{
				return false;
			}

			@Override
			public TileEntity createNewTileEntity(World world, int meta)
			{
				return new AirWallTE();
			}
		};
	}
}
