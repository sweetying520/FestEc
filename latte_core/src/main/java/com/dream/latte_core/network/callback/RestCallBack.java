package com.dream.latte_core.network.callback;

import com.dream.latte_core.network.Loader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zy on 2018/12/14.
 */
public class RestCallBack implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public RestCallBack(IRequest REQUEST, ISuccess SUCCESS, IError ERROR, IFailure FAILURE) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }else {
                    if(FAILURE != null){
                        FAILURE.onFailure(response.body());
                    }
                }
            }else {
                if(FAILURE != null){
                    FAILURE.onFailure(response.body());
                }
            }
        }else {
            if(FAILURE != null){
                FAILURE.onFailure(response.body());
            }
        }

        requestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(ERROR != null){
            ERROR.onError(t.getMessage());
        }

        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }

        requestFinish();
    }

    /**
     * 请求完成
     */
    private void requestFinish() {
        dismissLoading();
    }

    private void dismissLoading() {
        Loader.dismissLoading();
    }
}
