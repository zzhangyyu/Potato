package com.yoler.potato.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangyu on 2018/1/17.
 */

public class RegexUtils {

    /**
     * 验证由数字、26个英文字母或者下划线组成的字符串
     *
     * @param toValidate
     * @return
     */
    public static boolean checkEngNum_(String toValidate) {
        String regEx = "^\\w+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(toValidate);
        return matcher.matches();
    }
}
