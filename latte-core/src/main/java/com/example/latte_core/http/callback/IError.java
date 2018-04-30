package com.example.latte_core.http.callback;

/**
 * Created by huangjie on 2018/4/21.
 */

public interface IError {

    /**
     * @param code 错误码
     * @param mgs  错误信息
     */
    void onError(int code, String mgs);
}
