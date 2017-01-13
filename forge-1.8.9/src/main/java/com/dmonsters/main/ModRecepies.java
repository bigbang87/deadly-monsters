package com.dmonsters.main;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecepies {
	
	public static void AddCraftingRecepies() {
		rawConcreteRecepies();
		barbedWireRecepies();
		craftableItems();
		fencing();
	}
	
	private static void fencing() {
		ItemStack item;
		//mesh fence
		item = new ItemStack(ModBlocks.meshFence, 8);
		GameRegistry.addRecipe(item, new Object[] {
				"IRI",
				"IRI",
				'I', Items.iron_ingot,
				'R', ModItems.rebar
		});
		//mesh fence pole
		item = new ItemStack(ModBlocks.meshFencePole, 8);
		GameRegistry.addRecipe(item, new Object[] {
				"IRI",
				"IRI",
				"IRI",
				'I', Items.iron_ingot,
				'R', ModItems.rebar
		});	
	}
	
	private static void craftableItems() {
		ItemStack item;
		//rebar
		item = new ItemStack(ModItems.rebar, 16);
		GameRegistry.addRecipe(item, new Object[] {
				"I",
				"I",
				'I', Items.iron_ingot
		});		
		//purge pill
		item = new ItemStack(ModItems.purgePill, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"W",
				"C",
				"P",
				'W', Items.wheat,
				'C', new ItemStack(Items.dye ,1 ,2),
				'P', Items.paper,
		});
		//souleye
		item = new ItemStack(ModBlocks.souleye, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"FEF",
				"FHF",
				"FSF",
				'E', ModItems.babyEye,
				'F', ModItems.entrailFlesh,
				'S', ModItems.widemanSpine,
				'H', ModItems.womanHeart
		});	
	}
	
	private static void barbedWireRecepies() {
		ItemStack barbedWire;
		barbedWire = new ItemStack(ModBlocks.barbedWire, 4);
		GameRegistry.addRecipe(barbedWire, new Object[] {
				" F ",
				" I ",
				" F ",
				'I', Blocks.iron_bars,
				'F', Items.flint
		});
		GameRegistry.addRecipe(barbedWire, new Object[] {
				"FIF",
				'I', Blocks.iron_bars,
				'F', Items.flint
		});
	}
	
	private static void rawConcreteRecepies() {
		ItemStack rawConcrete;
		//default
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 64, 7);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"SSS",
				"WLW",
				"SSS",
				'S', Blocks.sand,
				'L', Items.slime_ball,
				'W', Items.water_bucket
		});
		//default alt
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 64, 7);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"SWS",
				"SLS",
				"SWS",
				'S', Blocks.sand,
				'L', Items.slime_ball,
				'W', Items.water_bucket
		});
		//white
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 0);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,15),
				'C', ModBlocks.rawConcrete
		});
		//orange
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 1);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,14),
				'C', ModBlocks.rawConcrete
		});
		//magenta
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 2);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,13),
				'C', ModBlocks.rawConcrete
		});
		//light blue
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 3);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,12),
				'C', ModBlocks.rawConcrete
		});
		//yellow
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 4);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,11),
				'C', ModBlocks.rawConcrete
		});
		//lime
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 5);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,10),
				'C', ModBlocks.rawConcrete
		});
		//pink
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 6);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,9),
				'C', ModBlocks.rawConcrete
		});
		//gray
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 7);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,8),
				'C', ModBlocks.rawConcrete
		});
		//silver
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 8);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,7),
				'C', ModBlocks.rawConcrete
		});
		//cyan
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 9);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,6),
				'C', ModBlocks.rawConcrete
		});
		//purple
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 10);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,5),
				'C', ModBlocks.rawConcrete
		});
		//blue
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 11);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,4),
				'C', ModBlocks.rawConcrete
		});
		//brown
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 12);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,3),
				'C', ModBlocks.rawConcrete
		});
		//green
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 13);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,2),
				'C', ModBlocks.rawConcrete
		});
		//red
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 14);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,1),
				'C', ModBlocks.rawConcrete
		});
		//black
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 15);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.dye ,1 ,0),
				'C', ModBlocks.rawConcrete
		});
	}
	
}
