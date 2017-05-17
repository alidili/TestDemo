package com.yang.testdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yang.testdemo.R;
import com.yang.testdemo.support_library.SupportLibraryMainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/3/28.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_support_library, R.id.btn_circle_progress_bar, R.id.btn_color_track_view,
            R.id.btn_coordinator_layout, R.id.btn_custom_image, R.id.btn_custom_img_container,
            R.id.btn_custom_progress_bar, R.id.btn_custom_volum_control_bar, R.id.btn_drawer_menu,
            R.id.btn_event_bus, R.id.btn_gallery, R.id.btn_image_button, R.id.btn_jd_index,
            R.id.btn_layout_transition, R.id.btn_ok_http, R.id.btn_panel_layout,
            R.id.btn_property_animation, R.id.btn_pull_to_refresh, R.id.btn_recycler_view,
            R.id.btn_rx_android, R.id.btn_rx_more, R.id.btn_scroller, R.id.btn_snack_bar,
            R.id.btn_svg, R.id.btn_tab_layout, R.id.btn_vdh_layout, R.id.btn_volley, R.id.btn_we_chat})
    public void onViewClicked(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.btn_support_library:
                intent = new Intent(this, SupportLibraryMainActivity.class);
                break;

            case R.id.btn_circle_progress_bar:
                intent = new Intent(this, CircleProgressBarActivity.class);
                break;

            case R.id.btn_color_track_view:
                intent = new Intent(this, ColorTrackViewActivity.class);
                break;

            case R.id.btn_coordinator_layout:
                intent = new Intent(this, CoordinatorLayoutActivity.class);
                break;

            case R.id.btn_custom_image:
                intent = new Intent(this, CustomImageActivity.class);
                break;

            case R.id.btn_custom_img_container:
                intent = new Intent(this, CustomImgContainerActivity.class);
                break;

            case R.id.btn_custom_progress_bar:
                intent = new Intent(this, CustomProgressBarActivity.class);
                break;

            case R.id.btn_custom_volum_control_bar:
                intent = new Intent(this, CustomVolumControlBarActivity.class);
                break;

            case R.id.btn_drawer_menu:
                intent = new Intent(this, DrawerMenuActivity.class);
                break;

            case R.id.btn_event_bus:
                intent = new Intent(this, EventBusActivity.class);
                break;

            case R.id.btn_gallery:
                intent = new Intent(this, GalleryActivity.class);
                break;

            case R.id.btn_image_button:
                intent = new Intent(this, ImageButtonActivity.class);
                break;

            case R.id.btn_jd_index:
                intent = new Intent(this, JDIndexActivity.class);
                break;

            case R.id.btn_layout_transition:
                intent = new Intent(this, LayoutTransitionActivity.class);
                break;

            case R.id.btn_ok_http:
                intent = new Intent(this, OkHttpActivity.class);
                break;

            case R.id.btn_panel_layout:
                intent = new Intent(this, PanelLayoutActivity.class);
                break;

            case R.id.btn_property_animation:
                intent = new Intent(this, PropertyAnimationActivity.class);
                break;

            case R.id.btn_pull_to_refresh:
                intent = new Intent(this, PullToRefreshActivity.class);
                break;

            case R.id.btn_recycler_view:
                intent = new Intent(this, RecyclerViewActivity.class);
                break;

            case R.id.btn_rx_android:
                intent = new Intent(this, RxAndroidActivity.class);
                break;

            case R.id.btn_rx_more:
                intent = new Intent(this, RxMoreActivity.class);
                break;

            case R.id.btn_scroller:
                intent = new Intent(this, ScrollerActivity.class);
                break;

            case R.id.btn_snack_bar:
                intent = new Intent(this, SnackbarActivity.class);
                break;

            case R.id.btn_svg:
                intent = new Intent(this, SVGActivity.class);
                break;

            case R.id.btn_tab_layout:
                intent = new Intent(this, TabLayoutActivity.class);
                break;

            case R.id.btn_vdh_layout:
                intent = new Intent(this, VDHLayoutActivity.class);
                break;

            case R.id.btn_volley:
                intent = new Intent(this, VolleyActivity.class);
                break;

            case R.id.btn_we_chat:
                intent = new Intent(this, WeChatActivity.class);
                break;

            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
