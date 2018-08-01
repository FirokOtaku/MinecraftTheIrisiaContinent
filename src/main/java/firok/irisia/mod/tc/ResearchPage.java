package firok.irisia.mod.tc;

import net.minecraft.util.ResourceLocation;

public class ResearchPage {
	String name;
	ResourceLocation icon;
	ResourceLocation bg1;
	ResourceLocation bg2;
	
	Object origin;
	
	public ResearchPage()
	{
		;
	}
	public ResearchPage(String nameIn,ResourceLocation iconIn,ResourceLocation bg1In,ResourceLocation bg2In)
	{
		name=nameIn;
		icon=iconIn;
		bg1=bg1In;
		bg2=bg2In;
		
		origin=null;
	}
	
	public String getName()
	{
		return name;
	}
	public ResourceLocation getIcon()
	{
		return icon;
	}
	public ResourceLocation getBg1()
	{
		return bg1;
	}
	public ResourceLocation getBg2()
	{
		return bg2;
	}
	public void registerThis()
	{
		// HALF
		;
	}
	
	private static Object TC_REG_RESEARCH_PAGE;
	static
	{
		TC_REG_RESEARCH_PAGE=null;
	}
}
