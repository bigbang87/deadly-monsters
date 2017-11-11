package com.dmonsters.ai;

import com.dmonsters.entity.EntityZombieChicken;

import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.EntityZombie;

public class EntityAIChickenAttack extends EntityAIAttackMelee
{
    private final EntityZombieChicken chicken;
    private int raiseArmTicks;

    public EntityAIChickenAttack(EntityZombieChicken chickenIn, double speedIn, boolean longMemoryIn)
    {
        super(chickenIn, speedIn, longMemoryIn);
        this.chicken = chickenIn;
    }
}