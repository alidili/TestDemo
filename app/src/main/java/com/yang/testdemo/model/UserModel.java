package com.yang.testdemo.model;

import android.os.SystemClock;

import com.yang.testdemo.bean.User;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yangle on 2016/6/27.
 */
public class UserModel {

    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                // 设置个2000ms的延迟，模拟网络访问、数据库操作等等延时操作
                SystemClock.sleep(2000);
                final User user = new User("杨乐");
                if (user == null) {
                    subscriber.onError(new Exception("User = null"));
                } else {
                    subscriber.onNext(user);
                    user.name = "小明";
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
