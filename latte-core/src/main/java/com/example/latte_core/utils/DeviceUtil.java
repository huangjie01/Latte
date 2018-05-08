package com.example.latte_core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.latte_core.app.Latte;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by huilianyi on 2017/3/16.
 */

public class DeviceUtil {

    /**
     * 获取设备品牌
     *
     * @return
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    public static String getDeviceName() {
        return Build.PRODUCT;
    }

    /**
     * 获取设备型号
     *
     * @return
     */
    public static String getDeviceType() {
        return Build.MODEL;
    }

    /**
     * 获取操作系统版本
     *
     * @return
     */
    public static String getDeviceOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getDeviceImei() {

        return DevicedIDUtil.getUniqueDevicedID();
    }


    public static int getDeviceWidth() {
        Point point = new Point();
        ((WindowManager) Latte.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        return point.x;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getWindowHeight() {
        Point point = new Point();
        ((WindowManager)Latte.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        return point.y;
    }

    /**
     * 获取当前的版本号
     *
     * @return
     */
    public static int getCurrentVersion() {
        int version = 0;
        PackageManager packageManager =Latte.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(Latte.getApplicationContext().getPackageName(), 0);
            version = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }


    public static String getAppVersion() {
        String version = "";
        PackageManager packageManager =Latte.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(Latte.getApplicationContext().getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }


    /**
     * 获取手机dpi
     *
     * @return
     */
    public static String getDeviceDpi() {
        //DisplayMetrics displayMetrics = Utils.ResUtils.getContext().getResources().getDisplayMetrics();
        //return displayMetrics.densityDpi;
        return getDeviceWidth() + "x" + getWindowHeight();
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusHeight = 0;
        Resources resource = context.getResources();
        int identifier = resource.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            statusHeight = resource.getDimensionPixelSize(identifier);
        }
        return statusHeight;

    }

    /**
     * 判断系统是否是miui系统
     *
     * @param
     * @return
     */
    public static boolean isMiui() {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + "ro.miui.ui.version.name");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return false;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return !StringUtils.isEmpty(line);
    }


    /**
     * 判断当前系统是否是flyme
     *
     * @return
     */
    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            if (method != null) {
                return true;
            } else {
                return isFlymeOs();
            }
        } catch (final Exception e) {
            return isFlymeOs();
        }
    }

    /**
     * 第二种办法判断系统是否是flame
     *
     * @return
     */
    private static boolean isFlymeOs() {
        try {
            Class<?> clas = Class.forName("android.os.SystemProperties");
            Method get = clas.getMethod("get", String.class, String.class);
            String invoke = (String) get.invoke(clas, "ro.build.display.id", "");
            if (!TextUtils.isEmpty(invoke)) {
                if (invoke.contains("flyme") || invoke.toLowerCase().contains("flyme")) {
                    return true;
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 小米手机实现修改状态栏字体颜色
     *
     * @param darkmode
     * @param activity
     */
    public static void setMiUiStatusBarDark(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 魅族手机修改状态栏字体颜色
     *
     * @param window
     * @param dark
     * @return
     */
    public static void setMeizuStatusBarDark(Window window, boolean dark) {
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
            } catch (Exception e) {
            }
        }

    }

    //设备是否root
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
                "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    //设备厂商
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 沉浸式状态栏设置
     *
     * @param isDark 是否让文字变黑
     */
    public static void setStatusBar(boolean isDark, Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int vis = decorView.getSystemUiVisibility();
            if (isDark) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(vis);
        }
    }


}
