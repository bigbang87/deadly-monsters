package com.dmonsters.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIChaseTroughBlocks extends EntityAIBase {
	
	private EntityCreature theEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private final double movementSpeed;

	public EntityAIChaseTroughBlocks(EntityCreature creatureIn, double speedIn) {
        this.theEntity = creatureIn;
        this.movementSpeed = speedIn;
        this.setMutexBits(1);
		theEntity = creatureIn;
		System.out.println("Radek: AI initialized");
	}
	
    public boolean shouldExecute()
    {
        if (this.theEntity.isWithinHomeDistanceCurrentPosition())
        {
            return false;
        }
        else
        {
            BlockPos blockpos = this.theEntity.getHomePosition();
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.theEntity, 16, 7, new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ()));

            if (vec3d == null)
            {
                return false;
            }
            else
            {
                this.movePosX = vec3d.xCoord;
                this.movePosY = vec3d.yCoord;
                this.movePosZ = vec3d.zCoord;
                System.out.println("Radek: should start");
                return true;
            }
        }
    }
    
    public boolean continueExecuting()
    {
    	boolean result = !this.theEntity.getNavigator().noPath();
    	System.out.println("Radek: continue result: " + result);
        return result;
    }

    public void startExecuting()
    {
    	System.out.println("Radek: start");
        this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
    }
}