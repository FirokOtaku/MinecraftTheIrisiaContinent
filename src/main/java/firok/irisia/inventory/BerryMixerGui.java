package firok.irisia.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.item.ItemLoader;
import firok.irisia.tileentity.BerryMixerTE;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BerryMixerGui
{
	public static class ServerSide extends Container
	{
		BerryMixerTE berryMixerTE=null;
		public ServerSide(TileEntity te)
		{
			super();
			if(te!=null && te instanceof BerryMixerTE)
				berryMixerTE=(BerryMixerTE)te;
		}

		@Override
		public boolean canInteractWith(EntityPlayer playerIn)
		{
			return true;
		}
	}
	@SideOnly(Side.CLIENT)
	public static class ClientSide extends GuiContainer
	{
		private static final String TEXTURE_PATH = Irisia.MODID + ":" + "textures/tc_page_bg.png";
		private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
		protected Slot[] rawMaterials;
		protected Slot sugar;
		protected Slot output;
		ServerSide serverSide=null;
		public ClientSide(ServerSide inventorySlotsIn)
		{
			super(inventorySlotsIn);
			serverSide=inventorySlotsIn;
			sugar=new Slot(inventorySlotsIn.berryMixerTE,0,1,1);

		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			this.mc.getTextureManager().bindTexture(TEXTURE);
			int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

			this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
		{
			// this.itemRender.renderItemAndEffectIntoGUI(fontRendererObj,new TextureManager(new I),serverSide.berryMixerTE.getStackInSlot(0),1,1);
			//GuiFurnace
			ItemStack sugar=serverSide.berryMixerTE.getStackInSlot(0);
			this.drawString(this.fontRendererObj,"sugar : "+(sugar==null||sugar.stackSize<=0?0:sugar.stackSize)
					,1,1,0x66ccff);
		}

	}
}