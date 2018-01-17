package com.dmonsters.ai;

import com.dmonsters.entity.EntityTopielec;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class EntityAITopielecAttack extends EntityAIBase {
    private final EntityTopielec topielec;
    private final float speed;
    private BlockPos targetPos;
    private int ticks = 0;
    private EntityPlayerMP playerEntity;
    
    private final int searchDistance = 8;
    
    public EntityAITopielecAttack(EntityTopielec _owner, float _speed) {
        this.topielec = _owner;
        this.speed = _speed;
    }

    public boolean shouldExecute() {
    	EntityPlayerMP player = (EntityPlayerMP) this.topielec.getAttackTarget();
    	if (player != null) {
	    	double distance = this.topielec.getDistance(player.posX, player.posY, player.posZ);
	    	if (distance < 2d) {
	        	playerEntity = player;
	    		return true;
	    	}
    	}
        return false;
    }

    public void updateTask() {
    	double myX = this.topielec.posX;
    	double myY = this.topielec.posY;
    	double myZ = this.topielec.posZ;
    	playerEntity.setPositionAndUpdate(myX, myY, myZ);
    	
    	ticks++;
    	if (ticks > 40)
    		return;
    	else
    		ticks = 0;
    	BlockPos targetPos = findBestPosition();
    	//System.out.println(targetPos);
    	float[] normVec = normlizeVector(targetPos.subtract(this.topielec.getPosition()));
    	//System.out.println(normVec[0] + ", " + myY + ", " + normVec[2]);
    	this.topielec.setMovementVector(normVec[0], normVec[1], normVec[2]);
    }
    
    private float[] normlizeVector(BlockPos v) {
    	float length = (float) Math.sqrt((v.getX() * v.getX()) + (v.getY() * v.getY()) + (v.getZ() * v.getZ()));
    	float[] newVec = new float[3];
		newVec[0] = (v.getX() / length) * speed;
		newVec[1] = (v.getY() / length) * speed;
		newVec[2] = (v.getZ() / length) * speed;
    	return newVec;
    }
    
    private BlockPos findBestPosition() {
    	BlockPos myPos = this.topielec.getPosition();
    	BlockPos bestPos = myPos; 
    	int minBoundsX = -searchDistance + myPos.getX();
    	int maxBoundsX = searchDistance + myPos.getX();
    	int minBoundsZ = -searchDistance + myPos.getZ();
    	int maxBoundsZ = searchDistance + myPos.getZ();
    	World worldIn = this.topielec.getEntityWorld();
    	int deepestY = myPos.getY();
    	//System.out.println("START " + myPos);
    	//System.out.println(deepestY);
    	for (int x = minBoundsX; x < maxBoundsX; x++) {
        	for (int z = minBoundsZ; z < maxBoundsZ; z++) { 
        		int tempDeepestY = myPos.getY();
        		for (int y = myPos.getY(); y > 0; y--) {
        			BlockPos currPos = new BlockPos(x, y, z);
            		Block block = worldIn.getBlockState(currPos).getBlock();
            		//System.out.println(y + " " + block + ", " + currPos);
            		if (block == Blocks.WATER && y <= tempDeepestY) {
            			tempDeepestY = y;
            		} else {
            			if (tempDeepestY <= deepestY) {
            				deepestY = tempDeepestY;
            				bestPos = currPos;
            			}
            			break;
            		}
            	}
        	}
    	}
    	//System.out.println("END: " + bestPos);
    	return new BlockPos(bestPos.getX(), myPos.getY(), bestPos.getZ());
    }
}