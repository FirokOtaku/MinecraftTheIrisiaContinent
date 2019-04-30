package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import static net.minecraft.item.EnumRarity.*;
import java.util.List;

public class RawMaterials
{
	// common materials
	public final static InformationItem ShardOfLove;
	public final static InformationItem Bezoar;
	public final static InformationItem IcyGel;
	public final static InformationItem WolfFur; // 狼毛
	public final static InformationItem IcyWolfFur; // 霜狼毛
	public final static InformationItem ScorchingWolfFur; // 炽狼毛
	public final static InformationItem HeavyCashmere; // 厚羊绒
	public final static InformationItem TortoiseShell; // 龟壳
	public final static InformationItem BoneShard; // 碎骨
	public final static InformationItem CasinosBadge; // 赌场徽章
	public final static InformationItem UnicornHorn; // 独角兽角
	public final static InformationItem UnicornBlood; // 独角兽血
	public final static InformationItem HotBlood; // 炽热血液
	public final static InformationItem SlimeCore; // 史莱姆核
	public final static InformationItem SoulCrystal; // 灵魂结晶
	public final static InformationItem DwartCoal; // 褐煤
	public final static InformationItem HotStone; // 热石
	public final static InformationItem TerrestrialDoveFeather; // 陆行鸽羽
	public final static InformationItem MagicalPaper; // 魔力纸张
	public final static InformationItem Ink; // 墨水
	public final static InformationItem InkBottle; // 墨水瓶
	public final static InformationItem PaperPile; // 一摞纸
	public final static InformationItem PaperBigPile; // 一大摞纸
	public final static InformationItem Nugget; // 金块
	public final static InformationItem StormBall; // 未充能的风暴球
	public final static InformationItem ChargedStormBall; // 充能的风暴球

	// food material
	public final static InformationItem BrownWheat; // 褐麦
	public final static InformationItem DwartFlour; // 矮人面粉
	public final static InformationItem ElfLeaves; // 精灵叶

	// ores and metals
	public final static InformationItem ElfStone;
	public final static InformationItem SolarCrystal;
	public final static InformationItem LunarCrystal;
	public final static InformationItem GlitteringSolarCrystal;
	public final static InformationItem GlitteringLunarCrystal;
	public final static InformationItem DarkIronIngot;
	public final static InformationItem VibrhythmIronIngot;
	public final static InformationItem LuxIronIngot;
	public final static InformationItem StormIronIngot;
	public final static InformationItem FlumetalIngot;
	public final static InformationItem SolitaIngot;
	public final static InformationItem MogigaIngot;
	public final static InformationItem DwartSteelIngot;
	public final static InformationItem MithrilIngot;
	public final static InformationItem AdamantiumIngot;
	public final static InformationItem SlimeIngot;

	// plot
	public final static InformationItem InvalidRunicRing; // 失效的符文护盾指环
	public final static InformationItem LostPage; // 遗失的书页

	// thaumic
	public final static InformationItem GoldenSilk;
	public final static InformationItem DiamondSilk;
	public final static InformationItem MultiCoreBrain; // 多核脑


	// irisia !
	public final static InformationItem CoinCopper;
	public final static InformationItem CoinCopperPile;
	public final static InformationItem CoinSilver;
	public final static InformationItem CoinSilverPile;
	public final static InformationItem CoinGold;
	public final static InformationItem CoinGoldPile;
	public final static InformationItem CoinIrisia;
	public final static InformationItem CoinIrisiaPile;
	public final static InformationItem VipCardCopper; // vip铜卡
	public final static InformationItem VipCardSilver; // vip银卡
	public final static InformationItem VipCardGold; // vip金卡
	public final static InformationItem VipCardDiamond; // vip钻石卡
	public final static InformationItem AncientThaumicNote; // 古代神秘使笔记
	public final static InformationItem AncientMachinePart;  // 古代机械碎片
	public final static InformationItem AncientFossilFragment; // 古代生物化石碎块
	public final static InformationItem AncientBrokenSword; // 古代断剑

