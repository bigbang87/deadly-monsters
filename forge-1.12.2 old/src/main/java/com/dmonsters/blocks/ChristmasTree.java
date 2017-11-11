package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
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

public class ChristmasTree extends Block {
	
    protected static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.9375D, 0.9375D);
    protected static final AxisAlignedBB BLOCK_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);

	public ChristmasTree() {
		super(Material.CACTUS);
        setUnlocalizedName(MainMod.MODID + ".christmas_tree");
        setRegistryName("christmas_tree");
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setTickRandomly(true);
        this.setHardness(2);
        this.setResistance(50);
	}
	
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	Random random = new Random();
    	float rndNum = random.nextFloat();
    	if (rndNum < 0.99F) {
    		BlockPos spawnPos = selectPos(worldIn, pos);
    		if (spawnPos != null) {
    			worldIn.setBlockState(spawnPos, ModBlocks.presentBox.getDefaultState());
    		}
    	}
    }
	
	private BlockPos selectPos(World worldIn, BlockPos currPos) {
		List<BlockPos> posList = new ArrayList<BlockPos>();
		Block block;
		BlockPos pos;
		//UP
		pos = new BlockPos(currPos.getX() + 1, currPos.getY(), currPos.getZ());
		block = worldIn.getBlockState(pos).getBlock();
		if (block == Blocks.AIR)
			posList.add(pos);
		//DOWN
		pos = new BlockPos(currPos.getX() - 1, currPos.getY(), currPos.getZ());
		block = worldIn.getBlockState(pos).getBlock();
		if (block == Blocks.AIR)
			posList.add(pos);
		//RIGHT
		pos = new BlockPos(currPos.getX(), currPos.getY(), currPos.getZ() + 1);
		block = worldIn.getBlockState(pos).getBlock();
		if (block == Blocks.AIR)
			posList.add(pos);
		//LEFT
		pos = new BlockPos(currPos.getX(), currPos.getY(), currPos.getZ() - 1);
		block = worldIn.getBlockState(pos).getBlock();
		if (block == Blocks.AIR)
			posList.add(pos);

		if (posList.size() > 0) {
			Random rand = new Random();
			int rndNum = rand.nextInt(posList.size());
			return posList.get(rndNum);
		}
		return null;
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
	public ChristmasTree setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
