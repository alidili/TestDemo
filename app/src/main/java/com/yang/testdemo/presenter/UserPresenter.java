package com.yang.testdemo.presenter;

import com.yang.testdemo.bean.User;
import com.yang.testdemo.impl.UserView;
import com.yang.testdemo.model.UserModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangle on 2016/6/27.
 */
public class UserPresenter {

    private UserView userView;
    private UserModel userModel;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        userModel = new UserModel();
    }

    public void getUser() {
        userView.showProgressDialog();

        userModel.getUser()
                .subscribeOn(Schedulers.io()) //在非UI线程中执行getUser
                .observeOn(AndroidSchedulers.mainThread()) //在UI线程中执行结果
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        userView.hideProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        userView.showError(e.getMessage());
                        userView.hideProgressDialog();
                    }

                    @Override
                    public void onNext(User user) {
                        userView.updateView(user);
                    }
                });
    }
}
