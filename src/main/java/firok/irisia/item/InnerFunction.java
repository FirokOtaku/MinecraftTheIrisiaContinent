package firok.irisia.item;

import firok.irisia.Irisia;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class InnerFunction
{
	public static Item ITEM_TEXTURE=new ITEM_TEXTURE_CLASS();
	public static class ITEM_TEXTURE_CLASS extends Item
	{
		public static final int idStone=0;
		private IIcon iconStone;
		public static final int idSmokeBomb=1;
		private IIcon iconSmokeBomb;
		public static final int idBurningSpearArrow=2;
		private IIcon iconBurningSpearArrow;
		public static final int idRunicArrow=3;
		private IIcon iconRunicArrow;
		public static final int idSuperova=4;
		private IIcon iconSupernova;


		@Override
		public IIcon getIconFromDamage(int damage)
		{
			switch(damage)
			{
				case idStone:
					return iconStone;
				case idSmokeBomb:
					return iconSmokeBomb;
				case idBurningSpearArrow:
					return iconBurningSpearArrow;
				case idRunicArrow:
					return iconRunicArrow;
				case idSuperova:
					return iconSupernova;
				default:
					return iconStone;
			}
		}

		@Override
		public void registerIcons(IIconRegister iir)
		{
			iconStone=iir.registerIcon(Irisia.MODID+":particle_stone");
			iconSmokeBomb=iir.registerIcon(Irisia.MODID+":particle_smoke_bomb");
			iconBurningSpearArrow=iir.registerIcon(Irisia.MODID+":particle_burning_spear_arrow");
			iconRunicArrow=iir.registerIcon(Irisia.MODID+":particle_runic_arrow");
			iconSupernova=iir.registerIcon(Irisia.MODID+":particle_supernova");
		}
	}
}
