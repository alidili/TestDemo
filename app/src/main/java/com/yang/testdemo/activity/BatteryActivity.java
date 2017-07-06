package com.yang.testdemo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.yang.testdemo.R;
import com.yang.testdemo.broadcast.BatteryChangeReceiver;
import com.yang.testdemo.widget.BatteryView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 电量显示
 * Created by yangle on 2017/7/6.
 */

public class BatteryActivity extends Activity {

    @Bind(R.id.battery_view)
    BatteryView batteryView;

    private BatteryChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        ButterKnife.bind(this);

        receiver = new BatteryChangeReceiver(new BatteryChangeReceiver.BatteryChangeListener() {
            @Override
            public void batteryChange(float electricQuantity) {
                batteryView.setElectricQuantity(electricQuantity);
            }
        });
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
