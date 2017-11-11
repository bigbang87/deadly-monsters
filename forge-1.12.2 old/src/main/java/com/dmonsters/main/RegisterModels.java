package com.dmonsters.main;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class RegisterModels {
    @SubscribeEvent
	public static void registerModels(final ModelRegistryEvent event) {
    	registerItemModels();
    	registerBlockItemModels();
	}
    
    private static void registerItemModels() {
    	registerItemModel(ModItems.rebar);
    	registerItemModel(ModItems.luckyEgg);
    	registerItemModel(ModItems.babyEye);
    	registerItemModel(ModItems.womanHeart);
    	registerItemModel(ModItems.widemanSpine);
    	registerItemModel(ModItems.entrailFlesh);
    	registerItemModel(ModItems.purgePill);
    	registerItemModel(ModItems.dagon);
    	registerItemModel(ModItems.flyingDagon);
    	registerItemModel(ModItems.modItem);
    	registerItemModel(ModItems.mobSpawnerItem_baby);
    	registerItemModel(ModItems.mobSpawnerItem_climber);
    	registerItemModel(ModItems.mobSpawnerItem_entrail);
    	registerItemModel(ModItems.mobSpawnerItem_freezer);
    	registerItemModel(ModItems.mobSpawnerItem_mutantSteve);
    	registerItemModel(ModItems.mobSpawnerItem_wideman);
    	registerItemModel(ModItems.mobSpawnerItem_woman);
    	registerItemModel(ModItems.mobSpawnerItem_zombieChicken);
    	registerItemModel(ModItems.mobSpawnerItem_present);
    	registerItemModel(ModItems.mobSpawnerItem_stranger);
    }
    
    private static void registerBlockItemModels() {
		registerBlockItemModel(ModBlocks.strengthenedStone);
		registerBlockItemModel(ModBlocks.strengthenedCobblestone);
		registerBlockItemModel(ModBlocks.souleye);
		registerBlockItemModel(ModBlocks.christmasTree);
		registerBlockItemModel(ModBlocks.dump);
		registerBlockItemModel(ModBlocks.barbedWire);
		registerBlockItemModel(ModBlocks.meshFence);
		registerBlockItemModel(ModBlocks.meshFencePole);
		registerBlockItemModel(ModBlocks.presentBlock);
		registerBlockItemModel(ModBlocks.presentBox);
    }
    
    private static void registerItemModel(Item item) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, 0, fullModelLocation);
    }
    
    private static void registerBlockItemModel(Block block) {
		final Item item = Item.getItemFromBlock(block);
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, 0, fullModelLocation);
    }
}
