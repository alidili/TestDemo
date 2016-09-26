package com.yang.testdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yang.testdemo.R;
import com.yang.testdemo.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/6/29.
 */
public class VolleyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_request)
    public void onClick() {
        HttpUtil.init(this);
        HttpUtil.addJsonPostRequest("http://www.quwan.ma/getAppkey", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("TAG", jsonObject.toString());
                try {
                    int code = jsonObject.getInt("code");
                    Log.i("TAG-CODE", code + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("TAG-ERROR", volleyError.getMessage(), volleyError);
            }
        });
    }
}
