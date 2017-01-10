package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityWoman;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelWoman;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderWoman extends RenderLiving<EntityWoman> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/woman.png");
    private ResourceLocation mobTextureTriggered = new ResourceLocation(MainMod.MODID + ":textures/entity/womanTriggered.png");

    public static final Factory FACTORY = new Factory();

    public RenderWoman(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWoman(), 0.5F);
    }
    
    protected void preRenderCallback(EntityWoman entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityWoman entity) {
    	if (entity.getTriggered())
    		return mobTextureTriggered;
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityWoman> {

        @Override
        public Render<? super EntityWoman> createRenderFor(RenderManager manager) {
            return new RenderWoman(manager);
        }

    }

}
