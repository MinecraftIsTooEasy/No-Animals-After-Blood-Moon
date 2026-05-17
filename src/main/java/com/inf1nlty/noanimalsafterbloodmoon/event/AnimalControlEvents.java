package com.inf1nlty.noanimalsafterbloodmoon.event;

import com.inf1nlty.noanimalsafterbloodmoon.control.AnimalControlState;
import com.inf1nlty.noanimalsafterbloodmoon.control.AnimalSpawnController;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.api.event.listener.IWorldInfoListener;
import net.minecraft.NBTTagCompound;
import net.minecraft.WorldServer;
import net.minecraft.server.MinecraftServer;

public final class AnimalControlEvents implements ITickListener, IWorldInfoListener {

    private static final AnimalControlEvents INSTANCE = new AnimalControlEvents();

    private AnimalControlEvents() {}

    public static AnimalControlEvents getInstance() {
        return INSTANCE;
    }

    @Override
    public void onServerTick(MinecraftServer server) {
        if (server == null || server.worldServers == null || server.worldServers.length == 0) {
            return;
        }

        WorldServer overworld = server.getOverworld();
        AnimalSpawnController.onOverworldServerTick(overworld);
    }

    @Override
    public void onNBTWrite(NBTTagCompound nbt) {
        AnimalControlState.writeToWorldInfo(nbt);
    }

    @Override
    public void onNBTRead(NBTTagCompound nbt) {
        AnimalControlState.readFromWorldInfo(nbt);
    }
}
