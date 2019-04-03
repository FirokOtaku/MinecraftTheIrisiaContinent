package firok.irisia.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.common.EntitySelectors;
import firok.irisia.common.GashaponManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;

import java.util.List;

public class Consumables
{
	public final static Item Gashapon; // 扭蛋
	static
	{
		Gashapon=new Item() // 扭蛋
		{
			@Override
			@SideOnly(Side.CLIENT)
			public void addInformation(ItemStack itemStack, EntityPlayer player, List infos, boolean p_77624_4_)
			{
				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				String key=tag.hasKey("key")?tag.getString("key"):"null_table";
				float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;

				infos.add(new StringBuffer()
						.append("Key : ")
						.append(key)
						.toString());
				infos.add(new StringBuffer().append("Bonus :")
						.append(bonus*100)
						.append("%")
						.toString());
			}
			@Override
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				if(world.isRemote)
					return itemStack;

				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				String key=tag.hasKey("key")?tag.getString("key"):"null_table";
				float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;
				if(player.isSneaking())
				{
					List<ItemStack> rolls=GashaponManager.rollItemsFromTableWithBonus(key,bonus);
					for(ItemStack is:rolls)
					{
						player.entityDropItem(is,0);
					}
					if(!player.capabilities.isCreativeMode)
						itemStack.stackSize--;
				}
				else
				{
					player.addChatComponentMessage(new ChatComponentText(new StringBuffer()
							.append("Key : ")
							.append(key)
							.append(", Bonus :")
							.append(bonus*100)
							.append("%")
							.toString()));
				}
				return itemStack;
			}
		};
	}
	// 显影之尘
	public static final Item DustOfAppearance=new Item()
	{
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List infos, boolean p_77624_4_)
		{ // todo 这里以后加上提示信息
//			NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
//			String key=tag.hasKey("key")?tag.getString("key"):"null_table";
//			float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;
//
//			infos.add(new StringBuffer()
//					.append("Key : ")
//					.append(key)
//					.toString());
//			infos.add(new StringBuffer().append("Bonus :")
//					.append(bonus*100)
//					.append("%")
//					.toString());
		}
		@Override
		public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		{
			if(world.isRemote)
				return itemStack;

			List entities=world.getEntitiesWithinAABBExcludingEntity(player,
					AxisAlignedBB.getBoundingBox
							(player.posX-10,player.posY-10,player.posZ-10,
									player.posX+10,player.posY+10,player.posZ+10),
					player.isSneaking()?
							EntitySelectors.SelectPlayerAlive:EntitySelectors.SelectEntityMonstersAlive);
			if(!player.capabilities.isCreativeMode)
				itemStack.stackSize--;
			return itemStack;
		}
	};

	public static final Item ThaumicNote=new Item()
	{
		{
			this.setHasSubtypes(true);
			this.setMaxDamage(0);
		}

		/**
		 * Gets an icon index based on an item's damage value
		 */
//		@SideOnly(Side.CLIENT)
//		public IIcon getIconFromDamage(int p_77617_1_)
//		{
//			int j = MathHelper.clamp_int(p_77617_1_, 0, 15);
//			return this.field_150920_d[j];
//		}

		/**
		 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
		 * different names based on their damage or NBT.
		 */
//		public String getUnlocalizedName(ItemStack p_77667_1_)
//		{
//			int i = MathHelper.clamp_int(p_77667_1_.getItemDamage(), 0, 15);
//			return super.getUnlocalizedName() + "." + field_150923_a[i];
//		}
		/**
		 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
		 */
//		@SideOnly(Side.CLIENT)
//		public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
//		{
//			for (int i = 0; i < 16; ++i)
//			{
//				p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
//			}
//		}
//
//		@SideOnly(Side.CLIENT)
//		public void registerIcons(IIconRegister p_94581_1_)
//		{
//			this.field_150920_d = new IIcon[field_150921_b.length];
//
//			for (int i = 0; i < field_150921_b.length; ++i)
//			{
//				this.field_150920_d[i] = p_94581_1_.registerIcon(this.getIconString() + "_" + field_150921_b[i]);
//			}
//		}
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List infos, boolean p_77624_4_)
		{ // todo 这里以后加上提示信息
//			NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
//			String key=tag.hasKey("key")?tag.getString("key"):"null_table";
//			float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;
//
//			infos.add(new StringBuffer()
//					.append("Key : ")
//					.append(key)
//					.toString());
//			infos.add(new StringBuffer().append("Bonus :")
//					.append(bonus*100)
//					.append("%")
//					.toString());
		}
		final byte len=8;
		final String[] ks=new String[]{"a1","a2","a3","a4","a5","a6","a7","a8"};
		final String[] vs=new String[]{"c1","c2","c3","c4","c5","c6","c7","c8"};
		@Override
		public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		{
			if(world.isRemote)
				return itemStack;

			if(player.isSneaking())
			{
				int damage=itemStack.getItemDamage();
				int amountRandom=
						damage>0&&damage<=7?
								(8+world.rand.nextInt(3)):
						damage<=14?
								(15+world.rand.nextInt(5)):
						damage<=21?
								(25+world.rand.nextInt(7)):
								0;
				switch(damage)
				{
					default:
						break;
				}

				NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
				for(byte i=0;i<len;i++)
				{
					if(tag.hasKey(ks[i]))
					{
						Aspect aspect=Aspect.getAspect(tag.getString(ks[i]));
						if(aspect==null)
							continue;
						// todo 这里以后加上动画
						Thaumcraft.proxy.getPlayerKnowledge()
								.addAspectPool(player.getCommandSenderName(),aspect,tag.getShort(vs[i]));
					}
				}

				if(!player.capabilities.isCreativeMode)
					itemStack.stackSize--;
			}
			return itemStack;
		}
	};
	public static final Item MazeSummoner=new Item()
	{
		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack itemStack, EntityPlayer player, List infos, boolean p_77624_4_)
		{ // todo 这里以后加上提示信息
//			NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
//			String key=tag.hasKey("key")?tag.getString("key"):"null_table";
//			float bonus=tag.hasKey("bonus")?tag.getFloat("bonus"):0;
//
//			infos.add(new StringBuffer()
//					.append("Key : ")
//					.append(key)
//					.toString());
//			infos.add(new StringBuffer().append("Bonus :")
//					.append(bonus*100)
//					.append("%")
//					.toString());
		}
		@Override
		public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
		{
			if(world.isRemote)
				return itemStack;

			NBTTagCompound tag=itemStack.hasTagCompound()?itemStack.getTagCompound():new NBTTagCompound();
			if(player.isSneaking())
			{
				; // gen maze

				if(!player.capabilities.isCreativeMode)
					itemStack.stackSize--;
			}
			return itemStack;
		}
	};

}
