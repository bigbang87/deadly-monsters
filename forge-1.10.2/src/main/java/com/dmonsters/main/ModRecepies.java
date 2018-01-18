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
		dagon();
		fencing();
		christmasTree();
		glowstoneFromSunlight();
		harpoon();
	}
	
	private static void glowstoneFromSunlight() {
		GameRegistry.addShapelessRecipe(new ItemStack(Items.GLOWSTONE_DUST, 2), ModItems.sunlightDrop);
	}
	
	private static void harpoon() {
		ItemStack item = new ItemStack(ModItems.harpoon_stone, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"XX",
				"FX",
				" S",
				'S', Items.STICK,
				'X', Blocks.COBBLESTONE,
				'F', Items.FLINT
		});
		item = new ItemStack(ModItems.harpoon_stone, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"XX",
				"FX",
				" S",
				'S', Items.STICK,
				'X', Blocks.STONE,
				'F', Items.FLINT
		});
		item = new ItemStack(ModItems.harpoon_iron, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"XX",
				"FX",
				" S",
				'S', Items.STICK,
				'X', Items.IRON_INGOT,
				'F', Items.FLINT
		});
		item = new ItemStack(ModItems.harpoon_diamond, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"XX",
				"FX",
				" S",
				'S', Items.STICK,
				'X', Items.DIAMOND,
				'F', Items.FLINT
		});
		item = new ItemStack(ModItems.harpoon_obsidian, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"XX",
				"FX",
				" S",
				'S', Items.STICK,
				'X', Blocks.OBSIDIAN,
				'F', Items.FLINT
		});
	}
	
	private static void christmasTree() {
		ItemStack item = new ItemStack(ModBlocks.christmasTree, 1);
		GameRegistry.addRecipe(item, new Object[] {
				" D ",
				"DSD",
				" P ",
				'S', new ItemStack(Blocks.SAPLING, 1, 1),
				'D', Items.DIAMOND,
				'P', Items.FLOWER_POT
		});
	}
	
	private static void fencing() {
		ItemStack item;
		//mesh fence
		item = new ItemStack(ModBlocks.meshFence, 8);
		GameRegistry.addRecipe(item, new Object[] {
				"IRI",
				"IRI",
				'I', Items.IRON_INGOT,
				'R', ModItems.rebar
		});
		//mesh fence pole
		item = new ItemStack(ModBlocks.meshFencePole, 8);
		GameRegistry.addRecipe(item, new Object[] {
				"IRI",
				"IRI",
				"IRI",
				'I', Items.IRON_INGOT,
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
				'I', Items.IRON_INGOT
		});		
		//purge pill
		item = new ItemStack(ModItems.purgePill, 1);
		GameRegistry.addRecipe(item, new Object[] {
				"W",
				"C",
				"P",
				'W', Items.WHEAT,
				'C', new ItemStack(Items.DYE ,1 ,2),
				'P', Items.PAPER,
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
	
	private static void dagon() {
		ItemStack item;
		item = new ItemStack(ModItems.dagon, 1);
		GameRegistry.addRecipe(item, new Object[] {
				" I ",
				"IRI",
				" B ",
				'I', Items.IRON_INGOT,
				'B', Items.BONE,
				'R', Items.REDSTONE
		});
		item = new ItemStack(ModItems.dagon, 1);
		GameRegistry.addRecipe(item, new Object[] {
				" I ",
				"BRI",
				" I ",
				'I', Items.IRON_INGOT,
				'B', Items.BONE,
				'R', Items.REDSTONE
		});
		item = new ItemStack(ModItems.dagon, 1);
		GameRegistry.addRecipe(item, new Object[] {
				" I ",
				"IRB",
				" I ",
				'I', Items.IRON_INGOT,
				'B', Items.BONE,
				'R', Items.REDSTONE
		});	
	}
	
	
	private static void barbedWireRecepies() {
		ItemStack barbedWire;
		barbedWire = new ItemStack(ModBlocks.barbedWire, 4);
		GameRegistry.addRecipe(barbedWire, new Object[] {
				" F ",
				" I ",
				" F ",
				'I', Blocks.IRON_BARS,
				'F', Items.FLINT
		});
		GameRegistry.addRecipe(barbedWire, new Object[] {
				"FIF",
				'I', Blocks.IRON_BARS,
				'F', Items.FLINT
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
				'S', Blocks.SAND,
				'L', Items.SLIME_BALL,
				'W', Items.WATER_BUCKET
		});
		//default alt
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 64, 7);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"SWS",
				"SLS",
				"SWS",
				'S', Blocks.SAND,
				'L', Items.SLIME_BALL,
				'W', Items.WATER_BUCKET
		});
		//white
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 0);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,15),
				'C', ModBlocks.rawConcrete
		});
		//orange
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 1);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,14),
				'C', ModBlocks.rawConcrete
		});
		//magenta
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 2);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,13),
				'C', ModBlocks.rawConcrete
		});
		//light blue
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 3);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,12),
				'C', ModBlocks.rawConcrete
		});
		//yellow
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 4);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,11),
				'C', ModBlocks.rawConcrete
		});
		//lime
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 5);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,10),
				'C', ModBlocks.rawConcrete
		});
		//pink
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 6);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,9),
				'C', ModBlocks.rawConcrete
		});
		//gray
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 7);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,8),
				'C', ModBlocks.rawConcrete
		});
		//silver
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 8);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,7),
				'C', ModBlocks.rawConcrete
		});
		//cyan
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 9);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,6),
				'C', ModBlocks.rawConcrete
		});
		//purple
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 10);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,5),
				'C', ModBlocks.rawConcrete
		});
		//blue
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 11);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,4),
				'C', ModBlocks.rawConcrete
		});
		//brown
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 12);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,3),
				'C', ModBlocks.rawConcrete
		});
		//green
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 13);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,2),
				'C', ModBlocks.rawConcrete
		});
		//red
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 14);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,1),
				'C', ModBlocks.rawConcrete
		});
		//black
		rawConcrete = new ItemStack(ModBlocks.rawConcrete, 8, 15);
		GameRegistry.addRecipe(rawConcrete, new Object[] {
				"CCC",
				"CDC",
				"CCC",
				'D', new ItemStack(Items.DYE ,1 ,0),
				'C', ModBlocks.rawConcrete
		});
	}
	
}
