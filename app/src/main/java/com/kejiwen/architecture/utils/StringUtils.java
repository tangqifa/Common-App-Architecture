package com.kejiwen.architecture.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String formatFloat(float f, int pos) {
        float p = 1f;
        StringBuilder format = new StringBuilder("#0");
        for (int i = 0; i < pos; i++) {
            if (i == 0) {
                format.append('.');
            }
            p *= 10f;
            format.append('0');
        }
        f = Math.round(f * p) / p;
        DecimalFormat formatter = new DecimalFormat(format.toString());
        return formatter.format(f);
    }


    public static int parseInt(String s, int def) {
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (Exception e) {
            return def;
        }
    }

    public static int parseInt(String s) {
        return parseInt(s, 0);
    }

    public static long parseLong(String value, long def) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return def;
        }
    }

    public static float parseFloat(String s) {
        if (s != null) {
            try {
                float i = Float.parseFloat(s);
                return i;
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static boolean isEmpty(String s) {
        if (s != null) {
            for (int i = 0, count = s.length(); i < count; i++) {
                char c = s.charAt(i);
                if (c != ' ' && c != '\t' && c != '\n' && c != '\r') {
                    return false;
                }
            }
        }
        return true;
    }

    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
}
