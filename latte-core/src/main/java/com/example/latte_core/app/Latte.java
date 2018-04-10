package com.example.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by huangjie on 2018/4/10.
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigureations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }


    private static WeakHashMap<String, Object> getConfigureations() {
        return Configurator.getInstance().getLatteConfigs();
    }
}
