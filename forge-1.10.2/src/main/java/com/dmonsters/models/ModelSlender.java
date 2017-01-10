package com.dmonsters.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSlender extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer Leftthing;
    ModelRenderer Rightthing;

    public ModelSlender() {
        super();
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -8.0F, -2.0F, 6, 8, 4);
        this.head.setRotationPoint(0.0F, -14.0F, -1.0F);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 20, 3);
        this.body.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 40, 16);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 3, 20, 3);
        this.rightarm.setRotationPoint(-5.0F, -11.0F, 0.0F);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 40, 16);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 3, 20, 3);
        this.leftarm.setRotationPoint(6.0F, -11.0F, 0.0F);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 3, 18, 3);
        this.rightleg.setRotationPoint(-2.0F, 6.0F, 0.0F);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.addBox(-1.0F, 0.0F, -2.0F, 3, 18, 3);
        this.leftleg.setRotationPoint(2.0F, 6.0F, 0.0F);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.Leftthing = new ModelRenderer(this, 23, 7);
        this.Leftthing.addBox(-5.0F, 0.0F, -2.0F, 1, 5, 3);
        this.Leftthing.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.Leftthing.setTextureSize(64, 64);
        this.Leftthing.mirror = true;
        this.setRotation(this.Leftthing, 0.0F, 0.0F, 0.0F);
        this.Rightthing = new ModelRenderer(this, 23, 7);
        this.Rightthing.addBox(4.0F, 0.0F, -2.0F, 1, 5, 3);
        this.Rightthing.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.Rightthing.setTextureSize(64, 64);
        this.Rightthing.mirror = true;
        this.setRotation(this.Rightthing, 0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.rightleg.render(f5);
        this.leftleg.render(f5);
        this.Leftthing.render(f5);
        this.Rightthing.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.head.rotateAngleY = par4 / 57.295776F;
        this.head.rotateAngleX = par5 / 57.295776F;
        this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
    }
}
