package com.dmonsters.entityProjectile;

import java.util.ArrayList;
import java.util.List;

import com.dmonsters.entity.EntityZombieChicken;
import com.dmonsters.main.ModBlocks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityLuckyEgg extends EntityThrowable {
	
    public EntityLuckyEgg(World worldIn)
    {
        super(worldIn);
    }
    
    public EntityLuckyEgg(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }
    
    public EntityLuckyEgg(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

	@Override
	protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
        }
        
        if (!this.worldObj.isRemote) {
        	//worldObj.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), ModBlocks.meshFence.getDefaultState());
	        if (this.rand.nextInt(10) == 0) {
	        	Item spawnedItem = spawnRandomItem();
	        	ItemStack newItem = new ItemStack(spawnedItem, 1);
	        	EntityItem item = new EntityItem(worldObj, this.posX, this.posY, this.posZ, damageItem(newItem));
	        	worldObj.spawnEntityInWorld(item);
	        } else if (this.rand.nextInt(10) == 1) {
	        	BlockPos blockPos = new BlockPos(this.posX, this.posY, this.posZ);
	            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldObj, (double)((float)blockPos.getX() + 0.5F), (double)blockPos.getY(), (double)((float)blockPos.getZ() + 0.5F), this.getThrower());
	            entitytntprimed.setFuse((short)(worldObj.rand.nextInt(entitytntprimed.getFuse() / 4) + entitytntprimed.getFuse() / 8));
	            worldObj.spawnEntityInWorld(entitytntprimed); 
	        } else {
		        if (this.rand.nextInt(10) <= 4) {
		            EntityChicken entitychicken = new EntityChicken(this.worldObj);
		            entitychicken.setGrowingAge(-24000);
		            entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
		            this.worldObj.spawnEntityInWorld(entitychicken);
		        } else {
		        	EntityZombieChicken entityzombiechicken = new EntityZombieChicken(this.worldObj);
		        	entityzombiechicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
		            this.worldObj.spawnEntityInWorld(entityzombiechicken);
		        }
	        }
        }
        
        double d0 = 0.08D;

        for (int k = 0; k < 8; ++k)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {Item.getIdFromItem(Items.EGG)});
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
	}

	private <T>T getRandomEntryFromList(List<T> list) {
		int size = list.size();
		if (size == 0)
			return null;
		int rnd = this.rand.nextInt(size);
		return list.get(rnd);
	}
	
	private ItemStack damageItem(ItemStack itemStack) {
		int maxDamage = itemStack.getMaxDamage();
		int bonusRepair = (int)(maxDamage * 0.2F);
		int rnd = this.rand.nextInt(maxDamage) - bonusRepair;
		if (rnd > 0) {
			itemStack.damageItem(rnd, this.getThrower());
		}
		return itemStack;
	}
	
	private Item spawnRandomItem() {    	
    	//TIER 00
    	List<Item> dropableItems_TIER_00 = new ArrayList<Item>();
    	dropableItems_TIER_00.add(Items.WOODEN_SWORD);
    	dropableItems_TIER_00.add(Items.LEATHER_BOOTS);
    	dropableItems_TIER_00.add(Items.LEATHER_CHESTPLATE);
    	dropableItems_TIER_00.add(Items.LEATHER_HELMET);
    	dropableItems_TIER_00.add(Items.LEATHER_LEGGINGS);

    	//TIER 01
    	List<Item> dropableItems_TIER_01 = new ArrayList<Item>();
    	dropableItems_TIER_01.add(Items.IRON_SWORD);
    	dropableItems_TIER_01.add(Items.STONE_SWORD);
    	dropableItems_TIER_01.add(Items.IRON_BOOTS);
    	dropableItems_TIER_01.add(Items.IRON_CHESTPLATE);
    	dropableItems_TIER_01.add(Items.IRON_HELMET);
    	dropableItems_TIER_01.add(Items.IRON_LEGGINGS);
    	
    	//TIER 02
    	List<Item> dropableItems_TIER_02 = new ArrayList<Item>();
    	dropableItems_TIER_02.add(Items.SHIELD);
    	dropableItems_TIER_02.add(Items.BOW);
    	dropableItems_TIER_02.add(Items.CHAINMAIL_BOOTS);
    	dropableItems_TIER_02.add(Items.CHAINMAIL_CHESTPLATE);
    	dropableItems_TIER_02.add(Items.CHAINMAIL_HELMET);
    	dropableItems_TIER_02.add(Items.CHAINMAIL_LEGGINGS);
    	
    	//TIER 03
    	List<Item> dropableItems_TIER_03 = new ArrayList<Item>();
    	dropableItems_TIER_03.add(Items.GOLDEN_SWORD);
    	dropableItems_TIER_03.add(Items.GOLDEN_BOOTS);
    	dropableItems_TIER_03.add(Items.GOLDEN_CHESTPLATE);
    	dropableItems_TIER_03.add(Items.GOLDEN_HELMET);
    	dropableItems_TIER_03.add(Items.GOLDEN_LEGGINGS);
    	
    	//TIER 04
    	List<Item> dropableItems_TIER_04 = new ArrayList<Item>();
    	dropableItems_TIER_04.add(Items.DIAMOND_SWORD);
    	dropableItems_TIER_04.add(Items.DIAMOND_BOOTS);
    	dropableItems_TIER_04.add(Items.DIAMOND_CHESTPLATE);
    	dropableItems_TIER_04.add(Items.DIAMOND_HELMET);
    	dropableItems_TIER_04.add(Items.DIAMOND_LEGGINGS);
    	
    	//choose tier
    	float rndTier = this.rand.nextFloat();
    	if (rndTier <= 0.3F) {
    		return getRandomEntryFromList(dropableItems_TIER_00);
    	} else if (rndTier > 0.3F && rndTier <= 0.55F) {
    		return getRandomEntryFromList(dropableItems_TIER_01);
    	} else if (rndTier > 0.55F && rndTier <= 0.75F) {
    		return getRandomEntryFromList(dropableItems_TIER_02);
    	} else if (rndTier > 0.75F && rndTier <= 0.9F) {
    		return getRandomEntryFromList(dropableItems_TIER_03);
    	} else if (rndTier > 0.9F && rndTier <= 1) {
    		return getRandomEntryFromList(dropableItems_TIER_04);
    	}
		return Items.APPLE;
	}
}