package com.example.latte_core.http;

import android.os.Build;

import com.example.latte_core.app.ConfigType;
import com.example.latte_core.app.Configurator;
import com.example.latte_core.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Streaming;

/**
 * Created by huangjie on 2018/4/21.
 */

public class RestCreator {


    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {

        private static final String BASE_URL = (String) Latte.getConfiguration(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static final class OKHttpHolder {
        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public static final class RestServiceHolder {

        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }
}
