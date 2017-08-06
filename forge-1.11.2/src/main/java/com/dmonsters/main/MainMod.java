package com.dmonsters.main;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import com.dmonsters.entityProjectile.EntityLuckyEgg;
import com.dmonsters.proxy.CommonProxy;

@Mod(modid = MainMod.MODID, name = MainMod.MODNAME, version = MainMod.MODVERSION)
public class MainMod {
	
	public static final String MODID = "dmonsters";
	public static final String MODNAME = "Deadly Monsters";
	public static final String MODVERSION = "1.6";
	public static final ModCreativeTabs MOD_CREATIVETAB = new ModCreativeTabs("modTab");
	

    @SidedProxy(clientSide = "com.dmonsters.proxy.ClientProxy", serverSide = "com.dmonsters.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static MainMod instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ModSounds.init();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);        
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
