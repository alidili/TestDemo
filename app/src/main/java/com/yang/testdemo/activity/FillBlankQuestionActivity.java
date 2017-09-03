package com.yang.testdemo.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 填空题
 * Created by yangle on 2017/8/24.
 */

public class FillBlankQuestionActivity extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView tvContent;

    // 答案集合
    private List<String> answerList;
    // 答案范围集合
    private List<Range> rangeList;
    // 答案填空处ClickableSpan对象集合
    private List<BlankClickableSpan> blankClickableSpanList;
    private SpannableStringBuilder spannableStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_blank_question);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        String content = "纷纷扬扬的_____下了半尺多厚。天地间_____的一片。我顺着_____工地走了四十多公里，只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。";
        spannableStringBuilder = new SpannableStringBuilder(content);

        // 答案范围集合
        rangeList = new ArrayList<>();
        rangeList.add(new Range(5, 10));
        rangeList.add(new Range(20, 25));
        rangeList.add(new Range(32, 37));

        // 答案集合
        answerList = new ArrayList<>();
        for (int i = 0; i < rangeList.size(); i++) {
            answerList.add("");
        }

        // 设置填空处点击事件
        blankClickableSpanList = new ArrayList<>();
        for (int i = 0; i < rangeList.size(); i++) {
            Range range = rangeList.get(i);
            BlankClickableSpan blankClickableSpan = new BlankClickableSpan(i);
            spannableStringBuilder.setSpan(blankClickableSpan, range.start, range.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            blankClickableSpanList.add(blankClickableSpan);
        }

        // 设置此方法后，点击事件才能生效
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        // 设置点击高亮颜色
        tvContent.setHighlightColor(Color.GRAY);
        tvContent.setText(spannableStringBuilder);
    }

    class BlankClickableSpan extends ClickableSpan {

        private int position;

        public BlankClickableSpan(int position) {
            this.position = position;
        }

        @Override
        public void onClick(final View widget) {
            Toast.makeText(FillBlankQuestionActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();

            LayoutInflater factory = LayoutInflater.from(FillBlankQuestionActivity.this);
            final View view = factory.inflate(R.layout.layout_input, null);
            final EditText etInput = (EditText) view.findViewById(R.id.et_input);

            new AlertDialog.Builder(FillBlankQuestionActivity.this)
                    .setTitle("输入答案")
                    .setView(view)
                    .setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String answer = " " + etInput.getText().toString() + " ";
                            // 移除原来的点击事件
                            spannableStringBuilder.removeSpan(blankClickableSpanList.get(position));

                            // 替换答案
                            Range range = rangeList.get(position);
                            spannableStringBuilder.replace(range.start, range.end, answer);

                            // 更新当前的答案范围
                            Range currentRange = new Range(range.start, range.start + answer.length());
                            rangeList.set(position, currentRange);

                            // 重新设置点击事件
                            spannableStringBuilder.setSpan(new BlankClickableSpan(position),
                                    currentRange.start, currentRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                            // 答案设置下划线
                            spannableStringBuilder.setSpan(new UnderlineSpan(),
                                    currentRange.start, currentRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tvContent.setText(spannableStringBuilder);

                            // 将答案添加到集合中
                            answerList.set(position, answer);

                            for (int i = 0; i < rangeList.size(); i++) {
                                if (i > position) {
                                    // 获取下一个答案原来的范围
                                    Range oldNextRange = rangeList.get(i);
                                    int oldNextAmount = oldNextRange.end - oldNextRange.start;
                                    // 计算新旧答案字数的差值
                                    int difference = currentRange.end - range.end;

                                    // 移除原来的点击事件
                                    spannableStringBuilder.removeSpan(blankClickableSpanList.get(i));

                                    // 更新下一个答案的范围
                                    Range nextRange = new Range(oldNextRange.start + difference,
                                            oldNextRange.start + difference + oldNextAmount);
                                    rangeList.set(i, nextRange);

                                    // 重新设置点击事件
                                    spannableStringBuilder.setSpan(new BlankClickableSpan(i),
                                            nextRange.start, nextRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    if (!TextUtils.isEmpty(answerList.get(i))) {
                                        // 答案设置下划线
                                        spannableStringBuilder.setSpan(new UnderlineSpan(),
                                                nextRange.start, nextRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    }

                                    // 更新下一个答案的点击范围
                                    tvContent.setText(spannableStringBuilder);
                                }
                            }
                        }
                    })
                    .create()
                    .show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            // 不显示下划线
            ds.setUnderlineText(false);
        }
    }

    class Range {

        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}
