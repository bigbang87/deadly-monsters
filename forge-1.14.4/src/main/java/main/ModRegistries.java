package main;

import init.ModBlocks;
import init.ModEntities;
import init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistries
{
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			ModItems.mod_item = new Item(new Item.Properties().group(ModMain.MOD_ITEMGROUP)).setRegistryName(location("mod_item")),
				
			ModItems.test_block = new BlockItem(ModBlocks.test_block, new Item.Properties().group(ModMain.MOD_ITEMGROUP)).setRegistryName(ModBlocks.test_block.getRegistryName())
		);
		
		ModEntities.registerEntitySpawnEggs(event);
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll
		(
			ModBlocks.test_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 3.0f).lightValue(15).sound(SoundType.CLOTH)).setRegistryName(location("test_block"))
		);
	}
	
	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
	{
		event.getRegistry().registerAll
		(
			ModEntities.test_entity
		);
		
		ModEntities.registerEntityWorldSpawns();
	}
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(ModMain.MODID, name);
	}
}