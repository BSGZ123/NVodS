package com.bskplu.common_utils.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * @ClassName: UUIDUtils
 * @Description: 生成唯一识别码
 * @Author BsKPLu
 * @Date 2022/2/13
 * @Version 1.1
 */
public class UUIDUtils {

    /**
     * 生成指定位数的随机字符串
     * @param number
     * @return
     */
    public static String getRandomNumber(Integer number) {
        return RandomStringUtils.randomNumeric(number);
    }

    /**
     * 生成16位uuid字符串
     * @return
     */
    public static String getRandomNumber() {
        return RandomStringUtils.randomNumeric(16);
    }

    /**
     * 生成随机[a-z]字符串，包含大小写
     * @param number :
     * @return
     */
    public static String getRandomString(Integer number) {
        return RandomStringUtils.randomAlphabetic(number);
    }

    /**
     * 生成从ASCII 32到126组成的随机字符串
     * @param number :
     * @return
     */
    public static String getRandomAscii(Integer number) {
        return RandomStringUtils.randomAscii(number);
    }


    /**
     * 生成没有 "-" 的uuid随机字符串  (包含字母和数字)
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
