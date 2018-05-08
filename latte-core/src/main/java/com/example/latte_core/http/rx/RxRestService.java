package com.example.latte_core.http.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by huangjie on 2018/4/16.
 */

public interface RxRestService {
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> parmas);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> parmas);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> parmas);

    @DELETE
    Observable<String> delete(@Url String url, @QueryMap Map<String, Object> parmas);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> parmas);


    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);
}
