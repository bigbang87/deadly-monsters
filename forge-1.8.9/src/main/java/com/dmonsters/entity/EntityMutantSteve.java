package com.dmonsters.entity;

import javax.annotation.Nullable;

import com.dmonsters.ai.EntityAIDrowning;
import com.dmonsters.ai.EntityAIMutantSteveAttack;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityBoat;

public class EntityMutantSteve extends EntityMob {
	
    public static final ResourceLocation LOOT = new ResourceLocation(MainMod.MODID, "steve_zombie");

    public EntityMutantSteve(World worldIn) {
        super(worldIn);
        setSize(0.9F, 1.95F);
        initEntityAI();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.13D * ModConfig.speedMultiplier * ModConfig.mutantSteveSpeedMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(16.0D * ModConfig.strengthMultiplier * ModConfig.mutantSteveStrengthMultiplier);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D * ModConfig.healthMultiplier * ModConfig.mutantSteveHealthMultiplier);
    }

    protected void initEntityAI() {
    	this.tasks.addTask(1, new EntityAIMutantSteveAttack(this, 2.0D, false));
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
            if (entityIn instanceof EntityLivingBase) {
            	this.playSound("dmonsters:mob.mutant.attack", 1, 1);
            	((EntityLivingBase)entityIn).knockBack(entityIn, 2, this.posX + entityIn.posX, this.posZ + entityIn.posZ);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public void onLivingUpdate() {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
            float f = this.getBrightness(1.0F);
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);
            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canSeeSky(blockpos)) {
            	this.setFire(8);
            }
        }
        super.onLivingUpdate();
    }
    
    @Override
    protected String getDeathSound()
    {
		return "dmonsters:mob.mutant.death";
    }
    
    @Override
    protected String getLivingSound()
    {
    	return "dmonsters:mob.mutant.idle";
    }

    @Override
    protected String getHurtSound()
    {
    	return "dmonsters:mob.mutant.hurt";
    }
    
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
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
        return 2;
    }
    
    @Override
    protected void dropFewItems(boolean unknowBool, int num) {
    	int quantity = this.rand.nextInt(3) + 1;
	    this.dropItem(Items.leather, quantity);
	    
	    if (this.rand.nextFloat() < 0.5f)
	    	return;
		
		float rndNum = this.rand.nextFloat();
		if (rndNum < 0.5f) { //high
		    this.dropItem(new ItemStack(Blocks.piston).getItem(), 1);
		} else if (rndNum >= 0.5f && rndNum < 0.85f) { //medium
		    this.dropItem(new ItemStack(Blocks.sticky_piston).getItem(), 1);
		} else { //low

		}
    }
}
