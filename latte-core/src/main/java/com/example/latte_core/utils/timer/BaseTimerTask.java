package com.example.latte_core.utils.timer;

import java.util.TimerTask;

/**
 * Created by huangjie on 2018/5/1.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener listener) {
        this.mITimerListener = listener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
