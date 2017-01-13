package com.dmonsters.entity;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.ai.EntityAIBabyAttack;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;

public class EntityBaby extends EntityMob {
    
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "baby");
    private int blindRefreshTick = 0;
    

    public EntityBaby(World worldIn) {
        super(worldIn);
        setSize(0.9F, 1.95F);
        initEntityAI();
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D * ModConfig.speedMultiplier * ModConfig.babySpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D * ModConfig.strengthMultiplier * ModConfig.babyStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D * ModConfig.healthMultiplier * ModConfig.babyHealthMultiplier);
    }
    
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canSeeSky(blockpos)) {
            	this.setFire(8);
            }
        }
        if (!this.worldObj.isRemote && this.getAttackTarget() != null) {
        	if (blindRefreshTick == 20) {
        		blindRefreshTick = 0;
            	((EntityLivingBase)this.getAttackTarget()).addPotionEffect(new PotionEffect(Potion.blindness.id, 100));
        	} else {
        		blindRefreshTick++;
        	}
        }
        super.onLivingUpdate();
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIBabyAttack(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    	this.applyEntityAI();
    }

    private void applyEntityAI() {
    	this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
        	((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 600));
        	this.playSound("dmonsters:mob.baby.attack", 1, 1);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    protected String getDeathSound()
    {
		return "dmonsters:mob.baby.death";
    }
    
    @Override
    protected String getLivingSound()
    {
    	return "dmonsters:mob.baby.idle";
    }

    @Override
    protected String getHurtSound()
    {
    	return "dmonsters:mob.baby.hurt";
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
        return super.getCanSpawnHere() && this.posY < 20;
    }
}
