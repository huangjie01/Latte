package com.example.latte_core.delegates.navigator;

import com.example.latte_core.bean.NavigatorBean;

import java.util.LinkedHashMap;

/**
 * Created by huangjie on 2018/5/13.
 */

public class NavigatorBuilder {
    private final LinkedHashMap<NavigatorBean, NavigatorItemDelegate> ITEMS = new LinkedHashMap<>();

    static NavigatorBuilder builder() {
        return new NavigatorBuilder();
    }


    public final NavigatorBuilder addNavigator(NavigatorBean bean, NavigatorItemDelegate navigatorItemDelegate) {

        ITEMS.put(bean, navigatorItemDelegate);
        return this;
    }

    public final NavigatorBuilder addNavigator(LinkedHashMap<NavigatorBean, NavigatorItemDelegate> itemMap) {
        ITEMS.putAll(itemMap);
        return this;
    }


    public final LinkedHashMap<NavigatorBean, NavigatorItemDelegate> build() {
        return ITEMS;
    }

}
