package com.dmonsters.models;

import com.dmonsters.entity.EntityMutantSteve;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelClimber extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer hips;
    ModelRenderer upperbody;
    ModelRenderer lowerleftleg;
    ModelRenderer lowerrightleg;
    ModelRenderer spike1;
    ModelRenderer spike2;
    ModelRenderer spike3;
    ModelRenderer spike4;
  
  public ModelClimber()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-3F, -8F, -6F, 6, 8, 8);
      head.setRotationPoint(0F, -1F, -3F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 13, 17);
      body.addBox(-2F, 0F, -1.5F, 4, 5, 3);
      body.setRotationPoint(0F, 5F, 1F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0.0872665F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-1F, -2F, -2F, 2, 16, 2);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 31, 16);
      leftarm.addBox(-1F, -2F, -2F, 2, 16, 2);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 35);
      rightleg.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
      rightleg.setRotationPoint(-2F, 11F, 1F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, -0.1745329F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-1.5F, 0F, -1.5F, 3, 7, 3);
      leftleg.setRotationPoint(2F, 11F, 1F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, -0.1745329F, 0F, 0F);
      hips = new ModelRenderer(this, 0, 27);
      hips.addBox(-3F, -4F, -2F, 6, 3, 4);
      hips.setRotationPoint(0F, 9F, 0F);
      hips.setTextureSize(64, 64);
      hips.mirror = true;
      setRotation(hips, 0F, 0F, 0F);
      body.addChild(hips);
      upperbody = new ModelRenderer(this, 29, 0);
      upperbody.addBox(-4F, -5F, -1.5F, 8, 8, 6);
      upperbody.setRotationPoint(0F, -2F, -1F);
      upperbody.setTextureSize(64, 64);
      upperbody.mirror = true;
      setRotation(upperbody, 0.2094395F, 0F, 0F);
      body.addChild(upperbody);
      lowerleftleg = new ModelRenderer(this, 0, 46);
      lowerleftleg.addBox(-1F, 0F, -2.5F, 2, 7, 3);
      lowerleftleg.setRotationPoint(0F, 6F, 1F);
      lowerleftleg.setTextureSize(64, 64);
      lowerleftleg.mirror = true;
      setRotation(lowerleftleg, 0.1745329F, 0F, 0F);
      leftleg.addChild(lowerleftleg);
      lowerrightleg = new ModelRenderer(this, 11, 46);
      lowerrightleg.addBox(-1F, 0F, -2.5F, 2, 7, 3);
      lowerrightleg.setRotationPoint(0F, 6F, 1F);
      lowerrightleg.setTextureSize(64, 64);
      lowerrightleg.mirror = true;
      setRotation(lowerrightleg, 0.5235988F, 0F, 0F);
      rightleg.addChild(lowerrightleg);
      spike1 = new ModelRenderer(this, 30, 36);
      spike1.addBox(0F, 0F, 0F, 1, 1, 4);
      spike1.setRotationPoint(1F, -2F, 3F);
      spike1.setTextureSize(64, 64);
      spike1.mirror = true;
      setRotation(spike1, 0.2094395F, 0F, 0F);
      spike2 = new ModelRenderer(this, 30, 42);
      spike2.addBox(0F, 0F, 0F, 1, 1, 4);
      spike2.setRotationPoint(-2F, -2F, 3F);
      spike2.setTextureSize(64, 64);
      spike2.mirror = true;
      setRotation(spike2, 0.2094395F, 0F, 0F);
      spike3 = new ModelRenderer(this, 30, 48);
      spike3.addBox(0F, 0F, 0F, 1, 1, 4);
      spike3.setRotationPoint(1F, 1F, 3F);
      spike3.setTextureSize(64, 32);
      spike3.mirror = true;
      setRotation(spike3, 0.2094395F, 0F, 0F);
      spike4 = new ModelRenderer(this, 30, 54);
      spike4.addBox(0F, 0F, 0F, 1, 1, 4);
      spike4.setRotationPoint(-2F, 2F, 3F);
      spike4.setTextureSize(64, 32);
      spike4.mirror = true;
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
          float f = 2.0F;
          GlStateManager.pushMatrix();
          this.head.render(scale);
          GlStateManager.popMatrix();
          GlStateManager.pushMatrix();
          this.body.render(scale);
          this.rightarm.render(scale);
          this.leftarm.render(scale);
          this.rightleg.render(scale);
          this.leftleg.render(scale);
          this.upperbody.render(scale);
          this.hips.render(scale);
          this.lowerleftleg.render(scale);
          this.lowerrightleg.render(scale);
          GlStateManager.popMatrix();
      }
      else
      {
          this.head.render(scale);
          this.body.render(scale);
          this.rightarm.render(scale);
          this.leftarm.render(scale);
          this.rightleg.render(scale);
          this.leftleg.render(scale);
          this.spike1.render(scale);
          this.spike2.render(scale);
          this.spike3.render(scale);
          this.spike4.render(scale);
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
    boolean flag = entityIn instanceof EntityMutantSteve;
      this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
      
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
  }

}
