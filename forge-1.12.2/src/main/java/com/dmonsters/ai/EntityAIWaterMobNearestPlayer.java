package com.dmonsters.ai;

import java.util.Collections;
import java.util.List;

import com.dmonsters.entity.EntityTopielec;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAIWaterMobNearestPlayer extends EntityAIBase {
    private EntityCreature owner;
    private EntityPlayer attackTarget;
    private float distance;

    public EntityAIWaterMobNearestPlayer(EntityCreature _owner, float _distance) {
        this.owner = _owner;
        this.distance = _distance;
    }

    public boolean shouldExecute() {
        EntityPlayer player = this.owner.world.getClosestPlayerToEntity(this.owner, this.distance);
        this.owner.setAttackTarget(player);
        return false;
        
      /*BlockPos AABB_01 = new BlockPos(this.owner.posX - this.distance, this.owner.posY - this.distance, this.owner.posZ - this.distance);
		BlockPos AABB_02 = new BlockPos(this.owner.posX + this.distance, this.owner.posY + this.distance, this.owner.posZ + this.distance);
		AxisAlignedBB AABB = new AxisAlignedBB(AABB_01, AABB_02);
        List<Entity> list = this.owner.world.getEntitiesWithinAABBExcludingEntity(this.owner, AABB);
        if (list.isEmpty()) {
            return false;
        } else {
        	for (int i = 0; i < list.size(); i++) {
            	Entity entity = list.get(i);
            	if (entity instanceof EntityPlayer) {
            		attackTarget = (EntityPlayer) entity;
                    return true;
            	}
        	}
        }
        */

    }
    
    public void startExecuting() {
        this.owner.setAttackTarget(this.attackTarget);
        super.startExecuting();
    }
}