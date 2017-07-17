package com.yang.testdemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.yang.testdemo.R;
import com.yang.testdemo.support_library.SupportLibraryMainActivity;
import com.yang.testdemo.utils.FloatWindowUtils;
import com.yl.aidldemo.AIDLService;
import com.yl.aidldemo.CallBack;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/3/28.
 */
public class MainActivity extends BaseActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_time_axis, R.id.btn_battery,
            R.id.btn_aidl, R.id.btn_float_window, R.id.btn_handler, R.id.btn_start_app,
            R.id.btn_credit_score, R.id.btn_screen_test, R.id.btn_swipe_to_load_layout,
            R.id.btn_support_library, R.id.btn_circle_progress_bar, R.id.btn_color_track_view,
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
            case R.id.btn_time_axis:
                intent = new Intent(this, TimeAxisActivity.class);
                break;

            case R.id.btn_battery:
                intent = new Intent(this, BatteryActivity.class);
                break;

            case R.id.btn_aidl:
                Intent serviceIntent = new Intent("android.intent.action.AIDLTestService");
                serviceIntent.setPackage("com.yl.aidldemo");
                bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btn_float_window:
                FloatWindowUtils.showFloatWindow(this);
                break;

            case R.id.btn_handler:
                intent = new Intent(this, HandlerActivity.class);
                break;

            case R.id.btn_start_app:
                intent = new Intent(this, StartAppActivity.class);
                break;

            case R.id.btn_credit_score:
                intent = new Intent(this, CreditScoreActivity.class);
                break;

            case R.id.btn_screen_test:
                intent = new Intent(this, ScreenTestActivity.class);
                break;

            case R.id.btn_swipe_to_load_layout:
                intent = new Intent(this, SwipeToLoadLayoutActivity.class);
                break;

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

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "服务已连接");
            AIDLService aidlService = AIDLService.Stub.asInterface(service);

            try {
                aidlService.registerCallBack(new CallBack.Stub() {
                    @Override
                    public void progress(String progress) throws RemoteException {
                        Log.i(TAG, "当前进度：" + progress);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "服务已断开");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatWindowUtils.removeFloatWindow();
    }
}
