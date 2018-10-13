package firok.irisia.block;

import firok.irisia.Keys;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
}
