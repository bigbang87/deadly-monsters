package com.dmonsters.ai;

import com.dmonsters.entity.EntityFreezer;
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
}