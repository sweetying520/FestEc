package com.dream.latte_core.network;

import android.content.Context;

import com.dream.latte_core.network.callback.IError;
import com.dream.latte_core.network.callback.IFailure;
import com.dream.latte_core.network.callback.IRequest;
import com.dream.latte_core.network.callback.ISuccess;
import com.dream.latte_core.network.callback.RestCallBack;

import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;

/**
 * Created by zy on 2018/12/14.
 */
public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final Context CONTEXT;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;


    RestClient(String URL, WeakHashMap<String, Object> PARAMS,
               Context CONTEXT, IRequest REQUEST,
               ISuccess SUCCESS,
               IError ERROR, IFailure FAILURE) {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.CONTEXT = CONTEXT;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(RequestMethod requestMethod, boolean showLoading) {
        RestService restService = RestCreator.getInstance().getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        showLoading(showLoading);

        switch (requestMethod) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case DETETE:
                call = restService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                MultipartBody.Part part = null;
                call = restService.upload(URL, part);
                break;
            default:
                break;
        }

        if(call != null){
            call.enqueue(getRestCallBack());
        }

    }


    /**
     * 加载的dialog
     */
    private void showLoading(boolean showLoading) {
        Loader.showLoading(CONTEXT,showLoading);
    }

    private RestCallBack getRestCallBack(){
        return new RestCallBack(REQUEST,SUCCESS,ERROR,FAILURE);
    }

    public void get(){
        request(RequestMethod.GET,true);
    }

    public void get(boolean isShowLoading){
        request(RequestMethod.GET,isShowLoading);
    }

    public void post(){
        request(RequestMethod.POST,true);
    }

    public void post(boolean isShowLoading){
        request(RequestMethod.POST,isShowLoading);
    }

    public void put(){
        request(RequestMethod.PUT,true);
    }

    public void put(boolean isShowLoading){
        request(RequestMethod.PUT,isShowLoading);
    }

    public void delete(){
        request(RequestMethod.DETETE,true);
    }

    public void delete(boolean isShowLoading){
        request(RequestMethod.DETETE,isShowLoading);
    }


}
