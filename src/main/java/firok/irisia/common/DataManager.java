package firok.irisia.common;

import firok.irisia.Irisia;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.*;
import java.util.HashMap;

public class DataManager
{
	private static HashMap<String,Integer> player_maxHps=new HashMap<>();
	public static void setPlayerMaxHp(String name,int maxHp)
	{
		player_maxHps.put(name,maxHp);
	}
	public static int getPlayerMaxHp(String name)
	{
		return player_maxHps.containsKey(name)?player_maxHps.get(name):0;
	}

	public static void savePlayerData(String name, File file)
	{
		FileOutputStream ofs;
		try
		{
			if(file!=null&&file.exists())
			{
				ofs=new FileOutputStream(file);
			}
			else
			{
				file.createNewFile();
				ofs=new FileOutputStream(file);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Irisia.log("创建玩家数据文件时发生错误 无法储存玩家数据");
			return;
		}

		try {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("maxHp",getPlayerMaxHp(name));
			CompressedStreamTools.writeCompressed(nbt,ofs);
			ofs.flush();
			ofs.close();
		} catch (Exception var10) {
			var10.printStackTrace();
		}
	}
	public static void loadPlayerData(String name,File file)
	{
		try {
			NBTTagCompound nbt = null;
			FileInputStream ifs;
			if (file != null && file.exists()) {
				try {
					ifs = new FileInputStream(file);
					nbt = CompressedStreamTools.readCompressed(ifs);
					ifs.close();
				} catch (Exception var9) {
					nbt=new NBTTagCompound();
				}
			}

			setPlayerMaxHp(name,nbt.hasKey("maxHp")?nbt.getInteger("maxHp"):0);

		} catch (Exception var10) {
			var10.printStackTrace();
			Irisia.log("读取玩家数据时发生错误");
		}
	}
}
