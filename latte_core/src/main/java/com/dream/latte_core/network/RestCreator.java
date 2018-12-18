package com.dream.latte_core.network;

import com.dream.latte_core.config.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zy on 2018/12/14.
 */
public class RestCreator {


    private static Retrofit mRetrofit;


    private static final class ParamsHolder{
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    private RestCreator(){
            mRetrofit = new Retrofit.Builder()
                    .client(OkhttpBuildHolder.BUILDER.build())
                    .baseUrl(Latte.getBaseUrl())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
    }


    public RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = mRetrofit.create(RestService.class);
    }


    private static final class OkhttpBuildHolder{
        static final long timeOut = 30L;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder()
                .readTimeout(timeOut,TimeUnit.SECONDS)
                .connectTimeout(timeOut,TimeUnit.SECONDS)
                .writeTimeout(timeOut,TimeUnit.SECONDS)
                .callTimeout(timeOut,TimeUnit.SECONDS);
    }



    public static RestCreator getInstance(){
        return RestCreatorHolder.REST_CREATOR;
    }

    private static final class RestCreatorHolder{
        private static final RestCreator REST_CREATOR = new RestCreator();
    }
}
