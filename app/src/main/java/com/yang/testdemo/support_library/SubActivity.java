package com.yang.testdemo.support_library;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yang.testdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_input_delete);
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("详情界面");
        //collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.color_2F9DD2));
    }
}
