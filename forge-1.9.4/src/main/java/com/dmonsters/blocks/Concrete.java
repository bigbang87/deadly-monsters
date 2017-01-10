package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dmonsters.main.IMetaBlockName;
import com.dmonsters.main.ItemBlockMeta;
import com.dmonsters.main.MainMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Concrete extends Block implements IMetaBlockName {
	
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	
	public Concrete() {
		super(Material.ROCK);
        setUnlocalizedName(MainMod.MODID + ".concrete");
        setRegistryName("concrete");
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlockMeta(this), getRegistryName());
        this.setHardness(5);
        this.setResistance(50);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.GRAY));
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {        
    	Item blockItem = Item.getItemFromBlock(this);
    	List<ItemStack> subItems = new ArrayList<ItemStack>();
    	this.getSubBlocks(blockItem, null, subItems);
    	for (int i = 0; i < subItems.size(); i++) {
    		ModelLoader.setCustomModelResourceLocation(blockItem, subItems.get(i).getMetadata(),
    		new ModelResourceLocation(this.getRegistryName(), "color=" + EnumDyeColor.byMetadata(i)));
    	}
    }
    
	@Override
	public Concrete setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
    public int quantityDropped(Random random)
    {
        return 0;
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
