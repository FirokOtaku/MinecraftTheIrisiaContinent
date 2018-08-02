package firok.irisia.world.maze;

import firok.irisia.world.GenPlan;

public class MazeGenPlan extends GenPlan
{
	public final int lenUnit;
	public final int lenWall;
	public MazeGenPlan(int x,int z,int u,int w)
	{
		// 迷宫的高度始终为4 地板和顶部占用1 总共为6
		// 迷宫单元大小最低是3×3
		// 墙壁最低厚度是1

		// 总大小
		super((x>0?x:1)*(u>2?u:3)+(w*(1+x>0?x:1)),6,(z>0?z:1)*(u>2?u:3)+(w*(1+z>0?z:1)));

		lenUnit=u>2?u:3;
		lenWall=w>1?w:1;
	}
}
