package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.testdemo.R;
import com.yang.testdemo.widget.swipetoloadlayout.OnLoadMoreListener;
import com.yang.testdemo.widget.swipetoloadlayout.OnRefreshListener;
import com.yang.testdemo.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * RecyclerView下拉刷新、上拉加载更多
 * Created by yangle on 2017/5/31.
 */

public class SwipeToLoadLayoutActivity extends BaseActivity {

    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;

    private final String TAG = "SwipeToLoadLayout";
    private List<String> list = new ArrayList<>();
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_to_load_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        initData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new RecyclerViewAdapter();
        swipeTarget.setLayoutManager(linearLayoutManager);
        swipeTarget.setAdapter(recyclerViewAdapter);

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "下拉刷新");
                swipeToLoadLayout.setRefreshing(false);
            }
        });

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.i(TAG, "上拉加载更多");
                initData();
                swipeToLoadLayout.setLoadingMore(false);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

        @Bind(R.id.tv_item)
        TextView tvItem;

        @Override
        public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SwipeToLoadLayoutActivity.this).inflate(R.layout.adapter_recyclerview, parent, false);
            return new RecyclerViewAdapter.RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
            holder.tvItem.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public RecyclerViewHolder(View view) {
                super(view);
                tvItem = (TextView) view.findViewById(R.id.tv_item);
            }
        }
    }

    private void initData() {
        for (int i = 0; i < 16; i++) {
            list.add(i + "");
        }
    }
}
