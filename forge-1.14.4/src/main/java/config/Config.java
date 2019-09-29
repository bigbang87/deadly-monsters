package config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import main.ModMain;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class Config {
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	
	public static final ForgeConfigSpec SERVER_CONFIG;
	
	static
	{
		MonstersConfig.init(SERVER_BUILDER);
		
		SERVER_CONFIG = SERVER_BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path)
	{
		ModMain.lOGGER.info("Loading config " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		ModMain.lOGGER.info("Config loaded");
		config.setConfig(file);
	}
}
