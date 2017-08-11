package com.yang.testdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.testdemo.R;
import com.yang.testdemo.bean.QuestionOption;
import com.yang.testdemo.utils.DensityUtils;

import java.util.List;

/**
 * 文字选择题
 * Created by yangle on 2017/8/10.
 */

public class TextChoiceQuestionAdapter extends RecyclerView.Adapter<TextChoiceQuestionAdapter.TextChoiceQuestionViewHolder> {

    private Context context;
    private List<QuestionOption> optionList;
    private OnItemClickListener onItemClickListener;
    private String[] options = {"A", "B", "C", "D", "E", "F"};
    private int[] optionBgId = {R.drawable.bg_frame_corner_83aff3, R.drawable.bg_frame_corner_eedd94,
            R.drawable.bg_frame_corner_acd7d6, R.drawable.bg_frame_corner_b5bddd};
    private int[] optionContentBgId = {R.drawable.bg_dceaff, R.drawable.bg_fff7d4,
            R.drawable.bg_e2fffe, R.drawable.bg_e5eaff};

    public TextChoiceQuestionAdapter(Context context, List<QuestionOption> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    @Override
    public TextChoiceQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_text_choice_question, parent, false);
        return new TextChoiceQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TextChoiceQuestionViewHolder holder, int position) {
        QuestionOption questionOption = optionList.get(position);

        holder.tvOption.setText(options[position]);
        holder.tvOptionContent.setText(questionOption.getOptionContent());

        if (questionOption.isSelected()) {
            holder.tvOption.setTextColor(Color.WHITE);
            holder.tvOption.setBackgroundResource(R.drawable.bg_corner_83aff3);
            ViewGroup.LayoutParams layoutParams = holder.tvOption.getLayoutParams();
            layoutParams.height = DensityUtils.dp2px(context, 162);
            holder.tvOption.setLayoutParams(layoutParams);
            holder.rlOptionContent.setBackgroundResource(R.drawable.bg_frame_83aff3);

        } else {
            holder.tvOption.setTextColor(Color.BLACK);
            holder.tvOption.setBackgroundResource(optionBgId[position % 4]);
            ViewGroup.LayoutParams layoutParams = holder.tvOption.getLayoutParams();
            layoutParams.height = DensityUtils.dp2px(context, 66);
            holder.tvOption.setLayoutParams(layoutParams);
            holder.rlOptionContent.setBackgroundResource(optionContentBgId[position % 4]);
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
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    class TextChoiceQuestionViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llOption;
        TextView tvOption;
        RelativeLayout rlOptionContent;
        TextView tvOptionContent;

        public TextChoiceQuestionViewHolder(View itemView) {
            super(itemView);
            llOption = (LinearLayout) itemView.findViewById(R.id.ll_option);
            tvOption = (TextView) itemView.findViewById(R.id.tv_option);
            rlOptionContent = (RelativeLayout) itemView.findViewById(R.id.rl_option_content);
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
