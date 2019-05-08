package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;

import com.star.plugin.markdown.model.Item;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:42
 */
public interface MarkDownType {

    /**
     * 正则表达式
     */
    String getRegex();

    /**
     * 设置Span
     */
    void setSpan(Spannable spannable, Item item, boolean edit);

    /**
     * 删除MarkDown格式
     */
    SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item);

}
