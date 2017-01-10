package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityWideman;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelWideman;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderWideman extends RenderLiving<EntityWideman> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/wideman.png");

    public static final Factory FACTORY = new Factory();

    public RenderWideman(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelWideman(), 0.5F);
    }
    
    protected void preRenderCallback(EntityWideman entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityWideman entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityWideman> {

        @Override
        public Render<? super EntityWideman> createRenderFor(RenderManager manager) {
            return new RenderWideman(manager);
        }

    }

}
