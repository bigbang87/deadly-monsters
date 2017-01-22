package com.dmonsters.proxy;

import com.dmonsters.entityProjectile.EntityLuckyEgg;
import com.dmonsters.main.ModBlocks;
import com.dmonsters.main.ModEntities;
import com.dmonsters.main.ModItems;
import com.dmonsters.network.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModItems.initModels();
        ModBlocks.initModels();
        ModEntities.initModels();
        PacketHandler.registerMessages();
    }
    
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);;
        ModEntities.initHackModels();
    }
}