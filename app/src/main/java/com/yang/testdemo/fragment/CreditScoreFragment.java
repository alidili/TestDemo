package com.yang.testdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yang.testdemo.R;

/**
 * 芝麻信用分
 * Created by yangle on 2017/6/8.
 */

public class CreditScoreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_credit_score, null);
    }
}
