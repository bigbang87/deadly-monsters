package com.dmonsters.models;

import com.dmonsters.entity.EntityMutantSteve;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTopielec extends ModelBase
{
    ModelRenderer head;
    ModelRenderer mainbody;
    ModelRenderer leftupperleg;
    ModelRenderer leftfoot;
    ModelRenderer leftlowerleg;
    ModelRenderer rightupperleg;
    ModelRenderer rightlowerleg;
    ModelRenderer rightfoot;
    ModelRenderer upperbody;
    ModelRenderer leftupperarm;
    ModelRenderer leftlowerfrontarm;
    ModelRenderer leftlowerbackarm;
    ModelRenderer rightupperarm;
    ModelRenderer rightlowerbackarm;
    ModelRenderer rightlowerfrontarm;
    ModelRenderer lefteye;
    ModelRenderer righteye;
    ModelRenderer mouth;
  
  public ModelTopielec()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-4F, -8F, -5F, 6, 8, 6);
    head.setRotationPoint(1F, 5F, 6F);
    head.setTextureSize(64, 64);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    mainbody = new ModelRenderer(this, 0, 15);
    mainbody.addBox(-5F, 0F, 0F, 10, 8, 9);
    mainbody.setRotationPoint(0F, 8F, -1F);
    mainbody.setTextureSize(64, 64);
    mainbody.mirror = true;
    setRotation(mainbody, 0F, 0F, 0F);
    leftupperleg = new ModelRenderer(this, 0, 33);
    leftupperleg.addBox(0F, 0F, -1F, 2, 4, 4);
    leftupperleg.setRotationPoint(2F, 16F, 4F);
    leftupperleg.setTextureSize(64, 64);
    leftupperleg.mirror = true;
    setRotation(leftupperleg, 0F, 0F, 0F);
    leftfoot = new ModelRenderer(this, 0, 48);
    leftfoot.addBox(-2F, 0F, -1F, 3, 1, 6);
    leftfoot.setRotationPoint(3F, 23F, 1F);
    leftfoot.setTextureSize(64, 64);
    leftfoot.mirror = true;
    setRotation(leftfoot, 0F, 0F, 0F);
    leftlowerleg = new ModelRenderer(this, 0, 42);
    leftlowerleg.addBox(0F, 0F, 0F, 1, 3, 2);
    leftlowerleg.setRotationPoint(2F, 20F, 3F);
    leftlowerleg.setTextureSize(64, 64);
    leftlowerleg.mirror = true;
    setRotation(leftlowerleg, 0F, 0F, 0F);
    rightupperleg = new ModelRenderer(this, 14, 33);
    rightupperleg.addBox(0F, 0F, 0F, 2, 4, 4);
    rightupperleg.setRotationPoint(-4F, 16F, 3F);
    rightupperleg.setTextureSize(64, 64);
    rightupperleg.mirror = true;
    setRotation(rightupperleg, 0F, 0F, 0F);
    rightlowerleg = new ModelRenderer(this, 7, 42);
    rightlowerleg.addBox(0F, 0F, 0F, 1, 3, 2);
    rightlowerleg.setRotationPoint(-3F, 20F, 3F);
    rightlowerleg.setTextureSize(64, 64);
    rightlowerleg.mirror = true;
    setRotation(rightlowerleg, 0F, 0F, 0F);
    rightfoot = new ModelRenderer(this, 21, 48);
    rightfoot.addBox(-2F, 0F, 0F, 3, 1, 6);
    rightfoot.setRotationPoint(-2F, 23F, 0F);
    rightfoot.setTextureSize(64, 64);
    rightfoot.mirror = true;
    setRotation(rightfoot, 0F, 0F, 0F);
    upperbody = new ModelRenderer(this, 27, 0);
    upperbody.addBox(-2F, 0F, -1F, 8, 3, 7);
    upperbody.setRotationPoint(-2F, 5F, 1F);
    upperbody.setTextureSize(64, 64);
    upperbody.mirror = true;
    setRotation(upperbody, 0F, 0F, 0F);
    leftupperarm = new ModelRenderer(this, 39, 15);
    leftupperarm.addBox(0F, 0F, -1F, 2, 8, 3);
    leftupperarm.setRotationPoint(5F, 9F, 4F);
    leftupperarm.setTextureSize(64, 64);
    leftupperarm.mirror = true;
    setRotation(leftupperarm, 0F, 0F, 0F);
    leftlowerfrontarm = new ModelRenderer(this, 39, 27);
    leftlowerfrontarm.addBox(0F, 0F, 0F, 2, 6, 1);
    leftlowerfrontarm.setRotationPoint(0F, 8F, 1F);
    leftlowerfrontarm.setTextureSize(64, 64);
    leftlowerfrontarm.mirror = true;
    setRotation(leftlowerfrontarm, 0.5235988F, 0F, 0F);
    leftupperarm.addChild(leftlowerfrontarm);
    leftlowerbackarm = new ModelRenderer(this, 47, 27);
    leftlowerbackarm.addBox(0F, 0F, 0F, 2, 6, 1);
    leftlowerbackarm.setRotationPoint(0F, 7.5F, -1F);
    leftlowerbackarm.setTextureSize(64, 64);
    leftlowerbackarm.mirror = true;
    setRotation(leftlowerbackarm, -0.5235988F, 0F, 0F);
    leftupperarm.addChild(leftlowerbackarm);
    rightupperarm = new ModelRenderer(this, 50, 15);
    rightupperarm.addBox(-2F, 0F, -1F, 2, 8, 3);
    rightupperarm.setRotationPoint(-5F, 9F, 4F);
    rightupperarm.setTextureSize(64, 64);
    rightupperarm.mirror = true;
    setRotation(rightupperarm, 0F, 0F, 0F);
    rightlowerbackarm = new ModelRenderer(this, 39, 35);
    rightlowerbackarm.addBox(0F, 0F, 0F, 2, 6, 1);
    rightlowerbackarm.setRotationPoint(-2F, 8F, 1F);
    rightlowerbackarm.setTextureSize(64, 64);
    rightlowerbackarm.mirror = true;
    setRotation(rightlowerbackarm, 0.5235988F, 0F, 0F);
    rightupperarm.addChild(rightlowerbackarm);
    rightlowerfrontarm = new ModelRenderer(this, 47, 35);
    rightlowerfrontarm.addBox(0F, 0F, 0F, 2, 6, 1);
    rightlowerfrontarm.setRotationPoint(-2F, 7.5F, -1F);
    rightlowerfrontarm.setTextureSize(64, 64);
    rightlowerfrontarm.mirror = true;
    setRotation(rightlowerfrontarm, -0.5235988F, 0F, 0F);
    rightupperarm.addChild(rightlowerfrontarm);
    lefteye = new ModelRenderer(this, 41, 48);
    lefteye.addBox(0F, 0F, 0F, 1, 1, 1);
    lefteye.setRotationPoint(1F, -13F, 0F);
    lefteye.setTextureSize(64, 64);
    lefteye.mirror = true;
    setRotation(lefteye, 0F, 0F, 0F);
    head.addChild(lefteye);
    righteye = new ModelRenderer(this, 41, 51);
    righteye.addBox(0F, 0F, 0F, 1, 1, 1);
    righteye.setRotationPoint(-2F, -13F, 0F);
    righteye.setTextureSize(64, 64);
    righteye.mirror = true;
    setRotation(righteye, 0F, 0F, 0F);
    head.addChild(righteye);
    mouth = new ModelRenderer(this, 46, 48);
    mouth.addBox(0F, 0F, 0F, 3, 1, 1);
    mouth.setRotationPoint(-1.5F, -10F, 0F);
    mouth.setTextureSize(64, 64);
    mouth.mirror = true;
    setRotation(mouth, 0F, 0F, 0F);
    head.addChild(mouth);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      head.render(scale);
      mainbody.render(scale);
      leftupperleg.render(scale);
      leftfoot.render(scale);
      leftlowerleg.render(scale);
      rightupperleg.render(scale);
      rightlowerleg.render(scale);
      rightfoot.render(scale);
      upperbody.render(scale);
      leftupperarm.render(scale);
      rightupperarm.render(scale);
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
      this.rightupperleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leftupperleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
      
      float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
      float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
      this.rightupperarm.rotateAngleZ = 0.0F;
      this.leftupperarm.rotateAngleZ = 0.0F;
      this.rightupperarm.rotateAngleY = -(0.1F - f * 0.6F);
      this.leftupperarm.rotateAngleY = 0.1F - f * 0.6F;
      float f2 = -(float)Math.PI / (flag ? 1.5F : 2.25F);
      this.rightupperarm.rotateAngleX = f2;
      this.leftupperarm.rotateAngleX = f2;
      this.rightupperarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
      this.leftupperarm.rotateAngleX += f * 1.2F - f1 * 0.4F;
      this.rightupperarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.leftupperarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
      this.rightupperarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
      this.leftupperarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
  }

}
