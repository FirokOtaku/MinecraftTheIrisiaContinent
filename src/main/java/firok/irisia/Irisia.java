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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;


@Mod(
modid = Irisia.MODID,
name = Irisia.NAME,
version = Irisia.VERSION,
acceptedMinecraftVersions = "1.7.10",
dependencies="after:Thaumcraft")
public class Irisia
{
    public static final String MODID = "Irisia";
    public static final String NAME = "The Irisia Continent";
    public static final String VERSION = "0.12.23";
    public static final boolean IN_DEV=true;

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
//        ResourceLocation rsl=new ResourceLocation("irisia:textures/pic.png");
//        System.out.println(rsl.getResourcePath());Render.bindEntityTexture
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

        String[] lowerTags=new String[tags.length];
        String[] fuTags=new String[tags.length];
        for(byte i=0;i<tags.length;i++)
        {
            lowerTags[i]=tags[i].toLowerCase();
            fuTags[i]=getFirstUpper(lowerTags[i]);
        }

        sbunn.append(lowerType);
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


    public static void log(Object str, EntityPlayer player)
    {
        if(!IN_DEV && player.worldObj.isRemote)
            return;

        player.addChatComponentMessage(new ChatComponentText(str.toString()));
    }
    public static void log(Object[] strs,EntityPlayer player)
    {
        for(Object str:strs)
        {
            log(str,player);
        }
    }
    public static void log(Exception e,EntityPlayer player)
    {
        log(e.getStackTrace(),player);
    }
    public static void log(StringBuffer sb)
    {
        log(sb.toString());
    }
    public static void log(String str)
    {
        if(IN_DEV)
        {
            System.out.println(str);
        }
    }
    public static void log(Exception e)
    {
        if(IN_DEV)
        {
            e.printStackTrace();
        }
    }
}