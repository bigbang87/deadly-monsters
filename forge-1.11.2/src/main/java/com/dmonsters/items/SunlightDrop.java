package com.dmonsters.items;

import com.dmonsters.entityProjectile.EntityDagon;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModConfig;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
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

public class SunlightDrop extends Item {
	
    public SunlightDrop() {
        setRegistryName("sunlightdrop");
        setUnlocalizedName(MainMod.MODID + ".sunlightdrop");
        GameRegistry.register(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
    	ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (ModConfig.hauntedCowDisableTimeChange) {
			Style red = new Style().setColor(TextFormatting.DARK_RED);
			TextComponentTranslation msg = new TextComponentTranslation("msg.dmonsters.hauntedcowTimeDisabled");
			msg.setStyle(red);
			playerIn.sendMessage(msg);
			return new ActionResult(EnumActionResult.FAIL, itemStackIn);
		}
        PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(playerIn.getPosition(), PacketClientFXUpdate.Type.SUNLIGHT_USE));
		worldIn.setWorldTime(0);
    	itemStackIn.shrink(1);
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}
