package firok.irisia.world;

public abstract class GenPlan
{
	public final int lenX;
	public final int lenY;
	public final int lenZ;
	public int[][][] datas;
	protected GenPlan(int x,int y,int z)
	{
		lenX=x>0?x:1;
		lenY=y>0?y:1;
		lenZ=z>0?z:1;
		datas=new int[lenX][lenY][lenZ];
	}

}
