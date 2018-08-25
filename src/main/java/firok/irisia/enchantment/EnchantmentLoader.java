package firok.irisia.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class EnchantmentLoader
{
	public static CustomEnchantment Culling;
	public static CustomEnchantment tearing;
	public static CustomEnchantment inscriptionCapacity;
	public static CustomEnchantment shadowy;
	public static CustomEnchantment magicProtection;

	public static CustomEnchantment enderGuarding;

	public static CustomEnchantment Test;

    public EnchantmentLoader()
    {
//        culling=new Culling();
//        tearing=new Tearing();
	    Test =new CustomEnchantment("Test",160,10,
			    1,3,
			    1,100);

    }
    static
    {
    	// 淘汰
	    Culling=new CustomEnchantment(EnumEnchantmentType.weapon,"Culling",161,10,
			    0,3,
			    1,100){
		    @Override
		    public void func_151368_a(EntityLivingBase en1, Entity en2, int level)
		    {
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
	    public int getMinEnchantability(int p_77321_1_)
	    {
		    return 1 + p_77321_1_ * 10;
	    }

	    /**
	     * Returns the maximum value of enchantability nedded on the enchantment level passed.
	     */
	    public int getMaxEnchantability(int p_77317_1_)
	    {
		    return this.getMinEnchantability(p_77317_1_) + 5;
	    }

	    /**
	     * Calculates de damage protection of the enchantment based on level and damage source passed.
	     */
	    public int calcModifierDamage(int p_77318_1_, DamageSource p_77318_2_)
	    {
		    return 0;
	    }

	    /**
	     * Determines if the enchantment passed can be applyied together with this enchantment.
	     */
	    public boolean canApplyTogether(Enchantment p_77326_1_)
	    {
		    return this != p_77326_1_;
	    }

	    public boolean canApply(ItemStack p_92089_1_)
	    {
		    return this.type.canEnchantItem(p_92089_1_.getItem());
	    }

	    // func_151368_a=onEntityDamaged
	    // en1攻击别人的玩家 en2被攻击的对象
	    public void func_151368_a(EntityLivingBase en1, Entity en2, int level)
	    {
		    System.out.println("Test :"+en1.toString()+" en2 :"+en2.toString()+" level :"+level);
	    }
	    // func_151368_b=onUserHurt
		// en1被攻击的玩家 en2攻击者
	    public void func_151367_b(EntityLivingBase en1, Entity en2, int level)
	    {
		    System.out.println("Test :"+en1.toString()+" en2 :"+en2.toString()+" level :"+level);
	    }
	    // func_152376_a=calcDamageByCreature
	    public float func_152376_a(int level, EnumCreatureAttribute attr)
	    {
	    	System.out.println("level :"+level+" attr :"+attr.toString());
		    return 0.0F;
	    }

	    /**
	     * This applies specifically to applying at the enchanting table. The other method {@link #canApply(ItemStack)}
	     * applies for <i>all possible</i> enchantments.
	     * @param stack
	     * @return
	     */
	    public boolean canApplyAtEnchantingTable(ItemStack stack)
	    {
		    return canApply(stack);
	    }

	    /**
	     * Is this enchantment allowed to be enchanted on books via Enchantment Table
	     * @return false to disable the vanilla feature
	     */
	    public boolean isAllowedOnBooks()
	    {
		    return true;
	    }

    }
}
