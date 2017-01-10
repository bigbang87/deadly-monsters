package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.blocks.SoulEye.EnumMode;
import com.dmonsters.main.IMetaBlockName;
import com.dmonsters.main.ItemBlockMeta;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dump extends Block implements IMetaBlockName {
	
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25, 0.0D, 0.25, 0.75, 0.4, 0.75);
    public static final PropertyInteger STACKS = PropertyInteger.create("stacks", 0, 15);
	
	public Dump() {
		super(Material.CAKE);
        setUnlocalizedName(MainMod.MODID + ".dump");
        setRegistryName("dump");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlockMeta(this), getRegistryName());
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(1);
        this.setResistance(1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STACKS, 0));
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
    	Item blockItem = Item.getItemFromBlock(this);
    	List<ItemStack> subItems = new ArrayList<ItemStack>();
    	this.getSubBlocks(blockItem, null, subItems);
    	for (int i = 0; i < 4; i++) {
    		ModelLoader.setCustomModelResourceLocation(blockItem, i,
    		new ModelResourceLocation(this.getRegistryName(), "stacks=" + i));
    	}
    }
    
	@Override
	public Dump setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		for (int i = 0; i < 1; i++) {
			double motionY = Math.abs(rand.nextGaussian() * 0.02D);
			float randX = rand.nextFloat();
			float randY = rand.nextFloat();
			float randZ = rand.nextFloat();
			worldIn.spawnParticle(EnumParticleTypes.SPELL_MOB,
					pos.getX() + randX, 
					pos.getY() + randY, 
					pos.getZ() + randZ,
					0,
					motionY,
					0,
					new int[0]);
		}
    }
	
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	if (!worldIn.isRemote) {
        	BlockPos blockPos;
        	blockPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
        	if (checkSaplingBlock(worldIn, blockPos, pos)) {
        		meta++;
        		if (meta == 4) {
        			return Blocks.AIR.getDefaultState();
        		}
        	}
        	blockPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
        	if (checkSaplingBlock(worldIn, blockPos, pos)) {
        		meta++;
        		if (meta == 4) {
        			return Blocks.AIR.getDefaultState();
        		}
        	}
        	blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        	if (checkSaplingBlock(worldIn, blockPos, pos)) {
        		meta++;
        		if (meta == 4) {
        			return Blocks.AIR.getDefaultState();
        		}
        	}
        	blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
        	if (checkSaplingBlock(worldIn, blockPos, pos)) {
        		meta++;
        		if (meta == 4) {
        			return Blocks.AIR.getDefaultState();
        		}
        	}
        	return ModBlocks.dump.getStateFromMeta(meta);
    	}
    	return Blocks.AIR.getDefaultState();
    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_)
    {
    	BlockPos blockPos;
    	blockPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
    	if (checkSaplingBlock(worldIn, blockPos, pos))
    		return;
    	blockPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
    	if (checkSaplingBlock(worldIn, blockPos, pos))
    		return;
    	blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
    	if (checkSaplingBlock(worldIn, blockPos, pos))
    		return;
    	blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
    	if (checkSaplingBlock(worldIn, blockPos, pos))
    		return;
    }
    
    private boolean checkSaplingBlock(World worldIn, BlockPos pos, BlockPos curretPos) {
    	IBlockState blockNear = worldIn.getBlockState(pos);
    	if (blockNear.getBlock() instanceof BlockSapling) {
    		updateDumpState(worldIn, curretPos);
    		BlockPlanks.EnumType test = (BlockPlanks.EnumType)blockNear.getActualState(worldIn, pos).getProperties().values().asList().get(1);
    		generateTree(worldIn, pos, blockNear, new Random(), test);
        	return true;
    	}
    	return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (int i = 0; i < 4; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
    
    private void updateDumpState(World worldIn, BlockPos curretPos) {
    	IBlockState block = worldIn.getBlockState(curretPos);
    	if (!(block.getBlock() instanceof Dump))
    		return;
    	int stateValue = block.getValue(STACKS).intValue();
    	stateValue++;
    	if (stateValue < 4)
    		worldIn.setBlockState(curretPos, ModBlocks.dump.getStateFromMeta(stateValue));
    	else
    		worldIn.setBlockState(curretPos, Blocks.AIR.getDefaultState());
    }
    
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand, BlockPlanks.EnumType saplingType)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        WorldGenerator worldgenerator = (WorldGenerator)(rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true));
        int i = 0;
        int j = 0;
        boolean flag = false;

        switch (saplingType)
        {
            case SPRUCE:
                label114:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTwoByTwoOfType(worldIn, pos, i, j, BlockPlanks.EnumType.SPRUCE, saplingType))
                        {
                            worldgenerator = new WorldGenMegaPineTree(false, rand.nextBoolean());
                            flag = true;
                            break label114;
                        }
                    }
                }

                if (!flag)
                {
                    i = 0;
                    j = 0;
                    worldgenerator = new WorldGenTaiga2(true);
                }

                break;
            case BIRCH:
                worldgenerator = new WorldGenBirchTree(true, false);
                break;
            case JUNGLE:
                IBlockState iblockstate = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
                IBlockState iblockstate1 = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
                worldgenerator = new WorldGenMegaJungle(true, 10, 20, iblockstate, iblockstate1);
                break;
            case ACACIA:
                worldgenerator = new WorldGenSavannaTree(true);
                break;
            case DARK_OAK:
                label390:

                for (i = 0; i >= -1; --i)
                {
                    for (j = 0; j >= -1; --j)
                    {
                        if (this.isTwoByTwoOfType(worldIn, pos, i, j, BlockPlanks.EnumType.DARK_OAK, saplingType))
                        {
                            worldgenerator = new WorldGenCanopyTree(true);
                            flag = true;
                            break label390;
                        }
                    }
                }

                if (!flag)
                {
                    return;
                }

            case OAK:
        }

        IBlockState iblockstate2 = Blocks.AIR.getDefaultState();

        if (flag)
        {
            worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
            worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
        }
        else
        {
            worldIn.setBlockState(pos, iblockstate2, 4);
        }

        if (!worldgenerator.generate(worldIn, rand, pos.add(i, 0, j)))
        {
            if (flag)
            {
                worldIn.setBlockState(pos.add(i, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
                worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
            }
            else
            {
                worldIn.setBlockState(pos, state, 4);
            }
        }
    }
    private boolean isTwoByTwoOfType(World worldIn, BlockPos pos, int p_181624_3_, int p_181624_4_, BlockPlanks.EnumType type, BlockPlanks.EnumType saplingType)
    {
        return this.isTypeAt(worldIn, pos.add(p_181624_3_, 0, p_181624_4_), type, saplingType) && this.isTypeAt(worldIn, pos.add(p_181624_3_ + 1, 0, p_181624_4_), type, saplingType) && this.isTypeAt(worldIn, pos.add(p_181624_3_, 0, p_181624_4_ + 1), type, saplingType) && this.isTypeAt(worldIn, pos.add(p_181624_3_ + 1, 0, p_181624_4_ + 1), type, saplingType);
    }

    public boolean isTypeAt(World worldIn, BlockPos pos, BlockPlanks.EnumType type, BlockPlanks.EnumType saplingType)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock() == this && saplingType == type;
    }
	
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return AABB;
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
	
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {STACKS});
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(STACKS)).intValue();
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(STACKS, Integer.valueOf(meta));
    }
    
    @Override
    public String getSpecialName(ItemStack stack) {
    	return "dump_" + Integer.toString(stack.getMetadata());
    }
    
    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
}
