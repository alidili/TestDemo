package com.yang.testdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yang.testdemo.R;
import com.yang.testdemo.bean.QuestionOption;
import com.yang.testdemo.helper.ItemTouchHelperAdapter;
import com.yang.testdemo.helper.OnStartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * 选择题
 * Created by yangle on 2017/8/10.
 */

public class ChoiceQuestionAdapter extends RecyclerView.Adapter<ChoiceQuestionAdapter.ChoiceQuestionViewHolder>
        implements ItemTouchHelperAdapter {

    private Context context;
    private List<QuestionOption> optionList;
    private OnItemClickListener onItemClickListener;
    private OnStartDragListener dragStartListener;
    private String[] options = {"A", "B", "C", "D", "E", "F"};

    public ChoiceQuestionAdapter(Context context, OnStartDragListener dragStartListener,
                                 List<QuestionOption> optionList) {
        this.context = context;
        this.dragStartListener = dragStartListener;
        this.optionList = optionList;
    }

    @Override
    public ChoiceQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_choice_question, parent, false);
        return new ChoiceQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChoiceQuestionViewHolder holder, int position) {
        QuestionOption questionOption = optionList.get(position);

        holder.tvOption.setText(options[position]);
        holder.tvOptionContent.setText(questionOption.getOptionContent());
        if (questionOption.isSelected()) {
            holder.llOption.setBackgroundResource(R.drawable.bg_text_option_selected);
            holder.tvOption.setTextColor(Color.WHITE);
        } else {
            holder.llOption.setBackgroundResource(R.drawable.bg_text_option);
            holder.tvOption.setTextColor(Color.BLACK);
        }

        // 回调点击事件
        if (onItemClickListener != null) {
            holder.llOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }

        holder.llOption.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    dragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        optionList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(optionList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    class ChoiceQuestionViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llOption;
        TextView tvOption;
        TextView tvOptionContent;

        public ChoiceQuestionViewHolder(View itemView) {
            super(itemView);
            llOption = (LinearLayout) itemView.findViewById(R.id.ll_option);
            tvOption = (TextView) itemView.findViewById(R.id.tv_option);
            tvOptionContent = (TextView) itemView.findViewById(R.id.tv_option_content);
        }
    }

    /**
     * 设置Item点击监听
     *
     * @param onItemClickListener 点击回调接口
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 点击回调接口
     */
    public interface OnItemClickListener {
        /**
         * 点击回调方法
         *
         * @param view 当前view
         * @param pos  点击位置
         */
        void onItemClick(View view, int pos);
    }
}
