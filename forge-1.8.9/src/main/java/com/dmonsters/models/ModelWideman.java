package com.dmonsters.models;

import com.dmonsters.entity.EntityWideman;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWideman extends ModelBase
{
  //fields
    ModelRenderer middlebody;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer leftlowerleg;
    ModelRenderer rightlowerleg;
    ModelRenderer rightfeet;
    ModelRenderer leftfeet;
    ModelRenderer lowerbody;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer upperbody;
  
  public ModelWideman()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    middlebody = new ModelRenderer(this, 0, 0);
    middlebody.addBox(0F, 0F, 0F, 10, 5, 7);
    middlebody.setRotationPoint(-5F, 2F, -3F);
    middlebody.setTextureSize(64, 64);
    middlebody.mirror = true;
    setRotation(middlebody, 0F, 0F, 0F);
    leftleg = new ModelRenderer(this, 35, 0);
    leftleg.addBox(0F, 0F, -3F, 4, 10, 5);
    leftleg.setRotationPoint(5F, 4F, 1F);
    leftleg.setTextureSize(64, 64);
    leftleg.mirror = true;
    setRotation(leftleg, 0F, 0F, 0F);
    rightleg = new ModelRenderer(this, 35, 16);
    rightleg.addBox(-4F, 0F, -3F, 4, 10, 5);
    rightleg.setRotationPoint(-5F, 4F, 1F);
    rightleg.setTextureSize(64, 64);
    rightleg.mirror = true;
    setRotation(rightleg, 0F, 0F, 0F);
    leftlowerleg = new ModelRenderer(this, 23, 13);
    leftlowerleg.addBox(-5F, -4F, -1F, 2, 8, 3);
    leftlowerleg.setRotationPoint(6F, 14F, -1F);
    leftlowerleg.setTextureSize(64, 64);
    leftlowerleg.mirror = true;
    setRotation(leftlowerleg, 0F, 0F, 0F);
    leftleg.addChild(leftlowerleg);
    rightlowerleg = new ModelRenderer(this, 25, 33);
    rightlowerleg.addBox(5F, -4F, -1F, 2, 8, 3);
    rightlowerleg.setRotationPoint(-8F, 14F, -1F);
    rightlowerleg.setTextureSize(64, 64);
    rightlowerleg.mirror = true;
    setRotation(rightlowerleg, 0F, 0F, 0F);
    rightleg.addChild(rightlowerleg);
    rightfeet = new ModelRenderer(this, 39, 42);
    rightfeet.addBox(5F, -4F, -1F, 4, 2, 7);
    rightfeet.setRotationPoint(-9F, 22F, -4F);
    rightfeet.setTextureSize(64, 64);
    rightfeet.mirror = true;
    setRotation(rightfeet, 0F, 0F, 0F);
    rightleg.addChild(rightfeet);
    leftfeet = new ModelRenderer(this, 39, 32);
    leftfeet.addBox(-5F, -4F, -1F, 4, 2, 7);
    leftfeet.setRotationPoint(5F, 22F, -4F);
    leftfeet.setTextureSize(64, 64);
    leftfeet.mirror = true;
    setRotation(leftfeet, 0F, 0F, 0F);
    leftleg.addChild(leftfeet);
    lowerbody = new ModelRenderer(this, 0, 13);
    lowerbody.addBox(-3F, 0F, -3F, 6, 6, 5);
    lowerbody.setRotationPoint(0F, 6F, 1F);
    lowerbody.setTextureSize(64, 32);
    lowerbody.mirror = true;
    setRotation(lowerbody, -0.3490659F, 0F, 0F);
    neck = new ModelRenderer(this, 0, 25);
    neck.addBox(0F, 0F, 0F, 4, 6, 3);
    neck.setRotationPoint(-2F, 10F, -3F);
    neck.setTextureSize(64, 32);
    neck.mirror = true;
    setRotation(neck, -0.3490659F, 0F, 0F);
    head = new ModelRenderer(this, 0, 35);
    head.addBox(-3F, 0F, -3F, 6, 6, 6);
    head.setRotationPoint(0F, 15F, -4F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    upperbody = new ModelRenderer(this, 0, 48);
    upperbody.addBox(0F, 0F, 0F, 6, 3, 5);
    upperbody.setRotationPoint(-3F, -1F, -2F);
    upperbody.setTextureSize(64, 64);
    upperbody.mirror = true;
    setRotation(upperbody, 0F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
          float f = 2.0F;
          GlStateManager.pushMatrix();
          this.leftlowerleg.render(scale);
          this.rightlowerleg.render(scale);
          this.leftfeet.render(scale);
          this.rightfeet.render(scale);
          GlStateManager.popMatrix();
      }
      else
      {
          this.upperbody.render(scale);
          this.leftleg.render(scale);
          this.rightleg.render(scale);
          this.middlebody.render(scale);
          this.head.render(scale);
          this.lowerbody.render(scale);
          this.neck.render(scale);
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
    boolean flag = entityIn instanceof EntityWideman && ((EntityWideman)entityIn).isArmsRaised();
      this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
      
      float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
      float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
      /*
      this.rightarm.rotateAngleZ = 0.0F;
      this.leftarm.rotateAngleZ = 0.0F;
      this.rightarm.rotateAngleY = -(0.1F - f * 0.6F);
      this.leftarm.rotateAngleY = 0.1F - f * 0.6F;
      float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
      this.rightarm.rotateAngleX = f2;
      this.leftarm.rotateAngleX = f2;
      this.rightarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
      this.leftarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
      this.rightarm.rotateAngleX += 90;
      this.leftarm.rotateAngleX -= 80;
      this.rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      this.leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      */
  }

}
