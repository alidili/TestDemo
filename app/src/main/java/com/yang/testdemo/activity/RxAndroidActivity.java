package com.yang.testdemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.testdemo.R;
import com.yang.testdemo.bean.User;
import com.yang.testdemo.impl.UserView;
import com.yang.testdemo.presenter.UserPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2016/6/24.
 */
public class RxAndroidActivity extends Activity implements UserView {

    @Bind(R.id.tv_user)
    TextView tvUser;

    private ProgressDialog progressDialog;
    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_android);
        ButterKnife.bind(this);

        userPresenter = new UserPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载，请稍后..");
    }

    @OnClick(R.id.btn_update)
    public void onClick() {
        userPresenter.getUser();
    }

    @Override
    public void updateView(User user) {
        if (user == null) {
            return;
        }
        tvUser.setText(user.name);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