	static
	{
		ShardOfLove = new InformationItem();
		Bezoar = new InformationItem();
		IcyGel = new InformationItem();
		WolfFur = new InformationItem();
		IcyWolfFur = new InformationItem();
		ScorchingWolfFur=new InformationItem();

		HeavyCashmere = new InformationItem();
		TortoiseShell = new InformationItem();
		BoneShard = new InformationItem();
		CasinosBadge = new InformationItem();
		UnicornHorn=new InformationItem(uncommon);
		UnicornBlood =new InformationItem(uncommon);
		HotBlood=new InformationItem(uncommon);
		SlimeCore=new InformationItem(uncommon);
		SoulCrystal=new InformationItem();
		DwartCoal=new InformationItem();
		HotStone=new InformationItem();
		TerrestrialDoveFeather=new InformationItem();
		MagicalPaper=new InformationItem();
		Ink=new InformationItem();
		InkBottle=new InformationItem();
		PaperPile=new InformationItem();
		PaperBigPile=new InformationItem();
		Nugget=new InformationItem();
		StormBall=new InformationItem();
		ChargedStormBall=new InformationItem(uncommon);

		BrownWheat=new InformationItem();
		DwartFlour=new InformationItem();
		ElfLeaves=new InformationItem();

		ElfStone= new InformationItem();
		SolarCrystal=new InformationItem();
		LunarCrystal=new InformationItem();
		GlitteringSolarCrystal=new InformationItem(uncommon);
		GlitteringLunarCrystal=new InformationItem(uncommon);
		DarkIronIngot=new InformationItem(uncommon);
		VibrhythmIronIngot=new InformationItem(uncommon);
		LuxIronIngot=new InformationItem(uncommon);
		StormIronIngot=new InformationItem(uncommon);
		FlumetalIngot= new InformationItem();
		SolitaIngot= new InformationItem();
		MogigaIngot= new InformationItem();
		DwartSteelIngot=new InformationItem();
		MithrilIngot=new InformationItem();
		AdamantiumIngot=new InformationItem();
		SlimeIngot=new InformationItem();

		InvalidRunicRing=new InformationItem(uncommon);
		LostPage=new InformationItem(uncommon);

		GoldenSilk = new InformationItem();
		DiamondSilk = new InformationItem();
		MultiCoreBrain=new InformationItem();

		CoinCopper = new InformationItem();
		CoinCopperPile = new InformationItem();
		CoinSilver = new InformationItem();
		CoinSilverPile = new InformationItem();
		CoinGold = new InformationItem();
		CoinGoldPile = new InformationItem();
		CoinIrisia = new InformationItem();
		CoinIrisiaPile = new InformationItem();
		VipCardCopper= new InformationItem(uncommon);
		VipCardSilver= new InformationItem(uncommon);
		VipCardGold= new InformationItem(rare);
		VipCardDiamond= new InformationItem(rare);
		AncientThaumicNote = new InformationItem(epic);
		AncientMachinePart = new InformationItem(epic);
		AncientFossilFragment=new InformationItem(epic);
		AncientBrokenSword=new InformationItem(epic);
	}

	public static class InformationItem extends Item implements Util.Informational
	{
		private String[] lines;
		private EnumRarity rarity;
		public InformationItem()
		{
			this(EnumRarity.common);
		}
		public InformationItem(EnumRarity r)
		{
			lines=Irisia.noString;
			rarity=r;
		}
		public void loadInformation()
		{
			String key=this.getUnlocalizedName()+".tooltip";
			String linesGet=StatCollector.translateToLocal(key);
			if(linesGet.equals(key))
			{
				lines=Irisia.noString;
			}
			else
			{
				lines=linesGet.split("<br>");
			}
		}
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean p_77624_4_)
		{
			if(lines.length==0)
				return;
			for(String line:lines)
			{
				info.add(line);
			}
		}
		@Override
		public EnumRarity getRarity(ItemStack stack)
		{
			return rarity;
		}

		@Override
		public Item setTextureName(String name)
		{
			this.iconString=name;
			// System.out.println(iconString);
			return this;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister iir)
		{
			this.itemIcon = iir.registerIcon(this.getIconString());
		}
		/**
		 * Returns the string associated with this Item's Icon.
		 */
		@SideOnly(Side.CLIENT)
		protected String getIconString()
		{
			return iconString==null?"ERROR":iconString;
		}
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int p_77617_1_)
		{
			return this.itemIcon;
		}
	}
}
