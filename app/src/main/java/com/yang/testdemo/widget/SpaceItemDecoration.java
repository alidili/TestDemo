package com.yang.testdemo.widget;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yang.testdemo.utils.DensityUtils;

/**
 * 分割线
 * Created by yangle on 2017/6/6.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int orientation = LinearLayoutManager.VERTICAL;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;

        if (orientation == LinearLayoutManager.HORIZONTAL) {
            int position = parent.getChildAdapterPosition(view);
            if (position % 2 != 0) {
                outRect.left = DensityUtils.dp2px(view.getContext(), 14);
            }
        }
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
