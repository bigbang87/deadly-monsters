package com.dmonsters.ai;

import com.dmonsters.entity.EntityStranger;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIStranger extends EntityAIAttackMelee
{
    private final EntityStranger stranger;

    public EntityAIStranger(EntityStranger strangerIn, double speedIn, boolean longMemoryIn) {
        super(strangerIn, speedIn, longMemoryIn);
        this.stranger = strangerIn;
    }
}