package com.dmonsters.main;

import java.util.Random;

import com.dmonsters.entity.EntityHauntedCow;
import com.dmonsters.network.PacketClientFXUpdate;
import com.dmonsters.network.PacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onPlayerAttack(AttackEntityEvent e) {
		if (ModConfig.hauntedCowDisabled)
			return;
		if (!e.getEntity().getEntityWorld().isRemote) {
			Entity entity = e.getTarget();
			if (entity instanceof EntityCow || entity instanceof EntityHauntedCow) {
				Random random = new Random();
				float rndChance = random.nextFloat();
				if (rndChance < 0.5F)
					return;
				World world = entity.getEntityWorld();
				EntityPlayer player = e.getEntityPlayer();
				Item itemClass = player.getHeldItemMainhand().getItem();
				if (itemClass instanceof ItemSword || itemClass instanceof ItemBow)
					return;
				entity.setDropItemsWhenDead(false);
				entity.setDead();
				spawnEntity(entity, new EntityHauntedCow(world));
	            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(entity.getPosition(), PacketClientFXUpdate.Type.SOULEYE));
				if (ModConfig.hauntedCowDisableTimeChange)
					return;
				Style red = new Style().setColor(TextFormatting.DARK_RED);
				TextComponentTranslation msg = new TextComponentTranslation("msg.dmonsters.hauntedcow");
				msg.setStyle(red);
	            PacketHandler.INSTANCE.sendToAll(new PacketClientFXUpdate(player.getPosition(), PacketClientFXUpdate.Type.TIME_CHANGE));
				world.setWorldTime(18000);
				player.addChatMessage(msg);
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
        worldIn.spawnEntityInWorld(entity);
    }
}