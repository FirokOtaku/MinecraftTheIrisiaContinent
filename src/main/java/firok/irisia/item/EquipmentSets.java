package firok.irisia.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.ability.CauseRepairItem;
import firok.irisia.ability.CauseVisRegen;
import firok.irisia.common.EntitySelectors;
import firok.irisia.potion.Potions;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.List;

import static firok.irisia.common.EventLoader.intervalEffectArmorTick;
import static firok.irisia.Util.ItemType;

public class EquipmentSets
{
	public final static EquipmentSet BoneSet;
	public final static EquipmentSet MithrilSet;
	public final static EquipmentSet AdamantiumSet;
	public final static EquipmentSet FlumetalSet;
	public final static EquipmentSet SpectreSet;
	public final static EquipmentSet DwartSteelSet;
	public final static EquipmentSet LifeWoodSet; // 已经转移
	public final static EquipmentSet SolitaSet;
	public final static EquipmentSet MogigaSet;
	public final static EquipmentSet DarkIronToolSet;
	public final static EquipmentSet LuxIronToolSet;
	public final static EquipmentSet VibrhythmToolSet;
	public final static EquipmentSet PhotosynthesisToolSet;

	public final static ArmorSet WolfFurSet;
	public final static ArmorSet IcyWolfFurSet;

	public final static EffectArmorSet WindRangerSet;
	public final static EffectArmorSet DwartMinerSet;
	public final static EffectArmorSet GarrisonSet;
	public final static EffectArmorSet NinjiaSet;
	public final static EffectArmorSet StormSet;
	public final static EffectArmorSet PhoneixSet;
	public final static EffectArmorSet DarkIronArmorSet;
	public final static EffectArmorSet LuxIronArmorSet;
	public final static EffectArmorSet PhotosynthesisArmorSet;

	public static final int timeEffectArmorBuff=intervalEffectArmorTick+5; // 套装提供的buff时间(tick)

