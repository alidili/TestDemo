package com.yang.testdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 电量改变广播接收
 * Created by yangle on 2017/7/6.
 */

public class BatteryChangeReceiver extends BroadcastReceiver {

    private BatteryChangeListener batteryChangeListener;

    public BatteryChangeReceiver(BatteryChangeListener batteryChangeListener) {
        this.batteryChangeListener = batteryChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            // 获取系统当前电量
            int level = intent.getIntExtra("level", 0);
            // 获取系统总电量
            int total = intent.getIntExtra("scale", 100);
            // 电量百分比
            float electricQuantity = (float) level / total;
            batteryChangeListener.batteryChange(electricQuantity);
        }
    }

    /**
     * 电量改变监听
     */
    public interface BatteryChangeListener {
        /**
         * 电量改变
         *
         * @param electricQuantity 当前电量百分比
         */
        void batteryChange(float electricQuantity);
    }
}
