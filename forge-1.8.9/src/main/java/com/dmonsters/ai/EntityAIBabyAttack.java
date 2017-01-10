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
        baby.setAttaking(true);
    }

    public void resetTask()
    {
        super.resetTask();
        this.baby.setArmsRaised(false);
        baby.setAttaking(false);
    }

    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.raiseArmTicks < 10)
        {
            this.baby.setArmsRaised(true);
        }
        else
        {
            this.baby.setArmsRaised(false);
        }
    }
}