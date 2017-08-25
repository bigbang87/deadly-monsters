package com.dmonsters.proxy;

import com.dmonsters.entityProjectile.EntityLuckyEgg;
import com.dmonsters.main.EventHandler;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModConfig;
import com.dmonsters.main.ModEntities;
import com.dmonsters.main.ModItems;
import com.dmonsters.main.ModRecepies;
import com.dmonsters.network.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
    	ModConfig.initConfig(e);
    	PacketHandler.init("dmonsters");
    	ModBlocks.init();
    	ModItems.init();
    	ModEntities.init();
    	ModRecepies.AddCraftingRecepies();
    }

    public void init(FMLInitializationEvent e) {
    	MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    	config.save();
    }
}
