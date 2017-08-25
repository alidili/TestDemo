package com.yang.testdemo.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 控件拖拽
 * Created by yangle on 2017/8/24.
 */

public class DragActivity extends BaseActivity {

    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.ll_container)
    LinearLayout llContainer;
    @Bind(R.id.rl_top_container)
    RelativeLayout rlTopContainer;

    private static final String IMAGE_VIEW_TAG = "已经拖到目标区域了";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ButterKnife.bind(this);

        ivIcon.setTag(IMAGE_VIEW_TAG);

        ivIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((String) v.getTag());
                ClipData data = new ClipData(IMAGE_VIEW_TAG,
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return true;
            }
        });

        rlTopContainer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;

                    case DragEvent.ACTION_DROP:
                        ivIcon.setX(event.getX() - ivIcon.getWidth() / 2);
                        ivIcon.setY(event.getY() - ivIcon.getHeight() / 2);
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;

                    default:
                        break;
                }
                return false;
            }
        });

        llContainer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // 拖拽开始事件
                        if (event.getClipDescription().hasMimeType(
                                ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        }
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        // 被拖放View进入目标View
                        llContainer.setBackgroundColor(Color.YELLOW);
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.i("拖拽位置", "x:" + event.getX() + "___y:" + event.getY());
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        // 被拖放View离开目标View
                        llContainer.setBackgroundColor(Color.BLUE);
                        tvTip.setText("");
                        return true;

                    case DragEvent.ACTION_DROP:
                        // 释放拖放阴影，并获取移动数据
                        ClipData.Item item = event.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();
                        tvTip.setText(dragData + event.getY() + ":" + event.getX());
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        // 拖放事件完成
                        return true;

                    default:
                        break;
                }
                return false;
            }
        });
    }
}
