package com.yang.testdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.yang.testdemo.R;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Handler测试
 * Created by yangle on 2017/6/12.
 */

public class HandlerActivity extends Activity {

    @Bind(R.id.tv_handler)
    TextView tvHandler;

    WeakHandler weakHandler = new WeakHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);

        //handler.sendEmptyMessageDelayed(0, 10 * 60 * 1000);
        weakHandler.sendEmptyMessageDelayed(0, 30 * 1000);
        finish();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("Handler：", "收到Handler发送的消息");
            tvHandler.setText("收到Handler发送的消息");
        }
    };

    private static class WeakHandler extends Handler {

        WeakReference<HandlerActivity> weakReference;

        public WeakHandler(HandlerActivity activity) {
            weakReference = new WeakReference<HandlerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = weakReference.get();
            Log.i("Handler：", "收到Handler发送的消息1");
            if (activity != null) {
                Log.i("Handler：", "收到Handler发送的消息2");
                activity.tvHandler.setText("收到Handler发送的消息");
            }
        }
    }
}
