package com.iorichina.utils.geo;

/**
 * 校验geo信息
 * <p>
 * Created by iorihuang on 2017/1/6.
 */
public class GeoHelper {
    /**
     * 校验经纬度是否合法
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    public static boolean isLongLatiValid(Integer longitude, Integer latitude) {
        if (null == longitude || longitude < -180 || longitude > 180) {
            return false;
        }

        if (null == latitude || latitude < -90 || latitude > 90) {
            return false;
        }

        return true;
    }
}
