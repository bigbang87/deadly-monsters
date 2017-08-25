package com.dmonsters.main;

import com.dmonsters.entity.EntityHauntedCow;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onPlayerAttack(AttackEntityEvent e) {
		if (!e.getEntity().getEntityWorld().isRemote) {
			Entity entity = e.getTarget();
			World world = entity.getEntityWorld();
			System.out.println(entity);
			if (entity instanceof EntityCow) {
				entity.setDropItemsWhenDead(false);
				entity.setDead();
				spawnEntity(entity, new EntityHauntedCow(world));
	            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entity.getPosition(), PacketClientFXUpdate.Type.SOULEYE));
			}
		}
	}
	
    private void spawnEntity(Entity targetEntity, Entity entity) {
    	World worldIn = targetEntity.getEntityWorld();
        EntityLiving entityliving = (EntityLiving)entity;
        entity.setLocationAndAngles(targetEntity.posX, targetEntity.posY, targetEntity.posZ, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
        entityliving.rotationYawHead = entityliving.rotationYaw;
        entityliving.renderYawOffset = entityliving.rotationYaw;
        entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
        entityliving.playLivingSound();
        worldIn.spawnEntity(entity);
    }
}