package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.entity.EntityZombieChicken;
import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PresentBox extends Block {
	
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.13, 0.0D, 0.13, 0.87, 0.75, 0.87);

	public PresentBox() {
		super(Material.cactus);
        setUnlocalizedName(MainMod.MODID + ".present_box");
        setRegistryName("present_box");
        GameRegistry.registerBlock(this);
        //GameRegistry.registerItem(new ItemBlock(this), getRegistryName());
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(1);
        this.setResistance(50);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
    	if (!worldIn.isRemote) {
    		Random rand = new Random();
    		float rndNum = rand.nextFloat();
    		if (rndNum < 0.7F) {
    			worldIn.setBlockToAir(pos);
    			worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1, true);
    		} else if (rndNum > 0.7F && rndNum < 0.8F) {
            	Item spawnedItem = spawnRandomItem();
            	ItemStack newItem = new ItemStack(spawnedItem, 1);
            	EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), newItem);
            	worldIn.spawnEntityInWorld(item);
            	worldIn.setBlockToAir(pos);
    		} else if (rndNum > 0.8F && rndNum < 0.95F)  {
	        	EntityLiving entity = spawnMonster(worldIn);;
	        	entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), playerIn.rotationYaw, 0.0F);
	        	worldIn.spawnEntityInWorld(entity);
            	worldIn.setBlockToAir(pos);
    		} else {
    			worldIn.setBlockState(pos, ModBlocks.dump.getStateFromMeta(rand.nextInt(5)));
    		}
    	}
        return true;
    }
	
	private Item spawnRandomItem() {
		List<Item> itemsList = new ArrayList<Item>();
		itemsList.add(Items.apple);
		itemsList.add(Items.gold_nugget);
		itemsList.add(Items.leather_helmet);
		itemsList.add(Items.fish);
		itemsList.add(Items.redstone);
		itemsList.add(ModItems.mobSpawnerItem_present);
		itemsList.add(Items.gunpowder);
		itemsList.add(Items.redstone);
		itemsList.add(Items.iron_ingot);
		itemsList.add(Items.iron_sword);
		Random rand = new Random();
		int rndNum = rand.nextInt(itemsList.size());
		return itemsList.get(rndNum);
	}
	
	private EntityLiving spawnMonster(World worldIn) {
		List<EntityLiving> monstersList = new ArrayList<EntityLiving>();
		monstersList.add(new EntityCreeper(worldIn));
		monstersList.add(new EntityZombie(worldIn));
		monstersList.add(new EntitySkeleton(worldIn));
		monstersList.add(new EntitySilverfish(worldIn));
		monstersList.add(new EntityBlaze(worldIn));
		monstersList.add(new EntityMagmaCube(worldIn));
		monstersList.add(new EntityPigZombie(worldIn));
		Random rand = new Random();
		int rndNum = rand.nextInt(monstersList.size());
		return monstersList.get(rndNum);
	}
	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return AABB;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return true;
    }
    
	@Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
	@Override
	public PresentBox setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
