package com.dmonsters.items;

import com.dmonsters.entityProjectile.EntityLuckyEgg;
import com.dmonsters.main.MainMod;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LuckyEgg extends Item {
	
	public LuckyEgg() {
        setRegistryName("luckyEgg");
        setUnlocalizedName(MainMod.MODID + ".luckyEgg");
        GameRegistry.registerItem(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (!playerIn.capabilities.isCreativeMode)
        {
            --itemStackIn.stackSize;
        }

        worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
        	EntityLuckyEgg entityegg = new EntityLuckyEgg(worldIn, playerIn);
        	worldIn.spawnEntityInWorld(entityegg);
        }

        return itemStackIn;
    }
}
