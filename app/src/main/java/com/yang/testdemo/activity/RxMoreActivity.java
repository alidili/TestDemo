package com.yang.testdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.testdemo.R;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by yangle on 2016/6/30.
 */
public class RxMoreActivity extends Activity {

    @Bind(R.id.tv_rx)
    TextView tvRx;

    final String[] manyWords = {"Hello", "I", "am", "your", "friend", "Spike"};
    final List<String> manyWordList = Arrays.asList(manyWords);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_more);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_rx)
    public void onClick() {
        //添加字符串，省略Action的其他方法，只使用一个onNext.
        Observable<String> obShow = Observable.just(sayMyName());
        //先映射，在设置TextView
        obShow.observeOn(AndroidSchedulers.mainThread())
                .map(upperLetterFunc)
                .subscribe(textViewAction);

        //单独显示数组中的每一个元素
        Observable<String> obMap = Observable.from(manyWordList);
        //映射之后分发
        obMap.observeOn(AndroidSchedulers.mainThread())
                .map(upperLetterFunc)
                .subscribe(toastAction);

        //优化过的代码，直接获取数组，再分发再合并，再显示Toast
        Observable.just(manyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(oneLetterFunc)
                .reduce(mergeStringFunc)
                .subscribe(toastAction);
    }

    //Action类似订阅者，设置TextView
    private Action1<String> textViewAction = new Action1<String>() {
        @Override
        public void call(String s) {
            tvRx.setText(s);
        }
    };

    //Action设置Toast
    private Action1<String> toastAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(RxMoreActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };

    //设置映射函数
    private Func1<List<String>, Observable<String>> oneLetterFunc = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> list) {
            //映射字符串
            return Observable.from(list);
        }
    };

    //设置大写字母
    private Func1<String, String> upperLetterFunc = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    //连接字符串
    private Func2<String, String, String> mergeStringFunc = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s2) {
            return String.format("%s %s", s, s2);
        }
    };

    //创建字符串
    private String sayMyName() {
        return "Hello, I am your friend, Spike!";
    }
}
