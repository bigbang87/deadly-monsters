package client.renders;

import client.models.TestEntityModel;
import entities.TestEntity;
import main.ModRegistries;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class TestEntityRender extends LivingRenderer<TestEntity, TestEntityModel>
{
	public TestEntityRender(EntityRendererManager manager)
	{
		super(manager, new TestEntityModel(), 1.0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(TestEntity arg0) {
		return ModRegistries.location("textures/entity/test_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<TestEntity>
	{
		@Override
		public EntityRenderer<? super TestEntity> createRenderFor(EntityRendererManager manager) {
			return new TestEntityRender(manager);
		}
	}
}
