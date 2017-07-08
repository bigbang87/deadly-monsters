package com.dmonsters.items;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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

public class Rebar extends Item {
	
    public Rebar() {
        setRegistryName("rebar");
        setUnlocalizedName(MainMod.MODID + ".rebar");
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
    	if (!playerIn.canPlayerEdit(pos, facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
            if (worldIn.getBlockState(pos).getBlock() == Blocks.STONE) {
                if (!playerIn.capabilities.isCreativeMode) {
            		stack.shrink(1);
                }
                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.strengthenedStone.getDefaultState(), 11);
            }
            if (worldIn.getBlockState(pos).getBlock() == Blocks.COBBLESTONE) {
                if (!playerIn.capabilities.isCreativeMode) {
            		stack.shrink(1);
                }
                worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.strengthenedCobblestone.getDefaultState(), 11);
            }
            stack.damageItem(1, playerIn);
            return EnumActionResult.SUCCESS;
        }
    }
}
