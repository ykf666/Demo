package com.code.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 找出字符串中所有的数字类型返回
 * <p>
 * Created by yankefei on 2022/4/12.
 */
public class ExtractNumbers {

    private final static Pattern PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");

    public static String[] extractNumbers(String[] numStr) {
        if (numStr == null || numStr.length == 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (String str : numStr) {
            Matcher matcher = PATTERN.matcher(str);
            if (matcher.find()) {
                list.add(str);
            }
        }
        return list.toArray(new String[]{});
    }

}
