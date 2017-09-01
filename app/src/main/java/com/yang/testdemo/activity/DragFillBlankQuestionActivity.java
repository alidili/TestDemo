package com.yang.testdemo.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 拖拽填空
 * Created by yangle on 2017/8/24.
 */

public class DragFillBlankQuestionActivity extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.btn_answer1)
    Button btnAnswer1;
    @Bind(R.id.btn_answer2)
    Button btnAnswer2;
    @Bind(R.id.rl_container)
    RelativeLayout rlContainer;

    // 答案范围集合
    private List<Range> rangeList;
    // 答案集合
    private List<String> answerList;
    private SpannableStringBuilder spannableStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_fill_blank_question);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        rangeList = new ArrayList<>();
        rangeList.add(new Range(5, 10));
        rangeList.add(new Range(20, 25));
        rangeList.add(new Range(32, 37));

        answerList = new ArrayList<>();
        for (int i = 0; i < rangeList.size(); i++) {
            answerList.add("");
        }

        String content = "纷纷扬扬的_____下了半尺多厚。天地间_____的一片。我顺着_____工地走了四十多公里，只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。";
        spannableStringBuilder = new SpannableStringBuilder(content);
        tvContent.setText(spannableStringBuilder);

        btnAnswer1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnAnswer1.getText().toString());
                ClipData data = new ClipData(null,
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return true;
            }
        });

        btnAnswer2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(btnAnswer2.getText().toString());
                ClipData data = new ClipData(null,
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item);
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return true;
            }
        });

        tvContent.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED: // 拖拽开始事件
                        Log.i("ACTION_DRAG_STARTED", "x:" + event.getX() + "___y:" + event.getY());
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        }
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED: // 被拖放View进入目标View
                        Log.i("ACTION_DRAG_ENTERED", "x:" + event.getX() + "___y:" + event.getY());
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.i("ACTION_DRAG_LOCATION", "x:" + event.getX() + "___y:" + event.getY());
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED: // 被拖放View离开目标View
                        Log.i("ACTION_DRAG_EXITED", "x:" + event.getX() + "___y:" + event.getY());
                        return true;

                    case DragEvent.ACTION_DROP:
                        Log.i("ACTION_DROP", "x:" + event.getX() + "___y:" + event.getY());
                        int position = 0;

                        // 获取TextView的Layout对象
                        Layout layout = tvContent.getLayout();
                        Rect bound = new Rect();
                        int line = layout.getLineForOffset(position);
                        layout.getLineBounds(line, bound);

                        // 当前x、y坐标
                        float currentX = event.getX();
                        float currentY = event.getY();

                        // 如果拖拽答案没有进行填空则return
                        boolean isContinue = false;

                        for (int i = 0; i < rangeList.size(); i++) {
                            Range range = rangeList.get(i);
                            // 字符顶部y坐标
                            int yAxisTop = bound.top;
                            // 字符底部y坐标
                            int yAxisBottom = bound.bottom;
                            // 字符左边x坐标
                            float xAxisLeft = layout.getPrimaryHorizontal(range.start);
                            // 字符右边x坐标
                            float xAxisRight = layout.getSecondaryHorizontal(range.end);

                            Log.i("ACTION_DROP", "yAxisTop:" + yAxisTop + "___yAxisBottom:" + yAxisBottom +
                                    "___xAxisLeft:" + xAxisLeft + "___xAxisRight:" + xAxisRight);

                            if (currentX > xAxisLeft && currentX < xAxisRight &&
                                    currentY < yAxisBottom && currentY > yAxisTop) {
                                position = i;
                                isContinue = true;
                                break;
                            }
                        }

                        if (!isContinue) {
                            return true;
                        }

                        // 释放拖放阴影，并获取移动数据
                        ClipData.Item item = event.getClipData().getItemAt(0);
                        String answer = " " + item.getText().toString() + " ";

                        // 替换答案
                        Range range = rangeList.get(position);
                        spannableStringBuilder.replace(range.start, range.end, answer);

                        // 更新当前的答案范围
                        Range currentRange = new Range(range.start, range.start + answer.length());
                        rangeList.set(position, currentRange);

                        // 答案设置下划线
                        spannableStringBuilder.setSpan(new UnderlineSpan(),
                                currentRange.start, currentRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        tvContent.setText(spannableStringBuilder);

                        // 添加答案
                        answerList.set(position, answer);

                        for (int i = 0; i < rangeList.size(); i++) {
                            if (i > position) {
                                // 获取下一个答案原来的范围
                                Range oldNextRange = rangeList.get(i);
                                int oldNextAmount = oldNextRange.end - oldNextRange.start;
                                // 计算新旧答案字数的差值
                                int difference = currentRange.end - range.end;

                                // 更新下一个答案的范围
                                Range nextRange = new Range(oldNextRange.start + difference,
                                        oldNextRange.start + difference + oldNextAmount);
                                rangeList.set(i, nextRange);

                                if (!TextUtils.isEmpty(answerList.get(i))) {
                                    // 答案设置下划线
                                    spannableStringBuilder.setSpan(new UnderlineSpan(),
                                            nextRange.start, nextRange.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }

                                tvContent.setText(spannableStringBuilder);
                                break;
                            }
                        }
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED: // 拖放事件完成
                        Log.i("ACTION_DRAG_ENDED", "x:" + event.getX() + "___y:" + event.getY());
                        return true;

                    default:
                        break;
                }
                return false;
            }
        });
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
