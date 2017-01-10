package com.dmonsters.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIDrowning extends EntityAIBase
{
    private final EntityLiving theEntity;

    public EntityAIDrowning(EntityLiving entitylivingIn)
    {
        this.theEntity = entitylivingIn;
        this.setMutexBits(4);
    }

    public boolean shouldExecute()
    {
        return this.theEntity.isInWater() || this.theEntity.isInLava();
    }

    public void updateTask()
    {

    }
}