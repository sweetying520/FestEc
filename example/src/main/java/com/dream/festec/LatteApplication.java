package com.dream.festec;

import android.app.Application;

import com.dream.latte_core.config.Latte;

/**
 * Created by zy on 2018/12/14.
 */
public class LatteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .config();
    }
}
