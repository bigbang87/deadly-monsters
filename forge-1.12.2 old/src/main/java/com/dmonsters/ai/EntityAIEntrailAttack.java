package com.dmonsters.ai;

import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.main.ModSounds;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIEntrailAttack extends EntityAIAttackMelee
{
    private final EntityEntrail mob;
    private int raiseArmTicks;

    public EntityAIEntrailAttack(EntityEntrail mobIn, double speedIn, boolean longMemoryIn)
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