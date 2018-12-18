package com.dream.latte_core.network;

import android.content.Context;
import android.text.TextUtils;

import com.dream.latte_core.network.callback.IError;
import com.dream.latte_core.network.callback.IFailure;
import com.dream.latte_core.network.callback.IRequest;
import com.dream.latte_core.network.callback.ISuccess;

import java.util.WeakHashMap;

/**
 * Created by zy on 2018/12/14.
 */
public class RestClientBuilder {
    private String mUrl;
    private WeakHashMap<String, Object> mParams = RestCreator.getInstance().getParams();
    private Context mContext;


    private IRequest mRequest;
    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;

    public RestClientBuilder url(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.mUrl = url;
        }
        return this;
    }

    public RestClientBuilder setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    public RestClientBuilder setRequest(IRequest mRequest) {
        this.mRequest = mRequest;
        return this;
    }

    public RestClientBuilder setSuccess(ISuccess mSuccess) {
        this.mSuccess = mSuccess;
        return this;
    }

    public RestClientBuilder setError(IError mError) {
        this.mError = mError;
        return this;
    }

    public RestClientBuilder setFailure(IFailure mFailure) {
        this.mFailure = mFailure;
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        params.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }


    public RestClient build() {
        return new RestClient(mUrl,
                mParams,
                mContext,
                mRequest,
                mSuccess,
                mError,
                mFailure);
    }
}
