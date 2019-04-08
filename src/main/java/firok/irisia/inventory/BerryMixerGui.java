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
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.container.ContainerArcaneWorkbench;

public class BerryMixerGui
{
	public static class BMSlot implements IInventory
	{
		public BerryMixerTE TE=null;
		int idSlot=-1;
		public BMSlot(BerryMixerTE te,int idSlot)
		{
			TE=te;
			this.idSlot=idSlot;
		}
		@Override
		public int getSizeInventory()
		{
			return TE.getSizeInventory();
		}

		@Override
		public ItemStack getStackInSlot(int id)
		{
			return TE.getStackInSlot(id);
		}

		@Override
		public ItemStack decrStackSize(int slot, int count)
		{
			return TE.decrStackSize(slot,count);
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int slot)
		{
			return TE.getStackInSlotOnClosing(slot);
		}

		@Override
		public void setInventorySlotContents(int slot, ItemStack itemStack)
		{
			TE.setInventorySlotContents(slot,itemStack);
		}

		@Override
		public String getInventoryName()
		{
			return TE.getInventoryName();
		}

		@Override
		public boolean hasCustomInventoryName()
		{
			return true;
		}

		@Override
		public int getInventoryStackLimit()
		{
			return TE.getInventoryStackLimit();
		}

		@Override
		public void markDirty()
		{
			TE.markDirty();
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer player)
		{
			return TE.isUseableByPlayer(player);
		}

		@Override
		public void openInventory()
		{
			TE.openInventory();
		}

		@Override
		public void closeInventory()
		{
			TE.closeInventory();
		}

		@Override
		public boolean isItemValidForSlot(int slot, ItemStack itemStack)
		{
			return TE.isItemValidForSlot(slot,itemStack);
		}
	}
	public static class ServerSide extends Container
	{
		EntityPlayer player=null;
		BerryMixerTE berryMixerTE=null;

		public IInventory craftResult;

		public ServerSide(TileEntity te,EntityPlayer player)
		{
			super();
			if(te!=null && te instanceof BerryMixerTE)
				berryMixerTE=(BerryMixerTE)te;

			this.player=player;
			Slot slotSugar=new Slot(berryMixerTE, 0, 0, 0);
			Slot[] slotBerries=new Slot[berryMixerTE.getSizeInventory()-2];
			Slot slotCrafting=new SlotCrafting(player,berryMixerTE,berryMixerTE,1,32,0)
			{
				@Override
				public void onPickupFromSlot(EntityPlayer player,ItemStack itemStack)
				{
					if(slotSugar!=null) slotSugar.decrStackSize(1);
					for(Slot slot:slotBerries)
					{
						if(slot!=null)
							slot.decrStackSize(1);
					}
				}
			};

			this.addSlotToContainer(slotSugar); // 糖
			this.addSlotToContainer(slotCrafting);
			for(int i=0;i<slotBerries.length;i++)
			{
				slotBerries[i]=new Slot(berryMixerTE, i+2, i * 32, 16)
				{
					@Override
					public void onSlotChange(ItemStack itemStack, ItemStack itemStack2)
					{
//						if (itemStack != null && itemStack2 != null)
//						{
//							if (itemStack.getItem() == itemStack2.getItem())
//							{
//								int i = itemStack2.stackSize - itemStack.stackSize;
//
//								if (i > 0)
//								{
//									this.onCrafting(itemStack, i);
//								}
//							}
//						}
						super.onSlotChange(itemStack,itemStack2);
//						boolean canMix=true;
//						if(!slotSugar.getHasStack()||slotCrafting.getHasStack())
//							canMix=false;
//						for(Slot slot:slotBerries)
//						{
//							if(slot==null)
//								canMix=false;
//						}
//						if(canMix)
//						{
//							slotCrafting.putStack(new ItemStack(Items.bread,2));
//						}
						if(berryMixerTE.canProcess())
						{
							ItemStack processResult=berryMixerTE.tryProcess();
							berryMixerTE.setInventorySlotContents(1,processResult);
						}

					}
				};
			}
			for (int i = 2; i < berryMixerTE.getSizeInventory(); ++i)
			{
				this.addSlotToContainer(slotBerries[i-2]);
			}

			for (int i = 0; i < 3; ++i)
			{
				for (int j = 0; j < 9; ++j)
				{
					this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
				}
			}
			for (int i = 0; i < 9; ++i)
			{
				this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 109));
			}
		}

		@Override
		public boolean canInteractWith(EntityPlayer playerIn)
		{
			return true;
		}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
		{
			return null;
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
		ServerSide serverSide=null;
		public ClientSide(ServerSide inventorySlotsIn)
		{
			super(inventorySlotsIn);
			serverSide=inventorySlotsIn;
			sugar=new Slot(inventorySlotsIn.berryMixerTE,0,1,1);
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
			ItemStack sugar=serverSide.berryMixerTE.getStackInSlot(0);
			this.drawString(this.fontRendererObj,"sugar : "+(sugar==null||sugar.stackSize<=0?0:sugar.stackSize)
					,1,1,0x66ccff);

			this.drawVerticalLine(30, 19, 36, 0xFF000000);
			this.drawHorizontalLine(8, 167, 43, 0xFF000000);

			String title = "莓果混合器";
			this.fontRendererObj.drawString(title,
					(this.xSize - this.fontRendererObj.getStringWidth(title)) / 2,
					6, 0x404040);

			ItemStack item = new ItemStack(Items.apple);
			//public void renderItemAndEffectIntoGUI(FontRenderer p_82406_1_,
			// TextureManager p_82406_2_,
			// final ItemStack p_82406_3_, int p_82406_4_, int p_82406_5_)
			this.itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj,this.mc.getTextureManager(),
					item, 8, 20);
		}

	}
}