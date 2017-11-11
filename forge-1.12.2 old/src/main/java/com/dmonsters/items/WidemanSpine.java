package com.dmonsters.items;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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

public class WidemanSpine extends Item {
	
    public WidemanSpine() {
        setRegistryName("widemanSpine");
        setUnlocalizedName(MainMod.MODID + ".widemanSpine");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.maxStackSize = 1;
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	target.knockBack(target, 4, attacker.posX - target.posX, attacker.posZ - target.posZ);
        return true;
    }
}