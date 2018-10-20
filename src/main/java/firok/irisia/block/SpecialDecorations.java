package firok.irisia.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Keys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

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

	public final static Block ArcaneShield;
	static
	{
		ArcaneShield=new Block(Material.glass)
		{
			{
				// this.setTickRandomly(true);
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
}
