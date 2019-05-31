package firok.irisia.ability;

import firok.irisia.Util;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CauseAnvilRepair
{
	public static boolean At(World world,int x,int y,int z)
	{
		if(world.getBlock(x,y,z)==Blocks.anvil)
		{
			int metaOrigin=world.getBlockMetadata(x,y,z);
			int direction=metaOrigin&3; // --xx 取后两位
			int damage=metaOrigin&12; // xx-- 取前两位
			if(damage==0) return false;

			damage=damage>>1;
			world.setBlockMetadataWithNotify(x,y,z,damage|direction, Util.BitSetBlockSendChangeToClients);
			return true;
		}
		return false;
	}
}
