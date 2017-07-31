package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * 竖版时间轴
 * Created by yangle on 2017/7/31.
 */

public class TimeAxisVerticalActivity extends AppCompatActivity {

    @Bind(R.id.rv_time_axis)
    RecyclerView rvTimeAxis;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_axis_vertical);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initView() {
        rvTimeAxis.setLayoutManager(new LinearLayoutManager(this));
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
                return 1;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View view = LayoutInflater.from(TimeAxisVerticalActivity.this).inflate(R.layout.adapter_time_axis_left, parent, false);
                return new TimeAxisHolderLeft(view);

            } else {
                View view = LayoutInflater.from(TimeAxisVerticalActivity.this).inflate(R.layout.adapter_time_axis_right, parent, false);
                return new TimeAxisHolderRight(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TimeAxisHolderLeft) {
                ((TimeAxisHolderLeft) holder).tvItem.setText(list.get(position));

            } else if (holder instanceof TimeAxisHolderRight) {
                ((TimeAxisHolderRight) holder).tvItem.setText(list.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class TimeAxisHolderLeft extends RecyclerView.ViewHolder {

            TextView tvItem;

            public TimeAxisHolderLeft(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }

        class TimeAxisHolderRight extends RecyclerView.ViewHolder {

            TextView tvItem;

            public TimeAxisHolderRight(View itemView) {
                super(itemView);
                tvItem = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }
}
