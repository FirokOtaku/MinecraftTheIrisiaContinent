package firok.irisia.common;

import firok.irisia.Irisia;
import net.minecraft.init.Items;

import java.util.*;

public final class TasteManager
{
	private static Object tempObject;
	private static final HashMap<Object,byte[]> tastes=new HashMap<>();
	private static byte[] tempTastes;

	private static final HashMap<Object,Integer> antiDs=new HashMap<>();
	private static int tempAntiD;

	private static final HashMap<Object,Integer> antiPs=new HashMap<>();
	private static int tempAntiP;

	private static final HashMap<Object,Integer> exps=new HashMap<>();
	private static int tempExp;

	private static final HashMap<Object,Integer> expls=new HashMap<>();
	private static int tempExpl;

	private static final HashMap<Object,int[]> mps=new HashMap<>();
	private static int[] tempMps;

	private static final HashMap<Object,Integer> mpls=new HashMap<>();
	private static int tempMpl;

	private static final HashMap<Object,int[]> pids=new HashMap<>();
	private static int[] tempPids;

	private static final HashMap<Object,int[]> pts=new HashMap<>();
	private static int[] tempPts;

	private static final HashMap<Object,int[]> pls=new HashMap<>();
	private static int[] tempPls;

	private static final HashMap<Object,int[]> prs=new HashMap<>();
	private static int[] tempPrs;

	private static boolean pinTo(Object obj)
	{
		if(obj==null)
			return false;

		if(tempObject==obj)
		{
			return true;
		}

		tempObject=obj;

		tempTastes=tastes.get(tempObject);
		tempAntiD=antiDs.get(tempObject);
		tempAntiP=antiPs.get(tempObject);
		tempExp=exps.get(tempObject);
		tempExpl=expls.get(tempObject);
		tempMps=mps.get(tempObject);
		tempMpl=mpls.get(tempObject);
		tempPids=pids.get(tempObject);
		tempPts=pts.get(tempObject);
		tempPls =pls.get(tempObject);
		tempPrs=prs.get(tempObject);

		return true;
	}
	public static void registerTaste(Object obj,
	                                 byte[] taste,int antiD,int antiP,
	                                 int exp,int expl,
	                                 int[] mps,int mpl,
	                                 int[] pids,int[] pts,int[] pls,int[] prs)
	{
		byte[] taste2r=taste.length==5?taste.clone():Irisia.byte5zero;
		int[] mps2r=mps!=null&&mps.length==5?mps.clone():Irisia.int5zero;
		int[] pids2r,pts2r,pls2r,prs2r;
		if(pids!=null&&pts!=null&&pls!=null&&prs!=null&&
			pids.length==pts.length&&pts.length==pls.length&&pls.length==prs.length)
		{
			pids2r=pids.clone();
			pts2r=pts.clone();
			pls2r=pls.clone();
			prs2r=prs.clone();
		}
		else
		{
			pids2r=Irisia.noInt;
			pts2r=Irisia.noInt;
			pls2r=Irisia.noInt;
			prs2r=Irisia.noInt;
		}
		TasteManager.tastes.put(obj,taste2r);
		TasteManager.antiDs.put(obj,antiD);
		TasteManager.antiPs.put(obj,antiP);
		TasteManager.exps.put(obj,exp);
		TasteManager.expls.put(obj,expl);
		TasteManager.mps.put(obj,mps2r);
		TasteManager.mpls.put(obj,mpl);
		TasteManager.pids.put(obj,pids2r);
		TasteManager.pts.put(obj,pts2r);
		TasteManager.pls.put(obj,pls2r);
		TasteManager.prs.put(obj,prs2r);
	}
	public static byte[] getTastes(Object obj)
	{
		if(pinTo(obj))
			return tempTastes;
		else
			return Irisia.byte5zero;

	}
	public static int getAntiD(Object obj)
	{
		if(pinTo(obj))
			return tempAntiD;
		else
			return 0;
	}
	public static int getAntiP(Object obj)
	{
		if(pinTo(obj))
			return tempAntiP;
		else
			return 0;
	}
	public static int getExp(Object obj)
	{
		if(pinTo(obj))
			return tempExp;
		else
			return 0;
	}
	public static int getExpl(Object obj)
	{
		if(pinTo(obj))
			return tempExpl;
		else
			return 0;
	}
	public static int[] getMps(Object obj)
	{
		if(pinTo(obj))
			return tempMps;
		else
			return Irisia.int5zero;
	}
	public static int getMpl(Object obj)
	{
		if(pinTo(obj))
			return tempMpl;
		else
			return 0;
	}
	public static int[] getPids(Object obj)
	{
		if(pinTo(obj))
			return tempPids;
		else
			return Irisia.noInt;
	}
	public static int[] getPls(Object obj)
	{
		if(pinTo(obj))
			return tempPls;
		else
			return Irisia.noInt;
	}
	public static int[] getPrs(Object obj)
	{
		if(pinTo(obj))
			return tempPrs;
		else
			return Irisia.noInt;
	}
	public static int[] getPts(Object obj)
	{
		if(pinTo(obj))
			return tempPts;
		else
			return Irisia.noInt;
	}

	static
	{
		registerTaste(Items.apple,new byte[]{1,0,0,0,0},0,0,0,0,
				null,0,null,null,null,null);
		registerTaste(Items.melon,new byte[]{1,0,0,0,0},0,0,0,0,
				null,0,null,null,null,null);
	}
}
