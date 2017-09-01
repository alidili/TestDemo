package com.yang.testdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by yangle on 2017/8/28.
 */

public class OpenUnknownSourceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("OpenUnknownSourceReceiver", "收到广播了");
    }
}
