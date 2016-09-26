package com.yang.testdemo.impl;

import com.yang.testdemo.bean.User;

/**
 * Created by yangle on 2016/6/27.
 */
public interface UserView {

    void updateView(User user);

    void showProgressDialog();

    void hideProgressDialog();

    void showError(String msg);
}
