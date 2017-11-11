package com.dmonsters.items;

import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntrailFlesh extends Item {
	
    public EntrailFlesh() {
        setRegistryName("entrailFlesh");
        setUnlocalizedName(MainMod.MODID + ".entrailFlesh");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	if (!attacker.world.isRemote && !(target instanceof EntityEntrail)) {
        	target.setDead();
        	double x, y, z = 0;
        	x = target.posX;
        	y = target.posY;
        	z = target.posZ;
        	Entity slime = new EntityEntrail(attacker.world);
        	slime.setPosition(x, y, z);
        	attacker.world.spawnEntity(slime);
        	//--stack.stackSize;
        	stack.shrink(1);
    	}
        return true;
    }
}
