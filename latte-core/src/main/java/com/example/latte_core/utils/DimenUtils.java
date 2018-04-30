package com.example.latte_core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.latte_core.app.Latte;

/**
 * Created by huangjie on 2018/4/22.
 */

public class DimenUtils {

    public static int getSrrenWidth() {
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }


    public static int getSrrenHeight() {
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
