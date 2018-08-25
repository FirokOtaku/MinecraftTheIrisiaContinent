package firok.irisia;

import net.minecraft.util.DamageSource;

public class DamageSources
{

	public final static DamageSource LightningDamege
	=new DamageSource("lightningDamage")
	.setDamageBypassesArmor()
	.setDamageIsAbsolute();
	
	public final static DamageSource AvariceDamege
	=new DamageSource("avariceDamage")
	.setDamageBypassesArmor()
	.setDamageIsAbsolute();
	
	public final static DamageSource PainboundDamege
	=new DamageSource("painboundDamage")
	.setDamageBypassesArmor()
	.setDamageIsAbsolute();
	
	public final static DamageSource ElectrostaticDamage
	=new DamageSource("electrostaticDamage")
	.setDamageBypassesArmor()
	.setDamageIsAbsolute();
}
