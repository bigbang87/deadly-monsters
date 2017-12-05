package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityTopielec;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelTopielec;

import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderTopielec extends RenderLiving<EntityTopielec> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/topielec.png");

    public static final Factory FACTORY = new Factory();

    public RenderTopielec(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelTopielec(), 0.5F);
    }
    
    protected void preRenderCallback(EntityTopielec entity, float f){
    	GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityTopielec entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityTopielec> {

        @Override
        public Render<? super EntityTopielec> createRenderFor(RenderManager manager) {
            return new RenderTopielec(manager);
        }

    }

}
