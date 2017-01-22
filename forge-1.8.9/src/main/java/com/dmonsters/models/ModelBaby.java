package com.dmonsters.models;

import com.dmonsters.entity.EntityBaby;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBaby extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer bodybig;
    ModelRenderer bodymiddle;
    ModelRenderer bodyleft;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer leg;
    ModelRenderer righteye;
    ModelRenderer lefteye;
    ModelRenderer mounth;
  
  public ModelBaby()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-4F, -8F, -4F, 8, 8, 11);
    head.setRotationPoint(0F, 12F, 0F);
    head.setTextureSize(64, 64);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    body = new ModelRenderer(this, 0, 20);
    body.addBox(0F, 0F, 0F, 3, 6, 3);
    body.setRotationPoint(-1.5F, 12F, -1F);
    body.setTextureSize(64, 64);
    body.mirror = true;
    setRotation(body, 0F, 0F, 0F);
    bodybig = new ModelRenderer(this, 0, 30);
    bodybig.addBox(0F, 0F, 0F, 8, 4, 11);
    bodybig.setRotationPoint(-6F, 20F, -3F);
    bodybig.setTextureSize(64, 64);
    bodybig.mirror = true;
    setRotation(bodybig, 0F, 0F, 0F);
    bodymiddle = new ModelRenderer(this, 13, 20);
    bodymiddle.addBox(0F, 0F, 0F, 6, 2, 5);
    bodymiddle.setRotationPoint(-4F, 18F, -2F);
    bodymiddle.setTextureSize(64, 64);
    bodymiddle.mirror = true;
    setRotation(bodymiddle, 0F, 0F, 0F);
    bodyleft = new ModelRenderer(this, 0, 46);
    bodyleft.addBox(0F, 0F, 0F, 4, 5, 4);
    bodyleft.setRotationPoint(2F, 19F, -1F);
    bodyleft.setTextureSize(64, 64);
    bodyleft.mirror = true;
    setRotation(bodyleft, 0F, 0F, 0F);
    rightarm = new ModelRenderer(this, 17, 46);
    rightarm.addBox(-2F, -9F, -1F, 2, 9, 2);
    rightarm.setRotationPoint(-6F, 21F, 1.533333F);
    rightarm.setTextureSize(64, 64);
    rightarm.mirror = true;
    setRotation(rightarm, 0F, 0F, 0F);
    leftarm = new ModelRenderer(this, 0, 56);
    leftarm.addBox(0F, -6F, -1F, 2, 6, 2);
    leftarm.setRotationPoint(6F, 21F, 2F);
    leftarm.setTextureSize(64, 64);
    leftarm.mirror = true;
    setRotation(leftarm, 0F, 0F, 0F);
    leg = new ModelRenderer(this, 26, 46);
    leg.addBox(-1F, -9F, 0F, 3, 10, 3);
    leg.setRotationPoint(-1F, 22F, 8F);
    leg.setTextureSize(64, 64);
    leg.mirror = true;
    setRotation(leg, 0F, 0F, 0F);
    righteye = new ModelRenderer(this, 39, 17);
    righteye.addBox(0F, 0F, 0F, 2, 2, 1);
    righteye.setRotationPoint(-3F, -4F, -5F);
    righteye.setTextureSize(64, 64);
    righteye.mirror = true;
    setRotation(righteye, 0F, 0F, 0F);
    head.addChild(righteye);
    lefteye = new ModelRenderer(this, 39, 12);
    lefteye.addBox(0F, 0F, 0F, 2, 2, 1);
    lefteye.setRotationPoint(1F, -4F, -5F);
    lefteye.setTextureSize(64, 64);
    lefteye.mirror = true;
    setRotation(lefteye, 0F, 0F, 0F);
    head.addChild(lefteye);
    mounth = new ModelRenderer(this, 48, 0);
    mounth.addBox(0F, 0F, 0F, 2, 1, 1);
    mounth.setRotationPoint(-1F, -1F, -5F);
    mounth.setTextureSize(64, 64);
    mounth.mirror = true;
    setRotation(mounth, 0F, 0F, 0F);
    head.addChild(mounth);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
          float f = 2.0F;
          GlStateManager.pushMatrix();
          righteye.render(scale);
          lefteye.render(scale);
          mounth.render(scale);
          GlStateManager.popMatrix();
      }
      else
      {
          this.head.render(scale);
          this.body.render(scale);
          this.rightarm.render(scale);
          this.leftarm.render(scale);
          this.bodybig.render(scale);
          this.bodymiddle.render(scale);
          this.bodyleft.render(scale);
          this.leg.render(scale);
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
    boolean flag = entityIn instanceof EntityBaby;
      this.leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      //this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      
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
      this.rightarm.rotateAngleX += 90;
      this.leftarm.rotateAngleX -= 80;
      this.rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      this.leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
  }

}
