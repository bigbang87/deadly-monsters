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
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
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
    
    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    public static final AxisAlignedBB PILLAR_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);
    public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.625D, 0.625D, 1.5D, 1.0D);
    public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 0.375D, 1.5D, 0.625D);
    public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.375D);
    public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.625D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);

	public MeshFence() {
		super(Material.ROCK);
        setUnlocalizedName(MainMod.MODID + ".meshFence");
        setRegistryName("meshFence");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
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
        	if (!distReason)
        		showConsoleText("msg.dmonsters.meshFence.error", placer);
        	else
    			showConsoleText("msg.dmonsters.meshFence.tooFarFromPole", placer);
        	return Blocks.AIR.getDefaultState();
    	}
    	return Blocks.AIR.getDefaultState();
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
    
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn)
    {
        state = state.getActualState(worldIn, pos);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, PILLAR_AABB);

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return BOUNDING_BOXES[getBoundingBoxIdx(state)];
    }

    private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
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
    
    private void showConsoleText(String path, EntityLivingBase entity) {
		Style red = new Style().setColor(TextFormatting.DARK_RED);
		TextComponentTranslation test = new TextComponentTranslation(path);
		test.setStyle(red);
		entity.addChatMessage(test);
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}
