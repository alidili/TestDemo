package com.yang.testdemo.activity;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yang.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 布局动画
 * Created by yangle on 2017/3/1.
 */
public class LayoutTransitionActivity extends BaseActivity {

    @Bind(R.id.listView)
    ListView listView;

    private List<String> list;
    private LayoutTransitionAdapter layoutTransitionAdapter;
    private LayoutTransition layoutTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        layoutTransitionAdapter = new LayoutTransitionAdapter();
        listView.setAdapter(layoutTransitionAdapter);

        layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(LayoutTransition.APPEARING,
                (ObjectAnimator.ofFloat(this, "translationY", -500, 0).setDuration(1000)));
        listView.setLayoutTransition(layoutTransition);
    }

    @OnClick(R.id.btn_add)
    public void onClick() {
        list.add("A");
        layoutTransitionAdapter.notifyDataSetChanged();
    }

    class LayoutTransitionAdapter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(LayoutTransitionActivity.this).inflate(
                        R.layout.adapter_layout_transition, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textView.setText(getItem(position));
            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.textView)
            TextView textView;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
