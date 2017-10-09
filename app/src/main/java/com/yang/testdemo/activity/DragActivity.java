package com.yang.testdemo.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTouch;

/**
 * 控件拖拽
 * Created by yangle on 2017/8/24.
 */

public class DragActivity extends BaseActivity implements View.OnDragListener {

    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.rl_container)
    RelativeLayout rlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ButterKnife.bind(this);

        // 目标区域设置拖拽事件监听
        rlContainer.setOnDragListener(this);
    }

    @OnTouch(R.id.iv_icon)
    public boolean onTouch(View v) {
        ClipData.Item item = new ClipData.Item("我来了");
        ClipData data = new ClipData(null, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
        v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        final int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED: // 拖拽开始
                Log.i("拖拽事件", "拖拽开始");
                return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);

            case DragEvent.ACTION_DRAG_ENTERED: // 被拖拽View进入目标区域
                Log.i("拖拽事件", "被拖拽View进入目标区域");
                return true;

            case DragEvent.ACTION_DRAG_LOCATION: // 被拖拽View在目标区域移动
                Log.i("拖拽事件", "被拖拽View在目标区域移动___X：" + event.getX() + "___Y：" + event.getY());
                tvTip.setText("X：" + event.getX() + "   Y：" + event.getY());
                return true;

            case DragEvent.ACTION_DRAG_EXITED: // 被拖拽View离开目标区域
                Log.i("拖拽事件", "被拖拽View离开目标区域");
                return true;

            case DragEvent.ACTION_DROP: // 放开被拖拽View
                Log.i("拖拽事件", "放开被拖拽View");
                // 释放拖拽阴影，并获取移动数据
                ClipData.Item item = event.getClipData().getItemAt(0);
                String content = item.getText().toString();
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
                return true;

            case DragEvent.ACTION_DRAG_ENDED: // 拖拽完成
                Log.i("拖拽事件", "拖拽完成");
                return true;

            default:
                break;
        }

        return false;
    }
}
