package firok.irisia.client;

import firok.irisia.block.BlockLoader;
import firok.irisia.item.ItemLoader;

public class ItemRenderLoader
{
    public ItemRenderLoader()
    {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
    }
}