package com.dmonsters.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.blocks.SoulEye.EnumMode;
import com.dmonsters.main.IMetaBlockName;
import com.dmonsters.main.ItemBlockMeta;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PresentBlock extends Block implements IMetaBlockName {
	
	public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumMode.class);
	
	public PresentBlock() {
		super(Material.IRON);
        setUnlocalizedName(MainMod.MODID + ".presentblock");
        setRegistryName("presentblock");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlockMeta(this), getRegistryName());
        this.setHardness(3);
        this.setResistance(50);
        this.setTickRandomly(true);
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	Random random = new Random();
    	float rndNum = random.nextFloat();
    	worldIn.setBlockToAir(pos);
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
	
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(COLOR, EnumMode.getStateFromMeta(meta));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumMode)state.getValue(COLOR)).getID();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {COLOR});
    }
    
    @Override
    public String getSpecialName(ItemStack stack) {
    	return EnumMode.getColorFromMeta(stack.getMetadata());
    }
	
	public enum EnumMode implements IStringSerializable {
	    GREEN(0, "green"),
	    YELLOW(1, "yellow");

	    private int ID;
	    private String name;
	    
	    private EnumMode(int ID, String name) {
	        this.ID = ID;
	        this.name = name;
	    }
	    
	    @Override
	    public String getName() {
	        return name;
	    }

	    public int getID() {
	        return ID;
	    }
	    
	    public static String getColorFromMeta(int meta) {
	    	String value = null;
	    	switch(meta) {
	    	case 0:
	    		value = "green";
	    		break;
	    	case 1:
	    		value = "yellow";
	    		break;
	    	}
	    	return value;
	    }
	    
	    public static EnumMode getStateFromMeta(int meta) {
	    	EnumMode mode = EnumMode.GREEN;
	    	switch(meta) {
		    	case 0:
		    		mode = EnumMode.GREEN;
		    		break;
		    	case 1:
		    		mode = EnumMode.YELLOW;
		    		break;
		    	}
	    	return mode;
	    }
	}
}
