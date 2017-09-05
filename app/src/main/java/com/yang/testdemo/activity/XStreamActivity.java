package com.yang.testdemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.yang.testdemo.R;
import com.yang.testdemo.bean.Answer;

/**
 * XStream
 * Created by yangle on 2017/9/5.
 */

public class XStreamActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xstream);

        Answer answer = new Answer();
        answer.idx = "1";
        answer.sig = "0";
        Answer.Scene scene = answer.new Scene();
        scene.idx = "1";
        scene.type = "fc_choice";
        Answer.Scene.Ques ques = scene.new Ques();
        ques.idx = "1";
        Answer.Scene.Ques.Opt opt = ques.new Opt();
        Answer.Scene.Ques.Opt.I i = opt.new I();
        i.idx = "1";
        i.ans = "A";
        i.r = "1";
        opt.i = i;
        ques.opt = opt;
        scene.ques = ques;
        answer.scene = scene;

        XStream xStream = new XStream();
        String xml = xStream.toXML(answer);
        Log.i("答案", xml);
    }
}
