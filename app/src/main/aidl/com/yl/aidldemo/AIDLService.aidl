package com.yl.aidldemo;

import com.yl.aidldemo.CallBack;

interface AIDLService {

    String getData();

    void registerCallBack(CallBack callBack);
}
