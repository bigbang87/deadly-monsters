package client.renders;

import entities.TestEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ModRenderRegistry
{
	public static void registryEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(TestEntity.class, new TestEntityRender.RenderFactory());
	}
}
