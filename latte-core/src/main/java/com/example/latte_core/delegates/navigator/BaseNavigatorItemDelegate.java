package com.example.latte_core.delegates.navigator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import com.example.latte_core.bean.NavigatorBean;
import com.example.latte_core.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by huangjie on 2018/5/13.
 */

public abstract class BaseNavigatorItemDelegate extends LatteDelegate {

    private ArrayList<NavigatorItemDelegate> ITEM_DELEGATE = new ArrayList<>();
    private ArrayList<NavigatorBean> ITEM_BEANS = new ArrayList<>();
    private final LinkedHashMap<NavigatorBean, NavigatorItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.BLACK;

    public abstract LinkedHashMap<NavigatorBean, NavigatorItemDelegate> setItems(NavigatorBuilder builder);


    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        mClickedColor = setClickedColor();
        final NavigatorBuilder builder = NavigatorBuilder.builder();
        final LinkedHashMap<NavigatorBean, NavigatorItemDelegate> items = setItems(builder);

        ITEMS.putAll(items);

        for (Map.Entry<NavigatorBean, NavigatorItemDelegate> item : ITEMS.entrySet()) {
            final NavigatorBean navigatorBean = item.getKey();
            final NavigatorItemDelegate navigatorItemDelegate = item.getValue();

            ITEM_BEANS.add(navigatorBean);
            ITEM_DELEGATE.add(navigatorItemDelegate);

        }
    }
}
