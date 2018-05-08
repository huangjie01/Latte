package com.example.latte_ec.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_ec.R;

/**
 * Created by huangjie on 2018/5/6.
 */

public class LoginDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
