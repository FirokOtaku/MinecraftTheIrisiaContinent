package firok.irisia.client;

import firok.irisia.entity.Summons;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class EntityRenderers
{
	public static class RenderSummonedGolem extends RenderLiving
	{
		private static final ResourceLocation ironGolemTextures = new ResourceLocation("textures/entity/iron_golem.png");

		public final ResourceLocation texture;
		private final ModelIronGolem ironGolemModel;
		public RenderSummonedGolem()
		{
			this(ironGolemTextures);
		}
		public RenderSummonedGolem(ResourceLocation texture)
		{
			super(new ModelIronGolem(), 0.5F);
			this.ironGolemModel = (ModelIronGolem)this.mainModel;
			this.texture=texture;
		}/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */

		public void doRender(Summons.SummonedGolem p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
		{
			super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		}
		public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
		{
			this.doRender((Summons.SummonedGolem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		}
		public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
		{
			this.doRender((Summons.SummonedGolem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		}
		public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
		{
			this.doRender((Summons.SummonedGolem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
		}

		protected ResourceLocation getEntityTexture(Summons.SummonedGolem golem)
		{
			return texture;
		}

		protected void rotateCorpse(Summons.SummonedGolem golem, float p1, float p2, float p3)
		{
			super.rotateCorpse(golem, p1, p2, p3);

			if ((double)golem.limbSwingAmount >= 0.01D)
			{
				float f3 = 13.0F;
				float f4 = golem.limbSwing - golem.limbSwingAmount * (1.0F - p3) + 6.0F;
				float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
				GL11.glRotatef(6.5F * f5, 0.0F, 0.0F, 1.0F);
			}
		}

		protected void renderEquippedItems(Summons.SummonedGolem golem, float r)
		{
			super.renderEquippedItems(golem, r);

			if (golem.getHoldRoseTick() != 0)
			{
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glPushMatrix();
				GL11.glRotatef(5.0F + 180.0F * this.ironGolemModel.ironGolemRightArm.rotateAngleX / (float)Math.PI, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(-0.6875F, 1.25F, -0.9375F);
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				float f1 = 0.8F;
				GL11.glScalef(f1, -f1, f1);
				int i = golem.getBrightnessForRender(r);
				int j = i % 65536;
				int k = i / 65536;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				this.bindTexture(TextureMap.locationBlocksTexture);
				this.field_147909_c.renderBlockAsItem(Blocks.red_flower, 0, 1.0F);
				GL11.glPopMatrix();
				GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			}
		}

		protected void renderEquippedItems(EntityLivingBase golem, float e)
		{
			this.renderEquippedItems((Summons.SummonedGolem)golem, e);
		}

		protected void rotateCorpse(EntityLivingBase golem, float f1, float f2, float f3)
		{
			this.rotateCorpse((Summons.SummonedGolem)golem, f1, f2, f3);
		}

		protected ResourceLocation getEntityTexture(Entity golem)
		{
			return this.getEntityTexture((Summons.SummonedGolem)golem);
		}
	}
}
