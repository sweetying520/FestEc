package com.dream.latte_core.network;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
 * Created by zy on 2018/12/14.
 */
public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> queryParameter);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> fieldParameter);


    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody mRequestBody);


    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> fieldParameter);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody mRequestBody);

    @GET
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> queryParameter);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> queryParameter);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part part);

}