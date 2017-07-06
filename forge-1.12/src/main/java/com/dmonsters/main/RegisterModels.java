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
    	registerItemModel(ModItems.modItem);
    	registerItemModel(ModItems.rebar);
    }
    
    private static void registerBlockItemModels() {
		registerBlockItemModel(ModBlocks.strengthenedStone);
		registerBlockItemModel(ModBlocks.souleye);
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
