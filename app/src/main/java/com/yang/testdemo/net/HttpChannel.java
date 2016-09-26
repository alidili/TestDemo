package com.yang.testdemo.net;

import com.yang.testdemo.Constant;
import com.yang.testdemo.utils.OutPutMessage;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * http通道
 * Created by yangle on 2016/7/1.
 */
public class HttpChannel {

    /**
     * 使用http发送消息（get方式）
     *
     * @param urlOrigin     网络访问接口
     * @param requestParams 请求参数
     */
    public static void sendMessageGet(final String urlOrigin, Map<String, String> requestParams) {
        OkHttpUtils
                .get()
                .url(Constant.SERVER_URL + urlOrigin)
                .params(requestParams)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        OutPutMessage.LogCatInfo("http接收", "网络访问失败:" + e.toString());
                        OutPutMessage.showToast("网络连接异常");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        OutPutMessage.LogCatInfo("http接收", s);
                    }
                });
    }

    /**
     * 使用http发送消息（post方式）
     *
     * @param urlOrigin     网络访问接口
     * @param requestParams 请求参数
     */
    public static void sendMessagePost(final String urlOrigin, Map<String, String> requestParams) {
        OkHttpUtils
                .post()
                .url(Constant.SERVER_URL + urlOrigin)
                .params(requestParams)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        OutPutMessage.LogCatInfo("http接收", "网络访问失败:" + e.toString());
                        OutPutMessage.showToast("网络连接异常");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        OutPutMessage.LogCatInfo("http接收", s);
                    }
                });
    }
}
