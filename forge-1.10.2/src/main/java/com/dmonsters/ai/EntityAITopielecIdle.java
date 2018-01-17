package com.dmonsters.ai;

import com.dmonsters.entity.EntityTopielec;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAITopielecIdle extends EntityAIBase {
    private final EntityTopielec topielec;

    public EntityAITopielecIdle(EntityTopielec _topielec) {
        this.topielec = _topielec;
    }

    public boolean shouldExecute() {
        return true;
    }

    public void updateTask() {
        int i = this.topielec.getAge();
        if (i > 100) {
            this.topielec.setMovementVector(0.0F, 0.0F, 0.0F);
        } else if (this.topielec.getRNG().nextInt(50) == 0 || !this.topielec.isInWater() || !this.topielec.hasMovementVector()) {
            float f = this.topielec.getRNG().nextFloat() * ((float)Math.PI * 2F);
            float f1 = MathHelper.cos(f) * 0.2F;
            float f2 = -0.1F + this.topielec.getRNG().nextFloat() * 0.2F;
            float f3 = MathHelper.sin(f) * 0.2F;
            this.topielec.setMovementVector(f1, f2, f3);
        }
    }
}