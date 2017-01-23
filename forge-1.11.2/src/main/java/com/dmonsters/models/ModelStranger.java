package com.dmonsters.models;

import com.dmonsters.entity.EntityMutantSteve;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStranger extends ModelBase
{
    ModelRenderer body;
    ModelRenderer stomach;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer leftarm;
    ModelRenderer rightarm;
    ModelRenderer lefthead;
    ModelRenderer righthead;
  
  public ModelStranger()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    body = new ModelRenderer(this, 0, 0);
    body.addBox(0F, 0F, 0F, 14, 16, 14);
    body.setRotationPoint(-7F, 1F, -7F);
    body.setTextureSize(64, 64);
    body.mirror = true;
    setRotation(body, 0F, 0F, 0F);
    stomach = new ModelRenderer(this, 0, 0);
    stomach.addBox(0F, 0F, 0F, 10, 9, 4);
    stomach.setRotationPoint(-5F, 5F, -11F);
    stomach.setTextureSize(64, 64);
    stomach.mirror = true;
    setRotation(stomach, 0F, 0F, 0F);
    leftleg = new ModelRenderer(this, 0, 0);
    leftleg.addBox(-2F, 0F, -3F, 5, 7, 5);
    leftleg.setRotationPoint(3F, 17F, 0F);
    leftleg.setTextureSize(64, 64);
    leftleg.mirror = true;
    setRotation(leftleg, 0F, 0F, 0F);
    rightleg = new ModelRenderer(this, 0, 0);
    rightleg.addBox(-3F, 0F, -3F, 5, 7, 5);
    rightleg.setRotationPoint(-3F, 17F, 0F);
    rightleg.setTextureSize(64, 64);
    rightleg.mirror = true;
    setRotation(rightleg, 0F, 0F, 0F);
    leftarm = new ModelRenderer(this, 0, 0);
    leftarm.addBox(0F, 0F, -2F, 4, 9, 4);
    leftarm.setRotationPoint(7F, 3F, 0F);
    leftarm.setTextureSize(64, 64);
    leftarm.mirror = true;
    setRotation(leftarm, 0F, 0F, 0F);
    rightarm = new ModelRenderer(this, 0, 0);
    rightarm.addBox(-4F, 0F, -2F, 4, 9, 4);
    rightarm.setRotationPoint(-7F, 3F, 0F);
    rightarm.setTextureSize(64, 64);
    rightarm.mirror = true;
    setRotation(rightarm, 0F, 0F, 0F);
    lefthead = new ModelRenderer(this, 0, 0);
    lefthead.addBox(-3F, -7F, -3F, 6, 7, 6);
    lefthead.setRotationPoint(4F, 1F, 0F);
    lefthead.setTextureSize(64, 64);
    lefthead.mirror = true;
    setRotation(lefthead, 0F, 0F, 0F);
    righthead = new ModelRenderer(this, 0, 0);
    righthead.addBox(-3F, -7F, -3F, 6, 7, 6);
    righthead.setRotationPoint(-4F, 1F, 0F);
    righthead.setTextureSize(64, 64);
    righthead.mirror = true;
    setRotation(righthead, 0F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
    	  
      }
      else
      {
          this.lefthead.render(scale);
          this.righthead.render(scale);
          this.body.render(scale);
          this.rightarm.render(scale);
          this.leftarm.render(scale);
          this.rightleg.render(scale);
          this.leftleg.render(scale);
      }
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
  {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
      boolean flag = entityIn instanceof EntityMutantSteve && ((EntityMutantSteve)entityIn).isArmsRaised();
      float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
      float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
      this.rightarm.rotateAngleZ = 0.0F;
      this.leftarm.rotateAngleZ = 0.0F;
      this.rightarm.rotateAngleY = -(0.1F - f * 0.6F);
      this.leftarm.rotateAngleY = 0.1F - f * 0.6F;
      float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
      this.rightarm.rotateAngleX = f2;
      this.leftarm.rotateAngleX = f2;
      this.rightarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
      this.leftarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
      this.rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      this.leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      
      this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      
      this.lefthead.rotateAngleY = netHeadYaw * 0.017453292F;
      this.lefthead.rotateAngleX = headPitch * 0.017453292F;
      
      this.righthead.rotateAngleY = netHeadYaw * 0.017453292F;
      this.righthead.rotateAngleX = headPitch * 0.017453292F;
  }

}
