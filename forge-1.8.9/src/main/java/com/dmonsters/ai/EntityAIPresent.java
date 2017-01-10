package com.dmonsters.ai;

import com.dmonsters.entity.EntityMutantSteve;
import com.dmonsters.entity.EntityPresent;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIPresent extends EntityAIAttackOnCollide
{
    private final EntityPresent present;
    private int ticks;
    private boolean ready;

    public EntityAIPresent(EntityPresent presentIn, double speedIn, boolean longMemoryIn) {
        super(presentIn, speedIn, longMemoryIn);
        this.present = presentIn;
    }
}