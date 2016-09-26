package com.yang.testdemo.activity;

import android.os.Bundle;

import com.yang.testdemo.Constant;
import com.yang.testdemo.R;
import com.yang.testdemo.net.HttpChannel;
import com.yang.testdemo.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/7/1.
 */
public class OkHttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_ok_http)
    public void onClick() {
        Map<String, String> params = new HashMap<>();
        params.put("key", "aaa");
        params.put("uid", "aaa");
        params.put("sign", StringUtils.sign("aaaaaaaaa"));
        HttpChannel.sendMessageGet(Constant.UrlOrigin.get_token, params);
    }
}
