package firok.irisia.common;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.FMLLog;
import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import firok.irisia.client.KeyLoader;
import firok.irisia.enchantment.EnchantmentLoader;
import firok.irisia.item.EquipmentSets;
import firok.irisia.item.ItemLoader;
import firok.irisia.item.RawMaterials;
import firok.irisia.potion.PotionLoader;
import firok.irisia.potion.Potions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

import static net.minecraft.client.Minecraft.getMinecraft;


@SuppressWarnings("static-method")
public class EventLoader
{
    public EventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }


    private static boolean hasNoticed=false;
    @SubscribeEvent
    public void onPlayerChat(net.minecraftforge.event.ServerChatEvent e)
    {
    	if(Irisia.IN_DEV && hasNoticed)
    		return;

	    try
	    {
		    EntityClientPlayerMP player=Minecraft.getMinecraft().thePlayer;
		    if(player!=null && player.worldObj.isRemote)
		    {
			    player.addChatMessage(new ChatComponentText("警告：你正在使用一份开发版mod，此版本mod仅用于调试之用。"));
			    player.addChatMessage(new ChatComponentText("此版本中出现的任何内容都不会得到保证，请勿将本版本mod用于正式游玩。"));
			    hasNoticed=true;
		    }
	    }
	    catch (Exception e2)
	    {
		    ;
	    }
    }

    @SubscribeEvent
    public void onLivingDead(net.minecraftforge.event.entity.living.LivingDeathEvent event)
    {
    	//SoulCrystal
	    EntityLivingBase enlb=event.entityLiving;
	    World world=enlb.worldObj;
	    if(world.isRemote || enlb instanceof EntityPlayer)
	    	return;

	    Random rand=world.rand;
	    if(rand.nextFloat()<0.01+enlb.getMaxHealth()/400) // 每10滴血上限+2.5%掉落几率
	    {
	    	enlb.entityDropItem(new ItemStack(RawMaterials.SoulCrystal),0);
	    }

	    LootManager.dropLoot(enlb); // 调用掉落物管理器的接口 来掉落物品
    }

    @SubscribeEvent
    public void onPlayerTick_effectArmorSet(LivingEvent.LivingUpdateEvent event)
    {
	    if (!event.entity.worldObj.isRemote && event.entity.ticksExisted%80==0 && event.entity instanceof EntityPlayer) {
		    EntityPlayer player = (EntityPlayer)event.entity;
		    // player.addChatComponentMessage(new ChatComponentText("checked"));
		    ItemStack[] armors=player.inventory.armorInventory;
		    if(armors[0]==null
		    ||armors[1]==null
		    ||armors[2]==null
		    ||armors[3]==null)
		    	return; // 检查是不是每个物品栏都有东西
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
				    // player.addChatComponentMessage(new ChatComponentText("all set"));
			    	if(ea1.set==EquipmentSets.WindRangerSet)
				    {
					    // player.addChatComponentMessage(new ChatComponentText("wind ranger"));
				    	player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,85,0));
					    player.addPotionEffect(new PotionEffect(Potion.damageBoost.id,85,0));
				    }
			    }
		    }
	    }

    }

    @SubscribeEvent // 玩家攻击别的生物发生的事件
    // entity & entityLiving & entityPlayer 攻击其它生物的玩家
    // target 被攻击的生物entity
	public void onPlayerAttackOthers(net.minecraftforge.event.entity.player.AttackEntityEvent event)
    {
//	    System.out.println("AttackEntityEvent\nentity:"+event.entity.toString()
//			    +"\nentityLiving:"+event.entityLiving.toString()
//	            +"\nentityPlayer:"+event.entityPlayer.toString()
//	            +"\ntarget:"+event.target.toString());
	    if(!(event.target instanceof EntityLivingBase))
	    	return;
	    if(event.entity.worldObj.isRemote)
	    	return;

	    EntityLivingBase enlb=event.entityLiving;
	    Collection effects=enlb.getActivePotionEffects();

	    PotionEffect amplificative=enlb.getActivePotionEffect(Potions.MagicAmplificative);
	    if(amplificative!=null)
	    {
		    firok.irisia.ability.CauseDamage.toLiving((EntityLivingBase) event.target,DamageSources.MagicAmplificativeDamage,
				    (1+amplificative.getAmplifier())*4,true);
	    }
    }

    @SubscribeEvent // 有生物攻击其它生物发生的事件
    // entity & entityLiving 被打的entity
    // ammount 攻击力
    // damageType mob/player
	public void onLivingAttackEvent(net.minecraftforge.event.entity.living.LivingAttackEvent event)
    {
//	    System.out.println("LivingAttackEvent\nentity:"+event.entity.toString()
//			    +"\nentityLiving:"+event.entityLiving.toString()
//			    +"\nammount:"+event.ammount
//			    +"\ndamage::"+toString(event.source));


    }

    @SubscribeEvent // 有生物受伤发生的事件
    // entity & entityLiving 受伤的entity
    // ammount 伤害数值
    // damageType mob/player
	public void onLivingHurtEvent(net.minecraftforge.event.entity.living.LivingHurtEvent event)
    {
//	    System.out.println("LivingHurtEvent\nentity:"+event.entity.toString()
//			    +"\nentityLiving:"+event.entityLiving.toString()
//			    +"\nammount:"+event.ammount
//			    +"\ndamage::"+toString(event.source));
	    float amount=event.ammount;
	    // Wise;
	    // Folly;
	    // MagicAmplificative;
	    // MagicResistance;
	    // Thresholded;
	    // Ethereal;
	    EntityLivingBase enlb=event.entityLiving;

	    if(enlb.worldObj.isRemote)
	    	return;

	    Collection effects=enlb.getActivePotionEffects();
	    for(Object obj:effects)
	    {
		    PotionEffect effect=(PotionEffect)obj;
		    if(effect.getPotionID()==Potions.MagicResistance.id)
		    {
		    	amount*= 1 - 0.2*(effect.getAmplifier()+1);
		    }
		    else if(effect.getPotionID()==Potions.Ethereal.id)
		    {
		    	amount*=event.source.isMagicDamage()?1.5:0;
		    }
	    }

	    PotionEffect thresholded=enlb.getActivePotionEffect(Potions.Thresholded);
	    if(thresholded!=null)
	    {
		    float maxDamage=16-thresholded.getAmplifier()*4;
		    if(maxDamage<0)maxDamage=0;
		    if(amount>maxDamage)amount=maxDamage;
	    }

	    if(amount<0)amount=0;
	    event.ammount=amount;
    }
    private static String toString(DamageSource damage)
    {
    	StringBuffer ret=new StringBuffer();
	    ret.append("\ndamageType: ");ret.append(damage.damageType);
    	ret.append("\nisMagicDamage: ");ret.append(damage.isMagicDamage());
	    ret.append("\nisDamageAbsolute: ");ret.append(damage.isDamageAbsolute());
	    ret.append("\nisDifficultyScaled: ");ret.append(damage.isDifficultyScaled());
	    ret.append("\nisProjectile: ");ret.append(damage.isProjectile());
	    ret.append("\nisFireDamage: ");ret.append(damage.isFireDamage());
	    ret.append("\nisUnblockable: ");ret.append(damage.isUnblockable());
    	return ret.toString();
    }
}