package com.yang.testdemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yang.testdemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 可截图的Dialog
 * Created by yangle on 2017/10/17.
 */

public class ScreenshotDialog extends Dialog {

    public ScreenshotDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        this.getWindow().getDecorView().setDrawingCacheEnabled(true);
        this.getWindow().getDecorView().buildDrawingCache();
    }

    public void saveBitmap() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path, System.currentTimeMillis() + ".png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            View view = this.getWindow().getDecorView();
            Bitmap bitmap = view.getDrawingCache();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "图片保存到了" + path, Toast.LENGTH_SHORT).show();
    }
}
