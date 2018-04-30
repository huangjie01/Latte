package com.example.huangjie.latte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.http.RestClient;
import com.example.latte_core.http.callback.IError;
import com.example.latte_core.http.callback.IFailure;
import com.example.latte_core.http.callback.ISuccess;

/**
 * Created by huangjie on 2018/4/16.
 */

public class MainDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testHttp();
    }

    private void testHttp() {
        RestClient.buidler()
                .url("http://news.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String mgs) {

                    }
                })
                .build()
                .get();
    }
}
