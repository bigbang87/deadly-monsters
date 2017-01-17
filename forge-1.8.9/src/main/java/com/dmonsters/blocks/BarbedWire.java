package com.dmonsters.blocks;

import java.util.Random;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BarbedWire extends Block {
	
    protected static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

	public BarbedWire() {
		super(Material.cactus);
        setUnlocalizedName(MainMod.MODID + ".barbedWire");
        setRegistryName("barbedWire");
        GameRegistry.registerBlock(this);
        //GameRegistry.registerItem(new ItemBlock(this), getRegistryName());
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(1);
        this.setResistance(1);
	}
	
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	if (entityIn instanceof EntityLivingBase)    		
    		((EntityLivingBase)entityIn).knockBack(entityIn, 1, pos.getX() - entityIn.posX, pos.getZ() - entityIn.posZ);
    }
    
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	if (!worldIn.isRemote) {
        	Block block = worldIn.getBlockState(pos).getBlock();
        	/*if (block == ModBlocks.barbedWire) {
        		this.dropBlockAsItem(worldIn, pos, this.getDefaultState(), 0);
	    		return ModBlocks.barbedWire.getDefaultState();
        	}*/
	    	BlockPos blockUnderPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
	    	IBlockState blockUnder = worldIn.getBlockState(blockUnderPos);
	    	if (blockUnder.getBlock() == ModBlocks.meshFence || blockUnder.getBlock() == ModBlocks.meshFencePole)
	    		return ModBlocks.barbedWire.getDefaultState();
    	}
    	return Blocks.air.getDefaultState();
    }
    
    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
    	BlockPos blockUnderPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
    	IBlockState blockUnder = worldIn.getBlockState(blockUnderPos);
    	if (blockUnder.getBlock() == ModBlocks.meshFence || blockUnder.getBlock() == ModBlocks.meshFencePole) {
    		//do nothing
    	} else {
    		worldIn.destroyBlock(pos, true);
    	}
    }

    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return BLOCK_AABB;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return BLOCK_COLLISION_AABB.offset(pos.getX(), pos.getY(), pos.getZ());
    }
    
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return true;
    }
    
	@Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
	@Override
	public BarbedWire setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
