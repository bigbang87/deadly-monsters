package com.dmonsters.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.dmonsters.main.MainMod;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModItems;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SoulEye extends Block {
	
	public static final PropertyEnum MODE = PropertyEnum.create("mode", EnumMode.class);
	
	public SoulEye() {
		super(Material.IRON);
        setUnlocalizedName(MainMod.MODID + ".souleye");
        setRegistryName("souleye");
        setCreativeTab(MainMod.MOD_CREATIVETAB);
        this.setHardness(3);
        this.setResistance(3);
        this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(MODE, EnumMode.SLEEP));
	}
    
	@Override
	public SoulEye setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
    	int meta = ((EnumMode)stateIn.getValue(MODE)).getID();
    	EnumMode mode = EnumMode.getStateFromMeta(meta);
    	if (mode != EnumMode.AWAKE)
    		return;
    	for(int x = -4; x < 4; x++) {
    		for (int z = -4; z < 4; z++) {
				for (int i = 0; i < 1; i++) {
					double motionX = rand.nextGaussian() * 0.001D;
					double motionY = Math.abs(rand.nextGaussian() * 0.02D);
					double motionZ = rand.nextGaussian() * 0.001D;
					float randX = rand.nextFloat();
					float randY = rand.nextFloat();
					float randZ = rand.nextFloat();
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
							pos.getX() + x + 0.5F + randX, 
							pos.getY() + randY, 
							pos.getZ() + z + 0.5F + randZ,
							motionX,
							motionY,
							motionZ,
							new int[0]);
				}
    		}
    	}
    }
	
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	if (!(state.getBlock() instanceof SoulEye))
    		return;
    	float lightLevel = worldIn.getLight(pos);
    	int meta = ((EnumMode)state.getValue(MODE)).getID();
    	EnumMode mode = EnumMode.getStateFromMeta(meta);
    	if (lightLevel <= 12) {
    		if (mode == EnumMode.SLEEP )
    			worldIn.setBlockState(pos, ModBlocks.souleye.getStateFromMeta(1));
    		else if (mode == EnumMode.AWAKING)
    			worldIn.setBlockState(pos, ModBlocks.souleye.getStateFromMeta(2));
    		else if (mode == EnumMode.AWAKE)
    			killLivingNearby(worldIn, pos, 4);
    	} else {
    		if (mode == EnumMode.AWAKING)
    			worldIn.setBlockState(pos, ModBlocks.souleye.getStateFromMeta(0));
    		else if (mode == EnumMode.AWAKE)
    			worldIn.setBlockState(pos, ModBlocks.souleye.getStateFromMeta(1));
    	}
    }
    
    private void killLivingNearby(World worldIn, BlockPos pos, int range) {
		if (!worldIn.isRemote) {
			BlockPos AABB_01 = new BlockPos(pos.getX() - range, pos.getY(), pos.getZ() - range);
			BlockPos AABB_02 = new BlockPos(pos.getX() + range, pos.getY() + range, pos.getZ() + range);
			AxisAlignedBB AABB = new AxisAlignedBB(AABB_01, AABB_02);
			List<EntityLiving> entities = worldIn.getEntitiesWithinAABB(EntityLiving.class, AABB);
			for (int i = 0; i < entities.size(); i++) {
				Entity entity = entities.get(i);
				spawnItem(entity);
				entity.setDead();
	            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entity.getPosition(), PacketClientFXUpdate.Type.SOULEYE));
			}
		}
    }
    
    private void spawnItem(Entity entity) {
    	Random rnd = new Random();
    	float rndFloat = rnd.nextFloat();
    	if (rndFloat > 0.5F)
    		return;
    	List<Item> itemsList = createDropTable();	
    	Item item = getItemToSpawn(itemsList);
    	entity.dropItem(item, 1);
    }
    
    private List<Item> createDropTable() {
    	List<Item> items = new ArrayList<Item>();
    	items.add(Items.EMERALD);
    	items.add(Items.GOLD_NUGGET);
    	items.add(Items.GUNPOWDER);
    	items.add(Items.REDSTONE);
    	items.add(Items.IRON_INGOT);
    	items.add(Items.QUARTZ);
    	return items;
    }
    
    private Item getItemToSpawn(List<Item> items) {
    	int itemsNumber = items.size();
    	Random rnd = new Random();
    	int randomItem = rnd.nextInt(itemsNumber);
    	return items.get(randomItem);
    }
	
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(MODE, EnumMode.getStateFromMeta(meta));
    }
    
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumMode)state.getValue(MODE)).getID();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {MODE});
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 1;
    }
	
	public enum EnumMode implements IStringSerializable {
	    SLEEP(0, "sleep"),
	    AWAKING(1, "awaking"),
	    AWAKE(2, "awake");

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
	    
	    public static EnumMode getStateFromMeta(int meta) {
	    	EnumMode mode = EnumMode.SLEEP;
	    	switch(meta) {
		    	case 0:
		    		mode = EnumMode.SLEEP;
		    		break;
		    	case 1:
		    		mode = EnumMode.AWAKING;
		    		break;
		    	case 2:
		    		mode = EnumMode.AWAKE;
		    		break;
		    	}
	    	return mode;
	    }
	}
}
