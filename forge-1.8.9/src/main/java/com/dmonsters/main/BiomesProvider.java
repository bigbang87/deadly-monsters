package com.dmonsters.main;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomesProvider {
	public static BiomeGenBase[] getBiomes() {
		List<BiomeGenBase> biomesList = new ArrayList<BiomeGenBase>();
		biomesList.add(BiomeGenBase.beach);
		biomesList.add(BiomeGenBase.birchForest);
		biomesList.add(BiomeGenBase.birchForestHills);
		biomesList.add(BiomeGenBase.coldBeach);
		biomesList.add(BiomeGenBase.coldTaiga);
		biomesList.add(BiomeGenBase.coldTaigaHills);
		biomesList.add(BiomeGenBase.desert);
		biomesList.add(BiomeGenBase.desertHills);
		biomesList.add(BiomeGenBase.extremeHills);
		biomesList.add(BiomeGenBase.extremeHillsEdge);
		biomesList.add(BiomeGenBase.extremeHillsPlus);
		biomesList.add(BiomeGenBase.forest);
		biomesList.add(BiomeGenBase.forestHills);
		biomesList.add(BiomeGenBase.iceMountains);
		biomesList.add(BiomeGenBase.icePlains);
		biomesList.add(BiomeGenBase.jungle);
		biomesList.add(BiomeGenBase.jungleEdge);
		biomesList.add(BiomeGenBase.jungleHills);
		biomesList.add(BiomeGenBase.plains);
		biomesList.add(BiomeGenBase.roofedForest);
		biomesList.add(BiomeGenBase.savanna);
		biomesList.add(BiomeGenBase.savannaPlateau);
		biomesList.add(BiomeGenBase.taiga);
		biomesList.add(BiomeGenBase.taigaHills);
		biomesList.add(BiomeGenBase.megaTaigaHills);
		biomesList.add(BiomeGenBase.megaTaiga);
		
		//konwersja na tablicê
		BiomeGenBase[] biomes = new BiomeGenBase[biomesList.size()];
		biomes = biomesList.toArray(biomes);
		return biomes;
	}
	
	public static BiomeGenBase[] getSnowBiomes() {
		List<BiomeGenBase> biomesList = new ArrayList<BiomeGenBase>();
		biomesList.add(BiomeGenBase.coldBeach);
		biomesList.add(BiomeGenBase.coldTaiga);
		biomesList.add(BiomeGenBase.coldTaigaHills);
		biomesList.add(BiomeGenBase.iceMountains);
		biomesList.add(BiomeGenBase.icePlains);
		biomesList.add(BiomeGenBase.extremeHills);
		biomesList.add(BiomeGenBase.extremeHillsEdge);
		biomesList.add(BiomeGenBase.extremeHillsPlus);
		
		//konwersja na tablicê
		BiomeGenBase[] biomes = new BiomeGenBase[biomesList.size()];
		biomes = biomesList.toArray(biomes);
		return biomes;
	}
}
