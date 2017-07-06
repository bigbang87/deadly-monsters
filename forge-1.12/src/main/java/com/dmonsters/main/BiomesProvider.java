package com.dmonsters.main;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraft.init.Biomes;

public class BiomesProvider {
	public static Biome[] getBiomes() {
		List<Biome> biomesList = new ArrayList<Biome>();
		biomesList.add(Biomes.BEACH);
		biomesList.add(Biomes.BIRCH_FOREST);
		biomesList.add(Biomes.BIRCH_FOREST_HILLS);
		biomesList.add(Biomes.COLD_BEACH);
		biomesList.add(Biomes.COLD_TAIGA);
		biomesList.add(Biomes.COLD_TAIGA_HILLS);
		biomesList.add(Biomes.DESERT);
		biomesList.add(Biomes.DESERT_HILLS);
		biomesList.add(Biomes.EXTREME_HILLS);
		biomesList.add(Biomes.EXTREME_HILLS_EDGE);
		biomesList.add(Biomes.EXTREME_HILLS_WITH_TREES);
		biomesList.add(Biomes.FOREST);
		biomesList.add(Biomes.FOREST_HILLS);
		biomesList.add(Biomes.ICE_MOUNTAINS);
		biomesList.add(Biomes.ICE_PLAINS);
		biomesList.add(Biomes.JUNGLE);
		biomesList.add(Biomes.JUNGLE_EDGE);
		biomesList.add(Biomes.JUNGLE_HILLS);
		biomesList.add(Biomes.PLAINS);
		biomesList.add(Biomes.REDWOOD_TAIGA);
		biomesList.add(Biomes.REDWOOD_TAIGA_HILLS);
		biomesList.add(Biomes.ROOFED_FOREST);
		biomesList.add(Biomes.SAVANNA);
		biomesList.add(Biomes.SAVANNA_PLATEAU);
		biomesList.add(Biomes.TAIGA);
		biomesList.add(Biomes.TAIGA_HILLS);
		
		//konwersja na tablicê
		Biome[] biomes = new Biome[biomesList.size()];
		biomes = biomesList.toArray(biomes);
		return biomes;
	}
	
	public static Biome[] getSnowBiomes() {
		List<Biome> biomesList = new ArrayList<Biome>();
		biomesList.add(Biomes.COLD_BEACH);
		biomesList.add(Biomes.COLD_TAIGA);
		biomesList.add(Biomes.COLD_TAIGA_HILLS);
		biomesList.add(Biomes.ICE_MOUNTAINS);
		biomesList.add(Biomes.ICE_PLAINS);
		biomesList.add(Biomes.EXTREME_HILLS);
		biomesList.add(Biomes.EXTREME_HILLS_EDGE);
		biomesList.add(Biomes.EXTREME_HILLS_WITH_TREES);
		
		//konwersja na tablicê
		Biome[] biomes = new Biome[biomesList.size()];
		biomes = biomesList.toArray(biomes);
		return biomes;
	}
}
