package com.dmonsters.models;

import com.dmonsters.entity.EntityFreezer;
import com.dmonsters.entity.EntityMutantSteve;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFreezer extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer neck;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer upperhead;
    ModelRenderer body;
    ModelRenderer hips;
    ModelRenderer meat;
    ModelRenderer torso;
  
  public ModelFreezer()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 1F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      neck = new ModelRenderer(this, 16, 16);
      neck.addBox(-4F, 0F, -2F, 4, 1, 4);
      neck.setRotationPoint(2F, 1F, 0F);
      neck.setTextureSize(64, 64);
      neck.mirror = true;
      setRotation(neck, 0F, 0F, 0F);
      torso = new ModelRenderer(this, 33, 7);
      torso.addBox(0F, 0F, 0F, 4, 5, 3);
      torso.setRotationPoint(-2F, 6F, -2F);
      torso.setTextureSize(64, 64);
      torso.mirror = true;
      setRotation(torso, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 51, 16);
      rightarm.addBox(-3F, -2F, -2F, 3, 12, 3);
      rightarm.setRotationPoint(-4F, 5F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 38, 16);
      leftarm.addBox(-1F, -2F, -2F, 3, 12, 3);
      leftarm.setRotationPoint(5F, 5F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      upperhead = new ModelRenderer(this, 0, 22);
      upperhead.addBox(0F, 0F, 0F, 6, 6, 6);
      upperhead.setRotationPoint(-3F, -13F, -3F);
      upperhead.setTextureSize(64, 64);
      upperhead.mirror = true;
      setRotation(upperhead, 0F, 0F, 0F);
      head.addChild(upperhead);
      body = new ModelRenderer(this, 33, 0);
      body.addBox(0F, 0F, 0F, 8, 4, 3);
      body.setRotationPoint(-4F, 2F, -2F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      hips = new ModelRenderer(this, 0, 35);
      hips.addBox(-4F, 0F, -4F, 8, 8, 8);
      hips.setRotationPoint(0F, 11F, 0F);
      hips.setTextureSize(64, 64);
      hips.mirror = true;
      setRotation(hips, 0F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
    	  this.upperhead.render(scale);
      }
      else
      {
          this.head.render(scale);
          this.body.render(scale);
          this.rightarm.render(scale);
          this.leftarm.render(scale);
          this.hips.render(scale);
          this.neck.render(scale);
          this.torso.render(scale);
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
	  boolean isAttaking = entityIn instanceof EntityFreezer && ((EntityFreezer)entityIn).getAttaking();
      float bottomRotSpeed = -.005F;
	  if (isAttaking) {
		  super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		  boolean flag = entityIn instanceof EntityFreezer && ((EntityFreezer)entityIn).isArmsRaised();

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
	      bottomRotSpeed *= -8;
	  } else {
	      this.head.rotateAngleY = 0;
	      this.head.rotateAngleX = 0;
	      this.rightarm.rotateAngleZ = 0;
	      this.leftarm.rotateAngleZ = 0;
	      this.rightarm.rotateAngleY = 0;
	      this.leftarm.rotateAngleY = 0;
	      this.rightarm.rotateAngleX = 0;
	      this.leftarm.rotateAngleX = 0;
	  }
  
      this.hips.rotateAngleY += bottomRotSpeed;
      
  }

}
