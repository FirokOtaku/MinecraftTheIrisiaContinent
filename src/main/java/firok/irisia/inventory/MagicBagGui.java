package firok.irisia.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.item.HandItemInv;
import firok.irisia.item.Tools;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


public class MagicBagGui
{
	public static class ServerSide extends Container
	{
		protected static final int height = 166;
		protected static final int windowBorder = 8;
		protected static final int slotSize = 16;
		protected static final int slotDistance = 2;
		protected static final int slotSeparator = 4;
		protected static final int hotbarYOffset = -24;
		protected static final int inventoryYOffset = -82;

		HandItemInv relativeInv;

		public ServerSide(EntityPlayer entityPlayer, HandItemInv boxInv)
		{
			relativeInv=boxInv;

			for (int j = 0; j < 3; ++j)
			{
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(boxInv,k + j * 9, 8 + k * 18, j * 18){
						@Override
						public void onSlotChange(ItemStack stack, ItemStack stack2)
						{
							super.onSlotChange(stack,stack2);
							boxInv.markDirty();
						}
					});
					this.addSlotToContainer(new Slot(entityPlayer.inventory, k + j * 9 + 9, 8 + k * 18, 53+ j * 18));
				}
			}
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(entityPlayer.inventory, j, 8 + j * 18, 111 ));
			}

		}
		@Override
		public void onContainerClosed(EntityPlayer player)
		{
			super.onContainerClosed(player);
			relativeInv.closeInventory();
		}
		@Override
		public boolean canInteractWith(EntityPlayer player)
		{
			return true;
		}
	}
	@SideOnly(Side.CLIENT)
	public static class ClientSide extends GuiContainer
	{
		private static final String TEXTURE_PATH = Irisia.MODID + ":" + "textures/gui/container/gui_berry_mixer.png";
		private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
		protected Slot[] rawMaterials;
		protected Slot sugar;
		protected Slot output;
		MagicBagGui.ServerSide serverSide=null;
		public ClientSide(MagicBagGui.ServerSide inventorySlotsIn)
		{
			super(inventorySlotsIn);
			serverSide=inventorySlotsIn;
			this.xSize = 176;
			this.ySize = 133;
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
			// this.itemRender.renderItemAndEffectIntoGUI(fontRendererObj,new TextureManager(new I),serverSide.itemStack.getStackInSlot(0),1,1);
			//GuiFurnace
			;
		}

	}
}
