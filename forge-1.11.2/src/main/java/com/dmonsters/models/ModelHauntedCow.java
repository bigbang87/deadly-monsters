package com.dmonsters.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHauntedCow extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer udders;
    ModelRenderer bodyInside;
  
  public ModelHauntedCow()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-4F, -4F, -6F, 8, 8, 6);
    head.setRotationPoint(0F, 7F, -8F);
    head.setTextureSize(64, 64);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    body = new ModelRenderer(this, 18, 14);
    body.addBox(-6F, -10F, -7F, 12, 18, 10);
    body.setRotationPoint(0F, 5F, 2F);
    body.setTextureSize(64, 64);
    body.mirror = true;
    setRotation(body, 1.570796F, 0F, 0F);
    leg1 = new ModelRenderer(this, 0, 16);
    leg1.addBox(-3F, 0F, -2F, 4, 12, 4);
    leg1.setRotationPoint(-3F, 12F, 7F);
    leg1.setTextureSize(64, 64);
    leg1.mirror = true;
    setRotation(leg1, 0F, 0F, 0F);
    leg2 = new ModelRenderer(this, 0, 16);
    leg2.addBox(-1F, 0F, -2F, 4, 12, 4);
    leg2.setRotationPoint(3F, 12F, 7F);
    leg2.setTextureSize(64, 64);
    leg2.mirror = true;
    setRotation(leg2, 0F, 0F, 0F);
    leg3 = new ModelRenderer(this, 0, 16);
    leg3.addBox(-3F, 0F, -3F, 4, 12, 4);
    leg3.setRotationPoint(-3F, 12F, -5F);
    leg3.setTextureSize(64, 64);
    leg3.mirror = true;
    setRotation(leg3, 0F, 0F, 0F);
    leg4 = new ModelRenderer(this, 0, 16);
    leg4.addBox(-1F, 0F, -3F, 4, 12, 4);
    leg4.setRotationPoint(3F, 12F, -5F);
    leg4.setTextureSize(64, 64);
    leg4.mirror = true;
    setRotation(leg4, 0F, 0F, 0F);
    horn1 = new ModelRenderer(this, 31, 0);
    horn1.addBox(-3F, -10F, 4F, 1, 3, 1);
    horn1.setRotationPoint(0F, 3F, -7F);
    horn1.setTextureSize(64, 64);
    horn1.mirror = true;
    setRotation(horn1, 0F, 0F, 0F);
    head.addChild(horn1);
    horn2 = new ModelRenderer(this, 31, 0);
    horn2.addBox(2F, -10F, 4F, 1, 3, 1);
    horn2.setRotationPoint(0F, 3F, -7F);
    horn2.setTextureSize(64, 64);
    horn2.mirror = true;
    setRotation(horn2, 0F, 0F, 0F);
    head.addChild(horn2);
    udders = new ModelRenderer(this, 52, 0);
    udders.addBox(-2F, -3F, 0F, 4, 6, 2);
    udders.setRotationPoint(0F, 14F, 6F);
    udders.setTextureSize(64, 64);
    udders.mirror = true;
    setRotation(udders, 0F, 0F, 0F);
    bodyInside = new ModelRenderer(this, 18, 42);
    bodyInside.addBox(-4F, -4F, -0F, 8, 8, 14);
    bodyInside.setRotationPoint(0F, 8F, -6F);
    bodyInside.setTextureSize(64, 64);
    setRotation(bodyInside, 0F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
          float f = 2.0F;
          GlStateManager.pushMatrix();
    	  horn1.render(scale);
    	  horn2.render(scale);
          GlStateManager.popMatrix();
      }
      else
      {
    	  head.render(scale);
    	  body.render(scale);
    	  udders.render(scale);
    	  bodyInside.render(scale);
    	  leg1.render(scale);
    	  leg2.render(scale);
    	  leg3.render(scale);
    	  leg4.render(scale);
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
    this.head.rotateAngleZ += headPitch * 0.002F;
    this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
  }

}
