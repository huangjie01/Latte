package com.example.latte_core.http.callback;

import com.example.latte_core.view.loader.LatteLoader;
import com.example.latte_core.view.loader.LoaderStyle;
import com.example.latte_core.view.loader.Loadercreator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huangjie on 2018/4/21.
 */

public class RequestCallBacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;


    public RequestCallBacks(IRequest request, ISuccess success, IError error, IFailure failure, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        if (LOADER_STYLE != null) {
            LatteLoader.stopLoader();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.stopLoader();
        }
    }
}
