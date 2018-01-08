package com.dmonsters.entity;

import javax.annotation.Nullable;

import com.dmonsters.ai.EntityAIEntrailAttack;
import com.dmonsters.ai.EntityAITopielecAttack;
import com.dmonsters.ai.EntityAITopielecFollowPlayer;
import com.dmonsters.ai.EntityAITopielecIdle;
import com.dmonsters.ai.EntityAIWaterMobNearestPlayer;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTopielec extends EntityMob {
	
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "topielec");
    private float targetVectorX;
    private float targetVectorY;
    private float targetVectorZ;
    private double lastWaterX;
    private double lastWaterY;
    private double lastWaterZ;
    
    public EntityTopielec(World worldIn) {
        super(worldIn);
        this.setSize(1F, 1F);
    }
    
    @Override
    protected void initEntityAI() {
        //this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(1, new EntityAITopielecAttack(this, 0.5F));
        this.tasks.addTask(2, new EntityAITopielecFollowPlayer(this, 0.5F));
        this.tasks.addTask(3, new EntityAITopielecIdle(this));
    	this.applyEntityAI();
    }
    
    private void applyEntityAI() {
    	this.targetTasks.addTask(0, new EntityAIWaterMobNearestPlayer(this, 20));
    }
    
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.TOPIELEC_AMBINET;
    }

    protected SoundEvent getHurtSound()
    {
        return ModSounds.TOPIELEC_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSounds.TOPIELEC_DEATH;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean getCanSpawnHere() {
        return this.posY > 45.0D && this.posY < (double)this.world.getSeaLevel() && super.getCanSpawnHere();
    }

    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    public int getTalkInterval() {
        return 120;
    }

    protected boolean canDespawn() {
        return true;
    }
    
    public void moveEntityWithHeading(float strafe, float forward) {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
        this.targetVectorX = randomMotionVecXIn;
        this.targetVectorY = randomMotionVecYIn;
        this.targetVectorZ = randomMotionVecZIn;
    }
    
    public void setLastWaterPosition(double waterX, double waterY, double waterZ) {
        this.lastWaterX = waterX;
        this.lastWaterY = waterY;
        this.lastWaterZ = waterZ;
    }
    
    public boolean hasMovementVector() {
        return this.targetVectorX != 0.0F || this.targetVectorY != 0.0F || this.targetVectorZ != 0.0F;
    }

    public void onEntityUpdate() {
        int i = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater()) {
        	this.setPosition(lastWaterX, lastWaterY, lastWaterZ);
            this.motionX = 0.0D;
            this.motionZ = 0.0D;
            if (!this.hasNoGravity())
                this.motionY -= 0.5D;
            --i;
            this.setAir(i);

            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.motionX = (double)(this.targetVectorX);
            this.motionY = (double)(this.targetVectorY);
            this.motionZ = (double)(this.targetVectorZ);
            this.setLastWaterPosition(this.posX, this.posY, this.posZ);
            this.setAir(300);
        }
    }

    public boolean isPushedByWater() {
        return false;
    }
    
    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }
}