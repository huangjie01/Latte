package com.example.latte_core.bean;

/**
 * Created by huangjie on 2018/5/13.
 * 导航item bean
 */

public final class NavigatorBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public NavigatorBean(CharSequence ICON, CharSequence TITLE) {
        this.ICON = ICON;
        this.TITLE = TITLE;
    }

    public CharSequence getICON() {
        return ICON;
    }

    public CharSequence getTITLE() {
        return TITLE;
    }

}
