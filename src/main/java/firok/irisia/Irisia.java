package firok.irisia;

import java.util.Random;

import firok.irisia.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;


@Mod(
modid = Irisia.MODID,
name = Irisia.NAME,
version = Irisia.VERSION,
acceptedMinecraftVersions = "1.10.2",
dependencies="after:Thaumcraft")
public class Irisia
{
    public static final String MODID = "Irisia";
    public static final String NAME = "The Irisia Continent";
    public static final String VERSION = "0.12.3";
    
    public static Random rand=new Random();

    @Instance(Irisia.MODID)
    public static Irisia instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        proxy.serverStarting(event);
    }
    
    @SidedProxy(
    		clientSide = "firok.irisia.client.ClientProxy", 
            serverSide = "firok.irisia.common.CommonProxy")
    public static CommonProxy proxy;


    public static void main(String... args)
    {
        NameObject nb1=transName("ore","mithrill");
        NameObject nb2=transName("item","mithrill","ingot");

        System.out.println(nb1);
        System.out.println(nb2);
    }
    public static NameObject transName(String type,String... tags)
    {
        return transName(true,type,tags);
    }
    public static NameObject transName(boolean byDefault,String type,String... tags)
    {
        StringBuffer sbunn=new StringBuffer();
        StringBuffer sbtn=new StringBuffer();

        String lowerType=type.toLowerCase();
        String fuType=getFirstUpper(lowerType);

        String[] lowerTags=new String[tags.length];
        String[] fuTags=new String[tags.length];
        for(byte i=0;i<tags.length;i++)
        {
            lowerTags[i]=tags[i].toLowerCase();
            fuTags[i]=getFirstUpper(lowerTags[i]);
        }

        sbunn.append(fuType);
        for(String fuTag:fuTags)
        {
            sbunn.append(fuTag);
        }
        sbtn.append(lowerType);
        if(byDefault)
        {
            for(byte i=(byte)(lowerTags.length-1);i>=0;i--)
            {
                sbtn.append('_');
                sbtn.append(lowerTags[i]);
            }
        }
        else
        {

            for(String lowerTag:lowerTags)
            {
                sbtn.append('_');
                sbtn.append(lowerTag);
            }
        }
        return new NameObject(sbunn.toString(),sbtn.toString());
    }
    public static class NameObject
    {
        public final String unlocalizedName;
        public final String textureName;
        public NameObject(String unn,String tn)
        {
            unlocalizedName=unn;
            textureName=tn;
        }
        @Override
        public String toString()
        {
            return this.unlocalizedName+","+this.textureName;
        }
    }
    public static String getFirstUpper(String str)
    {
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}