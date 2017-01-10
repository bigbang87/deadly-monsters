package com.dmonsters.ai;

import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.main.ModSounds;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;

public class EntityAIFreezerAttack extends EntityAIAttackOnCollide
{
    private final EntityFreezer freezer;
    private int raiseArmTicks;

    public EntityAIFreezerAttack(EntityFreezer freezerIn, double speedIn, boolean longMemoryIn)
    {
        super(freezerIn, speedIn, longMemoryIn);
        this.freezer = freezerIn;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
        freezer.setAttaking(true);
    }

    public void resetTask()
    {
        super.resetTask();
        this.freezer.setArmsRaised(false);
        freezer.setAttaking(false);
    }

    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.raiseArmTicks < 10)
        {
            this.freezer.setArmsRaised(true);
        }
        else
        {
            this.freezer.setArmsRaised(false);
        }
    }
}