package config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MonstersConfig
{
	public static ForgeConfigSpec.IntValue spawn_rate;
	
	public static void init(ForgeConfigSpec.Builder server)
	{
		server.comment("Test Value");
		spawn_rate = server
				.comment("Test Value Comment")
				.defineInRange("mosters.spawn_rate", 100, 1, 999);
	}
}
