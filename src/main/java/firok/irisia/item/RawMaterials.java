package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

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

	// food material
	public final static InformationItem BrownWheat; // 褐麦
	public final static InformationItem DwartFlour; // 矮人面粉

	// ores and metals
	public final static InformationItem ElfStone;
	public final static InformationItem FlumetalIngot;
	public final static InformationItem SolitaIngot;
	public final static InformationItem MogigaIngot;
	public final static InformationItem DwartSteelIngot;
	public final static InformationItem MithrilIngot;
	public final static InformationItem AdamantiumIngot;

	// plot
	public final static InformationItem InvalidRunicRing; // 失效的符文护盾指环
	public final static InformationItem LostPage; // 遗失的书页

	// thaumic
	public final static InformationItem GoldenSilk;
	public final static InformationItem DiamondSilk;

	// irisia !
	public final static InformationItem AncientThaumicNote; // 古代神秘使笔记
	public final static InformationItem AncientMachinePart;  // 古代机械碎片
	public final static InformationItem AncientFossilFragment; // 古代生物化石碎块

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
		UnicornHorn=new InformationItem();
		UnicornBlood =new InformationItem();

		BrownWheat=new InformationItem();
		DwartFlour=new InformationItem();

		ElfStone= new InformationItem();
		FlumetalIngot= new InformationItem();
		SolitaIngot= new InformationItem();
		MogigaIngot= new InformationItem();
		DwartSteelIngot=new InformationItem();
		MithrilIngot=new InformationItem();
		AdamantiumIngot=new InformationItem();

		InvalidRunicRing=new InformationItem();
		LostPage=new InformationItem();

		GoldenSilk = new InformationItem();
		DiamondSilk = new InformationItem();

		AncientThaumicNote = new InformationItem();
		AncientMachinePart = new InformationItem();
		AncientFossilFragment=new InformationItem();
	}

	public static class InformationItem extends Item
	{
		private String[] lines;
		public InformationItem()
		{
			lines=null;
		}
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean p_77624_4_)
		{
			if(lines==null)
			{
				String key=this.getUnlocalizedName()+".tooltip";
				String linesGet=StatCollector.translateToLocal(key);
				if(linesGet.equals(key))
				{
					lines=new String[0];
				}
				else
				{
					lines=linesGet.split("\\n");
				}
				System.out.println("key:"+key+" linesGet:"+linesGet+" lines.len:"+lines.length);
			}

			for(String line:lines)
			{
				info.add(line);
			}
		}
	}


}
