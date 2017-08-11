package com.yang.testdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.yang.testdemo.R;
import com.yang.testdemo.adapter.ChoiceQuestionAdapter;
import com.yang.testdemo.bean.QuestionOption;
import com.yang.testdemo.helper.OnStartDragListener;
import com.yang.testdemo.helper.SimpleItemTouchHelperCallback;
import com.yang.testdemo.utils.DensityUtils;
import com.yang.testdemo.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选择题
 * Created by yangle on 2017/8/10.
 */

public class ChoiceQuestionActivity extends BaseActivity implements OnStartDragListener {

    @Bind(R.id.rv_option)
    RecyclerView rvOption;

    private ChoiceQuestionAdapter choiceQuestionAdapter;
    private ItemTouchHelper itemTouchHelper;
    private List<QuestionOption> questionOptionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_question);
        ButterKnife.bind(this);

        initData();
        choiceQuestionAdapter = new ChoiceQuestionAdapter(this, this, questionOptionList);
        rvOption.setLayoutManager(new LinearLayoutManager(this));
        rvOption.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 20)));
        rvOption.setAdapter(choiceQuestionAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(choiceQuestionAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvOption);

        choiceQuestionAdapter.setOnItemClickListener(new ChoiceQuestionAdapter.OnItemClickListener() {
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

                choiceQuestionAdapter.notifyDataSetChanged();
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

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
