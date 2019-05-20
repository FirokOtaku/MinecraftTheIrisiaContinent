import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.*;

public class TestMain
{
	public static final File target=new File("D:/file2019");
	public static final Map<String,List<File>> files=new HashMap<>();
	public static void addFile(String suffix,File file)
	{
		List<File> list=files.get(suffix);
		if(list==null)
		{
			list=new ArrayList<>();
			files.put(suffix,list);
		}
		list.add(file);
	}
	public static void main(String... args) throws IOException
	{
		Scanner in=new Scanner(System.in); // 输入路径
		String path2read=in.nextLine();

		File start=new File(path2read);
		fun(start);

		for(Map.Entry<String,List<File>> entry:files.entrySet()) // 输出信息
		{
			System.out.println(
					String.format("%s文件:%d个",entry.getKey(),entry.getValue().size())
			);
		}
	}

	public static void fun(File file) throws IOException // 遍历
	{
		if(file==null||!file.exists()) return;

		if(file.isDirectory())
		{
			for(String path2read:file.list())
			{
				fun(new File(path2read));
			}
		}
		if(file.isFile())
		{
			FileUtils.copyFile(file,target);
			addFile(getSuffix(file),file);
		}
	}

	public static String getSuffix(File file) // 获取后缀
	{
		if(file==null) return null;

		String path=file.getAbsolutePath();
		int index=path.lastIndexOf(".");
		return index>=0?path.substring(index,path.length()):"";
	}
}
