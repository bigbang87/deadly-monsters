package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityClimber;
import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelClimber;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderClimber extends RenderLiving<EntityClimber> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/climber.png");

    public static final Factory FACTORY = new Factory();

    public RenderClimber(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelClimber(), 0.5F);
    }
    
    protected void preRenderCallback(EntityClimber entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityClimber entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityClimber> {

        @Override
        public Render<? super EntityClimber> createRenderFor(RenderManager manager) {
            return new RenderClimber(manager);
        }

    }

}
