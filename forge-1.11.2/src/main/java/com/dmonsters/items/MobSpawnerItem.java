package com.dmonsters.items;

import com.dmonsters.entity.EntityBaby;
import com.dmonsters.entity.EntityClimber;
import com.dmonsters.entity.EntityEntrail;
import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.entity.EntityMutantSteve;
import com.dmonsters.entity.EntityPresent;
import com.dmonsters.entity.EntityStranger;
import com.dmonsters.entity.EntityWideman;
import com.dmonsters.entity.EntityWoman;
import com.dmonsters.entity.EntityZombieChicken;
import com.dmonsters.main.MainMod;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MobSpawnerItem extends Item
{
	private String mobName;
	
    public MobSpawnerItem(String name)
    {
        setRegistryName("mobSpawnerItem_" + name);
        setUnlocalizedName(MainMod.MODID + ".mobSpawnerItem_" + name);
        GameRegistry.register(this.setCreativeTab(MainMod.MOD_CREATIVETAB));
        mobName = name;
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            pos = pos.offset(facing);
            double d0 = 0.0D;

            if (facing == EnumFacing.UP && iblockstate.getBlock() instanceof BlockFence) //Forge: Fix Vanilla bug comparing state instead of block
            {
                d0 = 0.5D;
            }

            Entity entity = spawnEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                {
                    entity.setCustomNameTag(stack.getDisplayName());
                }

                if (!playerIn.capabilities.isCreativeMode)
                {
                    //--stack.stackSize;
    	        	stack.shrink(1);
                }
            }

            return EnumActionResult.SUCCESS;
        }
    }
    
    private Entity spawnEntity(World worldIn, double x, double y, double z) {
    	Entity entity = getEntity(worldIn);
        EntityLiving entityliving = (EntityLiving)entity;
        entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
        worldIn.spawnEntity(entity);
        entityliving.playLivingSound();
    	return entity;
    }
    
    private Entity getEntity(World worldIn) {
    	Entity entity = new EntityZombieChicken(worldIn);
    	switch(mobName) {
    	case "baby":
    		 entity = new EntityBaby(worldIn);
    	break;
    	case "climber":
   		 entity = new EntityClimber(worldIn);
   		break;
    	case "entrail":
   		 entity = new EntityEntrail(worldIn);
   		break;
    	case "freezer":
   		 entity = new EntityFreezer(worldIn);
   		break;
    	case "mutantSteve":
   		 entity = new EntityMutantSteve(worldIn);
   		break;
    	case "wideman":
   		 entity = new EntityWideman(worldIn);
   		break;
    	case "woman":
      		 entity = new EntityWoman(worldIn);
      	break;
    	case "zombieChicken":
      		 entity = new EntityZombieChicken(worldIn);
      	break;
    	case "present":
     		 entity = new EntityPresent(worldIn);
     	break;
    	case "stranger":
    		 entity = new EntityStranger(worldIn);
    	break;
    	}
    	return entity;
    }
}
