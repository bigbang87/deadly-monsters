package com.dmonsters.ai;

import com.dmonsters.entity.EntityHauntedCow;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.EntityZombie;

public class EntityAIHauntedCowAttack extends EntityAIAttackMelee
{
    private final EntityHauntedCow hauntedCow;
    private int raiseArmTicks;

    public EntityAIHauntedCowAttack(EntityHauntedCow chickenIn, double speedIn, boolean longMemoryIn)
    {
        super(chickenIn, speedIn, longMemoryIn);
        this.hauntedCow = chickenIn;
    }
}