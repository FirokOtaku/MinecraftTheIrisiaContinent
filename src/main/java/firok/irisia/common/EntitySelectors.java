package firok.irisia.common;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class EntitySelectors
{
	public final static IEntitySelector SelectPlayerAlive = en -> en.isEntityAlive() && en instanceof EntityPlayer;

	public final static IEntitySelector SelectEntityLivingBaseAlive = en-> en.isEntityAlive() && en instanceof EntityLivingBase;

	public final static IEntitySelector SelectEntityMonstersAlive = en-> en.isEntityAlive() && en instanceof EntityMob;
}
