package com.dmonsters.network;


import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClientFXUpdate implements IMessage {
	
	public static enum Type {
		SOULEYE,
		DUMP,
		WOMAN_HEART;		
	    public static Type fromInteger(int x) {
	        switch(x) {
	        case 0:
	            return SOULEYE;
	        case 1:
	            return DUMP;
	        case 2:
	            return WOMAN_HEART;
	        }
	        return null;
	    }
	}
	
    private BlockPos blockPos;
    private Type FXtype;
    
    public PacketClientFXUpdate() {
    }

    public PacketClientFXUpdate(BlockPos pos, Type type) {
    	blockPos = pos;
    	FXtype = type;
    	
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        FXtype = FXtype.fromInteger(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(blockPos.getX());
        buf.writeInt(blockPos.getY());
        buf.writeInt(blockPos.getZ());
        buf.writeInt(FXtype.ordinal());
    }

    public static class Handler implements IMessageHandler<PacketClientFXUpdate, IMessage> {
        @Override
        public IMessage onMessage(PacketClientFXUpdate message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketClientFXUpdate message, MessageContext ctx) {
        	switch (message.FXtype) {
        	case SOULEYE:
        		SoulEye(message, ctx);
        		break;
        	case DUMP:
        		Dump(message, ctx);
        		break;
        	case WOMAN_HEART:
        		WomanHeart(message, ctx);
        		break;
			default:
				break;
        	}
        }
        
        private void SoulEye(PacketClientFXUpdate message, MessageContext ctx) {
        	World world = Minecraft.getMinecraft().theWorld;
        	BlockPos pos = message.blockPos;
        	world.playSound(pos.getX(), pos.getY(), pos.getZ(), "dmonsters:block.souleye.kill", 1, 1, false);
        	Random rnd = new Random();
        	for (int i = 0; i < 15; i++) {
				double motionX = rnd.nextGaussian() * 0.001D;
				double motionY = Math.abs(rnd.nextGaussian() * 0.08D);
				double motionZ = rnd.nextGaussian() * 0.001D;
				float randX = rnd.nextFloat();
				float randY = rnd.nextFloat();
				float randZ = rnd.nextFloat();
				world.spawnParticle(EnumParticleTypes.FLAME,
						pos.getX() + randX, 
						pos.getY() + 0.5F + randY, 
						pos.getZ() + randZ,
						motionX,
						motionY,
						motionZ,
						new int[0]);
        	}
        }
        
        private void Dump(PacketClientFXUpdate message, MessageContext ctx) {
        	World world = Minecraft.getMinecraft().theWorld;
        	BlockPos pos = message.blockPos;
        	world.playSound(pos.getX(), pos.getY(), pos.getZ(), "dmonsters:block.dump.make", 1, 1, false);
        }
        
        private void WomanHeart(PacketClientFXUpdate message, MessageContext ctx) {
        	World world = Minecraft.getMinecraft().theWorld;
        	BlockPos pos = message.blockPos;
        	world.playSound(pos.getX(), pos.getY(), pos.getZ(), "mob.maiden.attack", 0.25F, 1, false);
        	Random rnd = new Random();
        	for (int i = 0; i < 8; i++) {
				double motionX = rnd.nextGaussian() * 0.001D;
				double motionY = Math.abs(rnd.nextGaussian() * 0.08D);
				double motionZ = rnd.nextGaussian() * 0.001D;
				float randX = rnd.nextFloat();
				float randY = rnd.nextFloat();
				float randZ = rnd.nextFloat();
				world.spawnParticle(EnumParticleTypes.SPELL_INSTANT,
						pos.getX() + randX, 
						pos.getY() + 0.5F + randY, 
						pos.getZ() + randZ,
						motionX,
						motionY,
						motionZ,
						new int[0]);
        	}
        }
    }
}