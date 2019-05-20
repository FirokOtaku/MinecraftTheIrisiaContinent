package firok.irisia;

import java.util.*;
import firok.irisia.common.CommonProxy;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import firok.irisia.thaum.IrisiaWandTriggerManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import thaumcraft.api.aspects.Aspect;


@Mod(
modid = Irisia.MODID,
name = Irisia.NAME,
version = Irisia.VERSION,
acceptedMinecraftVersions = "1.7.10",
dependencies="after:Thaumcraft")
public class Irisia
{
    public static final String MODID = "irisia";
    public static final String NAME = "The Irisia Continent";
    public static final String VERSION = "0.12.102";
    public static final boolean IN_DEV=true;

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

    public static IrisiaWandTriggerManager wandManager;

    public static void main(String... args) throws Exception
    {

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

    public static void tellPlayer(String content,EntityPlayer player)
    {
        if(!player.worldObj.isRemote)
        {
            player.addChatComponentMessage(new ChatComponentText(content));
        }
    }
    public static void tellPlayerKey(String key,EntityPlayer player)
    {
        if(!player.worldObj.isRemote)
        {
            player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal(key)));
        }
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
    public static void log(Object[] objs)
    {
        for(Object obj:objs)
        {
            log(obj.toString());
        }
    }
    public static void log(String str)
    {
        if(IN_DEV)
        {
            if(logger==null) System.out.println(str);
            else logger.log(Level.INFO,str);
        }
    }
    public static void log(Exception e)
    {
        if(IN_DEV)
        {
            e.printStackTrace();
        }
    }
    public static Logger logger=null;

    public static void STOP()
    {
        throw new RuntimeException("GAME STOP");
    }


    public static final String[] noString=new String[0];
    public static final List<Aspect> noAspect;
    public static final List<Aspect> listPrimalAspect;
    public static final Aspect[] arrayPrimalAspect;
    public static final Potion[] noPotion=new Potion[0];
    public static final int[] noInt=new int[0];
    public static final byte[] noByte=new byte[0];
    public static final int[] int5zero=new int[]{0,0,0,0,0};
    public static final byte[] byte5zero=new byte[]{0,0,0,0,0};
    public static final Integer[] noInteger=new Integer[0];
    public static String getRomeInt(int i) // now used for BuffBauble s
    {
        switch (i)
        {
            case 0:return "Ⅰ";
            case 1:return "Ⅱ";
            case 2:return "Ⅲ";
            case 3:return "Ⅳ";
            case 4:return "Ⅴ";
            case 5:return "Ⅵ";
            case 6:return "Ⅶ";
            case 7:return "Ⅷ";
            case 8:return "Ⅸ";
            case 9:return "Ⅹ";
            default:return "[x]";
        }
    }
    static
    {
        noAspect=Collections.unmodifiableList(new ArrayList<>());

        listPrimalAspect=Collections.unmodifiableList(Aspect.getPrimalAspects());
        arrayPrimalAspect = new Aspect[listPrimalAspect.size()];
        for(int i=0;i<listPrimalAspect.size();i++) arrayPrimalAspect[i]=listPrimalAspect.get(i);
    }
}