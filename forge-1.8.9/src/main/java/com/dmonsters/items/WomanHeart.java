package com.dmonsters.items;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
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

public class WomanHeart extends Item {
	
    public WomanHeart() {
        setRegistryName("womanHeart");
        setUnlocalizedName(MainMod.MODID + ".womanHeart");
        GameRegistry.registerItem(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
        this.maxStackSize = 1;
        this.setMaxDamage(11);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.canPlayerEdit(pos, facing, stack)) {
            return false;
        } else {
        	if (!worldIn.isRemote) {
	        	Block blockIn = worldIn.getBlockState(pos).getBlock();
	        	if (playerIn.isSneaking()) {
		        	worldIn.setBlockState(pos, Blocks.water.getDefaultState());
	        	} else {
	        		worldIn.setBlockState(pos, Blocks.lava.getDefaultState());
	        	}
	            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(pos, PacketClientFXUpdate.Type.WOMAN_HEART));
	        	stack.damageItem(1, playerIn);
	            return true;
        	}
            return true;
        }
    }
}
