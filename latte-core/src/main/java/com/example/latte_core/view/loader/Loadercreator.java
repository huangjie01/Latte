package com.example.latte_core.view.loader;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by huangjie on 2018/4/22.
 */

public final class Loadercreator {
    private static final WeakHashMap<String, Indicator> LOAD_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if (LOAD_MAP.get(type) == null) {
            Indicator indicator = getIndicator(type);
            LOAD_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOAD_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        if (!name.contains(".")) {
            String defaultClassName = AVLoadingIndicatorView.class.getPackage().getName();
            builder.append(defaultClassName)
                    .append(".indicators")
                    .append(".");
        }
        builder.append(name);
        try {
            Class<?> loadClass = Class.forName(builder.toString());
            return (Indicator) loadClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
