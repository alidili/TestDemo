package com.yang.testdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yang.testdemo.R;
import com.yang.testdemo.adapter.GalleryAdapter;
import com.yang.testdemo.widget.JazzyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangle on 2016/3/28.
 */
public class GalleryActivity extends Activity {

    private JazzyViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initView();
    }

    private void initView() {
        viewpager = (JazzyViewPager) findViewById(R.id.viewpager);

        List<String> list = new ArrayList<>();
        list.add("http://www.pp3.cn/uploads/allimg/111110/15563RI9-7.jpg");
        list.add("http://photo.enterdesk.com/2011-2-16/enterdesk.com-1AA0C93EFFA51E6D7EFE1AE7B671951F.jpg");
        list.add("http://www.pp3.cn/uploads/allimg/111111/0955412061-6.jpg");
        list.add("http://www.wallcoo.com/human/SZ_154_OKINAWA_Japan_01/wallpapers/1920x1200/beach_of_Okinawa_GJ065.jpg");
        list.add("http://imgstore.cdn.sogou.com/app/a/100540002/834169.jpg");
        list.add("http://image.tianjimedia.com/uploadImages/2012/320/8N5IGLFH4HDY_1920x1080.jpg");

        GalleryAdapter galleryAdapter = new GalleryAdapter(this, viewpager, list);
        viewpager.setAdapter(galleryAdapter);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setPageMargin(30);
    }
}
