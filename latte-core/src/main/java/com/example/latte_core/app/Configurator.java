package com.example.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by huangjie on 2018/4/10.
 */

public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

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
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void initIcons() {

        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 配置字体图标
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 配置 host
     *
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return getInstance();
    }

    /**
     * 检查配置是否完成
     */
    public void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");

        }
    }

    final <T> T getConfiguration(Object object) {
        checkConfiguration();
        Object value = LATTE_CONFIGS.get(object);
        if (value == null) {
            new NullPointerException(object.toString());
        }

        return (T) LATTE_CONFIGS.get(object);
    }
}
