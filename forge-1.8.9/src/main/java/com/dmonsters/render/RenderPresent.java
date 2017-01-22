package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityPresent;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelPresent;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPresent extends RenderLiving<EntityPresent> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/present.png");

    public static final Factory FACTORY = new Factory();

    public RenderPresent(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelPresent(), 0.5F);
    }
    
    protected void preRenderCallback(EntityPresent entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityPresent entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityPresent> {

        @Override
        public Render<? super EntityPresent> createRenderFor(RenderManager manager) {
            return new RenderPresent(manager);
        }
    }
}
