package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityBaby;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelBaby;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBaby extends RenderLiving<EntityBaby> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/baby.png");

    public static final Factory FACTORY = new Factory();

    public RenderBaby(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBaby(), 0.5F);
    }
    
    protected void preRenderCallback(EntityBaby entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityBaby entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityBaby> {

        @Override
        public Render<? super EntityBaby> createRenderFor(RenderManager manager) {
            return new RenderBaby(manager);
        }

    }

}
