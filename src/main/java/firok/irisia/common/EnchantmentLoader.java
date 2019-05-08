package firok.irisia.common;

import firok.irisia.Irisia;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

import static firok.irisia.common.ConfigLoader.*;

public class EnchantmentLoader
{
	private static int countCustomEnchantment=0;
	public final static CustomEnchantment Culling;
	public final static CustomEnchantment EnderGuarding;
	public final static CustomEnchantment Flown;
	public final static CustomEnchantment Shadowy;
	public final static CustomEnchantment Elastic;
	public final static CustomEnchantment FightingConsciousness; // 战意
	public static CustomEnchantment tearing;
	public static CustomEnchantment inscriptionCapacity;
	public static CustomEnchantment magicProtection;


	public static CustomEnchantment Test;

    public EnchantmentLoader()
    {
//        culling=new Culling();
//        tearing=new Tearing();
//	    Test =new CustomEnchantment("Test",160,10,
//			    1,3,
//			    1,100);

    }
    static
    {
    	// 淘汰
	    Culling=new CustomEnchantment(EnumEnchantmentType.weapon,"Culling", ideCulling, idewCulling,
			    0,3,
			    1,100){
		    @Override
		    public void func_151368_a(EntityLivingBase en1, Entity en2, int level)
		    {
		    	if(en1.worldObj.isRemote)
		    		return;

			    if(en2 instanceof EntityLivingBase)
			    {
			    	EntityLivingBase enlb=(EntityLivingBase)en2;
			    	float maxHealth=enlb.getMaxHealth();
			    	float nowHealth=enlb.getHealth();
			    	if(nowHealth/maxHealth<level*0.15)
				    {
				    	enlb.setDead();
				    }
			    }
		    }
	    };
	    // 末影守护
	    EnderGuarding=new CustomEnchantment(EnumEnchantmentType.armor,"EnderGuarding", ideGuarding, idewEnderGuarding,
			    0,3,
			    1,100)
	    {
		    // func_151368_b=onUserHurt
		    // en1被攻击的玩家 en2攻击者
		    @Override
		    public void func_151367_b(EntityLivingBase en1, Entity en2, int level)
		    {
		    	if(en1.worldObj.isRemote)
		    		return;

			    if(en1.getDistanceSqToEntity(en2)<=10+level*5)
			    {
				    firok.irisia.ability.CauseTeleportation.teleportEntityRandomly(en2,10+level*5);
			    }
		    }
	    };
	    // 击飞
	    Flown=new CustomEnchantment(EnumEnchantmentType.weapon,"Flown", ideFlown, idewFlown,
			    0,3,
			    1,100)
	    {
		    // func_151368_a=onEntityDamaged
		    // en1攻击别人的玩家 en2被攻击的对象
		    @Override
		    public void func_151368_a(EntityLivingBase en1, Entity en2, int level)
		    {
		    	if(en1.worldObj.isRemote)
		    		return;

			    en2.motionY+=0.6+level*0.4;
		    }
	    };
	    // 暗影
	    Shadowy=new CustomEnchantment(EnumEnchantmentType.armor,"Shadowy", ideShadowy, idewShadowy,
			    0,3,
			    1,100)
	    {
		    // func_151368_b=onUserHurt
		    // en1被攻击的玩家 en2攻击者
		    @Override
		    public void func_151367_b(EntityLivingBase en1, Entity en2, int level)
		    {
			    if(en1.worldObj.isRemote || !(en2 instanceof EntityLivingBase))
				    return;

			    EntityLivingBase enlb=(EntityLivingBase)en2;
			    enlb.addPotionEffect(new PotionEffect(Potion.blindness.id,80,0));
		    }
	    };
	    // 弹力
	    Elastic=new CustomEnchantment(EnumEnchantmentType.armor,"Elastic", ideElastic, idewElastic,
			    0,3,
			    1,100)
	    {
		    // func_151368_b = onUserHurt
		    // en1被攻击的玩家 en2攻击者
		    @Override
		    public void func_151367_b(EntityLivingBase en1, Entity en2, int level)
		    {
			    if(en1.worldObj.isRemote || en1.getDistanceSqToEntity(en2)>3+level*3)
				    return;
			    // TODO 这里需要实现 LOW 还不知道怎么写
			    ;
		    }
	    };
	    // 战意
	    FightingConsciousness=new CustomEnchantment(EnumEnchantmentType.weapon,"FightingConsciousness",ideFightingConsciousness,idewFightingConsciousness,
			    0,2,
			    1,100)
	    {
		    // func_151368_a=onEntityDamaged
		    // en1攻击别人的玩家 en2被攻击的对象
		    @Override
		    public void func_151368_a(EntityLivingBase en1, Entity en2, int level)
		    {
		    	if(en1.worldObj.isRemote)
		    		return;

			    if(en1.worldObj.rand.nextFloat()<0.3)
			    {
			    	Irisia.log("tick : "+en1.ticksExisted+" , level : "+level,(EntityPlayer)en1);
			    	int targetLevel;
			    	PotionEffect power=en1.getActivePotionEffect(Potion.damageBoost);
				    if(power==null)
				    	targetLevel=0;
				    else
				    	targetLevel=power.getAmplifier()+1;

				    if(targetLevel>level*2)
				    	targetLevel=level*2;

				    en1.addPotionEffect(new PotionEffect(Potion.damageBoost.id,300,targetLevel));

				    Irisia.log("tick:"+en1.ticksExisted+",附魔等级"+level+",本次等级:"+targetLevel,(EntityPlayer)en1);
			    }
		    }
	    };
    }

