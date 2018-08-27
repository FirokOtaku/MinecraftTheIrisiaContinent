package firok.irisia.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.*;

public final class GashaponManager
{
	private static final Random rand;
	private static final HashMap<String,HashMap<ItemStack,Float>> tables;
	private static final HashMap<ItemStack,Float> nullTable;
	private static HashMap<ItemStack,Float> getTable(String key)
	{
		if(key!=null && tables.containsKey(key))
			return tables.get(key);
		else
			return nullTable;
	}
	private static List<ItemStack> rollItemsFromTableWithBonus(HashMap<ItemStack,Float> table,float bonus)
	{
		LinkedList<ItemStack> ret=new LinkedList<ItemStack>();
		if(table==null||table==nullTable||table.size()==0)
			return ret;
		for(ItemStack itemStack:table.keySet())
		{
			if(rand.nextFloat()-bonus>table.get(itemStack))
			{
				ret.add(itemStack.copy());
			}
		}
		return ret;
	}
	private static List<ItemStack> rollItemsFromTable(HashMap<ItemStack,Float> table)
	{
		return rollItemsFromTableWithBonus(table,0);
	}
	public static List<ItemStack> rollItemsFromTableWithBonus(String key,float bonus)
	{
		return rollItemsFromTableWithBonus(getTable(key),bonus);
	}
	public static List<ItemStack> rollItemsFromTable(String key)
	{
		return rollItemsFromTableWithBonus(key,0);
	}

	public static void addTable(String key)
	{
		if(!tables.containsKey(key))
			tables.put(key,new HashMap<ItemStack, Float>());
	}
	public static void addItemToTable(String key,ItemStack itemStack,Float rate)
	{
		HashMap<ItemStack,Float> table=getTable(key);
		if(table==nullTable)
			return;
		table.put(itemStack,rate);
	}
	public static boolean hasTable(String key)
	{
		return tables.containsKey(key);
	}
	static
	{
		rand=new Random();
		tables=new HashMap<String, HashMap<ItemStack, Float>>();
		nullTable=new HashMap<ItemStack, Float>();
	}
	public static void init()
	{
		// test table
		addTable("irisia:testTable");
		addItemToTable("irisia:testTable",new ItemStack(Items.stone_sword),0.3333f);
		addItemToTable("irisia:testTable",new ItemStack(Items.golden_sword),0.3333f);
		addItemToTable("irisia:testTable",new ItemStack(Items.diamond_sword),0.3333f);
	}
}
