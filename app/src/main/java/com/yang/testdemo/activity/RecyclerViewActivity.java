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
import com.yang.testdemo.adapter.HeaderViewRecyclerAdapter;
import com.yang.testdemo.listener.EndlessRecyclerOnScrollListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
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

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i("上拉加载更多", currentPage + "");
                HeaderViewRecyclerAdapter adapter = new HeaderViewRecyclerAdapter(recyclerViewAdapter);
                recyclerView.setAdapter(adapter);

                View loadMoreView = LayoutInflater.from(RecyclerViewActivity.this)
                        .inflate(R.layout.layout_refresh_footer, recyclerView, false);
                adapter.addFooterView(loadMoreView);
            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

        @Bind(R.id.tv_item)
        TextView tvItem;

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.adapter_recyclerview, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public RecyclerViewHolder(View view) {
                super(view);
                tvItem = (TextView) view.findViewById(R.id.tv_item);
            }
        }
    }
}
