

package com.mgcloud.common.utils;

/**
 * Redis所有Keys
 * <p>
 * tzen@e-veb.com
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key) {
        return "sessionid:" + key;
    }
}
