package init;

import entities.TestEntity;
import main.ModMain;
import main.ModRegistries;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;

public class ModEntities
{
	public static EntityType<?> test_entity = EntityType.Builder.create(TestEntity::new, EntityClassification.CREATURE).build(ModMain.MODID + ":tutorial_entity").setRegistryName(ModRegistries.location("test_entity"));
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			ModItems.test_entitiy_egg = registerSpawnEgg(test_entity, 0xfcba03, 0xff6600, "test_entity_egg")
		);
	}
	
	public static void registerEntityWorldSpawns()
	{
		registerEntityWorldSpawn(test_entity, Biomes.PLAINS, Biomes.BEACH);
	}
	
	public static Item registerSpawnEgg(EntityType<?> type, int color1, int color2, String name)
	{
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ModMain.MOD_ITEMGROUP));
		item.setRegistryName(ModRegistries.location(name));
		return item;
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome...biomes)
	{
		for (Biome biome : biomes)
		{
			if (biome != null)
			{
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 10, 1, 10));
			}
		}
	}
}
