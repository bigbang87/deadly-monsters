package com.dmonsters.main;

import com.dmonsters.blocks.BarbedWire;
import com.dmonsters.blocks.ChristmasTree;
import com.dmonsters.blocks.Concrete;
import com.dmonsters.blocks.Dump;
import com.dmonsters.blocks.FallingConcrete;
import com.dmonsters.blocks.MeshFence;
import com.dmonsters.blocks.MeshFencePole;
import com.dmonsters.blocks.PresentBlock;
import com.dmonsters.blocks.PresentBox;
import com.dmonsters.blocks.RawConcrete;
import com.dmonsters.blocks.SoulEye;
import com.dmonsters.blocks.StrengthenedCobblestone;
import com.dmonsters.blocks.StrengthenedStone;
import com.google.common.base.Preconditions;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static Concrete concrete;
	public static RawConcrete rawConcrete;
	public static FallingConcrete fallingConcrete;
	public static StrengthenedStone strengthenedStone;
	public static StrengthenedCobblestone strengthenedCobblestone;
	public static BarbedWire barbedWire;
	public static MeshFence meshFence;
	public static MeshFencePole meshFencePole;
	public static Dump dump;
	public static SoulEye souleye;
	public static PresentBlock presentBlock;
	public static ChristmasTree christmasTree;
	public static PresentBox presentBox;
	
	@Mod.EventBusSubscriber(modid = MainMod.MODID)
	public static class RegistrationHandler {
		
		private static IForgeRegistry<Item> itemRegistry;

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();
			init();
			registry.register(strengthenedStone);
			registry.register(souleye);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
			itemRegistry = event.getRegistry();
			registerItemBlock(strengthenedStone);
			registerItemBlock(souleye);
		}
		
		private static void registerItemBlock(Block block) {
			final Item itemBlock = new ItemBlock(block);
			final ResourceLocation registryName = block.getRegistryName();
			itemRegistry.register(itemBlock.setRegistryName(registryName));
		}
		
	}

    public static void init() {
    	concrete = new Concrete();
    	rawConcrete = new RawConcrete();
    	fallingConcrete = new FallingConcrete();
    	strengthenedStone = new StrengthenedStone();
    	strengthenedCobblestone = new StrengthenedCobblestone();
    	barbedWire = new BarbedWire();
    	meshFence = new MeshFence();
    	meshFencePole = new MeshFencePole();
    	dump = new Dump();
    	souleye = new SoulEye();
    	presentBlock = new PresentBlock();
    	christmasTree = new ChristmasTree();
    	presentBox = new PresentBox();
    	
    }
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	concrete.initModel();
    	rawConcrete.initModel();
    	fallingConcrete.initModel();
    	strengthenedCobblestone.initModel();
    	barbedWire.initModel();
    	meshFence.initModel();
    	meshFencePole.initModel();
    	dump.initModel();
    	souleye.initModel();
    	presentBlock.initModel();
    	christmasTree.initModel();
    	presentBox.initModel();
    }
}
