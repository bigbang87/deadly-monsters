package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dmonsters.main.IMetaBlockName;
import com.dmonsters.main.ItemBlockMeta;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RawConcrete extends Block implements IMetaBlockName {
	
    protected static final AxisAlignedBB RAW_CONCRETE_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
    protected static final AxisAlignedBB RAW_CONCRETE_COLLISION_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
    public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
    
	public RawConcrete() {
		super(Material.SAND);
        setUnlocalizedName(MainMod.MODID + ".rawConcrete");
        setRegistryName("rawConcrete");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlockMeta(this), getRegistryName());
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.GRAY));
        this.setSoundType(SoundType.SAND);
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        //ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    	Item blockItem = Item.getItemFromBlock(this);
    	List<ItemStack> subItems = new ArrayList<ItemStack>();
    	this.getSubBlocks(blockItem, null, subItems);
    	for (int i = 0; i < subItems.size(); i++) {
    		ModelLoader.setCustomModelResourceLocation(blockItem, subItems.get(i).getMetadata(),
    		new ModelResourceLocation(this.getRegistryName(), "color=" + EnumDyeColor.byMetadata(i)));
    	}
    }
    
	@Override
	public RawConcrete setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	if (!worldIn.isRemote) {
	    	BlockPos blockUnderPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
	    	IBlockState blockUnder = worldIn.getBlockState(blockUnderPos);
	    	if (blockUnder.getBlock() == Blocks.AIR || 
	    			blockUnder.getBlock() == ModBlocks.rawConcrete || 
	    			blockUnder.getMaterial() == Material.WATER) {
	    		if (placer instanceof EntityPlayer) {
	    			Style red = new Style().setColor(TextFormatting.DARK_RED);
	    			TextComponentTranslation test = new TextComponentTranslation("msg.dmonsters.rawConcrete.error");
	    			test.setStyle(red);
	    			placer.addChatMessage(test);
	    		}
	            return Blocks.AIR.getDefaultState();
	    	}
	        return this.getStateFromMeta(meta);
    	}
    	return Blocks.AIR.getDefaultState();
    }
	
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return RAW_CONCRETE_AABB;
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        return RAW_CONCRETE_COLLISION_AABB.offset(pos);
    }
    
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
    	BlockPos blockUnderPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
    	IBlockState blockUnder = worldIn.getBlockState(blockUnderPos);
    	if (blockUnder.getBlock() == Blocks.AIR || 
    			blockUnder.getBlock() == ModBlocks.rawConcrete || 
    			blockUnder.getMaterial() == Material.WATER) {
    		worldIn.setBlockState(pos, ModBlocks.fallingConcrete.getStateFromMeta(this.getMetaFromState(state)));
    	}
    }
	
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	Random random = new Random();
    	float rndNum = random.nextFloat();
    	if (rndNum < 0.5F) {
    		worldIn.setBlockState(pos, ModBlocks.concrete.getStateFromMeta(this.getMetaFromState(state)));
    	}
    }
    
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	worldIn.destroyBlock(pos, false);
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values())
        {
            list.add(new ItemStack(itemIn, 1, enumdyecolor.getMetadata()));
        }
    }
	
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR});
    }
    
    @Override
    public String getSpecialName(ItemStack stack) {
    	return EnumDyeColor.byMetadata(stack.getMetadata()).toString();
    }
}
