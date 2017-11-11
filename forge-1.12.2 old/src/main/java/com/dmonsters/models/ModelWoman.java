package com.dmonsters.models;

import com.dmonsters.entity.EntityWoman;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWoman extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer chest;
    ModelRenderer belly;
    ModelRenderer hips;
    ModelRenderer upperleftleg;
    ModelRenderer upperrightleg;
    ModelRenderer upperrightarm;
    ModelRenderer upperleftarm;
    ModelRenderer lowerrightleg;
    ModelRenderer lowerlefttleg;
    ModelRenderer lowerleftarm;
    ModelRenderer lowerrightarm;
  
  public ModelWoman()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-3.466667F, 0F, -7F, 7, 7, 7);
    head.setRotationPoint(0F, 14F, -7F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    chest = new ModelRenderer(this, 0, 15);
    chest.addBox(0F, 0F, 0F, 8, 4, 7);
    chest.setRotationPoint(-4F, 13F, -7F);
    chest.setTextureSize(64, 32);
    chest.mirror = true;
    setRotation(chest, 0.1115358F, 0F, 0F);
    belly = new ModelRenderer(this, 0, 27);
    belly.addBox(0F, 0F, 0F, 6, 3, 4);
    belly.setRotationPoint(-3F, 13F, 0F);
    belly.setTextureSize(64, 32);
    belly.mirror = true;
    setRotation(belly, -0.1115358F, 0F, 0F);
    hips = new ModelRenderer(this, 0, 35);
    hips.addBox(0F, 0F, 0F, 8, 4, 5);
    hips.setRotationPoint(-4F, 13F, 4F);
    hips.setTextureSize(64, 32);
    hips.mirror = true;
    setRotation(hips, -0.2230717F, 0F, 0F);
    upperleftleg = new ModelRenderer(this, 39, 14);
    upperleftleg.addBox(-2F, -2F, 0F, 4, 4, 8);
    upperleftleg.setRotationPoint(3F, 16F, 8F);
    upperleftleg.setTextureSize(64, 32);
    upperleftleg.mirror = true;
    setRotation(upperleftleg, 0.3490659F, 0.7853982F, 0F);
    upperrightleg = new ModelRenderer(this, 39, 1);
    upperrightleg.addBox(-2F, -2F, 0F, 4, 4, 8);
    upperrightleg.setRotationPoint(-3F, 16F, 8F);
    upperrightleg.setTextureSize(64, 32);
    upperrightleg.mirror = true;
    setRotation(upperrightleg, 0.3490659F, -0.7853982F, 0F);
    upperrightarm = new ModelRenderer(this, 0, 45);
    upperrightarm.addBox(-3F, -2F, -7F, 3, 3, 7);
    upperrightarm.setRotationPoint(-3F, 15F, -6F);
    upperrightarm.setTextureSize(64, 32);
    upperrightarm.mirror = true;
    setRotation(upperrightarm, -0.3490659F, 0.7853982F, 0F);
    upperleftarm = new ModelRenderer(this, 21, 45);
    upperleftarm.addBox(0F, -2F, -7F, 3, 3, 7);
    upperleftarm.setRotationPoint(3F, 15F, -6F);
    upperleftarm.setTextureSize(64, 32);
    upperleftarm.mirror = true;
    setRotation(upperleftarm, -0.3490659F, -0.7853982F, 0F);
    lowerrightleg = new ModelRenderer(this, 51, 27);
    lowerrightleg.addBox(0F, 0F, 0F, 3, 8, 3);
    lowerrightleg.setRotationPoint(-1F, 0F, 7F);
    lowerrightleg.setTextureSize(64, 32);
    lowerrightleg.mirror = true;
    setRotation(lowerrightleg, 0F, 0F, 0F);
    upperrightleg.addChild(lowerrightleg);
    lowerlefttleg = new ModelRenderer(this, 38, 27);
    lowerlefttleg.addBox(0F, 0F, 0F, 3, 8, 3);
    lowerlefttleg.setRotationPoint(-2F, 0F, 7F);
    lowerlefttleg.setTextureSize(64, 32);
    lowerlefttleg.mirror = true;
    setRotation(lowerlefttleg, 0F, 0F, 0F);
    upperleftleg.addChild(lowerlefttleg);
    lowerleftarm = new ModelRenderer(this, 42, 45);
    lowerleftarm.addBox(0F, 0F, 0F, 2, 8, 2);
    lowerleftarm.setRotationPoint(0F, 0F, -8F);
    lowerleftarm.setTextureSize(64, 64);
    lowerleftarm.mirror = true;
    setRotation(lowerleftarm, 0F, 0F, 0F);
    upperleftarm.addChild(lowerleftarm);
    lowerrightarm = new ModelRenderer(this, 51, 45);
    lowerrightarm.addBox(0F, 0F, 0F, 2, 8, 2);
    lowerrightarm.setRotationPoint(-2F, 0F, -8F);
    lowerrightarm.setTextureSize(64, 64);
    lowerrightarm.mirror = true;
    setRotation(lowerrightarm, 0F, 0F, 0F);
    upperrightarm.addChild(lowerrightarm);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
          float f = 2.0F;
          GlStateManager.pushMatrix();
    	  lowerrightleg.render(scale);
          lowerleftarm.render(scale);
          lowerrightarm.render(scale);
          GlStateManager.popMatrix();
      }
      else
      {
    	  head.render(scale);
    	  chest.render(scale);
    	  belly.render(scale);
    	  hips.render(scale);
    	  upperrightarm.render(scale);
    	  upperleftarm.render(scale);
    	  upperleftleg.render(scale);
    	  upperrightleg.render(scale);
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
    this.head.rotateAngleX = headPitch * 0.017453292F;
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    this.upperleftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    this.upperrightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    this.upperleftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    this.upperrightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
  }

}
