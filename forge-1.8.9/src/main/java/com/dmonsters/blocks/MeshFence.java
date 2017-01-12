package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MeshFence extends Block {
	
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

	public MeshFence() {
		super(Material.rock);
        setUnlocalizedName(MainMod.MODID + ".meshFence");
        setRegistryName("meshFence");
        GameRegistry.registerBlock(this);
        GameRegistry.registerItem(new ItemBlock(this), getRegistryName());
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(5);
        this.setResistance(5);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	if (!worldIn.isRemote) {
        	BlockPos neighborPos;
        	Block neighborBlock;
        	Boolean distReason = false;
        	//north
        	neighborPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
        	if (worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
        			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole) {
        		if (checkIfCanBuildInDirection(0, 0, -1, 8, pos, worldIn)) {
        			return this.getDefaultState();
        		} else {
        			distReason = true;
        		}
        	}
        	//east
        	neighborPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
        	if (worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
        			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole) {
        		if (checkIfCanBuildInDirection(1, 0, 0, 8, pos, worldIn)) {
        			return this.getDefaultState();
        		} else {
        			distReason = true;
        		}
        	}
        	//west
        	neighborPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
        	if (worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
        			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole) {
        		if (checkIfCanBuildInDirection(-1, 0, 0, 8, pos, worldIn)) {
        			return this.getDefaultState();
        		} else {
        			distReason = true;
        		}
        	}
        	//south
        	neighborPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        	if (worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
        			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole) {
        		if (checkIfCanBuildInDirection(0, 0, 1, 8, pos, worldIn)) {
        			return this.getDefaultState();
        		} else {
        			distReason = true;
        		}
        	}
        	return Blocks.air.getDefaultState();
    	}
    	return Blocks.air.getDefaultState();
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
    	BlockPos neighborPos;
    	Block neigbourBlock;
    	//north
		if (checkIfCanBuildInDirection(0, 0, -1, 8, pos, worldIn)) {
			return;
		}
		//east
		if (checkIfCanBuildInDirection(1, 0, 0, 8, pos, worldIn)) {
			return;
		}
		//west
		if (checkIfCanBuildInDirection(-1, 0, 0, 8, pos, worldIn)) {
			return;
		}
		//south
		if (checkIfCanBuildInDirection(0, 0, 1, 8, pos, worldIn)) {
			return;
		}
		worldIn.destroyBlock(pos, true);
    }
    
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity)
    {
        boolean flag = this.checkIfCanBuildInDirection(0, 0, -1, 8, pos, worldIn);
        boolean flag1 = this.checkIfCanBuildInDirection(0, 0, 1, 8, pos, worldIn);
        boolean flag2 = this.checkIfCanBuildInDirection(-1, 0, 0, 8, pos, worldIn);
        boolean flag3 = this.checkIfCanBuildInDirection(1, 0, 0, 8, pos, worldIn);
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag || flag1)
        {
            this.setBlockBounds(f, 0.0F, f2, f1, 1.5F, f3);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }

        f2 = 0.375F;
        f3 = 0.625F;

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        if (flag2 || flag3 || !flag && !flag1)
        {
            this.setBlockBounds(f, 0.0F, f2, f1, 1.5F, f3);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        boolean flag = this.checkIfCanBuildInDirection(0, 0, -1, 8, pos, worldIn);
        boolean flag1 = this.checkIfCanBuildInDirection(0, 0, 1, 8, pos, worldIn);
        boolean flag2 = this.checkIfCanBuildInDirection(-1, 0, 0, 8, pos, worldIn);
        boolean flag3 = this.checkIfCanBuildInDirection(1, 0, 0, 8, pos, worldIn);
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;

        if (flag)
        {
            f2 = 0.0F;
        }

        if (flag1)
        {
            f3 = 1.0F;
        }

        if (flag2)
        {
            f = 0.0F;
        }

        if (flag3)
        {
            f1 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }
    
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
    	BlockPos neighborPos;
    	Block neighborBlock;
    	boolean north, east, west, south = false;
    	//north
    	neighborPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
    	north = worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
    			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole ? true : false;
    	//east
    	neighborPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
    	east = worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
    			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole ? true : false;
    	//west
    	neighborPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
    	west = worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
    			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole ? true : false;
    	//south
    	neighborPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
    	south = worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFence ||
    			worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole ? true : false;
    	
    	return state.withProperty(NORTH, north).withProperty(EAST, east).withProperty(WEST, west).withProperty(SOUTH, south);
    }
    
    private boolean checkIfCanBuildInDirection(int x, int y, int z, int dist, BlockPos pos, IBlockAccess worldIn) {
    	BlockPos neighborPos = pos;
    	for (int i = 0; i < dist; i++) {
    		neighborPos = new BlockPos(neighborPos.getX() + x, neighborPos.getY() + y, neighborPos.getZ() + z);
        	if (worldIn.getBlockState(neighborPos).getBlock() == ModBlocks.meshFencePole) {
        		return true;
        	} else if (worldIn.getBlockState(neighborPos).getBlock() != ModBlocks.meshFence) {
        		return false;
        	}
    	}
    	return false;
    }
    
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}
