package com.example.latte_core.http.callback;

/**
 * Created by huangjie on 2018/4/21.
 */

public interface IRequest {

    /**
     * 请求开始回调
     */
    void onRequestStart();

    /**
     * 请求结束回调
     */
    void onRequestEnd();
}
