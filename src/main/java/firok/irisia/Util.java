package firok.irisia;

import net.minecraft.item.Item;

public class Util {
	public static String getDes(Item itemIn)
	{
		return itemIn.getUnlocalizedName()+".des";
	}
	
	public static float tcVersionLoaded()
	{
		return -1;
	}

}
