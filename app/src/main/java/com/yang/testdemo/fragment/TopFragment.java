package com.yang.testdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.testdemo.R;
import com.yang.testdemo.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by yangle on 2016/6/30.
 */
public class TopFragment extends Fragment {

    @Bind(R.id.tv_message)
    TextView tvMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 界面创建时，订阅事件， 接受消息
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onEvent(String msg) {
        tvMessage.setText(msg);
    }

    public void onEvent(User user) {
        tvMessage.setText(user.name);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
