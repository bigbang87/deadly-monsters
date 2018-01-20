package com.dmonsters.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Harpoon extends Item {
	
	private int attackDamage = 1;
	private String harpoonType = "stone";
	
    public Harpoon(String _harpoonType, int maxDamage, int _attackDamage) {
        setRegistryName("harpoon_" + _harpoonType);
        setUnlocalizedName(MainMod.MODID + ".harpoon_" + _harpoonType);
        GameRegistry.register(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
        this.maxStackSize = 1;
        attackDamage = _attackDamage;
        harpoonType = _harpoonType;
        if (maxDamage > -1)
        	this.setMaxDamage(maxDamage);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
    	float damage = 1;
    	if (target instanceof EntityTopielec)
    		damage = attackDamage;
    	target.attackEntityFrom(DamageSource.GENERIC, damage);
    	stack.damageItem(1, attacker);
        return true;
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
    	if (!playerIn.canPlayerEdit(pos, facing, stack)) {
            return EnumActionResult.FAIL;
        } else {
        	if (!worldIn.isRemote) {
	        	if (playerIn.isInWater()) {
	        		stack.damageItem(1, playerIn);
	    			Random rnd = new Random();
	    			float rndFloat = rnd.nextFloat();
	    			if (rndFloat < 0.25F) {
	    		    	List<Item> itemsList = createDropTable();	
	    		    	Item item = getItemToSpawn(itemsList);
	    		    	playerIn.dropItem(item, 1);
	    			}
	        	}
        	}
            return EnumActionResult.SUCCESS;
        }
    }
    
    private List<Item> createDropTable() {
    	List<Item> items = new ArrayList<Item>();
    	items.add(new ItemStack(Items.FISH, 1, 0).getItem());
    	items.add(new ItemStack(Items.FISH, 1, 1).getItem());
    	items.add(new ItemStack(Items.FISH, 1, 2).getItem());
    	items.add(new ItemStack(Items.FISH, 1, 3).getItem());
    	return items;
    }
    
    private Item getItemToSpawn(List<Item> items) {
    	int itemsNumber = items.size();
    	Random rnd = new Random();
    	int randomItem = rnd.nextInt(itemsNumber);
    	return items.get(randomItem);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    	TextComponentTranslation msg = new TextComponentTranslation("item.dmonsters.addInformation.harpoon_1");
    	tooltip.add(msg.getUnformattedText());
    	msg = new TextComponentTranslation("item.dmonsters.addInformation.harpoon_2");
    	tooltip.add(msg.getUnformattedText());
    }
}