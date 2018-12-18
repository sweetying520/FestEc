package com.dream.latte_core.config;

import android.content.Context;

/**
 * Created by zy on 2018/12/14.
 */
public class Latte {

    public static Configurator init(Context mContext) {
        Configurator.getInstance().getConfigMap().put(ConfigratorType.CONTEXT.name(), mContext);
        return Configurator.getInstance();
    }

    public static Context getContext() {
        return (Context) Configurator.getInstance().get(ConfigratorType.CONTEXT.name());
    }

    public static String getBaseUrl() {
        return (String) Configurator.getInstance().get(ConfigratorType.API_HOST.name());
    }
}
