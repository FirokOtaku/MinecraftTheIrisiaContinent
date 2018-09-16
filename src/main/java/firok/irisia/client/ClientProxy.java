package firok.irisia.client;

import firok.irisia.common.CommonProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        // new ItemRenderLoader();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        // new KeyLoader();
        for(Item item:ItemLoader.items) // info 加载一下提示信息
        {
            if(item instanceof Util.Informational)
            {
                ((Util.Informational) item).loadInformation();
            }
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}