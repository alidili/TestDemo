package com.yang.testdemo.bean;

/**
 * 问题选项
 * Created by yangle on 2017/8/10.
 */

public class QuestionOption {

    /**
     * 选项内容
     */
    private String optionContent;
    /**
     * 是否选中
     */
    private boolean isSelected;

    public QuestionOption(String optionContent, boolean isSelected) {
        this.optionContent = optionContent;
        this.isSelected = isSelected;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
