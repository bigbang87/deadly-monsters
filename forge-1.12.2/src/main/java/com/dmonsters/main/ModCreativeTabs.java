package com.dmonsters.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ModCreativeTabs extends CreativeTabs
{
    public ModCreativeTabs(String label)
    {
        super(label);
        this.setBackgroundImageName("mod.png");
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem()
    {
    	return new ItemStack(ModItems.modItem);
        //return ModItems.modItem;
    }
}