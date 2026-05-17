package com.inf1nlty.noanimalsafterbloodmoon;

import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigEnum;
import fi.dy.masa.malilib.config.options.ConfigInteger;

import java.util.ArrayList;
import java.util.List;

public class NoAnimalsAfterBloodMoonConfigs extends SimpleConfigs {

    private static final NoAnimalsAfterBloodMoonConfigs INSTANCE;

    public static final ConfigBoolean enableAnimalControl = new ConfigBoolean(
        "noanimalsafterbloodmoon.enableAnimalControl",
        true,
        "noanimalsafterbloodmoon.enableAnimalControl"
    );

    public static final ConfigEnum<NoAnimalTriggerMode> noAnimalTriggerMode = new ConfigEnum<>(
        "noanimalsafterbloodmoon.noAnimalTriggerMode",
        NoAnimalTriggerMode.FIRST_BLOOD_MOON,
        "noanimalsafterbloodmoon.noAnimalTriggerMode"
    );

    public static final ConfigInteger noAnimalStartDay = new ConfigInteger(
        "noanimalsafterbloodmoon.noAnimalStartDay",
        32,
        1,
        100000,
        false,
        "noanimalsafterbloodmoon.noAnimalStartDay"
    );

    public static final ConfigBoolean spawnBeforeFirstBloodMoon = new ConfigBoolean(
        "noanimalsafterbloodmoon.spawnBeforeFirstBloodMoon",
        true,
        "noanimalsafterbloodmoon.spawnBeforeFirstBloodMoon"
    );

    public static final ConfigBoolean clearAnimalsWhenNoAnimalModeStarts = new ConfigBoolean(
        "noanimalsafterbloodmoon.clearAnimalsWhenNoAnimalModeStarts",
        true,
        "noanimalsafterbloodmoon.clearAnimalsWhenNoAnimalModeStarts"
    );

    public static final ConfigBoolean keepTamedAnimals = new ConfigBoolean(
        "noanimalsafterbloodmoon.keepTamedAnimals",
        false,
        "noanimalsafterbloodmoon.keepTamedAnimals"
    );

    public static final ConfigBoolean blockAnimalSpawningInNoAnimalMode = new ConfigBoolean(
        "noanimalsafterbloodmoon.blockAnimalSpawningInNoAnimalMode",
        true,
        "noanimalsafterbloodmoon.blockAnimalSpawningInNoAnimalMode"
    );

    public static final List<ConfigBase<?>> General;
    public static final List<ConfigBase<?>> PreBloodMoon;
    public static final List<ConfigBase<?>> NoAnimalMode;
    public static final List<ConfigBase<?>> Total;
    public static final List<ConfigTab> Tabs;

    public NoAnimalsAfterBloodMoonConfigs(String name, List<ConfigBase<?>> values) {
        super(name, null, values);
    }

    @Override
    public List<ConfigTab> getConfigTabs() {
        return Tabs;
    }

    public static NoAnimalsAfterBloodMoonConfigs getInstance() {
        return INSTANCE;
    }

    public static boolean isAnimalControlEnabled() {
        return enableAnimalControl.getBooleanValue();
    }

    public static boolean shouldBlockAnimalSpawningInNoAnimalMode() {
        return blockAnimalSpawningInNoAnimalMode.getBooleanValue();
    }

    public static boolean shouldKeepTamedAnimals() {
        return keepTamedAnimals.getBooleanValue();
    }

    static {
        General = List.of(enableAnimalControl, noAnimalTriggerMode, noAnimalStartDay);
        PreBloodMoon = List.of(spawnBeforeFirstBloodMoon);
        NoAnimalMode = List.of(clearAnimalsWhenNoAnimalModeStarts, keepTamedAnimals, blockAnimalSpawningInNoAnimalMode);

        Total = new ArrayList<>();
        Total.addAll(General);
        Total.addAll(PreBloodMoon);
        Total.addAll(NoAnimalMode);

        Tabs = new ArrayList<>();
        Tabs.add(new ConfigTab("noanimalsafterbloodmoon.general", General));
        Tabs.add(new ConfigTab("noanimalsafterbloodmoon.preBloodMoon", PreBloodMoon));
        Tabs.add(new ConfigTab("noanimalsafterbloodmoon.noAnimalMode", NoAnimalMode));

        INSTANCE = new NoAnimalsAfterBloodMoonConfigs("NoAnimalsAfterBloodMoon", Total);
    }
}
