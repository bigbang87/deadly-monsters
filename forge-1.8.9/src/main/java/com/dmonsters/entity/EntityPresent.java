package com.dmonsters.entity;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.ai.EntityAIDrowning;
import com.dmonsters.ai.EntityAIEntrailAttack;
import com.dmonsters.ai.EntityAIPresent;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;

public class EntityPresent extends EntityMob {    
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "present");
    
    private int cageTicks = 0;
    private boolean debugCage;

    public EntityPresent(World worldIn) {
        super(worldIn);
        setSize(0.9F, 1.5F);
        initEntityAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D * ModConfig.speedMultiplier * ModConfig.entrailSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D * ModConfig.strengthMultiplier * ModConfig.entrailStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(26.0D * ModConfig.healthMultiplier * ModConfig.entrailHealthMultiplier);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPresent(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    	this.applyEntityAI();
    }

    private void applyEntityAI() {
    	this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
        	this.playSound("dmonsters:mob.present.attack", 1, 1);
        	if (entityIn instanceof EntityPlayer) {
        		makeCage((EntityPlayer) entityIn);
        	}
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void onLivingUpdate() {
    	if (debugCage) {
    		cageTicks++;
    		if (cageTicks == 400) {
    			cageTicks = 0;
    			debugCage = false;
    		}
    	}
        super.onLivingUpdate();
    }

    private void makeCage(EntityPlayer player) {
    	if (debugCage)
    		return;
    	debugCage = true;
    	
    	int height = 3;
    	int hSize = 3;
    	int vSize = 7;
    	int xPos = (int)this.posX;
    	int yPos = (int)this.posY;
    	int zPos = (int)this.posZ;
    	BlockPos pos;
    	Block block;
    	int xCent;
    	int zCent;
    	int hCenter = (int)(hSize * 0.5F) - 1;
    	//walls
    	for (int i = 0; i < vSize; i++) {
	    	for (int x = -hSize; x < hSize + 1; x++) {
	        	for (int z = -hSize; z < hSize + 1; z++) {
	        		//System.out.println("EntityPresent:: x:" + x + ", y: " + z + ", i: " + i);
	        		if (x == -hSize || x == hSize || z == -hSize || z == hSize) {
		        		pos = new BlockPos(x + xPos, yPos + height + i, z + zPos);
		        		block = this.worldObj.getBlockState(pos).getBlock();
		        		if (block == Blocks.air) {
		        			if (x == hCenter ||
		        				z == hCenter  )
		        				this.worldObj.setBlockState(pos, ModBlocks.presentBlock.getStateFromMeta(1));
		        			else
		        				this.worldObj.setBlockState(pos, ModBlocks.presentBlock.getStateFromMeta(0));
		        		}
	        		} else if (i == 0 || i == vSize - 1) {
	            		pos = new BlockPos(x + xPos, yPos + i + height, z + zPos);
		        		block = this.worldObj.getBlockState(pos).getBlock();
		        		if (block == Blocks.air) {
		        			xCent = hCenter;
		        			zCent = hCenter;
		        			if (x == xCent && z == zCent) {
		        				BlockPos lightPos = new BlockPos(xCent + xPos, yPos + height + 1, zCent + zPos);
			        			this.worldObj.setBlockState(lightPos, Blocks.torch.getDefaultState());
			        			if (!this.worldObj.isRemote) {
				                	Entity creeper = new EntityCreeper(this.worldObj);
				                	creeper.setPosition(lightPos.getX(), lightPos.getY(), lightPos.getZ());
				                	this.worldObj.spawnEntityInWorld(creeper);
			        			}
		        			}
		        			if (x == 0 || z == 0)
		        				this.worldObj.setBlockState(pos, ModBlocks.presentBlock.getStateFromMeta(1));
		        			else
		        				this.worldObj.setBlockState(pos, ModBlocks.presentBlock.getStateFromMeta(0));
		        		}
	        		}
	        	}
	    	}
    	}
    	
    	player.setVelocity(0, 0, 0);
    	player.setPositionAndUpdate(xPos + hSize * 0.5F - 1, yPos + height + 1, zPos + hSize * 0.5F - 1);
    }
    
    @Override
    protected String getDeathSound()
    {
		return "dmonsters:mob.present.death";
    }
    
    @Override
    protected String getLivingSound()
    {
    	return "dmonsters:mob.present.idle";
    }

    @Override
    protected String getHurtSound()
    {
    	return "dmonsters:mob.present.hurt";
    }

    @Override
    public boolean getCanSpawnHere()
    {
    	BlockPos pos = this.getPosition();    	
        return super.getCanSpawnHere() && this.worldObj.canSeeSky(pos);
    }
}
