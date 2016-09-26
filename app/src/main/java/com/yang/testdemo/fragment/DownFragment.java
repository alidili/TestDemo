package com.yang.testdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yang.testdemo.R;
import com.yang.testdemo.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by yangle on 2016/6/30.
 */
public class DownFragment extends Fragment implements View.OnLongClickListener {

    @Bind(R.id.btn_send)
    Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_down, null);
        ButterKnife.bind(this, view);
        btnSend.setOnLongClickListener(this);
        return view;
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
        EventBus.getDefault().post("冥道残月破");
    }

    @Override
    public boolean onLongClick(View v) {
        EventBus.getDefault().post(new User("犬夜叉"));
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
