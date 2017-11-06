package com.dmonsters.blocks;

import java.util.Random;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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

public class BarbedWire extends Block {
	
    protected static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

	public BarbedWire() {
		super(Material.CACTUS);
        setUnlocalizedName(MainMod.MODID + ".barbedWire");
        setRegistryName("barbedWire");
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
    		if (placer instanceof EntityPlayer) {
    			Style red = new Style().setColor(TextFormatting.DARK_RED);
    			TextComponentTranslation test = new TextComponentTranslation("msg.dmonsters.barbedWire.error");
    			test.setStyle(red);
    			placer.sendMessage(test);
    		}
    	}
    	return Blocks.AIR.getDefaultState();
    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_)
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
        return BLOCK_COLLISION_AABB.offset(pos);
    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
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
    
	@Override
	public BarbedWire setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
