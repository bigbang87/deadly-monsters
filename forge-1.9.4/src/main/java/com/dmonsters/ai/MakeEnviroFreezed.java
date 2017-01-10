package com.dmonsters.ai;

import java.util.Random;

import com.dmonsters.entity.EntityFreezer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MakeEnviroFreezed extends EntityAIBase
{
    private final EntityLiving theEntity;
    private EntityFreezer freezerEntity;

    public MakeEnviroFreezed(EntityLiving entitylivingIn)
    {
        this.theEntity = entitylivingIn;
        freezerEntity = (EntityFreezer)entitylivingIn;
    }

    public boolean shouldExecute()
    {
        return true;
    }
    
    int freezTicks = 0;
    public void updateTask()
    {
    	if (freezTicks++ >= 40) {
    		FreezAroundMe(-1);
    		FreezAroundMe(0);
    		FreezAroundMe(1);
    		freezTicks = 0;
    	}
    }
    
    private void FreezAroundMe(int yOffset) {
    	IBlockState blockToFreez;
    	BlockPos blockToFreezPos;
    	World worldin = this.theEntity.worldObj;
    	double y = this.theEntity.posY;
    	float hardness;
    	float hardnessTreshold = 5;
    	boolean destroyedBlock = false;
    	float randomChance = 0;
    	Random random = new Random();
    	int freeingArea = 0;
    	if (freezerEntity.getAttaking())
    		freeingArea = 2;
    	
    	for (int dx = -freeingArea; dx <= freeingArea; ++dx) {
    		for (int dz = -freeingArea; dz <= freeingArea; ++dz) {
    	    	blockToFreezPos = new BlockPos(this.theEntity.posX + dx, y + yOffset, this.theEntity.posZ + dz);
    	    	blockToFreez = worldin.getBlockState(blockToFreezPos);
    	    	if (blockToFreez.getBlock() == Blocks.WATER) {
    	    		worldin.setBlockState(blockToFreezPos, Blocks.ICE.getDefaultState());
    	    	}
    	    	//snow layer
                for (int l = 0; l < 4; ++l)
                {
                    int i = MathHelper.floor_double(this.theEntity.posX + dx + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                    int j = MathHelper.floor_double(this.theEntity.posY);
                    int k = MathHelper.floor_double(this.theEntity.posZ + dz + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                    BlockPos blockpos = new BlockPos(i, j, k);

                    if (worldin.getBlockState(blockpos).getMaterial() == Material.AIR && Blocks.SNOW_LAYER.canPlaceBlockAt(worldin, blockpos))
                    {
                    	worldin.setBlockState(blockpos, Blocks.SNOW_LAYER.getDefaultState());
                    }
                }
    		} 
    	}
    }
}