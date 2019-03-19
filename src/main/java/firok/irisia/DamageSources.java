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

	public final static DamageSource MagicAmplificativeDamage // 魔力增幅的额外伤害
			=new DamageSource("magicAmplificativeDamage")
			.setMagicDamage();

	public final static DamageSource StoneDamage // 被石头砸中的伤害
			=new DamageSource("stoneDamage")
			.setProjectile();
	
	public final static DamageSource ElectrostaticDamage
			=new DamageSource("electrostaticDamage")
			.setDamageBypassesArmor()
			.setDamageIsAbsolute();

	public final static DamageSource CursedDamage
			=new DamageSource("cursedDamage")
			.setDamageBypassesArmor()
			.setMagicDamage()
			.setDamageIsAbsolute();

	public final static DamageSource MilitaristicDamage
			=new DamageSource("militaristicDamage")
			.setDamageBypassesArmor()
			.setMagicDamage()
			.setDamageIsAbsolute();

	public final static DamageSource PlagueDamage
			=new DamageSource("plagueDamage")
			.setDamageBypassesArmor()
			.setDamageIsAbsolute();

	public final static DamageSource AliothDamage // 玉衡 独有
			=new DamageSource("aliothDamage")
			.setDamageBypassesArmor()
			.setDamageIsAbsolute();
}
