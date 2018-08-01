package firok.irisia.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Decorations
{
	public final static Block CorrosedBlock; // 被腐蚀的方块
	public final static Block Loess; // 黄土
	static
	{
		CorrosedBlock=new Block(Material.sand){};
		Loess=new Block(Material.sand){};
	}
}
