package com.yang.testdemo.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangle on 2017/1/14.
 */
public class SVGActivity extends BaseActivity {

    @Bind(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ButterKnife.bind(this);
        Glide.with(this).load("http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg").into(image);
    }
}
