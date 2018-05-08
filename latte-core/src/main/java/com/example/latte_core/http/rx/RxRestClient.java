package com.example.latte_core.http.rx;

import android.content.Context;

import com.example.latte_core.http.HttpMethod;
import com.example.latte_core.http.RestCreator;
import com.example.latte_core.http.callback.IError;
import com.example.latte_core.http.callback.IFailure;
import com.example.latte_core.http.callback.IRequest;
import com.example.latte_core.http.callback.ISuccess;
import com.example.latte_core.http.callback.RequestCallBacks;
import com.example.latte_core.view.loader.LatteLoader;
import com.example.latte_core.view.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by huangjie on 2018/4/16.
 * 网络请求Client
 */

public class RxRestClient {

    private final String URL;
    private final Map<String, Object> PARMASl = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        IRequest request,
                        ISuccess success,
                        IError error,
                        IFailure failure,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARMASl.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }


    public static RxRestClientBuidler buidler() {
        return new RxRestClientBuidler();
    }

    private Observable<String> request(HttpMethod httpMethod) {

        Observable<String> observable = null;
        final RxRestService restService = RestCreator.getRxRestService();

        if (LOADER_STYLE != null) {
            LatteLoader.showLoader(LOADER_STYLE, CONTEXT);
        }
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (httpMethod) {
            case GET:
                observable = restService.get(URL, PARMASl);
                break;
            case POST:
                observable = restService.post(URL, PARMASl);
                break;
            case DELETE:
                observable = restService.delete(URL, PARMASl);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(
                        MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part file = MultipartBody.Part.createFormData("file",
                        FILE.getName(), requestBody);
                observable = restService.upload(URL, file);
                break;
            case PUT:
                observable = restService.put(URL, PARMASl);
                break;
            case DOWNLOAD:

                break;
            case POST_RAW:
                observable = restService.postRaw(URL, BODY);
            default:
                break;
        }
        return observable;

    }

    private RequestCallBacks getCallBack() {
        return new RequestCallBacks(REQUEST, SUCCESS, ERROR, FAILURE, LOADER_STYLE);
    }


    public final void get() {
        request(HttpMethod.GET);
    }


    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARMASl.isEmpty()) {
                new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

}
