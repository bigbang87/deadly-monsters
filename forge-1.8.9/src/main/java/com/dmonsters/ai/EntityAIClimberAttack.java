package com.dmonsters.ai;

import com.dmonsters.entity.EntityClimber;
import com.dmonsters.main.ModSounds;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;

public class EntityAIClimberAttack extends EntityAIAttackOnCollide
{
    private final EntityClimber climber;
    private int raiseArmTicks;

    public EntityAIClimberAttack(EntityClimber climberIn, double speedIn, boolean longMemoryIn)
    {
        super(climberIn, speedIn, longMemoryIn);
        this.climber = climberIn;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    public void resetTask()
    {
        super.resetTask();
    }
}