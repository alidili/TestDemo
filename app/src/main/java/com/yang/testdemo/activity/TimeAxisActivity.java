package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 时间轴
 * Created by yangle on 2017/7/14.
 */

public class TimeAxisActivity extends BaseActivity {

    @Bind(R.id.rv_time_axis)
    RecyclerView rvTimeAxis;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_axis);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initView() {
        rvTimeAxis.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        rvTimeAxis.setAdapter(new TimeAxisAdapter());
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
    }

    class TimeAxisAdapter extends RecyclerView.Adapter {

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return 0;
            } else {
                if (position == 1) {
                    return 1;
                }
                return 2;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View view = LayoutInflater.from(TimeAxisActivity.this).inflate(R.layout.adapter_time_axis_top, parent, false);
                return new TimeAxisHolderTop(view);

            } else if (viewType == 2) {
                View view = LayoutInflater.from(TimeAxisActivity.this).inflate(R.layout.adapter_time_axis_bottom, parent, false);
                return new TimeAxisHolderBottom(view);

            } else {
                View view = LayoutInflater.from(TimeAxisActivity.this).inflate(R.layout.adapter_time_axis, parent, false);
                return new TimeAxisHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TimeAxisHolderTop) {
                ((TimeAxisHolderTop) holder).tvItem.setText(list.get(position));

            } else if (holder instanceof TimeAxisHolderBottom) {
                ((TimeAxisHolderBottom) holder).tvItem.setText(list.get(position));

            } else if (holder instanceof TimeAxisHolder) {
                ((TimeAxisHolder) holder).tvItem.setText(list.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class TimeAxisHolderTop extends RecyclerView.ViewHolder {

            TextView tvItem;

            public TimeAxisHolderTop(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }

        class TimeAxisHolderBottom extends RecyclerView.ViewHolder {

            TextView tvItem;

            public TimeAxisHolderBottom(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }

        class TimeAxisHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public TimeAxisHolder(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }
}
