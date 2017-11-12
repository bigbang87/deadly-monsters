package com.dmonsters.entity;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.ai.EntityAIDrowning;
import com.dmonsters.ai.EntityAIStranger;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModSounds;
import com.dmonsters.network.PacketClientSetVelocity;
import com.dmonsters.network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class EntityStranger extends EntityMob {
    private static final DataParameter<Boolean> ARMS_RAISED = EntityDataManager.createKey(EntityStranger.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(EntityStranger.class, DataSerializers.BOOLEAN);
    
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "stranger");
    
    public EntityStranger(World worldIn) {
        super(worldIn);
        setSize(1.0F, 1.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D * ModConfig.speedMultiplier * ModConfig.entrailSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D * ModConfig.strengthMultiplier * ModConfig.entrailStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D * ModConfig.healthMultiplier * ModConfig.entrailHealthMultiplier);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIStranger(this, 1.0D, true));
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
        	this.playSound(ModSounds.STRANGER_ATTACK, 1, 1);
        	if (entityIn instanceof EntityPlayer) {
        		hitPlayer(entityIn);
        		entityIn.attackEntityFrom(DamageSource.GENERIC, 10);
        	}
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void onLivingUpdate() {
        if (this.world.isDaytime() && !this.world.isRemote) {
            float f = this.getBrightness();
            BlockPos blockpos = this.getRidingEntity() instanceof EntityBoat ? (new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ)).up() : new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos)) {
            	this.setFire(8);
            }
        }
        super.onLivingUpdate();
    }
    
    private void hitPlayer(Entity entityIn) {
    	if (world.isRemote)
    		return;
    	this.playSound(ModSounds.STRANGER_IMPACT, 1, 1);
    	Random rnd = new Random();
    	float rndNum = rnd.nextFloat() + 0.1F;
    	entityIn.addVelocity(0, rndNum, 0);
    }
    
    @Override
    protected SoundEvent getDeathSound()
    {
    	return ModSounds.STRANGER_DEATH;
    }
    
    @Override
    protected SoundEvent getAmbientSound()
    {
    	return ModSounds.STRANGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource dmgSource)
    {
    	return ModSounds.STRANGER_HURT;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }
    
    /*
    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere() && this.getEntityWorld().getLightBrightness(this.getPosition()) < 5;
    }
    */
}
