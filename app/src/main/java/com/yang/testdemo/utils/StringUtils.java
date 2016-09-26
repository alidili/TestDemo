package com.yang.testdemo.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import com.yang.testdemo.R;
import com.yang.testdemo.logic.Controller;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by yangle on 2016/4/15.
 */
public class StringUtils {

    /**
     * 关键字高亮显示
     *
     * @param context 上下文
     * @param target  需要高亮的关键字
     * @param text    需要显示的文字
     * @return spannable 处理完后的结果
     */
    public static SpannableStringBuilder highlight(Context context, String text, String target) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span;
        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(context.getResources().getColor(R.color.color_EA2D2D));
            spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    /**
     * 使用secret签名后的值
     * 签名内容key+uid+secret，使用MD5加密
     *
     * @param str 要签名的值
     * @return 签名后的值
     */
    public static String sign(String str) {
        return strToMD5(str);
    }

    /**
     * 将字符串转换成MD5值
     *
     * @param str 要转换的字符串
     * @return MD5值
     */
    public static String strToMD5(String str) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param str 要格式化的字符串
     * @return 格式化完成后的字符串
     */
    public static String subZeroAndDot(String str) {
        if (str.indexOf(".") > 0) {
            //去掉多余的0
            str = str.replaceAll("0+?$", "");
            //如最后一位是.则去掉
            str = str.replaceAll("[.]$", "");
        }
        return str;
    }

    /**
     * 关键字高亮显示
     *
     * @param text   需要显示的文字
     * @param target 需要高亮的关键字
     * @param color  高亮颜色
     * @param start  头部增加高亮文字个数
     * @param end    尾部增加高亮文字个数
     * @return 处理完后的结果
     */
    public static SpannableStringBuilder highlight(String text, String target, int color,
                                                   int start, int end) {
        Context context = Controller.getContext();
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span;
        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(context.getResources().getColor(color));
            spannable.setSpan(span, m.start() - start, m.end() + end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }
}
