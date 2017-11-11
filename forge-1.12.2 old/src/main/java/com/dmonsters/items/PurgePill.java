package com.dmonsters.items;

import javax.annotation.Nullable;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModSounds;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PurgePill extends Item {
	
    public PurgePill() {
        setRegistryName("purgePill");
        setUnlocalizedName(MainMod.MODID + ".purgePill");
        this.setCreativeTab(MainMod.MOD_CREATIVETAB);
    }
    
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
        	if (entityplayer.getFoodStats().getFoodLevel() < 20) {
    			Style red = new Style().setColor(TextFormatting.DARK_RED);
    			TextComponentTranslation errorMsg = new TextComponentTranslation("msg.dmonsters.purgePill.error");
    			errorMsg.setStyle(red);
    			entityplayer.sendMessage(errorMsg);
    			if (entityplayer.getHealth() > 1)
    				entityplayer.setHealth(1);
    			else
    				entityplayer.attackEntityFrom(DamageSource.GENERIC, 999);
        	} else {
        		stack.shrink(1);
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
        	player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100));
            player.getFoodStats().setFoodLevel(2);
            player.getFoodStats().setFoodSaturationLevel(0);
        }
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
    	ItemStack itemStackIn = playerIn.getHeldItem(hand);
    	System.out.println(itemStackIn);
        if (playerIn.canEat(true))
        {
            playerIn.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }
        else
        {
            return new ActionResult(EnumActionResult.FAIL, itemStackIn);
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
