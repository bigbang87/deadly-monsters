package com.dmonsters.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StrengthenedCobblestone extends Block {
	public StrengthenedCobblestone() {
		super(Material.iron);
        setUnlocalizedName(MainMod.MODID + ".strengthenedCobblestone");
        setRegistryName("strengthenedCobblestone");
        GameRegistry.registerBlock(this);
        GameRegistry.registerItem(new ItemBlock(this), getRegistryName());
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(10);
        this.setResistance(25);
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
	@Override
	public StrengthenedCobblestone setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
    	if (playerIn.isSneaking()) {
        	if (!worldIn.isRemote) {
	        	worldIn.setBlockState(pos, Blocks.cobblestone.getDefaultState());
	        	/*
	        	ItemStack newItem = new ItemStack(ModItems.rebar, 1);
	        	EntityItem item = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, newItem);
	        	worldIn.spawnEntityInWorld(item);
	        	*/
        	}
            return true;
        }
        else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
        }
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	super.breakBlock(worldIn, pos, state);
    	ItemStack newItem = new ItemStack(ModItems.rebar, 1);
    	EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), newItem);
    	worldIn.spawnEntityInWorld(item);
    	/*
    	newItem = new ItemStack(Blocks.COBBLESTONE, 1);
    	item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), newItem);
    	worldIn.spawnEntityInWorld(item);
    	*/
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
}
