package com.dmonsters.items;

import javax.annotation.Nullable;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModSounds;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PurgePill extends Item {
	
    public PurgePill() {
        setRegistryName("purgePill");
        setUnlocalizedName(MainMod.MODID + ".purgePill");
        GameRegistry.registerItem(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
        	if (entityplayer.getFoodStats().getFoodLevel() < 20) {
    			if (entityplayer.getHealth() > 1)
    				entityplayer.setHealth(1);
    			else
    				entityplayer.attackEntityFrom(DamageSource.generic, 999);
        	} else {
                --stack.stackSize;
                this.onFoodEaten(stack, worldIn, entityplayer);
                addDumpBlockUnderPlayer(worldIn, entityplayer);
	            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entityplayer.getPosition(), PacketClientFXUpdate.Type.DUMP));
        	}
        }
        return stack;
    }
   
    private void addDumpBlockUnderPlayer(World worldIn, EntityPlayer playerIn) {
    	BlockPos pos = playerIn.getPosition();
    	worldIn.setBlockState(pos, ModBlocks.dump.getDefaultState());
    }
    
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(Potion.hunger.id, 100));
            player.getFoodStats().setFoodLevel(2);
            player.getFoodStats().setFoodSaturationLevel(0);
        }
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (playerIn.canEat(true))
        {
            return itemStackIn;
        }
        else
        {
            return itemStackIn;
        }
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.EAT;
    }

}