	static
	{
		BoneSet=new EquipmentSet("bone",Materials.BoneArmor,Materials.BoneTool,true,true,true,false);
		MithrilSet=new EquipmentSet("mithril",Materials.MithrilArmor,Materials.MithrilTool,true,true,true,false);
		AdamantiumSet=new EquipmentSet("adamantium",Materials.AdamantiumArmor,Materials.AdamantiumTool,true,true,true,false);
		FlumetalSet=new EquipmentSet("flumetal",Materials.FlumetalArmor,Materials.FlumetalTool,true,true,true,false);
		SpectreSet=new EquipmentSet("spectre",Materials.SpectreArmor,Materials.SpectreTool,true,true,true,false);
		DwartSteelSet=new EquipmentSet("dwartsteel",Materials.DwartSteelArmor,Materials.DwartSteelTool,true,true,true,false);
		LifeWoodSet=new EquipmentSet("lifewood",Materials.LifeWoodArmor,Materials.LifeWoodTool,false,false,false,true); // 转移到 EquipmentAutoRepair
		SolitaSet=new EquipmentSet("solita",Materials.SolitaArmor,Materials.SolitaTool,true,true,true,false);
		MogigaSet=new EquipmentSet("mogiga",Materials.MogigaArmor,Materials.MogigaTool,true,true,true,false);

		ItemFunctionHandler.ItemHitEntityHandler onHitEntityDarkIron=(type,itemStack,target,player)->{
			itemStack.damageItem(2, player);
			target.addPotionEffect(new PotionEffect(Potion.blindness.id,type==ItemType.Sword?120:60,0));
			return true;
		};
		DarkIronToolSet =new EquipmentSet("darkiron",Materials.DarkIronArmor,Materials.DarkMetalTool,true,true,false,false)
				.setHitEntityHandler(ItemType.Sword,onHitEntityDarkIron)
				.setHitEntityHandler(ItemType.Pickaxe,onHitEntityDarkIron)
				.setHitEntityHandler(ItemType.Axe,onHitEntityDarkIron)
				.setHitEntityHandler(ItemType.Hoe,onHitEntityDarkIron)
				.setHitEntityHandler(ItemType.Spade,onHitEntityDarkIron);

		ItemFunctionHandler.ItemHitEntityHandler onHitEntityLuxIron=(type,itemStack,target,player)-> {
			itemStack.damageItem(2, player);
			boolean isUndead=target.getCreatureAttribute()==EnumCreatureAttribute.UNDEAD;
			target.attackEntityFrom(DamageSource.causeMobDamage(player),isUndead?14:8);
			return true;
		};
		LuxIronToolSet =new EquipmentSet("luxiron",Materials.LuxIronArmor,Materials.LuxMetalTool,true,true,false,false)
				.setHitEntityHandler(ItemType.Sword,onHitEntityLuxIron)
				.setHitEntityHandler(ItemType.Pickaxe,onHitEntityLuxIron)
				.setHitEntityHandler(ItemType.Axe,onHitEntityLuxIron)
				.setHitEntityHandler(ItemType.Hoe,onHitEntityLuxIron)
				.setHitEntityHandler(ItemType.Spade,onHitEntityLuxIron);

		VibrhythmToolSet=new EquipmentSet("vibrhythmiron",null,Materials.VibrhythmMetalTool,true,true,false,false);

		ItemFunctionHandler.ItemUpdateHandler onItemUpdatePhotosynthesis=(type,itemStack,world,entity,position,onHand)->{
			if(world.isRemote||entity.ticksExisted%40!=0) return;
			if(world.getLightBrightness((int)entity.posX,(int)entity.posY,(int)entity.posZ)>8)
				CauseRepairItem.To(itemStack, (EntityLivingBase) entity);
		};
		PhotosynthesisToolSet=new EquipmentSet("photosynthesis",null,Materials.PhotosynthesisMetalTool,true,true,false,false)
				.setUpdateHandler(ItemType.Sword,onItemUpdatePhotosynthesis)
				.setUpdateHandler(ItemType.Pickaxe,onItemUpdatePhotosynthesis)
				.setUpdateHandler(ItemType.Axe,onItemUpdatePhotosynthesis)
				.setUpdateHandler(ItemType.Hoe,onItemUpdatePhotosynthesis)
				.setUpdateHandler(ItemType.Spade,onItemUpdatePhotosynthesis);

		WolfFurSet=new ArmorSet("wolffur",Materials.WolfFurArmor);
		IcyWolfFurSet=new ArmorSet("icywolffur",Materials.IcyWolfFurArmor);

		WindRangerSet = new EffectArmorSet("windranger",ItemArmor.ArmorMaterial.CLOTH)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				player.addPotionEffect(new PotionEffect(Potions.WindRanger.id,timeEffectArmorBuff,0));
			}
		};
		DwartMinerSet = new EffectArmorSet("dwartminer",ItemArmor.ArmorMaterial.IRON)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				player.addPotionEffect(new PotionEffect(Potion.digSpeed.id,timeEffectArmorBuff,0));
			}
		};
		GarrisonSet = new EffectArmorSet("garrison", ItemArmor.ArmorMaterial.CHAIN)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				player.addPotionEffect(new PotionEffect(Potion.resistance.id,timeEffectArmorBuff,0));
			}
		};
		NinjiaSet = new EffectArmorSet("ninjia", ItemArmor.ArmorMaterial.CLOTH)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				player.addPotionEffect(new PotionEffect(Potions.Ninjia.id,timeEffectArmorBuff,0));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,timeEffectArmorBuff,0));
				player.addPotionEffect(new PotionEffect(Potion.jump.id,timeEffectArmorBuff,0));
			}
		};
		StormSet = new EffectArmorSet("storm", Materials.StormArmor)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				player.addPotionEffect(new PotionEffect(Potions.Electrostatic.id,timeEffectArmorBuff,0));
			}
		};
		PhoneixSet =new EffectArmorSet("phoenix",Materials.MogigaArmor)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				player.setFire(5);
			}
		};
		DarkIronArmorSet =new EffectArmorSet("darkiron",Materials.DarkIronArmor)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				World world=player.worldObj;
				if(world.isRemote) return;

				List<EntityLivingBase> entities=world.getEntitiesWithinAABBExcludingEntity(player,
						AxisAlignedBB.getBoundingBox(player.posX-6,player.posY-6,player.posZ-6,
								player.posX+6,player.posY+6,player.posZ+6),
						EntitySelectors.SelectEntityMonstersAlive);

				for(EntityLivingBase entity:entities)
				{
					entity.addPotionEffect(new PotionEffect(Potion.blindness.id,timeEffectArmorBuff,0));
				}
			}
		};
		LuxIronArmorSet =new EffectArmorSet("luxiron",Materials.LuxIronArmor)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				;
			}
		};
		PhotosynthesisArmorSet =new EffectArmorSet("photosynthesis",Materials.PhotosynthesisMetalArmor)
		{
			@Override
			public void performEffect(ItemStack headStack, ItemStack chestStack, ItemStack legStack, ItemStack bootStack, EntityPlayer player)
			{
				if(player.worldObj.getLightBrightness((int)player.posX,(int)player.posY,(int)player.posZ)<=8) return;
				CauseRepairItem.To(headStack,player);
				CauseRepairItem.To(chestStack,player);
				CauseRepairItem.To(legStack,player);
				CauseRepairItem.To(bootStack,player);
			}
		};
	}
	/* Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is boots */

	/**
	 * 物品事件管理器
	 * */
	public static class ItemFunctionHandler
	{
		public static interface ItemRightClickHandler
		{
			ItemStack onRightClick(ItemType type,ItemStack itemStack,World world,EntityPlayer entity);
		}
		public static interface ItemHitEntityHandler
		{
			boolean onHitEntity(ItemType type,ItemStack itemStack, EntityLivingBase target, EntityLivingBase player);
		}
		public static interface ItemUseHandler
		{
			boolean onUse(ItemType type,ItemStack stack, EntityPlayer player, World world,
			              int x, int y, int z, int side, float hitX, float hitY, float hitZ);
		}
		public static interface ItemUseFirstHandler
		{
			public boolean onItemUseFirst(ItemType type,ItemStack stack, EntityPlayer player, World world,
			                              int x, int y, int z, int side, float hitX, float hitY, float hitZ);
		}
		public static interface ItemStopUsingHandler
		{
			public void onPlayerStoppedUsing(ItemType type,ItemStack itemStack, World world,
			                                 EntityPlayer player, int tick);
		}
		public static interface ItemUsingTickHandler
		{
			public void onUsingTick(ItemType type,ItemStack stack, EntityPlayer player, int count);
		}
		public static interface ItemUpdateHandler
		{
			public void onUpdate(ItemType type,ItemStack itemStack, World world, Entity entity,
			                     int position, boolean onHand);
		}
		public static interface ItemSwingHandler
		{
			public boolean onEntitySwing(ItemType type,EntityLivingBase entity, ItemStack itemStack);
		}
		public static interface ItemEntityUpdateHandler
		{
			public boolean onEntityUpdate(EntityItem entityItem);
		}
		ItemRightClickHandler onRightClick=null;
		ItemHitEntityHandler onHitEntity=null;
		ItemUseHandler onUse=null;
		ItemUseFirstHandler onUseFirst=null;
		ItemStopUsingHandler onStopUsing=null;
		ItemUsingTickHandler onUsingTick=null;
		ItemUpdateHandler onUpdate=null;
		ItemSwingHandler onSwing=null;
		ItemEntityUpdateHandler onEntityUpdate=null;
	}
	/**
	 * 装甲事件管理器
	 */
	public static class ArmorFunctionHandler extends ItemFunctionHandler
	{
		public static interface ArmorAttackedHandler
		{
			public boolean onAttacked();
		}
		ArmorAttackedHandler onAttacked=null;
	}
	/**
	 * 饰品事件管理器
	 */
	public static class BaubleFunctionHandler extends ItemFunctionHandler
	{
		;
	}
	public static class EquipmentSet
	{
		public final ItemArmor.ArmorMaterial armorMaterial;
		public final Item.ToolMaterial toolMaterial;

		public final boolean hasWeapon;
		public final ItemSword Sword;
		private ItemFunctionHandler handlerSword;

		public final boolean hasTools;
		public final ItemPickaxe Pickaxe;
		private ItemFunctionHandler handlerPickaxe;
		public final ItemAxe Axe;
		private ItemFunctionHandler handlerAxe;
		public final ItemSpade Spade;
		private ItemFunctionHandler handlerSpade;
		public final ItemHoe Hoe;
		private ItemFunctionHandler handlerHoe;

		public final boolean hasArmor;
		public final ItemCustomArmor Helmet;
		private ArmorFunctionHandler handlerHelmet;
		public final ItemCustomArmor Chestplate;
		private ArmorFunctionHandler handlerChestplate;
		public final ItemCustomArmor Leggings;
		private ArmorFunctionHandler handlerLeggings;
		public final ItemCustomArmor Boots;
		private ArmorFunctionHandler handlerBoots;

		public final boolean hasBaubles;
		public final Ring Ring;
		private BaubleFunctionHandler handlerRings;
		public final Amulet Amulet;
		private BaubleFunctionHandler handlerAmulet;
		public final Belt Belt;
		private BaubleFunctionHandler handlerBelt;

		public final String materialName;

		public ItemFunctionHandler getItemFunctionHandler(ItemType type)
		{
			switch (type)
			{
				case Sword:
					return handlerSword;
//				case Helmet:
//					return handlerHelmet;
//				case Chestplate:
//					return handlerChestplate;
//				case Leggings:
//					return handlerLeggings;
//				case Boots:
//					return handlerBoots;
				case Pickaxe:
					return handlerPickaxe;
				case Axe:
					return handlerAxe;
				case Hoe:
					return handlerHoe;
				case Spade:
					return handlerSpade;
//				case Amulet:
//					return handlerAmulet;
//				case Belt:
//					return handlerBelt;
//				case Rings:
//					return handlerRings;
				default:
//				case Item:
//				case Block:
					return null;
			}
		}
		public ArmorFunctionHandler getArmorFunctionHandler(ItemType type)
		{
			switch(type)
			{
				case Helmet:
					return handlerHelmet;
				case Chestplate:
					return handlerChestplate;
				case Leggings:
					return handlerLeggings;
				case Boots:
					return handlerBoots;
				default:
					return null;
			}
		}
		public BaubleFunctionHandler getBaubleFunctionHandler(ItemType type)
		{
			switch (type)
			{
				case Amulet:
					return handlerAmulet;
				case Belt:
					return handlerBelt;
				case Rings:
					return handlerRings;
				default:
					return null;
			}
		}
		public EquipmentSet setRightClickHandler(ItemType type, ItemFunctionHandler.ItemRightClickHandler handler)
		{
			getItemFunctionHandler(type).onRightClick=handler;
			return this;
		}
		public EquipmentSet setHitEntityHandler(ItemType type, ItemFunctionHandler.ItemHitEntityHandler handler)
		{
			getItemFunctionHandler(type).onHitEntity=handler;
			return this;
		}
		public EquipmentSet setUseHandler(ItemType type, ItemFunctionHandler.ItemUseHandler handler)
		{
			getItemFunctionHandler(type).onUse=handler;
			return this;
		}
		public EquipmentSet setUseFirstHandler(ItemType type, ItemFunctionHandler.ItemUseFirstHandler handler)
		{
			getItemFunctionHandler(type).onUseFirst=handler;
			return this;
		}
		public EquipmentSet setStopUsingHandler(ItemType type, ItemFunctionHandler.ItemStopUsingHandler handler)
		{
			getItemFunctionHandler(type).onStopUsing=handler;
			return this;
		}
		public EquipmentSet setUsingTickHandler(ItemType type, ItemFunctionHandler.ItemUsingTickHandler handler)
		{
			getItemFunctionHandler(type).onUsingTick=handler;
			return this;
		}
		public EquipmentSet setUpdateHandler(ItemType type, ItemFunctionHandler.ItemUpdateHandler handler)
		{
			getItemFunctionHandler(type).onUpdate=handler;
			return this;
		}
		public EquipmentSet setSwinghandler(ItemType type, ItemFunctionHandler.ItemSwingHandler handler)
		{
			getItemFunctionHandler(type).onSwing=handler;
			return this;
		}
		public EquipmentSet setEntityUpdate(ItemType type, ItemFunctionHandler.ItemEntityUpdateHandler handler)
		{
			getItemFunctionHandler(type).onEntityUpdate=handler;
			return this;
		}
		// todo high 装甲和饰品相关

		public EquipmentSet(String mn,ItemArmor.ArmorMaterial am, Item.ToolMaterial tm)
		{
			this(mn,am,tm,true,true,true,true);
		}
		public EquipmentSet(String mn,ItemArmor.ArmorMaterial am, Item.ToolMaterial tm,boolean hasWeapon,boolean hasTool,boolean hasArmor,boolean hasBauble)
		{
			materialName=mn;
			armorMaterial=am;
			toolMaterial=tm;

			handlerSword=new ItemFunctionHandler();
			handlerPickaxe=new ItemFunctionHandler();
			handlerAxe=new ItemFunctionHandler();
			handlerSpade=new ItemFunctionHandler();
			handlerHoe=new ItemFunctionHandler();
			handlerHelmet=new ArmorFunctionHandler();
			handlerChestplate=new ArmorFunctionHandler();
			handlerLeggings=new ArmorFunctionHandler();
			handlerBoots=new ArmorFunctionHandler();
			handlerRings =new BaubleFunctionHandler();
			handlerAmulet=new BaubleFunctionHandler();
			handlerBelt=new BaubleFunctionHandler();

			if(this.hasWeapon =hasWeapon && tm!=null)
				Sword=new ItemSword(tm)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerSword.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerSword.onRightClick.onRightClick(ItemType.Sword,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerSword.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerSword.onHitEntity.onHitEntity(ItemType.Sword,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerSword.onStopUsing!=null)
							handlerSword.onStopUsing.onPlayerStoppedUsing(ItemType.Sword,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerSword.onUseFirst!=null?
								handlerSword.onUseFirst.onItemUseFirst(ItemType.Sword,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerSword.onUse!=null?
								handlerSword.onUse.onUse(ItemType.Sword,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerSword.onUsingTick!=null)
							handlerSword.onUsingTick.onUsingTick(ItemType.Sword,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerSword.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerSword.onSwing.onEntitySwing(ItemType.Sword,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerSword.onUpdate!=null)
							handlerSword.onUpdate.onUpdate(ItemType.Sword,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerSword.onEntityUpdate!=null?
								handlerSword.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
			else
				Sword=null;

			if(hasTools=hasTool && tm!=null)
			{
				Pickaxe=new ItemPickaxe(tm){
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerPickaxe.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerPickaxe.onRightClick.onRightClick(ItemType.Pickaxe,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerPickaxe.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerPickaxe.onHitEntity.onHitEntity(ItemType.Pickaxe,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerPickaxe.onStopUsing!=null)
							handlerPickaxe.onStopUsing.onPlayerStoppedUsing(ItemType.Pickaxe,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerPickaxe.onUseFirst!=null?
								handlerPickaxe.onUseFirst.onItemUseFirst(ItemType.Pickaxe,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerPickaxe.onUse!=null?
								handlerPickaxe.onUse.onUse(ItemType.Pickaxe,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerPickaxe.onUsingTick!=null)
							handlerPickaxe.onUsingTick.onUsingTick(ItemType.Pickaxe,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerPickaxe.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerPickaxe.onSwing.onEntitySwing(ItemType.Pickaxe,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerPickaxe.onUpdate!=null)
							handlerPickaxe.onUpdate.onUpdate(ItemType.Pickaxe,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerPickaxe.onEntityUpdate!=null?
								handlerPickaxe.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Axe=new ItemAxe(tm){
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerAxe.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerAxe.onRightClick.onRightClick(ItemType.Axe,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerAxe.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerAxe.onHitEntity.onHitEntity(ItemType.Axe,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerAxe.onStopUsing!=null)
							handlerAxe.onStopUsing.onPlayerStoppedUsing(ItemType.Axe,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerAxe.onUseFirst!=null?
								handlerAxe.onUseFirst.onItemUseFirst(ItemType.Axe,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerAxe.onUse!=null?
								handlerAxe.onUse.onUse(ItemType.Axe,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerAxe.onUsingTick!=null)
							handlerAxe.onUsingTick.onUsingTick(ItemType.Axe,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerAxe.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerAxe.onSwing.onEntitySwing(ItemType.Axe,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerAxe.onUpdate!=null)
							handlerAxe.onUpdate.onUpdate(ItemType.Axe,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerAxe.onEntityUpdate!=null?
								handlerAxe.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Spade=new ItemSpade(tm)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerSpade.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerSpade.onRightClick.onRightClick(ItemType.Spade,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerSpade.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerSpade.onHitEntity.onHitEntity(ItemType.Spade,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerSpade.onStopUsing!=null)
							handlerSpade.onStopUsing.onPlayerStoppedUsing(ItemType.Spade,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerSpade.onUseFirst!=null?
								handlerSpade.onUseFirst.onItemUseFirst(ItemType.Spade,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerSpade.onUse!=null?
								handlerSpade.onUse.onUse(ItemType.Spade,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerSpade.onUsingTick!=null)
							handlerSpade.onUsingTick.onUsingTick(ItemType.Spade,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerSpade.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerSpade.onSwing.onEntitySwing(ItemType.Spade,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerSpade.onUpdate!=null)
							handlerSpade.onUpdate.onUpdate(ItemType.Spade,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerSpade.onEntityUpdate!=null?
								handlerSpade.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Hoe=new ItemHoe(tm)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerHoe.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerHoe.onRightClick.onRightClick(ItemType.Hoe,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerHoe.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerHoe.onHitEntity.onHitEntity(ItemType.Hoe,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerHoe.onStopUsing!=null)
							handlerHoe.onStopUsing.onPlayerStoppedUsing(ItemType.Hoe,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerHoe.onUseFirst!=null?
								handlerHoe.onUseFirst.onItemUseFirst(ItemType.Hoe,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerHoe.onUse!=null?
								handlerHoe.onUse.onUse(ItemType.Hoe,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerHoe.onUsingTick!=null)
							handlerHoe.onUsingTick.onUsingTick(ItemType.Hoe,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerHoe.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerHoe.onSwing.onEntitySwing(ItemType.Hoe,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerHoe.onUpdate!=null)
							handlerHoe.onUpdate.onUpdate(ItemType.Hoe,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerHoe.onEntityUpdate!=null?
								handlerHoe.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
			}
			else
			{
				Pickaxe=null;
				Axe=null;
				Spade=null;
				Hoe=null;
			}

			if(this.hasArmor =hasArmor && am!=null)
			{
				Helmet=new ItemCustomArmor(am, am.ordinal(), 0)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerHelmet.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerSword.onRightClick.onRightClick(ItemType.Sword,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerSword.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerSword.onHitEntity.onHitEntity(ItemType.Sword,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerSword.onStopUsing!=null)
							handlerSword.onStopUsing.onPlayerStoppedUsing(ItemType.Sword,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerSword.onUseFirst!=null?
								handlerSword.onUseFirst.onItemUseFirst(ItemType.Sword,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerSword.onUse!=null?
								handlerSword.onUse.onUse(ItemType.Sword,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerSword.onUsingTick!=null)
							handlerSword.onUsingTick.onUsingTick(ItemType.Sword,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerSword.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerSword.onSwing.onEntitySwing(ItemType.Sword,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerSword.onUpdate!=null)
							handlerSword.onUpdate.onUpdate(ItemType.Sword,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerHelmet.onEntityUpdate!=null?
								handlerHelmet.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Chestplate=new ItemCustomArmor(am, am.ordinal(), 1)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerChestplate.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerChestplate.onRightClick.onRightClick(ItemType.Chestplate,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerChestplate.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerChestplate.onHitEntity.onHitEntity(ItemType.Chestplate,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerChestplate.onStopUsing!=null)
							handlerChestplate.onStopUsing.onPlayerStoppedUsing(ItemType.Chestplate,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerChestplate.onUseFirst!=null?
								handlerChestplate.onUseFirst.onItemUseFirst(ItemType.Chestplate,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerChestplate.onUse!=null?
								handlerChestplate.onUse.onUse(ItemType.Chestplate,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerChestplate.onUsingTick!=null)
							handlerChestplate.onUsingTick.onUsingTick(ItemType.Chestplate,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerChestplate.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerChestplate.onSwing.onEntitySwing(ItemType.Chestplate,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerChestplate.onUpdate!=null)
							handlerChestplate.onUpdate.onUpdate(ItemType.Chestplate,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerChestplate.onEntityUpdate!=null?
								handlerChestplate.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Leggings=new ItemCustomArmor(am, am.ordinal(), 2)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerLeggings.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerLeggings.onRightClick.onRightClick(ItemType.Leggings,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerLeggings.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerLeggings.onHitEntity.onHitEntity(ItemType.Leggings,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerLeggings.onStopUsing!=null)
							handlerLeggings.onStopUsing.onPlayerStoppedUsing(ItemType.Leggings,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerLeggings.onUseFirst!=null?
								handlerLeggings.onUseFirst.onItemUseFirst(ItemType.Leggings,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerLeggings.onUse!=null?
								handlerLeggings.onUse.onUse(ItemType.Leggings,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerLeggings.onUsingTick!=null)
							handlerLeggings.onUsingTick.onUsingTick(ItemType.Leggings,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerLeggings.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerLeggings.onSwing.onEntitySwing(ItemType.Leggings,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerLeggings.onUpdate!=null)
							handlerLeggings.onUpdate.onUpdate(ItemType.Leggings,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerLeggings.onEntityUpdate!=null?
								handlerLeggings.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Boots=new ItemCustomArmor(am, am.ordinal(), 3)
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerBoots.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerBoots.onRightClick.onRightClick(ItemType.Boots,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerBoots.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerBoots.onHitEntity.onHitEntity(ItemType.Boots,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerBoots.onStopUsing!=null)
							handlerBoots.onStopUsing.onPlayerStoppedUsing(ItemType.Boots,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerBoots.onUseFirst!=null?
								handlerBoots.onUseFirst.onItemUseFirst(ItemType.Boots,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerBoots.onUse!=null?
								handlerBoots.onUse.onUse(ItemType.Boots,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerBoots.onUsingTick!=null)
							handlerBoots.onUsingTick.onUsingTick(ItemType.Boots,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerBoots.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerBoots.onSwing.onEntitySwing(ItemType.Boots,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerBoots.onUpdate!=null)
							handlerBoots.onUpdate.onUpdate(ItemType.Boots,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerBoots.onEntityUpdate!=null?
								handlerBoots.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
			}
			else
			{
				Helmet=null;
				Chestplate=null;
				Leggings=null;
				Boots=null;
			}

			if(hasBaubles=hasBauble)
			{
				Ring=new Ring()
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerRings.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerRings.onRightClick.onRightClick(ItemType.Rings,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerRings.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerRings.onHitEntity.onHitEntity(ItemType.Rings,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerRings.onStopUsing!=null)
							handlerRings.onStopUsing.onPlayerStoppedUsing(ItemType.Rings,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerRings.onUseFirst!=null?
								handlerRings.onUseFirst.onItemUseFirst(ItemType.Rings,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerRings.onUse!=null?
								handlerRings.onUse.onUse(ItemType.Rings,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerRings.onUsingTick!=null)
							handlerRings.onUsingTick.onUsingTick(ItemType.Rings,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerRings.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerRings.onSwing.onEntitySwing(ItemType.Rings,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerRings.onUpdate!=null)
							handlerRings.onUpdate.onUpdate(ItemType.Rings,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerRings.onEntityUpdate!=null?
								handlerRings.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Amulet=new Amulet()
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerAmulet.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerAmulet.onRightClick.onRightClick(ItemType.Amulet,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerAmulet.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerAmulet.onHitEntity.onHitEntity(ItemType.Amulet,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerAmulet.onStopUsing!=null)
							handlerAmulet.onStopUsing.onPlayerStoppedUsing(ItemType.Amulet,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerAmulet.onUseFirst!=null?
								handlerAmulet.onUseFirst.onItemUseFirst(ItemType.Amulet,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerAmulet.onUse!=null?
								handlerAmulet.onUse.onUse(ItemType.Amulet,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerAmulet.onUsingTick!=null)
							handlerAmulet.onUsingTick.onUsingTick(ItemType.Amulet,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerAmulet.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerAmulet.onSwing.onEntitySwing(ItemType.Amulet,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerAmulet.onUpdate!=null)
							handlerAmulet.onUpdate.onUpdate(ItemType.Amulet,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerAmulet.onEntityUpdate!=null?
								handlerAmulet.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
				Belt=new Belt()
				{
					@Override
					public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
					{
						return handlerBelt.onRightClick==null?
								super.onItemRightClick(itemStack,world,player):
								handlerBelt.onRightClick.onRightClick(ItemType.Belt,itemStack,world,player);
					}

					@Override
					public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
					{
						return handlerBelt.onHitEntity==null?
								super.hitEntity(itemStack,target,player):
								handlerBelt.onHitEntity.onHitEntity(ItemType.Belt,itemStack,target,player);
					}

					@Override
					public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int tick)
					{
						if(handlerBelt.onStopUsing!=null)
							handlerBelt.onStopUsing.onPlayerStoppedUsing(ItemType.Belt,itemStack,world,player,tick);
						else
							super.onPlayerStoppedUsing(itemStack, world, player, tick);
					}

					@Override
					public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerBelt.onUseFirst!=null?
								handlerBelt.onUseFirst.onItemUseFirst(ItemType.Belt,stack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
					{
						return handlerBelt.onUse!=null?
								handlerBelt.onUse.onUse(ItemType.Belt,itemStack,player,world,x,y,z,side,hitX,hitY,hitZ):
								super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
					}

					@Override
					public void onUsingTick(ItemStack itemStack, EntityPlayer player, int count)
					{
						if(handlerBelt.onUsingTick!=null)
							handlerBelt.onUsingTick.onUsingTick(ItemType.Belt,itemStack,player,count);
						else
							super.onUsingTick(itemStack, player, count);
					}

					@Override
					public boolean onEntitySwing(EntityLivingBase entity, ItemStack itemStack)
					{
						return handlerBelt.onSwing==null?
								super.onEntitySwing(entity,itemStack):
								handlerBelt.onSwing.onEntitySwing(ItemType.Belt,entity,itemStack);
					}

					@Override
					public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean onHand)
					{
						if(handlerBelt.onUpdate!=null)
							handlerBelt.onUpdate.onUpdate(ItemType.Belt,itemStack,world,entity,slot,onHand);
						else
							super.onUpdate(itemStack, world, entity, slot, onHand);
					}

					@Override
					public boolean onEntityItemUpdate(EntityItem entityItem)
					{
						return handlerBelt.onEntityUpdate!=null?
								handlerBelt.onEntityUpdate.onEntityUpdate(entityItem):
								super.onEntityItemUpdate(entityItem);
					}
				};
			}
			else
			{
				Ring=null;
				Amulet=null;
				Belt=null;
			}
		}

		public boolean inSet(Item item)
		{
			return inSetWeapon(item)||inSetTool(item)||inSetArmor(item)||inSetBauble(item);
		}
		public boolean inSetWeapon(Item item)
		{
			return item==this.Sword;
		}
		public boolean inSetTool(Item item)
		{
			return item==this.Pickaxe||item==this.Axe||item==this.Hoe||item==this.Spade;
		}
		public boolean inSetArmor(Item item)
		{
			return item==this.Helmet||item==this.Chestplate||item==this.Leggings||item==this.Boots;
		}
		public boolean inSetBauble(Item item)
		{
			return item==this.Ring||item==this.Amulet||item==this.Belt;
		}
	}

	/**
	 * 装甲套装
	 * 只包含装甲的配件
	 */
	public static class ArmorSet
	{
		public final String materialName;
		public final ItemArmor.ArmorMaterial armorMaterial;
		public final ItemCustomArmor Helmet;
		public final ItemCustomArmor Chestplate;
		public final ItemCustomArmor Leggings;
		public final ItemCustomArmor Boots;

		public ArmorSet(String mn,ItemArmor.ArmorMaterial armorMaterial)
		{
			this.armorMaterial=armorMaterial;
			Helmet=new ItemCustomArmor(armorMaterial, armorMaterial.ordinal(), 0);
			Chestplate=new ItemCustomArmor(armorMaterial, armorMaterial.ordinal(), 1);
			Leggings=new ItemCustomArmor(armorMaterial, armorMaterial.ordinal(), 2);
			Boots=new ItemCustomArmor(armorMaterial, armorMaterial.ordinal(), 3);
			this.materialName=mn;
		}
		protected ArmorSet(String mn,
		                   ItemArmor.ArmorMaterial armorMaterial,
		                   ItemCustomArmor helmet,ItemCustomArmor chestplate,
		                   ItemCustomArmor leggings,ItemCustomArmor boots)
		{
			this.materialName=mn;
			this.armorMaterial=armorMaterial;
			this.Helmet=helmet;
			this.Chestplate=chestplate;
			this.Leggings=leggings;
			this.Boots=boots;
		}
	}
	public static abstract class EffectArmorSet extends ArmorSet
	{
		public EffectArmorSet(String mn,ItemArmor.ArmorMaterial armorMaterial)
		{
			super(mn,armorMaterial,
					new EffectArmorPart(armorMaterial, 0),
					new EffectArmorPart(armorMaterial, 1),
					new EffectArmorPart(armorMaterial, 2),
					new EffectArmorPart(armorMaterial, 3));
			((EffectArmorPart)this.Helmet).set=this;
			((EffectArmorPart)this.Chestplate).set=this;
			((EffectArmorPart)this.Leggings).set=this;
			((EffectArmorPart)this.Boots).set=this;
		}
		public static class EffectArmorPart extends ItemCustomArmor
		{
			public EffectArmorSet set;
			public EffectArmorPart(ItemArmor.ArmorMaterial armorMaterial,int type)
			{
				super(armorMaterial,armorMaterial.ordinal(),type);
			}

			@Override
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
			{
				if(this.armorType==0)
					switchEnableEffect(itemStack);
				return super.onItemRightClick(itemStack, world, player);
			}

			@Override
			public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean onHand)
			{
				// super.addInformation(itemStack, player, info, onHand);
				if(this.armorType==0)
				{
					info.add(StatCollector.translateToLocal(
							getEnableEffect(itemStack)?
									Keys.InfoEnableArmorSetEffect:
									Keys.InfoDisableArmorSetEffect
					));
				}
			}
		}
		public static final String keyEnable="enable";
		public static void switchEnableEffect(ItemStack headStack)
		{
			NBTTagCompound nbt=headStack.hasTagCompound()?headStack.getTagCompound():new NBTTagCompound();
			nbt.setBoolean(keyEnable,nbt.hasKey(keyEnable)?!nbt.getBoolean(keyEnable):true);
			headStack.setTagCompound(nbt);
		}
		public static void setEnableEffect(ItemStack headStack,boolean enable)
		{
			NBTTagCompound nbt=headStack.hasTagCompound()?headStack.getTagCompound():new NBTTagCompound();
			nbt.setBoolean(keyEnable,enable);
			headStack.setTagCompound(nbt);
		}
		public static boolean getEnableEffect(ItemStack headStack)
		{
			return headStack.hasTagCompound() && headStack.getTagCompound().hasKey(keyEnable)?
					headStack.getTagCompound().getBoolean(keyEnable) : false;
		}

		public void performEffect(ItemStack[] armorStacks,EntityPlayer player)
		{
			performEffect(armorStacks[0],armorStacks[1],armorStacks[2],armorStacks[3],player);
		}
		public abstract void performEffect(ItemStack headStack,ItemStack chestStack,ItemStack legStack,ItemStack bootStack,
		                                   EntityPlayer player);

		public static EffectArmorSet getCurrentEquipmentedEffectArmorSet(EntityPlayer player)
		{
			ItemStack[] armors=player.inventory.armorInventory;
			if(armors[0]!=null
				&&armors[1]!=null
				&&armors[2]!=null
				&&armors[3]!=null) // 检查是不是每个物品栏都有东西
			{
				Item a1=armors[0].getItem();
				Item a2=armors[1].getItem();
				Item a3=armors[2].getItem();
				Item a4=armors[3].getItem();
				// player.addChatComponentMessage(new ChatComponentText("not null"));
				if(a1 instanceof EquipmentSets.EffectArmorSet.EffectArmorPart
						&& a2 instanceof EquipmentSets.EffectArmorSet.EffectArmorPart
						&& a3 instanceof EquipmentSets.EffectArmorSet.EffectArmorPart
						&& a4 instanceof EquipmentSets.EffectArmorSet.EffectArmorPart) // 检查是不是都是效果套的散件
				{
					EquipmentSets.EffectArmorSet.EffectArmorPart ea1=(EquipmentSets.EffectArmorSet.EffectArmorPart)a1;
					EquipmentSets.EffectArmorSet.EffectArmorPart ea2=(EquipmentSets.EffectArmorSet.EffectArmorPart)a2;
					EquipmentSets.EffectArmorSet.EffectArmorPart ea3=(EquipmentSets.EffectArmorSet.EffectArmorPart)a3;
					EquipmentSets.EffectArmorSet.EffectArmorPart ea4=(EquipmentSets.EffectArmorSet.EffectArmorPart)a4;
					// player.addChatComponentMessage(new ChatComponentText("effect parts"));
					if(ea1.set==ea2.set&&ea2.set==ea3.set&&ea3.set==ea4.set) // 检查是不是一套
					{
						return ea1.set;
					}
				}
			}
			return null;
		}

		// 凤凰套装火焰伤害转化
		public static void performPhoenixDamageTransform(EntityPlayer player,float amountDamage)
		{
			CauseVisRegen.AtPlayer(player,new AspectList().add(Aspect.FIRE,(int)amountDamage*25));
		}
	}

	/**
	 * 自定义装甲配件
	 * 主要重写了材质相关的功能
	 */
	public static class ItemCustomArmor extends ItemArmor
	{
//		public IIcon iconChest;
//		public IIcon iconLegs;
//		public IIcon iconBoots;
//		public IIcon iconChestOver;
//		public IIcon iconLegsOver;
//		public IIcon iconBootsOver;

		private IIcon icon;
		private String materialName;

		public ItemCustomArmor(ItemArmor.ArmorMaterial material,int index,int type)
		{
			this(material,null,index,type);
		}
		public ItemCustomArmor(ItemArmor.ArmorMaterial material,String name,int index,int type)
		{
			super(material,4,type);
			materialName=name==null?material.toString().toLowerCase():name;
			if(materialName.contains(":"))
			{
				materialName=materialName.split(":")[1];
			}
		}

		public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
			return this.armorType==2?
					Irisia.MODID+":textures/models/"+materialName+"_layer_2.png" :
					Irisia.MODID+":textures/models/"+materialName+"_layer_1.png";
		}
		@SideOnly(Side.CLIENT)
		public void registerIcons(IIconRegister ir)
		{
//			super.registerIcons(ir);
//			this.iconChest = ir.registerIcon("thaumcraft:clothchest");
//			this.iconLegs = ir.registerIcon("thaumcraft:clothlegs");
//			this.iconBoots = ir.registerIcon("thaumcraft:clothboots");
//			this.iconChestOver = ir.registerIcon("thaumcraft:clothchestover");
//			this.iconLegsOver = ir.registerIcon("thaumcraft:clothlegsover");
//			this.iconBootsOver = ir.registerIcon("thaumcraft:clothbootsover");
			icon=ir.registerIcon(this.getIconString());
		}
		@SideOnly(Side.CLIENT)
		public IIcon getIconFromDamage(int damage) {
			return this.icon==null?Items.iron_helmet.getIconFromDamage(damage):icon;
		}
		@Override
		public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
		{
			return getIcon(stack, renderPass);
		}
		@Override
		public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
			return getIconFromDamage(damage);
		}
	}


	/**
	 * 饰品基类
	 */
	public static abstract class ItemBauble extends Item implements IBauble
	{
		@Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
		{
			if (!par2World.isRemote) {
				InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(par3EntityPlayer);

				for(int i = 0; i < baubles.getSizeInventory(); ++i) {
					if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, par1ItemStack)) {
						baubles.setInventorySlotContents(i, par1ItemStack.copy());
						if (!par3EntityPlayer.capabilities.isCreativeMode) {
							par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, (ItemStack)null);
						}

						this.onEquipped(par1ItemStack, par3EntityPlayer);
						break;
					}
				}
			}

			return par1ItemStack;
		}

		@Override
		public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			;
		}

		@Override
		public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			if (!entityLivingBase.worldObj.isRemote)
			{
				entityLivingBase.worldObj
						.playSoundAtEntity(entityLivingBase, "random.orb", 0.1F, 1.3F);
			}
		}

		@Override
		public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			;
		}

		@Override
		public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			return true;
		}

		@Override
		public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase)
		{
			return true;
		}
	}

	/**
	 * 指环基类
	 */
	public static class Ring extends ItemBauble // 指环
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.RING;
		}
	}

	/**
	 * 护身符基类
	 */
	public static class Amulet extends ItemBauble // 护身符
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.AMULET;
		}
	}

	/**
	 * 腰带基类
	 */
	public static class Belt extends ItemBauble // 腰带
	{
		@Override
		public BaubleType getBaubleType(ItemStack itemStack)
		{
			return BaubleType.BELT;
		}
	}

}
/*public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
        if (!world.isRemote && stack.isItemDamaged() && entity.ticksExisted % 20 == 0 && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase)entity);
        }

    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        super.onArmorTick(world, player, armor);
        if (!world.isRemote && armor.getItemDamage() > 0 && player.ticksExisted % 20 == 0) {
            armor.damageItem(-1, player);
        }

    }*/