package com.yang.testdemo.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.yang.testdemo.R;
import com.yang.testdemo.widget.JazzyViewPager;

import java.util.List;

/**
 * Created by yangle on 2016/1/21.
 */
public class GalleryAdapter extends PagerAdapter {

    private Activity activity;
    private JazzyViewPager jazzyViewPager;
    private List<String> photoUrlList;
    private BitmapUtils bitmapUtils;

    public GalleryAdapter(Activity activity, JazzyViewPager jazzyViewPager, List<String> photoUrlList) {
        this.activity = activity;
        this.jazzyViewPager = jazzyViewPager;
        this.photoUrlList = photoUrlList;
        this.bitmapUtils = new BitmapUtils(activity);
    }

    @Override
    public int getCount() {
        return photoUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = activity.getLayoutInflater().inflate(R.layout.adapter_gallery, container, false);

        ImageView iv_gallery = (ImageView) view.findViewById(R.id.iv_gallery);
        iv_gallery.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (photoUrlList != null && !photoUrlList.isEmpty()) {
            bitmapUtils.display(iv_gallery, photoUrlList.get(position));
        }

        container.addView(view);
        jazzyViewPager.setObjectForPosition(view, position);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
