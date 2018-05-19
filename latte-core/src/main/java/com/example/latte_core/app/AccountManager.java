package com.example.latte_core.app;

import com.example.latte_core.utils.storage.LattePreference;

/**
 * Created by huangjie on 2018/5/12.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 设置是否登录
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }


    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }


    /**
     * 检查是否登录
     *
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (checker == null) {
            return;
        }

        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

}
