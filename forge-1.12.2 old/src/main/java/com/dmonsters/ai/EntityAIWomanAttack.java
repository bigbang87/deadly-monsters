package com.dmonsters.ai;

import com.dmonsters.entity.EntityWoman;
import com.dmonsters.main.ModSounds;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIWomanAttack extends EntityAIAttackMelee
{
    private final EntityWoman mob;
    private int raiseArmTicks;

    public EntityAIWomanAttack(EntityWoman mobIn, double speedIn, boolean longMemoryIn)
    {
        super(mobIn, speedIn, longMemoryIn);
        this.mob = mobIn;
    }

    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }
}