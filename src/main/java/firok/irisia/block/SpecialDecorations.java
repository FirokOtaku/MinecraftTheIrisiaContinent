package firok.irisia.block;

import firok.irisia.Keys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemEldritchObject;

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

	public final static SpecialCobweb PoisonouCobweb;
	public final static SpecialCobweb DirtyCobweb;
	public final static SpecialCobweb LavaCobweb;
	static
	{
		PoisonouCobweb=new SpecialCobweb();
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
			if(this==PoisonouCobweb)
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
}
