package com.dream.latte_core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zy on 2018/12/14.
 */
public class Configurator {


    private static Map<Object, Object> configMap;

    private Configurator() {
        configMap = new HashMap<>();
        configMap.put(ConfigratorType.IS_CONFIG.name(), false);
    }

    public static Configurator getInstance() {
        return ConfiguratorHolder.CONFIGURATOR;
    }


    private static final class ConfiguratorHolder {
        private static final Configurator CONFIGURATOR = new Configurator();
    }

    public Configurator withApiHost(String apiHost) {
        configMap.put(ConfigratorType.API_HOST.name(), apiHost);
        return this;
    }


    Map<Object, Object> getConfigMap() {
        return configMap;
    }

    public void config() {
        configMap.put(ConfigratorType.IS_CONFIG.name(), true);
    }

    private void check() {
        boolean isConfig = (boolean) configMap.get(ConfigratorType.IS_CONFIG.name());
        if (!isConfig) {
            throw new RuntimeException("Please initial config");
        }

    }


    <T> T get(String key) {
        check();
        return (T) configMap.get(key);
    }

}
