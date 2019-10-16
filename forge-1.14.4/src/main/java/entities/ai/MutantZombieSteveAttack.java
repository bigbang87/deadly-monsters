package entities.ai;

import entities.TestEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class MutantZombieSteveAttack extends MeleeAttackGoal
{
   private final TestEntity entity;
   private int raiseArmTicks;
   
	public MutantZombieSteveAttack(TestEntity entityIn, double speedIn, boolean useLongMemory)
	{
		super(entityIn, speedIn, useLongMemory);
		this.entity = entityIn;
	}
	
	public void tick() {
		super.tick();
		++this.raiseArmTicks;
		if (this.raiseArmTicks >= 5 && this.attackTick < 10)
		{
			this.entity.setAggroed(true);
		} 
		else
		{
			this.entity.setAggroed(false);
		}
   }

}
