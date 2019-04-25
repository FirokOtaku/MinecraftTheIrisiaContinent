package firok.irisia.common;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import baubles.api.BaublesApi;
import com.google.common.io.Files;
import cpw.mods.fml.client.SplashProgress;
import firok.irisia.DamageSources;
import firok.irisia.Irisia;
import firok.irisia.Keys;
import firok.irisia.item.EquipmentSets;
import firok.irisia.item.EquipmentUniqueBaubles;
import firok.irisia.item.RawMaterials;
import firok.irisia.potion.Potions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.command.CommandWeather;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.events.EventHandlerEntity;
import thaumcraft.common.lib.research.ResearchManager;


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

	    PotionEffect midas=enlb.getActivePotionEffect(Potions.Midas);
	    int factor=midas==null?0:midas.getAmplifier()+1; // 如果目标有迈达斯buff 掉钱更多

	    Random rand=world.rand;
	    if(rand.nextFloat()<0.01+enlb.getMaxHealth()/400) // 每10滴血上限+2.5%掉落几率
	    {
	    	enlb.entityDropItem(new ItemStack(RawMaterials.SoulCrystal),0);
	    }
	    enlb.entityDropItem(new ItemStack(RawMaterials.CoinCopper,(int)Math.ceil((1+factor*0.4)*enlb.getMaxHealth()/4)),0);

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
				    // todo 这里以后替换成HashMap实现 现在的写得太死了
			    	if(ea1.set==EquipmentSets.WindRangerSet)
				    {
					    // player.addChatComponentMessage(new ChatComponentText("wind ranger"));
					    player.addPotionEffect(new PotionEffect(Potions.WindRanger.id,85,0));
				    }
				    else if(ea1.set==EquipmentSets.DwartMinerSet)
				    {
				    	player.addPotionEffect(new PotionEffect(Potion.digSpeed.id,85,0));
				    }
				    else if(ea1.set==EquipmentSets.GarrisonSet)
				    {
				    	player.addPotionEffect(new PotionEffect(Potion.resistance.id,85,0));
				    }
				    else if(ea1.set==EquipmentSets.NinjiaSet)
				    {
				    	player.addPotionEffect(new PotionEffect(Potions.Ninjia.id,85,0));
				    	player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,85,0));
					    player.addPotionEffect(new PotionEffect(Potion.jump.id,85,0));
				    }
				    else if(ea1.set==EquipmentSets.StormSet)
				    {
				    	player.addPotionEffect(new PotionEffect(Potions.Electrostatic.id,85,0));
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

	    EntityPlayer player=event.entityPlayer;
	    IInventory baubles=BaublesApi.getBaubles(player);
	    if(baubles!=null)
	    {
	    	for(int i=0;i<baubles.getSizeInventory();i++)
		    {
		    	ItemStack stackInSlot=baubles.getStackInSlot(i);
		    	if(stackInSlot!=null&&stackInSlot.getItem()==EquipmentUniqueBaubles.MidasRelic) // 迈达斯判定
			    {
			    	((EntityLivingBase) event.target).addPotionEffect(new PotionEffect(Potions.Midas.id,200,1));
			    	break;
			    }
		    }
	    }
	    // Collection effects=enlb.getActivePotionEffects();

	    // 黩武debuff解除判定
	    PotionEffect militaristic=player.getActivePotionEffect(Potions.Militaristic);
	    if(militaristic!=null)
	    {
	    	if(player.worldObj.rand.nextFloat()<0.08) // 每次攻击只有8%几率消除黩武debuff
		    {
		    	player.removePotionEffect(Potions.Militaristic.id);
		    	player.worldObj.playSoundAtEntity(player,Keys.SoundGulp,1,1); // todo 这里以后换成别的声音
		    }
	    }

	    // 法力增幅buff结算
	    PotionEffect amplificative=player.getActivePotionEffect(Potions.MagicAmplificative);
	    if(amplificative!=null)
	    {
		    firok.irisia.ability.CauseDamage.toLiving((EntityLivingBase) event.target,DamageSources.MagicAmplificativeDamage,
				    (1+amplificative.getAmplifier())*4,true);
	    }

	    if(event.target instanceof EntityLivingBase) // potion leaderly // 领袖buff结算
	    {
		    List players=player.worldObj.getEntitiesWithinAABBExcludingEntity(player,
				    AxisAlignedBB.getBoundingBox(player.posX-5,player.posY-3,player.posZ-5,
						    player.posX+5,player.posY+3,player.posZ+5),
				    EntitySelectors.SelectPlayerAlive);
		    int levelLeaderly=0;
		    for(Object obj:players)
		    {
			    EntityPlayer playerNearby=(EntityPlayer)obj;
			    PotionEffect leaderly=playerNearby.getActivePotionEffect(Potions.Leaderly);
			    if(leaderly!=null)
			    {
				    levelLeaderly+=1+leaderly.getAmplifier();
			    }
		    }
		    firok.irisia.ability.CauseDamage.toLiving(
		    		(EntityLivingBase)event.target,
				    DamageSources.StoneDamage,3*levelLeaderly,
				    true);
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
	    EntityLivingBase enlb=event.entityLiving;

	    if(enlb.worldObj.isRemote)
	    	return;

	    float amount=event.ammount;

	    float rateMissPhy=0; // 闪避几率
	    float rateMissMag=0;
	    float maxHp=enlb.getMaxHealth();
	    float nowHp=enlb.getHealth();
	    boolean isFireDamage=event.source.isFireDamage();
	    // 如果是玩家 先判断身上的装备 提供一些装备效果
	    if(enlb instanceof EntityPlayer)
	    {
		    IInventory inv=BaublesApi.getBaubles((EntityPlayer)enlb);
		    for(int i=0;i<inv.getSizeInventory();i++)
		    {
			    ItemStack stackInSlot=inv.getStackInSlot(i);
			    if(stackInSlot==null) continue;

			    Item item=stackInSlot.getItem();
			    if(item== WainItems.PhecdaTheEcho && nowHp-amount<maxHp*0.1)
			    {
				    // 找到Echo护身符 检查是不是开启 另外是不是在等cd
				    NBTTagCompound nbt=stackInSlot.hasTagCompound()?stackInSlot.getTagCompound():new NBTTagCompound();
				    boolean isOn=nbt.hasKey("isOn")?nbt.getBoolean("isOn"):true;
				    int cd=nbt.hasKey("cd")?nbt.getInteger("cd"):0;

				    if(isOn&&cd<=0)
				    { // 一切就绪 给一个buff
					    enlb.worldObj.playSoundAtEntity(enlb,Keys.SoundDoor,1,1);
					    enlb.addPotionEffect(new PotionEffect(Potions.Echo.id,200,0));
					    cd=6;
				    }

				    nbt.setBoolean("isOn",isOn);
				    nbt.setInteger("cd",cd);
				    stackInSlot.setTagCompound(nbt);
			    }
			    else if(item==EquipmentUniqueBaubles.SylphBelt)
			    {
			    	rateMissPhy+=0.25f;
			    }
			    else if(isFireDamage && item==EquipmentUniqueBaubles.FrostyStone)
			    {
			    	amount=0;
			    }
		    }
	    }

	    // 先判断有没有风行和忍者效果 有的话先执行这个
	    PotionEffect ninjia=enlb.getActivePotionEffect(Potions.Ninjia);
	    PotionEffect windranger=enlb.getActivePotionEffect(Potions.WindRanger);
	    if(ninjia!=null)
	    {
	    	rateMissPhy+=ninjia.getAmplifier()*0.2+0.2;
	    }
	    if(windranger!=null)
	    {
	    	rateMissPhy+=1;
	    }
	    boolean isMagic=event.source.isMagicDamage();
	    if(isMagic && enlb.worldObj.rand.nextFloat()<rateMissMag)
	    {
		    event.setCanceled(true); // todo 以后加上音效
	    }
	    else if(!isMagic && enlb.worldObj.rand.nextFloat()<rateMissPhy)
	    {
		    event.setCanceled(true); // todo 以后加上音效
	    }

	    // 进行一些小效果的伤害计算
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

	    // 最后判断阈化效果 这个效果判定最强
	    PotionEffect thresholded=enlb.getActivePotionEffect(Potions.Thresholded);
	    if(thresholded!=null)
	    {
		    float maxDamage=16-thresholded.getAmplifier()*4;
		    if(maxDamage<0)maxDamage=0;
		    if(amount>maxDamage)amount=maxDamage;
	    }

	    if(amount<0) amount=0;
	    event.ammount=amount;
	    if(amount==0) event.setCanceled(true);

	    if(amount>0)
	    {
		    PotionEffect healing=enlb.getActivePotionEffect(Potions.Healing);
		    if(healing!=null)
		    {
			    enlb.removePotionEffect(Potions.Healing.id);
		    }
	    }

	    // echo
	    PotionEffect echo=enlb.getActivePotionEffect(Potions.Echo);
	    if(echo!=null && echo.getDuration()>0)
	    {
	    	event.setCanceled(true);
	    	enlb.worldObj.playSoundAtEntity(enlb, Keys.SoundGulp,1,1);
	    	enlb.heal(event.ammount);
	    }
    }
    public static String toString(DamageSource damage)
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