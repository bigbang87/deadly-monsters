package com.dmonsters.items;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Rebar extends Item {
	
    public Rebar() {
        setRegistryName("rebar");
        setUnlocalizedName(MainMod.MODID + ".rebar");
        GameRegistry.registerItem(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.canPlayerEdit(pos, facing, stack)) {
            return false;
        } else {
            if (worldIn.getBlockState(pos).getBlock() == Blocks.stone) {
                if (!playerIn.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
                worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.strengthenedStone.getDefaultState(), 11);
            }
            if (worldIn.getBlockState(pos).getBlock() == Blocks.cobblestone) {
                if (!playerIn.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
                worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, ModBlocks.strengthenedCobblestone.getDefaultState(), 11);
            }
            stack.damageItem(1, playerIn);
            return true;
        }
    }
}
