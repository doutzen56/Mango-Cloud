package com.mgcloud.common.utils;

import com.mgcloud.common.dto.KaptchaCodeDTO;

/**
 * 数学运算验证码
 * Created by tzen
 * tzen@e-veb.com
 * 2021/4/17 15:44
 */
public class CodeCreator {

    public KaptchaCodeDTO CodeCreator(String source) {
        KaptchaCodeDTO result = new KaptchaCodeDTO();
        String first = source.substring(0, 2);
        String second = source.substring(2, 3);
        int left = Integer.valueOf(first).intValue();
        int right = Integer.valueOf(second).intValue();
        //生成随机数，并且与2取模，确定运算符
        int seed = (int) (Math.random() * 10);
        int index = seed % 2;
        //计算运算结果，key保存结果，value保存运算式
        if (index == 1) {
            result.setCodeKey(String.valueOf(left - right));
            result.setCodeValue(String.valueOf(left) + "-" + String.valueOf(right) + "=");
        } else {
            result.setCodeKey(String.valueOf(left + right));
            result.setCodeValue(String.valueOf(left) + "+" + String.valueOf(right) + "=");
        }

        return result;
    }
}
