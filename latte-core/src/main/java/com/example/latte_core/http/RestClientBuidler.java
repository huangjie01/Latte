package com.example.latte_core.http;

import android.content.Context;

import com.example.latte_core.http.callback.IError;
import com.example.latte_core.http.callback.IRequest;
import com.example.latte_core.http.callback.ISuccess;
import com.example.latte_core.http.callback.IFailure;
import com.example.latte_core.view.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by huangjie on 2018/4/21.
 */

public class RestClientBuidler {

    private String mUrl;
    private Map<String, Object> mParmas = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;
    private Context mContext;
    private File mFile;
    private LoaderStyle mLoaderStyle;

    RestClientBuidler() {

    }

    public final RestClientBuidler url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuidler parmas(WeakHashMap<String, Object> parmas) {
        this.mParmas.putAll(parmas);
        return this;
    }

    public final RestClientBuidler parmas(String key, Object value) {

        mParmas.put(key, value);
        return this;
    }

    public final RestClientBuidler raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuidler onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuidler success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public final RestClientBuidler error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuidler failure(IFailure ifailure) {

        this.mIFailure = ifailure;
        return this;
    }

    public final RestClientBuidler file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuidler file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RestClientBuidler loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuidler loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, mParmas, mIRequest,
                mISuccess, mIError, mIFailure, mBody,
                mFile, mContext, mLoaderStyle);
    }
}
