package com.dmonsters.entity;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.ai.EntityAIDrowning;
import com.dmonsters.ai.EntityAIEntrailAttack;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
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

public class EntityEntrail extends EntityMob {

    public EntityEntrail(World worldIn) {
        super(worldIn);
        setSize(0.9F, 1.95F);
        initEntityAI();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D * ModConfig.speedMultiplier * ModConfig.entrailSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D * ModConfig.strengthMultiplier * ModConfig.entrailStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D * ModConfig.healthMultiplier * ModConfig.entrailHealthMultiplier);
    }
    
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canSeeSky(blockpos)) {
            	this.setFire(8);
            }
        }
        
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        super.onLivingUpdate();
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIEntrailAttack(this, 1.0D, true));
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
        	this.playSound("dmonsters:mob.entrail.attack", 1, 1);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (!this.worldObj.isRemote && !source.isFireDamage()) {
        	double x, y, z = 0;
        	x = this.posX;
        	y = this.posY;
        	z = this.posZ;
        	Entity slime = new EntitySlime(this.worldObj);
        	slime.setPosition(x, y, z);
        	this.worldObj.spawnEntityInWorld(slime);
    	}
    	boolean value = super.attackEntityFrom(source, amount);
        return value;
    }
    
    @Override
    protected String getDeathSound()
    {
		return "dmonsters:mob.entrail.death";
    }
    
    @Override
    protected String getLivingSound()
    {
    	return "dmonsters:mob.entrail.idle";
    }

    @Override
    protected String getHurtSound()
    {
    	return "dmonsters:mob.entrail.hurt";
    }
    
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return super.getCanSpawnHere() && this.posY < 50;
    }
}
