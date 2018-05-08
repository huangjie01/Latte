package com.example.latte_core.http.download;

import android.os.AsyncTask;

import com.example.latte_core.http.RestCreator;
import com.example.latte_core.http.callback.IError;
import com.example.latte_core.http.callback.IFailure;
import com.example.latte_core.http.callback.IRequest;
import com.example.latte_core.http.callback.ISuccess;

import java.io.File;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

/**
 * Created by huangjie on 2018/4/30.
 */

public class DownloadHander {
    private final String URL;
    private final Map<String, Object> PARMASl = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IError ERROR;
    private final IFailure FAILURE;

    public DownloadHander(String URL, IRequest REQUEST, ISuccess SUCCESS, String DOWNLOAD_DIR,
                          String EXTENSION, IError ERROR, IFailure FAILURE, String NAME) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.NAME = NAME;
    }

    public final void handerDownload() {

        RestCreator.getRestService().download(URL, PARMASl)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            ResponseBody body = response.body();
                            AsyncTask<Object, Void, File> saveFileTask =
                                    new SaveFileTask(REQUEST, SUCCESS)
                                            .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                                    DOWNLOAD_DIR, EXTENSION, body, NAME);

                            //这里一定要注意判断，否则文件下载不全
                            if (saveFileTask.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });

    }
}
