package com.example.latte_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.utils.storage.LattePreference;
import com.example.latte_core.utils.timer.BaseTimerTask;
import com.example.latte_core.utils.timer.ITimerListener;
import com.example.latte_core.view.launcher.ScrollLauncherTag;
import com.example.latte_ec.R;
import com.example.latte_ec.R2;
import com.example.latte_ec.sign.SignUpDelegate;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huangjie on 2018/5/1.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.timer)
    AppCompatTextView mTvTimer;
    Timer mTimer;
    private int mCount = 5;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                        }
                        checkIsShowScroll();
                    }
                }
            }
        });
    }

    @OnClick(R2.id.timer)
    public void finishTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        checkIsShowScroll();
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {

            start(new LauncherScrollDelegate(), SINGLETASK);

        } else {

            start(new SignUpDelegate());
            //检查用户是否已经登录
        }
    }


    public void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }
}
