package com.dmonsters.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHandler {
    private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {
    }

    public static int nextID() {
        return packetId++;
    }

    public static void init(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerMessages() {
        INSTANCE.registerMessage(PacketClientFXUpdate.Handler.class, PacketClientFXUpdate.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketClientSetVelocity.Handler.class, PacketClientSetVelocity.class, nextID(), Side.CLIENT);
    }
}
