package com.example.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte_core.app.ISignListener;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_ec.R;

/**
 * Created by huangjie on 2018/5/1.
 */

public class SignUpDelegate extends LatteDelegate {

    private ISignListener mSignListener;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ISignListener) {
            mSignListener = (ISignListener) activity;
        }

    }
}
