package com.dmonsters.models;

import com.dmonsters.entity.EntityMutantSteve;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPresent extends ModelBase
{
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightLeg;
    ModelRenderer leftLeg;
    ModelRenderer hat;
    ModelRenderer insideHat;
    ModelRenderer rightEye;
    ModelRenderer leftEye;
  
  public ModelPresent()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-5F, -10F, -5F, 12, 12, 12);
    head.setRotationPoint(-1F, 13F, 0F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    body = new ModelRenderer(this, 0, 56);
    body.addBox(-3F, 0F, -3F, 6, 2, 6);
    body.setRotationPoint(0F, 15F, 0F);
    body.setTextureSize(64, 32);
    body.mirror = true;
    setRotation(body, 0F, 0F, 0F);
    rightLeg = new ModelRenderer(this, 41, 41);
    rightLeg.addBox(-1F, 0F, -1F, 2, 7, 2);
    rightLeg.setRotationPoint(-2F, 17F, 0F);
    rightLeg.setTextureSize(64, 32);
    rightLeg.mirror = true;
    setRotation(rightLeg, 0F, 0F, 0F);
    leftLeg = new ModelRenderer(this, 50, 41);
    leftLeg.addBox(-1F, 0F, -1F, 2, 7, 2);
    leftLeg.setRotationPoint(2F, 17F, 0F);
    leftLeg.setTextureSize(64, 32);
    leftLeg.mirror = true;
    setRotation(leftLeg, 0F, 0F, 0F);
    hat = new ModelRenderer(this, 0, 25);
    hat.addBox(-4.5F, -15F, -16F, 13, 2, 13);
    hat.setRotationPoint(-1F, 4F, 5F);
    hat.setTextureSize(64, 32);
    hat.mirror = true;
    setRotation(hat, -0.4363323F, 0F, 0F);
    head.addChild(hat);
    insideHat = new ModelRenderer(this, 0, 41);
    insideHat.addBox(-4F, -16.5F, -13F, 10, 5, 10);
    insideHat.setRotationPoint(0F, 6F, 4F);
    insideHat.setTextureSize(64, 32);
    insideHat.mirror = true;
    setRotation(insideHat, -0.3490659F, 0F, 0F);
    head.addChild(insideHat);
    rightEye = new ModelRenderer(this, 49, 0);
    rightEye.addBox(0F, -14F, -6F, 2, 2, 2);
    rightEye.setRotationPoint(-3F, 2F, -3F);
    rightEye.setTextureSize(64, 32);
    rightEye.mirror = true;
    setRotation(rightEye, -0.3490659F, 0F, 0F);
    head.addChild(rightEye);
    leftEye = new ModelRenderer(this, 49, 5);
    leftEye.addBox(0F, -14F, -6F, 2, 2, 2);
    leftEye.setRotationPoint(3F, 2F, -3F);
    leftEye.setTextureSize(64, 32);
    leftEye.mirror = true;
    head.addChild(leftEye);
    setRotation(leftEye, -0.3490659F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
          GlStateManager.pushMatrix();
          this.hat.render(scale);
          this.insideHat.render(scale);
          this.rightEye.render(scale);
          this.leftEye.render(scale);
          GlStateManager.popMatrix();
      }
      else
      {
          this.head.render(scale);
          this.body.render(scale);
          this.leftLeg.render(scale);
          this.rightLeg.render(scale);
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
      this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
      
      float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
      float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
  }

}
