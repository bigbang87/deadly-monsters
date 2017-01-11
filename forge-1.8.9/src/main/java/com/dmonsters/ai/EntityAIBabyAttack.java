package com.dmonsters.ai;

import com.dmonsters.entity.EntityBaby;
import com.dmonsters.main.ModSounds;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;

public class EntityAIBabyAttack extends EntityAIAttackOnCollide
{
    private final EntityBaby baby;
    private int raiseArmTicks;

    public EntityAIBabyAttack(EntityBaby babyIn, double speedIn, boolean longMemoryIn)
    {
        super(babyIn, speedIn, longMemoryIn);
        this.baby = babyIn;
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

    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;
    }
}