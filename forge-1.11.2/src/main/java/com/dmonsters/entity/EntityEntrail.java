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
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;

public class EntityEntrail extends EntityMob {
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityEntrail.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(EntityEntrail.class, DataSerializers.BOOLEAN);
    private PriorityQueue<BlockPos> freezedBlocks = new PriorityQueue(10); 
    
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "entrail");
    

    public EntityEntrail(World worldIn) {
        super(worldIn);
        setSize(0.9F, 1.95F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D * ModConfig.speedMultiplier * ModConfig.entrailSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D * ModConfig.strengthMultiplier * ModConfig.entrailStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D * ModConfig.healthMultiplier * ModConfig.entrailHealthMultiplier);
    }
    
    public void onLivingUpdate() {
        if (this.world.isDaytime() && !this.world.isRemote) {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = this.getRidingEntity() instanceof EntityBoat ? (new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ)).up() : new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos)) {
            	this.setFire(8);
            }
        }
        
        if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
        super.onLivingUpdate();
    }

    @Override
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
        	this.playSound(ModSounds.ENTRAIL_ATTACK, 1, 1);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (!this.world.isRemote && !source.isFireDamage()) {
        	double x, y, z = 0;
        	x = this.posX;
        	y = this.posY;
        	z = this.posZ;
        	Entity slime = new EntitySlime(this.world);
        	slime.setPosition(x, y, z);
        	this.world.spawnEntity(slime);
    	}
    	boolean value = super.attackEntityFrom(source, amount);
        return value;
    }
    
    @Override
    protected SoundEvent getDeathSound()
    {
    	return ModSounds.ENTRAIL_DEATH;
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
    	return ModSounds.ENTRAIL_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound()
    {
    	return ModSounds.ENTRAIL_HURT;
    }
    
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
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
    	if (ModConfig.entrailSpawnOnTheSurface)
    		return super.getCanSpawnHere();
        return super.getCanSpawnHere() && this.posY < 50;
    }
}
