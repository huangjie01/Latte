package com.example.latte_core.http.interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * Created by huangjie on 2018/4/30.
 */

public abstract class BaseInterceptor implements Interceptor {

    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> parmas = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            parmas.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return parmas;
    }


    protected String getUrlParameters(Chain chain, String key) {
        final HttpUrl url = chain.request().url();
        return url.queryParameter(key);

    }


    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> parmas = new LinkedHashMap<>();
        int size = formBody.size();

        for (int i = 0; i < size; i++) {
            parmas.put(formBody.name(i), formBody.value(i));
        }
        return parmas;
    }


    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }


}
