package com.example.huangjie.latte;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.app.ISignListener;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.utils.DeviceUtil;
import com.example.latte_core.utils.StatusBarUtil;
import com.example.latte_ec.login.LoginDelegate;

/**
 * Created by huangjie on 2018/4/16.
 */

public class MainActivity extends ProxyActivity implements ISignListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarWhite();
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LoginDelegate();
    }


    public void setStatusBarWhite() {
        if (Build.VERSION.SDK_INT >= 23 || DeviceUtil.isMiui() || DeviceUtil.isFlyme()) {

            StatusBarUtil.setColor(this, Color.WHITE, 0);
            setStatusBar(true);
        }
    }

    /**
     * 沉浸式状态栏设置
     *
     * @param isDark 是否让文字变黑
     * @param
     */

    public void setStatusBar(boolean isDark) {
        if (DeviceUtil.isMiui()) {
            //因为小米最新系统让字体变色的方法变成了Android系统支持的方法，但是为了兼容老版本miui系统，需要同时写两个方法
            DeviceUtil.setMiUiStatusBarDark(isDark, this);
        } else if (DeviceUtil.isFlyme()) {
            DeviceUtil.setMeizuStatusBarDark(getWindow(), isDark);
            return;
        }

        Window window = getWindow();
        View decorView = window.getDecorView();
        int vis = decorView.getSystemUiVisibility();
        if (isDark) {
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }

        window.getDecorView().setSystemUiVisibility(vis);

    }

    @Override
    public void onSignInSuccess() {
    }

    @Override
    public void onSignUpSuccess() {

        Toast.makeText(getApplicationContext(), "组册成功", Toast.LENGTH_SHORT).show();

    }
}
