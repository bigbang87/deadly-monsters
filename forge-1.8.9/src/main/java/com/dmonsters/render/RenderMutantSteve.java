package com.dmonsters.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import com.dmonsters.entity.EntityMutantSteve;
import com.dmonsters.main.MainMod;
import com.dmonsters.models.ModelZombieSteve;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMutantSteve extends RenderLiving<EntityMutantSteve> {

    private ResourceLocation mobTexture = new ResourceLocation(MainMod.MODID + ":textures/entity/mutantSteve.png");

    public static final Factory FACTORY = new Factory();

    public RenderMutantSteve(RenderManager rendermanagerIn) {
        // We use the vanilla zombie model here and we simply
        // retexture it. Of course you can make your own model
        super(rendermanagerIn, new ModelZombieSteve(), 0.5F);
    }
    
    protected void preRenderCallback(EntityMutantSteve entity, float f){
    	GL11.glScalef(1.2F, 1.2F, 1.2F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityMutantSteve entity) {
        return mobTexture;
    }

    public static class Factory implements IRenderFactory<EntityMutantSteve> {

        @Override
        public Render<? super EntityMutantSteve> createRenderFor(RenderManager manager) {
            return new RenderMutantSteve(manager);
        }

    }

}
