package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.testdemo.R;
import com.yang.testdemo.listener.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by yangle on 2017/1/12.
 */
public class RecyclerViewActivity extends BaseActivity {

    @Bind(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        initData();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        ptrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrame.refreshComplete();
            }
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                recyclerViewAdapter.setLoadMore(true);

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                                recyclerViewAdapter.setLoadMore(false);
                            }
                        });
                    }
                }, 1000);
            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private boolean isLoadMore = false;
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;

        @Override
        public int getItemViewType(int position) {
            // 最后一个item设置为footerView
            if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return TYPE_ITEM;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //进行判断显示类型，来创建返回不同的View
            if (viewType == TYPE_ITEM) {
                View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.adapter_recyclerview, parent, false);
                return new RecyclerViewHolder(view);

            } else if (viewType == TYPE_FOOTER) {
                View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.layout_refresh_footer, parent, false);
                return new FootViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof RecyclerViewHolder) {
                ((RecyclerViewHolder) holder).tvItem.setText(list.get(position));

            } else if (holder instanceof FootViewHolder) {
                FootViewHolder footViewHolder = (FootViewHolder) holder;
                if (isLoadMore) {
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                } else {
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return list.size() + 1;
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public RecyclerViewHolder(View view) {
                super(view);
                tvItem = (TextView) view.findViewById(R.id.tv_item);
            }
        }

        class FootViewHolder extends RecyclerView.ViewHolder {

            TextView tvLoading;

            public FootViewHolder(View view) {
                super(view);
                tvLoading = (TextView) view.findViewById(R.id.tv_loading);
            }
        }

        public void setLoadMore(boolean isLoadMore) {
            this.isLoadMore = isLoadMore;
            notifyDataSetChanged();
        }
    }

    private void initData() {
        for (int i = 0; i < 25; i++) {
            list.add(i + "");
        }
    }
}
