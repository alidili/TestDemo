package com.yang.testdemo;

/**
 * 常量
 * Created by yangle on 2016/3/28.
 */
public class Constant {

    /**
     * 网络访问地址（开发使用）
     */
    public static final String SERVER_URL = "http://123.57.47.53:10011";

    /**
     * 网络访问接口
     */
    public static class UrlOrigin {
        /**
         * 获取访问令牌
         */
        public static final String get_token = "/authorize";
    }

    /**
     * 主要功能描述:输出目标
     */
    public static class OUT_TARGET {
        /**
         * 用户
         */
        public static final int USER = 0;
        /**
         * 开发
         */
        public static final int DEBUG = 1;
    }
}
