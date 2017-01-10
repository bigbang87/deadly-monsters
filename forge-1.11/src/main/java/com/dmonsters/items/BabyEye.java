package com.dmonsters.items;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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

public class BabyEye extends Item {
	
    public BabyEye() {
        setRegistryName("babyEye");
        setUnlocalizedName(MainMod.MODID + ".babyEye");
        GameRegistry.register(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
    	if (!playerIn.canPlayerEdit(pos, facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
        	if (!worldIn.isRemote) {
	        	Block blockIn = worldIn.getBlockState(pos).getBlock();
	        	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
	        	Item blockItem = this.getItemFromBlock(blockIn);
	        	ItemStack blockItemStack = new ItemStack(blockItem, 1);
	        	EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), blockItemStack);
	        	worldIn.spawnEntityInWorld(item);
	        	//--stack.stackSize;
	        	stack.func_190918_g(1);
        	}
            return EnumActionResult.SUCCESS;
        }
    }
}
