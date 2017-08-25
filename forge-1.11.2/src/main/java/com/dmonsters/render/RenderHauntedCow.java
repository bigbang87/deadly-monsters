package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityHauntedCow;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelHauntedCow;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHauntedCow extends RenderLiving<EntityHauntedCow> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/hauntedcow.png");

    public static final Factory FACTORY = new Factory();

    public RenderHauntedCow(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelHauntedCow(), 0.3F);
    }
    
    protected void preRenderCallback(EntityHauntedCow entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityHauntedCow entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityHauntedCow> {

        @Override
        public Render<? super EntityHauntedCow> createRenderFor(RenderManager manager) {
            return new RenderHauntedCow(manager);
        }
    }
}
