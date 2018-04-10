package com.example.latte_core.app;

import java.util.WeakHashMap;

/**
 * Created by huangjie on 2018/4/10.
 */

public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);

    }


    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 用于实现单例模式
     * 是线程安全的
     */
    public static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 配置完成
     */
    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }
}
