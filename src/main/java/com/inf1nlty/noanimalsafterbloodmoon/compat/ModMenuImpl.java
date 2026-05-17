package com.inf1nlty.noanimalsafterbloodmoon.compat;

import com.inf1nlty.noanimalsafterbloodmoon.NoAnimalsAfterBloodMoonConfigs;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class ModMenuImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> NoAnimalsAfterBloodMoonConfigs.getInstance().getConfigScreen(parent);
    }
}
