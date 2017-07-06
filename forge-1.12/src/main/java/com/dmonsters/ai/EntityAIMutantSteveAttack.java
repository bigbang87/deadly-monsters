package com.dmonsters.ai;

import java.util.Random;

import com.dmonsters.entity.EntityMutantSteve;
import com.dmonsters.main.ModSounds;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIMutantSteveAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityMutantSteve mutantSteve;
    private double targetPosX;
    private double targetPosY;
    private double targetPosZ;

    public EntityAIMutantSteveAttack(EntityMutantSteve zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.mutantSteve = zombieIn;
        this.setMutexBits(7);
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.mutantSteve.setArmsRaised(false);
    }

    private int ticks;
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.raiseArmTicks < 10) {
            this.mutantSteve.setArmsRaised(true);
        } else {
            this.mutantSteve.setArmsRaised(false);
        }

        ticks++;
        if (ticks == 20 && !this.mutantSteve.isInWater()) {
        	ticks = 0;
        	if (this.attacker.world.getGameRules().getBoolean("mobGriefing")) {
	        	DestroyAroundMe(0, 0.25F);
	        	DestroyAroundMe(1, 0.5F);
	        	DestroyAroundMe(2, 0.75F);
        	}
        }
    }
    
    private void DestroyAroundMe(int yOffset, float destroyChance) {
    	IBlockState blockToDestroy;
    	BlockPos blockToDestroyPos;
    	World worldin = this.attacker.world;
    	double y = this.attacker.posY;
    	float hardness;
    	float hardnessTreshold = 5;
    	boolean destroyedBlock = false;
    	float randomChance = 0;
    	Random random = new Random();
    	
    	for (int dx = -1; dx <= 1; ++dx) {
    		for (int dz = -1; dz <= 1; ++dz) {
    			if (dx == 0 && dz == 0)
    				continue;
    	    	blockToDestroyPos = new BlockPos(this.attacker.posX + dx, y + yOffset, this.attacker.posZ + dz);
    	    	blockToDestroy = worldin.getBlockState(blockToDestroyPos);
    	    	if (blockToDestroy.getBlock() != Blocks.AIR) {
    	        	hardness = blockToDestroy.getBlockHardness(this.attacker.world, blockToDestroyPos);
					if (hardness < hardnessTreshold) {
						randomChance = random.nextFloat();
    	            	if (randomChance < destroyChance) {
    	            		this.attacker.world.destroyBlock(blockToDestroyPos, true);
    	            		destroyedBlock = true;
    	            	}
					}
    	    	}
    		} 
    	}
    	
    	if (destroyedBlock) {
    		this.attacker.swingArm(EnumHand.MAIN_HAND);
    	}
    }
}