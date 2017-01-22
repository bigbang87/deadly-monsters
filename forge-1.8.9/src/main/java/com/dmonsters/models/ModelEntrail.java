package com.dmonsters.models;

import com.dmonsters.entity.EntityWoman;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEntrail extends ModelBase
{
    ModelRenderer head;
    ModelRenderer middle;
    ModelRenderer bottom;
    ModelRenderer end;
  
  public ModelEntrail()
  {
    textureWidth = 64;
    textureHeight = 64;
    
    middle = new ModelRenderer(this, 19, 0);
    middle.addBox(-3F, -3F, 0F, 6, 6, 7);
    middle.setRotationPoint(0F, 19F, -1F);
    middle.setTextureSize(64, 32);
    middle.mirror = true;
    setRotation(middle, 0F, 0F, 0F);
    bottom = new ModelRenderer(this, 46, 0);
    bottom.addBox(-2F, -2F, 2F, 4, 4, 5);
    bottom.setRotationPoint(0F, 19F, 3F);
    bottom.setTextureSize(64, 32);
    bottom.mirror = true;
    setRotation(bottom, 0F, 0F, 0F);
    head = new ModelRenderer(this, 0, 14);
    head.addBox(-4F, -8F, -7F, 8, 8, 8);
    head.setRotationPoint(0F, 9F, -1F);
    head.setTextureSize(64, 64);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    end = new ModelRenderer(this, 2, 2);
    end.addBox(-1F, -1F, 4.5F, 2, 2, 4);
    end.setRotationPoint(0F, 19F, 5F);
    end.setTextureSize(64, 64);
    end.mirror = true;
    setRotation(end, 0F, 0F, 0F);
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

	  head.render(scale);
	  middle.render(scale);
	  bottom.render(scale);
	  end.render(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
  {
	  //if (entityIn.getDistance(entityIn.prevPosX, entityIn.prevPosY, entityIn.prevPosZ) == 0)
		//  return;

      this.middle.rotateAngleX = MathHelper.cos(ageInTicks * 0.2F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(2 - 2));
      this.middle.rotationPointY = MathHelper.sin(ageInTicks * 0.2F + 2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.2F * (float)Math.abs(2 - 2);
      this.middle.rotationPointY += 5;
      
      this.bottom.rotateAngleX = MathHelper.cos(ageInTicks * 0.2F + 1 * 0.05F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
      this.bottom.rotationPointY = MathHelper.sin(ageInTicks * 0.2F + 1 * 0.05F * (float)Math.PI) * (float)Math.PI * 0.2F * (float)Math.abs(1 - 2);
      this.bottom.rotationPointY += 5;
      
      this.end.rotateAngleX = MathHelper.cos(ageInTicks * 0.2F + 0 * 0.5F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
      this.end.rotationPointY = MathHelper.sin(ageInTicks * 0.2F + 0 * 0.05F * (float)Math.PI) * (float)Math.PI * 0.2F * (float)Math.abs(0 - 2);
      this.end.rotationPointY += 5;
      
      this.head.rotateAngleY = netHeadYaw * 0.017453292F;
      this.head.rotateAngleX = headPitch * 0.017453292F;
  }

}
