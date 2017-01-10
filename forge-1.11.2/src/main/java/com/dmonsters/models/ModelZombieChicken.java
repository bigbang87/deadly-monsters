package com.dmonsters.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelZombieChicken extends ModelBase
{
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer upperhead;
    ModelRenderer middlehead;
    ModelRenderer bill;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer rightfeet;
    ModelRenderer leftfeet;
    ModelRenderer leftwing;
    ModelRenderer rightwing;
    
  
  public ModelZombieChicken()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 0, 12);
      head.addBox(-2F, -3F, -3F, 4, 3, 3);
      head.setRotationPoint(0F, 16F, 0F);
      head.setTextureSize(32, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 0, 0);
      body.addBox(0F, 0F, 0F, 6, 5, 7);
      body.setRotationPoint(-3F, 16F, -1F);
      body.setTextureSize(32, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      upperhead = new ModelRenderer(this, 0, 18);
      upperhead.addBox(0F, 0F, 0F, 2, 2, 3);
      upperhead.setRotationPoint(0F, -5F, -3F);
      upperhead.setTextureSize(32, 32);
      upperhead.mirror = true;
      setRotation(upperhead, 0F, 0F, 0F);
      head.addChild(upperhead);
      middlehead = new ModelRenderer(this, 0, 23);
      middlehead.addBox(0F, 0F, 0F, 1, 1, 3);
      middlehead.setRotationPoint(-1F, -4F, -3F);
      middlehead.setTextureSize(32, 32);
      middlehead.mirror = true;
      setRotation(middlehead, 0F, 0F, 0F);
      head.addChild(middlehead);
      bill = new ModelRenderer(this, 0, 27);
      bill.addBox(0F, 0F, 0F, 2, 1, 2);
      bill.setRotationPoint(-1F, -2F, -5F);
      bill.setTextureSize(32, 32);
      bill.mirror = true;
      setRotation(bill, 0F, 0F, 0F);
      head.addChild(bill);
      leftleg = new ModelRenderer(this, 12, 26);
      leftleg.addBox(0F, 0F, 0F, 1, 2, 1);
      leftleg.setRotationPoint(1F, 21F, 2F);
      leftleg.setTextureSize(32, 32);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 8, 26);
      rightleg.addBox(0F, 0F, 0F, 1, 2, 1);
      rightleg.setRotationPoint(-2F, 21F, 2F);
      rightleg.setTextureSize(32, 32);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      rightfeet = new ModelRenderer(this, 20, 20);
      rightfeet.addBox(0F, 0F, 0F, 2, 1, 2);
      rightfeet.setRotationPoint(-.75F, 2F, -1F);
      rightfeet.setTextureSize(32, 32);
      rightfeet.mirror = true;
      setRotation(rightfeet, 0F, 0F, 0F);
      rightleg.addChild(rightfeet);
      leftfeet = new ModelRenderer(this, 20, 24);
      leftfeet.addBox(0F, 0F, 0F, 2, 1, 2);
      leftfeet.setRotationPoint(-.25F, 2F, -1F);
      leftfeet.setTextureSize(32, 32);
      leftfeet.mirror = true;
      setRotation(leftfeet, 0F, 0F, 0F);
      leftleg.addChild(leftfeet);
      leftwing = new ModelRenderer(this, 14, 12);
      leftwing.addBox(0F, 0F, 0F, 1, 3, 4);
      leftwing.setRotationPoint(3F, 16F, 0F);
      leftwing.setTextureSize(32, 32);
      leftwing.mirror = true;
      setRotation(leftwing, -0.7807508F, -0.0743572F, -0.2974289F);
      rightwing = new ModelRenderer(this, 10, 19);
      rightwing.addBox(0F, 0F, 0F, 1, 3, 4);
      rightwing.setRotationPoint(-3F, 16F, 0F);
      rightwing.setTextureSize(32, 32);
      rightwing.mirror = true;
      setRotation(rightwing, 0F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

      if (this.isChild)
      {
    	  this.upperhead.render(scale);
    	  this.middlehead.render(scale);
    	  this.bill.render(scale);
    	  this.rightfeet.render(scale);
    	  this.leftfeet.render(scale);
      }
      else
      {
    	  this.head.render(scale);
    	  this.body.render(scale);
    	  this.leftleg.render(scale);
    	  this.rightleg.render(scale);
    	  this.leftwing.render(scale);
    	  this.rightwing.render(scale);
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
      this.head.rotateAngleX = headPitch * 0.017453292F;
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.4F * limbSwingAmount;
      this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.4F * limbSwingAmount;
      this.rightwing.rotateAngleZ = MathHelper.clamp(MathHelper.cos(limbSwing * 1 + (float)Math.PI) * 2.4F, 0.2F, 100);
  }

}
