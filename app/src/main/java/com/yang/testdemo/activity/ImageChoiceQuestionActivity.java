package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yang.testdemo.R;
import com.yang.testdemo.adapter.ImageChoiceQuestionAdapter;
import com.yang.testdemo.bean.QuestionOption;
import com.yang.testdemo.utils.DensityUtils;
import com.yang.testdemo.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 图文选择题
 * Created by yangle on 2017/8/10.
 */

public class ImageChoiceQuestionActivity extends BaseActivity {

    @Bind(R.id.rv_option)
    RecyclerView rvOption;

    private ImageChoiceQuestionAdapter imageChoiceQuestionAdapter;
    private List<QuestionOption> questionOptionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_question);
        ButterKnife.bind(this);

        initData();
        imageChoiceQuestionAdapter = new ImageChoiceQuestionAdapter(this, questionOptionList);
        rvOption.setLayoutManager(new GridLayoutManager(this, 2));
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(DensityUtils.dp2px(this, 22));
        spaceItemDecoration.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvOption.addItemDecoration(spaceItemDecoration);
        rvOption.setAdapter(imageChoiceQuestionAdapter);

        imageChoiceQuestionAdapter.setOnItemClickListener(new ImageChoiceQuestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                QuestionOption questionOption = questionOptionList.get(pos);
                boolean isSelected = questionOption.isSelected();

                // 单选
                if (!isSelected) {
                    questionOption.setSelected(true);
                    for (int i = 0; i < questionOptionList.size(); i++) {
                        if (i != pos) {
                            questionOptionList.get(i).setSelected(false);
                        }
                    }
                }

                // 多选
                /*if (isSelected) {
                    questionOption.setSelected(false);
                } else {
                    questionOption.setSelected(true);
                }*/

                imageChoiceQuestionAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        questionOptionList = new ArrayList<>();
        questionOptionList.add(new QuestionOption("选项一", false));
        questionOptionList.add(new QuestionOption("选项二", false));
        questionOptionList.add(new QuestionOption("选项三", false));
        questionOptionList.add(new QuestionOption("选项四", false));
    }
}
