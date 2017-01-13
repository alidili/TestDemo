package com.yang.testdemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.yang.testdemo.R;
import com.yang.testdemo.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangle on 2017/1/13.
 */
public class ImageButtonActivity extends BaseActivity {

    @Bind(R.id.image_button)
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_button);
        ButterKnife.bind(this);

        imageButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick() {
                Log.i("点击事件", "我被点击了");
            }
        });
    }
}
