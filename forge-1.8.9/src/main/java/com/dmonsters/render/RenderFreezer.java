package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelFreezer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFreezer extends RenderLiving<EntityFreezer> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/freezerIdle.png");
    private ResourceLocation mobTextureAttaking = new ResourceLocation(MainMod.MODID + ":textures/entity/freezerAngry.png");

    public static final Factory FACTORY = new Factory();

    public RenderFreezer(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelFreezer(), 0.5F);
    }
    
    protected void preRenderCallback(EntityFreezer entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityFreezer entity) {
    	//if (entity.getAttaking())
    	//	return mobTextureAttaking;
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityFreezer> {

        @Override
        public Render<? super EntityFreezer> createRenderFor(RenderManager manager) {
            return new RenderFreezer(manager);
        }

    }

}
