package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.renders.ModRenderRegistry;
import config.Config;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("deadlymonsters")
public class ModMain
{
	public static ModMain instance;
	
	public static final String MODID = "deadlymonsters";
	public static final ModItemGroup MOD_ITEMGROUP = new ModItemGroup();
	public static final Logger lOGGER = LogManager.getLogger(MODID);
	
	public ModMain()
	{
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG, "deadlymonsters.toml");
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		Config.loadConfig(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("deadlymonsters.toml").toString());
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		lOGGER.info("Setup method registered");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		ModRenderRegistry.registryEntityRenders();
		lOGGER.info("Client method registered");
	}
}
