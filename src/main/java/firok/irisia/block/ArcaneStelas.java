package firok.irisia.block;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;


public class ArcaneStelas
{
	public final static ArcaneStela a1;
	public final static ArcaneStela a2;
	static
	{
		a1=new ArcaneStela(Potion.moveSpeed);
		a2=new ArcaneStela(Potion.jump);
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