	public static class CustomEnchantment extends Enchantment
    {
    	public final int minLevel, maxLevel,maxEL,minEL;

    	public CustomEnchantment(String name,int id,int weight,
	                             int minEnchantmentLevel,int maxEnchantmentLevel,
	                             int minEnchantability,int maxEnchantability)
	    {
	    	this(EnumEnchantmentType.all,
				    name,id,weight,
				    minEnchantmentLevel,maxEnchantmentLevel,
				    minEnchantability,maxEnchantmentLevel);
	    }
	    public CustomEnchantment(EnumEnchantmentType typeIn,String name,int id,int weight,
	                             int minEnchantmentLevel,int maxEnchantmentLevel,
	                             int minEnchantability,int maxEnchantability)
	    {
	    	super(id,weight,typeIn);
	    	this.setName(name);
	    	minLevel=minEnchantmentLevel;
	    	maxLevel=maxEnchantmentLevel;
	    	minEL=minEnchantability;
	    	maxEL=maxEnchantability;

	    	System.out.println("使用id "+id+" 注册附魔 "+name);
		    countCustomEnchantment++;
	    }
	    @Override
	    public int getMinLevel()
	    {
		    return minLevel;
	    }

	    @Override
	    public int getMaxLevel()
	    {
		    return maxLevel;
	    }

	    /**
	     * Returns the minimal value of enchantability needed on the enchantment level passed.
	     */
	    @Override
	    public int getMinEnchantability(int p_77321_1_)
	    {
		    return 1 + p_77321_1_ * 10;
	    }

	    /**
	     * Returns the maximum value of enchantability nedded on the enchantment level passed.
	     */
	    @Override
	    public int getMaxEnchantability(int p_77317_1_)
	    {
		    return this.getMinEnchantability(p_77317_1_) + 5;
	    }

	    /**
	     * Calculates de damage protection of the enchantment based on level and damage source passed.
	     */
	    @Override
	    public int calcModifierDamage(int p_77318_1_, DamageSource p_77318_2_)
	    {
		    return 0;
	    }

	    /**
	     * Determines if the enchantment passed can be applyied together with this enchantment.
	     */
	    @Override
	    public boolean canApplyTogether(Enchantment p_77326_1_)
	    {
		    return this != p_77326_1_;
	    }

	    @Override
	    public boolean canApply(ItemStack p_92089_1_)
	    {
		    return this.type.canEnchantItem(p_92089_1_.getItem());
	    }

	    // func_151368_a=onEntityDamaged
	    // en1攻击别人的玩家 en2被攻击的对象
	    @Override
	    public void func_151368_a(EntityLivingBase en1, Entity en2, int level)
	    {
		    System.out.println("Test :"+en1.toString()+" en2 :"+en2.toString()+" level :"+level);
	    }
	    // func_151368_b=onUserHurt
		// en1被攻击的玩家 en2攻击者
	    @Override
	    public void func_151367_b(EntityLivingBase en1, Entity en2, int level)
	    {
		    System.out.println("Test :"+en1.toString()+" en2 :"+en2.toString()+" level :"+level);
	    }
	    // func_152376_a=calcDamageByCreature
	    @Override
	    public float func_152376_a(int level, EnumCreatureAttribute attr)
	    {
	    	System.out.println("level :"+level+" attr :"+attr.toString());
		    return 0.0F;
	    }

	    /**
	     * This applies specifically to applying $ the enchanting table. The other method {@link #canApply(ItemStack)}
	     * applies for <i>all possible</i> enchantments.
	     * @param stack
	     * @return
	     */
	    @Override
	    public boolean canApplyAtEnchantingTable(ItemStack stack)
	    {
		    return canApply(stack);
	    }

	    /**
	     * Is this enchantment allowed to be enchanted on books via Enchantment Table
	     * @return false to disable the vanilla feature
	     */
	    @Override
	    public boolean isAllowedOnBooks()
	    {
		    return true;
	    }
    }

	public static void info()
	{
		System.out.println("附魔数量 Total count of enchantments : "+countCustomEnchantment);
	}
}
