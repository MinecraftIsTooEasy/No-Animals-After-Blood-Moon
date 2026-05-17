package com.inf1nlty.noanimalsafterbloodmoon;

import com.inf1nlty.noanimalsafterbloodmoon.event.AnimalControlEvents;
import fi.dy.masa.malilib.config.ConfigManager;
import moddedmite.rustedironcore.api.event.Handlers;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.ModResourceManager;

public class NoAnimalsAfterBloodMoonMod implements ModInitializer {

    public static final String NAMESPACE = "noanimalsafterbloodmoon";

    public void onInitialize() {
        ModResourceManager.addResourcePackDomain(NAMESPACE);

        NoAnimalsAfterBloodMoonConfigs.getInstance().load();
        ConfigManager.getInstance().registerConfig(NoAnimalsAfterBloodMoonConfigs.getInstance());

        AnimalControlEvents events = AnimalControlEvents.getInstance();
        Handlers.Tick.register(events);
        Handlers.WorldInfo.register(events);
    }
}
