package firok.irisia.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;


public class ArcaneStelas
{
	public final static ArcaneStela StelaSpeed;
	public final static ArcaneStela StelaJump;
	public final static ArcaneStela StelaDigSpeed;
	public final static ArcaneStela StelaResistance;
	static
	{
		StelaSpeed =new ArcaneStela(Potion.moveSpeed);
		StelaJump =new ArcaneStela(Potion.jump);
		StelaDigSpeed=new ArcaneStela(Potion.digSpeed);
		StelaResistance=new ArcaneStela(Potion.resistance);
	}
	public static class ArcaneStela extends Block
	{
		public final Potion potion;
		public ArcaneStela(Potion potion)
		{
			super(Material.ground);
			this.potion=potion;
		}

		// 玩家右键点击方块
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subx, float suby, float subz)
		{
			if(world.isRemote)
				return false;

			try
			{
				if(potion==null)
					return false;

				ItemStack is=player.getHeldItem();
				if(is!=null && is.getItem() instanceof thaumcraft.common.items.wands.ItemWandCasting)
				{
					player.addPotionEffect(new PotionEffect(potion.getId(),12000,0));
				}
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}

		// 玩家破坏方块
		public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
		{
//			if(world.isRemote)
//				return;
//			System.out.println("onBlockActivated:"+player.toString());
//			return;
		}



		@Override
		public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
		{
			return side.getOpposite()!=ForgeDirection.UP;
		}
		@Override
		public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
		{
			return false;
		}
		@Override
		public boolean hasTileEntity(int metadata)
		{
			return false;
		}
		@Override
		public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
		{
			return false;
		}
		@Override
		public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player)
		{
			return false;
		}
		@Override
		public boolean isLeaves(IBlockAccess world, int x, int y, int z)
		{
			return false;
		}
		@Override
		public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
		{
			return false;
		}
		@Override
		public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target)
		{
			return false;
		}
		@Override
		public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
		{
			return false;
		}
		@Override
		public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
		{
			return false;
		}
		@Override
		public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
		{
			return false;
		}
		@Override
		public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
		{
			;
		}
		@Override
		public boolean isFertile(World world, int x, int y, int z)
		{
			return false;
		}
		@Override
		public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
		{
			return false;
		}
		@Override
		public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
		{
			return false;
		}
		@Override
		public float getEnchantPowerBonus(World world, int x, int y, int z)
		{
			return 0;
		}
	}
}
