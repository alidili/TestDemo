package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yang.testdemo.R;
import com.yang.testdemo.fragment.WeChatFragment;
import com.yang.testdemo.widget.WechatRadioGroup;

import java.util.ArrayList;
import java.util.List;

public class WeChatActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private WechatRadioGroup gradualRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        gradualRadioGroup = (WechatRadioGroup) findViewById(R.id.radiogroup);
        List<WeChatFragment> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            WeChatFragment fragment = new WeChatFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }
        viewPager.setAdapter(new DemoPagerAdapter(getSupportFragmentManager(), list));
        gradualRadioGroup.setViewPager(viewPager);
    }

    class DemoPagerAdapter extends FragmentPagerAdapter {
        List<WeChatFragment> mData;

        public DemoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public DemoPagerAdapter(FragmentManager fm, List<WeChatFragment> data) {
            super(fm);
            mData = data;
        }

        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }
    }
}
